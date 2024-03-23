m1 = int(input("Entere marks of test 1:"))
m2 = int(input("Entere marks of test 2:"))
m3 = int(input("Entere marks of test 3:"))
if m1<=m2 and m1<=m3:
    average = (m2+m3)/2
elif m2<=m1 and m2<=m3:
    average = (m1+m3)/2
elif m3<=m1 and m3<=m2:
    average = (m2+m1)/2
print("Average of best two test is: ",average)