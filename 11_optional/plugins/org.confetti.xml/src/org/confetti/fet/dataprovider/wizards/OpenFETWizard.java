package org.confetti.fet.dataprovider.wizards;

import static org.confetti.xml.core.InstituteXmlRelease.v5_23_4;
import static org.confetti.xml.core.InstituteXmlRelease.v5_24_0;

import org.confetti.fet.dataprovider.FETDataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.xml.FAOException;
import org.confetti.xml.InstituteHeaderFAO;
import org.confetti.xml.core.AbstractInstituteXml;
import org.confetti.xml.core.InstituteXmlRelease;
import org.confetti.xml.header.InstituteHeaderXml;
import org.eclipse.jface.wizard.Wizard;
import org.osgi.framework.Version;

/**
 * @author Bubla Gabor
 */
public class OpenFETWizard extends Wizard {

	private final OpenFETWizardModel model = new OpenFETWizardModel();
	
	@Override
	public void addPages() {
		setWindowTitle("Open");
		addPage(new ChooseFETFileWizardPage(model));
	}

	@Override
	public boolean performFinish() {
		try {
			InstituteHeaderXml header = new InstituteHeaderFAO().importFrom(model.getFile());
			Version semver = new Version(header.getVersion());
			
			InstituteXmlRelease<?> release = semver.compareTo(new Version(5, 24, 0)) < 0 ? v5_23_4 : v5_24_0;
			FETDataProvider dp = newDataProvider(release);
            ConfettiPlugin.getDefault().setDataProvider(dp, dp);
		} catch (FAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private <T extends AbstractInstituteXml> FETDataProvider newDataProvider(InstituteXmlRelease<T> rel) 
			throws FAOException {
		T xml = rel.newFAO().importFrom(model.getFile());
		return new FETDataProvider(xml, rel, model.getFile());
	}

}
