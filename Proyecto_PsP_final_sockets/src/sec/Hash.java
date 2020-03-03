package sec;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    private File f;
    public Hash() {
      f = new File("src/sec/sal");
    }
    public String getFpath(){
        return f.getAbsolutePath();
    }

    public String get_SHA_512_SecurePassword(String passwordToHash){
        String generatedPassword = null;
        String salt;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            salt = br.readLine();
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException | FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
