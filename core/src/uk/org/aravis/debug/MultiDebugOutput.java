package uk.org.aravis.debug;

import java.util.List;
import java.util.Vector;

public class MultiDebugOutput implements DebugOutput
{
    private List<DebugOutput> m_debuggers;
    public boolean m_debug = false;

    public MultiDebugOutput()
    {
        m_debuggers = new Vector<DebugOutput>();
    }

    public void debug(String owner, String message)
    {
        for (DebugOutput m_debugger : m_debuggers)
        {
            m_debugger.debug(owner, message);
        }
    }

    public void error(String owner, String message)
    {

        for (DebugOutput m_debugger : m_debuggers)
        {
            m_debugger.error(owner, message);
        }
    }

    protected void registerDebugger(DebugOutput debugger)
    {
        m_debuggers.add(debugger);
    }

    public void setDebugMode(boolean mode)
    {
        m_debug = mode;
    }

    public boolean registerDebugger(String classname)
    {
        Class c;
        DebugOutput debug;
        try
        {
            c = Class.forName(classname);
        }
        catch (ClassNotFoundException e)
        {
            DebugFactory.getDebug().error("DEBUG", "Could not load debugger: " + classname);
            return false;
        }
        try
        {
            debug = (DebugOutput) c.newInstance();
        }
        catch (InstantiationException e)
        {
            DebugFactory.getDebug().error("DEBUG", "Could not instantiate debugger: " + classname);
            return false;
        }

        catch (IllegalAccessException e)
        {
            DebugFactory.getDebug().error("DEBUG", "Could not instantiate debugger: " + classname);
            return false;
        }
        catch (ClassCastException e)
        {
            DebugFactory.getDebug().error("DEBUG", classname + " is not a debugger");
            return false;
        }
        catch (Exception e)
        {
            DebugFactory.getDebug().error("DEBUG", "Other exception with: " + classname);
            return false;
        }
        registerDebugger(debug);
        return true;
    }

}
