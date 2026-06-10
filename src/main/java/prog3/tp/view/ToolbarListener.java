package prog3.tp.view;

import java.util.List;
import java.util.Map;
import prog3.tp.model.Role;

interface ToolbarListener {
    void onEmployeeAdded(String name, String role, int calification, String filePath);

    void onIncompatibilityAdded(String firstEmployee, String secondEmployee);

    List<String> getEmployeeNames();

    void onRequirementsAdded(Map<Role, Integer> requirements);

    void onSolve();
}
