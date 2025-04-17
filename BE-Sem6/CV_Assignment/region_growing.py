import numpy as np
import cv2
import matplotlib.pyplot as plt
from skimage import io, color
from queue import Queue
import time

class RegionGrowing:
    def __init__(self, image, threshold=10, connectivity=8):
        """
        Initialize the Region Growing segmentation algorithm.
        
        Parameters:
        -----------
        image : ndarray
            Input image (grayscale or color)
        threshold : int
            Threshold for pixel similarity
        connectivity : int
            Connectivity type (4 or 8)
        """
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
        self.visited = np.zeros((self.height, self.width), dtype=bool)
        self.region_count = 0
        self.steps = []  # Store intermediate steps for visualization
        
    def _get_neighbors(self, point):
        """Get neighboring pixels based on connectivity."""
        x, y = point
        neighbors = []
        
        # 4-connectivity: North, East, South, West
        if self.connectivity == 4:
            if x > 0:
                neighbors.append((x-1, y))  # North
            if y < self.width-1:
                neighbors.append((x, y+1))  # East
            if x < self.height-1:
                neighbors.append((x+1, y))  # South
            if y > 0:
                neighbors.append((x, y-1))  # West
        
        # 8-connectivity: 4-connectivity + diagonals
        elif self.connectivity == 8:
            for i in range(-1, 2):
                for j in range(-1, 2):
                    if i == 0 and j == 0:
                        continue
                    ni, nj = x + i, y + j
                    if 0 <= ni < self.height and 0 <= nj < self.width:
                        neighbors.append((ni, nj))
        
        return neighbors
    
    def grow_region(self, seed_point):
        """
        Grow a region from the seed point.
        
        Parameters:
        -----------
        seed_point : tuple
            Starting point (x, y) for region growing
        
        Returns:
        --------
        region : ndarray
            Binary mask of the segmented region
        """
        if self.visited[seed_point]:
            return None
        
        self.region_count += 1
        region = np.zeros_like(self.image, dtype=np.uint8)
        seed_value = self.image[seed_point]
        
        # Use Queue for breadth-first search
        queue = Queue()
        queue.put(seed_point)
        self.visited[seed_point] = True
        region[seed_point] = 255
        
        step_count = 0
        while not queue.empty():
            current = queue.get()
            
            for neighbor in self._get_neighbors(current):
                x, y = neighbor
                
                # Skip if already visited
                if self.visited[x, y]:
                    continue
                
                # Check similarity
                if abs(int(self.image[x, y]) - int(seed_value)) <= self.threshold:
                    queue.put((x, y))
                    self.visited[x, y] = True
                    region[x, y] = 255
            
            # Save intermediate step (every 1000 pixels to avoid too many images)
            step_count += 1
            if step_count % 1000 == 0:
                self.steps.append(region.copy())
        
        # Save final region
        self.steps.append(region.copy())
        return region
    
    def segment_image(self, seed_points=None):
        """
        Segment the entire image using region growing.
        
        Parameters:
        -----------
        seed_points : list of tuples
            List of seed points (x, y). If None, automatic seed selection is used.
        
        Returns:
        --------
        segmented : ndarray
            Segmented image
        """
        start_time = time.time()
        
        # Automatic seed selection if not provided
        if seed_points is None:
            seed_points = []
            step = max(self.height, self.width) // 10  # Grid-based seed selection
            for i in range(0, self.height, step):
                for j in range(0, self.width, step):
                    seed_points.append((i, j))
        
        # Apply region growing for each seed point
        regions = []
        for seed in seed_points:
            region = self.grow_region(seed)
            if region is not None:
                regions.append(region)
                self.segmented = cv2.bitwise_or(self.segmented, region)
        
        end_time = time.time()
        print(f"Segmentation completed in {end_time - start_time:.2f} seconds")
        print(f"Total regions: {self.region_count}")
        
        return self.segmented
    
    def visualize_results(self, show_steps=False):
        """Visualize the segmentation results."""
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
        plt.figure(figsize=(15, 10))
        
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
        if show_steps and self.steps:
            num_steps = min(10, len(self.steps))
            step_indices = np.linspace(0, len(self.steps)-1, num_steps, dtype=int)
            
            plt.figure(figsize=(15, 8))
            for i, idx in enumerate(step_indices):
                plt.subplot(2, 5, i+1)
                plt.title(f'Step {idx}')
                plt.imshow(self.steps[idx], cmap='gray')
                plt.axis('off')
            
            plt.tight_layout()
            plt.show()