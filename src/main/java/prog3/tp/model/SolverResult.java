package prog3.tp.model;

import java.util.List;

public class SolverResult {
    private final List<Employee> _team;

    public SolverResult(List<Employee> team) {
        _team = team;
    }

    public List<Employee> team() {
        return _team;
    }
}
