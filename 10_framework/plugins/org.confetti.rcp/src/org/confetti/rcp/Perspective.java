package org.confetti.rcp;

import org.confetti.rcp.views.AssignmentsView;
import org.confetti.rcp.views.StudentGroupsView;
import org.confetti.rcp.views.SubjectsView;
import org.confetti.rcp.views.TeachersView;
import org.confetti.rcp.views.View;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

		IFolderLayout folderLeft = layout.createFolder("entities", IPageLayout.LEFT, 0.30f, editorArea);
		folderLeft.addView(TeachersView.ID);
		folderLeft.addView(SubjectsView.ID);
		folderLeft.addView(StudentGroupsView.ID);
//		folderLeft.addView(NavigationView.ID);

//		layout.addStandaloneView(NavigationView.ID, true, IPageLayout.LEFT, 0.25f, editorArea);
		IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 0.5f, editorArea);
		folder.addPlaceholder(View.ID + ":*");
//		folder.addView(View.ID);
		folder.addView(AssignmentsView.ID);

//		layout.getViewLayout(NavigationView.ID).setCloseable(false);
	}
}
