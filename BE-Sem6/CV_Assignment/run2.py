import cv2
import numpy as np
import matplotlib.pyplot as plt

def region_growing_with_color(image, seed_point, threshold=30):
    """
    Perform region growing segmentation using color features in HSV space.

    Parameters:
        image (numpy.ndarray): Color input image (BGR format).
        seed_point (tuple): Starting point for region growing (x, y).
        threshold (int): Color similarity threshold in HSV space.

    Returns:
        numpy.ndarray: Binary mask of the segmented region.
    """
    # Convert the image to HSV color space
    hsv_image = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
    height, width, _ = hsv_image.shape

    # Initialize variables
    segmented = np.zeros((height, width), dtype=np.uint8)
    visited = np.zeros((height, width), dtype=bool)
    seed_value = hsv_image[seed_point[1], seed_point[0]]
    stack = [seed_point]

    while stack:
        x, y = stack.pop()
        if visited[y, x]:
            continue
        visited[y, x] = True
        pixel_value = hsv_image[y, x]

        # Check color similarity (Euclidean distance in HSV space)
        distance = np.linalg.norm(pixel_value - seed_value)
        if distance <= threshold:
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
    image = cv2.imread(image_path)

    if image is None:
        print("Error: Could not load the image.")
        exit()

    # Define the seed point (x, y)
    seed_point = (100, 100)  # Replace with your desired seed point

    # Perform region growing with color
    threshold = 30  # Adjust the threshold as needed
    segmented_image = region_growing_with_color(image, seed_point, threshold)

    # Create a color mask for the segmented region
    color_mask = np.zeros_like(image)
    color_mask[segmented_image == 255] = [0, 255, 0]  # Green color for the segmented region

    # Overlay the mask on the original image
    overlay_image = cv2.addWeighted(image, 0.7, color_mask, 0.3, 0)

    # Display the images side by side
    plt.figure(figsize=(15, 5))

    # Original Image
    plt.subplot(1, 3, 1)
    plt.imshow(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))  # Convert BGR to RGB for proper display
    plt.title("Original Image")
    plt.axis("off")

    # Segmented Mask
    plt.subplot(1, 3, 2)
    plt.imshow(segmented_image, cmap="gray")
    plt.title("Segmented Mask")
    plt.axis("off")

    # Overlay Image
    plt.subplot(1, 3, 3)
    plt.imshow(cv2.cvtColor(overlay_image, cv2.COLOR_BGR2RGB))  # Convert BGR to RGB for proper display
    plt.title("Overlay with Segmented Region")
    plt.axis("off")

    plt.tight_layout()
    plt.show()