package prog3.tp.presenter;

public class EmployeeViewData {
    private final String _name;
    private final String _role;
    private final int _calification;

    public EmployeeViewData(String name, String role, int calification) {
        _name = name;
        _role = role;
        _calification = calification;
    }

    public String getName() {
        return _name;
    }

    public String getRole() {
        return _role;
    }

    public int getCalification() {
        return _calification;
    }
}
