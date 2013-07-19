package uk.org.aravis.debug;

import uk.org.aravis.util.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDebugOutput implements DebugOutput
{
    PrintWriter m_printer;
    FileWriter m_file;
    public boolean m_debug = false;

    FileDebugOutput() throws Exception
    {
        String filename = System.getProperty("user.debug.file");
        try
        {
            m_file = new FileWriter(filename);
        }
        catch (IOException e)
        {
            throw e;
        }
        m_printer = new PrintWriter(m_file);
    }

    public void debug(String owner, String message)
    {
        if (m_debug)
        {
            m_printer.println(Utils.getTimestamp() + ": DEBUG: " + owner + ": " + message);
            m_printer.flush();
        }
    }

    public void error(String owner, String message)
    {
        m_printer.println(Utils.getTimestamp() + ": ERROR: " + owner + ": " + message);
        m_printer.flush();
    }

    public void setDebugMode(boolean mode)
    {
        m_debug = mode;
    }

    protected void finalize() throws Exception
    {
        m_printer.close();
        try
        {
            m_file.close();
        }
        catch (IOException e)
        {
            throw e;
        }
    }
}
