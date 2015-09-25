package org.confetti.rcp.commands;

import static org.confetti.rcp.wizards.WizardUtil.watchWizardDialog;

import java.util.List;

import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.extensions.EngineWizardDescr;
import org.confetti.rcp.extensions.EngineWizardFactory;
import org.confetti.rcp.extensions.EngineWizardRegistry;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.views.AbstractEntityTableView;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * @author Gabor Bubla
 */
public class GenerateTimetableCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		//dialog with all the extensions
		ListDialog dlg = new ListDialog(shell) {
			@Override
			protected Control createDialogArea(Composite container) {
				Control createDialogArea = super.createDialogArea(container);
				Table table = getTableViewer().getTable();
				table.setHeaderVisible(true);
				AbstractEntityTableView.createColumn(table, Messages.General_Name, 150);
				AbstractEntityTableView.createColumn(table, Messages.GenerateTimetableCommand_Column_Author, 100);
				return createDialogArea;
			}
			@Override 
			protected int getTableStyle() {
				return super.getTableStyle() | SWT.FULL_SELECTION;
			}
		};
		dlg.setTitle(Messages.GenerateTimetableCommand_Title);
		dlg.setMessage(Messages.GenerateTimetableCommand_Message);
		dlg.setContentProvider(new ArrayContentProvider());
		dlg.setLabelProvider(new EngineLabelProvider());
		List<EngineWizardDescr> extensions = EngineWizardRegistry.INSTANCE.getExtensions();
		dlg.setInput(extensions);
		if (Window.OK != dlg.open()) {
			return null;
		}
		Object[] selected = dlg.getResult();
		if (selected == null || selected.length == 0) {
			return null;
		}
		
		//open the selected extension's wizard
		EngineWizardDescr selectedDescr = (EngineWizardDescr) selected[0];
		EngineWizardFactory wizardFactory = selectedDescr.getWizardFactory();
		WizardDialog dialog = new WizardDialog(shell, wizardFactory.createWizard(ConfettiPlugin.getDefault().getDataProvider().getValue()));
		watchWizardDialog(dialog);
		dialog.open();
		
		return null;
	}

	@Override public boolean isEnabled() { return ConfettiPlugin.getDefault().getDataProvider().getValue() == null ? false : true; }
	
	private class EngineLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override public Image getColumnImage(Object element, int columnIndex) { return null; }

		@Override
		public String getColumnText(Object element, int columnIndex) {
			EngineWizardDescr desc = (EngineWizardDescr) element;
			switch (columnIndex) {
				case 0:  return desc.getName();
				default: return desc.getAuthor();
			}
		}
		
	}
	
}
