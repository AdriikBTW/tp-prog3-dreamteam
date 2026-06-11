package prog3.tp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TeamSolverTest {

    private List<Employee> _employees;
    private EnumMap<Role, Integer> _requirements;
    private List<Incompatibility> _incompatibilities;

    @Before
    public void setUp() {
        _employees = new ArrayList<>();
        _requirements = new EnumMap<>(Role.class);
        _incompatibilities = new ArrayList<>();

        for (Role r : Role.values()) {
            _requirements.put(r, 0);
        }
    }

    @Test
    public void solve_IdealSelectionWithoutIncompatibilities() {
        Employee john = new Employee("John", Role.PROGRAMMER, 5);
        Employee peter = new Employee("Peter", Role.PROGRAMMER, 3);
        Employee anna = new Employee("Anna", Role.TEAM_LEADER, 5);
        Employee charlie = new Employee("Charlie", Role.TEAM_LEADER, 3);
        _employees.add(john);
        _employees.add(peter);
        _employees.add(anna);
        _employees.add(charlie);
        _requirements.put(Role.PROGRAMMER, 1);
        _requirements.put(Role.TEAM_LEADER, 1);
        TeamSolver solver = new TeamSolver(_employees, _requirements, _incompatibilities);
        List<Employee> result = solver.solve();

        assertEquals(2, result.size());
        assertTrue(result.contains(john));
        assertTrue(result.contains(anna));
    }

    @Test
    public void solve_IdealSelectionWithIncompatibilities() {
        Employee john = new Employee("John", Role.PROGRAMMER, 5);
        Employee peter = new Employee("Peter", Role.PROGRAMMER, 3);
        Employee anna = new Employee("Anna", Role.TEAM_LEADER, 5);
        Employee charlie = new Employee("Charlie", Role.TEAM_LEADER, 3);
        _employees.add(john);
        _employees.add(peter);
        _employees.add(anna);
        _employees.add(charlie);
        _requirements.put(Role.PROGRAMMER, 1);
        _requirements.put(Role.TEAM_LEADER, 1);
        _incompatibilities.add(new Incompatibility(john, anna));
        _incompatibilities.add(new Incompatibility(peter, anna));
        TeamSolver solver = new TeamSolver(_employees, _requirements, _incompatibilities);
        List<Employee> result = solver.solve();

        assertEquals(2, result.size());
        assertTrue(result.contains(john));
        assertTrue(result.contains(charlie));
    }

    @Test
    public void solve_UnfeasibleTeam() {
        _requirements.put(Role.TESTER, 1);
        TeamSolver solver = new TeamSolver(_employees, _requirements, _incompatibilities);
        List<Employee> result = solver.solve();

        assertTrue(result.isEmpty());
    }

    @Test
    public void solve_IncompatibilityInTheSameRole() {
        Employee anna = new Employee("Anna", Role.TESTER, 5);
        Employee john = new Employee("John", Role.TESTER, 5);
        Employee charlie = new Employee("Charlie", Role.TESTER, 5);
        Employee luke = new Employee("Luke", Role.TESTER, 4);
        _employees.add(anna);
        _employees.add(john);
        _employees.add(charlie);
        _employees.add(luke);
        _requirements.put(Role.TESTER, 3);
        _incompatibilities.add(new Incompatibility(anna, john));
        _incompatibilities.add(new Incompatibility(john, charlie));
        TeamSolver solver = new TeamSolver(_employees, _requirements, _incompatibilities);
        List<Employee> result = solver.solve();

        assertEquals(3, result.size());
        assertTrue(result.contains(anna));
        assertTrue(result.contains(charlie));
        assertTrue(result.contains(luke));
    }

    @Test
    public void solve_IgnoresUnrequiredRoles() {
        Employee anna = new Employee("Anna", Role.TEAM_LEADER, 4);
        Employee peter = new Employee("Peter", Role.TESTER, 5);
        _employees.add(anna);
        _employees.add(peter);
        _requirements.put(Role.TEAM_LEADER, 0);
        _requirements.put(Role.TESTER, 1);
        TeamSolver solver = new TeamSolver(_employees, _requirements, _incompatibilities);
        List<Employee> result = solver.solve();

        assertEquals(1, result.size());
        assertTrue(result.contains(peter));
    }

    @Test
    public void Solve_TotalIncompatibilityMakesTeamUnviable() {
        Employee anna = new Employee("Anna", Role.TEAM_LEADER, 5);
        Employee charlie = new Employee("Charlie", Role.PROGRAMMER, 5);
        _employees.add(anna);
        _employees.add(charlie);
        _requirements.put(Role.TEAM_LEADER, 1);
        _requirements.put(Role.PROGRAMMER, 1);
        _incompatibilities.add(new Incompatibility(anna, charlie));
        TeamSolver solver = new TeamSolver(_employees, _requirements, _incompatibilities);
        List<Employee> result = solver.solve();

        assertTrue(result.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void TeamSolver_EmployeesNull_ThrowException() {
        new TeamSolver(null, _requirements, _incompatibilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TeamSolver_RequirementsNull_ThrowException() {
        new TeamSolver(_employees, null, _incompatibilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TeamSolver_IncompatibilitiesNull_ThrowException() {
        new TeamSolver(_employees, _requirements, null);
    }
}
