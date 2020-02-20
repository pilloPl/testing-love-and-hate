package com.example.demo.complex;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.demo.complex.Result.Approved;
import static com.example.demo.complex.Result.Denied;
import static com.example.demo.complex.Result.Manual;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LeaveServiceTest {

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

    @DisplayName("Performer employee")
    @Test
    void performerShouldGetExtraDaysOff() {
        //given
        when(database.findByEmployeeId(1L))
                .thenReturn(new Object[]{"PERFORMER", 26});

        //when
        Result result = leaveService.requestPaidDaysOff(10, 1L);

        //then
        Mockito.verifyNoMoreInteractions(database);
        verify(escalationManager).notifyNewPendingRequest(eq(1L));
        Mockito.verifyNoInteractions(messageBus);
        Mockito.verifyNoInteractions(emailSender);

        assertEquals(result, Manual);
    }

    @DisplayName("p2 employee")
    @Test
    void pe2() {
        //given
        when(database.findByEmployeeId(1L))
                .thenReturn(new Object[]{"PERFORMER", 26});

        //when
        Result result = leaveService.requestPaidDaysOff(20, 1L);

        //then
        Mockito.verifyNoMoreInteractions(database);
        Mockito.verifyNoInteractions(escalationManager);
        Mockito.verifyNoInteractions(messageBus);
        Mockito.verify(emailSender).send(eq("next year"));

        assertEquals(result, Denied);
    }

    @DisplayName("Slacker employee")
    @Test
    void slackerShouldGetLessDaysOff() {
        //given
        when(database.findByEmployeeId(1L))
                .thenReturn(new Object[]{"SLACKER", 10});

        //when
        Result result = leaveService.requestPaidDaysOff(2, 1L);

        //then
        Mockito.verifyNoMoreInteractions(database);
        Mockito.verifyNoInteractions(escalationManager);
        Mockito.verifyNoInteractions(messageBus);
        Mockito.verify(emailSender).send(eq("next time"));
        assertEquals(result, Denied);
    }

    @DisplayName("regular employee")
    @Test
    void regularEmployee() {
        //given
        when(database.findByEmployeeId(1L))
                .thenReturn(new Object[]{"REGULAR", 10});

        //when
        Result result = leaveService.requestPaidDaysOff(2, 1L);

        //then
        verify(database).save(eq(new Object[]{"REGULAR", 12}));
        Mockito.verifyNoInteractions(escalationManager);
        Mockito.verify(messageBus).sendEvent("request approved");
        Mockito.verifyNoInteractions(emailSender);
        assertEquals(result, Approved);
    }

    @DisplayName("regular employee")
    @Test
    void regularEmployee2() {
        //given
        when(database.findByEmployeeId(1L))
                .thenReturn(new Object[]{"REGULAR", 10});

        //when
        Result result = leaveService.requestPaidDaysOff(22, 1L);

        //then
        Mockito.verifyNoMoreInteractions(database);
        Mockito.verifyNoInteractions(escalationManager);
        Mockito.verifyNoInteractions(messageBus);
        Mockito.verify(emailSender).send(eq("next year"));
        assertEquals(result, Denied);
    }



}