package util;

import com.tw.core.Exceptions.P2pException;
import com.tw.core.User;

/**
 * Created by Wenjie Chen on 12/29/14.
 */
public class Verify {
    public static void verifyUser(User user) throws P2pException {
        if (user.getEmail().isEmpty() || user.getEmail().length() == 0) {
            throw new P2pException("100", "Invalid E-Mail");
        }

        if (user.getPassword().isEmpty() || user.getPassword().length() == 0) {
            throw new P2pException("100", "Invalid Password");
        }

        if (user.getName().isEmpty() || user.getName().length() == 0) {
            throw new P2pException("100", "Invalid name");
        }
    }
}
