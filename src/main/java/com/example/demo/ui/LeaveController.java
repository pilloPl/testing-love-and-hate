package com.example.demo.ui;

import com.example.demo.complex.LeaveService;
import com.example.demo.complex.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//Handles HTTP requests
@RestController
public class LeaveController {

    private final LeaveService leaveService;
    private final HrService hrService;
    private final ReportingService reportingService;


    public LeaveController(LeaveService leaveService, HrService hrService, ReportingService reportingService) {
        this.leaveService = leaveService;
        this.hrService = hrService;
        this.reportingService = reportingService;
    }

    public ResponseEntity requestLeave(Long employeeId, String sex, String type, int days) {

        if (type.equals("maternal")) {
            if (hrService.qualifiedForMaternalLeave(employeeId, sex)) {
                throw new IllegalStateException();
            }
            //maternal leave service

        } else if (type.equals("vacation")) {

            Result result = leaveService.requestPaidDaysOff(days, employeeId);

            if (result == Result.Approved) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }

        } else if (type.equals("occassional")) {
            if (days != 1) {
                throw new IllegalStateException();
            }
            //occassional leave service
        }

        reportingService.reportLeave(employeeId, type);
        return ResponseEntity.ok().build();
    }
}


class HrService {

    boolean qualifiedForMaternalLeave(Long employeeId, String sex) {
        return true;
    }


}

class ReportingService {

    void reportLeave(Long employeeId, String type) {

    }
}