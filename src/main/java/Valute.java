import java.util.Arrays;
import java.util.LinkedList;

public class Valute {

    public static LinkedList<String> valutes = new LinkedList<>();

    public static boolean hasValute(String val){

        return valutes.contains(val);
    }
}
