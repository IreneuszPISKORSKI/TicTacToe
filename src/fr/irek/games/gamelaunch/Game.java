package fr.irek.games.gamelaunch;

import fr.irek.games.gamelaunch.cell.Cell;
import fr.irek.games.gamelaunch.player.ArtificialPlayer;
import fr.irek.games.gamelaunch.player.HumanPlayer;
import fr.irek.games.gamelaunch.player.Player;
import fr.irek.games.gamelaunch.view.View;

import static java.lang.Integer.parseInt;

public abstract class Game implements GameInterface{
    protected int sizeX;
    protected int sizeY;
    protected Cell[][] board_cells;
    private final Player[] players = new Player[2];
    protected final View view = new View();
    private final InteractionUtilisateur interUtil = new InteractionUtilisateur();

    public Game(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board_cells = new Cell[sizeY][sizeX];
        game_initialization();
        view.display_grid(board_cells, sizeX, sizeY);
    }

    protected void game_initialization() {
        int start = 0;
        for (int i = 0; i < players.length; i++) {

            String[] user = interUtil.scanner_enter_user();
            if (parseInt(user[1]) == 1) {
                players[i] = new HumanPlayer(start, user[0]);
            } else {
                players[i] = new ArtificialPlayer(start, user[0]);
            }
            start = view.show_player_names(players[i], start);
        }

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                this.board_cells[i][j] = new Cell();
            }
        }
    }

    protected void setOwner(Player player) {
        int[] pos = getMoveFromPlayer(player);
        board_cells[pos[0]][pos[1]].setStatus(player.getRepresentation());
    }

    protected int[] getMoveFromPlayer(Player player) {
//        int pos_x;
//        int pos_y;
        int[] pos_a; // unified position

        while (true) {
            pos_a = player.get_number();
            if (((pos_a[0] < 0 || pos_a[0] > sizeX) && !player.is_Im_ai())||((pos_a[1] < 0 || pos_a[1] > sizeY) && !player.is_Im_ai())) {
                view.exception_re_enter_position_off_grid();
                continue;
            } else if (((pos_a[0] < 0 || pos_a[0] > sizeX)||(pos_a[1] < 0 || pos_a[1] > sizeX)) && player.is_Im_ai()) {
                continue;
            }

//            pos_x = pos_a % size;
//            pos_y = pos_a / size;
            if (board_cells[pos_a[0]][pos_a[1]].getStatus() == 0) {
                break;
            }
            if (player.is_Im_ai()) {
                view.exception_occupied();
            }

        }
        return pos_a;
    }

    public void play() {
        int t = 0;
        for (int i = 0; i < sizeX * sizeY; i++) {
            setOwner(players[t]);
            view.display_grid(board_cells, sizeX, sizeY);
            if (did_i_win()) {
                view.show_player_with_color(players[t]);
                return;
            }
            t = t == 0 ? 1 : 0;
        }
        view.message_draw();
    }

    private boolean did_i_win() { // to camelcase
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                boolean test_diagonal =
                        (j < sizeX - 2 && i < sizeY - 2) && (
                                (board_cells[i][j].getStatus() > 0
                                        && board_cells[i][j].getStatus() == board_cells[j + 1][j + 1].getStatus()
                                        && board_cells[i][j].getStatus() == board_cells[j + 2][j + 2].getStatus())
                                        ||
                                        (board_cells[i][j + 2].getStatus() > 0
                                                && board_cells[i][j + 2].getStatus() == board_cells[i + 1][j + 1].getStatus()
                                                && board_cells[i][j + 2].getStatus() == board_cells[i + 2][j].getStatus())
                        );

                boolean test_vertical = i < sizeY - 2 && board_cells[i][j].getStatus() > 0
                        && board_cells[i][j].getStatus() == board_cells[i + 1][j].getStatus()
                        && board_cells[i][j].getStatus() == board_cells[i + 2][j].getStatus();

                boolean test_horizontal = j < sizeX - 2 && board_cells[i][j].getStatus() > 0
                        && board_cells[i][j].getStatus() == board_cells[i][j + 1].getStatus()
                        && board_cells[i][j].getStatus() == board_cells[i][j + 2].getStatus();

                if (test_diagonal || test_horizontal || test_vertical) {
                    return true;
                }
            }
        }
        return false;
    }
}
