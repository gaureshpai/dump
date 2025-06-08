import numpy as np 
from sklearn.neighbors import KNeighborsClassifier 
from sklearn.metrics import accuracy_score 

np.random.seed(0) 
data=np.random.rand(100) 
labels=np.zeros(100) 

labels[:50]=np.where(data[:50]<=0.5,1,2) 
test_labels=np.where(data[:50]<=0.5,1,2)
 
train_data=data[:50].reshape(-1,1) 
train_labels=labels[:50] 

test_data=data[:50].reshape(-1,1) 

print("Train Data\n:",train_data.flatten()) 
print("Train Labels\n:",test_data.flatten()) 
print("Test Data\n:",test_data.flatten()) 

K_values=[1,2,3,4,5,20,30] 
for K in K_values: 
    Knn=KNeighborsClassifier(n_neighbors=K) 
    Knn.fit(train_data,train_labels) 
    predicted_labels=Knn.predict(test_data) 
    accuracy=accuracy_score(test_labels,predicted_labels)*100 
    print(f"\nK={K}") 
    print("predicted Labels\n:",predicted_labels) 
    print(f"Accuracy\n:{accuracy:.2f}%")