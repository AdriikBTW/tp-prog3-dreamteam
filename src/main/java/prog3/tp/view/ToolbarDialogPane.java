package prog3.tp.view;

import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

abstract class ToolbarDialogPane extends JPanel {
    private static final int ROW = 0;
    private static final int COL = 2;
    private static final int HGAP = 5;
    private static final int VGAP = 5;
    final String _title;

    public ToolbarDialogPane(String title) {
        _title = title;
        this.setLayout(new GridLayout(ROW, COL, HGAP, VGAP));
    }

    abstract void initComponents();

    abstract void addComponents();

    int showDialog() {
        return JOptionPane.showConfirmDialog(
                null, this, _title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    void addComponent(String labelText, JComponent component) {
        this.add(new JLabel(labelText));
        this.add(component);
    }
}
