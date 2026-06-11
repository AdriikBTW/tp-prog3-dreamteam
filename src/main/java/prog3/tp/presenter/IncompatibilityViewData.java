package prog3.tp.presenter;

public class IncompatibilityViewData {
    private final String _employee1;
    private final String _employee2;

    public IncompatibilityViewData(String employee1, String employee2) {
        _employee1 = employee1;
        _employee2 = employee2;
    }

    public String getFirstEmployee() {
        return _employee1;
    }

    public String getSecondEmployee() {
        return _employee2;
    }
}
