package aaron.ren.pattern.observer;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
