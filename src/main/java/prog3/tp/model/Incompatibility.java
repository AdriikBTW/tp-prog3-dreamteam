package prog3.tp.model;

import java.util.List;
import java.util.ArrayList;

public class Incompatibility {
	
	private Employee _employee1;
	private Employee _employee2;
	
	public Incompatibility(Employee E1,Employee E2) {
		_employee1 = E1;
		_employee2 = E2;
	}
	
	public List<Employee> getIncompatibility() {
		
		 List <Employee>  ret = new ArrayList<>();
		 
		 ret.add(_employee1);  
		 ret.add(_employee2);
		 
		 return ret;
	}
}
