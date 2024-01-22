package fr.irek.games.gamelaunch.player;

import fr.irek.games.gamelaunch.InteractionUtilisateur;

public class HumanPlayer extends Player {
    private final InteractionUtilisateur intrUtilis = new InteractionUtilisateur();
    public HumanPlayer(int n, String name) {
        super(n, name);
        set_Im_ai(false);
    }

    @Override
    public int[] get_number(){
        return new int[]{intrUtilis.human_choose(this)};
    }
}
