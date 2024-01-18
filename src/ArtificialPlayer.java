public class ArtificialPlayer extends Player{
    public ArtificialPlayer(int n, String name) {
        super(n, name);
    }

    public int get_number(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return (int) (Math.random() * 10);
    }
}
