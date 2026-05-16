package prog3.tp.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

class Toolbar extends JToolBar {
    private ToolbarButton _employeeButton;
    private ToolbarListener _listener;

    public Toolbar(ToolbarListener listener) {
        _listener = listener;
        this.setFloatable(false);
        initButtons();
    }

    private void initButtons() {
        initEmployeeButton();

        this.add(_employeeButton);
    }

    void setFontForButtons(Font font) {
        // NOTE: check how to set the font in the button constructor, so we
        // dont set it from here manually
        _employeeButton.setFont(font);
    }

    private void initEmployeeButton() {
        _employeeButton = new ToolbarButton("");
        _employeeButton.setToolTipText("Add new employee.");
        _employeeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addNewEmployee();
                    }
                });
    }

    private void addNewEmployee() {
        EmployeeDialogPane dialog = new EmployeeDialogPane("New Employee");

        if (dialog.showDialog() == JOptionPane.OK_OPTION) {
            if (dialog.getName().isBlank()) showMessageError("Name field can't be empty.");
            else
                _listener.onEmployeeAdded(
                        dialog.getName(), dialog.getRole(), dialog.getCalification());
        }
    }

    private void showMessageError(String text) {
        JOptionPane.showMessageDialog(null, text, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
