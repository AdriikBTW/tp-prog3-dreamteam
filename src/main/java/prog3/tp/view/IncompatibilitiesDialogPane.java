package prog3.tp.view;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import prog3.tp.model.Employee;

public class IncompatibilitiesDialogPane extends ToolbarDialogPane {
    private JComboBox<Employee> _firstEmployee;
    private JComboBox<Employee> _secondEmployee;
    private List<Employee> _employees;

    public IncompatibilitiesDialogPane(String title, List<Employee> employees) {
        super(title);
        _employees = employees;

        initComponents();
        addComponents();
    }

    @Override
    void initComponents() {
        initFirstEmployee(_employees);
        initSecondEmployee(_employees);
    }

    private void initFirstEmployee(List<Employee> employees) {
        Employee[] items = employees.toArray(new Employee[0]);
        _firstEmployee = new JComboBox<>(items);
        _firstEmployee.setRenderer(new EmployeeRenderer());
    }

    private void initSecondEmployee(List<Employee> employees) {
        Employee[] items = employees.toArray(new Employee[0]);
        _secondEmployee = new JComboBox<>(items);
        _secondEmployee.setRenderer(new EmployeeRenderer());
    }

    private static class EmployeeRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Employee) setText(((Employee) value).getName());
            else setText("");
            return this;
        }
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
        Employee e = (Employee) _firstEmployee.getSelectedItem();
        return e == null ? null : e.getName();
    }

    public String getSecondEmployee() {
        Employee e = (Employee) _secondEmployee.getSelectedItem();
        return e == null ? null : e.getName();
    }

    public Employee getFirstEmployeeObject() {
        return (Employee) _firstEmployee.getSelectedItem();
    }

    public Employee getSecondEmployeeObject() {
        return (Employee) _secondEmployee.getSelectedItem();
    }
}
