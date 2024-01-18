import java.util.Scanner;

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
            String name = scanner_user();
            players[i] = new ArtificialPlayer(start, name);
            if (start == 0) {
                System.out.println("The player "+ c_term.YELLOW + players[0].getName() + c_term.RESET + " plays as " + c_term.YELLOW + "O" + c_term.RESET);
                System.out.println(" ");
                System.out.println("Now give me the second one");
                start = 1;
            }else {
                System.out.println("The player " + c_term.BLUE + players[1].getName() + c_term.RESET + " plays as " + c_term.BLUE + "X" + c_term.RESET);
            }
        }

        for (int i = 0; i < board_cells.length; i++) {
            for (int j = 0; j < board_cells[i].length; j++) {
                board_cells[i][j] = new Cell();
            }
        }
    }

    static String scanner_user(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the player's username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);  // Output user input
        return userName;
    }

    static void setOwner(Player player){
        int[] pos = getMoveFromPlayer(player);
        board_cells[pos[0]][pos[1]].setStatus(player.getRepresentation());
    }

    static int[] getMoveFromPlayer(Player player){
        int pos_x = 0;
        int pos_y = 0;
        int pos_a; // unified position
        int[] pos = new int[2];

//        boolean repeat = true;
        while (true) {

//            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//
////            System.out.println("Enter pos x:");
////            pos_x = myObj.nextInt();  // Read user input
////            System.out.println("Enter pos y:");
////            pos_y = myObj.nextInt();  // Read user input
//
//            if(player.getRepresentation()==1){
//                System.out.print(c_term.YELLOW + player.getName() + c_term.RESET);
//            }else {
//                System.out.print(c_term.BLUE + player.getName() + c_term.RESET);
//            }
//            System.out.println(" enter the grid number:");
//            try {
//                pos_a = myObj.nextInt() -1 ;  // Read user input
//            }catch (Exception e){
//                System.out.println("Please re-enter your choice, this time number...");
//                continue;
//            }
//

            pos_a = player.get_number();
            System.out.println(pos_a);
            if (pos_a<0 && pos_a>=size*size && !player.is_Im_ai()) {
                System.out.println("Please re-enter your choice, position off the grid");
                continue;
            } else if (pos_a<0 && pos_a>=size*size && player.is_Im_ai()) {
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
                System.out.println("Player " + players[t].getName() +" win!");
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
