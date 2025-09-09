import numpy as np

def solve(input_grid):
    """
    Transform the input grid according to the pattern.
    
    Args:
        input_grid (List[List[int]]): 2D list of integers representing the input grid
            0: Black     1: Blue       2: Red        3: Green     4: Yellow
            5: Grey      6: Fuchsia    7: Orange     8: Teal      9: Brown
            
    Returns:
        List[List[int]]: 2D list of integers representing the transformed grid
    """
    # Get grid dimensions
    height = len(input_grid)
    if height == 0:
        return []  # Edge case: empty grid
    width = len(input_grid[0])
    
    # Extract the order sequence from the second last row
    if height < 2:
        return [row[:] for row in input_grid]  # No order row, return copy
    order_row = input_grid[-2]
    seq = [x for x in order_row if x > 0]
    
    # Find all building label blocks using DFS to get bounds
    visited = [[False for _ in range(width)] for _ in range(height)]
    buildings_by_id = {}
    
    for r in range(height):
        for c in range(width):
            val = input_grid[r][c]
            if val > 1 and not visited[r][c]:
                # Start DFS for connected component of same val
                min_r, max_r = r, r
                min_c, max_c = c, c
                stack = [(r, c)]
                visited[r][c] = True
                while stack:
                    rr, cc = stack.pop()
                    min_r = min(min_r, rr)
                    max_r = max(max_r, rr)
                    min_c = min(min_c, cc)
                    max_c = max(max_c, cc)
                    for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                        nr, nc = rr + dr, cc + dc
                        if 0 <= nr < height and 0 <= nc < width and not visited[nr][nc] and input_grid[nr][nc] == val:
                            visited[nr][nc] = True
                            stack.append((nr, nc))
                # Add building
                b = {'id': val, 'min_r': min_r, 'max_r': max_r, 'min_c': min_c, 'max_c': max_c}
                if val not in buildings_by_id:
                    buildings_by_id[val] = []
                buildings_by_id[val].append(b)
    
    # Sort buildings for each ID by min_r then min_c
    for idd in buildings_by_id:
        buildings_by_id[idd].sort(key=lambda b: (b['min_r'], b['min_c']))
    
    # Get the sequence of buildings to visit
    path_buildings = []
    counters = {idd: 0 for idd in buildings_by_id}
    for idd in seq:
        if idd in buildings_by_id and counters[idd] < len(buildings_by_id[idd]):
            path_buildings.append(buildings_by_id[idd][counters[idd]])
            counters[idd] += 1
    
    # If no paths or insufficient buildings, return copy (edge case)
    if len(path_buildings) < 2:
        return [row[:] for row in input_grid]
    
    # Create output grid as copy of input
    output_grid = [row[:] for row in input_grid]
    
    # Connect consecutive buildings
    for i in range(len(path_buildings) - 1):
        b1 = path_buildings[i]
        b2 = path_buildings[i + 1]
        from_id = b1['id']
        
        # Check if horizontal (same label rows)
        if b1['min_r'] == b2['min_r'] and b1['max_r'] == b2['max_r']:
            # Determine left and right
            if b1['min_c'] < b2['min_c']:
                left_max_c = b1['max_c']
                right_min_c = b2['min_c']
            else:
                left_max_c = b2['max_c']
                right_min_c = b1['min_c']
            start_c = left_max_c + 1
            end_c = right_min_c - 1
            if start_c > end_c:
                continue  # No gap, adjacent or overlapping (edge case)
            for rr in range(b1['min_r'], b1['max_r'] + 1):
                for cc in range(start_c, end_c + 1):
                    if output_grid[rr][cc] == 0:
                        output_grid[rr][cc] = from_id
        
        # Check if vertical (same label cols)
        elif b1['min_c'] == b2['min_c'] and b1['max_c'] == b2['max_c']:
            # Determine upper and lower
            if b1['min_r'] < b2['min_r']:
                upper_max_r = b1['max_r']
                lower_min_r = b2['min_r']
            else:
                upper_max_r = b2['max_r']
                lower_min_r = b1['min_r']
            start_r = upper_max_r + 1
            end_r = lower_min_r - 1
            if start_r > end_r:
                continue  # No gap
            for rr in range(start_r, end_r + 1):
                for cc in range(b1['min_c'], b1['max_c'] + 1):
                    if output_grid[rr][cc] == 0:
                        output_grid[rr][cc] = from_id
    
    return output_grid