package prog3.tp.view;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.UIManager;
import prog3.tp.model.Role;
import prog3.tp.model.SolverResult;
import prog3.tp.presenter.EmployeeViewData;
import prog3.tp.presenter.IncompatibilityViewData;
import prog3.tp.presenter.Presenter;

public class Window implements View, ToolbarListener {
    private Presenter _presenter;
    private JFrame _frame;
    private Toolbar _toolbar;
    private TabMenu _tabs;

    public Window() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme");
        } catch (Exception e) {
            System.out.println("Error setting native look: " + e);
        }
        initialize();
    }

    public void setVisible(boolean visibility) {
        _frame.setVisible(visibility);
    }

    private void initialize() {
        setUpFrame();
        setUpToolbar();
        setUpTabs();

        _frame.add(_toolbar, BorderLayout.PAGE_START);
        _frame.add(_tabs);
    }

    private void setUpFrame() {
        _frame = new JFrame("Dream Team App");
        _frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setLayout(new BorderLayout());
    }

    private void setUpToolbar() {
        _toolbar = new Toolbar(this);
    }

    private void setUpTabs() {
        _tabs = new TabMenu();
    }

    @Override
    public void onEmployeeAdded(String name, String role, int calification, String imagePath) {
        _presenter.addEmployee(name, role, calification);
        ImageRegistry.register(name, imagePath);
    }

    @Override
    public List<String> getEmployeeNames() {
        return _presenter.getEmployees().stream()
            .map(e -> e.getName())
            .toList();
    }

    @Override
    public void onIncompatibilityAdded(String firstName, String secondName) {
        _presenter.onIncompatibilityAdded(firstName, secondName);
    }

    @Override
    public void onRequirementsAdded(Map<Role, Integer> requirements) {
        _presenter.addRequirements(requirements);
    }

    @Override
    public void onSolve() {
        _presenter.solve();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        _presenter = presenter;
    }

    @Override
    public void update() {
        _tabs.clearAll();

        updateEmployee();
        updateIncompatibilities();
    }

    private void updateEmployee() {
        for (EmployeeViewData e : _presenter.getEmployees())
            _tabs.addNewEmployee(e.getName(), e.getRole(), e.getCalification());
    }

    private void updateIncompatibilities() {
        for (IncompatibilityViewData i : _presenter.getIncompatibilities())
            _tabs.addNewIncompatibility(i.getFirstEmployee(), i.getSecondEmployee());
    }

    @Override
    public void showSolving() {
        _tabs.showSolving();
    }

    @Override
    public void showResult(SolverResult result) {
        _tabs.showTeamResult(result);
    }
}
