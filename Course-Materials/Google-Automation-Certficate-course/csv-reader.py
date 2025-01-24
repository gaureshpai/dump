import csv

users = [{"name": "gauresh","username":"hseruag","department":"it"},{"name":"f","username":"w","department":"it"}]
keys = ["name","username","department"]

with open('by_department.csv','w') as by_department:
    writer = csv.DictWriter(by_department,fieldnames=keys)
    writer.writeheader()