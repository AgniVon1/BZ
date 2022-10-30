
public class Model {
    private String name;
    private final int weight;
    private final int count;

    public Model(String name, int weight, int count) {
        this.name = name;
        this.weight = weight;
        this.count = count;
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

}
