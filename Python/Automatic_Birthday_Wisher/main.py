import pandas as pd
import datetime
import os
import base64
import json
import smtplib
from googleapiclient.discovery import build
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
import warnings

# Ignore deprecation warning
warnings.filterwarnings("ignore", category=DeprecationWarning, message=".*pyarrow.*")

# Authentication and email sending setup
SCOPES = ['https://www.googleapis.com/auth/gmail.send']

def get_credentials():
    creds = None
    if os.path.exists('token.json'):
        creds = json.load(open('token.json'))
    
    if not creds or not creds.get('token'):
        if creds and creds.get('refresh_token'):
            creds = Credentials.from_authorized_user_info(info=creds, scopes=SCOPES)
        
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(
                'credentials.json', SCOPES,redirect_uri='http://localhost:8080/')
            creds = flow.run_local_server(port=0)
        
        with open('token.json', 'w') as token:
            json.dump(creds, token)
            
    return creds

def send_email(service, to, subject, body):
    message = {
        'raw': base64.urlsafe_b64encode(f"Subject: {subject}\n\n{body}".encode('utf-8')).decode('utf-8')
    }
    send_message = service.users().messages().send(userId='me', body=message).execute()
    print(f"Email sent to {to} with subject: {subject} and message: {body}")

if __name__ == "__main__":
    # Load Excel data
    df = pd.read_excel("data.xlsx")
    today = datetime.datetime.now().strftime("%d-%m")
    yearNow = datetime.datetime.now().strftime("%Y")

    writeIndex = []

    # Get Gmail API credentials
    creds = get_credentials()
    service = build('gmail', 'v1', credentials=creds)

    for index, item in df.iterrows():
        if pd.notnull(item['Birthday']):
            bday = item['Birthday'].strftime("%d-%m")
            if (bday == today) and yearNow not in str(item['Year']):
                send_email(service, item["Email"], "Birthday Wishes", item["Dialogue"])
                writeIndex.append(index)
    
    if writeIndex:
        for i in writeIndex:
            yr = df.loc[i, 'Year']
            df.loc[i, 'Year'] = str(int(yr)) + "," + str(yearNow)
            print(f"Updated Year: {df.loc[i,'Year']}")
        
        df.to_excel("data.xlsx", index=False)