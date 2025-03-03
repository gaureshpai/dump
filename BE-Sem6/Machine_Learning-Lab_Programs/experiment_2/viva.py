import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

from sklearn.datasets import load_iris
data = load_iris()

df = pd.DataFrame(data.data, columns=data.feature_names)
df['Target'] = data.target

target_names = {i: name for i, name in enumerate(data.target_names)}
df['Target'] = df['Target'].map(target_names)

variable_meaning = {
    "sepal length (cm)": "Sepal Length in cm",
    "sepal width (cm)": "Sepal Width in cm",
    "petal length (cm)": "Petal Length in cm",
    "petal width (cm)": "Petal Width in cm",
    "Target": "Species of Iris (Setosa, Versicolor, Virginica)"
}
variable_df = pd.DataFrame(list(variable_meaning.items()), columns=["Feature", "Description"])
print("\nVariable Meaning Table")
print(variable_df)

print("\nBasic Information about Dataset:")
print(df.info())

print("\nFirst Five Rows of Dataset:")
print(df.head())

print("\nSummary Statistics:")
print(df.describe())

print("\nMissing values in each column:")
print(df.isnull().sum())

df.hist(figsize=(12, 8), bins=15, edgecolor='black')
plt.suptitle("Feature distributions", fontsize=16)
plt.show()

sns.boxplot(data=df.drop(columns=['Target']))
plt.xticks(rotation=45)
plt.title("Boxplots of Features to Identify Outliers")
plt.show()

corr_matrix = df.drop(columns=['Target']).corr()
sns.heatmap(corr_matrix, annot=True, cmap='coolwarm', fmt='.2f')
plt.title("Feature Correlation Heatmap")
plt.show()

sns.pairplot(df, hue='Target', diag_kind='kde')
plt.show()

print("\nKey Insights:")
print("1. The dataset has", df.shape[0], "rows and", df.shape[1], "columns.")
print("2. No missing values were found in the dataset.")
print("3. Histograms show that features like 'sepal width' have a more spread-out distribution.")
print("4. Boxplots indicate potential outliers in 'sepal width'.")
print("5. Correlation heatmap shows strong relationships between petal length, petal width, and species classification.")