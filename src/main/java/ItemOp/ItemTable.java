package ItemOp;

public class ItemTable {

    private Integer[][] table;

    public ItemTable() {
        initializeTable();
    }

    public Integer getCellValue(int rowIndex, int columnIndex) {
        return table[rowIndex][columnIndex];
    }

    public void setCellValue(int rowIndex, int columnIndex, int value) {
        this.table[rowIndex][columnIndex] = value;
    }

    public void addToCellValue(int rowIndex, int columnIndex, int value) {
        this.table[rowIndex][columnIndex] += value;
    }

    public void subtractFromCellValue(int rowIndex, int columnIndex, int value) {
        this.table[rowIndex][columnIndex] -= value;
    }

    public Integer[] getRow(int rowIndex) {
        return this.table[rowIndex];
    }

    public void setRow(int rowIndex, Integer[] row) {
        this.table[rowIndex] = row;
    }

    public void addToRow(int rowIndex, Integer[] row) {
        for (int i = 0; i < 10; i++) {
            addToCellValue(rowIndex, i, row[i]);
        }
    }

    public void clearTable() {
        initializeTable();
    }

    private void initializeTable() {
        table = new Integer[7][10];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                table[i][j] = 0;
            }
        }
    }
}
