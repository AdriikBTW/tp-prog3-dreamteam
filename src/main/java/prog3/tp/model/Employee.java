package prog3.tp.model;

class Employee {
    private String _name;
    private Role _role;
    private int _calification;

    Employee(String name, Role role, int calification) {
        _name = name;
        _role = role;
        _calification = calification;
    }

    String getName() {
        return _name;
    }

    Role getRole() {
        return _role;
    }

    int getCalification() {
        return _calification;
    }
}
