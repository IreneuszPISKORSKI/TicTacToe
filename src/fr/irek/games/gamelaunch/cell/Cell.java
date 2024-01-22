package fr.irek.games.gamelaunch.cell;

import fr.irek.games.gamelaunch.view.View;

public class Cell {
    private int status;
    private final View view = new View();

    public void getRepresentation(int number) {
        view.cell_representation(status, number);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
