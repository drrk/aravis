package uk.org.aravis.swinggui;

import uk.org.aravis.Aravis;
import uk.org.aravis.plugin_support.Plugin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.Vector;

import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.Spacer;

/**
 * User: kimball
 * Date: Jul 4, 2007
 * Time: 6:07:18 PM
 */
public class Config implements WindowListener {
    static void invokeForm() {

        if (m_form == null) {
            m_form = new JFrame("Config");
            m_form.setContentPane(getInstance().configPanel);
            m_form.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            m_form.addWindowListener(getInstance());
            m_form.pack();
        }
        m_form.setVisible(true);
    }


    private static Config getInstance() {
        if (m_instance == null) {
            m_instance = new Config();
        }
        return m_instance;
    }

    static void addTab(Plugin.ConfigPane configPane) {
        Config c = getInstance();
        if (configPane.getTabTip() == null) {
            c.configTabs.addTab(configPane.getTabName(), configPane.getTab(c));
        } else {
            c.configTabs.addTab(configPane.getTabName(), null, configPane.getTab(c), configPane.getTabTip());
        }
    }

    private Config() {
        appName.setText(Aravis.getGlobalResources().getString("l_AppName"));
        okButton.setText(Aravis.getGlobalResources().getString("l_OkLabel"));
        cancelButton.setText(Aravis.getGlobalResources().getString("l_CancelLabel"));
        m_dirtyConfigs = new Vector<Plugin.ConfigPane>();
    }

    private static JFrame m_form;
    private static Config m_instance;

    private List<Plugin.ConfigPane> m_dirtyConfigs;

    private JTabbedPane configTabs;
    private JPanel configPanel;
    private JLabel appName;
    private JButton okButton;
    private JButton cancelButton;

    /**
     * Invoked the first time a window is made visible.
     */
    public void windowOpened(WindowEvent e) {
        //Not implemented
    }

    /**
     * Invoked when the user attempts to close the window
     * from the window's system menu.
     */
    public void windowClosing(WindowEvent e) {
        if (!m_dirtyConfigs.isEmpty()) {
            //Ask to save first
            int result = JOptionPane.showConfirmDialog(m_form,
                    Aravis.getGlobalResources().getString("q_Save"),
                    Aravis.getGlobalResources().getString("t_Save"),
                    JOptionPane.YES_NO_CANCEL_OPTION);
            switch (result) {
                case JOptionPane.CANCEL_OPTION:
                    //Nothing
                    break;

                case JOptionPane.NO_OPTION:
                    //Close window without saving
                    //Reset preferences panes, and clear dirty list
                    for (Plugin.ConfigPane plugin_pref : m_dirtyConfigs) {
                        plugin_pref.resetComponent();
                    }
                    m_dirtyConfigs.clear();

                    m_form.setVisible(false);
                    break;

                case JOptionPane.YES_OPTION:
                    //Save preferences panes, and clear dirty list                    
                    for (Plugin.ConfigPane plugin_pref : m_dirtyConfigs) {
                        plugin_pref.saveConfig();
                    }
                    m_dirtyConfigs.clear();
                    m_form.setVisible(false);
                    break;

                default:
            }
        } else {
            m_form.setVisible(false);
        }
    }

    /**
     * Invoked when a window has been closed as the result
     * of calling dispose on the window.
     */
    public void windowClosed(WindowEvent e) {
        //Not Implemented
    }

    /**
     * Invoked when a window is changed from a normal to a
     * minimized state. For many platforms, a minimized window
     * is displayed as the icon specified in the window's
     * iconImage property.
     *
     * @see java.awt.Frame#setIconImage
     */
    public void windowIconified(WindowEvent e) {
        // Not Implemented
    }

    /**
     * Invoked when a window is changed from a minimized
     * to a normal state.
     */
    public void windowDeiconified(WindowEvent e) {
        // Not Implemented
    }

    /**
     * Invoked when the Window is set to be the active Window. Only a Frame or
     * a Dialog can be the active Window. The native windowing system may
     * denote the active Window or its children with special decorations, such
     * as a highlighted title bar. The active Window is always either the
     * focused Window, or the first Frame or Dialog that is an owner of the
     * focused Window.
     */
    public void windowActivated(WindowEvent e) {
        // Not Implemented
    }

    /**
     * Invoked when a Window is no longer the active Window. Only a Frame or a
     * Dialog can be the active Window. The native windowing system may denote
     * the active Window or its children with special decorations, such as a
     * highlighted title bar. The active Window is always either the focused
     * Window, or the first Frame or Dialog that is an owner of the focused
     * Window.
     */
    public void windowDeactivated(WindowEvent e) {
        // Not Implemented
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        configPanel = new JPanel();
        configPanel.setLayout(new BorderLayout(0, 0));
        configTabs = new JTabbedPane();
        configPanel.add(configTabs, BorderLayout.CENTER);
        appName = new JLabel();
        appName.setText("");
        configPanel.add(appName, BorderLayout.NORTH);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        configPanel.add(panel1, BorderLayout.SOUTH);
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        okButton = new JButton();
        okButton.setText("OK");
        panel1.add(okButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return configPanel;
    }
}
