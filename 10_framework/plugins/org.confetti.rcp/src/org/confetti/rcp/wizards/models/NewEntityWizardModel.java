package org.confetti.rcp.wizards.models;

import static com.google.common.collect.Lists.transform;
import static org.confetti.rcp.wizards.models.Problem.ALREADY_EXISTS;
import static org.confetti.rcp.wizards.models.Problem.DUPLICATED_NAME;
import static org.confetti.rcp.wizards.models.Problem.OK;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.confetti.rcp.nls.Messages;
import org.confetti.util.Tuple;

public class NewEntityWizardModel<T> implements InsertEntriesModel, VerifyEntriesModel {
	
	public interface EntityCreator<T> {
		void createEntities(List<String> names);
	}
	
	//------------------------ fields ----------------------------------------------------------------------------------
	private final String mWizardTitle;
	private final String mAddNamePageDescr;
	private final String mVerifyNamePageDescr;
	
	private final List<Tuple<String, Problem>> mNamesAndProblems = new ArrayList<>();
	private final List<String> mOriginalNames;
	private final EntityCreator<T> mCreator;
	private String mImageKey;
	
	//------------------------ constructors ----------------------------------------------------------------------------
	public NewEntityWizardModel(final List<String> originalNames, EntityCreator<T> creator,
			final String wizardTitle, final String addNamePageDescr, final String verifyNamePageDescr,
			final String imageKey) {
		this.mOriginalNames = originalNames;
		this.mCreator = creator;
		this.mWizardTitle = wizardTitle;
		this.mAddNamePageDescr = addNamePageDescr;
		this.mVerifyNamePageDescr = verifyNamePageDescr;
		this.mImageKey = imageKey;
	}

	//------------------------ InsertEntriesModel ----------------------------------------------------------------------
	@Override public String getInsertEntriesPageDescription() 	{ return mAddNamePageDescr; }
	@Override public String getInsertEntriesPageTitle() 		{ return Messages.NewEntityWizardModel_Names; }
	@Override public String getInsertEntriesPageImageKey() 		{ return mImageKey; }

	@Override
	public void addEntries(List<String> names) {
		mNamesAndProblems.clear();
		for (String name : names) {
			Problem state = mOriginalNames.contains(name) 
					? ALREADY_EXISTS 
					: isDuplicated(names, name) 
							? DUPLICATED_NAME 
							: OK;
			mNamesAndProblems.add(new Tuple<>(name, state));
		}
	}

	//------------------------ VerifyEntriesModel ----------------------------------------------------------------------
	@Override public String getVerifyEntriesPageTitle() 		{ return Messages.NewEntityWizardModel_Summary; }
	@Override public String getVerifyEntriesPageDescription() 	{ return mVerifyNamePageDescr; }
	@Override public List<Tuple<String, Problem>> getResult() 	{ return mNamesAndProblems; }

	//------------------------ NewEntityWizardModel --------------------------------------------------------------------
	public String getWizardTitle() { return mWizardTitle; }
	public void createEntities() {
	    mCreator.createEntities(transform(getResult(), (Tuple<String, Problem> tuple) -> tuple.getFirst()));
	}

	//------------------------ helpers ---------------------------------------------------------------------------------
	public static boolean isDuplicated(List<String> names, String name) {
		List<String> namesMinusName = new LinkedList<>(names);
		namesMinusName.remove(name);
		return namesMinusName.contains(name);
	}

}
