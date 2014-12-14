package org.confetti.rcp.wizards;

import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.core.Teacher;
import org.confetti.observable.ObservableList;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.wizards.models.ExportTimetableModel;
import org.confetti.rcp.wizards.pages.FolderChooseWizardPage;
import org.confetti.util.Tuple;
import org.eclipse.jface.wizard.Wizard;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * @author Gabor Bubla
 */
public class ExportTimetableWizard extends Wizard {

    private ExportTimetableModel model;

    public ExportTimetableWizard() {
        model = new ExportTimetableModel(null);
        setWindowTitle("Export timetables");
    }
    
    @Override
    public void addPages() {
        addPage(new FolderChooseWizardPage(model));
    }
    
    @Override
    public boolean performFinish() {
        exportTimetables(new File(model.getFolderPath(), "timetables"));
        return true;
    }

    private void exportTimetables(File folderPath) {
        //get DataProvider's days, hours
        DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        List<String> days = newArrayList(Iterables.transform(dp.getDays().getList(), new Function<Day, String>() {
            @Override
            public String apply(Day day) {
                return day.getName().getValue();
            }
        }));
        List<String> hours = newArrayList(Iterables.transform(dp.getHours().getList(), new Function<Hour, String>() {
            @Override
            public String apply(Hour hour) {
                return hour.getName().getValue();
            }
        }));
        
        //get the solution slots
        Map<Assignment, SolutionSlot> assignmentSolutionSlot = new HashMap<>();
        for (SolutionSlot slot : dp.getSolution().getValue()) {
            assignmentSolutionSlot.put(slot.getAssignment(), slot);
        }
        
        //get the teachers' and student groups' unsorted solution slots 
        List<Teacher> teachersList = newArrayList(dp.getTeachers().getList());
        List<StudentGroup> studentGroupsList = newArrayList(dp.getStudentGroups().getList());
        Map<Teacher, List<SolutionSlot>> teachersTimetable = new HashMap<>();
        Map<StudentGroup, List<SolutionSlot>> studentGroupsTimetable = new HashMap<>();
        for (Teacher teacher : teachersList) {
            List<SolutionSlot> solutionSlots = new ArrayList<>();
            for (Assignment assignment : teacher.getAssignments().getList()) {
                SolutionSlot solutionSlot = assignmentSolutionSlot.get(assignment);
                solutionSlots.add(solutionSlot);
            }
            teachersTimetable.put(teacher, solutionSlots);
        }
        for (StudentGroup studentGroup : studentGroupsList) {
            List<SolutionSlot> solutionSlots = new ArrayList<>();
            for (Assignment assignment : studentGroup.getAssignments().getList()) {
                SolutionSlot solutionSlot = assignmentSolutionSlot.get(assignment);
                solutionSlots.add(solutionSlot);
            }
            studentGroupsTimetable.put(studentGroup, solutionSlots);
        }
        
        //sort the solution slots and put empty string in empty slots
        exportToHTML(days, hours, teachersTimetable, studentGroupsTimetable);
    }
    
    private void exportToHTML(List<String> days, List<String> hours, Map<Teacher, List<SolutionSlot>> teachersTimetable,
            Map<StudentGroup, List<SolutionSlot>> studentGroupsTimetable
    ) {
        List<Tuple<String, List<List<String>>>> teachersSortedTimetable = new ArrayList<>();
        List<Tuple<String, List<List<String>>>> studentGroupsSortedTimetable = new ArrayList<>();
        
        for (Map.Entry<Teacher, List<SolutionSlot>> entry : teachersTimetable.entrySet()) {
            String teacherName = entry.getKey().getName().getValue();
            List<List<String>> teacherTimetable = createEmptyTimeTable(days.size(), hours.size());
            for (SolutionSlot solutionSlot : entry.getValue()) {
                teacherTimetable.get(days.indexOf(solutionSlot.getDay().getName().getValue())).set(hours.indexOf(solutionSlot.getHour().getName().getValue()),
                        solutionSlot.getAssignment().getSubject().getName().getValue()
                        + "\n"
                        + getNames(solutionSlot.getAssignment().getStudentGroups()));
                
            }
            Tuple<String, List<List<String>>> teacherTuple = new Tuple(teacherName, teacherTimetable);
            teachersSortedTimetable.add(teacherTuple);
        }
        for (Map.Entry<StudentGroup, List<SolutionSlot>> entry : studentGroupsTimetable.entrySet()) {
            String studentGroupName = entry.getKey().getName().getValue();
            List<List<String>> studentGroupTimetable = createEmptyTimeTable(days.size(), hours.size());
            for (SolutionSlot solutionSlot : entry.getValue()) {
                studentGroupTimetable.get(days.indexOf(solutionSlot.getDay().getName().getValue())).set(hours.indexOf(solutionSlot.getHour().getName().getValue()),
                        solutionSlot.getAssignment().getSubject().getName().getValue()
                        + "\n"
                        + getNames(solutionSlot.getAssignment().getTeachers()));
                
            }
            Tuple<String, List<List<String>>> studentGroupTuple = new Tuple(studentGroupName, studentGroupTimetable);
            studentGroupsSortedTimetable.add(studentGroupTuple);
        }
    }

    private List<List<String>> createEmptyTimeTable(int daysSize, int hoursSize) {
        List<List<String>> emptyTimetable = new ArrayList<List<String>>();
        for (int i = 0; i < daysSize; i++) {
            List<String> hoursList = new ArrayList<>();
            for (int j = 0; j < hoursSize; j++) {
                hoursList.add("");
            }
            emptyTimetable.add(hoursList);
        }
        return emptyTimetable;
    }
    
    private static List<String> getNames(ObservableList<? extends Nameable> items) {
        List<String> names = new ArrayList<>();
        for (Nameable nameable : items.getList()) {
            names.add(nameable.getName().getValue());
        }
        return names;
    }

}
