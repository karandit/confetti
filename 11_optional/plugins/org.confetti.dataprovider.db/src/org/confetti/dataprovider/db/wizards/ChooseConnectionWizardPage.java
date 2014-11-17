package org.confetti.dataprovider.db.wizards;

import static org.confetti.rcp.ConfettiPlugin.getImageDescriptor;

import org.confetti.rcp.ConfettiPlugin;
import org.confetti.util.Tuple;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Gabor Bubla
 */
public class ChooseConnectionWizardPage extends WizardPage {

	private final ChooseConnectionModel model;

	public ChooseConnectionWizardPage(ChooseConnectionModel model) {
		super("Choose", "Database", getImageDescriptor(ConfettiPlugin.IMG_BIG_DB));
		this.model = model;
		setDescription("Choose a connection");
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		final Composite compo = new Composite(parent, SWT.NONE);
		compo.setLayout(new GridLayout(2, false));
		
		Label label = new Label(compo, SWT.NONE);
		label.setText("Connection");
		
		ComboViewer combo = new ComboViewer(compo, SWT.READ_ONLY);
		combo.setContentProvider(ArrayContentProvider.getInstance());
		combo.setLabelProvider(new LabelProvider(){
		    @Override
		    public String getText(Object element) {
		        Tuple<String, String> tuple = (Tuple<String, String>) element;
		        return tuple.getFirst() + "(" + tuple.getSecond() + ")";
		    }
		});
		combo.setInput(ConfettiPlugin.getDefault().getConnectionSettings());
		combo.addSelectionChangedListener(new ISelectionChangedListener() {
            
            @Override
            public void selectionChanged(SelectionChangedEvent e) {
                if (!e.getSelection().isEmpty() && e.getSelection() instanceof IStructuredSelection) {
                    Object first = ((IStructuredSelection) e.getSelection()).getFirstElement();
                    ChooseConnectionWizardPage.this.model.setConnectionName((Tuple<String, String>) first);
                    setPageComplete(true);
                }
            }
        }); 
		setControl(compo);
	}

}
