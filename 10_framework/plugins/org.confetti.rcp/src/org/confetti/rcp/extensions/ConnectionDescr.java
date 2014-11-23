package org.confetti.rcp.extensions;

/**
 * @author Gabor Bubla
 */
public class ConnectionDescr {

    private final String dbType;
    private final ConnectionFactory connectionFactory;

    public ConnectionDescr(String dbType, ConnectionFactory connectionFactory) {
        this.dbType = dbType;
        this.connectionFactory = connectionFactory;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
    
    public String getDbType() {
        return dbType;
    }

    @Override
    public String toString() {
        return dbType;
    }
    
}
