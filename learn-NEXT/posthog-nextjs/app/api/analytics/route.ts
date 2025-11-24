import { NextRequest, NextResponse } from 'next/server';
import { analytics } from '@/lib/analytics-sdk/proto/analytics';

export async function POST(req: NextRequest) {
    try {
        const arrayBuffer = await req.arrayBuffer();
        const buffer = new Uint8Array(arrayBuffer);

        // Decode the message
        const message = analytics.Event.decode(buffer);

        // Convert to plain object
        const object = analytics.Event.toObject(message, {
            longs: String,
            enums: String,
            bytes: String,
        });

        console.log('--------------------------------------------------');
        console.log('Received Analytics Event:');
        console.log(JSON.stringify(object, null, 2));
        console.log('--------------------------------------------------');

        return NextResponse.json({ success: true });
    } catch (error) {
        console.error('Error processing analytics event:', error);
        return NextResponse.json({ success: false, error: 'Invalid protobuf message' }, { status: 400 });
    }
}
