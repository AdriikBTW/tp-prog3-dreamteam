package prog3.tp.view;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

class TabMenu extends JTabbedPane {
    private EmployeeTable _employeeTable;

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
        JTextField text = new JTextField("there!");
        this.addTab("Incompatibilities", text);
    }

    private void initTeamTab() {
        JTextField text = new JTextField("anashe");
        this.addTab("Teams", text);
    }

    void addNewEmployee(String name, String role, int calification) {
        _employeeTable.addNewEmployee(name, role, calification);
    }
}
