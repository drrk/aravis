package uk.org.aravis.plugin_support;

import uk.org.aravis.swinggui.Config;

import java.awt.*;

/**
 * Aravis Plugin.
 * <p/>
 * This is the defining point for plugins to the aravis system.
 * <p/>
 * Note, in order to save preferences for the plugin_support it is prefered that the plugin_support writer calls
 * Aravis.getPluginPreferences() which returns a java.util.prefs.Preferences object.
 * The reason for this that calling the preferences directly fron the java API is that this method will
 * generate a fake package of uk.org.aravis.&lt;pluginpackage&gt;.&lt;pluginclass&gt; to ensure all Aravis
 * preferences are in the same tree.
 * <p/>
 * <p/>
 * <p/>
 * Additionaly, in order to prevent clashes in ResourceBundle names, it is reccomended that the Resource bundle is
 * named as &lt;pluginname&gt;Resourses.  Actual support for internationalistion is up to the Plugin developer,
 * but is reccomended.
 */
public interface Plugin
{

    /**
     * Provide the config form for the Plugin.
     * This is to change any properties of the plugin_support, may be null.
     *
     * @return Config pane details.
     */
    public ConfigPane getConfigPane();

    /**
     * Privide a menu for the Plugin.
     * This is displayed on the main menu bar, or in the Plugins menu as selected by the user.
     * May be null if no menu provided.
     *
     * @return Menu for plugin_support.
     */
    public Menu getMenu();

    /**
     * Initilise the plugin_support.
     * This is called after the plugin_support is loaded, any setup should be in here.
     * Note, this is called before getMenu() or getConfigPane() and as such these may be setup in here.
     */
    public void init(); //Maybe have it take a reference to the main app?

    public interface ConfigPane
    {
        /**
         * Get the Swing component of the config items.
         * Note, this is on a TabbedPane, and as such should have no menus, or tabs of it's own.
         * Also, it should not have a save or cancel button, as this is implemented by the Aravis framework.
         * <p/>
         * Passed in is the reference to the Config form itself this exports an isDirty(Component) method.
         * Use this to indicate any changes that are made, so the user is requested,
         * to save if they close without saving, and to let Aravis know to save this plugins properties
         * when Ok is clicked.
         *
         * @param requester Reference to Config form.
         * @return Swing component to represent config panel.
         */
        public Component getTab(Config requester);

        /**
         * Name to be displayed on the tab.
         * Should usually be the same as the plugin_support name.
         *
         * @return Name of config tab.
         */
        public String getTabName();

        /**
         * Tool tip to be displayed on tab.
         * Extra description of the config tab, may be null if none required.
         *
         * @return Tool tip.
         */
        public String getTabTip();

        /**
         * Save the config from the component to the Aravis preferences store
         */
        public void saveConfig();

        /**
         * Reset the component to reflect the Aravis preferences store
         */
        public void resetComponent();
    }

    public static class InvalidReceipientException extends Exception
    {
        //Empty
    }
}
