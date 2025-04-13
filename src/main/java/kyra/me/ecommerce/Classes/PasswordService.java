package kyra.me.ecommerce.Classes;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordService {
    // Work factor (cost) - higher is more secure but slower
    private static final int WORKLOAD = 12;

    /**
     * Hashes a password for secure storage
     * @param plainPassword The raw password text
     * @return Secure hashed password (60 characters)
     */
    public static String hashPassword(String plainPassword) {
        // Generate a random salt and hash the password
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(WORKLOAD));
    }

    /**
     * Verifies a password against a stored hash
     * @param plainPassword The password to check
     * @param storedHash The stored hashed password
     * @return true if passwords match
     */
    public static boolean verifyPassword(String plainPassword, String storedHash) {
        if (storedHash == null || !storedHash.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash format");
        }
        return BCrypt.checkpw(plainPassword, storedHash);
    }
}