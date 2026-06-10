package prog3.tp.view;

class EmployeeTable extends DataTable {
    private static String[] COLUMN_NAMES = {"Name", "Role", "Calification"};

    EmployeeTable() {
        super(COLUMN_NAMES);
    }

    void addNewEmployee(String name, String role, int calification) {
        Object[] row = {name, role, calification};
        this.addNewRow(row);
    }
}
