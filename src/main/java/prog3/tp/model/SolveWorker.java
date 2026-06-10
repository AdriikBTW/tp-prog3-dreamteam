package prog3.tp.model;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.SwingWorker;

public class SolveWorker extends SwingWorker<SolverResult, Void> {
    private final List<Employee> _employees;
    private final EnumMap<Role, Integer> _requirements;
    private final List<Incompatibility> _incompatibilities;
    private final Consumer<SolverResult> _callback;

    public SolveWorker(
            List<Employee> employees,
            EnumMap<Role, Integer> requirements,
            List<Incompatibility> incompatibilities,
            Consumer<SolverResult> callback) {
        _employees = employees;
        _requirements = requirements;
        _incompatibilities = incompatibilities;
        _callback = callback;
    }

    @Override
    protected SolverResult doInBackground() {
        TeamSolver solver = new TeamSolver(_employees, _requirements, _incompatibilities);
        List<Employee> team = solver.solve();
        return new SolverResult(team);
    }

    @Override
    protected void done() {
        try {
            SolverResult result = get();
            _callback.accept(result);
        } catch (Exception e) {
            _callback.accept(new SolverResult(List.of()));
        }
    }
}
