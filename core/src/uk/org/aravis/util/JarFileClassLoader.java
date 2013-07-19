package uk.org.aravis.util;

import uk.org.aravis.Aravis;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Class loader to load from plugin_support jar files.
 * <p/>
 * TODO: cache classes
 * <p/>
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: May 20, 2007
 * Time: 8:42:51 PM
 */
public class JarFileClassLoader extends ClassLoader
{
    JarFile m_jarfile;

    public JarFileClassLoader(JarFile file) throws IOException
    {
        super();
        m_jarfile = file;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        ZipEntry classDataEntry = m_jarfile.getEntry(name.replace(".", Aravis.pathSeperator) + ".class");
        try
        {
            InputStream dataStream = m_jarfile.getInputStream(classDataEntry);

            long length = classDataEntry.getSize();
            if (length > Integer.MAX_VALUE)
            {
                throw (new ClassNotFoundException("Class size too large"));
            }
            int intLength = Long.valueOf(length).intValue();

            byte data[] = new byte[intLength];
            int bytesRead = dataStream.read(data, 0, intLength);
            if (bytesRead != intLength)
            {
                throw (new ClassNotFoundException("Failed to read class data"));
            }
            return defineClass(name, data, 0, bytesRead);
        }
        catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new ClassNotFoundException();
        }
    }
}
