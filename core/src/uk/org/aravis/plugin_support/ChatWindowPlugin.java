package uk.org.aravis.plugin_support;

import java.util.Collection;

/**
 * Define a plugin_support that provides a chat window for other plugins to use.
 * <p/>
 * This does not provide anything but a gui component.
 * User: kimball
 * Date: Jul 29, 2007
 * Time: 7:33:26 PM
 */
public interface ChatWindowPlugin extends Plugin {
    /**
     * Create a new chat window with no user list.
     *
     * @return window created.
     */
    public ChatWindow newWindow();

    /**
     * Create new window with a userlist.
     * <p/>
     * Initial userlist is a map of users.
     *
     * @param users Initial userlist.
     * @return Window created
     */
    public ChatWindowUserList newWindowUserList(Collection<User> users);

    /**
     * Define the elements of a chat window to return to the plugin_support user.
     */
    public interface ChatWindow {
        /**
         * Unique id to refer to plugin_support.
         *
         * @return ID of window.
         */
        public abstract String getId();

        /**
         * Add line to chat window.
         *
         * @param user  User to display text as from.
         * @param text  text to display.
         * @param flags flags defining display parameters.
         */
        public abstract void addLine(User user, String text, int flags);

        public static int TEXT_EMOTE = 1;
        public static int TEXT_PEMOTE = 2;

        public static String SYSTEM_ID = "__SYSTEM";
        public static String PLUGIN_ID = "__PLUGIN";

    }

    public interface ChatWindowUserList extends ChatWindow {
        public abstract void addUser(User user);

        public abstract void removeUser(User user);
    }


}
