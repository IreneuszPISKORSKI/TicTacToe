public class Cell {
    private int status;
    static Colors_Terminal c_term = new Colors_Terminal();

    void getRepresentation(int number) {
        System.out.print("|  " + number + " ");
        if (status == 1) {
            System.out.print("| " + c_term.YELLOW + "O " + c_term.RESET);
        } else if (status == 2) {
            System.out.print("| " + c_term.BLUE + "X " + c_term.RESET);
        }else {
            System.out.println("Error, player out of bounds");
        }

//        if (status == 0) {
//            if (number<10){
//                System.out.print("|  " + number + " ");
//            }else {
//                System.out.print("| " + number + " ");
//            }
//        } else if (status == 1) {
//            System.out.print("| " + c_term.YELLOW + "O " + c_term.RESET);
//        } else if (status == 2) {
//            System.out.print("| " + c_term.BLUE + "X " + c_term.RESET);
//        }else {
//            System.out.println("Error, player out of bounds");
//        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
