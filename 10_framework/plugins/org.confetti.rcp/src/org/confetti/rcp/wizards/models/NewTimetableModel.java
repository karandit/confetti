package org.confetti.rcp.wizards.models;

import static org.confetti.rcp.ConfettiPlugin.IMG_BIG_DAY;
import static org.confetti.rcp.ConfettiPlugin.IMG_BIG_HOUR;
import static org.confetti.rcp.ConfettiPlugin.IMG_BIG_INSTITUTE;
import static org.confetti.rcp.wizards.models.NewEntityWizardModel.isDuplicated;
import static org.confetti.rcp.wizards.models.Problem.DUPLICATED_NAME;
import static org.confetti.rcp.wizards.models.Problem.OK;

import java.util.LinkedList;
import java.util.List;

import org.confetti.rcp.nls.Messages;
import org.confetti.util.Tuple;

/**
 * @author Bubla Gabor
 */
public class NewTimetableModel implements EditNameAndCommentModel {

	public static class NewEntryModel implements InsertEntriesModel, VerifyEntriesModel {

		private final List<Tuple<String, Problem>> mEntriesAndProblems = new LinkedList<>();
		private String mInsTitle;
		private String mInsDesc;
		private String mVerTitle;
		private String mVerDescr;
		private String mImageKey;
		private NewEntryModel(String insTitle, String insDesc, String verTitle, String verDescr, String imageKey) {
			mInsTitle = insTitle;
			mInsDesc = insDesc;
			mVerTitle = verTitle;
			mVerDescr = verDescr;
			mImageKey = imageKey;
		}
		//---------------------- InsertEntriesModel --------------------------------------------------------------------
		@Override public String getInsertEntriesPageTitle() { return mInsTitle; }
		@Override public String getInsertEntriesPageDescription() { return mInsDesc; }
		@Override
		public void addEntries(List<String> entries) {
			mEntriesAndProblems.clear();
			for (String entry : entries) {
				Problem state = isDuplicated(entries, entry) ? DUPLICATED_NAME : OK;
				mEntriesAndProblems.add(new Tuple<>(entry, state));
			}
		}
		//---------------------- VerifyEntriesModel --------------------------------------------------------------------
		@Override public String getVerifyEntriesPageTitle() 		{ return mVerTitle; }
		@Override public String getVerifyEntriesPageDescription() 	{ return mVerDescr; }
		@Override public List<Tuple<String, Problem>> getResult() 	{ return mEntriesAndProblems; }
		@Override public String getPageImageKey() 		{ return mImageKey; }
	}
	
	private String instituteName;
	private String comment;
	private NewEntryModel daysModel = new NewEntryModel(Messages.NewTimetableModel_Days_Title, 
			Messages.NewTimetableModel_Days_Description, 
			Messages.NewTimetableModel_Days_Summary, "", IMG_BIG_DAY); //$NON-NLS-1$
	private NewEntryModel hoursModel = new NewEntryModel(Messages.NewTimetableModel_Hours_Title, 
			Messages.NewTimetableModel_Hours_Description, 
			Messages.NewTimetableModel_Hours_Summary, "", IMG_BIG_HOUR); //$NON-NLS-1$
	
	@Override public String getName() 						{ return instituteName; }
	@Override public void setName(String name) 				{ this.instituteName = name; }
	@Override public String getComment() 					{ return comment; }
	@Override public void setComment(String comment) 		{ this.comment = comment; }
	@Override public String getImageKey() 					{ return IMG_BIG_INSTITUTE; }
	
	public NewEntryModel getDaysModel() { return daysModel; } 
	public NewEntryModel getHoursModel() { return hoursModel; }
	
}
