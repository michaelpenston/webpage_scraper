import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class EmailScraper {
    public static void main(String[] args) throws Exception {
        //Take an input from scanner
        Scanner scanner = new Scanner(System.in);
        String name = "";

        while (scanner.hasNext()) {
            name = scanner.next();
            break;
        }
        scanner.close();

        String address = "https://www.ecs.soton.ac.uk/people/"+name;

        System.out.println(address);
        URL url = new URL(address);
        URLConnection con = url.openConnection();

        InputStream content = con.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(content);
        BufferedReader reader = new BufferedReader(streamReader);

        String[] lines = reader.lines().toArray(String[]::new);

        Interpreter i = new Interpreter();
        String[] namesFound = i.findName(lines);

        for (String eachName : namesFound) {
            System.out.println(eachName.substring(16, eachName.length()-1));
        }

        content.close();

    }
}
