package prog3.tp.view;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

class IncompatibilityDialogPane extends ToolbarDialogPane {
    private JComboBox<String> _firstEmployeeField;
    private JComboBox<String> _secondEmployeeField;
    private List<String> _employeeNames;

    IncompatibilityDialogPane(String title, List<String> employeeNames) {
        super(title);
        _employeeNames = employeeNames;

        initComponents();
        addComponents();
    }

    @Override
    void initComponents() {
        _firstEmployeeField = createEmployeeComboBox();
        _secondEmployeeField = createEmployeeComboBox();
    }

    private JComboBox<String> createEmployeeComboBox() {
        String[] items = _employeeNames.toArray(new String[0]);
        return new JComboBox<>(items);
    }

    @Override
    void addComponents() {
        this.addComponent("First employee: ", _firstEmployeeField);
        this.addComponent("Second employee: ", _secondEmployeeField);
    }

    private void addComponent(String labelText, JComponent component) {
        this.add(new JLabel(labelText));
        this.add(component);
    }

    String getFirstEmployee() {
        return (String) _firstEmployeeField.getSelectedItem();
    }

    String getSecondEmployee() {
        return (String) _secondEmployeeField.getSelectedItem();
    }
}
