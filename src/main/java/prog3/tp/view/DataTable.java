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
        this.setRowHeight(ImageRegistry.getScaledImageSize() + 10);
    }

    void addNewRow(Object[] rowData) {
        _tableModel.addRow(rowData);
    }

    void setRowHeight(int height) {
        _table.setRowHeight(height);
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

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getRowCount() > 0) {
                Object value = getValueAt(0, columnIndex);
                if (value != null) return value.getClass();
            }

            return super.getColumnClass(columnIndex);
        }
    }
}
