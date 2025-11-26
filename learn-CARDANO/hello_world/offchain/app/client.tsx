"use client";

import dynamic from "next/dynamic";

const Home = dynamic(() => import("@/app/Home"), { ssr: false });

export default function Client() {
  return <Home />;
}
