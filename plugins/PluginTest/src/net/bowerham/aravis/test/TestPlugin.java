package net.bowerham.aravis.test;

import org.apache.log4j.Logger;
import uk.org.aravis.plugin_support.Plugin;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: Jun 2, 2007
 * Time: 9:09:46 AM
 */
public class TestPlugin implements Plugin
{
    public ConfigPane getConfigPane()
    {
        return null; //No config
    }

    public Menu getMenu()
    {
        return null;  //No menu
    }

    public void init()
    {
        Logger.getLogger(this.getClass()).debug("Initilized test plugin");
        Dependancy d = new Dependancy();
        d.hello();


    }
}
