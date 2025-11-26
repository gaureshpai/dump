import { useState } from "react";
import { Button } from "@heroui/button";
import { Input } from "@heroui/input";
import { Modal, ModalBody, ModalContent, ModalFooter, ModalHeader, useDisclosure } from "@heroui/modal";
import { Address } from "@evolution-sdk/lucid";

import { Action } from "@/types/action";

export default function BasicTransfer(props: { onTransfer: Action; defaultChangeAddress: Address; }) {
  const { onTransfer, defaultChangeAddress } = props;

  const { isOpen, onOpen, onOpenChange } = useDisclosure();

  const [toAddress, setToAddress] = useState<Address>();
  const [lovelace, setLovelace] = useState(0n);
  const [changeAddress, setChangeAddress] = useState<Address>();

  return (<>
    <div className="flex flex-wrap gap-2 mb-2">
      <Button className="bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg" radius="full" onPress={onOpen}>
        Transfer
      </Button>
    </div>

    <Modal isOpen={isOpen} placement="top-center" onOpenChange={onOpenChange}>
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">Transfer</ModalHeader>
            <ModalBody>
              <Input label="To Address" placeholder=" " variant="bordered" onValueChange={setToAddress} />
              <Input
                label="Quantity"
                placeholder="0.000000"
                startContent={
                  <div className="pointer-events-none flex items-center">
                    <span className="text-default-400 text-small">ADA</span>
                  </div>
                }
                type="number"
                variant="bordered"
                onValueChange={(value: string) => setLovelace(BigInt(parseFloat(value) * 1_000000))}
              />
              <Input
                defaultValue={defaultChangeAddress}
                label="Change Address"
                placeholder="Address to send change to"
                variant="bordered"
                onValueChange={setChangeAddress}
              />
            </ModalBody>
            <ModalFooter>
              <Button
                className="bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg"
                radius="full"
                onPress={() => onTransfer({ toAddress, lovelace, changeAddress }).then(onClose)}
              >
                Submit
              </Button>
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  </>);
}
