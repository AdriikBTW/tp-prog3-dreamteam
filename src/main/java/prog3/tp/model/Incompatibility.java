package prog3.tp.model;

import java.util.ArrayList;
import java.util.List;

public class Incompatibility {

    private Employee _employee1;
    private Employee _employee2;

    public Incompatibility(Employee E1, Employee E2) {
        _employee1 = E1;
        _employee2 = E2;
    }

    public List<Employee> getIncompatibility() {

        List<Employee> ret = new ArrayList<>();

        ret.add(_employee1);
        ret.add(_employee2);

        return ret;
    }

    public Employee getIncompatibilityEmployee1() {
        return _employee1;
    }

    public Employee getIncompatibilityEmployee2() {
        return _employee2;
    }
}
