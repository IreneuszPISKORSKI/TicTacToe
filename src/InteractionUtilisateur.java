import java.util.Scanner;

public class InteractionUtilisateur {
    private View view = new View();
    public String[] scanner_user(){
        String[] user = new String[2];
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        view.enter_name();
        user[0] = myObj.nextLine();  // Read user input
        while (true){
            view.player_or_ai();
            int user_or_ai = 0;
            Scanner myInt = new Scanner(System.in);
            try {
                user_or_ai = myInt.nextInt();  // Read user input
            }catch (Exception e){
                view.exception_re_enter_number();
            }
            if(user_or_ai == 1 || user_or_ai == 2){
                user[1] = Integer.toString(user_or_ai);
                break;
            }
        }
        return user;
    }

    public Integer human_choose(Player player){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Integer pos_a = null;

        view.now_its_your_turn(player);
        try {
            pos_a = myObj.nextInt() -1 ;  // Read user input
        }catch (Exception e){
            view.exception_re_enter_number();
        }
        return pos_a;
    }
}
