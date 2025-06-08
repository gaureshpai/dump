import pandas as pd

data = pd.read_csv(r"./4.csv")
print(data)

def find_s_algorithm(data):
    attributes = data.iloc[:, :-1].values 
    target = data.iloc[:, -1].values 
    
    for i in range(len(target)):
        if target[i] == "Yes": 
            hypothesis = attributes[i].copy()
            break    
    
    for i in range(len(target)):
        if target[i] == "Yes": 
            for j in range(len(hypothesis)):
                if attributes[i][j] != hypothesis[j]:
                    hypothesis[j] = "?"
    return hypothesis

hypothesis = find_s_algorithm(data)
print(hypothesis)