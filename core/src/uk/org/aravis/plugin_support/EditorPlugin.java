package uk.org.aravis.plugin_support;

import javax.swing.text.Document;

//TODO: Really think about this one
/**
 * Defines a plugin_support that provides an Editor.
 * <p/>
 * This does not have to provide a gui element, for example it could launch an external editor.
 * <p/>
 * User: kimball
 * Date: Jul 29, 2007
 * Time: 7:31:29 PM
 */
public interface EditorPlugin
{
    public void editDocument(Document document);
}
