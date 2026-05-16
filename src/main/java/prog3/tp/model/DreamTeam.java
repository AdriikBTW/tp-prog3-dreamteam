package prog3.tp.model;

import java.util.ArrayList;
import java.util.List;
import prog3.tp.presenter.Observer;

public class DreamTeam implements Model {
    private Observer _observer;
    private List<Employee> employees;

    public DreamTeam() {
        employees = new ArrayList<>();
    }

    public void addEmployee(String name, String role, int calification) {
        Employee newEmployee = new Employee(name, stringToRole(role), calification);
        employees.add(newEmployee);

        _observer.update();
    }

    private Role stringToRole(String role) {
        Role r;
        switch (role) {
            case "Team Leader":
                r = Role.TEAM_LEADER;
                break;
            case "Arquitect":
                r = Role.ARQUITECT;
                break;
            case "Programmer":
                r = Role.PROGRAMMER;
                break;
            case "Tester":
                r = Role.TESTER;
                break;
            default:
                r = Role.UNKNOWN;
                break;
        }

        return r;
    }

    @Override
    public void addObserver(Observer observer) {
        _observer = observer;
    }
}
