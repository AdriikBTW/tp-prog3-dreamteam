package prog3.tp.model;

public enum Role {
    TEAM_LEADER("Team Leader"),
    ARQUITECT("Arquitect"),
    PROGRAMMER("Programmer"),
    TESTER("Tester");

    private final String _value;

    Role(String value) {
        _value = value;
    }

    public static Role toRole(String role) {
        for (Role r : values())
            if (r._value.equalsIgnoreCase(role))
                    return r;

        return null;
    }

    @Override
    public String toString()
    {
        return _value;
    }

}
