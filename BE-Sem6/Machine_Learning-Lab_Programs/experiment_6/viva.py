import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from scipy.spatial.distance import cdist

# Load Iris dataset
iris = load_iris()
df = pd.DataFrame(data=iris.data, columns=iris.feature_names)
df['target'] = iris.target

# Select one feature as X and another as Y for regression
# Example: Sepal length (X) and Petal length (Y)
X = df[['sepal length (cm)']].values
Y = df['petal length (cm)'].values

# Split into training and testing sets
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.2, random_state=42)

# Gaussian Kernel for Locally Weighted Regression
def gaussian_kernel(x, x_train, tau):
    return np.exp(-cdist(x, x_train, 'sqeuclidean') / (2 * tau ** 2))

# Locally Weighted Regression
def locally_weighted_regression(x_train, y_train, tau=0.5):
    x_train = np.hstack([np.ones((x_train.shape[0], 1)), x_train])  # Add bias term
    x_range = np.linspace(x_train[:, 1].min(), x_train[:, 1].max(), 100).reshape(-1, 1)
    x_range = np.hstack([np.ones((x_range.shape[0], 1)), x_range])  # Add bias term
    y_pred = []
    for x in x_range:
        weights = gaussian_kernel(x[1:].reshape(1, -1), x_train[:, 1:], tau).flatten()
        W = np.diag(weights)
        theta = np.linalg.pinv(x_train.T @ W @ x_train) @ (x_train.T @ W @ y_train)
        y_pred.append(x @ theta)
    plt.scatter(x_train[:, 1], y_train, label='Original Data')
    plt.plot(x_range[:, 1], y_pred, label='Locally Weighted Regression', color='red')
    plt.legend()
    plt.title('Locally Weighted Regression (Iris Dataset)')
    plt.xlabel('Sepal Length (cm)')
    plt.ylabel('Petal Length (cm)')
    plt.show()

# Apply Locally Weighted Regression
locally_weighted_regression(X_train, Y_train, tau=0.5)