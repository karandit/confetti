package org.confetti.rcp;

import org.confetti.rcp.views.AssignmentsView;
import org.confetti.rcp.views.ConstraintsView;
import org.confetti.rcp.views.InstituteView;
import org.confetti.rcp.views.RoomsView;
import org.confetti.rcp.views.StudentGroupsView;
import org.confetti.rcp.views.SubjectsView;
import org.confetti.rcp.views.TeachersView;
import org.confetti.rcp.views.TimeTableView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

		IFolderLayout folderEntities = layout.createFolder("entities", IPageLayout.LEFT, 0.30f, editorArea);
		folderEntities.addView(SubjectsView.ID);
		folderEntities.addView(TeachersView.ID);
		folderEntities.addView(StudentGroupsView.ID);
		folderEntities.addView(RoomsView.ID);
		folderEntities.addView(InstituteView.ID);
		
		IFolderLayout folderConstraints = layout.createFolder("constraints", IPageLayout.BOTTOM, 0.60f, "entities");
		folderConstraints.addView(ConstraintsView.ID);

		IFolderLayout folderAssignments = layout.createFolder("assignments", IPageLayout.RIGHT, 0.70f, editorArea);
		folderAssignments.addView(AssignmentsView.ID);
		
		IFolderLayout folderTimeTable = layout.createFolder("timetable", IPageLayout.BOTTOM, 0.50f, "assignments");
		folderTimeTable.addView(TimeTableView.ID);

	}
}
