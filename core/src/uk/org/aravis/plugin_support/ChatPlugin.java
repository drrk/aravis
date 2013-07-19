package uk.org.aravis.plugin_support;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: Jul 29, 2007
 * Time: 6:38:04 PM
 */
public interface ChatPlugin extends ConnectionPlugin
{
    /**
     * Called by {@link uk.org.aravis.Aravis} to mass away all plugins.
     *
     * @param message Away message, may be ignored.
     */
    public void setAway(String message);

    /**
     * Provide a list of reciepents this plugin_support can send to.
     * <p/>
     * The mapping is of id, display name, where id is the required id to for the plugin_support to send
     * the message.
     * <p/>
     * If the plugin_support is maintaining multiple connections {@link MultiInstancePlugin} then the display name and id should
     * be of the form connectionId{@link MultiInstancePlugin#getSeperator()}reciepientName
     * <p/>
     * This should be not be null, however users of this method should not assume it is not.
     *
     * @return List of reciepents.
     */
    public Collection<User> getChatReciepients();


    /**
     * Send a chat using this plugin_support.
     *
     * @param reciepient to send chat to.
     * @param Message    Message to send
     * @throws PluginNotConnectedException If the plugin_support is not conencted.
     * @throws InvalidReceipientException  if the reciepient is invalid.
     */
    public void sendChat(User reciepient, String Message)
            throws PluginNotConnectedException, InvalidReceipientException;

}
