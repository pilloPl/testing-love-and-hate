package com.example.demo.ui;

import com.example.demo.complex.LeaveService;
import com.example.demo.complex.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//Handles HTTP requests
@RestController
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    public ResponseEntity requestLeave(Long employeeId, String sex, String type, int days) {

        if (type.equals("maternal")) {
            if (sex != "F") {
                throw new IllegalStateException();
            }
            //maternal leave service

        } else if (type.equals("regular")) {

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

        return ResponseEntity.ok().build();
    }
}
