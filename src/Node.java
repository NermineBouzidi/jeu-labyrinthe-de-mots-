import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

 class Node {
    int row, col;
    char value;
    Color color;
     public BufferedImage image;
    Node(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.color=Color.WHITE;
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
     public void getImage(){
         try {
             image= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));


         }catch (IOException e){
             e.printStackTrace();
         }
     }
     public void draw(Graphics2D g2) {
         BufferedImage image = null;
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