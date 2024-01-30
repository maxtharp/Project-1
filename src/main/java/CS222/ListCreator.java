package CS222;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.List;

public class ListCreator {
    private static List<String> revisionListUsers(String jsonData){
        return JsonPath.read(jsonData, "$..user");
    }

    private static List<String> revisionListDates(String jsonData){
        return JsonPath.read(jsonData, "$..timestamp");
    }

    public List<String> revisionList(String jsonData){
        List<String> users = revisionListUsers(jsonData);
        List<String> dates = revisionListDates(jsonData);
        List<String> list = new ArrayList<>();

        for (int i = 0; i <= users.size() - 1; i++){
            list.add(dates.get(i) + " " + users.get(i));
        }
        return list;
    }

    public List<String> findRedirect(String jsonData){
        return JsonPath.read(jsonData, "$..redirects");
    }

    public boolean checkNoRedirection(List<String> findRedirect){
        return (findRedirect.isEmpty());
        }
    }

