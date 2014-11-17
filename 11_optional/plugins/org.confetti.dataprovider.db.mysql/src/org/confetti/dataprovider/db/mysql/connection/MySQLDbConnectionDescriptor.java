package org.confetti.dataprovider.db.mysql.connection;

import static java.text.MessageFormat.format;

import org.confetti.dataprovider.db.DbConnectionDescriptor;

/**
 * @author Gabor Bubla
 */
public class MySQLDbConnectionDescriptor extends DbConnectionDescriptor {
    
    private static final String URL = "jdbc:jtds:sqlserver://{0}:{1}/{2}";
    
    public MySQLDbConnectionDescriptor(String host, String port, String database, String username, String password) {
        super(host, port, database, username, password);
    }

    @Override public String getDriver() { return "com.mysql.jdbc.Driver"; } //$NON-NLS-1$;
    @Override public String getDialect() { return "org.hibernate.dialect.MySQLDialect"; } //$NON-NLS-1$;
    @Override public String getUrl() { return format(URL, host, port, database); }

}
