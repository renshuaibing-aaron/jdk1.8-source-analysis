package aaron.ren.pattern.chainofresponsibility;

public class LimitRuleHandler extends RuleHandler {
    @Override
    public void apply(Context context) {
       // int remainedTimes = activityService.queryRemainedTimes(context); // 查询剩余奖品
        int remainedTimes = 1;
        if (remainedTimes > 0) {
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("您来得太晚了，奖品被领完了");
        }
    }
}
