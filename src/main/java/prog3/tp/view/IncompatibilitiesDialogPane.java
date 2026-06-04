package prog3.tp.view;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

class IncompatibilitiesDialogPane extends ToolbarDialogPane {
    private JComboBox<String> _firstEmployee;
    private JComboBox<String> _secondEmployee;

    IncompatibilitiesDialogPane(String title) {
        super(title);

        initComponents();
        addComponents();
    }

    @Override
    void initComponents()
    {
        initFirstEmployee();
        initSecondEmployee();
    }

    private void initFirstEmployee() {
        // TODO: this should be a dynamic list, so it should receive this list
        // by paramater
        String[] items = {"Adrián", "Emiliano", "Lautaro", "Nahuel"};
        _firstEmployee = new JComboBox<>(items);
    }

    private void initSecondEmployee() {
        String[] items = {"Adrián", "Emiliano", "Lautaro", "Nahuel"};
        _secondEmployee = new JComboBox<>(items);
    }

    @Override
    void addComponents() {
        this.addComponent("First employee: ", _firstEmployee);
        this.addComponent("Second employee: ", _secondEmployee);
    }

    private void addComponent(String labelText, JComponent component) {
        this.add(new JLabel(labelText));
        this.add(component);
    }

    public String getFirstEmployee() {
        return (String) _firstEmployee.getSelectedItem();
    }

    public String getSecondEmployee() {
        return (String) _secondEmployee.getSelectedItem();
    }
}
