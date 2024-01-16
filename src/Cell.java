public class Cell {
    private int status;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public void Class() {
        getRepresentation();
    }

    void getRepresentation() {
        if (status == 0) {
            System.out.print("|   ");
        } else if (status == 1) {
            System.out.print("| " + ANSI_YELLOW + "O " +ANSI_RESET);
        } else if (status == 2) {
            System.out.print("| " + ANSI_BLUE + "X " + ANSI_RESET);
        }else {
            System.out.println("Error, player out of bounds");
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
