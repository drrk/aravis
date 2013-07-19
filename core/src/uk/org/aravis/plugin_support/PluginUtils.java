package uk.org.aravis.plugin_support;

import uk.org.aravis.Aravis;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.jar.JarFile;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: Jul 29, 2007
 * Time: 6:15:42 PM
 */
public class PluginUtils {
// ------------------------------ FIELDS ------------------------------

    private Pattern m_ShellMatcher;
    private Pattern m_AravisMatcher;
    private HashMap<String, PluginClassLoader> m_loadedPlugins;
    private static PluginUtils m_instance;

// -------------------------- STATIC METHODS --------------------------

    /**
     * Retreive the defination of the plugin_support entry point for the object passed.
     * <p/>
     * If the passed object was not part of a plugin_support IllegalArgumentException is thrown
     *
     * @param o Object to check
     * @return Class definition
     * @throws IllegalArgumentException If passed object not part of plugin_support.
     */
    public static Class getPluginClass(Object o) throws IllegalArgumentException {
        ClassLoader cl = o.getClass().getClassLoader();
        if (cl instanceof PluginClassLoader) {
            return ((PluginClassLoader) cl).getMainClass().getClass();
        }
        throw new IllegalArgumentException("Not part of plugin");
    }

    public static Preferences getPluginPreferences(Class<? extends Plugin> plugin) {
        String pluginName = plugin.getName().replaceAll(".", "/");
        String aravisName = Aravis.class.getName().replaceAll(".", "/");
        return Preferences.userRoot().node(aravisName + "/" + pluginName);
    }

// --------------------------- CONSTRUCTORS ---------------------------

    private PluginUtils() {
        m_loadedPlugins = new HashMap<String, PluginClassLoader>();
        m_ShellMatcher = Pattern.compile("^uk\\.org\\.aravis\\.swinggui\\.Shell.*");
        m_AravisMatcher = Pattern.compile("^uk\\.org\\.aravis\\.Aravis.*");
    }

// -------------------------- OTHER METHODS --------------------------

    public static PluginUtils getInstance() {
        if (m_instance == null) {
            m_instance = new PluginUtils();
        }
        return m_instance;
    }

    /**
     * This is *NOT* to be called outside Aravis core.
     * If you attempt to it will throw an IllegalArgumentException.
     *
     * @throws IllegalArgumentException If called outside Aravis core.
     */
    public void loadDefaultPlugins() throws IllegalArgumentException {
        //Get the stack trace and make sure an allowed class called us (ie Aravis)
        if (!validCaller(m_AravisMatcher)) {
            throw new IllegalArgumentException();
        }
        //Load the default plugins.
    }

    private boolean validCaller(Pattern regex) {
        //Get stack trace
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        //The top of stack is our caller, the one above is the caller to check.

        //The stack contains two system methods, then this one then the one we want to check.
        assert (stack.length > 4);

        StackTraceElement interestedElement = stack[4];

        return regex.matcher(interestedElement.toString()).matches();
    }

    /**
     * This is *NOT* to be called outside Aravis core.
     * If you attempt to it will throw an IllegalArgumentException.
     *
     * @param pluginFile File object of plugin_support jar.
     * @param reload     If the plugin_support should be reloaded from disk if already loaded
     * @return If plugin_support loaded sucessfully
     * @throws java.io.IOException      If IOException occurs in Underlying code.
     * @throws uk.org.aravis.plugin_support.InvalidPluginException
     *                                  If pluginFile is not a plugin_support
     * @throws IllegalArgumentException If called outside Aravis core.
     */
    public boolean loadPlugin(File pluginFile, boolean reload)
            throws IOException, InvalidPluginException, IllegalArgumentException {
        //Get the stack trace and make sure an allowed class called us (ie Shell)
        if (!validCaller(m_ShellMatcher)) {
            throw new IllegalArgumentException();
        }

        //Ensure we can read plugin_support
        if (pluginFile == null || !pluginFile.canRead()) {
            throw new IOException();
        }

        if (!reload && m_loadedPlugins.containsKey(pluginFile.getName())) {
            return false;
        }

        JarFile pluginJarFile = null;
        pluginJarFile = new JarFile(pluginFile);
        //Get a class loader to read this file.
        PluginClassLoader cl = null;
        cl = new PluginClassLoader(pluginJarFile);

        m_loadedPlugins.put(pluginFile.getName(), cl);
        Plugin p = cl.getMainClass();
        p.init();
        return true;
    }

    // Public Methods
    /**
     * Called by a connection plugin_support when defered login complete.
     * <p/>
     * Can return either a success or failure status, ass appropiate
     *
     * @param plugin ConnectionPlugin that is returning status.
     * @param status Login status of plugin_support.
     */
    public void pluginLoginCallback(ConnectionPlugin plugin, ConnectionPlugin.LoginStatus status) {

    }
}
