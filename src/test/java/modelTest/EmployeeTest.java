package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import prog3.tp.model.DreamTeam;
import prog3.tp.model.Employee;

public class EmployeeTest {
    private Employee createEmployee(String name, String role, int calification) {
        DreamTeam dt = new DreamTeam();
        dt.addObserver(() -> {});
        dt.addEmployee(name, role, calification);
        return dt.getEmployees().get(0);
    }

    @Test
    public void getName_returnsName() {
        Employee emp = createEmployee("Alice", "Programmer", 4);
        assertEquals("Alice", emp.getName());
    }

    @Test
    public void getCalification_returnsCalification() {
        Employee emp = createEmployee("Bob", "Tester", 3);
        assertEquals(3, emp.getCalification());
    }

    @Test
    public void equals_sameAttributes_returnsTrue() {
        Employee emp1 = createEmployee("Charlie", "Arquitect", 5);
        Employee emp2 = createEmployee("Charlie", "Arquitect", 5);
        assertEquals(emp1, emp2);
    }

    @Test
    public void equals_differentName_returnsFalse() {
        Employee emp1 = createEmployee("Alice", "Programmer", 4);
        Employee emp2 = createEmployee("Bob", "Programmer", 4);
        assertNotEquals(emp1, emp2);
    }

    @Test
    public void equals_differentRole_returnsFalse() {
        Employee emp1 = createEmployee("Alice", "Programmer", 4);
        Employee emp2 = createEmployee("Alice", "Tester", 4);
        assertNotEquals(emp1, emp2);
    }

    @Test
    public void equals_differentCalification_returnsFalse() {
        Employee emp1 = createEmployee("Alice", "Programmer", 4);
        Employee emp2 = createEmployee("Alice", "Programmer", 5);
        assertNotEquals(emp1, emp2);
    }

    @Test
    public void equals_null_returnsFalse() {
        Employee emp = createEmployee("Alice", "Programmer", 4);
        assertFalse(emp.equals(null));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        Employee emp = createEmployee("Alice", "Programmer", 4);
        assertFalse(emp.equals("not an employee"));
    }

    @Test
    public void equals_sameInstance_returnsTrue() {
        Employee emp = createEmployee("Alice", "Programmer", 4);
        assertTrue(emp.equals(emp));
    }

    @Test
    public void hashCode_equalObjects_haveSameHashCode() {
        Employee emp1 = createEmployee("Charlie", "Arquitect", 5);
        Employee emp2 = createEmployee("Charlie", "Arquitect", 5);
        assertEquals(emp1.hashCode(), emp2.hashCode());
    }

    @Test
    public void hashCode_differentObjects_haveDifferentHashCode() {
        Employee emp1 = createEmployee("Alice", "Programmer", 4);
        Employee emp2 = createEmployee("Bob", "Tester", 3);
        assertNotEquals(emp1.hashCode(), emp2.hashCode());
    }
}
