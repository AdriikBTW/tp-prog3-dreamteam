package prog3.tp.view;

interface ToolbarListener {
    void onEmployeeAdded(String name, String role, int calification);

    void onIncompatibilityAdded();

    void onRequirementsAdded(
            int arquitectAmount, int programmerAmount, int teamLeaderAmount, int testerAmount);
}
