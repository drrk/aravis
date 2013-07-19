package uk.org.aravis.jadis.net;

import org.apache.log4j.Logger;
import org.jdom.Element;
import uk.org.aravis.Aravis;
import uk.org.aravis.AravisData;
import uk.org.aravis.util.Utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;


/**
 * JADIS Server Notification handler.
 * Handle notifications form JADIS server
 * see the <a href='http://www.lancs.ac.uk/~clinch/jadis/api.html'>JADIS api</a>
 * for more details.
 * Loops continually once loaded in it's own thread.
 */
public class Notify implements Runnable
{

    private JadisConnection m_connection;

    private Collection<NotifyObservers> m_observers;
    private static Notify m_me;


    /**
     * Contruct Notify class.
     * Create class to handle JADIS noitifications,
     * and start thread to handle them.
     *
     * @param connection The connection object
     */
    public Notify(JadisConnection connection)
    {
        m_connection = connection;
        m_me = this;
        m_observers = new Vector<NotifyObservers>();

        new java.lang.Thread(this).start();
    }

    public static Notify getNotify()
    {
        return m_me;
    }

    public void addObserver(NotifyObservers obs)
    {
        m_observers.add(obs);
    }

    public int countObservers()
    {
        return m_observers.size();
    }

    /**
     * Thread start point.
     * Notify thread running code.
     */
    public void run()
    {
        int return_value = Aravis.E_OK;

        while (return_value == Aravis.E_OK)
        {
            Logger.getLogger(this.getClass()).debug("Notify loog start");
            Element re = null;

            try
            {
                re = m_connection.getNotifications();
            }
            catch (JadisException e)
            {
                Logger.getLogger(this.getClass()).debug("Exception in notify loop", e);
                re = null;
            }
            catch (IOException e)
            {
                Logger.getLogger(this.getClass()).debug("Fatal Exception in notify loop", e);
            }

            return_value = handle_result(re);
            if (!Aravis.isRunning())
            {
                return_value = Aravis.E_GUI_DONE;
            }
            if (!m_connection.loggedIn())
            {
                return_value = Aravis.E_GUI_DONE;
            }
            Logger.getLogger(this.getClass()).debug("End of notification loop");

        }
        Logger.getLogger(this.getClass()).debug("End of notification thread");
    }

    /**
     * Notifications result handler
     *
     * @param result JADIS notification list
     * @return Exit code
     */
    private synchronized int handle_result(Element result)
    {

        int return_code = Aravis.E_OK;
        if (result == null)
        {
            return return_code;
        }

        /* Handle notification(s) BEFORE calling again */
        List response_list = result.getChildren("notification");

        for (Object o : response_list)
        {
            Element element = (Element) o;

            String strtype = element.getAttributeValue("type");

            Logger.getLogger(this.getClass()).debug("Notification type: " + strtype);

            int type = 0;
            if ("channel_message".equals(strtype))
            {
                type = AravisData.AD_SAY;
            } else if ("channel_emotion".equals(strtype))
            {
                type = AravisData.AD_EMOTE;
            } else if ("channel_join".equals(strtype))
            {
                type = AravisData.AD_JOIN;
            } else if ("channel_leave".equals(strtype))
            {
                type = AravisData.AD_LEAVE;
            }
            Logger.getLogger(this.getClass()).debug("Notification type code : " + type);

            AravisData ad = new AravisData();
            ad.setType(type);
            try
            {
                ad.setTimestamp(Utils.stringToDate(element.getAttributeValue("timestamp")));
            }
            catch (ParseException e)
            {
                ad.setTimestamp(new Date());
            }
            switch (type)
            {
                case 0:
                    // Do Nothing
                    break;

                case AravisData.AD_LOGIN:
                case AravisData.AD_LOGOUT:
                    ad.setDestination("$Aravis");

                    break;
                case AravisData.AD_SHUTDOWN:
                case AravisData.AD_RESTART:

                    break;

                case AravisData.AD_EMOTE:
                case AravisData.AD_SAY:
                    ad.setDestination(element.getAttributeValue("channel"));
                    ad.setSender(element.getAttributeValue("from_handle"));
                    ad.setText(element.getText());
                    break;
                case AravisData.AD_JOIN:
                case AravisData.AD_LEAVE:
                    ad.setDestination(element.getAttributeValue("channel"));
                    ad.setSender(element.getAttributeValue("handle"));
                    break;

                case AravisData.AD_INVITE:
                case AravisData.AD_REVOKE:

                    break;
                case AravisData.AD_UINVITE:
                case AravisData.AD_UREVOKE:
                    ad.setDestination("$Aravis");

                    break;
                default:
                    break;
            }
            if (type != 0)
            {
                Logger.getLogger(this.getClass()).debug("Notifying observers, count: " + this.countObservers());
                notifyObservers(ad);
            }
        }

        return return_code;
    }

    private void notifyObservers(Object o)
    {
        for (NotifyObservers obs : m_observers)
        {
            obs.update(o);
        }
    }
}
