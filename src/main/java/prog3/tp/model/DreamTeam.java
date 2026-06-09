package prog3.tp.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import prog3.tp.presenter.Observer;

public class DreamTeam implements Model {
    private Observer _observer;
    private List<Employee> _employees;
    private List<Incompatibility> List_incompatibility;
    private EnumMap<Role, Integer> _requirements;

    public DreamTeam() {
        _employees = new ArrayList<>();
        List_incompatibility = new ArrayList<>();
        _requirements = new EnumMap<>(Role.class);
    }

    public void addEmployee(String name, String role, int calification) {
        Employee newEmployee = new Employee(name, Role.toRole(role), calification);
        _employees.add(newEmployee);

        _observer.update();
    }

    public void addIncompatibility(Employee E1, Employee E2) {
        if (!_employees.contains(E1) || !_employees.contains(E2)) {
            throw new IllegalArgumentException();
        }

        Incompatibility incompatibles = new Incompatibility(E1, E2);
        List_incompatibility.add(incompatibles);

        _observer.update();
    }

    public void setRequirement(Role role, int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }

        _requirements.put(role, count);
    }

    public int getRequiredCount(Role role) {
        if (_requirements.containsKey(role)) {
            return _requirements.get(role);
        } else return 0;
    }

    @Override
    public Employee findEmployeeByName(String name) {

        for (Employee e : _employees) {
            if (e.getName().equals(name)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Nombre de empleado no existe");
    }

    public List<Employee> getEmployees() {
        return _employees;
    }

    public List<Incompatibility> getIncompatibilities() {
        return List_incompatibility;
    }

    @Override
    public void addObserver(Observer observer) {
        _observer = observer;
    }
}
