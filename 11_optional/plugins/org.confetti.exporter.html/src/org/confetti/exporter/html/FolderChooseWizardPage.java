package org.confetti.exporter.html;

import java.io.File;

import org.confetti.exporter.html.nls.Messages;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Gabor Bubla
 */
public class FolderChooseWizardPage extends WizardPage {

    private ExportTimetableModel model;

    public FolderChooseWizardPage(ExportTimetableModel model) {
        super("Export", Messages.FolderChooseWizardPage_Name, null); //$NON-NLS-1$
        setDescription(Messages.FolderChooseWizardPage_Description);
        this.model = model;
        setPageComplete(false);
    }

    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        final DirectoryFieldEditor fieldEditor = new DirectoryFieldEditor("DirectoryFieldEditor", Messages.FolderChooseWizardPage_Folder, composite); //$NON-NLS-1$
        fieldEditor.getTextControl(composite).addModifyListener((ModifyEvent e) -> {
                String stringValue = fieldEditor.getStringValue();
                if (null != stringValue && !stringValue.equals("")) { //$NON-NLS-1$
                    File folderPath = new File(stringValue);
                    if (folderPath.exists() && folderPath.isDirectory()) {
                        setPageComplete(true);
                        model.setFolderPath(folderPath);
                    } else {
                        setPageComplete(false);
                    }
                } else {
                    setPageComplete(false);
                }
            }
        );
        setControl(composite);
    }

}
