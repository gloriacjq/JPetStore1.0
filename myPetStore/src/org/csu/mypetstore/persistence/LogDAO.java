package org.csu.mypetstore.persistence;

import java.util.Date;

public interface LogDAO {
    void insertLog(Date date, String username, String log);
}
