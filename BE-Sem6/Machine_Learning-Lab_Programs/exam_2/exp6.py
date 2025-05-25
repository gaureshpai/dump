import numpy as np 
import matplotlib.pyplot as plt 
 
def local_regression(x0, X, Y, tau): 
    x0 = [1, x0] 
    X = [[1, i] for i in X] 
    X = np.asarray(X) 
    W=np.diag(np.exp(-np.sum((X-x0)**2,axis=1)/(2*tau*tau))) 
    beta = np.linalg.pinv(X.T@ W @ X)@X.T@ W @Y 
    y_pred=np.dot(beta,x0) 
    return y_pred 
 
def draw(tau): 
    prediction = [local_regression(x0, X, Y, tau) for x0 in domain] 
    plt.plot(X, Y, 'o', color = 'black') 
    plt.plot(domain, prediction, color = 'red') 
    plt.show() 
    
X = np.linspace(-3, 3, num = 100) 
domain = X 

Y = np.log(np.abs(X ** 2 - 1)+ .5) 
print("X values:",X) 
print("Y values:",Y) 

print("\n Regression Line Fit for different values of Tau- 10,0.1,0.01,0.001") 
draw(10) 
draw(0.1) 
draw(0.01) 
draw(0.001) 