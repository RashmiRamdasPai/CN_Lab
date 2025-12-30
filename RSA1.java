import java.util.*;
import java.security.spec.*;
import javax.crypto.Cipher;
import java.security.*;
public class RSA{
        public static KeyPair generateKeyPair() throws Exception{
                KeyPairGenerator keygen=KeyPairGenerator.getInstance("RSA");
                keygen.initialize(2048);
                return keygen.generateKeyPair();
        }
        public static String encrypt(String message,PublicKey publicKey) throws Exception{
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] encryptedBytes=cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }

        public static String decrypt(String encryptedmessage,PrivateKey privateKey) throws Exception{
                Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.DECRYPT_MODE,privateKey);
                byte[] decryptedBytes=cipher.doFinal(Base64.getDecoder().decode(encryptedmessage));
                return new String(decryptedBytes);
        }
        //Convert base64 string to public key
        public static PublicKey getpublickeyfrombase64(String base64publickey) throws Exception{
                byte[] keyBytes=Base64.getDecoder().decode(base64publickey);
                X509EncodedKeySpec spec=new X509EncodedKeySpec(keyBytes);
                KeyFactory keyfactory=KeyFactory.getInstance("RSA");
                return keyfactory.generatePublic(spec);
        }
        public static PrivateKey getprivatekeyfrombase64(String base64privatekey) throws Exception{
                byte[] keybytes=Base64.getDecoder().decode(base64privatekey);
                PKCS8EncodedKeySpec spec=new PKCS8EncodedKeySpec(keybytes);
                KeyFactory keyfactory=KeyFactory.getInstance("RSA");
                return keyfactory.generatePrivate(spec);
        }
        public static void main(String args[]){
                Scanner sc=new Scanner(System.in);
                try{
                        KeyPair keypair=generateKeyPair();
                        String publickeybase64=Base64.getEncoder().encodeToString(keypair.getPublic().getEncoded());
                        String privatekeybase64=Base64.getEncoder().encodeToString(keypair.getPrivate().getEncoded());
                        System.out.println("Generated Public key:");
                        System.out.println(publickeybase64);
                        System.out.println("Generated Private key:");
                        System.out.println(privatekeybase64);



                        System.out.println("Enter message to encrypt");
                                String msg=sc.nextLine().trim();
                                System.out.println("Enter the public key");
                                String pubkeyinput=sc.nextLine().trim();
                                PublicKey publickey=getpublickeyfrombase64(pubkeyinput);
                                String encryptedMessage=encrypt(msg,publickey);;
                                System.out.println("Encrypted Message:");
                                System.out.println(encryptedMessage);


                                System.out.println("Enter encrypted message:");
                                String encryptmsg=sc.nextLine().trim();
                                System.out.println("Enter the private key:");
                                String privkeyinput=sc.nextLine().trim();
                                PrivateKey privatekey=getprivatekeyfrombase64(privkeyinput);
                                String decryptedMessage=decrypt(encryptmsg,privatekey);

                                System.out.println(decryptedMessage);
                        }
                catch(Exception e){
                        e.printStackTrace();
                }}}
