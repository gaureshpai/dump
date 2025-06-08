import pandas as pd 
import numpy as np 
import seaborn as sns 
import matplotlib.pyplot as plt 
from sklearn.linear_model import LinearRegression 
from sklearn.preprocessing import PolynomialFeatures 
from sklearn.model_selection import train_test_split 
from sklearn.metrics import mean_squared_error, r2_score 

data = pd.read_csv("BostonHousing.csv") 

X = data[['lstat']]
y = data['medv']

poly = PolynomialFeatures(degree=2)
X_poly = poly.fit_transform(X)

X_train, X_test, y_train, y_test = train_test_split(X_poly, y, test_size=0.2, random_state=42)

model = LinearRegression()
model.fit(X_train, y_train)

y_pred = model.predict(X_test)

mse = mean_squared_error(y_test, y_pred)
rmse = np.sqrt(mse)
r2 = r2_score(y_test, y_pred)
print("Mean Squared Error:", mse)
print("Root Mean Squared Error (RMSE):", rmse)
print("R² Score:", r2)

X_range = pd.DataFrame({'lstat': np.linspace(X.min()[0], X.max()[0], 300)})
X_range_poly = poly.transform(X_range)
y_range_pred = model.predict(X_range_poly)

sns.scatterplot(x='lstat', y='medv', data=data, label='Actual data', alpha=0.4, color='blue')
sns.lineplot(x='lstat', y=y_range_pred, data=X_range, label='Polynomial Fit', color='red')
plt.title("Polynomial Regression - Boston Housing (lstat vs medv)")
plt.xlabel("% Lower Status of Population (lstat)")
plt.ylabel("Median Value of Homes (medv)")
plt.legend()
plt.show()