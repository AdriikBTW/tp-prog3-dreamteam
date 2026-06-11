package prog3.tp.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeamSolver {
    private List<Employee> _allEmployees;
    private EnumMap<Role, Integer> _requirements;
    private List<Incompatibility> _incompatibilities;
    private List<Employee> _bestTeam = new ArrayList<>();
    private int _maxScore = -1;
    private EnumMap<Role, Integer> _currentCounts = new EnumMap<>(Role.class);
    private Map<Employee, Set<Employee>> _incompatMap = new HashMap<>();
    private int[] _maxRemainingFromIndex;

    public TeamSolver(
            List<Employee> employees,
            EnumMap<Role, Integer> reqs,
            List<Incompatibility> incompatibilities) {
        if (employees == null || reqs == null || incompatibilities == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        _allEmployees = new ArrayList<>(employees);

        _requirements = new EnumMap<>(reqs);

        _incompatibilities = new ArrayList<>(incompatibilities);

        for (Incompatibility inc : _incompatibilities) {
            Employee e1 = inc.getIncompatibilityEmployee1();
            Employee e2 = inc.getIncompatibilityEmployee2();

            _incompatMap.computeIfAbsent(e1, k -> new HashSet<>()).add(e2);
            _incompatMap.computeIfAbsent(e2, k -> new HashSet<>()).add(e1);
        }

        int n = _allEmployees.size();
        _maxRemainingFromIndex = new int[n + 1];
        _maxRemainingFromIndex[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            _maxRemainingFromIndex[i] =
                    _maxRemainingFromIndex[i + 1] + _allEmployees.get(i).getCalification();
        }

        for (Role role : Role.values()) {
            _currentCounts.put(role, 0);
        }
    }

    public List<Employee> solve() {
        backtracking(0, new ArrayList<>(), 0);
        return _bestTeam;
    }

    private void backtracking(int index, List<Employee> currentTeam, int currentScore) {
        if (index < _maxRemainingFromIndex.length) {
            if (currentScore + _maxRemainingFromIndex[index] <= _maxScore) return;
        }

        if (isValidTeam()) {
            if (currentScore > _maxScore) {
                _maxScore = currentScore;
                _bestTeam = new ArrayList<>(currentTeam);
            }
            return;
        }

        if (index == _allEmployees.size()) {
            return;
        }

        Employee candidate = _allEmployees.get(index);
        Role role = candidate.getRole();

        int currentCount = _currentCounts.get(role);
        int requiredCount = _requirements.getOrDefault(role, 0);

        if (currentCount < requiredCount && isCompatible(candidate, currentTeam)) {

            currentTeam.add(candidate);
            _currentCounts.put(role, currentCount + 1);

            backtracking(index + 1, currentTeam, currentScore + candidate.getCalification());

            currentTeam.remove(currentTeam.size() - 1);
            _currentCounts.put(role, currentCount);
        }

        backtracking(index + 1, currentTeam, currentScore);
    }

    private boolean isCompatible(Employee candidate, List<Employee> currentTeam) {
        if (currentTeam.isEmpty()) return true;

        Set<Employee> incompatible = _incompatMap.get(candidate);
        if (incompatible == null || incompatible.isEmpty()) return true;

        for (Employee member : currentTeam) {
            if (incompatible.contains(member)) return false;
        }
        return true;
    }

    private boolean isValidTeam() {
        for (Role role : Role.values()) {
            int required = _requirements.getOrDefault(role, 0);
            int current = _currentCounts.get(role);
            if (current != required) return false;
        }
        return true;
    }
}
