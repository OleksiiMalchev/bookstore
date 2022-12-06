package com.javacourse.bookstore.services;

import java.io.FileInputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SomeСlass {
    static Logger LOGGER;
    static {
        try(FileInputStream ins = new FileInputStream("C:\\Users\\User\\IdeaProjects\\bookstore\\src\\log.config")){
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(SomeСlass.class.getName());
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
    private final Random randomNumber = new Random();
    public void doSomething(){
        long nextLong = randomNumber.nextLong();
        LOGGER.log(Level.INFO,"Random number:" + nextLong);
    }
}
