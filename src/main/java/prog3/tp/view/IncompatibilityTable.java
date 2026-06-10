package prog3.tp.view;

class IncompatibilityTable extends DataTable {
    private static String[] COLUMN_NAMES = {"First Employee", "Second Employee"};

    IncompatibilityTable() {
        super(COLUMN_NAMES);
    }

    void addNewIncompatibility(String firstEmployee, String secondEmployee) {
        Object[] row = {firstEmployee, secondEmployee};
        this.addNewRow(row);
    }

}
