import { ReactNode, useState } from "react";

import { Connection, WalletContext } from "./context";

export default function WalletProvider(props: { children: ReactNode }) {
  const Children = () => <>{props.children}</>;

  return (
    <WalletContext.Provider value={useState<Connection | undefined>(undefined)}>
      <Children />
    </WalletContext.Provider>
  );
}
