import { createContext, Dispatch, SetStateAction, useContext } from "react";
import { Address, LucidEvolution, PaymentKeyHash, RewardAddress, StakeKeyHash, WalletApi } from "@evolution-sdk/lucid";

export type Connection = {
  api: WalletApi;
  lucid: LucidEvolution;

  address: Address;
  pkh: PaymentKeyHash;

  stakeAddress: RewardAddress | null;
  skh: StakeKeyHash | null;
};

export const WalletContext =
  createContext<[Connection | undefined, Dispatch<SetStateAction<Connection | undefined>>]>([undefined, () => undefined]);
export const useWallet =
  () => useContext(WalletContext);
