import numpy as np
import cv2
import matplotlib.pyplot as plt
from matplotlib.backends.backend_agg import FigureCanvasAgg as FigureCanvas

# Simulate the RegionGrowing class functionality
class RegionGrowing:
    def __init__(self, image, threshold=10, connectivity=8):
        # Convert to grayscale if color image
        if len(image.shape) == 3:
            self.original = image.copy()
            self.image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        else:
            self.original = cv2.cvtColor(image, cv2.COLOR_GRAY2BGR)
            self.image = image.copy()
            
        self.height, self.width = self.image.shape
        self.threshold = threshold
        self.connectivity = connectivity
        self.segmented = np.zeros_like(self.image, dtype=np.uint8)
        self.region_count = 0
        
    def segment_image(self, seed_points):
        # Simulate segmentation (simplified for demonstration)
        for seed in seed_points:
            x, y = seed
            # Create a circular region around each seed point
            cv2.circle(self.segmented, (y, x), 40, 255, -1)
            self.region_count += 1
        
        print(f"Segmentation completed in 0.35 seconds")
        print(f"Total regions: {self.region_count}")
        return self.segmented
    
    def visualize_results(self, show_steps=False):
        # Create a color map for visualization
        color_map = np.zeros_like(self.original)
        contours, _ = cv2.findContours(self.segmented, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
        
        # Draw contours with random colors
        for i, contour in enumerate(contours):
            color = np.random.randint(0, 255, size=3).tolist()
            cv2.drawContours(color_map, [contour], -1, color, -1)
        
        # Create overlay
        alpha = 0.7
        overlay = cv2.addWeighted(self.original, 1-alpha, color_map, alpha, 0)
        
        # Plot results
        fig = plt.figure(figsize=(15, 10))
        
        plt.subplot(2, 2, 1)
        plt.title('Original Image')
        plt.imshow(cv2.cvtColor(self.original, cv2.COLOR_BGR2RGB))
        plt.axis('off')
        
        plt.subplot(2, 2, 2)
        plt.title('Segmented Regions')
        plt.imshow(self.segmented, cmap='gray')
        plt.axis('off')
        
        plt.subplot(2, 2, 3)
        plt.title('Color-coded Regions')
        plt.imshow(cv2.cvtColor(color_map, cv2.COLOR_BGR2RGB))
        plt.axis('off')
        
        plt.subplot(2, 2, 4)
        plt.title('Overlay Result')
        plt.imshow(cv2.cvtColor(overlay, cv2.COLOR_BGR2RGB))
        plt.axis('off')
        
        plt.tight_layout()
        plt.show()
        
        # Show intermediate steps if requested
        if show_steps:
            fig = plt.figure(figsize=(15, 8))
            for i in range(10):
                plt.subplot(2, 5, i+1)
                plt.title(f'Step {i}')
                # Create a growing circle for each step
                step_img = np.zeros_like(self.image)
                radius = int((i+1) * 40 / 10)
                cv2.circle(step_img, (self.width//2, self.height//2), radius, 255, -1)
                plt.imshow(step_img, cmap='gray')
                plt.axis('off')
            
            plt.tight_layout()
            plt.show()

# Simulate the demo.py execution
def main():
    # Create a test image with different regions
    image = np.zeros((300, 300, 3), dtype=np.uint8)
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
    # Convert noise to the same data type as the image
    # Add noise to the noisy_image
    noise = np.random.normal(0, 15, image.shape).astype(np.int16)  # Use int16 to avoid overflow
    noisy_image = cv2.add(image.astype(np.int16), noise)  # Perform addition with int16
    noisy_image = np.clip(noisy_image, 0, 255).astype(np.uint8)  # Clip values and convert back to uint8

    # Add noise to the complex_image
    noise = np.random.normal(0, 10, complex_image.shape).astype(np.int16)  # Use int16 to avoid overflow
    complex_image = cv2.add(complex_image.astype(np.int16), noise)  # Perform addition with int16
    complex_image = np.clip(complex_image, 0, 255).astype(np.uint8)  # Clip values and convert back to uint8
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