package fr.irek.games.gamelaunch.player;

public class ArtificialPlayer extends Player {
    public ArtificialPlayer(int n, String name) {
        super(n, name);
    }

    @Override
    public int[] get_number(){
        return new int[]{(int) (Math.random() * 9)};
    }
}
