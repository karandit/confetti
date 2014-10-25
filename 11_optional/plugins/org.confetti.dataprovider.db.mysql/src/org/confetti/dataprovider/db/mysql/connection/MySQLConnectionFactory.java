package org.confetti.dataprovider.db.mysql.connection;

import org.confetti.rcp.extensions.ConnectionFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

/**
 * @author Gabor Bubla
 */
public class MySQLConnectionFactory implements ConnectionFactory {

    private static final String KEY_PASSWORD = "PASSWORD";
    private static final String KEY_USER = "USER";
    private static final String KEY_DATABASE = "DATABASE";
    private static final String KEY_PORT = "PORT";
    private static final String KEY_HOST = "HOST";
    
    private StringFieldEditor hostField;
    private StringFieldEditor portField;
    private StringFieldEditor databaseField;
    private StringFieldEditor usernameField;
    private StringFieldEditor passwordField;

    @Override
    public Composite createComposite(Composite parent) {
        Composite mySQLcomposite = new Composite(parent, SWT.NONE);
        mySQLcomposite.setLayout(new GridLayout(1, true)); 
        GridDataFactory.fillDefaults().grab(true, false).applyTo(mySQLcomposite);
        
        hostField = new StringFieldEditor("host", "&Host address", mySQLcomposite);
        portField = new StringFieldEditor("port", "&Port number", mySQLcomposite);
        databaseField = new StringFieldEditor("database", "&Database name", mySQLcomposite);
        usernameField = new StringFieldEditor("username", "&Username", mySQLcomposite);
        passwordField = new StringFieldEditor("password", "P&assword", mySQLcomposite) {
            @Override
            protected void doFillIntoGrid(Composite parent, int numColumns) {
                super.doFillIntoGrid(parent, numColumns);
                getTextControl().setEchoChar('*');
            }
        };
        
        Button testConnectionButton = new Button(mySQLcomposite, SWT.NONE);
        testConnectionButton.setText("Test connection");
        testConnectionButton.addSelectionListener(new TestConnectionSelectionListener());
        
        return mySQLcomposite;
    }
    
    @Override
    public void save(String connName, IPreferenceStore preferenceStore) {
        preferenceStore.setValue(connName +"_" + KEY_HOST, hostField.getStringValue());
        preferenceStore.setValue(connName +"_" + KEY_PORT, portField.getStringValue());
        preferenceStore.setValue(connName +"_" + KEY_DATABASE, databaseField.getStringValue());
        preferenceStore.setValue(connName +"_" + KEY_USER, usernameField.getStringValue());
        preferenceStore.setValue(connName +"_" + KEY_PASSWORD, passwordField.getStringValue());
    }
    
    @Override
    public void load(String connName, IPreferenceStore preferenceStore) {
        hostField.setStringValue(preferenceStore.getString(connName +"_" + KEY_HOST)); 
        portField.setStringValue(preferenceStore.getString(connName +"_" + KEY_PORT)); 
        databaseField.setStringValue(preferenceStore.getString(connName +"_" + KEY_DATABASE)); 
        usernameField.setStringValue(preferenceStore.getString(connName +"_" + KEY_USER)); 
        passwordField.setStringValue(preferenceStore.getString(connName +"_" + KEY_PASSWORD)); 
    }

    private class TestConnectionSelectionListener extends SelectionAdapter {
        //FIXME really test the connection
        @Override
        public void widgetSelected(SelectionEvent e) {
            MessageDialog.openInformation(
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
                    "Test connection", "Database connection is valid");
        }
    }

}
