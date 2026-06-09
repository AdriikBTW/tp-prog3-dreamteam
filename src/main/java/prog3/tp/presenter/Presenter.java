package prog3.tp.presenter;

import java.util.List;
import javax.swing.JOptionPane;
import prog3.tp.model.Employee;
import prog3.tp.model.Model;
import prog3.tp.view.IncompatibilitiesDialogPane;
import prog3.tp.view.View;

public class Presenter implements Observer {
    private final Model _model;
    private final View _view;

    public Presenter(Model model, View view) {
        _model = model;
        _view = view;

        _view.setPresenter(this);
        _model.addObserver(this);
    }

    public void addEmployee(String name, String role, int calification) {
        _model.addEmployee(name, role, calification);
    }

    public void onIncompatibilityAdded() {
        List<Employee> employees = _model.getEmployees();

        IncompatibilitiesDialogPane dialog = new IncompatibilitiesDialogPane("New incompatibility", employees);

        if (dialog.showDialog() == JOptionPane.OK_OPTION) {
            Employee first = dialog.getFirstEmployeeObject();
            Employee second = dialog.getSecondEmployeeObject();

            if (first == null || second == null) {
                JOptionPane.showMessageDialog(null, "Select two employees.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (first.equals(second)) {
                JOptionPane.showMessageDialog(null, "Select different employees.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            _model.addIncompatibility(first, second);
        }
    }

    @Override
    public void update() {
        _view.update();
    }
}
