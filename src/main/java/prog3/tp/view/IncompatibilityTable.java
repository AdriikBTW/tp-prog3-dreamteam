package prog3.tp.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class IncompatibilityTable extends JScrollPane {
    private JTable _table;
    private DefaultTableModel _tableModel;
    private static String[] COLUMN_NAMES = {"First Employee", "Second Employee"};

    IncompatibilityTable() {
        _tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        _table = new JTable(_tableModel);
        this.setViewportView(_table);
    }

    void addNewIncompatibility(String firstEmployee, String secondEmployee) {
        Object[] row = {firstEmployee, secondEmployee};
        _tableModel.addRow(row);
    }

}
