let parent: number[];
let componentCount: number;

function find(x: number): number {
    if (parent[x] !== x) {
        parent[x] = find(parent[x]);
    }
    return parent[x];
}

function union(a: number, b: number): boolean {
    const rootA = find(a);
    const rootB = find(b);

    if (rootA !== rootB) {
        parent[rootA] = rootB;
        componentCount--;
        return true;
    }
    return false;
}

function maximumSafenessFactor(grid: number[][]): number {
    const gridSize = grid.length;

    if (grid[0][0] === 1 || grid[gridSize - 1][gridSize - 1] === 1) {
        return 0;
    }

    const bfsQueue: number[][] = [];
    const infinity = 1 << 30;

    const distanceMatrix: number[][] = Array(gridSize)
        .fill(0)
        .map(() => Array(gridSize).fill(infinity));

    for (let row = 0; row < gridSize; ++row) {
        for (let col = 0; col < gridSize; ++col) {
            if (grid[row][col] === 1) {
                distanceMatrix[row][col] = 0;
                bfsQueue.push([row, col]);
            }
        }
    }

    const directions = [-1, 0, 1, 0, -1];

    while (bfsQueue.length > 0) {
        const [currentRow, currentCol] = bfsQueue.shift()!;

        for (let dir = 0; dir < 4; ++dir) {
            const nextRow = currentRow + directions[dir];
            const nextCol = currentCol + directions[dir + 1];

            if (nextRow >= 0 && nextRow < gridSize &&
                nextCol >= 0 && nextCol < gridSize &&
                distanceMatrix[nextRow][nextCol] === infinity) {

                distanceMatrix[nextRow][nextCol] = distanceMatrix[currentRow][currentCol] + 1;
                bfsQueue.push([nextRow, nextCol]);
            }
        }
    }

    const cellsWithSafeness: number[][] = [];
    for (let row = 0; row < gridSize; ++row) {
        for (let col = 0; col < gridSize; ++col) {
            cellsWithSafeness.push([distanceMatrix[row][col], row, col]);
        }
    }

    cellsWithSafeness.sort((a, b) => b[0] - a[0]);

    const totalCells = gridSize * gridSize;
    parent = Array(totalCells)
        .fill(0)
        .map((_, index) => index);
    componentCount = totalCells;

    for (const [safeness, row, col] of cellsWithSafeness) {
        for (let dir = 0; dir < 4; ++dir) {
            const adjacentRow = row + directions[dir];
            const adjacentCol = col + directions[dir + 1];

            if (adjacentRow >= 0 && adjacentRow < gridSize &&
                adjacentCol >= 0 && adjacentCol < gridSize &&
                distanceMatrix[adjacentRow][adjacentCol] >= safeness) {

                union(row * gridSize + col, adjacentRow * gridSize + adjacentCol);
            }
        }

        if (find(0) === find(totalCells - 1)) {
            return safeness;
        }
    }

    return 0;
}