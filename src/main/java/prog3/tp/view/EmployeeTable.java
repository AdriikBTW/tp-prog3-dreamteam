package prog3.tp.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class EmployeeTable extends JScrollPane {
    private JTable _table;
    private DefaultTableModel _tableModel;
    private static String[] COLUMN_NAMES = {"Name", "Role", "Calification"};

    EmployeeTable() {
        _tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        _table = new JTable(_tableModel);
        this.setViewportView(_table);
    }

    void addNewEmployee(String name, String role, int calification) {
        Object[] row = {name, role, calification};
        _tableModel.addRow(row);
    }
}
