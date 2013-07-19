package uk.org.aravis.jadis;

import uk.org.aravis.plugin_support.*;

import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: May 12, 2007
 * Time: 5:28:23 PM
 */
public class JadisPlugin implements ConnectionPlugin, MessagePlugin, ChatPlugin, MultiInstancePlugin {
    List m_connections;

    public ConfigPane getConfigPane() {
        return null;
    }

    public Menu getMenu() {
        return null;
    }

    public void init() {

    }

    public LoginStatus login() {
        return null;
    }

    public void logout() {

    }

    public void setAway(String message) {

    }

    /**
     * Provide a list of reciepents this plugin_support can send to.
     * <p/>
     * The mapping is of id, display name, where id is the required id to for the plugin_support to send
     * the message.
     * <p/>
     * If the plugin_support is maintaining multiple connections {@link uk.org.aravis.plugin_support.MultiInstancePlugin} then the display name and id should
     * be of the form connectionId{@link uk.org.aravis.plugin_support.MultiInstancePlugin#getSeperator()}reciepientName
     * <p/>
     * This should be not be null, however users of this method should not assume it is not.
     *
     * @return List of reciepents.
     */
    public Collection<User> getChatReciepients() {
        //I guess this should be implemented as a page, so return all the logged in users.
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Send a chat using this plugin_support.
     *
     * @param reciepient to send chat to.
     * @param Message    Message to send
     * @throws uk.org.aravis.plugin_support.ConnectionPlugin.PluginNotConnectedException
     *          If the plugin_support is not conencted.
     * @throws uk.org.aravis.plugin_support.Plugin.InvalidReceipientException
     *          if the reciepient is invalid.
     */
    public void sendChat(User reciepient, String Message) throws PluginNotConnectedException, InvalidReceipientException {
        //send as page
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isChat() {
        return true;
    }


    /**
     * Provides a list of all Jadis users.
     * <p/>
     * This will return the Jadis username, and the Jadis Handle as the Display name
     * <p/>
     *
     * @return List of Jadis users.
     */
    public Collection<User> getMessageReceipients() {
        //Return all the Jadis users.
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Provide a pattern for valid recipients.
     * <p/>
     * For Jadis this, returns null, as we return a list of users.
     *
     * @return Regex
     */
    public Pattern getMessageReceipientPattern() {
        return null;
    }

    /**
     * Send a message using this plugin_support.
     *
     * @param reciepient to send message to.
     * @param Message    Message to send
     * @throws uk.org.aravis.plugin_support.MessagePlugin.CannotSendException
     *          If the message cannot be sent.
     * @throws uk.org.aravis.plugin_support.Plugin.InvalidReceipientException
     *          if the reciepient is invalid.
     */
    public void sendMessage(User reciepient, String Message) throws CannotSendException, InvalidReceipientException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Seperator to be used when connection id is appended to the front of username etc.
     * Allows other plugins to determine the connections.
     * <p/>
     * Usually this is {@link uk.org.aravis.Aravis#pathSeperator}
     *
     * @return seperator
     */
    public String getSeperator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get the list of connections available.
     * <p/>
     * Returned is a map of connedtionId and Displayname.  The connection ID must be unique.
     *
     * @return List of connections.
     */
    public Map<String, String> getConnections() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
