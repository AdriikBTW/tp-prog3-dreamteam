package prog3.tp.view;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.UIManager;
import prog3.tp.model.Role;
import prog3.tp.model.SolverResult;
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
    public void onEmployeeAdded(String name, String role, int calification) {
        _presenter.addEmployee(name, role, calification);
        _tabs.addNewEmployee(name, role, calification);
    }

    @Override
    public List<String> getEmployeeNames() {
        return _presenter.getEmployeeNames();
    }

    @Override
    public void onIncompatibilityAdded(String firstName, String secondName) {
        _presenter.onIncompatibilityAdded(firstName, secondName);
        _tabs.addNewIncompatibility(firstName, secondName);
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
        // TODO: implement logic
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
