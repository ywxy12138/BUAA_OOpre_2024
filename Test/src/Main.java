import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
       Iterator<String> iterator = list.iterator();
       while(iterator.hasNext()) {
           String s = iterator.next();
           if ("B".equals(s)) {
               list.remove(s);
           }
       }
        System.out.println(list.contains("B"));
    }
}