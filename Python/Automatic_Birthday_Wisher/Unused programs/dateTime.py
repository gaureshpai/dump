import pandas as pd;
import datetime;

def sendEmail():
    pass

if __name__ == "__main__":
    df = pd.read_excel("data.xlsx");
    # print(df);
    today = datetime.datetime.now(); # displays the date(yyyy-mm-dd) with time
    today = datetime.datetime.now().strftime("%d-%m"); # displays the date and month
    print(today);