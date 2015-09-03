package org.confetti.rcp.wizards.pages;

import java.io.File;

import org.confetti.rcp.wizards.models.ExportTimetableModel;
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
        super("Export", "Export", null);
        setDescription("Choose a folder to export to");
        this.model = model;
        setPageComplete(false);
    }

    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        final DirectoryFieldEditor fieldEditor = new DirectoryFieldEditor("DirectoryFieldEditor", "Directory:", composite);
        fieldEditor.getTextControl(composite).addModifyListener((ModifyEvent e) -> {
                String stringValue = fieldEditor.getStringValue();
                if (null != stringValue && !stringValue.equals("")) {
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
