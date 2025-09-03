/**
 * @param {number[][]} inputGrid
 * @returns {number[][]}
 */
function solve(inputGrid) {
    const height = inputGrid.length;
    if (height === 0) return [];
    const width = inputGrid[0].length;
    if (width === 0) return [[]];

    const Point = (r, c) => ({ r, c, id: `${r},${c}` });

    // 1. Find building components and their centers
    const buildings = [];
    const visited = Array(height).fill().map(() => Array(width).fill(false));

    for (let r = 0; r < height; r++) {
        for (let c = 0; c < width; c++) {
            if (!visited[r][c] && (inputGrid[r][c] === 1 || inputGrid[r][c] === 2)) {
                const componentType = inputGrid[r][c];
                const q = [Point(r, c)];
                visited[r][c] = true;
                let head = 0;
                let sumR = 0, sumC = 0, count = 0;

                while (head < q.length) {
                    const curr = q[head++];
                    sumR += curr.r;
                    sumC += curr.c;
                    count++;
                    const dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]];
                    for (const [dr, dc] of dirs) {
                        const nr = curr.r + dr;
                        const nc = curr.c + dc;
                        if (nr >= 0 && nr < height && nc >= 0 && nc < width && !visited[nr][nc] && inputGrid[nr][nc] === componentType) {
                            visited[nr][nc] = true;
                            q.push(Point(nr, nc));
                        }
                    }
                }
                const center = Point(Math.round(sumR / count), Math.round(sumC / count));
                buildings.push({ center, type: componentType });
            }
        }
    }

    const postOfficeIndex = buildings.findIndex(b => b.type === 2);
    if (postOfficeIndex === -1) return inputGrid;
    const postOfficeBuilding = buildings.splice(postOfficeIndex, 1)[0];
    buildings.unshift(postOfficeBuilding);

    const n = buildings.length;
    if (n <= 1) return inputGrid;

    // 2. Custom pathfinder with L-shape priority and BFS fallback
    const getPathAndDistance = (start, end) => {
        const pathAttempts = [];
        const dr = Math.sign(end.r - start.r);
        const dc = Math.sign(end.c - start.c);

        // Attempt 1: H-V
        let path1 = [start];
        for (let c = start.c + dc; c !== end.c + dc; c += dc) path1.push(Point(start.r, c));
        for (let r = start.r + dr; r !== end.r + dr; r += dr) path1.push(Point(r, end.c));
        if (!path1.some(p => inputGrid[p.r][p.c] === 5)) pathAttempts.push(path1);

        // Attempt 2: V-H
        let path2 = [start];
        for (let r = start.r + dr; r !== end.r + dr; r += dr) path2.push(Point(r, start.c));
        for (let c = start.c + dc; c !== end.c + dc; c += dc) path2.push(Point(end.r, c));
        if (!path2.some(p => inputGrid[p.r][p.c] === 5)) pathAttempts.push(path2);

        if (pathAttempts.length > 0) {
            const bestPath = pathAttempts.sort((a,b) => a.length - b.length)[0];
            return { path: bestPath, distance: bestPath.length - 1 };
        }

        // Fallback to BFS
        const q = [[start, [start]]];
        const visitedOnPath = new Set([start.id]);
        const dirs = [[-1, 0], [0, 1], [1, 0], [0, -1]];
        while (q.length > 0) {
            const [curr, path] = q.shift();
            if (curr.r === end.r && curr.c === end.c) return { path, distance: path.length - 1 };
            for (const [dr, dc] of dirs) {
                const next = Point(curr.r + dr, curr.c + dc);
                if (next.r >= 0 && next.r < height && next.c >= 0 && next.c < width && inputGrid[next.r][next.c] !== 5 && !visitedOnPath.has(next.id)) {
                    visitedOnPath.add(next.id);
                    q.push([next, [...path, next]]);
                }
            }
        }
        return { distance: Infinity, path: null };
    };

    const dist = Array(n).fill().map(() => Array(n).fill(Infinity));
    const pathSegments = Array(n).fill().map(() => Array(n).fill(null));

    for (let i = 0; i < n; i++) {
        for (let j = i; j < n; j++) {
            if (i === j) { dist[i][j] = 0; continue; }
            const { distance, path } = getPathAndDistance(buildings[i].center, buildings[j].center);
            dist[i][j] = dist[j][i] = distance;
            pathSegments[i][j] = path;
            if(path) pathSegments[j][i] = [...path].reverse();
        }
    }

    // 3. Iterative Greedy Search with Backtracking
    const tour = [0];
    const visitedTour = new Array(n).fill(false);
    visitedTour[0] = true;
    const blacklist = Array(n).fill(0).map(() => new Set());

    while (tour.length < n) {
        const currIdx = tour[tour.length - 1];
        const candidates = [];
        for (let j = 0; j < n; j++) {
            if (!visitedTour[j] && !blacklist[currIdx].has(j) && dist[currIdx][j] !== Infinity) {
                candidates.push(j);
            }
        }

        if (candidates.length > 0) {
            candidates.sort((a, b) => dist[currIdx][a] - dist[currIdx][b]);
            const nextIdx = candidates[0];
            tour.push(nextIdx);
            visitedTour[nextIdx] = true;
        } else {
            if (tour.length <= 1) return inputGrid; // Unsolvable
            const failedNode = tour.pop();
            visitedTour[failedNode] = false;
            blacklist[failedNode] = new Set();
            const prevNode = tour[tour.length - 1];
            blacklist[prevNode].add(failedNode);
        }
    }

    // 4. Draw the final path
    const outputGrid = inputGrid.map(row => [...row]);
    for (let i = 0; i < tour.length - 1; i++) {
        const path = pathSegments[tour[i]][tour[i + 1]];
        if (path) {
            for (const point of path) {
                if (outputGrid[point.r][point.c] === 0) {
                    outputGrid[point.r][point.c] = 3;
                }
            }
        }
    }

    return outputGrid;
}
