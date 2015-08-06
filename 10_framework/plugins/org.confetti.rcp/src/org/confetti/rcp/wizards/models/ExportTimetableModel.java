package org.confetti.rcp.wizards.models;

import java.io.File;

/**
 * @author Gabor Bubla
 */
public class ExportTimetableModel {

    private File folderPath;

    public ExportTimetableModel(File folderPath) {
        this.folderPath = folderPath;
    }
    
    public File getFolderPath() {
        return folderPath;
    }
    
    public void setFolderPath(File folderPath) {
        this.folderPath = folderPath;
    }
    
}
