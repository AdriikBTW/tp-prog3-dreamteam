package prog3.tp.view;

class IncompatibilityTable extends DataTable {
    private static String[] COLUMN_NAMES = {"Photo", "First Employee", "Photo", "Second Employee"};

    IncompatibilityTable() {
        super(COLUMN_NAMES);
    }

    void addNewIncompatibility(String firstEmployee, String secondEmployee) {
        Object photo1 = ImageRegistry.getIcon(firstEmployee);
        Object photo2 = ImageRegistry.getIcon(secondEmployee);
        Object[] row = {photo1, firstEmployee, photo2, secondEmployee};
        this.addNewRow(row);
    }
}
