package org.confetti.dataprovider.db.util;

import org.confetti.dataprovider.db.ConnectionDescriptor;
import org.confetti.dataprovider.db.entities.AssignmentDb;
import org.confetti.dataprovider.db.entities.DayDb;
import org.confetti.dataprovider.db.entities.HourDb;
import org.confetti.dataprovider.db.entities.InstituteDb;
import org.confetti.dataprovider.db.entities.RoomDb;
import org.confetti.dataprovider.db.entities.StudentGroupDb;
import org.confetti.dataprovider.db.entities.SubjectDb;
import org.confetti.dataprovider.db.entities.TeacherDb;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Gabor Bubla
 */
public class HibernateUtil {

	public static SessionFactory createSessionFactory(ConnectionDescriptor connDesc) {
		Configuration configuration = new Configuration();
		
		configuration.addAnnotatedClass(InstituteDb.class);
		configuration.addAnnotatedClass(HourDb.class);
		configuration.addAnnotatedClass(DayDb.class);
		configuration.addAnnotatedClass(SubjectDb.class);
		configuration.addAnnotatedClass(TeacherDb.class);
		configuration.addAnnotatedClass(StudentGroupDb.class);
		configuration.addAnnotatedClass(RoomDb.class);
		configuration.addAnnotatedClass(AssignmentDb.class);
		
		return configuration.buildSessionFactory();
	}
	
}
