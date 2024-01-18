public class ArtificialPlayer extends Player{
    public ArtificialPlayer(int n, String name) {
        super(n, name);
    }

    public Integer get_number(){
        return (int) (Math.random() * 9);
    }
}
