import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

 class Node {
    int row, col;
    char value;
    Node(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return row == node.row && col == node.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
/*class Node {
    int x, y;
    char value;
    List<Node> neighbors;

    Node(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }
}*/