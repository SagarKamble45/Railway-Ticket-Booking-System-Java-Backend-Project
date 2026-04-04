package ticket.booking.util;
import org.mindrot.jbcrypt.BCrypt;
public class UserServiceUtil {

    public static String hashPassword (String normalPassword) {
        return BCrypt.hashpw(normalPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword (String normalPassword, String hashedPassword) {
        return BCrypt.checkpw(normalPassword, hashedPassword);
    }
}
