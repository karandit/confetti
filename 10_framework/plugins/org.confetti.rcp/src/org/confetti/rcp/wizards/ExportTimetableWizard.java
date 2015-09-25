package org.confetti.rcp.wizards;

import static com.google.common.collect.Lists.newArrayList;
import static org.confetti.rcp.wizards.ExportTimetableWizard.PrintersToHTML.MATRIX;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Entity;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.core.Teacher;
import org.confetti.observable.ObservableList;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.nls.Messages;
import org.confetti.rcp.wizards.models.ExportTimetableModel;
import org.confetti.rcp.wizards.pages.FolderChooseWizardPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * @author Gabor Bubla
 */
public class ExportTimetableWizard extends Wizard {

    public interface PrintToHTML {
        void print(PrintStream out, List<String> days, List<String> hours, String name, List<List<String>> timetable);
    }
    
    public enum PrintersToHTML implements PrintToHTML {
        MATRIX {
            @Override
            public void print(PrintStream out, List<String> days, List<String> hours, String name, List<List<String>> timetable) {
                out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\nhttp://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"); //$NON-NLS-1$
                out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">"); //$NON-NLS-1$
                out.println();
                
                out.println("<head>"); //$NON-NLS-1$
                out.println("\t<title>" + name + "</title>"); //$NON-NLS-1$ //$NON-NLS-2$
                out.println("</head>"); //$NON-NLS-1$
                out.println();
                
                out.println("<body>"); //$NON-NLS-1$
                out.println();
                
                out.println("\t<table border=\"1\">"); //$NON-NLS-1$
                out.println("\t\t<caption>" + name + "</caption>"); //$NON-NLS-1$ //$NON-NLS-2$
                out.println("\t\t<thead>"); //$NON-NLS-1$
                out.println("\t\t\t<tr>"); //$NON-NLS-1$
                out.println("\t\t\t\t<th></th>"); //$NON-NLS-1$
                for (String day : days) {
                    out.println("\t\t\t\t<th>" + day + "</th>"); //$NON-NLS-1$ //$NON-NLS-2$
                }
                out.println("\t\t\t</tr>"); //$NON-NLS-1$
                out.println("\t\t</thead>"); //$NON-NLS-1$
                out.println("\t\t<tbody>"); //$NON-NLS-1$
                int hourCounter = 0;
                for (List<String> hour : timetable) {
                    out.println("\t\t\t<tr>"); //$NON-NLS-1$
                    out.println("\t\t\t\t<th>" + hours.get(hourCounter++) + "</th>"); //$NON-NLS-1$ //$NON-NLS-2$
                    for (String day : hour) {
                        out.println("\t\t\t\t<td>" + day + "</td>"); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                    out.println("\t\t\t</tr>"); //$NON-NLS-1$
                }
                out.println("\t\t</tbody>"); //$NON-NLS-1$
                out.println("\t</table>"); //$NON-NLS-1$
                
                out.println();
                out.println("</body>"); //$NON-NLS-1$
                out.println();
                
                out.println("</html>"); //$NON-NLS-1$
            }
        };

    }
    
    private ExportTimetableModel model;

    public ExportTimetableWizard() {
        model = new ExportTimetableModel(null);
        setWindowTitle(Messages.ExportTimetableWizard_Title);
    }
    
    @Override
    public void addPages() {
        addPage(new FolderChooseWizardPage(model));
    }
    
