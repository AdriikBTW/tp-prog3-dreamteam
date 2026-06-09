package prog3.tp.view;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

class TabMenu extends JTabbedPane {
    private EmployeeTable _employeeTable;
    private IncompatibilityTable _incompatibilityTable;

    TabMenu() {
        initEmployeesTab();
        initIncompatibilitiesTab();
        initTeamTab();
    }

    private void initEmployeesTab() {
        _employeeTable = new EmployeeTable();
        this.addTab("Employees", _employeeTable);
    }

    private void initIncompatibilitiesTab() {
        _incompatibilityTable = new IncompatibilityTable();
        this.addTab("Incompatibilities", _incompatibilityTable);
    }

    private void initTeamTab() {
        JTextField text = new JTextField("anashe");
        this.addTab("Teams", text);
    }

    void addNewEmployee(String name, String role, int calification) {
        _employeeTable.addNewEmployee(name, role, calification);
    }

    void addNewIncompatibility(String firstEmployee, String secondEmployee) {
        _incompatibilityTable.addNewIncompatibility(firstEmployee, secondEmployee);
    }
}
