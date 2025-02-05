import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Player {

    Node position ;
    String word = new String() ;
    FileHandler file ;
    boolean collect = false ;
    ArrayList<String> words = new ArrayList() ;



    Player(Playground grid)
    {
        int i = 0 ;
        while (i < grid.nodenum) {
            if(!Playground.nodes[i].isWall() && Playground.nodes[i].nextNode() != null)
            {
                this.position = Playground.nodes[i] ;
                break ;
            }
            i+=16 ;
        }
        this.file = new FileHandler() ;
    }

    public void updateplayer(KeyHandler keys, Node[] nodes)
    {
        int futurepos ;

        if(keys.downpress)
        {
            futurepos = this.position.getNum() + 16 ;
            if (cango(this.position, futurepos)) {
                this.position = nodes[futurepos] ;
                this.docollect();
            }
            keys.downpress = false ;
        }

        if(keys.uppress)
        {
            futurepos = this.position.getNum() - 16 ;
            if (cango(this.position, futurepos)) {
                this.position = nodes[futurepos] ;
                this.docollect();
            }
            keys.uppress = false ;
        }

        if(keys.leftpress)
        {
            futurepos = this.position.getNum() - 1 ;
            if (cango(this.position, futurepos)) {
                this.position = nodes[futurepos] ;
                this.docollect();
            }
            keys.leftpress = false ;
        }

        if(keys.rightpress)
        {
            futurepos = this.position.getNum() + 1 ;
            if (cango(this.position, futurepos)) {
                this.position = nodes[futurepos] ;
                this.docollect();
            }
            keys.rightpress = false ;
        }
        if(keys.uprightpress)
        {
            futurepos = this.position.getNum() - 15 ;
            if (cango(this.position, futurepos)) {
                this.position = nodes[futurepos] ;
                this.docollect();
            }
            keys.uprightpress = false ;
        }
        if(keys.upleftpress)
        {
            futurepos = this.position.getNum() - 17 ;
            if (cango(this.position, futurepos)) {
                this.position = nodes[futurepos] ;
                this.docollect();
            }
            keys.upleftpress = false ;
        }
        if(keys.downrightpress)
        {
            futurepos = this.position.getNum() + 17 ;
            if (cango(this.position, futurepos)) {
                this.position = nodes[futurepos] ;
                this.docollect();
            }
            keys.downrightpress = false ;
        }
        if(keys.downleftpress)
        {
            futurepos = this.position.getNum() + 15 ;
            if (cango(this.position, futurepos)) {
                this.position = nodes[futurepos] ;
                this.docollect();
            }
            keys.downleftpress = false ;
        }
        if(keys.collectpress)
        {
            keys.collectpress = false ;

            this.collect = !this.collect;

            if(this.word.length() == 0)
                this.word += this.position.getC() ;

            if(!this.collect)
            {
                System.out.println(submit()) ;
                System.out.println(this.words);
            }
            System.out.println(this.word);

        }

    }

    public void docollect()
    {
        if (this.collect)
        {
            this.word += this.position.getC() ;
            System.out.println(this.word);
        }
    }



    public byte submit()//this function will submit the collected word to the file to see if it is in the dictionary
    {
        file.beginfromtop();

        file.getfromfile();

        while(FileHandler.data != null)
        {
            if(FileHandler.data.equals(this.word))
            {
                if(this.words.isEmpty())
                {
                    this.words.add(word) ;
                }else if (this.words.indexOf(word) == -1)
                {
                    this.words.add(word) ;
                }else
                {
                    this.word = "" ;
                    System.out.println("this word is already exist in your collected words");
                    return 2 ; // 2 if the word exists in collected words
                }

                this.word = "" ;
                return 1;// 1 if the word exists in file (succes)
            }
            file.getfromfile();
        }
        this.word = "" ;
        return 0 ;// 0 if the word not exists in file
    }



    boolean cango(Node pos, int i)
    {
        if (pos != null) {
            if(pos.getNum() == i)
                return true ;
        }else{
            return false ;
        }
        return cango(pos.nextNode(), i) ;
    }
    //---------------------Graphics--------------------------------
    public void getPlayerImage(){
       /* try {
            up1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen_walk1-left.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen_walk1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));

        }catch (IOException e){
            e.printStackTrace();
        }*/
    }
    public void drawplayer(Graphics2D g2)
    {
        g2.setColor(Color.RED) ;
        g2.drawRect(this.position.getX()+2, this.position.getY()+2, 48-4, 48-4);
    }
}

