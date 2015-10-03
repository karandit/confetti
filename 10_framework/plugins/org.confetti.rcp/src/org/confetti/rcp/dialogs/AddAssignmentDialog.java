package org.confetti.rcp.dialogs;

import static com.google.common.collect.Iterables.isEmpty;
import static com.google.common.collect.Iterables.toArray;

import java.util.List;

import org.confetti.core.Nameable;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Gabor Bubla
 */
public class AddAssignmentDialog extends Dialog {

    public AddAssignmentDialog(Shell parentShell) {
        super(parentShell);
    }
    
    @Override
    protected Point getInitialSize() {
        return new Point(800, 600);
    }
    
    @Override
    protected boolean isResizable() {
        return true;
    }
    
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Add assignment");
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        // do not create the OK and Cancel buttons
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout(3, true));
        
        //create labels
        Label informationLabel = new Label(container, SWT.NONE);
        informationLabel.setText(
                "Select a subject, one or more teacher and one or more student group. " + 
                "(Use the Ctrl and Shift keys for multiple selection)");
        GridDataFactory.fillDefaults().span(3, 1).applyTo(informationLabel);
        
        //create bold fonts for these 3 labels
        Label subjectLabel = new Label(container, SWT.NONE);
        FontDescriptor boldDescriptor = FontDescriptor.createFrom(subjectLabel.getFont()).setStyle(SWT.BOLD);
        Font boldFont = boldDescriptor.createFont(subjectLabel.getDisplay());
        subjectLabel.setFont(boldFont);
        subjectLabel.setText("Subjects");
        Label teacherLabel = new Label(container, SWT.NONE);
        teacherLabel.setText("Teachers");
        teacherLabel.setFont(boldFont);
        Label studentGroupLabel = new Label(container, SWT.NONE);
        studentGroupLabel.setText("Student groups");
        studentGroupLabel.setFont(boldFont);
        
        //create viewers
        TreeViewer subjectsViewer = new TreeViewer(container, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.FULL_SELECTION);
        subjectsViewer.setContentProvider(new EntityContentProvider());
        subjectsViewer.setLabelProvider(new EntityLabelProvider());
        subjectsViewer.setInput(ConfettiPlugin.getDefault().getDataProvider().getValue().getSubjects().getList());
        GridDataFactory.fillDefaults().grab(true, true).applyTo(subjectsViewer.getControl());
        
        TreeViewer teachersViewer = new TreeViewer(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.FULL_SELECTION);
        teachersViewer.setContentProvider(new EntityContentProvider());
        teachersViewer.setLabelProvider(new EntityLabelProvider());
        teachersViewer.setInput(ConfettiPlugin.getDefault().getDataProvider().getValue().getTeachers().getList());
        GridDataFactory.fillDefaults().grab(true, true).applyTo(teachersViewer.getControl());
        
        TreeViewer studentgroupsViewer = new TreeViewer(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.FULL_SELECTION);
        studentgroupsViewer.setContentProvider(new StudentGroupContentProvider());
        studentgroupsViewer.setLabelProvider(new EntityLabelProvider());
        studentgroupsViewer.setInput(ConfettiPlugin.getDefault().getDataProvider().getValue().getStudentGroups().getList());
        GridDataFactory.fillDefaults().grab(true, true).applyTo(studentgroupsViewer.getControl());
        
        //create button
        Button createButton = new Button(container, SWT.NONE);
        createButton.setText("&Create assignment");
        createButton.addSelectionListener(new CreateAssignmentSelectionListener(subjectsViewer, teachersViewer, studentgroupsViewer));
        parent.getShell().setDefaultButton(createButton);
        GridDataFactory.fillDefaults().span(3, 1).align(SWT.END, SWT.END).applyTo(createButton);
        
        return container;
    }
    
    private class EntityLabelProvider extends LabelProvider {
        @Override public Image getImage(Object element) { return super.getImage(element); }
        @Override
        public String getText(Object element) {
            if (element instanceof Nameable) {
                return ((Nameable) element).getName().getValue();
            } 
            return null;
        }
    }
    
    private class EntityContentProvider implements ITreeContentProvider {
        @Override public void dispose() { }
        @Override public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }
        @Override public Object[] getElements(Object inputElement) { return ((List<?>) inputElement).toArray(); }
        @Override public Object[] getChildren(Object parentElement) { return null; }
        @Override public Object getParent(Object element) { return null; }
        @Override public boolean hasChildren(Object element) { return false; }
    }
    
    private class StudentGroupContentProvider implements ITreeContentProvider {
        @Override public void dispose() { }
        @Override public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }
        @Override public Object[] getElements(Object parent)    { return ((List<?>) parent).toArray(); }
        @Override public Object getParent(Object child)         { return ((StudentGroup) child).getParent(); }
        @Override public boolean hasChildren(Object parent)     { return !isEmpty(((StudentGroup) parent).getChildren().getList()); }
        @Override public Object[] getChildren(Object parent)    { return toArray(((StudentGroup) parent).getChildren().getList(), StudentGroup.class); }
    }
    
    private class CreateAssignmentSelectionListener extends SelectionAdapter {

        private Viewer subjectsViewer;
        private Viewer teachersViewer;
        private Viewer studentgroupsViewer;

        public CreateAssignmentSelectionListener(Viewer subjectsViewer, Viewer teachersViewer, Viewer studentgroupsViewer) {
            this.subjectsViewer = subjectsViewer;
            this.teachersViewer = teachersViewer;
            this.studentgroupsViewer = studentgroupsViewer;
        }
        
        @Override
        public void widgetSelected(SelectionEvent e) {
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            
            //warning dialog if a viewer has no selection
            if (subjectsViewer.getSelection().isEmpty() || teachersViewer.getSelection().isEmpty() || studentgroupsViewer.getSelection().isEmpty()) {
                MessageDialog.openWarning(shell, 
                        "Wrong selection", "Please select a subject, one or more teacher and one or more student group.");
                return;
            }
            
            //collect the selections
            IStructuredSelection selection = (IStructuredSelection) subjectsViewer.getSelection();
            Subject subject = (Subject) selection.getFirstElement();
            selection = (IStructuredSelection) teachersViewer.getSelection();
            @SuppressWarnings("unchecked")
			List<Teacher> teachers = selection.toList();
            selection = (IStructuredSelection) studentgroupsViewer.getSelection();
            @SuppressWarnings("unchecked")
			List<StudentGroup> studentGroups = selection.toList();
            
            //create the assignment
            ConfettiPlugin.getDefault().getDataPersister().get().addAssignment(subject, teachers, studentGroups);
            MessageDialog.openInformation(shell, "Success", "Selected assignment succesfully added");
            
            //reset viewers' selection
//            subjectsViewer.setSelection(null);
//            teachersViewer.setSelection(null);
//            studentgroupsViewer.setSelection(null);
        }
    }
    
}
