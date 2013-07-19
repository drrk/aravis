package uk.org.aravis.debug;

public class DebugFactory
{
    private static DebugOutput m_debug;

    public static DebugOutput getDebug()
    {
        if (m_debug == null)
        {
            m_debug = new ScreenDebugOutput();
        }
        return m_debug;
    }
}
