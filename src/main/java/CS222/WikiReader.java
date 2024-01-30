package CS222;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

public class WikiReader {
    public static void main(String[] args) throws IOException {
        UserInput userInput = new UserInput();
        URLConnection connection = connectToWikipedia(userInput.WikiName());
        String jsonData = readJsonAsStringFrom(connection);

        ListCreator listCreator = new ListCreator();
        List<String> revisionList;
        revisionList = listCreator.revisionList(jsonData);
        List<String> findRedirect = listCreator.findRedirect(jsonData);


        System.out.println("Date/Time:           User:");
        for (int i = 0; i <= revisionList.size() - 1; i++){
            System.out.println(revisionList.get(i));
        }
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

    private static String readJsonAsStringFrom(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }

    private static void printRawJson(String jsonData) {
        System.out.println(jsonData);
    }
}
