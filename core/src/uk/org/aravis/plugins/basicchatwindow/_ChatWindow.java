package uk.org.aravis.plugins.basicchatwindow;

import java.awt.*;
import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: kimball
 * Date: Sep 16, 2007
 * Time: 5:41:17 PM
 */
public class _ChatWindow {


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner non-commercial license
		m_panel = new JPanel();
		JSplitPane splitPane1 = new JSplitPane();
		JSplitPane splitPane2 = new JSplitPane();
		JScrollPane scrollPane1 = new JScrollPane();
		m_chat = new JTextPane();
		JScrollPane scrollPane2 = new JScrollPane();
		m_inputText = new JTextPane();
		JScrollPane scrollPane3 = new JScrollPane();
		m_userList = new JList();

		//======== m_panel ========
		{
			m_panel.setLayout(new GridBagLayout());
			((GridBagLayout)m_panel.getLayout()).columnWidths = new int[] {780, 0};
			((GridBagLayout)m_panel.getLayout()).rowHeights = new int[] {500, 0};
			((GridBagLayout)m_panel.getLayout()).columnWeights = new double[] {0.01, 1.0E-4};
			((GridBagLayout)m_panel.getLayout()).rowWeights = new double[] {0.01, 1.0E-4};

			//======== splitPane1 ========
			{
				splitPane1.setResizeWeight(0.99);

				//======== splitPane2 ========
				{
					splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
					splitPane2.setResizeWeight(0.95);

					//======== scrollPane1 ========
					{

						//---- m_chat ----
						m_chat.setEditable(false);
						scrollPane1.setViewportView(m_chat);
					}
					splitPane2.setTopComponent(scrollPane1);

					//======== scrollPane2 ========
					{
						scrollPane2.setViewportView(m_inputText);
					}
					splitPane2.setBottomComponent(scrollPane2);
				}
				splitPane1.setLeftComponent(splitPane2);

				//======== scrollPane3 ========
				{
					scrollPane3.setViewportView(m_userList);
				}
				splitPane1.setRightComponent(scrollPane3);
			}
			m_panel.add(splitPane1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner non-commercial license
	private JPanel m_panel;
	private JTextPane m_chat;
	private JTextPane m_inputText;
	private JList m_userList;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
