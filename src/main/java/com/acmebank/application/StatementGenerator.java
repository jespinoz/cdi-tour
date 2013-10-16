package com.acmebank.application;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class StatementGenerator {

    private static final Logger logger = Logger.getLogger(
            StatementGenerator.class.getName());

    @Schedule(second = "*/30", minute = "*", hour = "*")
    public void generateMonthlyStatements() {
        logger.log(Level.INFO, "Generating monthly statements...");
    }
}
