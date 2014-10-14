package org.confetti.rcp.dialogs;

import org.confetti.core.Nameable;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Gabor Bubla
 */
public class AddAssignmentDialog extends Dialog {

    public AddAssignmentDialog(Shell parentShell) {
        super(parentShell);
    }
    
    @Override
    protected Point getInitialSize() {
        return new Point(800, 600);
    }
    
    @Override
    protected boolean isResizable() {
        return true;
    }
    
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Add assignment");
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        
        ListViewer subjectsViewer = new ListViewer(container, SWT.BORDER | SWT.SINGLE);
        subjectsViewer.setContentProvider(new ArrayContentProvider());
        subjectsViewer.setLabelProvider(new EntityLabelProvider());
        subjectsViewer.setInput(ConfettiPlugin.getDefault().getDataProvider().getValue().getSubjects().getList());

        
        
        Button addButton = new Button(container, SWT.NONE);
        addButton.setText("Create assignment");
        return container;
    }
    
    private class EntityLabelProvider extends LabelProvider {
        @Override public Image getImage(Object element) { return super.getImage(element); }
        @Override
        public String getText(Object element) {
            if (element instanceof Nameable) {
                return ((Nameable) element).getName().getValue();
            } 
            return null;
        }
    }
    
}
