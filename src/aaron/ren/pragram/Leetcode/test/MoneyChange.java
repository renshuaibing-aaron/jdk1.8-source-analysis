/*
package aaron.ren.pragram.leetcode.test;

*/
/**
 *��д��Ǯת�����ֱ�ʾ��������һ������٣����10500
 *//*

public class MoneyChange {

    public static void main(String[] args) {

        String money = "��ʰ��Ԫ���Ҽ��";

        System.out.println(changeMoney(money));

    }

    public static long changeMoney(String money) {

        long sum = 0;

        if (-1 == money.indexOf("Ԫ")) {//���û�г���ҼԪǮ�ͷ���

            return sum;

        }

        String newMoney = money.substring(0, money.indexOf("Ԫ") + 1);//ȥ��Ԫ����Ĳ���

        //�ָ��������ʽ������Ǫ���ְ�����ʰ���֡� Ȼ����÷ָ��ĺͣ�֮�����������ĺ͡�

        if (-1 != newMoney.indexOf("��")) {

            String[] spits = newMoney.split("��");//��������ڵĲ��־ͷָ�����ڵĲ���

            sum += getSubMoney(spits[0], "��");

            newMoney = spits[1];

        }

        if (-1 != newMoney.indexOf("��")) {//���������Ĳ��־ͷָ������Ĳ���

            String[] spits = newMoney.split("��");

            sum += getSubMoney(spits[0], "��");

            newMoney = spits[1];

        }

        if (!newMoney.equals("Ԫ")) {//����ָ�����ֻ��Ԫ�ˣ��������򲿷ֵĺ�

            sum += getSubMoney(newMoney.substring(0, newMoney.indexOf("Ԫ")), "Ԫ");

        }

        return sum;

    }

    private static long getSubMoney(String money, String unit) {//�ָ�󷵻طָ�ֵ�����

        long subSum = 0;//�洢�Ӳ��ֵĺ�

        char[] moneys = money.toCharArray();

        int rememberInt = 0;//������¼���ֲ���

        for (int i = 0; i < moneys.length; i++) {

            int increament = increateNum(moneys[i]);

            if (-1 == increament) {

                rememberInt = getMoney(moneys[i]);

                if (i == (moneys.length - 1)) {

                    subSum += getMoney(moneys[i]);

                }

            } else if (increament > 0) {//����ǵ�λ��ʰ��Ǫ����.�����Զ�Ӧ�Ľ���

                subSum += increament * rememberInt;

            } else {

                continue;

            }

        }

        return subSum * getUnit(unit);

    }

    private static long getUnit(String unit) {//���ݵ�λ������ĸ���

        if (unit.equals("Ԫ")) {

            return 1;

        } else if (unit.equals("��")) {

            return 10000;

        } else if (unit.equals("��")) {

            return 100000000;

        } else {

            return -1;

        }

    }

    private static int increateNum(char c) {//���ݵ�λ����Ҫ�˵���

        switch (c) {

            case 'ʰ': {

                return 10;

            }
            case '��': {

                return 100;

            }
            case 'Ǫ': {

                return 1000;

            }
            case '��': {

                return 0;

            }

            default: {

                return -1;

            }

        }

    }

    private static int getMoney(char m) {//���ش�д���������Ӧ������

        switch (m) {

            case 'Ҽ': {

                return 1;

            }
            case '��': {

                return 2;

            }
            case '��': {

                return 3;

            }
            case '��': {

                return 4;

            }
            case '��': {

                return 5;

            }
            case '½': {

                return 6;

            }
            case '��': {

                return 7;

            }
            case '��': {

                return 8;

            }
            case '��': {

                return 9;

            }
            default: {

                return 0;

            }

        }
    }
}*/
