def fn(n):
    if n == 1:
        return 0
    elif n == 2:
        return 1
    else:
        return fn(n-1)+fn(n-2)
    
num = int(input("Enter a number: "))

if num > 0:
    print("fn(",num,")=",fn(num),sep=" ")
else:
    print("Error in input")