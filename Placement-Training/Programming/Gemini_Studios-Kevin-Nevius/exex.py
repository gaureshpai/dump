from google.colab import userdata
key = userdata.get('GOOGLE_API_KEY_1')

%pip install --upgrade google-genai

import sys
if "google.colab" in sys.modules:
    from google.colab import auth
    auth.authenticate_user()

from google.colab import userdata
from google import genai
GOOGLE_API_KEY = userdata.get('GOOGLE_API_KEY_1')
client = genai.Client(api_key=GOOGLE_API_KEY)

MODEL_ID = "gemini-2.0-flash-001"

prompt = "what can i do in AI workshop"
response = client.models.generate_content(model=MODEL_ID, contents=prompt)
print(response.text)

from IPython.display import Markdown, display
from google import genai
from google.genai.types import GenerateContentConfig

prompt1 = "what color is the sky?"
respnse = client.models.generate_content(model=MODEL_ID, contents=prompt1)
display(Markdown(respnse.text))

print("Checkout: https://cloud.google.com/vertex-ai/generative-ai/docs/learn/prompts/system-instructions")

chat = client.chats.create(
    model = MODEL_ID,
    config = GenerateContentConfig(
        system_instruction=
        """Your name is AJIET bot, first you always have to greet the user with his/her name",
        Try to answer the question followed by 7 that,
        Give answers only in bullet points"""
    ),
)

res = chat.send_message("what color is the sky?")
display(Markdown(res.text))

print("Checkout: https://ai.google.dev/gemini-api/docs/thinking")

from IPython.display import HTML, Image, Markdown, display
from google import genai
from google.genai.types import (
    FunctionDeclaration,
    GenerateContentConfig,
    GoogleSearch,
    HarmBlockThreshold,
    HarmCategory,
    Part,
    SafetySetting,
    ThinkingConfig,
    Tool,
    ToolCodeExecution
)

response1 = client.models.generate_content(model=MODEL_ID, contents="who is the father of Liberalization and why", config = GenerateContentConfig(thinking_config = ThinkingConfig(thinking_budget=0)))
display(Markdown(response1.text))

res2 = chat.send_message("remember what i say, first i am telling good afternoon")
display(Markdown(res2.text))

res2 = chat.send_message("in the afternoon i did cover the memory part  of gemini and my name is gauresh")
display(Markdown(res2.text))

res2 = chat.send_message("what did i say first")
display(Markdown(res2.text))

chat.get_history(0)

google_search_tool = Tool(google_search = GoogleSearch())
response = client.models.generate_content(model=MODEL_ID, contents="What is the sbi stock value today", config = GenerateContentConfig(tools = [google_search_tool]))
display(Markdown(response.text))

print(response.candidates[0].grounding_metadata)
HTML(response.candidates[0].grounding_metadata.search_entry_point.rendered_content)

%pip install -q google-generativeai gradio pillow

ffrom logging import debug
from urllib.parse import uses_fragment
# Develop the chat bot for AJIET. whhich is Human kind interface to human interactions
%pip install -q google-generativeai gradio pillow


import google.genai as genai
GOOGLE_API_KEY= userdata.get('GOOGLE_API_KEY_1')
MODEL_ID = "gemini-2.0-flash"
client = genai.Client(api_key=GOOGLE_API_KEY)
from IPython.display import Markdown, display
import gradio as gr
from google.colab import userdata
from google import genai
from google.genai.types import (
    Tool,
    GoogleSearch,
    GenerateContentConfig,
    Content,
    Part
)
#import the model
MODEL_ID ='gemini-2.0-flash'
#Set the Tool
google_search_tool = Tool(google_search=GoogleSearch())
#initialize Chat history
chat_history = []
#Define the chat function
def grounded_chat(user_input, history):
  global chat_history
  try:
    # Add user Message as a part object
     user_message = Content(role="user", parts=[Part(text= user_input)])
     chat_history.append(user_message)
    #generate response using tool
     response= client.models.generate_content(
        model=MODEL_ID,
        contents= chat_history,
        config=GenerateContentConfig(tools=[google_search_tool])
     )
     #add model response
     model_reply= Content(role="model", parts=[Part(text=response.text)])
     chat_history.append(model_reply)
     return response.text
  except Exception as e:
    return str(e)
    #gradio Chat interface
    chat_interface = gr.ChatInterface(fn=grounded_chat,
                                      title="AJIET Help Desk with google Search Grounding",
                                      theme="defualt").launch(share=True,debug=True)