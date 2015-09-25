package org.confetti.rcp;

import org.confetti.rcp.views.AssignmentsView;
import org.confetti.rcp.views.ConstraintsView;
import org.confetti.rcp.views.InstituteView;
import org.confetti.rcp.views.RoomsView;
import org.confetti.rcp.views.StudentGroupsView;
import org.confetti.rcp.views.SubjectsView;
import org.confetti.rcp.views.TeachersView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

		IFolderLayout folderEntities = layout.createFolder("entities", IPageLayout.LEFT, 0.30f, editorArea); //$NON-NLS-1$
		folderEntities.addView(InstituteView.ID);
		folderEntities.addView(SubjectsView.ID);
		folderEntities.addView(TeachersView.ID);
		folderEntities.addView(StudentGroupsView.ID);
		folderEntities.addView(RoomsView.ID);
		
		IFolderLayout folderConstraints = layout.createFolder("constraints", IPageLayout.BOTTOM, 0.60f, "entities"); //$NON-NLS-1$ //$NON-NLS-2$
		folderConstraints.addView(ConstraintsView.ID);

		IFolderLayout folderAssignments = layout.createFolder("assignments", IPageLayout.RIGHT, 0.70f, editorArea); //$NON-NLS-1$
		folderAssignments.addView(AssignmentsView.ID);

	}
}
