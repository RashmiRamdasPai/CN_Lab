import java.security.*;
import javax.crypto.Cipher;
import java.util.*;
import java.util.Base64;
import java.security.spec.*;

public class RSA {

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    public static String encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes()));
    }

    public static String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
    }

    public static PublicKey getPublicKeyFromBase64(String base64) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(base64);
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bytes));
    }

    public static PrivateKey getPrivateKeyFromBase64(String base64) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(base64);
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bytes));
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("Enter 'E' to Encrypt or 'D' to Decrypt:");
            String choice = sc.nextLine().trim().toUpperCase();

            if (choice.equals("E")) {

                KeyPair keyPair = generateKeyPair();
                String pubKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
                String privKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

                System.out.println("\nGenerated Public Key:\n" + pubKey);
                System.out.println("\nGenerated Private Key:\n" + privKey);

                System.out.println("\nEnter message to encrypt:");
                String message = sc.nextLine();

                PublicKey pk = getPublicKeyFromBase64(pubKey);
                String encrypted = encrypt(message, pk);

                System.out.println("\nEncrypted Message:\n" + encrypted);
            }

            else if (choice.equals("D")) {

                System.out.println("Enter encrypted message (Base64):");
                String encrypted = sc.nextLine().trim();

                System.out.println("Enter private key (Base64):");
                String privKeyInput = sc.nextLine().trim();

                PrivateKey privateKey = getPrivateKeyFromBase64(privKeyInput);
                String decrypted = decrypt(encrypted, privateKey);

                System.out.println("\nDecrypted Message:\n" + decrypted);
            }

            else {
                System.out.println("Invalid choice.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
