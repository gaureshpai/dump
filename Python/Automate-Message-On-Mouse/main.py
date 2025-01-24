import pyautogui
import time

time.sleep(4) #wait for 4 seconds

count = 0

while count <= 100:
    pyautogui.typewrite("Hello"+ str(count))
    pyautogui.press("enter")
    count = count + 1