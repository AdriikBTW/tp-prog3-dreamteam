package prog3.tp.view;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

class EmployeeDialogPane extends ToolbarDialogPane {
    private JTextField _name;
    private JComboBox<String> _role;
    private JComboBox<Integer> _calification;

    EmployeeDialogPane(String title) {
        super(title);

        initComponents();
        addComponents();
    }

    @Override
    void initComponents() {
        initNameField();
        initRoleField();
        initCalificationField();
    }

    private void initNameField() {
        _name = new JTextField();
    }

    private void initRoleField() {
        String[] items = {"Arquitect", "Programmer", "Team Leader", "Tester"};
        _role = new JComboBox<>(items);
    }

    private void initCalificationField() {
        Integer[] items = {1, 2, 3, 4, 5};
        _calification = new JComboBox<>(items);
    }

    @Override
    void addComponents() {
        addComponent("Name: ", _name);
        addComponent("Role: ", _role);
        addComponent("Calification: ", _calification);
    }

    private void addComponent(String labelText, JComponent component) {
        this.add(new JLabel(labelText));
        this.add(component);
    }

    public String getName() {
        return _name.getText();
    }

    public String getRole() {
        return (String) _role.getSelectedItem();
    }

    public int getCalification() {
        return (int) _calification.getSelectedItem();
    }
}
