package prog3.tp.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.UIManager;
import prog3.tp.presenter.Presenter;

public class Window implements View, ToolbarListener {
    private Presenter _presenter;
    private JFrame _frame;
    private Toolbar _toolbar;
    private TabMenu _tabs;
    private Font _font;

    public Window() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme");
        } catch (Exception e) {
            System.out.println("Error setting native look: " + e);
        }
        loadFont();
        initialize();
    }

    private void loadFont() {
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/jetbrains.ttf");
            _font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(_font);
        } catch (Exception e) {
            _font = new Font("SansSerif", Font.PLAIN, 14);
        }
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
        _toolbar.setFontForButtons(_font);
    }

    private void setUpTabs() {
        _tabs = new TabMenu();
    }

    @Override
    public void onEmployeeAdded(String name, String role, int calification) {
        _presenter.addEmployee(name, role, calification);
    }

    @Override
    public void onIncompatibilityAdded(String firstEmployee, String secondEmployee) {
        // TODO: implement logic
    }

    @Override
    public void onRequirementsAdded(int arquitectAmount, int programmerAmount, int teamLeaderAmount, int testerAmount) {
        // TODO: implement logic
    }

    @Override
    public void setPresenter(Presenter presenter) {
        _presenter = presenter;
    }

    @Override
    public void update() {
        // TODO: implement logic
    }
}
