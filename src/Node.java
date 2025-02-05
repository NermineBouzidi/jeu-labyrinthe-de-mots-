import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Node {

    private char c ;
    private int num ;
    private int x,y ;
    private boolean wall ;
    private Node next ;

    Node()
    {
        this.x = 0 ;
        this.y = 0 ;
        this.num = 0 ;
        this.wall = false ;
        this.c = '?' ;
        this.next = null ;
    }

    public Node(int num) {
        this.num = num;
        this.next = null ;
    }

    void addNode(Node n)
    {
        this.next = n;
    }
    Node nextNode()
    {
        return this.next ;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }
}

