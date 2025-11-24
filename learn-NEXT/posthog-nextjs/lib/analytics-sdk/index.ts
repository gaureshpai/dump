import { Analytics, AnalyticsConfig } from './core';

let analyticsInstance: Analytics | null = null;

export const init = (config: AnalyticsConfig) => {
    if (!analyticsInstance) {
        analyticsInstance = new Analytics(config);
    }
    return analyticsInstance;
};

export const capture = (event: string, properties?: Record<string, string>) => {
    if (analyticsInstance) {
        analyticsInstance.capture(event, properties);
    } else {
        console.warn('[Analytics] Not initialized. Call init() first.');
    }
};

export const identify = (distinctId: string) => {
    if (analyticsInstance) {
        analyticsInstance.identify(distinctId);
    } else {
        console.warn('[Analytics] Not initialized. Call init() first.');
    }
};

export default {
    init,
    capture,
    identify,
};
