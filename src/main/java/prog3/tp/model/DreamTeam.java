package prog3.tp.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import prog3.tp.presenter.Observer;

public class DreamTeam implements Model {
    private Observer _observer;
    private List<Employee> employees;
    private List<Incompatibility> List_incompatibility;
    private EnumMap<Role, Integer> _requirements = new EnumMap<>(Role.class);

    public DreamTeam() {
        employees = new ArrayList<>();
        List_incompatibility = new ArrayList<>();
    }

    public void addEmployee(String name, String role, int calification) {
        Employee newEmployee = new Employee(name, stringToRole(role), calification);
        employees.add(newEmployee);

        _observer.update();
    }
    
    
    public void addIncompatibility(Employee E1,Employee E2) {
    	Incompatibility incompatibles = new Incompatibility(E1,E2);
    	List_incompatibility.add(incompatibles);
    }
    
    public void addRequirement(Role role, int cant) {
       if(cant < 0) {
           throw new IllegalArgumentException();
       }
       
       if(_requirements.containsKey(role)) {
           _requirements.replace(role, cant);
           return;
       }
       
       _requirements.put(role, cant);
    }
    
    public void setRequirement(Role role, int count) {
        if (_requirements == null)
            _requirements = new EnumMap<>(Role.class);
        
        if(count < 0) {
            throw new IllegalArgumentException();
        }
        
        if(_requirements.containsKey(role)) {
           throw new IllegalArgumentException();
        }
        
        
        _requirements.put(role, count);  
    }
    

    private Role stringToRole(String role) {
        Role r;
        switch (role) {
            case "Team Leader":
                r = Role.TEAM_LEADER;
                break;
            case "Arquitect":
                r = Role.ARQUITECT;
                break;
            case "Programmer":
                r = Role.PROGRAMMER;
                break;
            case "Tester":
                r = Role.TESTER;
                break;
            default:
                r = Role.UNKNOWN;
                break;
        }

        return r;
    }
    

    @Override
    public void addObserver(Observer observer) {
        _observer = observer;
    }
}
