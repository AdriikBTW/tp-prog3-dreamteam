package prog3.tp.model;

import java.util.Objects;

public class Employee {
    private String _name;
    private Role _role;
    private int _calification;

    public Employee(String name, Role role, int calification) {
        _name = name;
        _role = role;
        _calification = calification;
    }

    public String getName() {
        return _name;
    }

    public Role getRole() {
        return _role;
    }

    public int getCalification() {
        return _calification;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;

        Employee other = (Employee) obj;

        return _calification == other._calification &&
               Objects.equals(_name, other._name) &&
               _role == other._role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name, _role, _calification);
    }
}
