package aaron.ren.pattern.chainofresponsibility;

public class Main {
    public static void main(String[] args) {
        RuleHandler newUserHandler = new NewUserRuleHandler();
        RuleHandler locationHandler = new LocationRuleHandler();
        RuleHandler limitHandler = new LimitRuleHandler();

        // 假设本次活动仅校验地区和奖品数量，不校验新老用户
        locationHandler.setSuccessor(limitHandler);

        Context  context=new Context();
        locationHandler.apply(context);
    }
}
