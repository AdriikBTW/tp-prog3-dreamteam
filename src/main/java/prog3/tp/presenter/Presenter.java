package prog3.tp.presenter;

import java.util.List;
import java.util.Map;
import prog3.tp.model.Employee;
import prog3.tp.model.Model;
import prog3.tp.model.Role;
import prog3.tp.view.View;

public class Presenter implements Observer {
    private final Model _model;
    private final View _view;

    public Presenter(Model model, View view) {
        _model = model;
        _view = view;

        _view.setPresenter(this);
        _model.addObserver(this);
    }

    public void addEmployee(String name, String role, int calification) {
        _model.addEmployee(name, role, calification);
    }

    public void onIncompatibilityAdded(String name1, String name2) {
        Employee e1 = _model.findEmployeeByName(name1);
        Employee e2 = _model.findEmployeeByName(name2);
        _model.addIncompatibility(e1, e2);
    }

    public List<String> getEmployeeNames() {
        return _model.getEmployees().stream()
                .map(Employee::getName)
                .toList();
    }

    public void addRequirements(Map<Role, Integer> requirements) {
        for (Map.Entry<Role, Integer> entry : requirements.entrySet()) {
            Role role = entry.getKey();
            int amount = entry.getValue();
            _model.setRequirement(role, amount);
        }
    }

    @Override
    public void update() {
        _view.update();
    }
}
