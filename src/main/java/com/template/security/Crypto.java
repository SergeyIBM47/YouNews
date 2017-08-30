package com.template.security;

import com.template.exception.CryptoException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Created by Igor Zadyra at 4/15/2015
 * @author Last modified by $Author: apitenko@rightandabove.com $author $ <br>
 * @author Last modified on $Date: 2015-04-22 18:53:00 +0300 (Wed, 22 Apr 2015) $date $ at revision $Revision: 107 $revision $ <br>
 */
@Component
public class Crypto {

    private String salt = "dae6f8b8d1124bb2";
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encodePassword(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean passwordMatches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * Encrypts input string using default cipher and password
     *
     * @param input
     * @param password
     * @return Hex encoded string of result of encrypting
     * @throws Exception
     */
    public String encryptHexString(String input, String password) throws CryptoException {
        try {
            return Encryptors.text(password, salt).encrypt(input);
        } catch (Exception e) {
            throw new CryptoException("Encryption error", e);
        }
    }

    /**
     * Decrypts the hex encoded string using default cipher and password
     *
     * @param hexInput
     * @param password
     * @return String
     * @throws Exception
     */
    public String decryptHexString(String hexInput, String password) throws CryptoException {
        try {
            return Encryptors.text(password, salt).decrypt(hexInput);
        } catch (Exception e) {
            throw new CryptoException("Decryption error", e);
        }
    }

}
