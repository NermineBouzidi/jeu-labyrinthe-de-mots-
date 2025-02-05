import java.io.*;

public class FileHandler {
    public static String data;
    private BufferedReader reader;

    public FileHandler() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Assets/random_words.txt");
            if (is == null) {
                throw new FileNotFoundException("File not found: Assets/random_words.txt");
            }
            reader = new BufferedReader(new InputStreamReader(is));
            FileHandler.data = "";
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error opening file");
        }
    }


    public void beginfromtop() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Assets/random_words.txt");
            if (is == null) {
                throw new FileNotFoundException("File not found: Assets/random_words.txt");
            }
            reader = new BufferedReader(new InputStreamReader(is));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void getfromfile() {
        try {
            String line = reader.readLine();
            if (line != null) {
                FileHandler.data = line.trim();
            } else {
                FileHandler.data = ""; // Set empty if EOF
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void closeFile() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
