/*class Graph {
    private final Node[][] grid;
    private final int rows;
    private final int cols;

    Graph(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Node[rows][cols];

        // Initialize nodes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Node(i, j, '-');
            }
        }

        // Add edges (neighbors)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i > 0) grid[i][j].addNeighbor(grid[i - 1][j]); // Up
                if (i < rows - 1) grid[i][j].addNeighbor(grid[i + 1][j]); // Down
                if (j > 0) grid[i][j].addNeighbor(grid[i][j - 1]); // Left
                if (j < cols - 1) grid[i][j].addNeighbor(grid[i][j + 1]); // Right
                if (i > 0 && j > 0) grid[i][j].addNeighbor(grid[i - 1][j - 1]); // Top-left diagonal
                if (i > 0 && j < cols - 1) grid[i][j].addNeighbor(grid[i - 1][j + 1]); // Top-right diagonal
                if (i < rows - 1 && j > 0) grid[i][j].addNeighbor(grid[i + 1][j - 1]); // Bottom-left diagonal
                if (i < rows - 1 && j < cols - 1) grid[i][j].addNeighbor(grid[i + 1][j + 1]); // Bottom-right diagonal
            }
        }
    }

    Node getNode(int x, int y) {
        return grid[x][y];
    }

    void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].value + " ");
            }
            System.out.println();
        }
    }

    boolean canPlaceWord(String word, int startX, int startY, int dx, int dy) {
        int x = startX;
        int y = startY;

        for (char c : word.toCharArray()) {
            if (x < 0 || x >= rows || y < 0 || y >= cols || (grid[x][y].value != '-' && grid[x][y].value != c)) {
                return false;
            }
            x += dx;
            y += dy;
        }
        return true;
    }

    void placeWord(String word, int startX, int startY, int dx, int dy) {
        int x = startX;
        int y = startY;

        for (char c : word.toCharArray()) {
            grid[x][y].value = c;
            x += dx;
            y += dy;
        }
    }
}*/