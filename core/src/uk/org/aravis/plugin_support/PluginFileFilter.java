package uk.org.aravis.plugin_support;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: Jun 22, 2007
 * Time: 12:32:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class PluginFileFilter implements FilenameFilter
{


    public boolean accept(File dir, String file)
    {
        if (!file.endsWith(".jar"))
        {
            return false;
        }

        JarFile jf = null;
        try
        {
            jf = new JarFile(new File(dir, file));
        }
        catch (IOException e)
        {
            return false;
        }
        Enumeration<JarEntry> entries = jf.entries();
        while (entries.hasMoreElements())
        {
            JarEntry entry = entries.nextElement();
            if (entry.getName().endsWith("Plugin.class"))
            {
                return true;
            }
        }
        return false;
    }
}
