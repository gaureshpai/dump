import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
from sklearn import datasets
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA

# Load California housing dataset
california = datasets.fetch_california_housing()
X = california.data  # Extracting feature matrix
y = california.target

scaler = StandardScaler()  # Create an instance of the StandardScaler class
X_scaled = scaler.fit_transform(X)

cov_matrix = np.cov(X_scaled.T)  # Compute covariance matrix
print(cov_matrix)
eigenvalues, eigenvectors = np.linalg.eig(cov_matrix)
print("Eigenvalues:", eigenvalues)
print("Eigenvectors:\n", eigenvectors)

fig = plt.figure(figsize=(8, 6))
ax = fig.add_subplot(111, projection='3d')
colors = ['red', 'green', 'blue', 'yellow', 'purple', 'orange', 'cyan', 'magenta']
labels = california.feature_names
for i in range(len(colors)):
    if i < X_scaled.shape[1]:
        ax.scatter(X_scaled[:, i], X_scaled[:, (i+1) % X_scaled.shape[1]], X_scaled[:, (i+2) % X_scaled.shape[1]], color=colors[i], label=labels[i])
ax.set_xlabel('Feature 1')
ax.set_ylabel('Feature 2')
ax.set_zlabel('Feature 3')
ax.set_title('3D Visualization of California Housing Data Before PCA')
plt.legend()
plt.show()

U, S, Vt = np.linalg.svd(X_scaled, full_matrices=False)
print("Singular Values:", S)

pca = PCA(n_components=2)  # We choose 2 components because we want to visualize
X_pca = pca.fit_transform(X_scaled)

explained_variance = pca.explained_variance_ratio_  # Attribute contains the proportion of the variance explained by each principal component.
print(f"Explained Variance by PC1: {explained_variance[0]:.2f}")
print(f"Explained Variance by PC2: {explained_variance[1]:.2f}")

plt.figure(figsize=(8, 6))
for i in range(len(colors)):
    if i < X_pca.shape[1]:
        plt.scatter(X_pca[:, 0], X_pca[:, 1], color=colors[i], label=labels[i])

plt.xlabel('Principal Component 1')
plt.ylabel('Principal Component 2')
plt.title('PCA on California Housing Dataset (Dimensionality Reduction)')
plt.legend()
plt.grid()
plt.show()

fig = plt.figure(figsize=(8, 6))
ax = fig.add_subplot(111, projection='3d')
for i in range(len(colors)):
    if i < X_scaled.shape[1]:
        ax.scatter(X_scaled[:, i], X_scaled[:, (i+1) % X_scaled.shape[1]], X_scaled[:, (i+2) % X_scaled.shape[1]], color=colors[i], label=labels[i])
for i in range(3):  # Plot first three eigenvectors
    ax.quiver(0, 0, 0, eigenvectors[i, 0], eigenvectors[i, 1], eigenvectors[i, 2], color='black', length=1)
ax.set_xlabel('Feature 1')
ax.set_ylabel('Feature 2')
ax.set_zlabel('Feature 3')
ax.set_title('3D Data with Eigenvectors')
plt.legend()
plt.show()