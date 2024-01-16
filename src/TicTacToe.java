import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    static int size = 3;
    static Cell[][] board_cells = new Cell[size][size];
    static Player[] players = new Player[2];
    Colors_Terminal c_term = new Colors_Terminal();

    public TicTacToe() {
        int start = 0;
        for (int i = 0; i < players.length ; i++) {
            String name = scanner_user();
            players[i] = new Player(start, name);
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
//                board_cells[i][j].setStatus( (int) (Math.random() * 3));
            }
        }
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
                board_cells[i][j].getRepresentation();
            }
            System.out.println("|");
            show_line();
        }
        System.out.println();
    }

    static String scanner_user(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the player's username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);  // Output user input
        return userName;
    }

    static void setOwner(int player){
        int[] pos = getMoveFromPlayer();
        board_cells[pos[0]][pos[1]].setStatus(player);
    }

    static int[] getMoveFromPlayer(){
        int pos_x;
        int pos_y;
        int[] pos = new int[2];
        do {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter pos x:");
            pos_x = myObj.nextInt();  // Read user input

            System.out.println("Enter pos y:");
            pos_y = myObj.nextInt();  // Read user input


        }while (board_cells[pos_y][pos_x].getStatus()!=0 && pos_x>=0 && pos_x<size && pos_y>=0 && pos_y<size);
        pos[0]= pos_y;
        pos[1]= pos_x;
        return pos;
    }

    static void play(){
        int t = 0;
        for (int i = 0; i < 9; i++) {
            setOwner(players[t].getRepresentation());
            if (t == 0) {
                t = 1;
            }else {
                t=0;
            }
            display();
        }
    }
}
