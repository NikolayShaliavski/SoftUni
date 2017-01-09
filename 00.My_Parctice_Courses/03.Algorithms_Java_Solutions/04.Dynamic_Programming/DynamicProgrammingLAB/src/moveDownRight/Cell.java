package moveDownRight;

public class Cell {

    private int row;

    private int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return String.format("Cell {%d %d}", this.row, this.col);
    }
}
