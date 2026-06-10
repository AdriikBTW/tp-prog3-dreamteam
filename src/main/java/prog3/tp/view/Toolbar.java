package prog3.tp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

class Toolbar extends JToolBar {
    private ToolbarButton _employeeButton;
    private ToolbarButton _incompatibilityButton;
    private ToolbarButton _requirementsButton;
    private ToolbarButton _solveButton;
    private ToolbarListener _listener;

    public Toolbar(ToolbarListener listener) {
        _listener = listener;
        this.setFloatable(false);
        initButtons();
    }

    private void initButtons() {
        initEmployeeButton();
        initIncompatibilitiesButton();
        initRequirementsButton();
        initSolveButton();

        this.add(_employeeButton);
        this.add(_incompatibilityButton);
        this.add(_requirementsButton);
        this.addSeparator();
        this.add(_solveButton);
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
        	//if (dialog.getName().trim().isEmpty()) showMessageError("Name field can't be empty.");
            else
                _listener.onEmployeeAdded(
                        dialog.getName(), dialog.getRole(), dialog.getCalification());
        }
    }

    private void initIncompatibilitiesButton() {
        _incompatibilityButton = new ToolbarButton("󰌺");
        _incompatibilityButton.setToolTipText("Add new incompatibility.");
        _incompatibilityButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addNewIncompatibility();
                    }
                });
    }

    private void addNewIncompatibility() {
        List<String> names = _listener.getEmployeeNames();
        IncompatibilityDialogPane dialog = new IncompatibilityDialogPane("New incompatibility", names);

        if (dialog.showDialog() == JOptionPane.OK_OPTION) {
            String firstEmployee = dialog.getFirstEmployee();
            String secondEmployee = dialog.getSecondEmployee();

            if (firstEmployee == null || secondEmployee == null) {
                showMessageError("Employees can't be empty.");
                return;
            }

            if (firstEmployee.equals(secondEmployee)) {
                showMessageError("Select different employees.");
                return;
            }

            _listener.onIncompatibilityAdded(firstEmployee, secondEmployee);
        }
    }

    private void initRequirementsButton() {
        _requirementsButton = new ToolbarButton("");
        _requirementsButton.setToolTipText("Add requirements.");
        _requirementsButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addRequirements();
                    }
                });
    }

    private void addRequirements() {
        RequirementsDialogPane dialog = new RequirementsDialogPane("Requirements");

        if (dialog.showDialog() == JOptionPane.OK_OPTION)
            _listener.onRequirementsAdded(dialog.getRequirements());
    }

    private void initSolveButton() {
        _solveButton = new ToolbarButton("\u25B6");
        _solveButton.setToolTipText("Find optimal team.");
        _solveButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        _listener.onSolve();
                    }
                });
    }

    private void showMessageError(String text) {
        JOptionPane.showMessageDialog(null, text, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
