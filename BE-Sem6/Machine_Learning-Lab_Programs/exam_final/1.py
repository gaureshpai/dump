import numpy as np 
import seaborn as sns 
import matplotlib.pyplot as plt 
from sklearn.datasets import fetch_california_housing 

housing_data=fetch_california_housing(as_frame=True) 
df=housing_data.frame
numerical_features=df.select_dtypes(include=[np.number]).columns 

df.hist(figsize=(12,8),bins=30,edgecolor='black') 
plt.suptitle("Histograms",fontsize=16) 
plt.ylabel('Frequency') 
plt.show() 
    
for col in numerical_features: 
    plt.figure(figsize=(6,6)) 
    sns.boxplot(df[col],color='blue') 
    plt.title(col) 
    plt.ylabel(col) 
    plt.show() 
 
print("Outliers detection:") 
for feature in numerical_features: 
    Q1=df[feature].quantile(0.25) 
    Q3=df[feature].quantile(0.75) 
    IQR=Q3-Q1 
    lower_bound=Q1-1.5*IQR 
    upper_bound=Q3+1.5*IQR  
    outliers=df[(df[feature]<lower_bound)|(df[feature]>upper_bound)] 
    print(f" {feature}:{len(outliers)} outliers")
    