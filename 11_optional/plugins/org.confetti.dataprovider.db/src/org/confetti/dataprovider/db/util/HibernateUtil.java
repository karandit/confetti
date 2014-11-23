package org.confetti.dataprovider.db.util;

import java.util.Properties;

import org.confetti.dataprovider.db.DbConnectionDescriptor;
import org.confetti.dataprovider.db.entities.AssignmentDb;
import org.confetti.dataprovider.db.entities.DayDb;
import org.confetti.dataprovider.db.entities.HourDb;
import org.confetti.dataprovider.db.entities.InstituteDb;
import org.confetti.dataprovider.db.entities.RoomDb;
import org.confetti.dataprovider.db.entities.StudentGroupDb;
import org.confetti.dataprovider.db.entities.SubjectDb;
import org.confetti.dataprovider.db.entities.TeacherDb;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Gabor Bubla
 */
public class HibernateUtil {

    /**Driver class. value: "hibernate.connection.driver_class" */
    private final static String HIBERNATE_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class"; //$NON-NLS-1$

    /** User name. value: "hibernate.connection.username" */
    private final static String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username"; //$NON-NLS-1$

    /** Password. value: "hibernate.connection.password" */
    private final static String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password"; //$NON-NLS-1$

    /** Connection URL. value: "hibernate.connection.url" */
    private final static String HIBERNATE_CONNECTION_URL = "hibernate.connection.url"; //$NON-NLS-1$

    /** Server dialect. value: "hibernate.dialect" */
    private final static String HIBERNATE_DIALECT = "hibernate.dialect"; //$NON-NLS-1$
    
    public static enum Mode {
        CREATE_DROP { public String toString() { return "create-drop"; } },
        CREATE      { public String toString() { return "create"; } },
        UPDATE      { public String toString() { return "update"; } }
    }
    
    public static SessionFactory createSessionFactory(DbConnectionDescriptor connDesc, Mode mode) {
        Properties p = new Properties();
        p.setProperty(HIBERNATE_DIALECT,                    connDesc.getDialect());
        p.setProperty(HIBERNATE_CONNECTION_DRIVER_CLASS,    connDesc.getDriver());
        p.setProperty(HIBERNATE_CONNECTION_URL,             connDesc.getUrl());
        p.setProperty(HIBERNATE_CONNECTION_USERNAME,        connDesc.getUsername());
        p.setProperty(HIBERNATE_CONNECTION_PASSWORD,        connDesc.getPassword());
        p.setProperty("hibernate.hbm2ddl.auto", mode.toString());
        p.setProperty("hibernate.show_sql", "true");
        
        Configuration configuration = new Configuration();
        configuration.setProperties(p);
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

    public static void runTx(SessionFactory sessFact, Tx tx) {
        Session session = sessFact.openSession();
        try {
            Transaction trans = session.beginTransaction();
            try {
                tx.run(session, trans);
                trans.commit();
            } catch (Exception e) {
                trans.rollback();
            }
        } finally {
            session.close();
        }
    }
    
}
