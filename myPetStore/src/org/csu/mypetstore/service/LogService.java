package org.csu.mypetstore.service;

import org.csu.mypetstore.persistence.LogDAO;
import org.csu.mypetstore.persistence.impl.LogDAOImpl;

import java.util.Date;

public class LogService {
    private LogDAO logDAO;

    public LogService(){
        logDAO = new LogDAOImpl();
    }

    public void insertLog(String username, String logText){
        logDAO.insertLog(new Date(), username, logText);
    }
}
