package org.confetti.rcp.commands;

import static com.google.common.collect.Iterables.transform;
import static org.confetti.rcp.wizards.WizardUtil.watchWizardDialog;

import java.util.LinkedList;
import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Entity;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.confetti.rcp.extensions.NewWizardDescr;
import org.confetti.rcp.extensions.NewWizardFactory;
import org.confetti.rcp.extensions.NewWizardRegistry;
import org.confetti.rcp.wizards.NewTimetableWizard;
import org.confetti.rcp.wizards.models.NewTimetableModel;
import org.confetti.rcp.wizards.models.NewTimetableModel.NewEntryModel;
import org.confetti.rcp.wizards.models.Problem;
import org.confetti.util.Tuple;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * @author Gabor Bubla
 */
public class NewWizardCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        //wizard for getting the Institute name, comment, List of days, List of hours
        NewTimetableModel model = new NewTimetableModel();
        {
            WizardDialog dialog = new WizardDialog(shell, new NewTimetableWizard(model));
            watchWizardDialog(dialog);
            dialog.setTitle("New...");
            if (Window.OK != dialog.open()) {
                return null;
            }
        }
        
        //dialog with all the extensions
        ListDialog dlg = new ListDialog(shell);
        dlg.setContentProvider(new ArrayContentProvider());
        dlg.setLabelProvider(new LabelProvider());
        dlg.setTitle("New");
        dlg.setMessage("Choose an input");
        List<NewWizardDescr> extensions = NewWizardRegistry.INSTANCE.getExtensions();
        dlg.setInput(extensions); 
        if (Window.OK != dlg.open()) {
            return null;
        }
        Object[] selected = dlg.getResult();
        if (selected == null || selected.length == 0) {
            return null;
        }
        //open the selected extension's wizard
        NewWizardDescr selectedDescr = (NewWizardDescr) selected[0];
        NewWizardFactory wizardFactory = selectedDescr.getWizardFactory();
        List<String> days = getEntries(model.getDaysModel());
        List<String> hours = getEntries(model.getHoursModel());
        
        EmptyDataProvider dp = new EmptyDataProvider(model.getName(), model.getComment(), days, hours);
        WizardDialog dialog = new WizardDialog(shell, wizardFactory.createWizard(dp));
        watchWizardDialog(dialog);
        dialog.open();
        
        return null;
    }
    
    private List<String> getEntries(NewEntryModel model) {
        List<String> res = new LinkedList<>();
        for (Tuple<String, Problem> tuple : model.getResult()) {
            res.add(tuple.getFirst());
        }
        return res;
    }

    private static class EmptyDataProvider implements DataProvider {

        private final ValueMutator<String> name;
        private final ValueMutator<String> comment;
        private final ListMutator<Day> days;
        private final ListMutator<Hour> hours;
        
        private static class UserInputDay implements Day {
            private final ValueMutator<String> name;
            UserInputDay(String value) { this.name = new ValueMutator<>(this, value); }
            @Override public ObservableValue<String> getName() { return name.getObservableValue(); }
        }
        private static class UserInputHour implements Hour {
            private final ValueMutator<String> name;
            UserInputHour(String value) { this.name = new ValueMutator<>(this, value); }
            @Override public ObservableValue<String> getName() { return name.getObservableValue(); }
        }
        
        public EmptyDataProvider(String name, String comment, Iterable<String> days, Iterable<String> hours) {
            this.name = new ValueMutator<>(this, name);
            this.comment = new ValueMutator<>(this, comment);
            this.days = new ListMutator<>(transform(days, x -> new UserInputDay(x)));
            this.hours = new ListMutator<>(transform(hours, x -> new UserInputHour(x)));
        }
        
        @Override public ObservableValue<String> getName() { return name.getObservableValue(); }
		@Override public ObservableValue<String> getComment() { return comment.getObservableValue(); }
        @Override public ObservableList<Day> getDays() { return days.getObservableList(); }
        @Override public ObservableList<Hour> getHours() { return hours.getObservableList(); }
        @Override public String getInformation() { return null; }
        //--------------------------not used----------------------------------------------------------------------------
        @Override public ObservableList<Subject> getSubjects() { return null; }
        @Override public ObservableList<Teacher> getTeachers() { return null; }
        @Override public ObservableList<StudentGroup> getStudentGroups() { return null; }
        @Override public ObservableList<Room> getRooms() { return null; }
        @Override public ObservableList<Assignment> getAssignments() { return null; }
        @Override public ObservableList<Constraint> getConstraints() { return null; }
        @Override public ObservableList<Tag> getTags() { return null; }
        @Override public ObservableValue<Iterable<SolutionSlot>> getSolution() { return null; }
        @Override public void addSubjects(List<String> names) { }
        @Override public void addTeachers(List<String> names) { }
        @Override public void addStudentGroups(StudentGroup parent, List<String> names) { }
        @Override public void addRooms(List<String> names) { }
        @Override public Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups) { return null; }
        @Override public void addConstraint(String type, ConstraintAttributes attrs) { }
        @Override public void setSolution(Iterable<SolutionSlot> solution) { }
        @Override public void removeSubjects(List<Subject> subjects) { }
        @Override public void removeTeachers(List<Teacher> teachers) { }
        @Override public void removeStudentGroups(List<StudentGroup> studentGroups) { }
        @Override public void removeRooms(List<Room> rooms) { }
        @Override public void removeAssignment(Assignment assignment) { }
        @Override public void rename(Entity entity, String newName) { }
		@Override public void updateConstraint(Constraint constraint, ConstraintAttributes attrs) { }
		@Override public void updateInstituteNameAndComment(String newName, String newComment) { }
    }

}
