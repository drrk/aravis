package uk.org.aravis.plugin_support;

import java.util.Map;

/**
 * Defines a plugin_support that can have multiple 'instances'.
 * <p/>
 * This would probabally be used most in conjunction with {@link ConnectionPlugin}; for example an IMAP plugin_support may
 * support multiple accounts.
 * <p/>
 * User: kimball
 * Date: Jul 29, 2007
 * Time: 7:11:19 PM
 */
public interface MultiInstancePlugin
{
    /**
     * Seperator to be used when connection id is appended to the front of username etc.
     * Allows other plugins to determine the connections.
     * <p/>
     * Usually this is {@link uk.org.aravis.Aravis#pathSeperator}
     *
     * @return seperator
     */
    public String getSeperator();

    /**
     * Get the list of connections available.
     * <p/>
     * Returned is a map of connedtionId and Displayname.  The connection ID must be unique.
     *
     * @return List of connections.
     */
    public Map<String, String> getConnections();
}
