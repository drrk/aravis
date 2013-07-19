package uk.org.aravis.jadis;

import uk.org.aravis.plugin_support.User;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: Aug 16, 2007
 * Time: 4:56:57 PM
 */
public class JadisUser extends User {
    public JadisUser(String id) {
        super(id);
    }

    public JadisUser(String id, String displayName) {
        super(id, displayName);
    }
}
