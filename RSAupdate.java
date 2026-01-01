import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import java.util.Base64;
public class RSAupdate{

public static KeyPair generatekeypair() throws Exception{
        KeyPairGenerator keygen=KeyPairGenerator.getInstance("RSA");
        keygen.initialize(2048);
        return keygen.generateKeyPair();
}

public static String encrypt(String message,PublicKey publickey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publickey);
        byte[] encryptedbytes=cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedbytes);
}

public static String decrypt(String encryptedmessage,PrivateKey privatekey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privatekey);
        byte[] decryptedbytes=cipher.doFinal(Base64.getDecoder().decode(encryptedmessage));
        return new String(decryptedbytes);
}

public static PublicKey getpublickeyfrombase64(String publickey64) throws Exception{
        byte[] keybytes=Base64.getDecoder().decode(publickey64);
        X509EncodedKeySpec spec=new X509EncodedKeySpec(keybytes);
        KeyFactory keyfactory =KeyFactory.getInstance("RSA");
        return keyfactory.generatePublic(spec);
}

public static PrivateKey getprivatekeyfrombase64(String privatekey64) throws Exception{
        byte[] keybytes=Base64.getDecoder().decode(privatekey64);
        PKCS8EncodedKeySpec spec= new PKCS8EncodedKeySpec(keybytes);
        KeyFactory keyfactory=KeyFactory.getInstance("RSA");
        return keyfactory.generatePrivate(spec);
}
public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        try{
                KeyPair keypair=generatekeypair();
                String publickey64=Base64.getEncoder().encodeToString(keypair.getPublic().getEncoded());
                String privatekey64=Base64.getEncoder().encodeToString(keypair.getPrivate().getEncoded());
                System.out.println("Public Key: "+publickey64);
                System.out.println("Private Key: "+privatekey64);
                System.out.println("Enter the message to encrypt");
                String message=sc.next().trim();
                System.out.println("Enter the public key:");
                String pubkeyinput=sc.next().trim();
                PublicKey publickey = getpublickeyfrombase64(pubkeyinput);
                System.out.println("Encrypted message:");
                String encryptedmessage=encrypt(message,publickey);
                System.out.println(encryptedmessage);
                System.out.println("decryption:");
                System.out.println("Enter the encrypted Message:");
                String encryptmsg=sc.next().trim();
                System.out.println("Enter private key:");
                String privatekeyinput=sc.next().trim();
                PrivateKey privatekey=getprivatekeyfrombase64(privatekeyinput);
                String dcrptmessage=decrypt(encryptmsg,privatekey);
                System.out.println("Decrypted Message:");
                System.out.println(dcrptmessage);
        }
        catch(Exception e){
                e.printStackTrace();
        }}
}
~
