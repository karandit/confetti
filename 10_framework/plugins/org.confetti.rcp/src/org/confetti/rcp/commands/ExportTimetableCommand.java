package org.confetti.rcp.commands;

import static com.google.common.collect.Lists.newArrayList;

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
import org.confetti.util.Tuple;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * @author Gabor Bubla
 */
public class ExportTimetableCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
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
        
        //get the solutionslots
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
        printTimetables3(days, hours, teachersTimetable, studentGroupsTimetable);
//        printTimetables2(days, hours, teachersTimetable, studentGroupsTimetable);
//        printTimetables(teachersTimetable, studentGroupsTimetable);
        
        return null;
    }

    private void printTimetables3(List<String> days, List<String> hours, Map<Teacher, List<SolutionSlot>> teachersTimetable,
            Map<StudentGroup, List<SolutionSlot>> studentGroupsTimetable
    ) {
        List<Tuple<String, List<List<String>>>> teachersSortedTimetable = new ArrayList<>();
        List<Tuple<String, List<List<String>>>> studentGroupsSortedTimetable = new ArrayList<>();
        
//        List<List<String>> emptyTimetable = new ArrayList<>();
//        for (int i = 0; i < days.size(); i++) {
//            emptyTimetable.add(Collections.nCopies(hours.size(), ""));
//        }
        
        for (Map.Entry<Teacher, List<SolutionSlot>> entry : teachersTimetable.entrySet()) {
            String teacherName = entry.getKey().getName().getValue();
            List<List<String>> teacherTimetable = createEmptyTimeTable2(days.size(), hours.size());
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
            List<List<String>> studentGroupTimetable = createEmptyTimeTable2(days.size(), hours.size());
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

    private List<List<String>> createEmptyTimeTable2(int daysSize, int hoursSize) {
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

    private void printTimetables2(List<String> days, List<String> hours, Map<Teacher, List<SolutionSlot>> teachersTimetable,
            Map<StudentGroup, List<SolutionSlot>> studentGroupsTimetable
    ) {
        List<Tuple<String, String[][]>> teachersSortedTimetable = new ArrayList<>();
        List<Tuple<String, String[][]>> studentGroupsSortedTimetable = new ArrayList<>();
        
        for (Map.Entry<Teacher, List<SolutionSlot>> entry : teachersTimetable.entrySet()) {
            String teacherName = entry.getKey().getName().getValue();
            String[][] teacherTimetable = createEmptyTimeTable(days.size(), hours.size());
            for (SolutionSlot solutionSlot : entry.getValue()) {
                teacherTimetable[days.indexOf(solutionSlot.getDay().getName().getValue())][hours.indexOf(solutionSlot.getHour().getName().getValue())] = 
                        solutionSlot.getAssignment().getSubject().getName().getValue()
                        + "\n"
                        + getNames(solutionSlot.getAssignment().getStudentGroups());
            }
            Tuple<String, String[][]> teacherTuple = new Tuple(teacherName, teacherTimetable);
            teachersSortedTimetable.add(teacherTuple);
        }
        
        for (Map.Entry<StudentGroup, List<SolutionSlot>> entry : studentGroupsTimetable.entrySet()) {
            String studentGroupName = entry.getKey().getName().getValue();
            String[][] studentGroupTimetable = createEmptyTimeTable(days.size(), hours.size());
            for (SolutionSlot solutionSlot : entry.getValue()) {
                studentGroupTimetable[days.indexOf(solutionSlot.getDay().getName().getValue())][hours.indexOf(solutionSlot.getHour().getName().getValue())] = 
                        solutionSlot.getAssignment().getSubject().getName().getValue()
                        + "\n"
                        + getNames(solutionSlot.getAssignment().getTeachers());
                solutionSlot.getDay().getName().getValue();
                solutionSlot.getHour().getName().getValue();
            }
            Tuple<String, String[][]> studentGroupTuple = new Tuple(studentGroupName, studentGroupTimetable);
            studentGroupsSortedTimetable.add(studentGroupTuple);
        }
        System.out.println("a");
    }
    
    private String[][] createEmptyTimeTable(int k, int l) {
        String[][] emptyTimeTable = new String[k][l];
        for (int i = 0; i < emptyTimeTable.length; i++) {
            for (int j = 0; j < emptyTimeTable[i].length; j++) {
                emptyTimeTable[i][j] = "";
            }
        }
        return emptyTimeTable;
    }

    private void printTimetables(Map<Teacher, List<SolutionSlot>> teachersTimetable, Map<StudentGroup, List<SolutionSlot>> studentGroupsTimetable) {
        for (Day day : ConfettiPlugin.getDefault().getDataProvider().getValue().getDays().getList()) {
            System.out.println(day.getName().getValue());
        }
        for (Hour hour : ConfettiPlugin.getDefault().getDataProvider().getValue().getHours().getList()) {
            System.out.println(hour.getName().getValue());
        }
        for (Map.Entry<Teacher, List<SolutionSlot>> entry : teachersTimetable.entrySet()) {
            System.out.println(entry.getKey().getName().getValue());
            for (SolutionSlot solutionSlot : entry.getValue()) {
                System.out.println("\t"
                        + solutionSlot.getDay().getName().getValue()
                        + ", "
                        + solutionSlot.getHour().getName().getValue()
                        );
            }
        }
        for (Map.Entry<StudentGroup, List<SolutionSlot>> entry : studentGroupsTimetable.entrySet()) {
            System.out.println(entry.getKey().getName().getValue());
            for (SolutionSlot solutionSlot : entry.getValue()) {
                System.out.println("\t"
                        + solutionSlot.getDay().getName().getValue()
                        + ", "
                        + solutionSlot.getHour().getName().getValue()
                        );
            }
        }
    }

    @Override
    public boolean isEnabled() {
        DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        if (dp != null && dp.getSolution().getValue() != null) {
            return true;
        }
        return false;
    }
    
}
