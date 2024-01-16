import java.lang.reflect.Array;

public class TicTacToe {
    static int size = 6;
    static Cell[][] board_cells = new Cell[size][size];

    public TicTacToe() {
        for (int i = 0; i < board_cells.length; i++) {
            for (int j = 0; j < board_cells[i].length; j++) {
                board_cells[i][j] = new Cell();
                board_cells[i][j].status = (int) (Math.random() * 3);
                System.out.print(board_cells[i][j].status);
            }
            System.out.println(" ");
        }
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
}
