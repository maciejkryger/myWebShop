package pl.javarun.mywebshop.util;


import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Author: Ian Gallagher <igallagher@securityinnovation.com>
 * <p>
 * This code utilizes jBCrypt, which you need installed to use.
 * jBCrypt: http://www.mindrot.org/projects/jBCrypt/
 */

public class PasswordUtil {

    public static String hashPassword(String password_plaintext) {

        // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value.
        int workload = 12;

        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password_plaintext, salt);

    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified;

        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
}

