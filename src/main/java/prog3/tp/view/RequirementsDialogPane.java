package prog3.tp.view;

import java.util.EnumMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import prog3.tp.model.Role;

class RequirementsDialogPane extends ToolbarDialogPane {
    private Map<Role, JSpinner> _spinners;

    RequirementsDialogPane(String title) {
        super(title);

        initComponents();
        addComponents();
    }

    @Override
    void initComponents() {
        _spinners = new EnumMap<>(Role.class);

        for (Role role : Role.values())
            _spinners.put(role, createSpinner());
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
        for (Map.Entry<Role, JSpinner> entry: _spinners.entrySet()) {
            Role role = entry.getKey();
            JSpinner spinner = entry.getValue();
            this.addComponent("Amount of " + role + ": ", spinner);
        }
    }

    private void addComponent(String labelText, JComponent component) {
        this.add(new JLabel(labelText));
        this.add(component);
    }

    Map<Role, Integer> getRequirements() {
        Map<Role, Integer> requirements = new EnumMap<>(Role.class);

        for (Map.Entry<Role, JSpinner> entry: _spinners.entrySet()) {
            Role role = entry.getKey();
            int amount = (int) entry.getValue().getValue();
            requirements.put(role, amount);
        }

        return requirements;
    }
}
