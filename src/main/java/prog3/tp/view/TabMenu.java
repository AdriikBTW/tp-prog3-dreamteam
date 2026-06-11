package prog3.tp.view;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import prog3.tp.presenter.EmployeeViewData;

class TabMenu extends JTabbedPane {
    private EmployeeTable _employeeTable;
    private IncompatibilityTable _incompatibilityTable;
    private JPanel _teamPanel;
    private JLabel _teamStatus;
    private TeamTable _teamTable;

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
        _teamPanel = new JPanel(new BorderLayout());

        _teamStatus = new JLabel("Press Solve to find the optimal team.", SwingConstants.CENTER);
        _teamPanel.add(_teamStatus, BorderLayout.NORTH);

        _teamTable = new TeamTable();
        _teamPanel.add(_teamTable, BorderLayout.CENTER);

        this.addTab("Teams", _teamPanel);
    }

    void addNewEmployee(String name, String role, int calification) {
        _employeeTable.addNewEmployee(name, role, calification);
    }

    void addNewIncompatibility(String firstEmployee, String secondEmployee) {
        _incompatibilityTable.addNewIncompatibility(firstEmployee, secondEmployee);
    }

    void showSolving() {
        _teamStatus.setText("Solving...");
        _teamTable.clear();
    }

    void showTeamResult(List<EmployeeViewData> result) {
        if (result.isEmpty()) {
            _teamStatus.setText("No feasible team found.");
            _teamTable.clear();
            return;
        }

        _teamStatus.setText("Optimal team found (" + result.size() + " members):");
        _teamTable.setTeam(result);
    }

    void clearAll() {
        _employeeTable.clear();
        _incompatibilityTable.clear();
    }
}
