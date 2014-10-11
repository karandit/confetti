package org.confetti.rcp.preferences;

import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class DatabasePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public DatabasePreferencePage() {
		super(GRID);
		setPreferenceStore(ConfettiPlugin.getDefault().getPreferenceStore());
	}
	
	@Override
	public void createFieldEditors() {
		super.addField(new ComboFieldEditor(PreferenceConstants.DB_PLATFORM, "Database &type", new String[][] { { "MySQL", "MySQL" } }, getFieldEditorParent()));
		super.addField(new StringFieldEditor(PreferenceConstants.DB_HOST, "&Host address", getFieldEditorParent()));
		super.addField(new StringFieldEditor(PreferenceConstants.DB_PORT, "&Port number", getFieldEditorParent()));
		super.addField(new StringFieldEditor(PreferenceConstants.DB_DATABASE, "&Database name", getFieldEditorParent()));
		super.addField(new StringFieldEditor(PreferenceConstants.DB_USERNAME, "&Username", getFieldEditorParent()));
		super.addField(new StringFieldEditor(PreferenceConstants.DB_PASSWORD, "P&assword", getFieldEditorParent()) {
		    @Override
		    protected void doFillIntoGrid(Composite parent, int numColumns) {
		        super.doFillIntoGrid(parent, numColumns);
		        getTextControl().setEchoChar('*');
		    }
		});
		
		Button testConnectionButton = new Button(getFieldEditorParent(), SWT.NONE);
		testConnectionButton.setText("Test connection");
		testConnectionButton.addSelectionListener(new TestConnectionSelectionListener());
	}

	private class TestConnectionSelectionListener implements SelectionListener {
        //FIXME really test the connection
	    @Override
        public void widgetSelected(SelectionEvent e) {
            MessageDialog.openInformation(getShell(), "Test connection", "Database connection is valid");
        }
        
        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
            MessageDialog.openInformation(getShell(), "Test connection", "Database connection is valid");
        }
    }

    @Override
	public void init(IWorkbench workbench) {
	}
	
}
