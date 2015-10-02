package org.confetti.fet.engine;

import static com.google.common.collect.Lists.transform;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.confetti.fet.xml.FAOException;
import org.confetti.fet.xml.Institute_v5_24_0_FAO;
import org.confetti.fet.xml.core.Institute_v5_24_0_Xml;
import org.confetti.fet.xml.core.Institute_v5_24_0_XmlBuilder;
import org.confetti.fet.xml.core.NameGetter;
import org.confetti.util.Tuple;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Kárándi Tamás
 */
public class FETRunnable implements IRunnableWithProgress {

	private final DataProvider mDataProvider;
	private final URL mCopyingUrl;
	private final boolean mVerbose;
	private final List<Listener> listeners = new LinkedList<>();
	private Iterable<SolutionSlot> mSolution;
	private long assgId = 1;
	
	public FETRunnable(DataProvider dp, URL copyingUrl, boolean verbose) {
		this.mDataProvider = dp;
		this.mCopyingUrl = copyingUrl;
		this.mVerbose = verbose;
	}

	public Iterable<SolutionSlot> getSolution() {
		return mSolution;
	}

	public void attachPrintListener(Listener listener) {
		listeners.add(listener);
	}
	
	void print(String text) {
		Event event = new Event();
		event.data = text;
		listeners.forEach(listener -> listener.handleEvent(event));
	}
	
	@Override
	public void run(IProgressMonitor arg0) throws InvocationTargetException, InterruptedException {
		try {
			Tuple<Institute_v5_24_0_Xml, List<Tuple<Long, Assignment>>> res = 
					new Institute_v5_24_0_XmlBuilder(new NameGetter(), this::generateAssgId)
												.buildWithAssignmentMap(mDataProvider);
			Institute_v5_24_0_Xml inst = res.getFirst();
			
			Tuple<List<String>, File> command = buildCommand(inst, mCopyingUrl, mVerbose);
			Process process = new ProcessBuilder(command.getFirst()).start();
			InputStream is = process.getInputStream();
			try (InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr)) {
				String line;
				while ((line = br.readLine()) != null) {
					print(line);
				}
			}
			process.waitFor();
			if (process.exitValue() == 0) {
				storeSolution(res.getSecond(), command.getSecond());
			}
		} catch (Exception e) {
			throw new InvocationTargetException(e);
		}
	}
	
	private long generateAssgId(Assignment assg) {
		return assgId++;
	}
	
	private static Tuple<List<String>, File> buildCommand(Institute_v5_24_0_Xml inst, URL copyingUrl, boolean verbose) 
			throws IOException, FAOException {
		//Executable
		URL fileURL = FileLocator.toFileURL(copyingUrl);
		String executable = new File(new File(fileURL.getFile()).getParentFile(), "fet-cl").toString();
		List<String> command = new ArrayList<>();
		command.add(executable);
		
		//Input file
		Path tmpDir = Files.createTempDirectory("confetti");
		String inputFile = new File(tmpDir.toFile(), "input.fet").toString();
		new Institute_v5_24_0_FAO().exportTo(inst, inputFile);
		command.add("--inputfile=" + inputFile.toString());
		
		//Output dir
		File resultsDir = new File(tmpDir.toFile(), "results");
		Files.createDirectory(resultsDir.toPath());
		command.add("--outputdir=" + resultsDir.toString());
		if (verbose) {
			command.add("--verbose=true");
		}
		return new Tuple<>(command, resultsDir);
	}
	
	private void storeSolution(List<Tuple<Long, Assignment>> assignmentsById, File resultsDir) 
			throws FAOException {
		//Read the solution file
		File solutionFile  = Paths.get(resultsDir.getAbsolutePath(), "timetables", "input", "input_activities.xml")
								.toFile();
		SolutionXML solutionXml = new SolutionFAO().importFrom(solutionFile);
		
		final Map<String, Room> roomsByName = convertToMap(mDataProvider.getRooms().getList());
		final Map<String, Day> daysByName = convertToMap(mDataProvider.getDays().getList());
		final Map<String, Hour> hoursByName = convertToMap(mDataProvider.getHours().getList());
		final Map<String, Assignment> assignmentsByIdStr = new HashMap<>();
		for (Tuple<Long, Assignment> tuple : assignmentsById) {
			//TODO: check if the id is not too long, because it could add a comma
			assignmentsByIdStr.put(tuple.getFirst().toString(), tuple.getSecond()); 
		}
		mSolution = transform(solutionXml.getActivities(), (ResultActivityXML act) -> {
		    	Assignment assignment = assignmentsByIdStr.get(act.getId());
		    	Day day = daysByName.get(act.getDay());
		    	Hour hour = hoursByName.get(act.getHour());
		    	Room room = roomsByName.get(act.getRoom());
				return new SolutionSlot(assignment, day, hour, room);
			}
		);
	}
	
	private static <T extends Nameable> Map<String, T> convertToMap(Iterable<T> items) {
		final Map<String, T> map = new HashMap<>();
		for (T item : items) {
			map.put(item.getName().getValue(), item);
		}
		return map;
	}

}
