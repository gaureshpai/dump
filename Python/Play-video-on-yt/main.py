import pywhatkit;

try:
    song = input("Enter the title of the Video to be played: ")
    pywhatkit.playonyt(song)
except:
    print("An unexpected error occured")