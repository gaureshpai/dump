s = input("Enter a sentence:")
w,d,u,l = 0,0,0,0
l_w = s.split()
w = len(l_w)

for ch in s:
    if ch.isdigit():
        d+=1
    elif ch.isupper():
        u+=1
    elif ch.islower():
        l+=1

print("Number of words:",w)
print("Number of digits:",d)
print("Number of uppercase letters:",u)
print("Number of lowercase letters:",l)

