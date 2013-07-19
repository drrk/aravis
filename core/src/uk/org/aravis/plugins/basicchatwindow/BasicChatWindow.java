package uk.org.aravis.plugins.basicchatwindow;

import uk.org.aravis.plugin_support.ChatWindowPlugin;
import uk.org.aravis.plugin_support.Plugin;
import uk.org.aravis.plugin_support.User;

import java.awt.*;
import java.util.Collection;

/**
 * User: kimball
 * Date: Jul 29, 2007
 * Time: 10:19:55 PM
 */
public class BasicChatWindow implements Plugin, ChatWindowPlugin {
    /**
     * Create a new chat window with no user list.
     *
     * @return window created.
     */
    public ChatWindow newWindow() {
        return null;
    }

    /**
     * Create new window with a userlist.
     * <p/>
     * Initial userlist is a map of users.
     *
     * @param users Initial userlist.
     * @return Window created
     */
    public ChatWindowUserList newWindowUserList(Collection<User> users) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Provide the config form for the Plugin.
     * This is to change any properties of the plugin_support, may be null.
     *
     * @return Config pane details.
     */
    public ConfigPane getConfigPane() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Privide a menu for the Plugin.
     * This is displayed on the main menu bar, or in the Plugins menu as selected by the user.
     * May be null if no menu provided.
     *
     * @return Menu for plugin_support.
     */
    public Menu getMenu() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Initilise the plugin_support.
     * This is called after the plugin_support is loaded, any setup should be in here.
     * Note, this is called before getMenu() or getConfigPane() and as such these may be setup in here.
     */
    public void init() //Maybe have it take a reference to the main app?
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
