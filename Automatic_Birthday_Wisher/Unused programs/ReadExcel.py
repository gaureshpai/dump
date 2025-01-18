#### ReadExcel.py
#### This program is used to read an excel file

import pandas as pd;

def sendEmail():
    pass

if __name__ == "__main__":
    df = pd.read_excel("data.xlsx");
    print(df);