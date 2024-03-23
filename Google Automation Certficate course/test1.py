import os
import csv

# Create a file with data in it
def create_file(filename):
    with open(filename, "w") as file:
        file.write("name,color,type\n")
        file.write("carnation,pink,annual\n")
        file.write("daffodil,yellow,perennial\n")
        file.write("iris,blue,perennial\n")
        file.write("poinsettia,red,perennial\n")
        file.write("sunflower,yellow,annual\n")

# Read the file contents and format the information about each row
def contents_of_file(filename):
    return_string = ""

    # Call the function to create the file 
    create_file(filename)
    keys = ["name", "color", "type"]

    # Open the file
    with open(filename, newline='') as file:
        # Create a csv.DictReader object
        csv_reader = csv.DictReader(file)

        # Process each row of the CSV file
        for row in csv_reader:
            return_string += "a {} {} is {}\n".format(row["color"], row["name"], row["type"])

    return return_string

# Call the function
print(contents_of_file("flowers.csv"))