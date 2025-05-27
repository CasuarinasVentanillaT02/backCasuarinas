package com.systems.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class CryptoJSDecrypt {

    private static byte[][] deriveKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] key = new byte[keyLength];
        byte[] iv = new byte[ivLength];
        byte[] concatenatedHashes = new byte[0];
        byte[] currentHash = new byte[0];
        int neededLength = keyLength + ivLength;
        while (concatenatedHashes.length < neededLength) {
            md5.update(currentHash);
            md5.update(password);
            md5.update(salt);
            currentHash = md5.digest();
            for (int i = 1; i < iterations; i++) {
                currentHash = md5.digest(currentHash);
            }
            byte[] newConcatenatedHashes = new byte[concatenatedHashes.length + currentHash.length];
            System.arraycopy(concatenatedHashes, 0, newConcatenatedHashes, 0, concatenatedHashes.length);
            System.arraycopy(currentHash, 0, newConcatenatedHashes, concatenatedHashes.length, currentHash.length);
            concatenatedHashes = newConcatenatedHashes;
        }
        System.arraycopy(concatenatedHashes, 0, key, 0, keyLength);
        System.arraycopy(concatenatedHashes, keyLength, iv, 0, ivLength);
        return new byte[][] {key, iv};
    }

    public static String decrypt(String encryptedBase64, String password) throws Exception {
        byte[] encrypted = Base64.getDecoder().decode(encryptedBase64);

        byte[] saltHeader = Arrays.copyOfRange(encrypted, 0, 8);
        String saltHeaderString = new String(saltHeader);
        if (!"Salted__".equals(saltHeaderString)) {
            throw new IllegalArgumentException("Invalid salt header");
        }

        byte[] salt = Arrays.copyOfRange(encrypted, 8, 16);

        final int keyLength = 32; // 256 bits
        final int ivLength = 16;  // 128 bits
        byte[][] keyAndIV = deriveKeyAndIV(keyLength, ivLength, 1, salt, password.getBytes("UTF-8"));
        byte[] key = keyAndIV[0];
        byte[] iv = keyAndIV[1];

        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] ciphertext = Arrays.copyOfRange(encrypted, 16, encrypted.length);

        byte[] decrypted = cipher.doFinal(ciphertext);
        return new String(decrypted, "UTF-8");
    }
}
