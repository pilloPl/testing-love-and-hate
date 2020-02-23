package com.example.demo.complex;


import com.example.demo.ui.LeaveController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LeaveControllerTest {

    LeaveController leaveController = new LeaveController(new LeaveService(new LeaveDatabase(), new MessageBus(), new EmailSender(), new EscalationManager()));

    @Test
    void firstTest() throws Exception {



    }

}