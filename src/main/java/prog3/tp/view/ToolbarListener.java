package prog3.tp.view;

import java.util.List;

interface ToolbarListener {
    void onEmployeeAdded(String name, String role, int calification);

    void onIncompatibilityAdded(String firstEmployee, String secondEmployee);

    List<String> getEmployeeNames();

    void onRequirementsAdded(
            int arquitectAmount, int programmerAmount, int teamLeaderAmount, int testerAmount);
}
