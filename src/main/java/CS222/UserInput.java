package CS222;
import java.util.Scanner;

public class UserInput {
    public String WikiName (){
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the exact name of a Wikipedia page:\n");
        return sc.nextLine();
    }
}
