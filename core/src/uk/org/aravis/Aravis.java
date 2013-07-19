package uk.org.aravis;

import org.apache.log4j.BasicConfigurator;
import uk.org.aravis.plugin_support.PluginUtils;
import uk.org.aravis.swinggui.Shell;

import java.util.ResourceBundle;

/**
 * Aravis Communication Centre.
 * <p/>
 * Main comms point of system, all messages to and from plugins go through here.
 * <p/>
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: May 11, 2007
 * Time: 7:27:31 PM
 */
public class Aravis
{
// ------------------------------ FIELDS ------------------------------

    public static final int E_OK = 1;
    public static final int E_GUI_DONE = 2;

    //Public Members
    public static String pathSeperator;

    //Private Members
    private static Aravis m_instance;
    private static boolean m_macos;
    private static PluginUtils pluginUtils;

// -------------------------- STATIC METHODS --------------------------

    //Public static methods

    /**
     * Convience method to see if we are running on MacOS.
     * This is too allow us to tweak the UI to fit the MacOS standards
     *
     * @return true if running on Mac OS X
     */
    public static boolean is_macos()
    {
        return m_macos;
    }

    public static Aravis getInstance()
    {
        if (m_instance == null)
        {
            throw new IllegalStateException("Aravis has not been initizized yet");
        }
        return m_instance;
    }

    public static ResourceBundle getGlobalResources()
    {
        return ResourceBundle.getBundle("Aravis");
    }

    public static void quit()
    {
        //Logout
        //Unload plugins
        //Exit
        System.exit(0);
    }


    //TODO: Implement this - should return false if the Aravis App is exiting to allow infinite loops to end
    public static boolean isRunning()
    {
        return true;  //To change body of created methods use File | Settings | File Templates.
    }

// --------------------------- CONSTRUCTORS ---------------------------

    // Private methods

    private Aravis()
    {

        PluginUtils.getInstance().loadDefaultPlugins();
        new Shell();
    }

// --------------------------- main() method ---------------------------

    public static void main(String args[])
    {
        // This will be the main line for Aravis.
        // It will load up the inital window, and check for the plugins that should be loaded
        BasicConfigurator.configure();
        m_macos = System.getProperty("os.name").contains("Mac OS X");
        Aravis.pathSeperator = System.getProperty("file.separator");
        Aravis.init();
    }

    // Package Private static Methods
    static void init()
    {
        m_instance = new Aravis();
    }
}


