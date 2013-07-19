package uk.org.aravis.plugin_support;

import uk.org.aravis.plugin_support.Plugin.InvalidReceipientException;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: Jul 29, 2007
 * Time: 6:38:30 PM
 */

/**
 * Framework for plugins that support sending asynchronous messages, ie email, jadis conference.
 * <p/>
 * This is different from ChatPlugin specifically as it does not indicate a perminate connection
 * For example an SMTP only plugin_support may implement this, but not ConnectionPlugin
 * <p/>
 * Allows other plugins to send messages.
 * <p/>
 * Users of this interface should also check if plugin_support supports {@link MultiInstancePlugin} as they should prepend the
 * instance name to the receipent, seperated by {@link uk.org.aravis.plugin_support.MultiInstancePlugin#getSeperator()}
 */
public interface MessagePlugin
{
    /**
     * Provide a list of reciepents this plugin_support can send to.
     * <p/>
     * The mapping is of id, display name, where id is the required id to for the plugin_support to send the message
     * and must match the pattern if provided.
     * <p/>
     * This may be null, in which case the Pattern is usually set.
     *
     * @return List of reciepents or null.
     */
    public Collection<User> getMessageReceipients();

    /**
     * Provide a pattern for valid recipients.
     *
     * @return Regex
     */
    public Pattern getMessageReceipientPattern();

    /**
     * Send a message using this plugin_support.
     *
     * @param reciepient to send message to.
     * @param Message    Message to send
     * @throws CannotSendException        If the message cannot be sent.
     * @throws InvalidReceipientException if the reciepient is invalid.
     */
    public void sendMessage(User reciepient, String Message)
            throws CannotSendException, InvalidReceipientException;

    public static class CannotSendException extends Exception
    {

    }


}
