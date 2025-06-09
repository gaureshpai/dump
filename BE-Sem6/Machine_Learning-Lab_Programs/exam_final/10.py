import pandas as pd 
import matplotlib.pyplot as plt 
import seaborn as sns 
from sklearn.cluster import KMeans 
from sklearn.decomposition import PCA 
from sklearn.preprocessing import StandardScaler 
from sklearn.datasets import load_breast_cancer

data = load_breast_cancer() 
df = pd.DataFrame(data.data, columns=data.feature_names) 
actual_labels = data.target 

scaler = StandardScaler() 
df_scaled = scaler.fit_transform(df) 

kmeans = KMeans(n_clusters=2, random_state=42, n_init=10) 
kmeans.fit(df_scaled) 
cluster_labels = kmeans.labels_ 

pca = PCA(n_components=2) 
df_pca = pca.fit_transform(df_scaled) 

df_visual = pd.DataFrame(df_pca, columns=['PC1', 'PC2']) 
df_visual['Cluster'] = cluster_labels 
df_visual['Actual'] = actual_labels 

plt.figure(figsize=(12, 5)) 

plt.subplot(1, 2, 1) 
sns.scatterplot(x='PC1', y='PC2', hue='Cluster', data=df_visual, palette='Set1') 
plt.title('K-Means Clustering on Wisconsin Breast Cancer Dataset') 

plt.subplot(1, 2, 2) 
sns.scatterplot(x='PC1', y='PC2', hue='Actual', data=df_visual, palette='Set2') 
plt.title('Actual Diagnosis') 
plt.tight_layout() 
plt.show() 