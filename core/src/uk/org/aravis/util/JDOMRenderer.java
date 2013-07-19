package uk.org.aravis.util;

import org.apache.log4j.Logger;
import org.apache.log4j.or.ObjectRenderer;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Render a JDOM Document or Element
 * User: kimball
 * Date: Jan 26, 2007
 * Time: 9:15:27 AM
 */
public class JDOMRenderer implements ObjectRenderer
{
    private static XMLOutputter m_outputter = new XMLOutputter(Format.getPrettyFormat().setOmitDeclaration(true).setEncoding("UTF-8"));


    public String doRender(Object object)
    {
        if (object instanceof Element)
        {
            StringWriter s = new StringWriter();

            try
            {
                m_outputter.output((Element) object, s);
            }
            catch (IOException e)
            {
                Logger.getLogger(this.getClass()).error("Error Logging JDOM Element", e);
            }
            return s.toString();
        } else if (object instanceof Document)
        {
            StringWriter s = new StringWriter();
            try
            {
                m_outputter.output((Document) object, s);
            }
            catch (IOException e)
            {
                Logger.getLogger(this.getClass()).error("Error Logging JDOM Document", e);
            }
            return "\n" + s.toString();
        }
        return "";
    }
}
