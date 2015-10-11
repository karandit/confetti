package org.confetti.rcp.wizards.pages;

import static org.confetti.rcp.ConfettiPlugin.IMG_BIG_ASSIGNMENT;
import static org.confetti.rcp.ConfettiPlugin.getImageDescriptor;

import java.util.function.Function;
import java.util.function.Supplier;

import org.confetti.core.DataProvider;
import org.confetti.observable.ObservableList;
import org.confetti.rcp.views.EntityTableLabelProvider;
import org.confetti.rcp.views.StudentGroupContentProvider;
import org.confetti.rcp.wizards.models.AssignmentModel;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class AssignmentParticipantWizardPage extends ModelableWizardPage<AssignmentModel> 
implements IWizardPageNavigatable {

	private static final int VIEWER_STYLE = SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.FULL_SELECTION;

	private TableViewer subjectsViewer;
	private TableViewer teachersViewer;
	private TreeViewer studentGroupsViewer;

	public AssignmentParticipantWizardPage(AssignmentModel model) {
		super("assignment-participants", "Assignment", getImageDescriptor(IMG_BIG_ASSIGNMENT), model);
		setDescription(
				"Select a subject, one or more teacher and one or more student group. " + 
                "Use the Ctrl and Shift keys for multiple selection.");
	}

	@Override public void pageShowed() { }

	@SuppressWarnings("unchecked")
	@Override
	public void pageHid() {
		getModel().setSubjects(((IStructuredSelection) subjectsViewer.getSelection()).toList());
		getModel().setTeachers(((IStructuredSelection) teachersViewer.getSelection()).toList());
		getModel().setStudentGroups(((IStructuredSelection) studentGroupsViewer.getSelection()).toList());
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(3, true));
        
        //create labels
        newLabel(container, "Subjects");
        newLabel(container, "Teachers");
        newLabel(container, "Student groups");
        
        //create viewers
        IContentProvider tableContentProvider = new ArrayContentProvider();
        EntityTableLabelProvider labelProvider = new EntityTableLabelProvider();
        Supplier<TableViewer> tableViewerFactory = () -> new TableViewer(container, VIEWER_STYLE);

		subjectsViewer = newViewer(dp -> dp.getSubjects(), tableViewerFactory, tableContentProvider, labelProvider);
		teachersViewer = newViewer(dp -> dp.getTeachers(), tableViewerFactory, tableContentProvider, labelProvider);
		studentGroupsViewer = newViewer(dp -> dp.getStudentGroups(), () -> new TreeViewer(container, VIEWER_STYLE), 
        		StudentGroupContentProvider.INSTANCE, labelProvider);
        
		setControl(container);
	}

	private static void newLabel(Composite container, String text) {
		Label label = new Label(container, SWT.NONE);
        label.setText(text);
	}

	private <T extends StructuredViewer> T newViewer(Function<DataProvider, ObservableList<?>> items, 
			Supplier<T> factory, IContentProvider contentProvider, IBaseLabelProvider labelProvider) {
		T viewer = factory.get();
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
        viewer.setInput(items.apply(getModel().getDataProvider()).getList());
		return viewer;
	}

}
