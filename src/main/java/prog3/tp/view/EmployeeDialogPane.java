package prog3.tp.view;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import prog3.tp.model.Role;

class EmployeeDialogPane extends ToolbarDialogPane {
    private JTextField _name;
    private JComboBox<String> _role;
    private JComboBox<String> _calification;

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
        String[] items = new String[Role.values().length];

        for (int i = 0; i < Role.values().length; i++)
            items[i] = Role.values()[i].toString();

        _role = new JComboBox<>(items);
    }

    private void initCalificationField() {

        String[] items = {
            "★", "★★", "★★★", "★★★★", "★★★★★",
        };
        _calification = new JComboBox<>(items);
    }

    @Override
    void addComponents() {
        this.addComponent("Name: ", _name);
        this.addComponent("Role: ", _role);
        this.addComponent("Calification: ", _calification);
    }

    public String getName() {
        return _name.getText();
    }

    public String getRole() {
        return (String) _role.getSelectedItem();
    }

    public int getCalification() {
        return ((String) _calification.getSelectedItem()).length();
    }
}
