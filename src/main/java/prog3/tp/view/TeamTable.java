package prog3.tp.view;

import java.util.List;
import prog3.tp.model.Employee;

class TeamTable extends DataTable {
    private static String[] COLUMN_NAMES = {"Name", "Role", "Calification"};

    TeamTable() {
        super(COLUMN_NAMES);
    }

    void setTeam(List<Employee> team) {
        this.clear();

        // TODO: refactor this so it not depends so much of the model the view.
        // we have coupling with Role, better not to add more
        for (Employee e : team) {
            Object[] row = {e.getName(), e.getRole().toString(), e.getCalification()};
            this.addNewRow(row);
        }
    }
}
