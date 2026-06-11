package prog3.tp.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import prog3.tp.model.Role;

class EmployeeDialogPane extends ToolbarDialogPane {
    private JTextField _name;
    private JComboBox<String> _role;
    private JComboBox<String> _calification;
    private JTextField _filePath;
    private JButton _browseButton;

    EmployeeDialogPane(String title) {
        super(title);

        initComponents();
        addComponents();
    }

    @Override
    void initComponents() {
        initFileChooserField();
        initNameField();
        initRoleField();
        initCalificationField();
    }

    private void initFileChooserField() {
        _filePath = new JTextField();
        _filePath.setEditable(false);
        _browseButton = new JButton("");
        _browseButton.addActionListener(e -> openFileChooser());
    }

    private void openFileChooser() {
        JFileChooser fc = new JFileChooser();

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            _filePath.setText(fc.getSelectedFile().getAbsolutePath());
    }

    private void initNameField() {
        _name = new JTextField();
    }

    private void initRoleField() {
        String[] items = new String[Role.values().length];

        for (int i = 0; i < Role.values().length; i++) items[i] = Role.values()[i].toString();

        _role = new JComboBox<>(items);
    }

    private void initCalificationField() {

        String[] items = {
            "★", "★★", "★★★", "★★★★", "★★★★★",
        };
        _calification = new JComboBox<>(items);
    }

    @Override
    void addComponents() {
        this.addComponent("Name: ", _name);
        this.addComponent("Role: ", _role);
        this.addComponent("Calification: ", _calification);
        addFileChooser();
    }

    private void addFileChooser() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(_filePath, BorderLayout.CENTER);
        panel.add(_browseButton, BorderLayout.EAST);
        this.addComponent("Photo: ", panel);
    }

    public String getName() {
        return _name.getText();
    }

    public String getRole() {
        return (String) _role.getSelectedItem();
    }

    public int getCalification() {
        return ((String) _calification.getSelectedItem()).length();
    }

    public String getImagePath() {
        return _filePath.getText();
    }
}
