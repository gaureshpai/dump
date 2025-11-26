import { useState } from "react";
import { Button } from "@heroui/button";
import { Input } from "@heroui/input";
import { Modal, ModalBody, ModalContent, ModalFooter, ModalHeader, useDisclosure } from "@heroui/modal";

import { Action } from "@/types/action";

export default function HelloWorld(props: { onDeploy: Action; onMint: Action; onSpend: Action; }) {
  const { onDeploy, onMint, onSpend } = props;

  const { isOpen, onOpen, onOpenChange } = useDisclosure();

  const [tokenName, setTokenName] = useState("");
  const [imageURL, setImageURL] = useState("https://duckduckgo.com/i/b7a47367f8a759f5.jpg");

  return (<>
    <div className="flex flex-wrap gap-2 mb-2">
      <Button className="bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg" radius="full" onPress={onDeploy}>
        Deploy
      </Button>

      <Button className="bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg" radius="full" onPress={onOpen}>
        Mint
      </Button>

      <Button className="bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg" radius="full" onPress={onSpend}>
        Spend
      </Button>
    </div>

    <Modal id="mint-modal" isOpen={isOpen} placement="top-center" onOpenChange={onOpenChange}>
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">Mint</ModalHeader>
            <ModalBody>
              <Input
                label="Token Name" placeholder=" "
                variant="bordered" onValueChange={setTokenName}
              />
              <Input
                label="Image URL" defaultValue="https://duckduckgo.com/i/b7a47367f8a759f5.jpg" maxLength={64}
                variant="bordered" onValueChange={setImageURL}
              />
            </ModalBody>
            <ModalFooter>
              <Button
                className="bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg"
                radius="full"
                onPress={() => onMint({ tokenName, imageURL }).then(onClose)}
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
