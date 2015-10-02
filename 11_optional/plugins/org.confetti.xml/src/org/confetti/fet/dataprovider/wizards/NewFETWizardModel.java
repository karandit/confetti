package org.confetti.fet.dataprovider.wizards;

import java.io.File;

import org.confetti.core.DataProvider;

/**
 * @author Gabor Bubla
 */
public class NewFETWizardModel {

    private DataProvider dp;
    private File mFile;

    public NewFETWizardModel(DataProvider dp) {
        this.dp = dp;
    }
    
    public DataProvider getDp() { return dp; }
    public File getFile()           { return mFile; }
    public void setFile(File file)  { mFile = file; }

}
