import numpy as np
import matplotlib.pyplot as plt
from collections import Counter

def generate_data():
    np.random.seed(42)
    x = np.random.rand(100)
    labels = np.array(["class1" if xi <= 0.5 else "class2" for xi in x])  # Fixed to match the size of x
    return x, labels

def knn_classify(x_train, y_train, test_x, k):
    predictions = []
    for test_point in test_x:
        distances = np.abs(test_point - x_train)
        nearest_indices = np.argsort(distances)[:k]
        nearest_labels = y_train[nearest_indices]
        most_common_label = Counter(nearest_labels).most_common(1)[0][0]
        predictions.append(most_common_label)
    return np.array(predictions)

def plot_results(x, train_x, train_y, predictions, k):
    plt.figure(figsize=(8, 5))
    plt.scatter(train_x, [1] * len(train_x), 
                c=["blue" if label == "class1" else "red" for label in train_y], 
                label="Training Data", marker="o")
    plt.scatter(x[:len(predictions)], [1] * len(predictions), 
                c=["blue" if label == "class1" else "red" for label in predictions], 
                label="Test Predictions", marker="x")
    plt.axvline(0.5, color='gray', linestyle='--', label="Decision Boundary")  # Fixed axrline to axvline
    
    plt.legend()
    plt.title(f"KNN Classification Results (k={k})")
    plt.xlabel("X Values")
    plt.show()

# Main Execution
x, labels = generate_data()
train_x, train_y = x[:50], labels[:50]  # First 50 points for training
test_x = x[50:]  # Remaining points for testing
k = 3  # Number of neighbors

predictions = knn_classify(train_x, train_y, test_x, k)
plot_results(x, train_x, train_y, predictions, k)