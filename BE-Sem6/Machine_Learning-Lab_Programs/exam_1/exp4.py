import pandas as pd
data = pd.read_csv(r"./data.csv")
print(data)

def find_s_algorithm(data):
    """Implementation of find-S Algorithm to find the most specific hypothesis"""
    
    #Extract feature columns and target column
    attributes = data.iloc[:, :-1].values #all colmuns except last
    target = data.iloc[:, -1].values #last column (class labels)
    
    #Step 1: Initialize S to the most specific hypothesis +ve
    for i in range(len(target)):
        if target[i] == "Yes": #consider only positive examples
            hypothesis = attributes[i].copy()
            break
        
    #Step 2: Update hypothesis based on other positive examples
    for i in range(len(target)):
        if target[i] == "Yes": #consider only positive examples
            for j in range(len(hypothesis)):
                if attributes[i][j] != hypothesis[j]:
                    hypothesis[j] = "?"
    
    return hypothesis


hypothesis = find_s_algorithm(data)
print(hypothesis)