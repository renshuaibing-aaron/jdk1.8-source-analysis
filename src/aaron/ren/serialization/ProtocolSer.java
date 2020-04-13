/*
package aaron.ren.serialization;

import java.util.Arrays;

public class ProtocolSer {

    public static void main(String[] args) {
        //��ȡһ��PBPlayer�Ĺ�����
        PlayerModule.PBPlayer.Builder builder = PlayerModule.PBPlayer.newBuilder();
        //��������
        builder.setPlayerId(101).setAge(20).setName("neil").addSkills(1001);
        //���������
        PlayerModule.PBPlayer player = builder.build();
        //���л����ֽ�����
        byte[] byteArray = player.toByteArray();
        System.out.println(Arrays.toString(byteArray));
        return byteArray;
    }


    public static void toPlayer(byte[] bs) throws Exception{
        PlayerModule.PBPlayer player = PlayerModule.PBPlayer.parseFrom(bs);
        System.out.println("playerId:" + player.getPlayerId());
        System.out.println("age:" + player.getAge());
        System.out.println("name:" + player.getName());
        System.out.println("skills:" + (Arrays.toString(player.getSkillsList().toArray())));
    }

}
*/
