import { Button } from "@heroui/button";

import {} from "@evolution-sdk/lucid";
import { Wallet } from "@/types/cardano";

export default function WalletConnector(props: { onConnectWallet: (wallet: Wallet) => void; }) {
  function getWallets() {
    const wallets: Wallet[] = [];
    const { cardano } = window;

    for (const c in cardano) {
      const wallet = cardano[c];

      if (!wallet.apiVersion) continue;

      wallets.push(wallet);
    }

    return wallets.sort((l, r) => {
      return l.name.toUpperCase() < r.name.toUpperCase() ? -1 : 1;
    });
  }

  const wallets = getWallets();

  if (!wallets.length) return <span className="uppercase">No Cardano Wallet</span>;

  return (
    <div className="flex flex-wrap gap-2">
      {wallets.map((wallet, w) => (
        <Button
          key={`wallet.${w}`}
          className="bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg capitalize"
          radius="full"
          onPress={() => props.onConnectWallet(wallet)}
        >
          {wallet.name}
        </Button>
      ))}
    </div>
  );
}
