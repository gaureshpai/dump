import os
import csv

def create_file(filename):
    with open(filename, "w") as file:
        file.write("name,color,type\n")
        file.write("carnation,pink,annual\n")
        file.write("daffodil,yellow,perennial\n")
        file.write("iris,blue,perennial\n")
        file.write("poinsettia,red,perennial\n")
        file.write("sunflower,yellow,annual\n")

def contents_of_file(filename):
    return_string = ""

    create_file(filename)

    with open(filename, "r") as f:
        
        rows = csv.reader(f)
        
        next(rows)
        
        for row in rows:
            name, color, type = row
            
            return_string += "a {} {} is {}\n".format( color,name, type)

    return return_string

print(contents_of_file("flowers.csv"))
