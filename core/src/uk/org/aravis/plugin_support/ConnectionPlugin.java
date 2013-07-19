package uk.org.aravis.plugin_support;

/**
 * Interface for plugins that implement the concept of a perminate connection of some type.
 * User: kimball
 * Date: May 20, 2007
 * Time: 6:56:32 PM
 */
public interface ConnectionPlugin extends Plugin
{
    /**
     * Called by Aravis to mass login all plugins.
     *
     * @return If login was a success, or registered callback
     */
    public LoginStatus login();

    /**
     * Called by Aravis to mass logout all plugins.
     */
    public void logout();

    public enum LoginStatus
    {
        SUCCESS,
        FAILURE,
        DEFFERED
    }

    /**
     * Thrown when connection plugins are used when not connected/loggedin
     */
    public static class PluginNotConnectedException extends Exception
    {
        //Empty
    }
}
