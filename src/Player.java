public class Player {
    private int representation;

    private String name;
    public Player(int n, String name){
        this.name = name;
        if (n==0){
            representation = 1;
        }else {
            representation = 2;
        }
    }
    public int getRepresentation() {
        return representation;
    }

    public String getName() {
        return name;
    }
}
