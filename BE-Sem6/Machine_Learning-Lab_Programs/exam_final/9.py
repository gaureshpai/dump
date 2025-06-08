import numpy as np 
import matplotlib.pyplot as plt 
from sklearn.datasets import fetch_olivetti_faces 
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score, confusion_matrix
 
data=fetch_olivetti_faces() 
X = data.data
y = data.target
images = data.images

for i in range(10):
    plt.subplot(2, 5, i + 1)
    plt.imshow(images[i], cmap='gray')
plt.tight_layout()
plt.show()

x_train, x_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

model = GaussianNB()
model.fit(x_train, y_train)
y_pred = model.predict(x_test)

accuracy = accuracy_score(y_test, y_pred)
conf_matrix = confusion_matrix(y_test, y_pred)

print("Accuracy:", accuracy)
print("Confusion Matrix:\n", conf_matrix)

misclassified_indices = np.where(y_pred != y_test)[0]

if misclassified_indices.size > 0:
    plt.figure(figsize=(10, 5))
    for i, index in enumerate(misclassified_indices[:10]):
        plt.subplot(2, 5, i + 1)
        plt.imshow(images[index], cmap='gray')
        plt.title(f"Predicted: {y_pred[index]}\nActual: {y_test[index]}")
        plt.axis('off')
    plt.tight_layout()
    plt.show()