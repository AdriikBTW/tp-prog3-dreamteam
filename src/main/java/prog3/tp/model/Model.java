package prog3.tp.model;

import java.util.List;
import prog3.tp.presenter.Observer;

public interface Model {
    public void addEmployee(String name, String role, int calification);

    public void addObserver(Observer observer);

    public void addIncompatibility(Employee E1, Employee E2);

    public void setRequirement(Role r, int count);

    public int getRequiredCount(Role r);

    public Employee findEmployeeByName(String name);

    public List<Employee> getEmployees();

    public List<Incompatibility> getIncompatibilities();
}
