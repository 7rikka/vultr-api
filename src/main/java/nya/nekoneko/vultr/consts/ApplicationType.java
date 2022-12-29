package nya.nekoneko.vultr.consts;

/**
 * @author Ho
 */
public enum ApplicationType {
    /**
     *
     */
    ALL("all"),
    /**
     *
     */
    MARKETPLACE("marketplace"),
    /**
     *
     */
    ONE_CLICK("one-click");

    private final String value;

    private ApplicationType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
