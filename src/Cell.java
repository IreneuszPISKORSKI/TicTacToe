public class Cell {
    private int status;
    private View view = new View();

    void getRepresentation(int number) {
        view.cell_representation(status, number);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
