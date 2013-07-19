package uk.org.aravis.jadis.net;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Hashtable;


public class JadisConnection
{
    private URL m_url;
    private SAXBuilder m_db;

    private XMLOutputter serializer;

    private HttpURLConnection connection;
    private HttpURLConnection notifications_connection;

    private String sid;

    public JadisConnection()
            throws MalformedURLException, JadisException
    {
        this(new URL("http://charn.lancs.ac.uk:8001/lubbs"));
    }

    public JadisConnection(URL url)
            throws JadisException

    {
        m_url = url;
        m_db = new SAXBuilder();
        serializer = new XMLOutputter(Format.getPrettyFormat().setOmitDeclaration(true).setEncoding("US-ASCII"));

    }

    public JadisConnection(String url)
            throws MalformedURLException, JadisException
    {
        this(new URL(url));
    }

    private HttpURLConnection doConnect()
            throws JadisException, MalformedURLException
    {
        return doConnect(false);
    }

    private HttpURLConnection doConnect(boolean notify)
            throws JadisException, MalformedURLException
    {
        HttpURLConnection con;
        /*if (notify)
        {
           con = notifications_connection;
        } else {
            con = connection;
        }
        */
        try
        {
            con = (HttpURLConnection) m_url.openConnection();
        }
        catch (IOException e)
        {
            throw new JadisException(JadisException.ErrorCode.E_CONNECT);
        }
        try
        {
            con.setRequestMethod("POST");
        }
        catch (ProtocolException e)
        {
            throw new MalformedURLException();
        }
        con.setRequestProperty("Content-Type", "text/xml");
        con.setRequestProperty("User-Agent", "Aravis 0.6b");
        con.setRequestProperty("Accept", "text/xml");
        con.setDoOutput(true);
        return con;
    }


    private Element sendXML(Element root) throws JadisException, IOException
    {
        return sendXML(root, false);
    }

    private Element sendXML(Element root, boolean notify) throws JadisException, IOException
    {
        if (root == null)
        {
            throw new IllegalArgumentException(new NullPointerException());
        }
        Document rq_doc = new Document(root);
        Logger.getLogger(this.getClass()).debug("Sending XML");
        Logger.getLogger(this.getClass()).debug(rq_doc);
        HttpURLConnection connection = doConnect(notify);
        connection.connect();
        OutputStream os = connection.getOutputStream();
        serializer.output(rq_doc, os);


        InputStream is = connection.getInputStream();

        Document re_doc;

        try
        {
            re_doc = m_db.build(is);
        }
        catch (JDOMException e)
        {
            throw new JadisException(JadisException.ErrorCode.E_SERVER);
        }

        is.close();
        os.close();
        //connection.disconnect();
        Logger.getLogger(this.getClass()).debug("Recieved XML:");
        Logger.getLogger(this.getClass()).debug(re_doc);

        Element re = re_doc.getRootElement();

        int code;
        try
        {
            code = Integer.parseInt(re.getAttributeValue("status"));
        }
        catch (NumberFormatException e)
        {
            throw new JadisException(JadisException.ErrorCode.E_BADXML);
        }

        if (code != 0)
        {
            JadisException je = new JadisException(JadisException.ErrorCode.E_JADIS);
            je.setJadisError(code,
                             re.getAttributeValue("description"));
            throw je;
        }

        return re;
    }


    public Hashtable<String, String> system_getInfo()
            throws IOException, JadisException
    {
        Hashtable<String, String> response = new Hashtable<String, String>();

        /* Create request document tree */


        Element rq = new Element("system_info");

        Element re = sendXML(rq);

        Element re_system_getInfo = re.getChild("server_description");
        response.put("server_description",
                     re_system_getInfo.getText());

        return response;
    }

    public String getSid()
    {
        return sid;
    }

    public void user_login(String handle,
                           String password,
                           String clientIdentifier)
            throws IOException, JadisException
    {
        Element rq_user_login = new Element("user_login");

        rq_user_login.setAttribute("handle", handle);
        rq_user_login.setAttribute("password", password);
        rq_user_login.setAttribute("client_identifier", clientIdentifier);

        Element re = sendXML(rq_user_login);

        sid = re.getText();


    }

    public void user_logout()
            throws IOException, JadisException
    {
        if (sid == null)
        {
            throw new JadisException(JadisException.ErrorCode.E_CONNECT, "Not Logged in");
        }
        Element rq_user_login = new Element("user_logout");

        rq_user_login.setAttribute("sid", sid);


        Element re = sendXML(rq_user_login);

        sid = re.getText();


    }

    public void channel_sendMessage(String channel, String message) throws JadisException, IOException
    {
        Element rq = new Element("channel_sendMessage");
        rq.setAttribute("sid", sid);
        rq.setAttribute("channel", channel);
        rq.setAttribute("message", message);

        sendXML(rq);
    }

    public void channel_sendEmotion(String channel, String message) throws JadisException, IOException
    {
        Element rq = new Element("channel_sendEmotion");
        rq.setAttribute("sid", sid);
        rq.setAttribute("channel", channel);
        rq.setAttribute("emotion", message);

        sendXML(rq);
    }

    public void channel_join(String channel) throws JadisException, IOException
    {
        Element rq = new Element("channel_join");
        rq.setAttribute("sid", sid);
        rq.setAttribute("channel", channel);

        sendXML(rq);
    }

    public void channel_leave(String channel) throws JadisException, IOException
    {
        Element rq = new Element("channel_leave");
        rq.setAttribute("sid", sid);
        rq.setAttribute("channel", channel);

        sendXML(rq);
    }

    Element getNotifications() throws JadisException, IOException
    {

        Element rq = new Element("system_getNotifications");
        rq.setAttribute("sid", sid);
        Element re = null;

        Logger.getLogger(this.getClass()).debug("sending xml request");
        re = sendXML(rq, true);
        Logger.getLogger(this.getClass()).debug("returned from xml request");


        return re;

    }

    public boolean loggedIn()
    {
        return true;
    }
}

