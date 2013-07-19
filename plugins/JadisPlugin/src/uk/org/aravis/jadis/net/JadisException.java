package uk.org.aravis.jadis.net;

public class JadisException extends Exception
{
    public enum ErrorCode
    {
        OK,
        E_CONNECT,
        E_SERVER,
        E_BADXML,
        E_JADIS
    }

    private ErrorCode m_error_code;
    private int m_jadis_error;
    private String m_jadis_message;
    private String m_jadis_error_details;

    public JadisException(ErrorCode error_code)
    {
        m_error_code = error_code;
    }

    public JadisException(ErrorCode error_code, String message)
    {
        super(message);
        m_error_code = error_code;
    }


    public void setJadisError(int code, String message)
    {
        m_jadis_error = code;
        m_jadis_message = message;
        m_jadis_error_details = "";
    }

    public void setJadisError(int code, String message, String details)
    {
        m_jadis_error = code;
        m_jadis_message = message;
        m_jadis_error_details = details;
    }

    public ErrorCode getErrorCode()
    {
        return m_error_code;
    }

    public String getJadisErrorMessage()
    {
        if (m_error_code != ErrorCode.E_JADIS)
        {
            throw new IllegalStateException();
        }

        return m_jadis_message;
    }

    public String toString()
    {
        return this.getMessage() + " code: " + m_jadis_error + " jadis message: " + m_jadis_message + " details: " + m_jadis_error_details;
    }
}
