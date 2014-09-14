package org.confetti.fet.engine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author Gabor Bubla
 */
public class FETEngineWizard extends Wizard {

	@Override
	public void addPages() {
		setWindowTitle("Generate");
		addPage(new FETEngineWizardPage("FET Wizard Page"));
	}
	
	@Override
	public boolean performFinish() {
		List<String> command = new ArrayList<String>();
	    command.add("d:\\Apps\\fet-5.22.0\\fet-cl.exe");
	    command.add("--inputfile=d:\\Apps\\fet-5.22.0\\examples\\Denmark\\small-school.fet");
	    command.add("--outputdir=d:\\GitHub\\results\\");
		ProcessBuilder builder = new ProcessBuilder(command);
		
		try {
			final Process process = builder.start();
		    InputStream is = process.getInputStream();
		    InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader br = new BufferedReader(isr);
		    String line;
		    while ((line = br.readLine()) != null) {
		      System.out.println(line);
		    }
		    return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
