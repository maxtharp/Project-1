package CS222;
import com.jayway.jsonpath.JsonPath;

import java.util.List;
import java.util.Scanner;

public class Input {
    public String WikiName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the name of a Wikipedia page: ");
        return sc.nextLine();
    }

    public void checkIsEmpty(String wikiName){
        if (wikiName.isEmpty()){
            System.err.println("Please enter a valid article name.");
            System.exit(0);
        }
    }

    public void checkIsValid(String jsonData){
        List<String> hasRevisions = JsonPath.read(jsonData, "$..user");
        System.out.println(hasRevisions);
        if (hasRevisions.isEmpty()) {
            System.err.println("There is no wikipedia article that matches your search.");
            System.exit(0);
        }
    }

    public void findRedirect(String jsonData, String wikiName) {
        List<String> redirectID = JsonPath.read(jsonData, "$..redirects..to");
        if (redirectID.isEmpty()) {
            System.out.println("\nYou searched: " + wikiName);
        } else {
            System.out.println("\nYour search was redirected to: " + redirectID.get(0));
        }
    }
}
