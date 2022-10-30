import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] params) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input : ");
        String enteredValue = in.nextLine();
        //String enteredValue = "Saab";

        ArrayList<Model> models = new ArrayList<>();
        models.add(new Model("Saab",1200,4));
        models.add(new Model("Volvo XC90",1500,7));
        models.add(new Model("Lada Largus",1200,2));
        models.add(new Model("Jeep",2100,4));

        List<Model> temp = models.stream().filter(model -> model.getName().equals(enteredValue)).toList();
        Model enterValue = new Model((temp.isEmpty())?new Model(enteredValue):temp.get(0));

        ArrayList<Data> items = new ArrayList<>();

        items.add(new Data(1, "I",  enteredValue + " ТС котегории В:" , 0));
        items.add(new Data(12, "I",  enteredValue + " фургон: " , 0));
        items.add(new Data(17, "I",  enteredValue + " внедарожник: " , 0));
        items.add(new Data(100, "I",  enteredValue + " семейный(7-8м.): " , 0));

        items.add(new Data(101, "L","AND" , 100));
        items.add(new Data(102, "P",">", 101));
        items.add(new Data(103, "V",String.valueOf(enterValue.getCount()), 102));
        items.add(new Data(104,"C", "6", 102));
        items.add(new Data(105, "P","<", 101));
        items.add(new Data(106, "V",String.valueOf(enterValue.getCount()), 105));
        items.add(new Data(107,"C", "9", 105));

        items.add(new Data(50, "L","AND" , 12));
        items.add(new Data(54, "P","<", 50));
        items.add(new Data(55, "V",String.valueOf(enterValue.getCount()), 54));
        items.add(new Data(56,"C", "3", 54));

        items.add(new Data(18, "L","AND" , 17));
        items.add(new Data(19, "P",">", 18));
        items.add(new Data(20, "V",String.valueOf(enterValue.getWeight()), 19));
        items.add(new Data(21,"C", "2000", 19));

        items.add(new Data(2, "L","AND", 1));
        items.add(new Data(3, "P","equals", 2));
        items.add(new Data(6,"V",  enteredValue, 3));
        items.add(new Data(7,"C", models.toString(), 3));
        items.add(new Data(4, "P","<", 2));
        items.add(new Data(8, "V",String.valueOf( enterValue.getWeight()), 4));
        items.add(new Data(9,"C", "3500", 4));
        items.add(new Data(5, "P","<", 2));
        items.add(new Data(10, "V",String.valueOf( enterValue.getCount()), 5));
        items.add(new Data(11,"C", "9", 5));
        DataNode tree = DataNode.makeTree(items, new TreeNode.TypeAdapter<Data, DataNode>() {
            @Override
            public DataNode newInstance() {
                return new DataNode();
            }

            @Override
            public boolean isChildOf(Data parentNodeData, Data childNodeData) {
                return parentNodeData.id == childNodeData.parentId;
            }
            @Override
            public boolean isTopLevelItem(Data data) {
                return data.parentId == 0;
            }

        });

        Stack<String> output = tree.Solution(tree.postOrder());
        output.add(enterValue.toString());
        System.out.println(output);
    }
    static class DataNode extends TreeNode<Data, DataNode>  {
        @Override
        public String toString() {
            return data.type + " " + data.value;
        }

    }
    public static class Data {
         private int id;
         private String type;
         private String value;
         private int parentId;

        public Data(int id, String type,String value, int parentId) {
            this.id = id;
            this.type = type;
            this.value = value;
            this.parentId = parentId;
        }

        public Data(Data data) {
            this.type = data.getType();
            this.value = data.getValue();
        }

        public String getType() {
            return type;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return type + " " + value;
        }
    }
}