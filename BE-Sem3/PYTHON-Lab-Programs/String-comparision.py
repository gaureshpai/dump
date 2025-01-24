str1 = input("Enter string 1:")
str2 = input("Enter string 2:")

if len(str2)<len(str1):
    short = len(str2)
    long = len(str1)

else:
    short = len(str1)
    long = len(str2)
    
matchCnt = 0

for i in range(short):
    if str1[i] == str2[i]:
        matchCnt += 1

print("Similarity between two said strings:")
print(matchCnt/long)