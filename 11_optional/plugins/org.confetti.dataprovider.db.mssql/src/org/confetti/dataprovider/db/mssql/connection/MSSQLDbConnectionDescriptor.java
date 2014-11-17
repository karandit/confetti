package org.confetti.dataprovider.db.mssql.connection;

import static java.text.MessageFormat.format;

import org.confetti.dataprovider.db.DbConnectionDescriptor;

/**
 * @author Gabor Bubla
 */
public class MSSQLDbConnectionDescriptor extends DbConnectionDescriptor {

    private static final String URL = "jdbc:jtds:sqlserver://{0}:{1};databaseName={2}";

    public MSSQLDbConnectionDescriptor(String host, String port, String database, String username, String password) {
        super(host, port, database, username, password);
    }

    @Override public String getDriver() { return "net.sourceforge.jtds.jdbc.Driver"; } //$NON-NLS-1$;
    @Override public String getDialect() { return "org.hibernate.dialect.SQLServer2008Dialect"; } //$NON-NLS-1$;
    @Override public String getUrl() { return format(URL, host, port, database); }

}
