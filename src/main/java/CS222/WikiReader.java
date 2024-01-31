package CS222;
import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

public class WikiReader {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (!connectionStatus()){
            System.err.print("Could not connect to Wikipedia.");
        }

        Input input = new Input();
        ListCreator listCreator = new ListCreator();
        String userSearch = input.WikiName();
        URLConnection connection = connectToWikipedia(userSearch);
        String jsonData = readJsonAsStringFrom(connection);

        //begin output
        input.checkIsValid(jsonData);
        input.checkIsEmpty(userSearch);
        input.findRedirect(jsonData, userSearch);
        listCreator.printFormattedList(jsonData);
    }
    private static URLConnection connectToWikipedia(String wikiName) throws IOException {
        String encodedUrlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(wikiName, Charset.defaultCharset()) +
                "&rvprop=timestamp|user&rvlimit=14&redirects";
        URL url = new URL(encodedUrlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (maxwell.tharp@bsu.edu)");
        connection.connect();
        return connection;
    }
    private static boolean connectionStatus() throws IOException, InterruptedException {
        Process process = java.lang.Runtime.getRuntime().exec("ping en.wikipedia.org");
        int checkTermination = process.waitFor();
        return checkTermination == 0;
    }

    private static String readJsonAsStringFrom(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }

    private static void printRawJson(String jsonData) {
        System.out.println(jsonData);
    }
}
