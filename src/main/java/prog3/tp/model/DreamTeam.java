package prog3.tp.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import prog3.tp.presenter.Observer;

public class DreamTeam implements Model {
    private List<Observer> _observers;
    private List<Employee> _employees;
    private List<Incompatibility> List_incompatibility;
    private EnumMap<Role, Integer> _requirements;

    public DreamTeam() {
        _observers = new LinkedList<>();
        _employees = new ArrayList<>();
        List_incompatibility = new ArrayList<>();
        _requirements = new EnumMap<>(Role.class);
        for (Role r : Role.values()) {
            _requirements.put(r, 1);
        }
    }

    public void addEmployee(String name, String role, int calification) {
        Employee newEmployee = new Employee(name, Role.toRole(role), calification);
        _employees.add(newEmployee);

        notifyObservers();
    }

    public void addIncompatibility(Employee E1, Employee E2) {
        if (!_employees.contains(E1) || !_employees.contains(E2)) {
            throw new IllegalArgumentException();
        }

        Incompatibility incompatibles = new Incompatibility(E1, E2);
        List_incompatibility.add(incompatibles);

        notifyObservers();
    }

    public void setRequirement(Role role, int count) {
        if (count <= 0 || role == null) {
            throw new IllegalArgumentException();
        }

        _requirements.put(role, count);
    }

    public int getRequiredCount(Role role) {
        return _requirements.get(role);
    }

    public EnumMap<Role, Integer> getRequirements() {
        return _requirements.clone();
    }

    @Override
    public Employee findEmployeeByName(String name) {

        for (Employee e : _employees) {
            if (e.getName().equals(name)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Employee name does not exist.");
    }

    public List<Employee> getEmployees() {
        return _employees;
    }

    public List<Incompatibility> getIncompatibilities() {
        return List_incompatibility;
    }

    @Override
    public void addObserver(Observer observer) {
        _observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer o : _observers) o.update();
    }

    public void removeObserver(Observer observer) {
        _observers.remove(observer);
    }
}
