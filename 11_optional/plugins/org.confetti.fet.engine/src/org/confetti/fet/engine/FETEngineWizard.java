package org.confetti.fet.engine;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Sets.newHashSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.Room;
import org.confetti.core.SolutionSlot;
import org.confetti.fet.engine.solution.ResultActivityXML;
import org.confetti.fet.engine.solution.SolutionFAO;
import org.confetti.fet.engine.solution.SolutionXML;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.util.Tuple;
import org.confetti.xml.InstituteFAO;
import org.confetti.xml.core.ActivityXml;
import org.confetti.xml.core.DayXml;
import org.confetti.xml.core.DaysXml;
import org.confetti.xml.core.HourXml;
import org.confetti.xml.core.HoursXml;
import org.confetti.xml.core.InstituteXml;
import org.confetti.xml.core.RoomXml;
import org.confetti.xml.core.SubjectXml;
import org.confetti.xml.core.TeacherXml;
import org.confetti.xml.core.YearXml;
import org.confetti.xml.core.space.SpaceConstraint;
import org.confetti.xml.core.space.misc.ConstraintBasicCompulsorySpace;
import org.confetti.xml.core.time.TimeConstraint;
import org.confetti.xml.core.time.misc.ConstraintBasicCompulsoryTime;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import com.google.common.base.Function;

/**
 * @author Gabor Bubla
 */
public class FETEngineWizard extends Wizard {

	private final DataProvider mDataProvider;
	private FETEngineWizardPage mConsolePage;
	
	public FETEngineWizard(DataProvider dp) {
		this.mDataProvider = dp;
	}

	@Override
	public void addPages() {
		setWindowTitle("Generate");
		mConsolePage = new FETEngineWizardPage("FET Wizard Page");
		addPage(mConsolePage);
	}
	
	@Override
	public boolean performFinish() {
		try {
			URL fetClUrl = FETEngineWizard.class.getResource("COPYING");
			if (fetClUrl == null) {
				MessageDialog.openWarning(this.getShell(), "Warning", "Unfortunatelly FET client not found.");
				return true;
			}
			//Executable
			URL fileURL = FileLocator.toFileURL(fetClUrl);
			String executable = new File(new File(fileURL.getFile()).getParentFile(), "fet-cl").toString();
			List<String> command = new ArrayList<>();
			command.add(executable);
			
			//Input file
			Tuple<InstituteXml, List<Tuple<Long, Assignment>>> res = createInstitueXml(mDataProvider);
			InstituteXml inst = res.getFirst();
			Path tmpDir = Files.createTempDirectory("confetti");
			String inputFile = new File(tmpDir.toFile(), "input.fet").toString();
			new InstituteFAO().exportTo(inst, inputFile);
			command.add("--inputfile=" + inputFile.toString());
			
			//Output dir
			File resultsDir = new File(tmpDir.toFile(), "results");
			Files.createDirectory(resultsDir.toPath());
			command.add("--outputdir=" + resultsDir.toString());
			
			final Process process = new ProcessBuilder(command).start();
		    InputStream is = process.getInputStream();
		    try (InputStreamReader isr = new InputStreamReader(is);
		    		BufferedReader br = new BufferedReader(isr)) {
			    String line;
			    while ((line = br.readLine()) != null) {
			    	mConsolePage.print(line);
			    }
		    }
		    
		    //Reading in the solution file
		    File solutionFile  = Paths.get(resultsDir.getAbsolutePath(), 
		    							"timetables", "input", "input_activities.xml")
		    							.toFile();
		    SolutionXML solution = new SolutionFAO().importFrom(solutionFile);
		    
		    final Map<String, Room> mapRooms = convertToMap(mDataProvider.getRooms().getList());
		    final Map<String, Day> mapDays = convertToMap(mDataProvider.getDays().getList());
		    final Map<String, Hour> mapHours = convertToMap(mDataProvider.getHours().getList());
		    final Map<String, Assignment> mapAssignments = new HashMap<>();
		    for (Tuple<Long, Assignment> tuple : res.getSecond()) {
		    	//TODO: check if the id is not too long, because it could add a comma
		    	mapAssignments.put(tuple.getFirst().toString(), tuple.getSecond()); 
		    }
		    Iterable<SolutionSlot> dpSolution = transform(solution.getActivities(), 
		    		(ResultActivityXML act) -> {
			    	Assignment assignment = mapAssignments.get(act.getId());
			    	Day day = mapDays.get(act.getDay());
			    	Hour hour = mapHours.get(act.getHour());
			    	Room room = mapRooms.get(act.getRoom());
					return new SolutionSlot(assignment, day, hour, room);
				}
			);
			ConfettiPlugin.getDefault().getDataProvider().getValue().setSolution(dpSolution);
		    MessageDialog.openInformation(this.getShell(), "Success", "Timetable generated successfully.");
			return true;
		} catch (Throwable e) {
			MessageDialog.openError(this.getShell(), "Error", e.getLocalizedMessage());
			e.printStackTrace();
			return true;
		}
	}

	private static <T extends Nameable> Map<String, T> convertToMap(Iterable<T> items) {
		final Map<String, T> map = new HashMap<>();
		for (T item : items) {
			map.put(item.getName().getValue(), item);
		}
		return map;
	}

	private static <T, F> List<T> convertToList(Iterable<F> items, Function<F, T> f) {
		return transform(newArrayList(items), f); 
	}

	private Tuple<InstituteXml, List<Tuple<Long, Assignment>>> createInstitueXml(DataProvider dp) {
		InstituteXml inst = new InstituteXml(dp.getName().getValue(), "5.22.0", "generated by confetti");
		
		List<TimeConstraint> timeConstraints = new LinkedList<>();
		timeConstraints.add(new ConstraintBasicCompulsoryTime(100, true));
		inst.setTimeConstraints(timeConstraints);
		
		List<SpaceConstraint> spaceConstraints = new LinkedList<>();
		spaceConstraints.add(new ConstraintBasicCompulsorySpace());
		inst.setSpaceConstraints(spaceConstraints);
		
		//Transforming Subjects, Teachers, StudentGroups, Rooms, Days, Hours for FET
		inst.setSubjects(convertToList(dp.getSubjects().getList(), subj -> new SubjectXml(subj.getName().getValue())));
		inst.setTeachers(convertToList(dp.getTeachers().getList(), teacher -> new TeacherXml(teacher.getName().getValue())));
		inst.setYears(convertToList(dp.getStudentGroups().getList(), sG -> new YearXml(sG)));
		inst.setRooms(convertToList(dp.getRooms().getList(), room -> new RoomXml(room.getName().getValue())));
		inst.setDays(new DaysXml(convertToList(dp.getDays().getList(), day -> new DayXml(day.getName().getValue()))));
		inst.setHours(new HoursXml(convertToList(dp.getHours().getList(), hour -> new HourXml(hour.getName().getValue()))));
		
		//Transforming Assignments for FET
		Set<Assignment> assignments = new HashSet<>();
		dp.getSubjects().getList().forEach(subj -> assignments.addAll(newHashSet(subj.getAssignments().getList())));
		
		long counter = 1;
		List<Tuple<Long, Assignment>> tuples = new LinkedList<>();
		for (Assignment assignment : assignments) {
			tuples.add(new Tuple<>(counter++, assignment));
		}
		
		inst.setActivities(transform(tuples, tuple -> new ActivityXml(tuple.getFirst(), tuple.getSecond())));
		return new Tuple<>(inst, tuples);
	}

}
