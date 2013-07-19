package uk.org.aravis;

import java.util.Date;

public class AravisData
{

    private String m_destination;
    private String m_sender;
    private String m_reciever;
    private String m_text;
    private Date m_timestamp;
    private int m_type;

    public final static int AD_DEBUG = 1;
    public final static int AD_ERROR = 2;

    /* Jadis defined notification codes */
    public final static int AD_LOGIN = 1;
    public final static int AD_LOGOUT = 2;
    public final static int AD_SHUTDOWN = 3;
    public final static int AD_RESTART = 4;
    public final static int AD_ABORT = 5;
    public final static int AD_PCHANGE = 6;

    public final static int AD_SAY = 300;
    public final static int AD_JOIN = 301;
    public final static int AD_LEAVE = 302;
    public final static int AD_INVITE = 303;
    public final static int AD_REVOKE = 304;
    public final static int AD_UINVITE = 305;
    public final static int AD_UREVOKE = 306;
    public final static int AD_EMOTE = 307;

    public boolean checkName(String name)
    {
        if (name.compareTo(m_destination) == 0)
        {
            return true;
        }

        return false;
    }

    public void setDestination(String destination)
    {
        m_destination = destination;
    }

    public void setSender(String sender)
    {
        m_sender = sender;
    }

    public void setReciever(String reciever)
    {
        m_reciever = reciever;
    }

    public void setText(String text)
    {
        m_text = text;
    }

    public void setTimestamp(Date timestamp)
    {
        m_timestamp = timestamp;
    }

    public void setType(int type)
    {
        m_type = type;
    }

    public String getSender()
    {
        return m_sender;
    }

    public String getReciever()
    {
        return m_reciever;
    }

    public String getText()
    {
        return m_text;
    }

    public Date getTimestamp()
    {
        return m_timestamp;
    }

    public int getType()
    {
        return m_type;
    }
}
