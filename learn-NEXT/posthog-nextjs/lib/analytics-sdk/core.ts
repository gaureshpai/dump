import { analytics } from './proto/analytics';

export interface AnalyticsConfig {
  apiEndpoint: string;
  flushInterval?: number;
  maxQueueSize?: number;
}

export class Analytics {
  private config: AnalyticsConfig;
  private queue: analytics.IEvent[] = [];
  private distinctId: string = 'anonymous';
  private flushTimer: NodeJS.Timeout | null = null;

  constructor(config: AnalyticsConfig) {
    this.config = {
      flushInterval: 5000,
      maxQueueSize: 10,
      ...config,
    };
    this.startFlushTimer();
  }

  public identify(distinctId: string) {
    this.distinctId = distinctId;
  }

  public capture(eventName: string, properties: Record<string, string> = {}) {
    const event: analytics.IEvent = {
      messageId: this.generateId(),
      timestamp: new Date().toISOString(),
      event: eventName,
      distinctId: this.distinctId,
      properties: properties,
    };

    this.queue.push(event);

    if (this.queue.length >= (this.config.maxQueueSize || 10)) {
      this.flush();
    }
  }

  private async flush() {
    if (this.queue.length === 0) return;

    const eventsToSend = [...this.queue];
    this.queue = [];

    try {
      // In a real world scenario, we might batch these into a single message
      // For this demo, we'll send them one by one or as a list if the API supported it.
      // Let's send them one by one for simplicity with the current proto definition,
      // or we could define a Batch message.
      // Sending one by one is inefficient but easiest for this demo without changing proto.
      
      // Wait, let's just send the raw bytes of each event in a loop or Promise.all
      // Or better, let's change the proto to support a Batch if we want to be "good".
      // But sticking to the plan: "Send events to an API endpoint".
      
      const promises = eventsToSend.map(async (event) => {
        const message = analytics.Event.create(event);
        const buffer = analytics.Event.encode(message).finish();
        const arrayBuffer = buffer.slice().buffer;

        await fetch(this.config.apiEndpoint, {
          method: 'POST',
          body: arrayBuffer,
          headers: {
            'Content-Type': 'application/x-protobuf',
          },
        });
      });

      await Promise.all(promises);
      console.log(`[Analytics] Flushed ${eventsToSend.length} events.`);
    } catch (error) {
      console.error('[Analytics] Failed to flush events:', error);
      // Re-queue events? For simplicity, we drop them here.
    }
  }

  private startFlushTimer() {
    if (this.flushTimer) clearInterval(this.flushTimer);
    this.flushTimer = setInterval(() => {
      this.flush();
    }, this.config.flushInterval);
  }

  private generateId(): string {
    return Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
  }
}
