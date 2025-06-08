import pandas as pd 
import numpy as np 
import seaborn as sns 
import matplotlib.pyplot as plt 
from sklearn.linear_model import LinearRegression 
from sklearn.model_selection import train_test_split 
from sklearn.metrics import mean_squared_error, r2_score 

data = pd.read_csv("BostonHousing.csv") 
X = data.drop('medv', axis=1)
y = data['medv'] 

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42) 
model = LinearRegression() 
model.fit(X_train, y_train)

y_pred = model.predict(X_test) 
mse = mean_squared_error(y_test, y_pred)
rmse = np.sqrt(mse) 
r2 = r2_score(y_test, y_pred) 
print("Mean Squared Error:", mse) 
print("Root Mean Squared Error (rmSE):", rmse) 
print("R² Score:", r2)

rm_model = LinearRegression() 
rm_model.fit(data[['rm']], data['medv']) 
data['Predicted'] = rm_model.predict(data[['rm']]) 

sns.scatterplot(x='rm', y='medv', data=data, label='Actual data', color='blue', alpha=0.5) 
sns.lineplot(x='rm', y='Predicted', data=data, label='Regression Line (rm only)', color='red')

plt.title("Linear Regression - rm vs medv (Visualization)") 
plt.xlabel("Average Number of Rooms (rm)") 
plt.ylabel("Median Home Value (medv)") 
plt.legend() 
plt.show()