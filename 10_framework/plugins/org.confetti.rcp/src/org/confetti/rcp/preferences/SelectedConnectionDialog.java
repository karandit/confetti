package org.confetti.rcp.preferences;

import org.confetti.rcp.extensions.ConnectionFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Gabor Bubla
 */
class SelectedConnectionDialog extends Dialog {

    private final ConnectionFactory connectionFactory;
    private final String connName;
    private final IPreferenceStore prefStore;

    public SelectedConnectionDialog(Shell parentShell, IPreferenceStore prefStore, ConnectionFactory connectionFactory, String connName) {
        super(parentShell);
        this.prefStore = prefStore;
        this.connectionFactory = connectionFactory;
        this.connName = connName;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        connectionFactory.createComposite(container);
        connectionFactory.load(connName, prefStore);
        return container;
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (IDialogConstants.OK_ID == buttonId) {
            connectionFactory.save(connName, prefStore);
        }
        super.buttonPressed(buttonId);
    }
    
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Configure the connection");
    }
    
    @Override
    protected Point getInitialSize() {
        return new Point(300, 300);
    }
    
}
