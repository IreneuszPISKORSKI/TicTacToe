package fr.irek.games.tictactoe;

import fr.irek.games.gamelaunch.*;
import fr.irek.games.gamelaunch.player.Player;
import fr.irek.games.gamelaunch.view.View;

public class TicTacToe extends Game {

    public TicTacToe() {
        super(3, 3);
    }
    @Override
    protected int[] getMoveFromPlayer(Player player) {
        int pos_x;
        int pos_y;
        int pos_a; // unified position
        int[] pos = new int[2];

        while (true) {
            pos_a = player.get_number()[0];
            if ((pos_a < 0 || pos_a > sizeX * sizeY) && !player.is_Im_ai()) {
                view.exception_re_enter_position_off_grid();
                continue;
            } else if ((pos_a < 0 || pos_a > sizeX * sizeY) && player.is_Im_ai()) {
                continue;
            }

            pos_x = pos_a % sizeX;
            pos_y = pos_a / sizeY;
            if (board_cells[pos_y][pos_x].getStatus() == 0) {
                break;
            }
            if (player.is_Im_ai()) {
                view.exception_occupied();
            }

        }
        pos[0] = pos_y;
        pos[1] = pos_x;
        return pos;
    }
}
