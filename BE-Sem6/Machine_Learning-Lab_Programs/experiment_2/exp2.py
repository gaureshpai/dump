import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

from sklearn.datasets import fetch_california_housing
data = fetch_california_housing()

df = pd.DataFrame(data.data, columns=data.feature_names)
df['Target'] = data.target

variable_meaning = { 
    "MedInc":"Median Income in block group",
    "HouseAge":"Median House Age in block group",
    "AveRooms":"Average number of rooms per household",
    "AveBedrms":"Average number of bedrooms per household",
    "Population":"Population of block group",
    "Ave0ccup":"Average number of household members",
    "Latitude":"Latitude of block group",
    "Longitude":"Longitude of block group",
    "Target":"Median house value (in $100,000$)"
}
variable_df = pd.DataFrame(list(variable_meaning.items()),columns=["Feature","Description"])
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

df.hist(figsize=(12,8),bins=30,edgecolor='black')
plt.suptitle("Feature distributions", fontsize=16)
plt.show()

sns.boxplot(data=df)
plt.xticks(rotation=45)
plt.title("Boxplots of Features to Identify Outliers")
plt.show()

corr_matrix = df.corr()
sns.heatmap(corr_matrix, annot=True, cmap='coolwarm', fmt='.2f')
plt.title("Feature Correlation Heatmap")
plt.show()

sns.pairplot(df[['MedInc','HouseAge','AveRooms','Target']],diag_kind='kde')
plt.show()

print("\nKey Insights:")
print("1. The dataset has", df.shape[0], "rows and", df.shape[1], "columns.")
print("2. No missing values were found in the dataset.")
print("3. Histograms show skewed distributions in some features like 'MedInc'.")
print("4. Boxplots indicate potential outliers in 'AveRooms' and 'AveOccup'.")
print("5. Correlation heatmap shows 'MedInc' has the highest correlation with house prices.")