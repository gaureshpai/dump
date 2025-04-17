import numpy as np
import cv2
import matplotlib.pyplot as plt
import time
from region_growing import RegionGrowing
from advanced_region_growing import AdvancedRegionGrowing
from skimage.segmentation import slic, watershed
from skimage.feature import peak_local_max
from scipy import ndimage as ndi
from skimage import filters

def compare_methods(image, seed_points=None):
    """
    Compare different segmentation methods.
    
    Parameters:
    -----------
    image : ndarray
        Input image
    seed_points : list of tuples
        List of seed points (x, y)
    """
    # Make a copy of the original image
    original = image.copy()
    
    # Convert to grayscale if color image
    if len(image.shape) == 3:
        gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    else:
        gray = image.copy()
        original = cv2.cvtColor(gray, cv2.COLOR_GRAY2BGR)
    
    # If no seed points provided, use center of image
    if seed_points is None:
        h, w = gray.shape
        seed_points = [(h//2, w//2)]
    
    # Method 1: Basic Region Growing
    start_time = time.time()
    rg = RegionGrowing(image, threshold=15, connectivity=8)
    rg_result = rg.segment_image(seed_points)
    rg_time = time.time() - start_time
    
    # Method 2: Advanced Region Growing (Texture)
    start_time = time.time()
    arg_texture = AdvancedRegionGrowing(image, threshold=20, connectivity=8, feature_type='texture')
    arg_texture_result = arg_texture.segment_image(seed_points)
    arg_texture_time = time.time() - start_time
    
    # Method 3: Advanced Region Growing (Color, if applicable)
    if len(image.shape) == 3:
        start_time = time.time()
        arg_color = AdvancedRegionGrowing(image, threshold=25, connectivity=8, feature_type='color')
        arg_color_result = arg_color.segment_image(seed_points)
        arg_color_time = time.time() - start_time
    else:
        arg_color_result = np.zeros_like(gray)
        arg_color_time = 0
    
    # Method 4: SLIC Superpixels (for comparison)
    start_time = time.time()
    segments_slic = slic(image, n_segments=100, compactness=10, sigma=1, start_label=1)
    slic_result = np.zeros_like(gray)
    
    # Extract the superpixels that contain seed points
    for seed in seed_points:
        x, y = seed
        if 0 <= x < gray.shape[0] and 0 <= y < gray.shape[1]:
            segment_id = segments_slic[x, y]
            slic_result[segments_slic == segment_id] = 255
    
    slic_time = time.time() - start_time
    
    # Method 5: Watershed (for comparison)
    start_time = time.time()
    
    # Generate markers for watershed
    gradient = filters.sobel(gray)
    markers = np.zeros_like(gray)
    
    # Mark seed points
    for seed in seed_points:
        x, y = seed
        if 0 <= x < gray.shape[0] and 0 <= y < gray.shape[1]:
            markers[x, y] = 2  # Foreground
    
    # Mark background (border)
    border_width = 5
    markers[:border_width, :] = 1
    markers[-border_width:, :] = 1
    markers[:, :border_width] = 1
    markers[:, -border_width:] = 1
    
    # Apply watershed
    watershed_result = watershed(gradient, markers)
    watershed_binary = np.zeros_like(gray)
    watershed_binary[watershed_result == 2] = 255
    
    watershed_time = time.time() - start_time
    
    # Create visualization
    fig, axes = plt.subplots(2, 3, figsize=(15, 10))
    
    # Original image with seed points
    axes[0, 0].imshow(cv2.cvtColor(original, cv2.COLOR_BGR2RGB))
    for seed in seed_points:
        x, y = seed
        axes[0, 0].plot(y, x, 'ro', markersize=8)
    axes[0, 0].set_title('Original Image with Seed Points')
    axes[0, 0].axis('off')
    
    # Basic Region Growing
    axes[0, 1].imshow(rg_result, cmap='gray')
    axes[0, 1].set_title(f'Basic Region Growing\nTime: {rg_time:.2f}s')
    axes[0, 1].axis('off')
    
    # Advanced Region Growing (Texture)
    axes[0, 2].imshow(arg_texture_result, cmap='gray')
    axes[0, 2].set_title(f'Advanced RG (Texture)\nTime: {arg_texture_time:.2f}s')
    axes[0, 2].axis('off')
    
    # Advanced Region Growing (Color)
    axes[1, 0].imshow(arg_color_result, cmap='gray')
    axes[1, 0].set_title(f'Advanced RG (Color)\nTime: {arg_color_time:.2f}s')
    axes[1, 0].axis('off')
    
    # SLIC Superpixels
    axes[1, 1].imshow(slic_result, cmap='gray')
    axes[1, 1].set_title(f'SLIC Superpixels\nTime: {slic_time:.2f}s')
    axes[1, 1].axis('off')
    
    # Watershed
    axes[1, 2].imshow(watershed_binary, cmap='gray')
    axes[1, 2].set_title(f'Watershed\nTime: {watershed_time:.2f}s')
    axes[1, 2].axis('off')
    
    plt.tight_layout()
    plt.show()
    
    # Return results for further analysis
    return {
        'basic_rg': (rg_result, rg_time),
        'advanced_rg_texture': (arg_texture_result, arg_texture_time),
        'advanced_rg_color': (arg_color_result, arg_color_time),
        'slic': (slic_result, slic_time),
        'watershed': (watershed_binary, watershed_time)
    }

def evaluate_segmentation(ground_truth, segmentation):
    """
    Evaluate segmentation quality using metrics like Dice coefficient.
    
    Parameters:
    -----------
    ground_truth : ndarray
        Ground truth segmentation mask
    segmentation : ndarray
        Predicted segmentation mask
    
    Returns:
    --------
    metrics : dict
        Dictionary of evaluation metrics
    """
    # Convert to binary
    gt_binary = ground_truth > 0
    seg_binary = segmentation > 0
    
    # Calculate intersection and union
    intersection = np.logical_and(gt_binary, seg_binary).sum()
    union = np.logical_or(gt_binary, seg_binary).sum()
    
    # Calculate metrics
    dice = (2.0 * intersection) / (gt_binary.sum() + seg_binary.sum() + 1e-10)
    jaccard = intersection / (union + 1e-10)
    
    # Calculate false positive and false negative rates
    false_positive = np.logical_and(np.logical_not(gt_binary), seg_binary).sum() / (np.logical_not(gt_binary).sum() + 1e-10)
    false_negative = np.logical_and(gt_binary, np.logical_not(seg_binary)).sum() / (gt_binary.sum() + 1e-10)
    
    return {
        'dice': dice,
        'jaccard': jaccard,
        'false_positive_rate': false_positive,
        'false_negative_rate': false_negative
    }

def main():
    # Create a synthetic test image with ground truth
    image_size = (300, 300)
    
    # Background
    image = np.ones((*image_size, 3), dtype=np.uint8) * 200
    
    # Create objects with different intensities
    ground_truth = np.zeros(image_size, dtype=np.uint8)
    
    # Object 1: Circle
    center1 = (100, 100)
    radius1 = 50
    y, x = np.ogrid[:image_size[0], :image_size[1]]
    mask1 = (x - center1[1]) ** 2 + (y - center1[0]) ** 2 <= radius1 ** 2
    image[mask1] = (120, 80, 80)
    ground_truth[mask1] = 1
    
    # Object 2: Rectangle
    top_left = (150, 150)
    bottom_right = (250, 250)
    mask2 = np.zeros(image_size, dtype=bool)
    mask2[top_left[0]:bottom_right[0], top_left[1]:bottom_right[1]] = True
    image[mask2] = (80, 120, 80)
    ground_truth[mask2] = 2
    
    # Add noise
    noise = np.random.normal(0, 15, image.shape).astype(np.int8)
    noisy_image = cv2.add(image, noise)
    noisy_image = np.clip(noisy_image, 0, 255).astype(np.uint8)
    
    # Define seed points
    seed_points = [(100, 100), (200, 200)]
    
    # Compare methods
    results = compare_methods(noisy_image, seed_points)
    
    # Evaluate results
    print("\nSegmentation Evaluation:")
    print("=======================")
    
    for method_name, (result, time_taken) in results.items():
        metrics = evaluate_segmentation(ground_truth > 0, result)
        print(f"\n{method_name.upper()}:")
        print(f"  Time: {time_taken:.4f} seconds")
        print(f"  Dice Coefficient: {metrics['dice']:.4f}")
        print(f"  Jaccard Index: {metrics['jaccard']:.4f}")
        print(f"  False Positive Rate: {metrics['false_positive_rate']:.4f}")
        print(f"  False Negative Rate: {metrics['false_negative_rate']:.4f}")
    
    # Try with a real medical image if available
    try:
        medical_image = cv2.imread('brain_mri.jpg')
        if medical_image is not None:
            print("\nProcessing medical image...")
            h, w = medical_image.shape[:2]
            medical_seed_points = [(h//2, w//2)]
            compare_methods(medical_image, medical_seed_points)
    except Exception as e:
        print(f"Could not process medical image: {e}")

if __name__ == "__main__":
    main()