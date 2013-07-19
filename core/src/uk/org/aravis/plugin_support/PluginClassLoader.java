package uk.org.aravis.plugin_support;

import org.apache.log4j.Logger;
import uk.org.aravis.Aravis;
import uk.org.aravis.util.JarFileClassLoader;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: May 20, 2007
 * Time: 11:23:11 PM
 */
public class PluginClassLoader extends JarFileClassLoader
{
    private Class<Plugin> m_mainClass;
    private Plugin m_mainClassInstance = null;
    public PluginClassLoader(JarFile file)
            throws IOException, InvalidPluginException
    {
        super(file);
        Enumeration<JarEntry> entries = file.entries();
        while (entries.hasMoreElements())
        {
            JarEntry entry = entries.nextElement();
            if (entry.getName().endsWith("Plugin.class"))
            {
                Class<?> c;
                try
                {
                    c = this.loadClass(entry.getName().replace(".class", "").replace(Aravis.pathSeperator, "."));
                }
                catch (ClassNotFoundException e)
                {
                    throw new InvalidPluginException();
                }
                if (Plugin.class.isAssignableFrom(c))
                {
                    m_mainClass = (Class<Plugin>) c;
                } else
                {
                    throw new InvalidPluginException();
                }
            }
        }
    }

    public Plugin getMainClass()
    {
        if (m_mainClassInstance != null){
            try
            {
                m_mainClassInstance = m_mainClass.newInstance();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        return m_mainClassInstance;

    }

    /**
     * Loads the class with the specified <a href="#name">binary name</a>.  The
     * default implementation of this method searches for classes in the
     * following order:
     * <p/>
     * <p><ol>
     * <p/>
     * <li><p> Invoke {@link #findLoadedClass(String)} to check if the class
     * has already been loaded.  </p></li>
     * <p/>
     * <li><p> Invoke the {@link #loadClass(String) <tt>loadClass</tt>} method
     * on the parent class loader.  If the parent is <tt>null</tt> the class
     * loader built-in to the virtual machine is used, instead.  </p></li>
     * <p/>
     * <li><p> Invoke the {@link #findClass(String)} method to find the
     * class.  </p></li>
     * <p/>
     * </ol>
     * <p/>
     * <p> If the class was found using the above steps, and the
     * <tt>resolve</tt> flag is true, this method will then invoke the {@link
     * #resolveClass(Class)} method on the resulting <tt>Class</tt> object.
     * <p/>
     * <p> Subclasses of <tt>ClassLoader</tt> are encouraged to override {@link
     * #findClass(String)}, rather than this method.  </p>
     *
     * @param name    The <a href="#name">binary name</a> of the class
     * @param resolve If <tt>true</tt> then resolve the class
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class could not be found
     */
    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException
    {
        /* For now we just call super, however we are going to use this to
         * prevent plugins accessing certain classes.
         */
        return super.loadClass(name, resolve);
    }

    public static void main(String[] args)
    {
        JarFile jarfile = null;
        PluginClassLoader pl = null;
        try
        {
            jarfile = new JarFile("/Users/kimball/IdeaProjects/Aravis/PluginTest/PluginTest.jar");
        }
        catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try
        {
            pl = new PluginClassLoader(jarfile);
        }
        catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        catch (InvalidPluginException e)
        {
            System.out.println();
            Logger.getRootLogger().fatal("Not a valid plugin, exiting");
            System.exit(1);
        }
        Plugin p = pl.getMainClass();
        p.init();


    }

}
