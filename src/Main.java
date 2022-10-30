import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] params) {
      // System.out.println(FuncUtils.logic("true true false false","AND"));
        Tree root = new Tree(new Model("Буханка",2790,11),
                        new Tree(new Model("Головастик",3070,2),
                                new Tree(new Model("Бортовой",2670,2), null,
                                        new Tree(new Model("Буханка",2800,11))),null),
                        new Tree(new Model("Патриот",2670,5),
                                new Tree(new Model("Хантер",2520,5), new Tree(new Model("TEST",200,2)), null),
                                new Tree(new Model("Буханка",2800,11), new Tree(new Model("Буханка",4800,11)),
                                        new Tree(new Model("Буханка",2800,11)))));
        wide(root);


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
    public static void wide(Tree root) {
        Stack<Tree> stack = new Stack<>();
        stack.push(root);
        List<String> auto = Arrays.asList("Буханка","Головастик","Бортовой","Патриот","Хантер");

        while (!stack.isEmpty()) {
            Tree node = stack.pop();
            ArrayList<Data> items = new ArrayList<>();
            items.add(new Data(1, "I",  node.model.getName() + " ТС котегории В:" , 0));
            items.add(new Data(12, "I",  node.model.getName() + " грузовик: " , 0));
            items.add(new Data(17, "I",  node.model.getName() + " автобус: " , 0));

            items.add(new Data(18, "L","AND" , 17));
            items.add(new Data(19, "P",">", 18));
            items.add(new Data(20, "V",String.valueOf(node.model.getCount()), 19));
            items.add(new Data(21,"C", "12", 19));

            items.add(new Data(13, "L","AND" , 12));
            items.add(new Data(14, "P","<", 13));
            items.add(new Data(15, "V",String.valueOf( node.model.getWeight()), 14));
            items.add(new Data(16,"C", "3500", 14));
            items.add(new Data(14, "P","<", 13));
            items.add(new Data(15, "V",String.valueOf(node.model.getCount()), 14));
            items.add(new Data(16,"C", "3", 14));

            items.add(new Data(8, "V",String.valueOf( node.model.getWeight()), 4));
            items.add(new Data(2, "L","AND", 1));
            items.add(new Data(3, "P","equals", 2));
            items.add(new Data(6,"V",  node.model.getName(), 3));
            items.add(new Data(7,"C", auto.toString(), 3));
            items.add(new Data(4, "P","<", 2));
            items.add(new Data(8, "V",String.valueOf( node.model.getWeight()), 4));
            items.add(new Data(9,"C", "3500", 4));
            items.add(new Data(5, "P","<", 2));
            items.add(new Data(10, "V",String.valueOf( node.model.getCount()), 5));
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
            System.out.println(tree.Solution(tree.postOrder()));

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
    static class Tree {
        Model model;
        Tree left;
        Tree right;

        public Tree(Model model, Tree left, Tree right) {
            this.model = model;
            this.left = left;
            this.right = right;
        }

        public Tree(Model model) {
            this.model = model;
        }
    }
}