    @Override
    public boolean performFinish() {
        try {
            File folderPath = new File(model.getFolderPath(), "timetables"); //$NON-NLS-1$
            folderPath.mkdir();
            exportTimetables(folderPath);
        } catch (IOException e) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.ExportTimetableWizard_Error, Messages.ExportTimetableWizard_Warning_CouldNotCreateFiles + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    private void exportTimetables(File folderPath) throws IOException {
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
        export(folderPath, days, hours, teachersTimetable, studentGroupsTimetable);
    }
    
    private void export(File folderPath, List<String> days, List<String> hours, Map<Teacher, List<SolutionSlot>> teachersTimetable,
            Map<StudentGroup, List<SolutionSlot>> studentGroupsTimetable
    ) throws IOException {
        File teachersFolder = new File(folderPath, "teachers"); //$NON-NLS-1$
        File studentGroupsFolder = new File(folderPath, "studentgroups"); //$NON-NLS-1$
        teachersFolder.mkdir();
        studentGroupsFolder.mkdir();
        
        for (Map.Entry<Teacher, List<SolutionSlot>> entry : teachersTimetable.entrySet()) {
            String teacherName = entry.getKey().getName().getValue();
            List<List<String>> teacherTimetable = createEmptyTimeTable(hours.size(), days.size());
            for (SolutionSlot solutionSlot : entry.getValue()) {
                        teacherTimetable.get(hours.indexOf(solutionSlot.getHour().getName().getValue())).set(days.indexOf(solutionSlot.getDay().getName().getValue()),
                        solutionSlot.getAssignment().getSubject().getName().getValue()
                        + "<br />" //$NON-NLS-1$
                        + getNames(solutionSlot.getAssignment().getStudentGroups()));
                
            }
            try (PrintStream out = new PrintStream(new File(teachersFolder, teacherName + ".html"))) { //$NON-NLS-1$
                MATRIX.print(out, days, hours, teacherName, teacherTimetable);
            }
        }
        for (Map.Entry<StudentGroup, List<SolutionSlot>> entry : studentGroupsTimetable.entrySet()) {
            String studentGroupName = entry.getKey().getName().getValue();
            List<List<String>> studentGroupTimetable = createEmptyTimeTable(hours.size(), days.size());
            for (SolutionSlot solutionSlot : entry.getValue()) {
                studentGroupTimetable.get(hours.indexOf(solutionSlot.getHour().getName().getValue())).set(days.indexOf(solutionSlot.getDay().getName().getValue()),
                        solutionSlot.getAssignment().getSubject().getName().getValue()
                        + "<br />" //$NON-NLS-1$
                        + getNames(solutionSlot.getAssignment().getTeachers()));
                
            }
            try (PrintStream out = new PrintStream(new File(studentGroupsFolder, studentGroupName + ".html"))) { //$NON-NLS-1$
                MATRIX.print(out, days, hours, studentGroupName, studentGroupTimetable);
            }
        }
        exportToHTMLIndex(folderPath);
        exportToHTMLFrame(folderPath, teachersTimetable, studentGroupsTimetable);
    }
    
    private void exportToHTMLIndex(File folderPath) throws IOException {
        try (PrintStream out = new PrintStream(new File(folderPath, "index.html"))) { //$NON-NLS-1$
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">"); //$NON-NLS-1$
            out.println("<html>"); //$NON-NLS-1$
            
            out.println("<head>"); //$NON-NLS-1$
            out.println("<title>Index</title>"); //$NON-NLS-1$
            out.println("</head>"); //$NON-NLS-1$
            
            out.println("<frameset cols=\"20%,80%\">"); //$NON-NLS-1$
            out.println("<frame src=\"overview-frame.html\">"); //$NON-NLS-1$
            out.println("<frame name=\"entityFrame\" scrolling=\"yes\">"); //$NON-NLS-1$
            out.println("</frameset"); //$NON-NLS-1$
            
            out.println("</html>"); //$NON-NLS-1$
        }
    }

    private void exportToHTMLFrame(File folderPath, Map<Teacher, List<SolutionSlot>> teachersTimetable,
            Map<StudentGroup, List<SolutionSlot>> studentGroupsTimetable
    ) throws IOException {
        try (PrintStream out = new PrintStream(new File(folderPath, "overview-frame.html"))) { //$NON-NLS-1$
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"); //$NON-NLS-1$
            out.println("<html>"); //$NON-NLS-1$
            
            out.println("<head>"); //$NON-NLS-1$
            out.println("<title>Overview list</title>"); //$NON-NLS-1$
            out.println("</head>"); //$NON-NLS-1$
            
            out.println("<body>"); //$NON-NLS-1$
            out.println("<h2>Teachers</h2>"); //$NON-NLS-1$
            out.println("<ul>"); //$NON-NLS-1$
            List<String> teacherNames = convertToNames(teachersTimetable.keySet());
            Collections.sort(teacherNames);
            for (String name : teacherNames) {
                out.println("<li><a href=\"teachers/" + name + ".html\" target=\"entityFrame\">" + name + "</a></li>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            out.println("</ul>"); //$NON-NLS-1$
            
            out.println("<h2>Student groups</h2>"); //$NON-NLS-1$
            out.println("<ul>"); //$NON-NLS-1$
            List<String> studentGroupNames = convertToNames(studentGroupsTimetable.keySet());
            Collections.sort(studentGroupNames);
            for (String name : studentGroupNames) {
                out.println("<li><a href=\"studentgroups/" + name + ".html\" target=\"entityFrame\">" + name + "</a></li>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            out.println("</ul>"); //$NON-NLS-1$
            out.println("</body>"); //$NON-NLS-1$
            
            out.println("</html>"); //$NON-NLS-1$
        }
    }

    private List<String> convertToNames(Set<? extends Entity> set) {
        List<String> names = newArrayList(Iterables.transform(set, new Function<Entity, String>() {
            @Override public String apply(Entity e) { return e.getName().getValue(); }
        }));
        return names;
    }

    private List<List<String>> createEmptyTimeTable(int hoursSize, int daysSize) {
        List<List<String>> emptyTimetable = new ArrayList<>();
        for (int i = 0; i < hoursSize; i++) {
            List<String> hoursList = new ArrayList<>();
            for (int j = 0; j < daysSize; j++) {
                hoursList.add(""); //$NON-NLS-1$
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
