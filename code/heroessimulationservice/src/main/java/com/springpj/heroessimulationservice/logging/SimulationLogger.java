package com.springpj.heroessimulationservice.logging;

public enum SimulationLogger {

    INSTANCE;

    private static final String WARNING = "WARNING";
    private static final String INFO = "INFO";
    private static final String DEBUG = "DEBUG";

    private StringBuilder log;

    private SimulationLogger() {
        log = new StringBuilder();
    }

    public String dump(){
        String result = log.toString();
        reset();

        return result;
    }

    public void reset(){
        log = new StringBuilder();
    }


    public void info(String message){
        message(INFO, message);
    }

    public void debug(String message){
        message(DEBUG, message);
    }

    public void warning(String message){
        message(WARNING, message);
    }

    public void message(String severity, String message){
        log.append(String.format("%s: ", severity)).append(message).append("\n");
    }
}
