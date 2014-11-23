package org.confetti.dummy.newwizard;

import java.util.Arrays;

import org.confetti.core.DataProvider;
import org.confetti.rcp.extensions.NewWizardFactory;
import org.eclipse.jface.wizard.IWizard;

public class DummyNewWizardFactory implements NewWizardFactory {

    @Override
    public IWizard createWizard(DataProvider dp) {
        return new DummyNewWizard(dp.getName().getValue(), "default", Arrays.asList(""), Arrays.asList(""));
    }

}
