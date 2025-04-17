import numpy as np
import cv2
import matplotlib.pyplot as plt
from skimage import io, color, filters
from queue import Queue
import time
from matplotlib.widgets import Slider, Button
import tkinter as tk
from tkinter import filedialog
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

class AdvancedRegionGrowing:
    def __init__(self, image, threshold=10, connectivity=8, feature_type='intensity'):
        """
        Initialize the Advanced Region Growing segmentation algorithm.
        
        Parameters:
        -----------
        image : ndarray
            Input image (grayscale or color)
        threshold : int
            Threshold for pixel similarity
        connectivity : int
            Connectivity type (4 or 8)
        feature_type : str
            Feature to use for similarity ('intensity', 'texture', 'color')
        """
        self.original = image.copy()
        
        # Convert to grayscale if color image
        if len(image.shape) == 3:
            self.image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
            self.is_color = True
        else:
            self.image = image.copy()
            self.is_color = False
            
        self.height, self.width = self.image.shape
        self.threshold = threshold
        self.connectivity = connectivity
        self.feature_type = feature_type
        self.segmented = np.zeros_like(self.image, dtype=np.uint8)
        self.visited = np.zeros((self.height, self.width), dtype=bool)
        self.region_count = 0
        self.steps = []  # Store intermediate steps for visualization
        
        # Precompute features
        self.features = self._compute_features()
        
    def _compute_features(self):
        """Compute features for similarity measurement."""
        if self.feature_type == 'intensity':
            return self.image
        
        elif self.feature_type == 'texture':
            # Compute texture features (Gabor filter responses)
            features = np.zeros((self.height, self.width, 4))
            
            # Apply Gabor filters at different orientations
            for i, theta in enumerate([0, np.pi/4, np.pi/2, 3*np.pi/4]):
                kernel = cv2.getGaborKernel((21, 21), 5, theta, 10, 1, 0, ktype=cv2.CV_32F)
                filtered = cv2.filter2D(self.image, cv2.CV_8UC3, kernel)
                features[:, :, i] = filtered
                
            return features
        
        elif self.feature_type == 'color' and self.is_color:
            # Use color features (HSV color space)
            hsv = cv2.cvtColor(self.original, cv2.COLOR_BGR2HSV)
            return hsv
        
        # Default to intensity
        return self.image
    
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
    
    def _calculate_similarity(self, p1, p2):
        """Calculate similarity between two pixels based on feature type."""
        if self.feature_type == 'intensity':
            return abs(int(self.features[p1]) - int(self.features[p2]))
        
        elif self.feature_type == 'texture':
            # Euclidean distance in texture feature space
            return np.sqrt(np.sum((self.features[p1] - self.features[p2])**2))
        
        elif self.feature_type == 'color' and self.is_color:
            # Distance in HSV space (weighted)
            h_weight, s_weight, v_weight = 0.5, 0.3, 0.2
            h_diff = min(abs(self.features[p1][0] - self.features[p2][0]), 180 - abs(self.features[p1][0] - self.features[p2][0])) / 180.0
            s_diff = abs(self.features[p1][1] - self.features[p2][1]) / 255.0
            v_diff = abs(self.features[p1][2] - self.features[p2][2]) / 255.0
            
            return h_weight * h_diff + s_weight * s_diff + v_weight * v_diff
        
        # Default to intensity difference
        return abs(int(self.features[p1]) - int(self.features[p2]))
    
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
                
                # Check similarity based on feature type
                similarity = self._calculate_similarity(current, (x, y))
                
                if similarity <= self.threshold:
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
            seed_points = self._automatic_seed_selection()
        
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
    
    def _automatic_seed_selection(self):
        """Automatically select seed points based on image properties."""
        # Use edge detection to avoid placing seeds on edges
        edges = cv2.Canny(self.image, 50, 150)
        
        # Dilate edges to create a buffer zone
        kernel = np.ones((5, 5), np.uint8)
        dilated_edges = cv2.dilate(edges, kernel, iterations=1)
        
        # Create a grid of potential seed points
        seed_points = []
        step = max(self.height, self.width) // 15  # Adjust grid density
        
        for i in range(step//2, self.height, step):
            for j in range(step//2, self.width, step):
                # Avoid placing seeds on or near edges
                if dilated_edges[i, j] == 0:
                    seed_points.append((i, j))
        
        return seed_points
    
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
    
    def interactive_segmentation(self):
        """Interactive segmentation with adjustable parameters."""
        # Create a figure for interactive visualization
        fig, ax = plt.subplots(figsize=(10, 8))
        plt.subplots_adjust(bottom=0.25)
        
        # Display the original image
        ax.imshow(cv2.cvtColor(self.original, cv2.COLOR_BGR2RGB))
        ax.set_title('Click to add seed points, then press "Segment"')
        ax.axis('off')
        
        # Store seed points
        seed_points = []
        seed_plot, = ax.plot([], [], 'ro', markersize=8)
        
        # Create sliders for parameters
        ax_threshold = plt.axes([0.25, 0.15, 0.65, 0.03])
        ax_connectivity = plt.axes([0.25, 0.1, 0.65, 0.03])
        
        threshold_slider = Slider(ax_threshold, 'Threshold', 1, 50, valinit=self.threshold, valstep=1)
        connectivity_slider = Slider(ax_connectivity, 'Connectivity', 4, 8, valinit=self.connectivity, valstep=4)
        
        # Create buttons
        ax_segment = plt.axes([0.8, 0.025, 0.1, 0.04])
        ax_reset = plt.axes([0.65, 0.025, 0.1, 0.04])
        
        segment_button = Button(ax_segment, 'Segment')
        reset_button = Button(ax_reset, 'Reset')
        
        # Event handlers
        def on_click(event):
            if event.inaxes == ax:
                x, y = int(event.ydata), int(event.xdata)
                if 0 <= x < self.height and 0 <= y < self.width:
                    seed_points.append((x, y))
                    seed_plot.set_data([p[1] for p in seed_points], [p[0] for p in seed_points])
                    fig.canvas.draw_idle()
        
        def on_segment(event):
            # Update parameters
            self.threshold = threshold_slider.val
            self.connectivity = int(connectivity_slider.val)
            
            # Reset segmentation
            self.segmented = np.zeros_like(self.image, dtype=np.uint8)
            self.visited = np.zeros((self.height, self.width), dtype=bool)
            self.region_count = 0
            self.steps = []
            
            # Perform segmentation
            if seed_points:
                self.segment_image(seed_points)
                
                # Update display
                ax.clear()
                ax.imshow(cv2.cvtColor(self.original, cv2.COLOR_BGR2RGB))
                
                # Overlay segmentation
                contours, _ = cv2.findContours(self.segmented, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
                for contour in contours:
                    color = np.random.randint(0, 255, size=3).tolist()
                    contour_img = np.zeros_like(self.original)
                    cv2.drawContours(contour_img, [contour], -1, color, -1)
                    ax.imshow(cv2.cvtColor(contour_img, cv2.COLOR_BGR2RGB), alpha=0.5)
                
                ax.plot([p[1] for p in seed_points], [p[0] for p in seed_points], 'ro', markersize=8)
                ax.set_title(f'Segmentation Result: {self.region_count} regions')
                ax.axis('off')
                fig.canvas.draw_idle()
        
        def on_reset(event):
            # Clear seed points
            seed_points.clear()
            seed_plot.set_data([], [])
            
            # Reset display
            ax.clear()
            ax.imshow(cv2.cvtColor(self.original, cv2.COLOR_BGR2RGB))
            ax.set_title('Click to add seed points, then press "Segment"')
            ax.axis('off')
            fig.canvas.draw_idle()
        
        # Connect event handlers
        fig.canvas.mpl_connect('button_press_event', on_click)
        segment_button.on_clicked(on_segment)
        reset_button.on_clicked(on_reset)
        
        plt.show()

# GUI Application
class RegionGrowingApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Region Growing Segmentation")
        self.root.geometry("1200x800")
        
        # Create frames
        self.control_frame = tk.Frame(root, width=300, height=800)
        self.control_frame.pack(side=tk.LEFT, fill=tk.Y)
        
        self.display_frame = tk.Frame(root, width=900, height=800)
        self.display_frame.pack(side=tk.RIGHT, fill=tk.BOTH, expand=True)
        
        # Control elements
        tk.Label(self.control_frame, text="Region Growing Segmentation", font=("Arial", 16)).pack(pady=10)
        
        # Load image button
        tk.Button(self.control_frame, text="Load Image", command=self.load_image).pack(pady=10)
        
        # Parameter controls
        tk.Label(self.control_frame, text="Threshold:").pack(pady=5)
        self.threshold_var = tk.IntVar(value=15)
        tk.Scale(self.control_frame, from_=1, to=50, orient=tk.HORIZONTAL, 
                 variable=self.threshold_var, length=250).pack()
        
        tk.Label(
                 variable=self.threshold_var, length=250).pack()
        
        tk.Label(self.control_frame, text="Connectivity:").pack(pady=5)
        self.connectivity_var = tk.IntVar(value=8)
        tk.Radiobutton(self.control_frame, text="4-connectivity", variable=self.connectivity_var, value=4).pack(anchor=tk.W)
        tk.Radiobutton(self.control_frame, text="8-connectivity", variable=self.connectivity_var, value=8).pack(anchor=tk.W)
        
        # Feature type selection
        tk.Label(self.control_frame, text="Feature Type:").pack(pady=5)
        self.feature_var = tk.StringVar(value="intensity")
        tk.Radiobutton(self.control_frame, text="Intensity", variable=self.feature_var, value="intensity").pack(anchor=tk.W)
        tk.Radiobutton(self.control_frame, text="Texture", variable=self.feature_var, value="texture").pack(anchor=tk.W)
        tk.Radiobutton(self.control_frame, text="Color", variable=self.feature_var, value="color").pack(anchor=tk.W)
        
        # Seed point controls
        tk.Label(self.control_frame, text="Seed Points:").pack(pady=5)
        self.seed_mode_var = tk.StringVar(value="manual")
        tk.Radiobutton(self.control_frame, text="Manual Selection", variable=self.seed_mode_var, value="manual").pack(anchor=tk.W)
        tk.Radiobutton(self.control_frame, text="Automatic", variable=self.seed_mode_var, value="auto").pack(anchor=tk.W)
        
        # Action buttons
        tk.Button(self.control_frame, text="Segment", command=self.segment_image).pack(pady=10)
        tk.Button(self.control_frame, text="Reset", command=self.reset).pack(pady=5)
        tk.Button(self.control_frame, text="Save Result", command=self.save_result).pack(pady=5)
        
        # Status label
        self.status_var = tk.StringVar(value="Load an image to begin")
        tk.Label(self.control_frame, textvariable=self.status_var, wraplength=250).pack(pady=10)
        
        # Initialize variables
        self.image = None
        self.segmenter = None
        self.seed_points = []
        self.fig = None
        self.canvas = None
        self.ax = None
        
        # Create initial figure
        self.create_figure()
    
    def create_figure(self):
        """Create the matplotlib figure for display."""
        self.fig = plt.Figure(figsize=(9, 7))
        self.canvas = FigureCanvasTkAgg(self.fig, master=self.display_frame)
        self.canvas.get_tk_widget().pack(fill=tk.BOTH, expand=True)
        
        self.ax = self.fig.add_subplot(111)
        self.ax.set_title("No Image Loaded")
        self.ax.axis('off')
        self.canvas.draw()
    
    def load_image(self):
        """Load an image from file."""
        file_path = filedialog.askopenfilename(
            filetypes=[("Image files", "*.jpg *.jpeg *.png *.bmp *.tif *.tiff")])
        
        if file_path:
            try:
                self.image = cv2.imread(file_path)
                if self.image is None:
                    self.status_var.set("Error: Could not load image")
                    return
                
                # Display the image
                self.ax.clear()
                self.ax.imshow(cv2.cvtColor(self.image, cv2.COLOR_BGR2RGB))
                self.ax.set_title("Click to add seed points")
                self.ax.axis('off')
                self.canvas.draw()
                
                # Reset seed points
                self.seed_points = []
                
                # Connect click event
                self.canvas.mpl_connect('button_press_event', self.on_click)
                
                self.status_var.set(f"Image loaded: {file_path.split('/')[-1]}")
            except Exception as e:
                self.status_var.set(f"Error: {str(e)}")
    
    def on_click(self, event):
        """Handle mouse clicks for seed point selection."""
        if event.inaxes == self.ax and self.seed_mode_var.get() == "manual" and self.image is not None:
            x, y = int(event.ydata), int(event.xdata)
            if 0 <= x < self.image.shape[0] and 0 <= y < self.image.shape[1]:
                self.seed_points.append((x, y))
                
                # Update display
                self.ax.plot(y, x, 'ro', markersize=8)
                self.canvas.draw()
                
                self.status_var.set(f"Added seed point at ({x}, {y}). Total: {len(self.seed_points)}")
    
    def segment_image(self):
        """Perform image segmentation."""
        if self.image is None:
            self.status_var.set("Error: No image loaded")
            return
        
        # Get parameters
        threshold = self.threshold_var.get()
        connectivity = self.connectivity_var.get()
        feature_type = self.feature_var.get()
        
        try:
            # Create segmenter
            self.segmenter = AdvancedRegionGrowing(
                self.image, 
                threshold=threshold,
                connectivity=connectivity,
                feature_type=feature_type
            )
            
            # Use automatic seed points if selected
            if self.seed_mode_var.get() == "auto" or not self.seed_points:
                self.seed_points = self.segmenter._automatic_seed_selection()
                self.status_var.set(f"Using {len(self.seed_points)} automatic seed points")
            
            # Perform segmentation
            start_time = time.time()
            segmented = self.segmenter.segment_image(self.seed_points)
            end_time = time.time()
            
            # Display results
            self.display_results()
            
            self.status_var.set(f"Segmentation completed in {end_time - start_time:.2f} seconds. "
                               f"Found {self.segmenter.region_count} regions.")
        except Exception as e:
            self.status_var.set(f"Error during segmentation: {str(e)}")
    
    def display_results(self):
        """Display segmentation results."""
        if self.segmenter is None:
            return
        
        # Create color map for visualization
        color_map = np.zeros_like(self.image)
        contours, _ = cv2.findContours(self.segmenter.segmented, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
        
        # Draw contours with random colors
        for contour in contours:
            color = np.random.randint(0, 255, size=3).tolist()
            cv2.drawContours(color_map, [contour], -1, color, -1)
        
        # Create overlay
        alpha = 0.7
        overlay = cv2.addWeighted(self.image, 1-alpha, color_map, alpha, 0)
        
        # Update display
        self.fig.clear()
        
        # Original image
        ax1 = self.fig.add_subplot(2, 2, 1)
        ax1.imshow(cv2.cvtColor(self.image, cv2.COLOR_BGR2RGB))
        ax1.set_title('Original Image')
        ax1.axis('off')
        
        # Segmented regions
        ax2 = self.fig.add_subplot(2, 2, 2)
        ax2.imshow(self.segmenter.segmented, cmap='gray')
        ax2.set_title('Segmented Regions')
        ax2.axis('off')
        
        # Color-coded regions
        ax3 = self.fig.add_subplot(2, 2, 3)
        ax3.imshow(cv2.cvtColor(color_map, cv2.COLOR_BGR2RGB))
        ax3.set_title('Color-coded Regions')
        ax3.axis('off')
        
        # Overlay result
        ax4 = self.fig.add_subplot(2, 2, 4)
        ax4.imshow(cv2.cvtColor(overlay, cv2.COLOR_BGR2RGB))
        ax4.set_title('Overlay Result')
        ax4.axis('off')
        
        self.fig.tight_layout()
        self.canvas.draw()
    
    def reset(self):
        """Reset the application state."""
        if self.image is not None:
            # Clear seed points
            self.seed_points = []
            
            # Reset display
            self.ax.clear()
            self.ax.imshow(cv2.cvtColor(self.image, cv2.COLOR_BGR2RGB))
            self.ax.set_title("Click to add seed points")
            self.ax.axis('off')
            self.canvas.draw()
            
            self.status_var.set("Reset completed. Add seed points.")
    
    def save_result(self):
        """Save the segmentation result."""
        if self.segmenter is None or self.segmenter.segmented is None:
            self.status_var.set("Error: No segmentation result to save")
            return
        
        file_path = filedialog.asksaveasfilename(
            defaultextension=".png",
            filetypes=[("PNG files", "*.png"), ("JPEG files", "*.jpg"), ("All files", "*.*")]
        )
        
        if file_path:
            try:
                # Create color map for visualization
                color_map = np.zeros_like(self.image)
                contours, _ = cv2.findContours(self.segmenter.segmented, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
                
                # Draw contours with random colors
                for contour in contours:
                    color = np.random.randint(0, 255, size=3).tolist()
                    cv2.drawContours(color_map, [contour], -1, color, -1)
                
                # Create overlay
                alpha = 0.7
                overlay = cv2.addWeighted(self.image, 1-alpha, color_map, alpha, 0)
                
                # Save the result
                cv2.imwrite(file_path, overlay)
                self.status_var.set(f"Result saved to {file_path}")
            except Exception as e:
                self.status_var.set(f"Error saving result: {str(e)}")

# Main function to run the GUI application
def run_gui():
    root = tk.Tk()
    app = RegionGrowingApp(root)
    root.mainloop()

if __name__ == "__main__":
    run_gui()