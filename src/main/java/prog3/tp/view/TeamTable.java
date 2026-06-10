package prog3.tp.view;

import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import prog3.tp.model.Employee;

class TeamTable extends JScrollPane {
    private JTable _table;
    private DefaultTableModel _tableModel;
    private static String[] COLUMN_NAMES = {"Name", "Role", "Calification"};

    TeamTable() {
        _tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        _table = new JTable(_tableModel);
        this.setViewportView(_table);
    }

    void setTeam(List<Employee> team) {
        _tableModel.setRowCount(0);
        for (Employee e : team) {
            Object[] row = {e.getName(), e.getRole().toString(), e.getCalification()};
            _tableModel.addRow(row);
        }
    }

    void clear() {
        _tableModel.setRowCount(0);
    }
}
