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
                component_cells = [(r,c)]
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
                            component_cells.append((nr,nc))
                # Add building
                b = {'id': val, 'min_r': min_r, 'max_r': max_r, 'min_c': min_c, 'max_c': max_c, 'cells': set(component_cells)}
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

        # Cells of b2 that should not be overwritten
        b2_cells = b2['cells']

        row_overlap = max(b1['min_r'], b2['min_r']) <= min(b1['max_r'], b2['max_r'])
        col_overlap = max(b1['min_c'], b2['min_c']) <= min(b1['max_c'], b2['max_c'])

        # Straight connections
        if row_overlap or col_overlap:
            if row_overlap:
                if b1['max_c'] < b2['min_c']:
                    left_b, right_b = b1, b2
                else:
                    left_b, right_b = b2, b1
                start_c, end_c = left_b['max_c'] + 1, right_b['min_c']
                for rr in range(b1['min_r'], b1['max_r'] + 1):
                    for cc in range(start_c, end_c):
                        if (rr, cc) in b2_cells:
                            break
                        output_grid[rr][cc] = from_id
            if col_overlap:
                if b1['max_r'] < b2['min_r']:
                    top_b, bottom_b = b1, b2
                else:
                    top_b, bottom_b = b2, b1
                start_r, end_r = top_b['max_r'] + 1, bottom_b['min_r']
                for cc in range(b1['min_c'], b1['max_c'] + 1):
                    for rr in range(start_r, end_r):
                        if (rr, cc) in b2_cells:
                            break
                        output_grid[rr][cc] = from_id
        # L-shaped connections
        else:
            # H-then-V
            r1_center = (b1['min_r'] + b1['max_r']) // 2
            c2_center = (b2['min_c'] + b2['max_c']) // 2

            # Horizontal segment
            c_start = min(b1['max_c'] + 1, c2_center)
            c_end = max(b1['max_c'] + 1, c2_center)
            if b1['min_c'] > c2_center:
                 c_start = min(c2_center, b1['min_c'])
                 c_end = max(c2_center, b1['min_c'])

            for c in range(c_start, c_end):
                if (r1_center, c) in b2_cells: break
                output_grid[r1_center][c] = from_id

            # Vertical segment
            r_start = min(r1_center, b2['max_r'] + 1)
            r_end = max(r1_center, b2['max_r'] + 1)
            if r1_center > b2['min_r']:
                r_start = min(b2['min_r'], r1_center)
                r_end = max(b2['min_r'], r1_center)

            for r in range(r_start, r_end):
                if (r, c2_center) in b2_cells: break
                output_grid[r][c2_center] = from_id


    return output_grid

if __name__ == '__main__':
    # Test with the first case from error.txt
    input_grid = [
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 4, 4, 4, 4, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 4, 4, 4, 4, 1, 0, 0, 0, 0, 1, 1, 3, 3, 1, 1, 0, 0, 0, 0],
      [0, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 4, 4, 4, 4, 1, 0, 0, 0, 0, 1, 1, 3, 3, 1, 1, 0, 0, 0, 0],
      [0, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 4, 4, 4, 4, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 1, 1, 3, 3, 1, 1, 6, 6, 6, 1, 1, 6, 6, 1, 1, 0, 0, 0, 0, 1, 1, 9, 9, 1, 1, 0, 0, 0, 0],
      [0, 1, 1, 3, 3, 1, 1, 6, 6, 6, 1, 1, 6, 6, 1, 1, 0, 0, 0, 0, 1, 1, 9, 9, 1, 1, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 0, 0, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 5, 5, 5, 5, 1, 0, 0, 0, 0],
      [0, 1, 1, 9, 9, 1, 1, 0, 0, 0, 1, 1, 4, 4, 1, 1, 0, 0, 0, 0, 1, 5, 5, 5, 5, 1, 0, 0, 0, 0],
      [0, 1, 1, 9, 9, 1, 1, 0, 0, 0, 1, 1, 4, 4, 1, 1, 0, 0, 0, 0, 1, 5, 5, 5, 5, 1, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 5, 5, 5, 5, 1, 0, 0, 0, 0],
      [0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 2, 0, 4, 0, 3, 0, 9, 0, 5, 0, 4, 0, 6, 0, 3, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    ]
    expected_output = [
      [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0],[0,1,2,2,2,2,1,2,2,2,1,4,4,4,4,1,4,4,4,4,1,1,1,1,1,1,0,0,0,0],[0,1,2,2,2,2,1,2,2,2,1,4,4,4,4,1,4,4,4,4,1,1,3,3,1,1,0,0,0,0],[0,1,2,2,2,2,1,2,2,2,1,4,4,4,4,1,4,4,4,4,1,1,3,3,1,1,0,0,0,0],[0,1,2,2,2,2,1,2,2,2,1,4,4,4,4,1,4,4,4,4,1,1,1,1,1,1,0,0,0,0],[0,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0],[0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0],[0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0],[0,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0],[0,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0],[0,1,1,3,3,1,1,6,6,6,1,1,6,6,1,1,6,6,6,6,1,1,9,9,1,1,0,0,0,0],[0,1,1,3,3,1,1,6,6,6,1,1,6,6,1,1,6,6,6,6,1,1,9,9,1,1,0,0,0,0],[0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,6,6,6,6,1,1,1,1,1,1,0,0,0,0],[0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,6,6,6,6,1,1,1,1,1,1,0,0,0,0],[0,0,0,3,3,0,0,0,0,0,0,0,4,4,0,0,6,6,6,6,0,0,9,9,0,0,0,0,0,0],[0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,5,5,5,5,1,1,1,1,1,1,0,0,0,0],[0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,5,5,5,5,1,5,5,5,5,1,0,0,0,0],[0,1,1,9,9,1,1,0,0,0,1,1,4,4,1,1,5,5,5,5,1,5,5,5,5,1,0,0,0,0],[0,1,1,9,9,1,1,0,0,0,1,1,4,4,1,1,5,5,5,5,1,5,5,5,5,1,0,0,0,0],[0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,5,5,5,5,1,5,5,5,5,1,0,0,0,0],[0,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,2,0,4,0,3,0,9,0,5,0,4,0,6,0,3,0,9,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
    ]
    
    actual_output = solve(input_grid)
    
    if actual_output == expected_output:
        print("Test passed!")
    else:
        print("Test failed!")