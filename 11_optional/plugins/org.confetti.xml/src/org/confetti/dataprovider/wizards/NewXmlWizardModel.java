package org.confetti.dataprovider.wizards;

import java.io.File;

import org.confetti.core.DataProvider;

/**
 * @author Gabor Bubla
 */
public class NewXmlWizardModel {

    private DataProvider dp;
    private File mFile;

    public NewXmlWizardModel(DataProvider dp) {
        this.dp = dp;
    }
    
    public DataProvider getDp() { return dp; }
    public File getFile()           { return mFile; }
    public void setFile(File file)  { mFile = file; }

}
