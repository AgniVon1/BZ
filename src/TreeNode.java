import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeNode<T extends Main.Data, N extends TreeNode<T, N>> {
    T data;
    ArrayList<N> children;
    N parent;

    public TreeNode() {
    }


    public interface TypeAdapter<T, N> {
        N newInstance();
        boolean isChildOf(T parentNodeData, T childNodeData);
        boolean isTopLevelItem(T data);
    }

    public static <T extends Main.Data, N extends TreeNode<T , N>> N makeTree(List<T> datas, TypeAdapter<T, N> typeAdapter) {
        N root = typeAdapter.newInstance();
        root.children = new ArrayList<>();
        for (T top: FuncUtils.filter(datas, typeAdapter::isTopLevelItem)) {
            root.children.add(extractNode(top, root, datas, typeAdapter));
        }
        return root;
    }

    protected static <T extends Main.Data, N extends TreeNode<T, N>> N extractNode(T data, N parent, List<T> datas, TypeAdapter<T, N> typeAdapter) {
        N node = typeAdapter.newInstance();
        node.data = data;
        node.parent = parent;
        List<T> directChildren = FuncUtils.filter(datas, d -> typeAdapter.isChildOf(data, d));
        if (!directChildren.isEmpty()) {
            node.children = new ArrayList<>();
            for (T child: directChildren) {
                node.children.add(extractNode(child, node, datas, typeAdapter));
            }
        }
        return node;
    }

    public Stack<TreeNode<T,N>> postOrder() {
        Stack<TreeNode<T, N>> empty = new Stack<TreeNode<T, N>>();
        Stack<TreeNode<T, N>> output = new Stack<TreeNode<T, N>>();
        empty.push(this);
        TreeNode<T, N> node = new TreeNode<T, N>();
        while (!empty.isEmpty()) {
            node = empty.pop();
            output.push(node);
            if (node.children != null) {
                for (TreeNode<T, N> chillnode : node.children) {
                    empty.push(chillnode);
                }
            }
        }
        return output;
    }

    public Stack<String> Solution(Stack<TreeNode<T,N>> output) {
        Stack<Main.Data> ObjCner = new Stack<>();
        Stack<String> out = new Stack<>();

        while (!output.isEmpty() && output.peek().data != null) {
            Main.Data _data = new Main.Data(output.pop().data);
            switch (_data.getType()) {
                case "C", "V" -> {
                    ObjCner.push(_data);
                    // System.out.println(ObjCner.peek().getType() + " , " + ObjCner.peek().getValue());
                }
                case "P" -> {
                    _data.setValue(FuncUtils.predicate(ObjCner.pop().getValue(), ObjCner.pop().getValue(), _data.getValue()));
                    ObjCner.push(_data);
                     ///System.out.println(ObjCner.peek().getType() + " , " + ObjCner.peek().getValue());
                }
                case "L" -> {
                    StringBuilder buff = new StringBuilder();
                    while (!ObjCner.isEmpty() && ObjCner.peek().getType().equals("P")) {
                        buff.append(ObjCner.pop().getValue()).append(' ');
                    }
                    //System.out.println(buff);
                    _data.setValue(FuncUtils.logic(String.valueOf(buff), _data.getValue()));
                    ObjCner.push(_data);
                    //System.out.println(ObjCner.peek().getType() + " , " + ObjCner.peek().getValue());
                }
                case "I" -> {
                    //System.out.println(_data.getType() + " , "+ _data.getValue() + ObjCner.peek().getValue());
                    out.add(_data.getValue() + ObjCner.pop().getValue());
                }
            }
        }
        return out;
    }
}