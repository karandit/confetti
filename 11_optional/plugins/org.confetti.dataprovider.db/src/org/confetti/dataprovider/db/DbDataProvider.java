package org.confetti.dataprovider.db;

import static java.util.Arrays.asList;
import static org.confetti.dataprovider.db.util.HibernateUtil.runTx;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.confetti.core.Teacher;
import org.confetti.dataprovider.db.dto.AssignmentDTO;
import org.confetti.dataprovider.db.dto.DayDTO;
import org.confetti.dataprovider.db.dto.EntityDTO;
import org.confetti.dataprovider.db.dto.HourDTO;
import org.confetti.dataprovider.db.dto.RoomDTO;
import org.confetti.dataprovider.db.dto.StudentGroupDTO;
import org.confetti.dataprovider.db.dto.SubjectDTO;
import org.confetti.dataprovider.db.dto.TeacherDTO;
import org.confetti.dataprovider.db.entities.AbstractEntityDb;
import org.confetti.dataprovider.db.entities.AssignmentDb;
import org.confetti.dataprovider.db.entities.DayDb;
import org.confetti.dataprovider.db.entities.GetEntityClassVisitor;
import org.confetti.dataprovider.db.entities.HourDb;
import org.confetti.dataprovider.db.entities.InstituteDb;
import org.confetti.dataprovider.db.entities.RoomDb;
import org.confetti.dataprovider.db.entities.StudentGroupDb;
import org.confetti.dataprovider.db.entities.SubjectDb;
import org.confetti.dataprovider.db.entities.TeacherDb;
import org.confetti.dataprovider.db.util.Tx;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.confetti.util.Tuple;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * @author Gabor Bubla
 */
public class DbDataProvider implements DataProvider {

	//----------------------------- fields -----------------------------------------------------------------------------
	private ValueMutator<String> instName = new ValueMutator<>();
	private ListMutator<Teacher> teachers = new ListMutator<>();
	private ListMutator<Subject> subjects = new ListMutator<>();
	private ListMutator<StudentGroup> stdGroups = new ListMutator<>();
	private ListMutator<Room> rooms = new ListMutator<>();
	private ListMutator<Day> days = new ListMutator<>();
	private ListMutator<Hour> hours = new ListMutator<>();
	private ListMutator<Assignment> assignments = new ListMutator<>();
	private ListMutator<Constraint> constraints = new ListMutator<>();
	private ValueMutator<Iterable<SolutionSlot>> solution = new ValueMutator<>();
	
	private final SessionFactory sFact;
	private final Long instId;
    private final String info;
	
	public DbDataProvider(SessionFactory sessFact, InstituteDb instDb, String info) {
	    this.info = info;
	    this.sFact = sessFact;
	    instId = instDb.getId();
	    for (DayDb day : instDb.getDays()) {
	        days.addItem(new DayDTO(day.getName()));
	    }
	    for (HourDb hour : instDb.getHours()) {
	        hours.addItem(new HourDTO(hour.getName()));
	    }
        for (SubjectDb subj : instDb.getSubjects()) {
            subjects.addItem(new SubjectDTO(subj.getId(), subj.getName()));
        }
        for (TeacherDb teacher : instDb.getTeachers()) {
            teachers.addItem(new TeacherDTO(teacher.getId(), teacher.getName()));
        }
        for (StudentGroupDb sG : instDb.getStudentGroups()) {
            StudentGroupDTO studentGroupDTO = new StudentGroupDTO(sG.getId(), sG.getName());
            stdGroups.addItem(studentGroupDTO);
            //TODO read as the children of the StudentGroups
        }
        for (RoomDb room : instDb.getRooms()) {
            rooms.addItem(new RoomDTO(room.getId(), room.getName()));
        }

        Iterable<Subject> allSubjects = subjects.getObservableList().getList();
        Iterable<Teacher> allTeachers = teachers.getObservableList().getList();
        Map<String, StudentGroup> allStdGroups = collectStudentGroups(stdGroups.getObservableList().getList());
        for (AssignmentDb assDb : instDb.getAssignments()) {
            AssignmentDTO ass = new AssignmentDTO(assDb.getId(), findByName(allSubjects, assDb.getSubjects().iterator().next().getName()));
            if (assDb.getStudentGroups() != null) {
                for (StudentGroupDb stGroupDb : assDb.getStudentGroups()) {
                    ass.addStudentGroup(allStdGroups.get(stGroupDb.getName()));
                }
            }
            if (assDb.getTeachers() != null) {
                for (TeacherDb teacherDb : assDb.getTeachers()) {
                    ass.addTeacher(findByName(allTeachers, teacherDb.getName()));
                }
            }
        }
	}
	
