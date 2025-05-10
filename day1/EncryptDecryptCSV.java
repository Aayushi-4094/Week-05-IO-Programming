import java.io.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class EncryptDecryptCSV {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1234567890123456"; 
    
    public static void main(String[] args) throws Exception {
        String inputFile = "employee_data.csv";
        String outputFile = "encrypted_employee_data.csv";
        
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String encryptedSalary = encrypt(data[3]);
            String encryptedEmail = encrypt(data[4]);
            writer.write(data[0] + "," + data[1] + "," + data[2] + "," + encryptedSalary + "," + encryptedEmail + "\n");
        }
        
        reader.close();
        writer.close();
        
        BufferedReader encryptedReader = new BufferedReader(new FileReader(outputFile));
        BufferedWriter decryptedWriter = new BufferedWriter(new FileWriter("decrypted_employee_data.csv"));
        
        while ((line = encryptedReader.readLine()) != null) {
            String[] data = line.split(",");
            String decryptedSalary = decrypt(data[3]);
            String decryptedEmail = decrypt(data[4]);
            decryptedWriter.write(data[0] + "," + data[1] + "," + data[2] + "," + decryptedSalary + "," + decryptedEmail + "\n");
        }
        
        encryptedReader.close();
        decryptedWriter.close();
    }
    
    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    
    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}
