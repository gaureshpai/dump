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