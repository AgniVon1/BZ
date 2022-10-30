import java.util.Collection;

public class Model {
    private String name;
    private final int weight;
    private final int count;

    public Model(String name, int weight, int count) {
        this.name = name;
        this.weight = weight;
        this.count = count;
    }
    public Model(Model model) {
        this.name =  model.getName();
        this.weight = model.getWeight();
        this.count = model.getCount();
    }

    public Model(String name) {
        this.name = "<undf>";
        this.weight = 0;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getWeight() {
        return weight;
    }
    public int getCount() {
        return count;
    }


    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", count=" + count +
                '}';
    }
}
