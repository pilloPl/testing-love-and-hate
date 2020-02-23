package com.example.demo.complex;


import com.example.demo.ui.LeaveController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class LeaveControllerTest {

    LeaveController leaveController = new LeaveController(new LeaveService(new LeaveDatabase(), new MessageBus(), new EmailSender(), new EscalationManager(), new Configuration()));

    @Test
    void firstTest() throws Exception {

        //insert toDatabase

        ResponseEntity response = leaveController.requestLeave(1L, "F", "maternal", 20);


        //assert EmailSender
        //assert MessageBus
        //assert EscalationManager
        //assert database
        //assert result ->



    }

}