import seaborn as sns 
import matplotlib.pyplot as plt 
from sklearn.datasets import fetch_california_housing 

housing_data = fetch_california_housing(as_frame=True) 
df = housing_data.frame 

correlation_matrix = df.corr() 
print("\nCorrelation Matrix:") 
print(correlation_matrix) 

plt.figure(figsize=(10, 8)) 
sns.heatmap(correlation_matrix, annot=True, cmap='coolwarm', fmt='.2f', linewidths=0.5) 
plt.title("Correlation Matrix Heatmap") 
plt.show() 

sns.pairplot(df, diag_kind='kde') 
plt.show()