package org.confetti.rcp.preferences;

import static org.confetti.rcp.ConfettiPlugin.KEY_CONNECTIONS;
import static org.confetti.rcp.ConfettiPlugin.KEY_TYPE;
import static org.confetti.rcp.views.AbstractEntityTableView.createColumn;

import java.util.List;

import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.extensions.ConnectionDescr;
import org.confetti.rcp.extensions.ConnectionFactory;
import org.confetti.rcp.extensions.ConnectionRegistry;
import org.confetti.util.Tuple;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * @author Gabor Bubla
 */
public class ConnectionPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    //--------------------------- fields -------------------------------------------------------------------------------
    private List<ConnectionDescr> extensions = ConnectionRegistry.INSTANCE.getExtensions();
    private List<Tuple<String, String>> connectionSettings; 
	
    //--------------------------- Override methods ---------------------------------------------------------------------
	@Override
	public void init(IWorkbench workbench) {
	    noDefaultAndApplyButton();
	    setPreferenceStore(ConfettiPlugin.getDefault().getPreferenceStore());
	    connectionSettings = ConfettiPlugin.getDefault().getConnectionSettings();
	}
	
	@Override
	protected Control createContents(Composite parent) {
	    parent.setLayout(new GridLayout(2, false));
	    
	    Table table = new Table(parent, SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.BORDER);
	    table.setHeaderVisible(true);
	    createColumn(table, "Connection name", 150);
	    createColumn(table, "Connection type", 150);
	    GridDataFactory.fillDefaults().grab(true, true).applyTo(table);
	    
	    TableViewer viewer = new TableViewer(table);
	    viewer.setContentProvider(new ArrayContentProvider());
	    viewer.setLabelProvider(new ConnectionLabelProvider());
	    viewer.setInput(connectionSettings);
	    
	    Composite buttonsComposite = new Composite(parent, SWT.NONE);
	    GridData gridData = new GridData();
	    gridData.verticalAlignment = GridData.BEGINNING;
	    buttonsComposite.setLayoutData(gridData);
	    FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
	    fillLayout.spacing = 10;
        buttonsComposite.setLayout(fillLayout);
	    
	    Button addButton = new Button(buttonsComposite, SWT.NONE);
	    addButton.setText("&Add");
	    addButton.addSelectionListener(new AddNewConnectionSelectionListener(viewer));
	    Button editButton = new Button(buttonsComposite, SWT.NONE);
	    editButton.setText("&Edit");
	    editButton.addSelectionListener(new EditConnectionSelectionListener(viewer));
	    Button removeButton = new Button(buttonsComposite, SWT.NONE);
	    removeButton.setText("&Remove");
	    removeButton.addSelectionListener(new RemoveConnectionSelectionListener(viewer));
	    
	    return parent;
	}
	
	//--------------------------- helper methods -----------------------------------------------------------------------
	
	private String transformToCSV(List<Tuple<String, String>> connectionSettings) {
	    StringBuilder sb = new StringBuilder();
	    boolean first = true;
	    for (Tuple<String, String> tuple : connectionSettings) {
	        if (first) {
	            first = false;
	        } else {
	            sb.append(",");
	        }
	        sb.append(tuple.getFirst());
	    }
	    return sb.toString();
	}

	//--------------------------- inner Classes ------------------------------------------------------------------------
	private class ConnectionLabelProvider extends LabelProvider implements ITableLabelProvider {
        @Override public Image getColumnImage(Object element, int columnIndex) { return null; }
        @Override
        public String getColumnText(Object element, int columnIndex) {
            Tuple<String, String> tuple = (Tuple<String, String>) element; 
            switch (columnIndex) {
                case 0:     return tuple.getFirst();
                default:    return tuple.getSecond();
            }
        }
	}
	
	private class AddNewConnectionSelectionListener extends SelectionAdapter {
        
	    private final TableViewer viewer;

        public AddNewConnectionSelectionListener(TableViewer viewer) {
            this.viewer = viewer;
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            
            // Connection name
            InputDialog inputDlg = new InputDialog(shell, "Connection name", "Specify a name for the connection", "", new IInputValidator() {
                @Override
                public String isValid(String newText) {
                    if (newText.isEmpty()) {
                        return "Please enter the connection name";
                    }
                    for (Tuple<String, String> tuple : connectionSettings) {
                        if (tuple.getFirst().equals(newText)) {
                            return "Connection name already exists";
                        }
                    }
                    return null;
                }
            });
            if (Window.OK != inputDlg.open()) {
                return;
            }
            String connName = inputDlg.getValue();
            
            // Connection type
            ListDialog dlg = new ListDialog(shell);
            dlg.setContentProvider(new ArrayContentProvider());
            dlg.setLabelProvider(new LabelProvider());
            dlg.setTitle("Connection types");
            dlg.setMessage("Choose an input");
            dlg.setInput(extensions); 
            if (Window.OK != dlg.open()) {
                return;
            }
            Object[] selected = dlg.getResult();
            if (selected == null || selected.length == 0) {
                return;
            }
            ConnectionDescr selectedDescr = (ConnectionDescr) selected[0];

            // Connection details
            IPreferenceStore prefStore = getPreferenceStore();
            ConnectionFactory connFactory = selectedDescr.getConnectionFactory();
            SelectedConnectionDialog dialog = new SelectedConnectionDialog(shell, prefStore, connFactory, connName);
            int ret = dialog.open();
            if (ret == Window.OK) {
                connectionSettings.add(new Tuple<>(connName, selectedDescr.getDbType()));
                prefStore.setValue(connName + "_" + KEY_TYPE, selectedDescr.getDbType());
                prefStore.setValue(KEY_CONNECTIONS, transformToCSV(connectionSettings));
                viewer.refresh();
            }
        }
	}
	
	private class EditConnectionSelectionListener extends SelectionAdapter {
	    
	    private final TableViewer viewer;
        
        public EditConnectionSelectionListener(TableViewer viewer) {
            this.viewer = viewer;
        }
        
        @Override
        public void widgetSelected(SelectionEvent e) {
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            
            //warning dialog if a viewer has no selection
            if (viewer.getSelection().isEmpty()) {
                MessageDialog.openWarning(shell, "Wrong selection", "Please select a connection.");
                return;
            }
            
            //get the selection
            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
            Tuple<String, String> selectedConn = (Tuple<String, String>) selection.getFirstElement();
            ConnectionDescr foundConn = ConnectionRegistry.INSTANCE.getConnectionByType(selectedConn.getSecond());
            if (foundConn != null) {
                SelectedConnectionDialog dialog = new SelectedConnectionDialog(shell, getPreferenceStore(), 
                        foundConn.getConnectionFactory(), selectedConn.getFirst());
                dialog.open();
                return;
            }
            
            //the selected connection not found in the extensions
            MessageDialog.openError(shell, "Error", "The selected connection is not found.");
        }
	}
	
	private class RemoveConnectionSelectionListener extends SelectionAdapter {
	    
	    private final TableViewer viewer;
	    
	    public RemoveConnectionSelectionListener(TableViewer viewer) {
	        this.viewer = viewer;
	    }
	    
	    @Override
	    public void widgetSelected(SelectionEvent e) {
	        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	        
	        //warning dialog if a viewer has no selection
            if (viewer.getSelection().isEmpty()) {
                MessageDialog.openWarning(shell, "Wrong selection", "Please select a connection.");
                return;
            }
            
            //get the selection
            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
            Tuple<String, String> selectedConn = (Tuple<String, String>) selection.getFirstElement();
            
            //confirmation dialog
            if (!MessageDialog.openConfirm(shell, "Remove connection", 
                    "\"" + selectedConn.getFirst() + "\" named connection will be deleted. Are you sure?")) {
                return;
            }
            
            //remove it from connectionSettings
            connectionSettings.remove(selectedConn);
            
            //remove it from CONNECTIONS in the PreferenceStore
            getPreferenceStore().setValue(KEY_CONNECTIONS, transformToCSV(connectionSettings));
            viewer.refresh();
	    }
	}
	
}