	private Map<String, StudentGroup> collectStudentGroups(Iterable<StudentGroup> list) {
        Map<String, StudentGroup> res = new HashMap<>();
        for (StudentGroup sg : list) {
            res.put(sg.getName().getValue(), sg);
            res.putAll(collectStudentGroups(sg.getChildren().getList()));
        }
        return res;
    }
	
	private static <T extends Entity> T findByName(Iterable<T> items, String name) {
        for (T item : items) {
            if (item.getName().getValue().equals(name)) {
                return item;
            }
        }
        return null;
    }
	
	//----------------------------- DataProvider's Accessor API --------------------------------------------------------
	@Override public String getInformation()                               { return info; }
	@Override public ObservableValue<String> getName() 					   { return instName.getObservableValue(); }
	@Override public ObservableList<Teacher> getTeachers() 				   { return teachers.getObservableList(); }
	@Override public ObservableList<Subject> getSubjects() 				   { return subjects.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	   { return stdGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms() 					   { return rooms.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						   { return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 					   { return hours.getObservableList(); }
	@Override public ObservableList<Assignment> getAssignments() 		   { return assignments.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints() 		   { return constraints.getObservableList(); }
	@Override public ObservableValue<Iterable<SolutionSlot>> getSolution() { return solution.getObservableValue(); }
	
	//----------------------------- DataProvider's Persister API -------------------------------------------------------

    @Override
    public void addSubjects(final List<String> names) {
        List<SubjectDb> newEntities = createEntities(names, new Function<Tuple<String, InstituteDb>, SubjectDb>() {
            @Override public SubjectDb apply(Tuple<String, InstituteDb> tuple) { return new SubjectDb(tuple.getFirst(), tuple.getSecond()); }
        });
        
        for (SubjectDb newEntity : newEntities) {
            subjects.addItem(new SubjectDTO(newEntity.getId(), newEntity.getName()));
        }
    }

	@Override public void addTeachers(final List<String> names) {
	    List<TeacherDb> newEntities = createEntities(names, new Function<Tuple<String, InstituteDb>, TeacherDb>() {
            @Override public TeacherDb apply(Tuple<String, InstituteDb> tuple) { return new TeacherDb(tuple.getFirst(), tuple.getSecond()); }
        });
        
	    for (TeacherDb newEntity : newEntities) {
            teachers.addItem(new TeacherDTO(newEntity.getId(), newEntity.getName()));
        }
	}
	
	@Override public void addStudentGroups(StudentGroup parent, List<String> names) { 
        if (parent == null) {
            List<StudentGroupDb> newEntities = createEntities(names, new Function<Tuple<String, InstituteDb>, StudentGroupDb>() {
                @Override public StudentGroupDb apply(Tuple<String, InstituteDb> tuple) { return new StudentGroupDb(tuple.getFirst(), tuple.getSecond()); }
            });
            
            for (StudentGroupDb newEntity : newEntities) {
                stdGroups.addItem(new StudentGroupDTO(newEntity.getId(), newEntity.getName()));
            }
        } else { // TODO implement if has parent
        }
	}
	
	@Override public void addRooms(final List<String> names) {
	    List<RoomDb> newEntities = createEntities(names, new Function<Tuple<String, InstituteDb>, RoomDb>() {
            @Override public RoomDb apply(Tuple<String, InstituteDb> tuple) { return new RoomDb(tuple.getFirst(), tuple.getSecond()); }
        });
	    
	    for (RoomDb newEntity : newEntities) {
	        rooms.addItem(new RoomDTO(newEntity.getId(), newEntity.getName()));
        }
	}
	
    @Override
    public Assignment addAssignment(final Subject subject, final Iterable<Teacher> teachers, final Iterable<StudentGroup> studentGroups) {
        final AssignmentDb[] goBack = new AssignmentDb[1];
        runTx(sFact, new Tx() {
            @Override
            public void run(final Session session, Transaction trans) {
                InstituteDb instDb = (InstituteDb) session.load(InstituteDb.class, instId);
                SubjectDb entityDb = (SubjectDb) session.load(SubjectDb.class, ((SubjectDTO) subject).getId());
                Iterable<TeacherDb> teacherDbs = Iterables.transform(teachers, new Function<Teacher, TeacherDb>() {
                    @Override
                    public TeacherDb apply(Teacher teacher) {
                        return (TeacherDb) session.load(TeacherDb.class, ((TeacherDTO) teacher).getId());
                    }
                });
                Iterable<StudentGroupDb> studentGroupDbs = Iterables.transform(studentGroups, new Function<StudentGroup, StudentGroupDb>() {
                    @Override
                    public StudentGroupDb apply(StudentGroup stdGroup) {
                        return (StudentGroupDb) session.load(StudentGroupDb.class, ((StudentGroupDTO) stdGroup).getId());
                    }
                });
                
                AssignmentDb assignmentDb = new AssignmentDb(instDb, asList(entityDb), teacherDbs, studentGroupDbs);
                session.persist(assignmentDb);
                goBack[0] = assignmentDb;
            }
        });
        AssignmentDb assignmentDb = goBack[0];

        //---notify the client UI
        AssignmentDTO assignmentDto = new AssignmentDTO(assignmentDb.getId(), subject);
        for (Teacher teacher : teachers) {
            assignmentDto.addTeacher(teacher);
        }
        for (StudentGroup studentGroup : studentGroups) {
            assignmentDto.addStudentGroup(studentGroup);
        }
        assignments.addItem(assignmentDto);
        return assignmentDto;
    }
    
	@Override public void setSolution(Iterable<SolutionSlot> solution) { }

    @Override public void removeSubjects(List<Subject> toRemove) { removeEntities(SubjectDb.class, toRemove, this.subjects); }
	@Override public void removeTeachers(List<Teacher> toRemove) { removeEntities(TeacherDb.class, toRemove, this.teachers); }
	@Override public void removeStudentGroups(List<StudentGroup> toRemove) { removeEntities(StudentGroupDb.class, toRemove, this.stdGroups); }
	@Override public void removeRooms(List<Room> toRemove) { removeEntities(RoomDb.class, toRemove, this.rooms); }

    @Override
    public void removeAssignment(final Assignment assignment) {
        runTx(sFact, new Tx() {
            @Override
            public void run(Session session, Transaction trans) {
                AssignmentDTO assignmentDTO = (AssignmentDTO) assignment;
                AssignmentDb loadedAss = (AssignmentDb) session.load(AssignmentDb.class, assignmentDTO.getId());
                session.delete(loadedAss);
            }
        });
        
        assignment.getSubject().removeAssignment(assignment);
        for (Teacher teacher : assignment.getTeachers().getList()) {
            teacher.removeAssignment(assignment);
        }
        for (StudentGroup studentGroup : assignment.getStudentGroups().getList()) {
            studentGroup.removeAssignment(assignment);
        }
        assignments.removeItem(assignment);
    }
	
	@Override public void rename(Entity entity, final String newName) { 
	    final Class<? extends AbstractEntityDb> clazz = entity.accept(GetEntityClassVisitor.INSTANCE, null);
	    final EntityDTO entityDto = (EntityDTO) entity;
	    
	    runTx(sFact, new Tx() {
            @Override
            public void run(Session session, Transaction trans) {
                AbstractEntityDb entityDb = (AbstractEntityDb) session.load(clazz, entityDto.getId());
                entityDb.setName(newName);
                session.persist(entityDb);
            }
        });
	    
	    entityDto.getNameMutator().setValue(entity, newName);
	}
    
	//----------------------------- helpers ----------------------------------------------------------------------------
    private <T extends AbstractEntityDb> List<T> createEntities(final List<String> names, 
            final Function<Tuple<String, InstituteDb>, T> f) {
        final List<T> newEntities = new LinkedList<T>();
        runTx(sFact, new Tx() {
            @Override
            public void run(Session session, Transaction trans) {
                InstituteDb instDb = (InstituteDb) session.load(InstituteDb.class, instId);
                for (String name : names) {
                    T newEntity = f.apply(new Tuple<String, InstituteDb>(name, instDb));
                    newEntities.add(newEntity);
                    session.persist(newEntity);
                }
            }
        });
        return newEntities;
    }
    
    private <T> void removeEntities(final Class<?> clazz, final List<? extends T> entitiesToRemove, final ListMutator<T> allEntities) {
        runTx(sFact, new Tx() {
            @Override
            public void run(Session session, Transaction trans) {
                for (T entityDto : entitiesToRemove) {
                    AbstractEntityDb entityDb = (AbstractEntityDb) session.load(clazz, ((EntityDTO) entityDto).getId());
                    session.delete(entityDb);
                }
            }
        });
        
        for (T entityToRemove : entitiesToRemove) {
            allEntities.removeItem(entityToRemove);
        }
    }

	@Override
	public Constraint addConstraint(String uid, ConstraintAttributes attrs) {
		throw new RuntimeException("Not implemented yet.");
	}

	@Override
	public void updateConstraint(Constraint constraint, ConstraintAttributes attrs) {
		throw new RuntimeException("Not implemented yet.");
	}
    
}
