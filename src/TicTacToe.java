import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class TicTacToe {
    static int size = 3;
    static Cell[][] board_cells = new Cell[size][size];
    static Player[] players = new Player[2];
    static Colors_Terminal c_term = new Colors_Terminal();

    public TicTacToe() {
        game_initialization();
        display();
    }

    static void show_line(){
        for (int i = 0; i < size ; i++) {
            System.out.print("----");
        }
        System.out.println("-");
    }
    static void display(){
        show_line();
        for (int i = 0; i < board_cells.length; i++) {
            for (int j = 0; j < board_cells[i].length; j++) {
                board_cells[i][j].getRepresentation(j+i*size +1);
            }
            System.out.println("|");
            show_line();
        }
        System.out.println();
    }

    static void game_initialization(){
        int start = 0;
        for (int i = 0; i < players.length ; i++) {


            String[] user = scanner_user();
            if (parseInt(user[1])==1){
                players[i] = new HumanPlayer(start, user[0]);
            }else {
                players[i] = new ArtificialPlayer(start, user[0]);
            }
            if (start == 0) {
                System.out.println("The player "+ Colors_Terminal.YELLOW + players[0].getName() + Colors_Terminal.RESET + " plays as " + Colors_Terminal.YELLOW + "O" + Colors_Terminal.RESET);
                System.out.println(" ");
                System.out.println("Now give me the second one");
                start = 1;
            }else {
                System.out.println("The player " + Colors_Terminal.BLUE + players[1].getName() + Colors_Terminal.RESET + " plays as " + Colors_Terminal.BLUE + "X" + Colors_Terminal.RESET);
            }
        }

        for (int i = 0; i < board_cells.length; i++) {
            for (int j = 0; j < board_cells[i].length; j++) {
                board_cells[i][j] = new Cell();
            }
        }
    }

    static String[] scanner_user(){
        String[] user = new String[2];
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the player's name");
        user[0] = myObj.nextLine();  // Read user input
        while (true){
            System.out.println("Player (1) or AI(2)?");
            int user_or_ai = 0;
            Scanner myInt = new Scanner(System.in);
            try {
                user_or_ai = myInt.nextInt();  // Read user input
            }catch (Exception e){
                System.out.println("Please re-enter your choice, this time number...");
            }
            if(user_or_ai == 1 || user_or_ai == 2){
                user[1] = Integer.toString(user_or_ai);
                break;
            }
        }
        System.out.println("Username is: " + user[0]);  // Output user input
        return user;
    }

    static void setOwner(Player player){
        int[] pos = getMoveFromPlayer(player);
        board_cells[pos[0]][pos[1]].setStatus(player.getRepresentation());
    }

    static int[] getMoveFromPlayer(Player player){
        int pos_x = 0;
        int pos_y = 0;
        Integer pos_a; // unified position
        int[] pos = new int[2];

        while (true) {

            pos_a = player.get_number();
            if (pos_a!=null && pos_a<=0 && pos_a>=size*size && !player.is_Im_ai()) {
                System.out.println("Please re-enter your choice, position off the grid");
                continue;
            } else if (pos_a == null || (pos_a < 0 && pos_a >= size * size && player.is_Im_ai())) {
                continue;
            }

            pos_x = pos_a % size;
            pos_y = pos_a / size;
            if (board_cells[pos_y][pos_x].getStatus()==0 ){
               break;
            }
            if (player.is_Im_ai()){
                System.out.println("This field is already occupied");
            }

        }
        pos[0] = pos_y;
        pos[1] = pos_x;
        return pos;
    }

    static void play(){
        int t = 0;
        for (int i = 0; i < size*size; i++) {
            setOwner(players[t]);
            display();
            if (did_i_win()){
                if (players[t].getRepresentation()==1){
                    System.out.println("Player " + Colors_Terminal.YELLOW + players[0].getName() + Colors_Terminal.RESET +" win!");
                } else if (players[t].getRepresentation()==2) {
                    System.out.println("Player " + Colors_Terminal.BLUE + players[1].getName() + Colors_Terminal.RESET +" win!");
                }
                return;
            }
            t = t == 0 ? 1 : 0;
        }
        System.out.println("Draw!");

    }
    static boolean did_i_win(){ // to camelcase
        for (int i = 0; i<size; i++){
            for (int j = 0; j < size; j++) {
                boolean test_diagonal =
                    (j<size-2 && i<size-2) &&(
                        (board_cells[i][j].getStatus() > 0
                        && board_cells[i][j].getStatus() == board_cells[j+1][j+1].getStatus()
                        && board_cells[i][j].getStatus() == board_cells[j+2][j+2].getStatus())
                        ||
                        (board_cells[i][j+2].getStatus() > 0
                        && board_cells[i][j+2].getStatus() == board_cells[i+1][j+1].getStatus()
                        && board_cells[i][j+2].getStatus() == board_cells[i+2][j].getStatus())
                    );

                boolean test_vertical = i<size-2 && board_cells[i][j].getStatus() > 0
                        && board_cells[i][j].getStatus() == board_cells[i+1][j].getStatus()
                        && board_cells[i][j].getStatus() == board_cells[i+2][j].getStatus();

                boolean test_horizontal = j<size-2 && board_cells[i][j].getStatus() > 0
                        && board_cells[i][j].getStatus() == board_cells[i][j+1].getStatus()
                        && board_cells[i][j].getStatus() == board_cells[i][j+2].getStatus();

                if (test_diagonal || test_horizontal || test_vertical){
                    return true;
                }

//                if (j<size-3 && i<size-3){
//                    if ((board_cells[i][j].getStatus() > 0
//                        && board_cells[i][j].getStatus() == board_cells[j+1][j+1].getStatus()
//                        && board_cells[i][j].getStatus() == board_cells[j+3][j+3].getStatus()
//                        && board_cells[i][j].getStatus() == board_cells[j+2][j+2].getStatus())
//                        ||
//                        (board_cells[i][j+3].getStatus() > 0
//                        && board_cells[i][j+3].getStatus() == board_cells[i+1][j+2].getStatus()
//                        && board_cells[i][j+3].getStatus() == board_cells[i+2][j+1].getStatus()
//                        && board_cells[i][j+3].getStatus() == board_cells[i+3][j].getStatus())){
//                        return true;
//                    }
//                }
//                if (i<size-3 && board_cells[i][j].getStatus() > 0
//                    && board_cells[i][j].getStatus() == board_cells[i+1][j].getStatus()
//                    && board_cells[i][j].getStatus() == board_cells[i+3][j].getStatus()
//                    && board_cells[i][j].getStatus() == board_cells[i+2][j].getStatus()){
//                    return true;
//                }
//
//                if (j<size-3&& board_cells[i][j].getStatus() > 0
//                    && board_cells[i][j].getStatus() == board_cells[i][j+1].getStatus()
//                    && board_cells[i][j].getStatus() == board_cells[i][j+3].getStatus()
//                    && board_cells[i][j].getStatus() == board_cells[i][j+2].getStatus()){
//                    return true;
//                }
            }
        }
        return false;
    }
}
