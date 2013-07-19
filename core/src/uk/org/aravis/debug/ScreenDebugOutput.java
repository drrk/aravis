package uk.org.aravis.debug;

import uk.org.aravis.util.Utils;

public class ScreenDebugOutput implements DebugOutput
{
    public boolean m_debug = false;

    public void debug(String owner, String message)
    {
        if (m_debug)
        {
            System.err.println(Utils.getTimestamp() + ": DEBUG: " + owner + ": " + message);
        }
    }

    public void setDebugMode(boolean mode)
    {
        m_debug = mode;
    }

    public void error(String owner, String message)
    {
        System.err.println(Utils.getTimestamp() + ": ERROR: " + owner + ": " + message);
    }
}
