package prog3.tp.view;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

class TabMenu extends JTabbedPane {
    TabMenu() {
        initEmployeesTab();
        initIncompatibilitiesTab();
        initTeamTab();
    }

    private void initEmployeesTab() {
        JTextField text = new JTextField("hello");
        this.addTab("Employees", text);
    }

    private void initIncompatibilitiesTab() {
        JTextField text = new JTextField("there!");
        this.addTab("Incompatibilities", text);
    }

    private void initTeamTab() {
        JTextField text = new JTextField("anashe");
        this.addTab("Teams", text);
    }
}
