package prog3.tp.model;

import prog3.tp.presenter.Observer;

public interface Model {
    public void addEmployee(String name, String role, int calification);

    public void addObserver(Observer observer);

    public void addIncompatibility(Employee E1, Employee E2);
}
