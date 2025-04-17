import cv2
import numpy as np
from skimage.feature import local_binary_pattern
import matplotlib.pyplot as plt

def compute_lbp(image, radius=1, n_points=8):
    """
    Compute the Local Binary Pattern (LBP) of an image.

    Parameters:
        image (numpy.ndarray): Grayscale input image.
        radius (int): Radius of the LBP pattern.
        n_points (int): Number of points in the LBP pattern.

    Returns:
        numpy.ndarray: LBP image.
    """
    lbp = local_binary_pattern(image, n_points, radius, method="uniform")
    return lbp.astype(np.uint8)

def region_growing_with_texture(image, seed_point, lbp_image, threshold=2):
    """
    Perform region growing segmentation using texture features.

    Parameters:
        image (numpy.ndarray): Grayscale input image.
        seed_point (tuple): Starting point for region growing (x, y).
        lbp_image (numpy.ndarray): LBP image for texture features.
        threshold (int): Texture similarity threshold.

    Returns:
        numpy.ndarray: Binary mask of the segmented region.
    """
    height, width = image.shape
    segmented = np.zeros_like(image, dtype=np.uint8)
    visited = np.zeros_like(image, dtype=bool)
    seed_value = lbp_image[seed_point[1], seed_point[0]]
    stack = [seed_point]

    while stack:
        x, y = stack.pop()
        if visited[y, x]:
            continue
        visited[y, x] = True
        texture_value = lbp_image[y, x]

        # Check texture similarity
        if abs(int(texture_value) - int(seed_value)) <= threshold:
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

    # Compute the LBP image
    radius = 1
    n_points = 8 * radius
    lbp_image = compute_lbp(image, radius, n_points)

    # Define the seed point (x, y)
    seed_point = (100, 100)  # Replace with your desired seed point

    # Perform region growing with texture
    threshold = 2  # Adjust the threshold as needed
    segmented_image = region_growing_with_texture(image, seed_point, lbp_image, threshold)

    # Display the images side by side
    plt.figure(figsize=(15, 5))

    # Original Image
    plt.subplot(1, 3, 1)
    plt.imshow(image, cmap="gray")
    plt.title("Original Image")
    plt.axis("off")

    # LBP Image
    plt.subplot(1, 3, 2)
    plt.imshow(lbp_image, cmap="gray")
    plt.title("LBP Image")
    plt.axis("off")

    # Segmented Image
    plt.subplot(1, 3, 3)
    plt.imshow(segmented_image, cmap="gray")
    plt.title("Segmented Region (Texture)")
    plt.axis("off")

    plt.tight_layout()
    plt.show()