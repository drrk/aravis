package net.bowerham.aravis.test;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: Jun 2, 2007
 * Time: 10:02:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class Dependancy
{
    public void hello()
    {
        Logger.getLogger(this.getClass()).debug("Hello World");
    }
}
