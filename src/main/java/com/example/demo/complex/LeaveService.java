package com.example.demo.complex;

import static com.example.demo.complex.Result.Manual;

public class LeaveService {

    final LeaveDatabase database;
    final MessageBus messageBus;
    final EmailSender emailSender;
    final EscalationManager escalationManager;
    final Configuration configuration;

    LeaveService(LeaveDatabase database, MessageBus messageBus, EmailSender emailSender, EscalationManager escalationManager, Configuration configuration) {
        this.database = database;
        this.messageBus = messageBus;
        this.emailSender = emailSender;
        this.escalationManager = escalationManager;
        this.configuration = configuration;
    }

    public Result requestPaidDaysOff(int days, Long employeeId) {

        validate(days);

        Something something = loadDataToDecide(employeeId);

        Result result = decide(days, something);

        //komunikacja
        reactToDecision(something, result, employeeId);

        return result;
    }

    private void reactToDecision(Something something, Result result, Long employeeId) {
        if (result == Result.Approved) {
            database.save(something);
            messageBus.sendEvent("request approved");
        }
        if (result == Result.Denied) {
            emailSender.send("next year, champ!");
        }
        if (result == Result.Manual) {
            escalationManager.notifyNewPendingRequest(employeeId);
        }
    }

    private void validate(int days) {
        if (days < 0) {
            throw new IllegalArgumentException();
        }
    }

    private Result decide(int days, Something something) {
        return something.request(days);
    }

    private Something loadDataToDecide(Long employeeId) {
        return database.findByEmployeeId(employeeId);
    }


}


class Something {
    private Long employeeId;
    private String employeeStatus;
    private int daysSoFar;

    Something(Long employeeId, String employeeStatus, int daysSoFar) {
        this.employeeId = employeeId;
        this.employeeStatus = employeeStatus;
        this.daysSoFar = daysSoFar;
    }

    Result request(int newDays) {
        if (daysSoFar + newDays > 26) {
            if (employeeStatus.equals("PERFORMER") && daysSoFar + new TooManyDaysSolver(newDays).newDays < 45) {
                return Manual;
            } else {
                return Result.Denied;
            }
        } else {

            if (employeeStatus.equals("SLACKER")) {
                return Result.Denied;
            } else {
                daysSoFar = daysSoFar + newDays;
                return Result.Approved;
            }
        }
    }

    public int getDaysSoFar() {
        return daysSoFar;
    }

    private class TooManyDaysSolver {
        private int newDays;

        public TooManyDaysSolver(int newDays) {
            this.newDays = newDays;
        }

    }
}

class LeaveDatabase {

    Something findByEmployeeId(Long employeeId) {
        return new Something(employeeId, "employeeStatus", 2);
    }

    void save(Something employeeData) {

    }
}

class MessageBus {

    void sendEvent(String msg) {
    }
}

class EmailSender {

    void send(String msg) {
    }
}

class EscalationManager {

    void notifyNewPendingRequest(Long employeeId) {
    }
}

class Configuration {

    final int MAX_DAYS = 26;

    int getMaxDays() {
        return MAX_DAYS;
    }

}