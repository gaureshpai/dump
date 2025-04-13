import numpy as np
from collections import Counter

# Sample training dataset: [Feature1, Feature2, Feature3], Target (Result)
def generate_student_data():
    # Example dataset: [Hours Studied, Attendance %, Assignments Completed], Result
    data = np.array([
        [5.0, 80, 6, "Pass"],
        [6.5, 90, 7, "Pass"],
        [4.0, 60, 4, "Fail"],
        [7.0, 85, 8, "Pass"],
        [3.5, 50, 3, "Fail"],
        [6.0, 75, 6, "Pass"],
        [2.5, 40, 2, "Fail"],
        [8.0, 95, 9, "Pass"],
        [4.5, 65, 5, "Fail"],
        [7.5, 88, 8, "Pass"]
    ])
    features = data[:, :-1].astype(float)  # Extract features
    labels = data[:, -1]  # Extract target labels
    return features, labels

# KNN Classification Function
def knn_classify(features_train, labels_train, test_instance, k):
    distances = np.linalg.norm(features_train - test_instance, axis=1)  # Compute Euclidean distance
    nearest_indices = np.argsort(distances)[:k]  # Get indices of k nearest neighbors
    nearest_labels = labels_train[nearest_indices]  # Get labels of nearest neighbors
    most_common_label = Counter(nearest_labels).most_common(1)[0][0]  # Majority vote
    return most_common_label

# Main Execution
features, labels = generate_student_data()
test_instance = np.array([6.1, 40, 5])  # Test instance: [Hours Studied, Attendance %, Assignments Completed]
k = 3  # Number of neighbors

# Perform KNN Classification
result = knn_classify(features, labels, test_instance, k)
print(f"The predicted result for the test instance {test_instance} is: {result}")