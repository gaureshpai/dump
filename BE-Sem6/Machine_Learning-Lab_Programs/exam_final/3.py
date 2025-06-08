import numpy as np 
import matplotlib.pyplot as plt  
from sklearn import datasets 
from sklearn.preprocessing import StandardScaler 
from sklearn.decomposition import PCA 
 
iris=datasets.load_iris() 
X=iris.data 
y=iris.target 

scaler=StandardScaler() 
X_scaled=scaler.fit_transform(X) 

print("Covariance matrix is:") 
cov_matrix=np.cov(X_scaled.T) 
print(cov_matrix) 

eigenvalues,eigenvectors=np.linalg.eig(cov_matrix) 
print("Eigenvalues:",eigenvalues) 
print("Eigenvectors:\n",eigenvectors)
 
fig=plt.figure(figsize=(8,6)) 
ax=fig.add_subplot(111,projection='3d') 
colors=['red','green','blue'] 
labels=iris.target_names 
for i in range(len(colors)): 
    ax.scatter(X_scaled[y==i,0],X_scaled[y==i,1],X_scaled[y==i,2],color=colors[i],label=labels[i]) 
    ax.set_xlabel('Sepal Length') 
    ax.set_ylabel('Sepal Width') 
    ax.set_zlabel('Petal Length') 
ax.set_title('3D Visualization of Iris Data Before PCA') 
plt.legend() 
plt.show()
 
U,S,Vt=np.linalg.svd(X_scaled,full_matrices=False) 
print("Singular Values:",S) 

pca=PCA(n_components=2) 
X_pca=pca.fit_transform(X_scaled) 
explained_variance=pca.explained_variance_ratio_ 
print(f"Explained Variance by PC1:{explained_variance[0]:.2f}") 
print(f"Explained Variance by PC2:{explained_variance[1]:.2f}")
 
plt.figure(figsize=(8,6)) 
for i in range(len(colors)): 
    plt.scatter(X_pca[y==i,0],X_pca[y==i,1],color=colors[i],label=labels[i]) 
    plt.xlabel('Principal Components 1') 
    plt.ylabel('Principal Components 2') 
plt.title('PCA on Iris Dataset (Dimensinality Reduction)') 
plt.legend() 
plt.grid() 
plt.show() 

fig=plt.figure(figsize=(8,6)) 
ax=fig.add_subplot(111,projection='3d') 
for i in range(len(colors)): 
    ax.scatter(X_scaled [y == i, 0], X_scaled [y == i, 1], X_scaled [y == i, 2], color=colors[i], label=labels[i]) 
for i in range(3): 
    ax.quiver(0, 0, 0, eigenvectors [i, 0], eigenvectors [i, 1], eigenvectors [i, 2], color='black', length=1) 
    ax.set_xlabel('Sepal Length') 
    ax.set_ylabel('Sepal Width') 
    ax.set_zlabel('Petal Length') 
    ax.set_title('3D Data with Eigenvectors') 
plt.legend() 
plt.show()