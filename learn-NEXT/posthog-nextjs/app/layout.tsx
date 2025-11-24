import './globals.css'
import { PostHogProvider } from '@/components/providers'
import "./globals.css";
import { Suspense } from 'react';

export const metadata = {
  title: "posthog-nextjs",
  description: "This is the posthog-nextjs project",
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body>
        <Suspense>
          <PostHogProvider>
            {children}
          </PostHogProvider>
        </Suspense>
      </body>
    </html>
  )
}