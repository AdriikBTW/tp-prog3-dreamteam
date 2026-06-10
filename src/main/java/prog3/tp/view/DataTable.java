package prog3.tp.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class DataTable extends JScrollPane {
    private JTable _table;
    private DefaultTableModel _tableModel;

    DataTable(String[] columnNames) {
        _tableModel = new ReadOnlyTable(columnNames, 0);
        _table = new JTable(_tableModel);
        this.setViewportView(_table);
    }

    void addNewRow(Object[] rowData) {
        _tableModel.addRow(rowData);
    }

    void clear() {
        _tableModel.setRowCount(0);
    }

    private class ReadOnlyTable extends DefaultTableModel {
        ReadOnlyTable(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }
}
