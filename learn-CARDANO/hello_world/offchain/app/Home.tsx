import { useState } from "react";
import { Lucid, paymentCredentialOf, stakeCredentialOf } from "@evolution-sdk/lucid";

import { Wallet } from "@/types/cardano";
import { Connection, useWallet } from "@/components/connection/context";
import { getErrorMessage } from "@/components/utils";
import { network, provider } from "@/config/lucid";
import WalletConnector from "@/components/pages/Home/WalletConnector";
import Dashboard from "@/components/pages/Home/Dashboard";

export default function Home() {
  //#region Wallet Connection
  const [connection, setConnection] = useWallet();

  async function connectWallet(wallet: Wallet): Promise<Connection> {
    const [api, lucid] = await Promise.all([wallet.enable(), Lucid(provider, network)]);

    lucid.selectWallet.fromAPI(api);

    const [address, stakeAddress] = await Promise.all([lucid.wallet().address(), lucid.wallet().rewardAddress()]);

    const pkh = paymentCredentialOf(address).hash;
    const skh = stakeAddress ? stakeCredentialOf(stakeAddress).hash : null;

    return { api, lucid, address, pkh, stakeAddress, skh };
  }

  const onConnectWallet = (wallet: Wallet) => connectWallet(wallet).then(setConnection).catch(handleError);
  //#endregion

  const [result, setResult] = useState("");

  const handleError = (error: any) => getErrorMessage(error).then(setResult).catch(console.error);

  return (
    <div className="flex justify-center overflow-hidden">
      <div className="flex flex-col gap-2 overflow-hidden">
        {connection ? (
          // wallet connected: Show Dashboard
          <Dashboard setActionResult={setResult} onError={handleError} />
        ) : (
          // no wallet connected yet: Show Wallet button List
          <WalletConnector onConnectWallet={onConnectWallet} />
        )}
        <span className="font-mono break-words whitespace-pre-wrap">{result}</span>
      </div>
    </div>
  );
}
