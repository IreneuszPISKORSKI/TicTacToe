package fr.irek.games.gamelaunch.view;

import fr.irek.games.gamelaunch.cell.Cell;
import fr.irek.games.gamelaunch.player.Player;
import fr.irek.games.gamelaunch.view.ColorTerminal;

public class View {

    public void enter_name(){
        System.out.println("Enter name");
    }

    public void player_or_ai(){
        System.out.println("Player (1) or AI (2)?");
    }

    public void show_player_with_color(Player player){
        if (player.getRepresentation()==1){
            System.out.println("Player " + ColorTerminal.YELLOW.getCode() + player.getName() + ColorTerminal.RESET.getCode() +" win!");
        }else {
            System.out.println("Player " + ColorTerminal.BLUE.getCode() + player.getName() + ColorTerminal.RESET.getCode() +" win!");
        }
    }

    public int show_player_names(Player player, int start){
        if (start == 0) {
            System.out.println("The player "+ ColorTerminal.YELLOW.getCode() + player.getName() + ColorTerminal.RESET.getCode() + " plays as " + ColorTerminal.YELLOW.getCode() + "O" + ColorTerminal.RESET.getCode());
            System.out.println(" ");
            System.out.println("Now give me the second one");
        }else {
            System.out.println("The player " + ColorTerminal.BLUE.getCode() + player.getName() + ColorTerminal.RESET.getCode() + " plays as " + ColorTerminal.BLUE.getCode() + "X" + ColorTerminal.RESET.getCode());
        }
        return 1;
    }

    public void display_grid(Cell[][] board_cells, int sizeX, int sizeY){
        show_line(sizeX);
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                board_cells[i][j].getRepresentation(j+i*sizeX +1);
            }
            System.out.println("|");
            show_line(sizeX);
        }
        System.out.println();
    }

    private void show_line(int size){
        for (int i = 0; i < size ; i++) {
            System.out.print("----");
        }
        System.out.println("-");
    }

    public void message_draw(){
        System.out.println("Draw!");
    }

    public void exception_re_enter_number(){
        System.out.println("Please re-enter your choice, this time number...");
    }

    public void exception_re_enter_position_off_grid(){
        System.out.println("Please re-enter your choice, position off the grid");
    }
    public void exception_occupied(){
        System.out.println("This field is already occupied");
    }

    public void cell_representation(int status, int number){
        if (status == 0) {
            System.out.print("| " + number + " ");
        } else if (status == 1) {
            System.out.print("| " + ColorTerminal.YELLOW.getCode() + "O " + ColorTerminal.RESET.getCode());
        } else if (status == 2) {
            System.out.print("| " + ColorTerminal.BLUE.getCode() + "X " + ColorTerminal.RESET.getCode());
        }else {
            System.out.println("Error, player out of bounds");
        }
    }

    public void now_its_your_turn(Player player){
        if(player.getRepresentation()==1){
            System.out.print(ColorTerminal.YELLOW.getCode() + player.getName() + ColorTerminal.RESET.getCode());
        }else {
            System.out.print(ColorTerminal.BLUE.getCode() + player.getName() + ColorTerminal.RESET.getCode());
        }
        System.out.println(" enter the grid number:");
    }
}
