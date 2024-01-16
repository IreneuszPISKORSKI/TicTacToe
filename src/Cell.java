public class Cell {
    int status;

    public void Class() {
        getRepresentation();
    }

    void getRepresentation() {
        if (status == 0) {
            System.out.print("|   ");
        } else if (status == 1) {
            System.out.print("| O ");
        } else if (status == 2) {
            System.out.print("| X ");
        }else {
            System.out.println("Error, player out of bound");
        }
    }
}
