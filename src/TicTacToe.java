import static java.lang.Integer.parseInt;

public class TicTacToe {
    private final int size = 3;
    private final Cell[][] board_cells = new Cell[size][size];
    private final Player[] players = new Player[2];
    private final View view = new View();
    private final InteractionUtilisateur interUtil = new InteractionUtilisateur();

    public TicTacToe() {
        game_initialization();
        view.display_grid(board_cells, size);
    }

    private void game_initialization() {
        int start = 0;
        for (int i = 0; i < players.length; i++) {

            String[] user = interUtil.scanner_user();
            if (parseInt(user[1]) == 1) {
                players[i] = new HumanPlayer(start, user[0]);
            } else {
                players[i] = new ArtificialPlayer(start, user[0]);
            }
            start = view.show_player_names(players[i], start);
        }

        for (int i = 0; i < board_cells.length; i++) {
            for (int j = 0; j < board_cells[i].length; j++) {
                board_cells[i][j] = new Cell();
            }
        }
    }

    private void setOwner(Player player) {
        int[] pos = getMoveFromPlayer(player);
        board_cells[pos[0]][pos[1]].setStatus(player.getRepresentation());
    }

    private int[] getMoveFromPlayer(Player player) {
        int pos_x;
        int pos_y;
        Integer pos_a; // unified position
        int[] pos = new int[2];

        while (true) {
            pos_a = player.get_number();
            if (pos_a != null && (pos_a < 0 || pos_a > size * size) && !player.is_Im_ai()) {
                view.exception_re_enter_position_off_grid();
                continue;
            } else if (pos_a == null || ((pos_a < 0 || pos_a > size * size) && player.is_Im_ai())) {
                continue;
            }

            pos_x = pos_a % size;
            pos_y = pos_a / size;
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

    public void play() {
        int t = 0;
        for (int i = 0; i < size * size; i++) {
            setOwner(players[t]);
            view.display_grid(board_cells, size);
            if (did_i_win()) {
                view.show_player_with_color(players[t]);
                return;
            }
            t = t == 0 ? 1 : 0;
        }
        view.message_draw();
    }

    private boolean did_i_win() { // to camelcase
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean test_diagonal =
                        (j < size - 2 && i < size - 2) && (
                                (board_cells[i][j].getStatus() > 0
                                        && board_cells[i][j].getStatus() == board_cells[j + 1][j + 1].getStatus()
                                        && board_cells[i][j].getStatus() == board_cells[j + 2][j + 2].getStatus())
                                        ||
                                        (board_cells[i][j + 2].getStatus() > 0
                                                && board_cells[i][j + 2].getStatus() == board_cells[i + 1][j + 1].getStatus()
                                                && board_cells[i][j + 2].getStatus() == board_cells[i + 2][j].getStatus())
                        );

                boolean test_vertical = i < size - 2 && board_cells[i][j].getStatus() > 0
                        && board_cells[i][j].getStatus() == board_cells[i + 1][j].getStatus()
                        && board_cells[i][j].getStatus() == board_cells[i + 2][j].getStatus();

                boolean test_horizontal = j < size - 2 && board_cells[i][j].getStatus() > 0
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
