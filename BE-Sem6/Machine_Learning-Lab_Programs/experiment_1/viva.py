import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# Load the Iris dataset from a CSV file
df = pd.read_csv('iris.csv')

# Histograms of numerical features
df.hist(bins=30, figsize=(12, 10), layout=(2, 2), edgecolor='black')
plt.suptitle("Histograms of numerical features", fontsize=16)
plt.show()

# Boxplots of numerical features
plt.figure(figsize=(12, 8))

# List of numerical columns (excluding 'variety')
numerical_columns = df.columns[:-1]

for i, column in enumerate(numerical_columns, 1):
    plt.subplot(4, 2, i)
    plt.boxplot(df[column])
    plt.title(f"Boxplot of {column}")
    plt.ylabel(column)  # Add y-axis label

plt.tight_layout()
plt.show()

# Outlier detection and count
outliers = {}
for column in numerical_columns:
    q1 = df[column].quantile(0.25)
    q3 = df[column].quantile(0.75)
    iqr = q3 - q1
    lower_bound = q1 - 1.5 * iqr
    upper_bound = q3 + 1.5 * iqr
    outliers[column] = df[(df[column] < lower_bound) | (df[column] > upper_bound)][column].count()

print("Outliers count as per feature:")
# Print outlier counts
for feature, count in outliers.items():
    print(f"{feature}: {count} outliers")