import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(int n, String name) {
        super(n, name);
        set_Im_ai(false);
    }

    public int get_number(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int pos_a = -55;

        if(getRepresentation()==1){
            System.out.print(c_term.YELLOW + getName() + c_term.RESET);
        }else {
            System.out.print(c_term.BLUE + getName() + c_term.RESET);
        }
        System.out.println(" enter the grid number:");
        try {
            pos_a = myObj.nextInt() -1 ;  // Read user input
        }catch (Exception e){
            System.out.println("Please re-enter your choice, this time number...");
        }
        return pos_a;
    }
}
