/*
package aaron.ren.pragram.leetcode.test;

*/
/**
 *大写的钱转成数字表示，如输入一万零五百，输出10500
 *//*

public class MoneyChange {

    public static void main(String[] args) {

        String money = "贰拾伍元伍角壹分";

        System.out.println(changeMoney(money));

    }

    public static long changeMoney(String money) {

        long sum = 0;

        if (-1 == money.indexOf("元")) {//如果没有超过壹元钱就返回

            return sum;

        }

        String newMoney = money.substring(0, money.indexOf("元") + 1);//去除元后面的部分

        //分割成如下形式：数字仟数字佰数字拾数字。 然后调用分割后的和，之后在求整个的和。

        if (-1 != newMoney.indexOf("亿")) {

            String[] spits = newMoney.split("亿");//如果存在亿的部分就分割出含亿的部分

            sum += getSubMoney(spits[0], "亿");

            newMoney = spits[1];

        }

        if (-1 != newMoney.indexOf("万")) {//如果存在万的部分就分割出含万的部分

            String[] spits = newMoney.split("万");

            sum += getSubMoney(spits[0], "万");

            newMoney = spits[1];

        }

        if (!newMoney.equals("元")) {//如果分割的最后不只有元了，就求不上万部分的和

            sum += getSubMoney(newMoney.substring(0, newMoney.indexOf("元")), "元");

        }

        return sum;

    }

    private static long getSubMoney(String money, String unit) {//分割后返回分割部分的数和

        long subSum = 0;//存储子部分的和

        char[] moneys = money.toCharArray();

        int rememberInt = 0;//用来记录数字部分

        for (int i = 0; i < moneys.length; i++) {

            int increament = increateNum(moneys[i]);

            if (-1 == increament) {

                rememberInt = getMoney(moneys[i]);

                if (i == (moneys.length - 1)) {

                    subSum += getMoney(moneys[i]);

                }

            } else if (increament > 0) {//如果是单位：拾，仟，佰.。乘以对应的进制

                subSum += increament * rememberInt;

            } else {

                continue;

            }

        }

        return subSum * getUnit(unit);

    }

    private static long getUnit(String unit) {//根据单位返回零的个数

        if (unit.equals("元")) {

            return 1;

        } else if (unit.equals("万")) {

            return 10000;

        } else if (unit.equals("亿")) {

            return 100000000;

        } else {

            return -1;

        }

    }

    private static int increateNum(char c) {//根据单位返回要乘的数

        switch (c) {

            case '拾': {

                return 10;

            }
            case '佰': {

                return 100;

            }
            case '仟': {

                return 1000;

            }
            case '零': {

                return 0;

            }

            default: {

                return -1;

            }

        }

    }

    private static int getMoney(char m) {//返回大写人民币所对应的数字

        switch (m) {

            case '壹': {

                return 1;

            }
            case '贰': {

                return 2;

            }
            case '叁': {

                return 3;

            }
            case '肆': {

                return 4;

            }
            case '伍': {

                return 5;

            }
            case '陆': {

                return 6;

            }
            case '柒': {

                return 7;

            }
            case '捌': {

                return 8;

            }
            case '玖': {

                return 9;

            }
            default: {

                return 0;

            }

        }
    }
}*/
