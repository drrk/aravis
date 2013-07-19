package uk.org.aravis.debug;

public interface DebugOutput
{

    public void debug(String owner, String message);

    public void error(String owner, String message);

    public void setDebugMode(boolean mode);
}
