package org.confetti.dataprovider.wizards;

import static com.google.common.io.Files.getFileExtension;
import static org.confetti.rcp.ConfettiPlugin.getImageDescriptor;

import java.io.File;

import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Gabor Bubla
 */
public class SaveFETFileWizardPage extends WizardPage {

    private NewFETWizardModel model;

    public SaveFETFileWizardPage(NewFETWizardModel model) {
        super("Choose", "FET", getImageDescriptor(ConfettiPlugin.IMG_BIG_FILE));
        this.model = model;
        setDescription("Choose a location and a name for a FET file");
        setPageComplete(false);
    }

    @Override
    public void createControl(Composite parent) {
        final Composite compo = new Composite(parent, SWT.NONE);
        compo.setLayout(new GridLayout(3, false));
        
        Label label = new Label(compo, SWT.NONE);
        label.setText("File");
        
        final Text text = new Text(compo, SWT.BORDER);
        GridDataFactory.fillDefaults().grab(true, false).applyTo(text);
        
        //decorator for text, starts with warning because the text starts empty
        final ControlDecoration controlDecoration = new ControlDecoration(text, SWT.RIGHT | SWT.TOP);
        controlDecoration.setDescriptionText("Please enter a new .fet file");
        controlDecoration.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage());
        
        //listener which validates the text: if the input is a non-existing file, and it's extension is fet:
        //hides the decorator + puts the file in the model + sets the page to complete
        text.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                if (e.getSource() instanceof Text) {
                    String text = ((Text) e.getSource()).getText();
                    File f = new File(text);
                    if (!f.exists() && getFileExtension(text).equals("fet")) {
                        controlDecoration.hide();
                        model.setFile(f);
                        setPageComplete(true);
                    } else {
                        controlDecoration.show();
                        model.setFile(null);
                        setPageComplete(false);
                    }
                }
            }
        });
        
        //button opens a save file dialog and sets the text to the selected file with path with .fet appended to it
        Button saveButton = new Button(compo, SWT.NONE);
        saveButton.setText("Save...");
        GridDataFactory.fillDefaults().indent(5, 0).applyTo(saveButton);
        saveButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
                dialog.setFilterExtensions(new String[] {"*.fet"});
                String fileName = dialog.open();
                if (fileName != null) {
                    fileName = fileName.endsWith(".fet") ? fileName : fileName + ".fet";
                    text.setText(fileName);
                }
            }
        });
        
        setControl(compo);
    }

}
