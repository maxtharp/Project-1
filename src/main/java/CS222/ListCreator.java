package CS222;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.List;

public class ListCreator {
    private static List<String> dateUserList(String jsonData) {
        List<String> dates = JsonPath.read(jsonData, "$..timestamp");
        List<String> users = JsonPath.read(jsonData, "$..user");
        List<String> list = new ArrayList<>();

        for (int i = 0; i <= users.size() - 1; i++) {
            list.add(dates.get(i) + " " + users.get(i));
        }
        return list;
    }
    public void printFormattedList(String jsonData){
        List<String> revisionList = dateUserList(jsonData);
        System.out.println("\nDate/Time:           User:");
        for (int i = 0; i <= revisionList.size() - 1; i++){
            System.out.println(revisionList.get(i));
        }
    }
}

