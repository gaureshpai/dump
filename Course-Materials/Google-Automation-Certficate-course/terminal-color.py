import colorama
from colorama import Fore

user_name = input("Please enter your name: ")
print("Welcome!!! "+user_name)
print(user_name + " likes " + Fore.BLUE+ "Blue")
print(user_name + " likes " + Fore.RED+ "Red")