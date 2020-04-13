package aaron.ren.pattern.chainofresponsibility;

public class LimitRuleHandler extends RuleHandler {
    @Override
    public void apply(Context context) {
       // int remainedTimes = activityService.queryRemainedTimes(context); // ��ѯʣ�ཱƷ
        int remainedTimes = 1;
        if (remainedTimes > 0) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("������̫���ˣ���Ʒ��������");
        }
    }
}
