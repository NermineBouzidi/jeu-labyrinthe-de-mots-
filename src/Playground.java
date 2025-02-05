import java.awt.*;
import java.io.IOException;

import static java.lang.Math.random;

public class Playground {

    int nodenum = 16 * 12; //this will calculate the node number this will define the exact number of nodes

    static Node[] nodes ; //this will generate the links to all nodes

    FileHandler file = new FileHandler() ;

    Playground()
    {
        nodes = new Node[nodenum] ;
    }

    //here we are going to generate grid without graph
    public void generategrid()
    {
        int x = 0 ;//start of x
        int y = 0 ;//start of y
        int dx = 48 ;//gap between each node on x
        int dy = 48 ;//gap between each node on y
        boolean b ;


        //here i will tell each node what place will take in panel 
        //and other data
        for (int i = 0; i < nodenum ; i++) {
            nodes[i] = new Node() ;
            nodes[i].setNum(i);
            b = random() < 0.5 ;
            if(b)
            {
                if(i - 16 > 0)
                {
                    if(nodes[i-16].isWall())
                    {
                        b = false ;
                    }
                }
            }
            nodes[i].setWall(b) ;
            nodes[i].setX(x);
            nodes[i].setY(y);

            x+=dx ;
            if(x >= 48 * 16)
            {
                x = 0 ;
                y+=dy ;
            }

        }

        //this fonction will generate from nodes a graph
        generategraph() ;

        givechar() ;
    }

    private void generategraph()
    {
        //i will tell each node what node should have a relation with
        Node newnode ;
        Node q ;
        for (int i = 0; i < nodenum; i++) {
            //i didn't figure how to make this in loop so i wrote it 8 times with diffenrent conditions
            //these conditions identify wich one of the 8 naborhood should visite and put them into a linked liste
            q = nodes[i] ;
            if(!q.isWall())
            {
                if ((i+1) >= 0 && (i+1) < nodenum && !(i % 16 == 15)&& !(nodes[i+1].isWall()))
                {
                    newnode = new Node(nodes[i+1].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i-1) >= 0 && (i-1) < nodenum && !(i % 16 == 0)&& !(nodes[i-1].isWall()))
                {
                    newnode = new Node(nodes[i-1].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i-16) >= 0 && (i-16) < nodenum && !(nodes[i-16].isWall()))
                {
                    newnode = new Node(nodes[i-16].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i+16) >= 0 && (i+16) < nodenum && !(nodes[i+16].isWall()))
                {
                    newnode = new Node(nodes[i+16].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i-15) >= 0 && (i-15) < nodenum && i % 16 != 15 && !(nodes[i-15].isWall()))
                {
                    newnode = new Node(nodes[i-15].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i+15) >= 0 && (i+15) < nodenum && i % 16 != 0 && !(nodes[i+15].isWall()))
                {
                    newnode = new Node(nodes[i+15].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i-17) >= 0 && (i-17) < nodenum && i % 16 != 0 && !(nodes[i-17].isWall()))
                {
                    newnode = new Node(nodes[i-17].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i+17) >= 0 && ((i+17) < nodenum) && (i % 16 != 15) && !(nodes[i+17].isWall()))
                {
                    newnode = new Node(nodes[i+17].getNum()) ;
                    q.addNode(newnode);
                }
            }
        }
    }

    private void givechartoothers(Node node, int i, String s)
    {
        if (i < s.length() && node != null)
        {
            if (nodes[node.getNum()].getC() == '?')
            {
                nodes[node.getNum()].setC(s.charAt(i)) ;
                node = nodes[node.getNum()] ;
                givechartoothers(node.nextNode(), i+1, s);
            }else
            {
                givechartoothers(node.nextNode(), i, s);
            }
        }
    }

    private void givechar() {
        file.beginfromtop();
        file.getfromfile();

        for (Node node : nodes) {
            if (!node.isWall() && (node.getC() == '?' || node.getC() == '\0')) {
                if (!FileHandler.data.isEmpty()) {
                    givechartoothers(node, 0, FileHandler.data);
                    file.getfromfile();
                }
            }

        }

        file.closeFile(); // Properly close the BufferedReader
    }


    public void showgraph()
    {
        for(Node node : nodes)
        {
            Node q = node ;
            while (q != null) {
                System.out.print("node"+q.getNum()+ "\t");
                q = q.nextNode() ;
            }
            System.out.println("\n");
        }
    }

    public void shownodes(Graphics2D g2)
    {
        /*for (Node node : nodes) {
            System.out.println(node.getX());
            System.out.println(node.getY());
            System.out.println();
        }*/


        for(int i = 0; i < nodenum; i++)
        {
            if(nodes[i].isWall())
            {
                g2.setColor(Color.black);
            }else
            {
                g2.setColor(Color.white);
            }

            g2.fillRect(nodes[i].getX()+2, nodes[i].getY()+2, 48-4, 48-4);
            g2.setColor(Color.black);
            if(!nodes[i].isWall())
                g2.drawString(nodes[i].getC()+"\0", nodes[i].getX()+20, nodes[i].getY()+20);
        }

    }

}
