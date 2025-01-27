import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class Labyrinth  {
    public static final int NODE_SIZE = 30; // Size of each node (grid cell)
    public static final int LABYRINTH_WIDTH = 15; // Width of the labyrinth (in nodes)
    public static final int LABYRINTH_HEIGHT = 10; // Height of the labyrinth (in nodes)

    public Node[][] labyrinth;
    private Set<Node> walls;
    public StringBuilder currentWord;
    public Set<String> validWords = new HashSet<>();
    private Node startPoint;
    private Node endPoint;
    GamePanel gp;
    KeyHandler keyHandler=new KeyHandler();
    private Map<Node, List<Node>> graph; // Graph representation of labyrinth
    public int score;
    public Player player;
    private BufferedImage undoIcon;
    private BufferedImage hintIcon;
    private BufferedImage scoreIcon;

    private final Rectangle undoButtonBounds;
    private final Rectangle hintButtonBounds;




    public Labyrinth(GamePanel gp) {
        // Initialize the labyrinth and walls
        this.gp=gp;

        // Load icons
        try {
            undoIcon = ImageIO.read(getClass().getResourceAsStream("/assets/Menu/Replay_BTN.png")); // Replace with your undo icon path
            hintIcon = ImageIO.read(getClass().getResourceAsStream("/assets/Menu/Upgrade_BTN.png")); // Replace with your hint icon path
            scoreIcon = ImageIO.read(getClass().getResourceAsStream("/assets/Menu/Score.png")); // Replace with your hint icon path

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Define bounds for buttons
        undoButtonBounds = new Rectangle(gp.getWidth() - 100, 20, 40, 40); // Adjust position/size as needed
        hintButtonBounds = new Rectangle(gp.getWidth() - 100, 20, 40, 40);
        labyrinth = new Node[LABYRINTH_HEIGHT][LABYRINTH_WIDTH];
        graph = new HashMap<>();
        this.walls = new HashSet<>();
        this.currentWord = new StringBuilder();
        this.validWords = new HashSet<>();
        this.score = 0;

        // Generate the labyrinth from a file
        generateLabyrinth("/assets/dictionary.txt");

        // Initialize the start and end points
        startPoint = labyrinth[1][1];
        endPoint = labyrinth[LABYRINTH_HEIGHT - 2][LABYRINTH_WIDTH - 2];
        //playerNode = startPoint;
        buildGraph(); // Build graph for BFS

        // Add KeyListener for player movement
        player = new Player(this.gp,this.keyHandler,this);
        //player.playerNode=startPoint;
        gp.addKeyListener(this.keyHandler);  // Add the KeyHandler to the GamePanel



    }

    private void generateLabyrinth(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            List<String> words = new ArrayList<>();
            String line;

            // Read all words from the file
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String word = line.trim().toUpperCase();
                    words.add(word);
                    validWords.add(word);
                }
            }

            // Fill the labyrinth with characters from words
            Random random = new Random();
            for (int row = 0; row < LABYRINTH_HEIGHT; row++) {
                for (int col = 0; col < LABYRINTH_WIDTH; col++) {
                    if (row == 0 || row == LABYRINTH_HEIGHT - 1 || col == 0 || col == LABYRINTH_WIDTH - 1) {
                        labyrinth[row][col] = new Node(row, col, '#');
                        walls.add(labyrinth[row][col]);
                    } else {
                        String randomWord = words.get(random.nextInt(words.size()));
                        char randomChar = randomWord.charAt(random.nextInt(randomWord.length()));
                        labyrinth[row][col] = new Node(row, col, randomChar);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading words file.");
        }

    }
    private void buildGraph() {
        for (int row = 0; row < LABYRINTH_HEIGHT; row++) {
            for (int col = 0; col < LABYRINTH_WIDTH; col++) {
                Node current = labyrinth[row][col];
                if (current.value != '#') {
                    graph.putIfAbsent(current, new ArrayList<>());

                    for (int[] direction : new int[][]{
                            {-1, 0},   // Up
                            {1, 0},    // Down
                            {0, -1},   // Left
                            {0, 1},    // Right
                            {-1, -1},  // Up-Left (Diagonal)
                            {-1, 1},   // Up-Right (Diagonal)
                            {1, -1},   // Down-Left (Diagonal)
                            {1, 1}     // Down-Right (Diagonal)
                    }){
                        int newRow = row + direction[0];
                        int newCol = col + direction[1];
                        if (isWithinBounds(newRow, newCol) && labyrinth[newRow][newCol].value != '#') {
                            graph.get(current).add(labyrinth[newRow][newCol]);
                        }
                    }
                }
            }
        }

    }
    public boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < LABYRINTH_HEIGHT && col >= 0 && col < LABYRINTH_WIDTH;
    }
    public void drawLabyrinth(Graphics2D g) {
        // Center the labyrinth on the screen

        int offsetX = (gp.getWidth() - (LABYRINTH_WIDTH * NODE_SIZE)) / 2;
        int offsetY = (gp.getHeight() - (LABYRINTH_HEIGHT * NODE_SIZE)) / 2;

        for (int row = 0; row < LABYRINTH_HEIGHT; row++) {
            for (int col = 0; col < LABYRINTH_WIDTH; col++) {
                Node node = labyrinth[row][col];
                int x = offsetX + col * NODE_SIZE;
                int y = offsetY + row * NODE_SIZE;

                if (node.value == '#') {
                    g.setColor(Color.BLACK);
                    //gp.tileM.setTile(g,x,y);
                   // gp.tileM.draw(g);
                    //g.drawImage(ImageIO.read(getClass().getResourceAsStream("/assets/tile_0007.png")));
                } else if (node.equals(startPoint)) {
                    g.setColor(Color.GREEN); // Start point
                } else if (node.equals(endPoint)) {
                    g.setColor(Color.RED); // End point
                } else {
                    g.setColor(node.color);
                }
                g.fillRect(x, y, NODE_SIZE, NODE_SIZE);

                g.setColor(Color.GRAY);
                g.drawRect(x, y, NODE_SIZE, NODE_SIZE);

                if (node.value != '#') {
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(node.value), x + 15, y + 25);
                }
            }
        }

        player.draw(g);

        // Display the current word
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString("Current Word: " + currentWord, 20, 550);
        g.setColor(Color.WHITE);


        // Display the score
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(""+ score, 160, 40);
       g.drawImage(scoreIcon, 20, 10, undoButtonBounds.width*3, 35, null);


        if (undoIcon != null) {
            g.drawImage(undoIcon, 700, 10, undoButtonBounds.width, undoButtonBounds.height, null);
        }

        // Draw Hint button
        if (hintIcon != null) {
            g.drawImage(hintIcon, 650, 10, hintButtonBounds.width, hintButtonBounds.height, null);
        }
    }





    public void finalizeWord() {
        if (validWords.contains(currentWord.toString())) {
            //System.out.println("Word accepted: " + currentWord);
            this.score+=10;
        } else {
            this.score+=0;
            //System.out.println("Word not valid: " + currentWord);
        }
        //currentWord.setLength(0); // Clear the current word
        gp.repaint();
    }



}

