az version # to get the version iof Azure

az interactive # to enter interactive mode

az vm create --resource-group "[sandbox resource group name]" --name my-vm --public-ip-sku Standard --image Ubuntu2204 --admin-username azureuser --generate-ssh-keys # to create a VM

az vm extension set --resource-group "[sandbox resource group name]" --vm-name my-vm --name customScript --publisher Microsoft.Azure.Extensions --version 2.1 --settings '{"fileUris":["https://raw.githubusercontent.com/MicrosoftDocs/mslearn-welcome-to-azure/master/configure-nginx.sh"]}' --protected-settings '{"commandToExecute": "./configure-nginx.sh"}' # to install nginx on the VM

IPADDRESS="$(az vm list-ip-addresses --resource-group "IntroAzureRG" --name my-vm --query "[].virtualMachine.network.publicIpAddresses[*].ipAddress" --output tsv)" # to get the public IP address of the VM

curl --connect-timeout 5 http://$IPADDRESS # to test the nginx installation

echo $IPADDRESS # to display the IP address

az network nsg list --resource-group "IntroAzureRG" --query '[].name' --output tsv # to list the network security groups in the resource group

az network nsg rule list --resource-group "IntroAzureRG" --nsg-name my-vmNSG # to list the rules in the network security group

az network nsg rule list --resource-group "IntroAzureRG" --nsg-name my-vmNSG --query '[].{Name:name, Priority:priority, Port:destinationPortRange, Access:access}' --output table # to list the rules in a table format

az network nsg rule create --resource-group "IntroAzureRG" --nsg-name my-vmNSG --name allow-http --protocol tcp --priority 100 --destination-port-range 80 --access Allow # to create a rule to allow HTTP traffic

az network nsg rule list --resource-group "IntroAzureRG" --nsg-name my-vmNSG --query '[].{Name:name, Priority:priority, Port:destinationPortRange, Access:access}' --output table # to verify the new rule

curl --connect-timeout 5 http://$IPADDRESS # to test the nginx installation again