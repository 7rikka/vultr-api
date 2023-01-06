package nya.nekoneko.vultr.consts;

/**
 * @author Ho
 */

public enum VultrPlanType {
    /**
     *
     */
    ALL("all"),
    /**
     *
     */
    VC2("vc2"),
    /**
     *
     */
    VDC("vdc"),
    /**
     *
     */
    VHF("vhf"),
    /**
     *
     */
    VHP("vhp"),
    /**
     *
     */
    VOC("voc"),
    /**
     *
     */
    VOC_G("voc-g"),
    /**
     *
     */
    VOC_C("voc-c"),
    /**
     *
     */
    VOC_M("voc-m"),
    /**
     *
     */
    VOC_S("voc-s"),
    /**
     *
     */
    VCG("vcg");
    private final String value;

    VultrPlanType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
