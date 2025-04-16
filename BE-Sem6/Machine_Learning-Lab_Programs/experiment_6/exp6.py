import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures
from sklearn.pipeline import make_pipeline
from scipy.spatial.distance import cdist

df_linear = pd.read_csv("linear_dataset.csv")
df_lwr = pd.read_csv("lwr_dataset.csv")
df_poly = pd.read_csv("polynomial_dataset.csv")

def linear_regression(df):
    x,y = df[['X']],df['Y']
    model = LinearRegression()
    model.fit(x,y)
    y_pred = model.predict(x)
    plt.scatter(x,y,label='Original Data')
    plt.plot(x,y_pred,label='Linear Regression',color='red')
    plt.legend()
    plt.title('Linear Regression')
    plt.show()

linear_regression(df_linear)

def gaussian_kernel(x, x_train, tau):
    return np.exp(-cdist(x, x_train, 'sqeuclidean') ** 2 / (2 * tau ** 2))


def locally_weighted_regression(x_train, y_train, tau=0.5):
    x_train = np.hstack([np.ones((x_train.shape[0], 1)), x_train])
    x_range = np.linspace(x_train[:, 1].min(), x_train[:, 1].max(), 100)
    y_pred = []
    for x in x_range:
        x_vec = np.array([1,x])
        weights = gaussian_kernel(x,x_train[:,1:], tau).flatten()
        w = np.diag(weights)
        theta = np.linalg.pinv(x_train.T @ w @ x_train) @ x_train.T @ w @ (x_train.T @ w @ y_train)
        y_pred.append(x_vec @ theta)
    plt.scatter(x_train[:, 1], y_train, label='Original Data')
    plt.plot(x_range, y_pred, label='Locally Weighted Regression', color='red')
    plt.legend()
    plt.title('Locally Weighted Regression')
    plt.show()

locally_weighted_regression(df_lwr[['X']].values, df_lwr['Y'].values)

def polynomial_regression(df,degree=3):
    x,y = deff[['X']],df['Y']
    model = make_pipeline(PolynomialFeatures(degree),LinearRegression())
    model.fit(x,y)
    y_pred = model.predict(x)
    plt.scatter(x,y,label='Original Data')
    plt.plot(x,y_pred,label=f'Polynomial Regression(deg = {degree})',color='red')
    plt.legend()
    plt.title('Polynomial Regression')
    plt.show()
    
polynomial_regression(df_poly,degree=3)