import { Accordion, AccordionItem } from "@heroui/accordion";
import {
  Address,
  Data,
  fromText,
  Lovelace,
  mintingPolicyToId,
  PolicyId,
  toUnit,
  TxSignBuilder,
  Validator,
  validatorToAddress,
} from "@evolution-sdk/lucid";

import { network } from "@/config/lucid";
import { Script } from "@/config/script";
import { ActionGroup } from "@/types/action";
import { useWallet } from "@/components/connection/context";
import BasicTransfer from "@/components/actions/BasicTransfer";
import HelloWorld from "@/components/actions/HelloWorld";

export default function Dashboard(props: { setActionResult: (result: string) => void; onError: (error: any) => void; }) {
  const [connection] = useWallet();

  if (!connection) return <span className="uppercase">Wallet Disconnected</span>;

  const { api, lucid, address, pkh } = connection;

  async function submitTx(tx: TxSignBuilder) {
    const txSigned = await tx.sign.withWallet().complete();
    const txHash = await txSigned.submit();

    return txHash;
  }

  const actions: Record<string, ActionGroup> = {
    //#region No smart-contract interaction
    BasicTransaction: {
      transfer: async ({ toAddress, lovelace, changeAddress }: { toAddress: Address; lovelace: Lovelace; changeAddress: Address; }) => {
        try {
          if (!lucid.wallet()) lucid.selectWallet.fromAPI(api);

          const tx = await lucid
            .newTx()
            .pay.ToAddress(toAddress, { lovelace })
            .validTo(new Date().getTime() + 15 * 60_000) // ~15 minutes
            .complete({ changeAddress });

          submitTx(tx).then(props.setActionResult).catch(props.onError);
        } catch (error) {
          props.onError(error);
        }
      },
    },
    //#endregion

    //#region Smart-contract interactions
    HelloWorld: {
      deploy: async () => {
        try {
          if (!lucid.wallet()) lucid.selectWallet.fromAPI(api);

          const validator: Validator = { type: "PlutusV3", script: Script.HelloWorld };
          const validatorAddress = validatorToAddress(network, validator);

          const tx = await lucid
            .newTx()
            .pay.ToAddressWithData(
              validatorAddress,
              undefined,
              undefined,
              validator,
            )
            .validTo(new Date().getTime() + 15 * 60_000) // ~15 minutes
            .complete();

          submitTx(tx).then(props.setActionResult).catch(props.onError);
        } catch (error) {
          props.onError(error);
        }
      },

      mint: async ({ tokenName, imageURL }: { tokenName: string; imageURL: string; }) => {
        try {
          if (!lucid.wallet()) lucid.selectWallet.fromAPI(api);

          const validator: Validator = { type: "PlutusV3", script: Script.HelloWorld };
          const validatorAddress = validatorToAddress(network, validator);
          const mintingPolicy: PolicyId = mintingPolicyToId(validator);

          const assetName = fromText(tokenName);
          const assetUnit = toUnit(mintingPolicy, assetName);

          const redeemer = Data.to(assetName);

          const utxos = await lucid.utxosAt(validatorAddress);
          const refUTxO = utxos.find(({ scriptRef }) => validator.script === scriptRef?.script);

          const newTx = lucid.newTx();

          if (refUTxO) newTx.readFrom([refUTxO]);
          else newTx.attach.Script(validator);

          const tx = await newTx
            .mintAssets(
              { [assetUnit]: 1n },
              redeemer,
            )
            .attachMetadata(
              721,
              {
                [mintingPolicy]: {
                  [assetName]: {
                    name: tokenName,
                    image: imageURL,
                  },
                },
              },
            )
            .validTo(new Date().getTime() + 15 * 60_000) // ~15 minutes
            .complete();

          submitTx(tx).then(props.setActionResult).catch(props.onError);
        } catch (error) {
          props.onError(error);
        }
      },

      spend: async () => {
        try {
          if (!lucid.wallet()) lucid.selectWallet.fromAPI(api);

          const validator: Validator = { type: "PlutusV3", script: Script.HelloWorld };
          const validatorAddress = validatorToAddress(network, validator);

          // const redeemer = Data.to("abcd");
          // const redeemer = Data.to(43n);
          const redeemer = Data.to(42n);

          const utxos = await lucid.utxosAt(validatorAddress);

          const tx = await lucid
            .newTx()
            .collectFrom(utxos, redeemer)
            .attach.SpendingValidator(validator)
            .validTo(new Date().getTime() + 15 * 60_000) // ~15 minutes
            .complete();

          submitTx(tx).then(props.setActionResult).catch(props.onError);
        } catch (error) {
          props.onError(error);
        }
      },
    },
    //#endregion
  };

  return (
    <div className="flex flex-col gap-2">
      <span>{address}</span>

      <Accordion variant="splitted">
        {/* No SC */}
        <AccordionItem key="0" aria-label="Accordion 0" title="Basic Transaction (no smart-contract interaction)">
          <BasicTransfer defaultChangeAddress={address} onTransfer={actions.BasicTransaction.transfer} />
        </AccordionItem>

        {/* Hello World */}
        <AccordionItem key="1" aria-label="Accordion 1" title="Hello World">
          <HelloWorld onDeploy={actions.HelloWorld.deploy} onMint={actions.HelloWorld.mint} onSpend={actions.HelloWorld.spend} />
        </AccordionItem>
      </Accordion>
    </div>
  );
}
