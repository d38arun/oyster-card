package oyster.enums;

public enum Mode {
    BUS("Bus"),
    TUBE("Tube");

    private String mode;

    private Mode(String mode)
    {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
