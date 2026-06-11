package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.EnumMap;
import org.junit.Before;
import org.junit.Test;
import prog3.tp.model.DreamTeam;
import prog3.tp.model.Employee;
import prog3.tp.model.Incompatibility;
import prog3.tp.model.Role;
import prog3.tp.presenter.Observer;

public class DreamTeamTest {
    private DreamTeam _dreamTeam;

    private static class TestObserver implements Observer {
        boolean updated = false;

        @Override
        public void update() {
            updated = true;
        }
    }

    @Before
    public void setUp() {
        _dreamTeam = new DreamTeam();
        _dreamTeam.addObserver(() -> {});
    }

    @Test
    public void addEmployee_addsEmployeeToList() {
        _dreamTeam.addEmployee("Alice", "Programmer", 4);
        assertEquals(1, _dreamTeam.getEmployees().size());
        assertEquals("Alice", _dreamTeam.getEmployees().get(0).getName());
    }

    @Test
    public void addEmployee_multipleEmployees_incrementsList() {
        _dreamTeam.addEmployee("Alice", "Programmer", 4);
        _dreamTeam.addEmployee("Bob", "Tester", 3);
        _dreamTeam.addEmployee("Charlie", "Arquitect", 5);
        assertEquals(3, _dreamTeam.getEmployees().size());
    }

    @Test
    public void addEmployee_notifiesObserver() {
        TestObserver observer = new TestObserver();
        _dreamTeam.addObserver(observer);
        _dreamTeam.addEmployee("Alice", "Programmer", 4);
        assertTrue(observer.updated);
    }

    @Test
    public void removeObserverTest() {
        TestObserver observer1 = new TestObserver();
        TestObserver observer2 = new TestObserver();

        _dreamTeam.addObserver(observer1);
        _dreamTeam.addObserver(observer2);
        _dreamTeam.removeObserver(observer1);

        _dreamTeam.addEmployee("Linus", "Programmer", 5);

        assertFalse(observer1.updated);
        assertTrue(observer2.updated);
    }

    @Test
    public void addEmployee_withUnknownRole_assignsUnknown() {
        _dreamTeam.addEmployee("Alice", "InvalidRole", 4);
        Employee emp = _dreamTeam.getEmployees().get(0);
        assertEquals("Alice", emp.getName());
    }

    @Test
    public void addIncompatibility_validEmployees_addsToList() {
        Employee alice = addEmployee("Alice", "Programmer", 4);
        Employee bob = addEmployee("Bob", "Tester", 3);
        _dreamTeam.addIncompatibility(alice, bob);
        assertEquals(1, _dreamTeam.getIncompatibilities().size());
    }

    @Test
    public void addIncompatibility_returnsCorrectIncompatibility() {
        Employee alice = addEmployee("Alice", "Programmer", 4);
        Employee bob = addEmployee("Bob", "Tester", 3);
        _dreamTeam.addIncompatibility(alice, bob);
        Incompatibility inc = _dreamTeam.getIncompatibilities().get(0);
        assertEquals(alice, inc.getIncompatibilityEmployee1());
        assertEquals(bob, inc.getIncompatibilityEmployee2());
    }

    @Test
    public void addIncompatibility_firstEmployeeNotInList_throwsException() {
        addEmployee("Alice", "Programmer", 4);
        Employee alice = _dreamTeam.getEmployees().get(0);
        Employee bob = new Employee("Bob", null, 3);
        assertThrows(
                IllegalArgumentException.class, () -> _dreamTeam.addIncompatibility(alice, bob));
    }

    @Test
    public void addIncompatibility_secondEmployeeNotInList_throwsException() {
        addEmployee("Bob", "Tester", 3);
        Employee bob = _dreamTeam.getEmployees().get(0);
        Employee alice = new Employee("Alice", null, 4);
        assertThrows(
                IllegalArgumentException.class, () -> _dreamTeam.addIncompatibility(alice, bob));
    }

    @Test
    public void getEmployees_returnsAllAddedEmployees() {
        addEmployee("Alice", "Programmer", 4);
        addEmployee("Bob", "Tester", 3);
        addEmployee("Charlie", "Arquitect", 5);
        assertEquals(3, _dreamTeam.getEmployees().size());
        assertEquals("Alice", _dreamTeam.getEmployees().get(0).getName());
        assertEquals("Bob", _dreamTeam.getEmployees().get(1).getName());
        assertEquals("Charlie", _dreamTeam.getEmployees().get(2).getName());
    }

    @Test
    public void getIncompatibilities_returnsAddedIncompatibilities() {
        Employee alice = addEmployee("Alice", "Programmer", 4);
        Employee bob = addEmployee("Bob", "Tester", 3);
        Employee charlie = addEmployee("Charlie", "Arquitect", 5);
        _dreamTeam.addIncompatibility(alice, bob);
        _dreamTeam.addIncompatibility(alice, charlie);
        assertEquals(2, _dreamTeam.getIncompatibilities().size());
    }

    private Employee addEmployee(String name, String role, int calification) {
        _dreamTeam.addEmployee(name, role, calification);
        return _dreamTeam.getEmployees().get(_dreamTeam.getEmployees().size() - 1);
    }

    @Test
    public void setRequirementsTest() {
        Role[] roles = Role.values();

        for (Role role : roles) _dreamTeam.setRequirement(role, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setZeroAmountInRequirementsTest() {
        _dreamTeam.setRequirement(Role.TEAM_LEADER, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeAmountInRequirementsTest() {
        _dreamTeam.setRequirement(Role.TEAM_LEADER, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullRoleInRequirementsTest() {
        _dreamTeam.setRequirement(null, 10);
    }

    @Test
    public void getRequirementsTest() {
        _dreamTeam.setRequirement(Role.PROGRAMMER, 10);
        assertEquals(10, _dreamTeam.getRequiredCount(Role.PROGRAMMER));
    }

    @Test
    public void getRolesInRequirementsTest() {
        EnumMap<Role, Integer> roles = new EnumMap<>(Role.class);
        EnumMap<Role, Integer> returnRoles = new EnumMap<>(Role.class);
        Role[] roleValues = Role.values();

        for (int i = 0; i < roleValues.length; i++) {
            Role role = roleValues[i];
            int amount = (i + 1) * 2;

            roles.put(role, amount);
            _dreamTeam.setRequirement(role, amount);
        }

        returnRoles = _dreamTeam.getRequirements();
        assertEquals(roles, returnRoles);
    }

    @Test
    public void findEmployeeByNameTest() {
        Employee employee = new Employee("Linus", Role.PROGRAMMER, 5);
        _dreamTeam.addEmployee("Linus", Role.PROGRAMMER.toString(), 5);
        assertEquals(employee, _dreamTeam.findEmployeeByName("Linus"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findNonExistingEmployeeByNameTest() {
        _dreamTeam.addEmployee("Linus", Role.PROGRAMMER.toString(), 5);
        _dreamTeam.findEmployeeByName("Adrián");
    }
}
