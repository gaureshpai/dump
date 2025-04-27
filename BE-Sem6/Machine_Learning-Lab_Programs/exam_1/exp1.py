import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

from sklearn.datasets import fetch_california_housing
data = fetch_california_housing()
df = pd.DataFrame(data.data, columns=data.feature_names)
# plt.figure(figsize=(12, 10))
df.hist(bins=30, figsize=(12, 10), layout=(4,2), edgecolor='black')
plt.suptitle("Histograms of numerical features", fontsize=16)
plt.show()

for i, column in enumerate(df.columns, 1):
    plt.subplot(4, 2, i)
    plt.boxplot(df[column])
    plt.title(f"Boxplot of {column}")

plt.tight_layout()
plt.show()

outliers = {}
for column in df.columns:
    q1 = df[column].quantile(0.25)
    q3 = df[column].quantile(0.75)
    iqr = q3 - q1
    lower_bound = q1 - 1.5 * iqr
    upper_bound = q3 + 1.5 * iqr
    outliers[column] = df[(df[column] < lower_bound) | (df[column] > upper_bound)][column].count()
    print(f"Outliers in {column}: {outliers[column]}")
    for feature, count in outliers.items():
        print(f"{feature}: {count} outliers")