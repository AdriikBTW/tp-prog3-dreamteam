package prog3.tp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IncompatibilityTest {
    private Employee createEmployee(String name, String role, int calification) {
        DreamTeam dt = new DreamTeam();
        dt.addObserver(() -> {});
        dt.addEmployee(name, role, calification);
        return dt.getEmployees().get(0);
    }

    @Test
    public void getIncompatibilityEmployee1_returnsFirstEmployee() {
        Employee emp1 = createEmployee("Alice", "Programmer", 4);
        Employee emp2 = createEmployee("Bob", "Tester", 3);
        Incompatibility inc = new Incompatibility(emp1, emp2);
        assertEquals(emp1, inc.getIncompatibilityEmployee1());
    }

    @Test
    public void getIncompatibilityEmployee2_returnsSecondEmployee() {
        Employee emp1 = createEmployee("Alice", "Programmer", 4);
        Employee emp2 = createEmployee("Bob", "Tester", 3);
        Incompatibility inc = new Incompatibility(emp1, emp2);
        assertEquals(emp2, inc.getIncompatibilityEmployee2());
    }

    @Test
    public void getIncompatibility_returnsBothEmployees() {
        Employee emp1 = createEmployee("Alice", "Programmer", 4);
        Employee emp2 = createEmployee("Bob", "Tester", 3);
        Incompatibility inc = new Incompatibility(emp1, emp2);
        assertNotNull(inc.getIncompatibility());
        assertEquals(2, inc.getIncompatibility().size());
        assertTrue(inc.getIncompatibility().contains(emp1));
        assertTrue(inc.getIncompatibility().contains(emp2));
    }
}
