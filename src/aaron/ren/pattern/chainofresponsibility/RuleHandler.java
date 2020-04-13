package aaron.ren.pattern.chainofresponsibility;

public abstract class RuleHandler {
    // ºó¼Ì½Úµã
    protected RuleHandler successor;

    public abstract void apply(Context context);

    public void setSuccessor(RuleHandler successor) {
        this.successor = successor;
    }

    public RuleHandler getSuccessor() {
        return successor;
    }
}