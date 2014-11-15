package org.confetti.dataprovider.db.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Gabor Bubla
 */
public interface Tx {

    void run(Session session, Transaction trans);

}
