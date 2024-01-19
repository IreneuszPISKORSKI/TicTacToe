import java.util.Scanner;

public class HumanPlayer extends Player{
    private InteractionUtilisateur intrUtilis = new InteractionUtilisateur();
    public HumanPlayer(int n, String name) {
        super(n, name);
        set_Im_ai(false);
    }

    public Integer get_number(){
        return intrUtilis.human_choose(this);
    }
}
