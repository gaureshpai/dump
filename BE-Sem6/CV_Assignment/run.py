import cv2
import numpy as np

# filepath: d:\webdev\dump\region_growing.py

def region_growing(image, seed_point, threshold=10):
    """
    Perform region growing segmentation on an image.

    Parameters:
        image (numpy.ndarray): Grayscale input image.
        seed_point (tuple): Starting point for region growing (x, y).
        threshold (int): Intensity difference threshold for similarity.

    Returns:
        numpy.ndarray: Binary mask of the segmented region.
    """
    # Initialize variables
    height, width = image.shape
    segmented = np.zeros_like(image, dtype=np.uint8)
    visited = np.zeros_like(image, dtype=bool)
    seed_value = image[seed_point[1], seed_point[0]]
    stack = [seed_point]

    # Region growing loop
    while stack:
        x, y = stack.pop()
        if visited[y, x]:
            continue
        visited[y, x] = True
        intensity = image[y, x]

        # Check similarity
        if abs(intensity - seed_value) <= threshold:
            segmented[y, x] = 255  # Mark as part of the region

            # Add neighbors to the stack
            for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                nx, ny = x + dx, y + dy
                if 0 <= nx < width and 0 <= ny < height and not visited[ny, nx]:
                    stack.append((nx, ny))

    return segmented

if __name__ == "__main__":
    # Load the image
    image_path = "image.jpg"  # Replace with your image path
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)

    if image is None:
        print("Error: Could not load the image.")
        exit()

    # Define the seed point (x, y)
    seed_point = (100, 100)  # Replace with your desired seed point

    # Perform region growing
    threshold = 15  # Adjust the threshold as needed
    segmented_image = region_growing(image, seed_point, threshold)

    # Display the results
    cv2.imshow("Original Image", image)
    cv2.imshow("Segmented Region", segmented_image)
    cv2.waitKey(0)
    cv2.destroyAllWindows()