package prog3.tp.view;

class EmployeeTable extends DataTable {
    private static String[] COLUMN_NAMES = {"Photo", "Name", "Role", "Calification"};
    private static final int IMAGE_SIZE = 50;

    EmployeeTable() {
        super(COLUMN_NAMES);
        this.setRowHeight(IMAGE_SIZE + 10);
    }

    void addNewEmployee(String name, String role, int calification, String imagePath) {
        Object photo = ImageRegistry.getIcon(name);

        Object[] row = {photo, name, role, calification};
        this.addNewRow(row);
    }
}
