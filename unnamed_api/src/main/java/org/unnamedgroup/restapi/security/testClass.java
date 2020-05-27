package org.unnamedgroup.restapi.security;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class testClass {

    // Everyone here is made for testing purposes

    // This is user to generate hashes froma  plain text string
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return(hashed_password);
    }

    // Test the authentication function
    public static void main(String[] args) throws SQLException {
        System.out.println(AutenticazioneResource.authenticate("admin", "admin"));
    }

}
