package aaron.ren.classloader;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESInstance {

    private static String ALGORITHM = "DESede";

    /**
     * º”√‹
     *
     * @param key
     * @param src
     * @return
     */
    public static byte[] enCode(byte[] key, byte[] src) {

        byte[] value = null;
        SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            value = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * Ω‚√‹
     *
     * @param key
     * @param src
     * @return
     */
    public static byte[] deCode(byte[] key, byte[] src) {
        byte[] value = null;
        SecretKey deskey = new SecretKeySpec(key, ALGORITHM);

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            value = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }
}