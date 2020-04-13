package aaron.ren.pattern.chainofresponsibility;

public class LocationRuleHandler extends RuleHandler {
    @Override
    public void apply(Context context) {
       // boolean allowed = activityService.isSupportedLocation(context.getLocation);
        boolean allowed = true;
        if (allowed) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("�ǳ���Ǹ�������ڵĵ����޷����뱾�λ");
        }
    }
}
