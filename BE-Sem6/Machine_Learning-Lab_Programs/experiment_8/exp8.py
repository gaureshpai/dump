import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix
from sklearn.tree import export_graphviz
from IPython.display import Image
import pydotplus
import warnings

warnings.filterwarnings("ignore")

data = pd.read_csv(r"./Breast Cancer Dataset.csv")
pd.set_option('display.max_columns', None)  

data.head()
data.shape
data.info()
data.diagnosis.unique()
data.isnull().sum()
df = data.drop(['id'],axis=1)
df['diagnosis'] = df['diagnosis'].map({'M': 1, 'B': 0})
df.describe().T

corr = df.corr(method='pearson')
plt.figure(figsize=(18, 10))
sns.heatmap(corr, annot=True, fmt=".2f", cmap='coolwarm', linewidths=0.5)
plt.xticks(rotation=90, ha='right')
plt.yticks(rotation=0)
plt.show()

x = df.drop(['diagnosis'], axis=1)
y = df['diagnosis']
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)
model = DecisionTreeClassifier(criterion='entropy')
model.fit(x_train, y_train)
model

import math
def entropy(column):
    counts = column.value_counts()
    probabilities = counts / len(column)
    return -sum(probabilities * probabilities.apply(math.log2))

def conditional_entropy(data, x, target):
    feature_values = data[x].unique()
    weighted_entropy = 0
    for value in feature_values:
        subset = data[data[x] == value]
        weighted_entropy += (len(subset) / len(data)) * entropy(subset[target])
    return weighted_entropy

def information_gain(data,x,target):
    total_entropy = entropy(data[target])
    feature_conditional_entropy = conditional_entropy(data, x, target)
    return total_entropy - feature_conditional_entropy

for feature in x:
    ig = information_gain(df, feature, 'diagnosis')
    print(f"Information Gain for {feature}: {ig:.4f}")
    
dot_data = export_graphviz(model, out_file=None,
                       feature_names=x_train.columns,
                       precision=2,
                       proportion=False,
                       filled=True, rounded=True)

graph = pydotplus.graph_from_dot_data(dot_data)
Image(graph.create_png())

plt.figure(figsize=(20, 10))
plot_tree(model, filled=True, feature_names=x.columns, fontsize=10,class_names=['Malignant', 'Benign'],rounded=True)
y_pred = model.predict(x_test)
y_pred

accuracy = accuracy_score(y_test, y_pred)*100
classification_report_result = classification_report(y_test, y_pred)
print("Accuracy: {:.2f}%".format(accuracy))
print("Classification Report:\n", classification_report_result)