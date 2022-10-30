import java.util.*;
import java.util.stream.Collectors;

public class FuncUtils {
    public static <X> List<X> filter(List<X> list, Func1<X, Boolean> filterFunction) {
        ArrayList<X> result = new ArrayList<>();
        for(X item: list) {
            if (filterFunction.apply(item)) {
                result.add(item);
            }
        }
        return result;
    }
    public static String predicate(String _const, String value, String _operation){
        String result = null;
        switch (_operation) {
            case "<" -> result = String.valueOf(Integer.parseInt(value) < Integer.parseInt(_const));
            case ">" -> result = String.valueOf(Integer.parseInt(value) > Integer.parseInt(_const));
            case "==" -> result = String.valueOf(value.equals(_const));
            //[Model{name='Saab', weight=2900, count=2}, Model{name='Test', weight=2900, count=2}]
            case "equals" -> {
                result = String.valueOf(_const.contains(value));
            }
        }
        return result;
    }
    public static String logic(String pred, String _operation){
        Stack<Boolean> booleansStack = Arrays.stream(
                                                         pred.split(" "))
                                                                .map(Boolean::parseBoolean)
                                                                    .collect(Collectors.toCollection(Stack::new)
                                                                    );
        if(Objects.equals(_operation, "NOT")){
            return String.valueOf(!booleansStack.pop());
        }
        Boolean first;
        Boolean second;
        while (booleansStack.size() >= 2 ) {
            first = booleansStack.pop();
            second = booleansStack.pop();
            switch (_operation) {
                case "AND" -> booleansStack.push(first && second);
                case "OR" -> booleansStack.push(first || second);
                case "XOR" -> booleansStack.push((first && !second) || (!first && second));// (A AND !B) OR (!A AND B).
            }
        }
        return booleansStack.pop().toString();
    }
}