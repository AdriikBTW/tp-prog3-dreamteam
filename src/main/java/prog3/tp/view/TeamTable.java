package prog3.tp.view;

import java.util.List;
import prog3.tp.presenter.EmployeeViewData;

class TeamTable extends DataTable {
    private static String[] COLUMN_NAMES = {"Photo", "Name", "Role", "Calification"};

    TeamTable() {
        super(COLUMN_NAMES);
    }

    void setTeam(List<EmployeeViewData> team) {
        this.clear();

        for (EmployeeViewData e : team) {
            Object photo = ImageRegistry.getIcon(e.getName());
            Object[] row = {photo, e.getName(), e.getRole().toString(), e.getCalification()};
            this.addNewRow(row);
        }
    }
}
