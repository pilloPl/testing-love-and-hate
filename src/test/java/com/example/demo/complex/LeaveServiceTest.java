package com.example.demo.complex;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LeaveServiceTest {

    static final long ONE = 1L;

    @InjectMocks
    LeaveService leaveService;

    @Mock
    LeaveDatabase database;

    @Mock
    EscalationManager escalationManager;

    @Mock
    MessageBus messageBus;

    @Mock
    EmailSender emailSender;


    @DisplayName("Performer employee's request will be manually processed after 26th day")
    @Test
    void requests_of_performers_will_be_manually_processed_after_26th_day() {

    }

    @DisplayName("Performer employee does not get more than 45 days")
    @Test
    void performers_can_get_more_than_45_days() {


    }

    @DisplayName("Slacker does not get any days")
    @Test
    void slackers_do_not_get_any_leave() {


    }

    @DisplayName("regular employee does not get more than 26 days")
    @Test
    void regular_employee_doesnt_get_more_than_26_days() {

    }


    @DisplayName("Regular employee gets 26 days")
    @Test
    void regular_employee_gets_26_days() {

    }


}