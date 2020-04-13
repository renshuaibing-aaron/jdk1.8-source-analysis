package aaron.ren.pattern.chainofresponsibility;

public class NewUserRuleHandler extends RuleHandler {
    @Override
    public void apply(Context context) {
        if (context.isNewUser()) {
            // ����к�̽ڵ�Ļ���������ȥ
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("�û�������û�����");
        }
    }
}