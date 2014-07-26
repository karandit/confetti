package org.confetti.rcp.wizards;

import static org.confetti.rcp.wizards.NewEntityWizardModel.Problem.DUPLICATED_NAME;
import static org.confetti.rcp.wizards.NewEntityWizardModel.Problem.OK;

import java.util.ArrayList;
import java.util.List;

import org.confetti.util.Tuple;

public class NewEntityWizardModel<T> {
	
	public interface EntityCreator<T> {
		T createEntity(String name);
	}
	public enum Problem {
		OK("OK"),
		ALREADY_EXISTS("Already exists"),
		DUPLICATED_NAME("Duplicated name");
		
		private String mDesc;
		Problem(String desc) {
			mDesc = desc;
		}

		public String getDescription() { return mDesc; }
	}
	
	private final String mWizardTitle;
	private final String mAddNamePageDescr;
	private final String mVerifyNamePageDescr;
	
	private final List<Tuple<String, Problem>> mNamesAndProblems = new ArrayList<>();
	private final List<String> mOriginalNames;
	private final EntityCreator<T> mCreator;

	public NewEntityWizardModel(final List<String> originalNames, EntityCreator<T> creator,
			final String wizardTitle, final String addNamePageDescr, final String verifyNamePageDescr) {
		this.mOriginalNames = originalNames;
		this.mCreator = creator;
		this.mWizardTitle = wizardTitle;
		this.mAddNamePageDescr = addNamePageDescr;
		this.mVerifyNamePageDescr = verifyNamePageDescr;
	}
	
	public List<Tuple<String, Problem>> getItems() {
		return mNamesAndProblems;
	}

	public void addNames(List<String> names) {
		mNamesAndProblems.clear();
		for (String name : names) {
			Problem state = mOriginalNames.contains(name) ? DUPLICATED_NAME : OK;
			mNamesAndProblems.add(new Tuple<>(name, state));
		}
	}

	public String getWizardTitle() { return mWizardTitle; }
	public String getAddNamePageDescription() { return mAddNamePageDescr; }
	public String getVerifyNamePageDescription() { return mVerifyNamePageDescr; }

	public void createEntities() {
		for (Tuple<String, Problem> tuple : getItems()) {
			mCreator.createEntity(tuple.getFirst());
		}
	}
}
