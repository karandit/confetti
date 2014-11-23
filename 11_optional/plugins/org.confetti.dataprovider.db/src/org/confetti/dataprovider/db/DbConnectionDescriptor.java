package org.confetti.dataprovider.db;

/**
 * @author Gabor Bubla
 */
public abstract class DbConnectionDescriptor {
    
    protected final String host;
    protected final String port;
    protected final String database;
    private final String username;
    private final String password;

    public DbConnectionDescriptor(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public abstract String getDriver();
    public abstract String getUrl();
    public abstract String getDialect();
    
}
