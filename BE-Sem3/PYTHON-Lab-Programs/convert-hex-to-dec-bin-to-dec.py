def binary_to_decimal(binary):
    decimal = 0
    for digit in binary:
        decimal = decimal * 2 + int(digit)
    return decimal

def octal_to_hexadecimal(octal):
    decimal = 0
    for digit in octal:
        decimal = decimal * 8 + int(digit)
    hexadecimal = hex(decimal).lstrip("0x").upper()
    return hexadecimal

binary = input("Enter a binary number: ")
octal = input("Enter an octal number: ")

decimal = binary_to_decimal(binary)

print("The decimal equivalent of", binary, "is:", decimal)

hexadecimal = octal_to_hexadecimal(octal)

print("The hexadecimal equivalent of", octal, "is:", hexadecimal)
