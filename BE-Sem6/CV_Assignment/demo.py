import numpy as np
import cv2
import matplotlib.pyplot as plt
from region_growing import RegionGrowing

def main():
    # Load test image
    # Using a sample image for demonstration
    image = np.zeros((300, 300, 3), dtype=np.uint8)
    
    # Create a test image with different regions
    cv2.rectangle(image, (50, 50), (100, 100), (255, 0, 0), -1)  # Blue square
    cv2.rectangle(image, (150, 150), (250, 250), (0, 255, 0), -1)  # Green square
    cv2.circle(image, (200, 75), 50, (0, 0, 255), -1)  # Red circle
    
    # Add some noise
    noise = np.random.normal(0, 15, image.shape).astype(np.int8)
    noisy_image = cv2.add(image, noise)
    noisy_image = np.clip(noisy_image, 0, 255).astype(np.uint8)
    
    # Display original image
    plt.figure(figsize=(10, 5))
    plt.subplot(1, 2, 1)
    plt.title('Original Image')
    plt.imshow(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))
    plt.axis('off')
    
    plt.subplot(1, 2, 2)
    plt.title('Noisy Image')
    plt.imshow(cv2.cvtColor(noisy_image, cv2.COLOR_BGR2RGB))
    plt.axis('off')
    
    plt.tight_layout()
    plt.show()
    
    # Apply region growing segmentation
    seed_points = [(75, 75), (200, 200), (200, 75)]  # Seeds for each region
    
    # Create region growing object
    rg = RegionGrowing(noisy_image, threshold=25, connectivity=8)
    
    # Segment the image
    segmented = rg.segment_image(seed_points)
    
    # Visualize results
    rg.visualize_results(show_steps=True)
    
    # Try with a real image
    try:
        # Load a real image
        real_image = cv2.imread('brain_mri.jpg')
        if real_image is not None:
            plt.figure(figsize=(8, 8))
            plt.title('Real Image')
            plt.imshow(cv2.cvtColor(real_image, cv2.COLOR_BGR2RGB))
            plt.axis('off')
            plt.show()
            
            # Apply region growing
            rg_real = RegionGrowing(real_image, threshold=15, connectivity=8)
            
            # Use center of image as seed point
            h, w = real_image.shape[:2]
            seed_real = [(h//2, w//2)]
            
            # Segment
            segmented_real = rg_real.segment_image(seed_real)
            
            # Visualize
            rg_real.visualize_results()
    except Exception as e:
        print(f"Could not process real image: {e}")
    
    # Create a more complex synthetic image for testing
    complex_image = np.zeros((400, 400, 3), dtype=np.uint8)
    
    # Background
    complex_image[:, :] = (200, 200, 200)
    
    # Create multiple objects
    cv2.circle(complex_image, (100, 100), 70, (100, 50, 50), -1)  # Object 1
    cv2.rectangle(complex_image, (250, 50), (350, 150), (50, 100, 50), -1)  # Object 2
    cv2.ellipse(complex_image, (150, 280), (100, 50), 0, 0, 360, (50, 50, 100), -1)  # Object 3
    cv2.circle(complex_image, (300, 300), 60, (150, 100, 100), -1)  # Object 4
    
    # Add gradient to make it more challenging
    y, x = np.mgrid[0:400, 0:400]
    gradient = (x + y) / 4
    gradient = np.stack([gradient, gradient, gradient], axis=2).astype(np.uint8)
    complex_image = cv2.addWeighted(complex_image, 0.8, gradient, 0.2, 0)
    
    # Add noise
    noise = np.random.normal(0, 10, complex_image.shape).astype(np.int8)
    complex_image = cv2.add(complex_image, noise)
    complex_image = np.clip(complex_image, 0, 255).astype(np.uint8)
    
    # Display complex image
    plt.figure(figsize=(8, 8))
    plt.title('Complex Synthetic Image')
    plt.imshow(cv2.cvtColor(complex_image, cv2.COLOR_BGR2RGB))
    plt.axis('off')
    plt.show()
    
    # Apply region growing to complex image
    complex_seeds = [(100, 100), (300, 100), (150, 280), (300, 300)]
    rg_complex = RegionGrowing(complex_image, threshold=20, connectivity=8)
    segmented_complex = rg_complex.segment_image(complex_seeds)
    rg_complex.visualize_results()

if __name__ == "__main__":
    main()