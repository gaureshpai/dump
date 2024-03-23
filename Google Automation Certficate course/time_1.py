import os
import datetime

def file_date(filename):
    with open(filename, 'w') as file:
        pass
    timestamp = os.path.getmtime(filename)
    date = datetime.datetime.fromtimestamp(timestamp)
    return date.strftime("%Y-%m-%d")
print(file_date("newfile.txt"))