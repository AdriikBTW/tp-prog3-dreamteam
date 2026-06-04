package prog3.tp.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

class RequirementsDialogPane extends ToolbarDialogPane {
    private JSpinner _arquitectAmount;
    private JSpinner _programmerAmount;
    private JSpinner _teamLeaderAmount;
    private JSpinner _testerAmount;

    RequirementsDialogPane(String title) {
        super(title);

        initComponents();
        addComponents();
    }

    @Override
    void initComponents()
    {
        _arquitectAmount = createSpinner();
        _programmerAmount = createSpinner();
        _teamLeaderAmount = createSpinner();
        _testerAmount = createSpinner();
    }

    private JSpinner createSpinner() {
        int initialValue = 1;
        int minValue = 1;
        int step = 1;
        SpinnerNumberModel model = new SpinnerNumberModel(initialValue, minValue, null, step);
        return new JSpinner(model);
    }

    @Override
    void addComponents() {
        this.addComponent("Amount of Arquitects: ", _arquitectAmount);
        this.addComponent("Amount of Programmers: ", _programmerAmount);
        this.addComponent("Amount of Team Leaders: ", _teamLeaderAmount);
        this.addComponent("Amount of Testers: ", _testerAmount);
    }

    private void addComponent(String labelText, JComponent component) {
        this.add(new JLabel(labelText));
        this.add(component);
    }

    int getArquitectAmount() {
        return getSpinnerValue(_arquitectAmount);
    }

    int getProgrammerAmount() {
        return getSpinnerValue(_programmerAmount);
    }

    int getTeamLeaderAmount() {
        return getSpinnerValue(_teamLeaderAmount);
    }

    int getTesterAmount() {
        return getSpinnerValue(_testerAmount);
    }

    private int getSpinnerValue(JSpinner spinner) {
        return (int) spinner.getValue();
    }
}
