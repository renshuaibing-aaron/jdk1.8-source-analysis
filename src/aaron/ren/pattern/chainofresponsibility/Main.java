package aaron.ren.pattern.chainofresponsibility;

public class Main {
    public static void main(String[] args) {
        RuleHandler newUserHandler = new NewUserRuleHandler();
        RuleHandler locationHandler = new LocationRuleHandler();
        RuleHandler limitHandler = new LimitRuleHandler();

        // ���豾�λ��У������ͽ�Ʒ��������У�������û�
        locationHandler.setSuccessor(limitHandler);

        Context  context=new Context();
        locationHandler.apply(context);
    }
}
