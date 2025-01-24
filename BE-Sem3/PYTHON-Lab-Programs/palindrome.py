def is_palindrome(number):
    num_str = str(number)
    return num_str ==  num_str[::-1]

def count_digit(number):
    num_str = str(number)
    digit_occur = {str(i):0 for i in range(10)}
    for digit in num_str:
        digit_occur[digit] +=1
    return digit_occur

input_number = int(input("Enter a number: "))

if is_palindrome(input_number):
    print(f"{input_number} is a palindrome")
else:
    print(f"{input_number} is not a palindrome")
    
occur = count_digit(input_number)
print("Digit occurences:")

for digit,count in occur.items():
    print(f"Digit {digit}:{count} times.")
