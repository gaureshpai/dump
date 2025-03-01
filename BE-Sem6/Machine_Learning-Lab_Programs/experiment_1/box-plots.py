import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.datasets import fetch_california_housing

data = fetch_california_housing()
df = pd.DataFrame(data.data, columns=data.feature_names)

plt.figure(figsize=(12, 8))

for i, column in enumerate(df.columns, 1):
    plt.subplot(4, 2, i)
    plt.boxplot(df[column])
    plt.title(f"Boxplot of {column}")

plt.tight_layout()
plt.show()
