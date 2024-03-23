import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

# Load datasets
df_iris = sns.load_dataset('iris')
df_tips = sns.load_dataset('tips')
df_tips = pd.get_dummies(df_tips)

data = {
    'day': ['Sun', 'Sun', 'Sun', 'Sun', 'Sun', 'Sun', 'Sun', 'Sun', 'Sun', 'Sun'],
    'total_bill': [16.99, 10.34, 21.01, 23.68, 24.59, 25.29, 8.77, 26.88, 15.04, 14.78],
    'sex': ['Female', 'Male', 'Male', 'Male', 'Female', 'Male', 'Male', 'Male', 'Male', 'Male']
}

df_tips = pd.DataFrame(data)

# Distribution Plot
sns.histplot(df_iris['sepal_length'])
plt.show()

# Joint Plot
sns.jointplot(x='sepal_length', y='sepal_width', data=df_iris, kind='reg')
plt.show()

# KDE Plot
sns.jointplot(x='sepal_length', y='sepal_width', data=df_iris, kind='kde')
plt.show()

# Box Plot
sns.boxplot(x='day', y='total_bill', data=df_tips, hue='sex')
plt.show()

# Violin Plot
sns.violinplot(x='day', y='total_bill', data=df_tips, hue='sex')
plt.show()

# Strip Plot
sns.stripplot(x='day', y='total_bill', data=df_tips, jitter=True, hue='sex')
plt.show()

# Swarm Plot
sns.swarmplot(x='day', y='total_bill', data=df_tips, hue='sex')
plt.show()
corr_matrix = df_tips.corr()

# Heatmap
plt.figure(figsize=(8, 6))
sns.heatmap(corr_matrix, annot=True, cmap='coolwarm')
plt.show()
