'use client';

import { useEffect, useState } from 'react';
import { init, capture, identify } from '@/lib/analytics-sdk';

export default function Home() {
  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    init({
      apiEndpoint: '/api/analytics',
      flushInterval: 2000,
    });
    setMounted(true);
    capture('page_view', { path: '/' });
  }, []);

  const handleTrack = () => {
    capture('button_click', { button_id: 'track_btn', random_val: Math.random().toString() });
  };

  const handleIdentify = () => {
    const userId = 'user_' + Math.floor(Math.random() * 1000);
    identify(userId);
    capture('identify', { new_user_id: userId });
    alert(`Identified as ${userId}`);
  };

  if (!mounted) return null;

  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <main className="flex flex-col gap-8 row-start-2 items-center sm:items-start">
        <h1 className="text-4xl font-bold">Analytics SDK Demo</h1>
        <p className="text-lg">Open the server console to see the Protobuf events arriving.</p>

        <div className="flex gap-4">
          <button
            onClick={handleTrack}
            className="rounded-full border border-solid border-transparent transition-colors flex items-center justify-center bg-foreground text-background gap-2 hover:bg-[#383838] dark:hover:bg-[#ccc] text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5"
          >
            Track Event
          </button>
          <button
            onClick={handleIdentify}
            className="rounded-full border border-solid border-black/[.08] dark:border-white/[.145] transition-colors flex items-center justify-center hover:bg-[#f2f2f2] dark:hover:bg-[#1a1a1a] hover:border-transparent text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5"
          >
            Identify User
          </button>
        </div>
      </main>
    </div>
  );
}