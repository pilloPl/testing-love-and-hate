package com.example.demo.complex;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    @Mock
    Configuration configuration;

    @DisplayName("Performer employee's request will be manually processed after 26th day")
    @Test
    void performerShouldGetExtraDaysOff() {
        //given
        when(database.findByEmployeeId(ONE)).thenReturn(new Object[]{"PERFORMER", 10});

        //when
        Result result = leaveService.requestPaidDaysOff(30, ONE);

        //then
        verify(escalationManager).notifyNewPendingRequest(ONE);
        verifyNoMoreInteractions(database);
        verifyNoInteractions(emailSender);
        verifyNoInteractions(messageBus);
        assertEquals(Result.Manual, result);
    }

    @DisplayName("Performer employee does not get more than 45 days")
    @Test
    void performerShouldGetNoMoreThan45Days() {
        //given
        when(database.findByEmployeeId(ONE)).thenReturn(new Object[]{"PERFORMER", 10});

        //when
        Result result = leaveService.requestPaidDaysOff(40, ONE);

        //then
        verifyNoInteractions(escalationManager);
        verifyNoMoreInteractions(database);
        verify(emailSender).send("next year, champ!");
        verifyNoInteractions(messageBus);
        assertEquals(Result.Denied, result);

    }

    @DisplayName("Slacker does not get any days")
    @Test
    void slackerShouldNotGetAnyDays() {
        //given
        when(database.findByEmployeeId(ONE)).thenReturn(new Object[]{"SLACKER", 10});

        //when
        Result result = leaveService.requestPaidDaysOff(1, ONE);

        //then
        verifyNoInteractions(escalationManager);
        verifyNoMoreInteractions(database);
        verify(emailSender).send("next time");
        verifyNoInteractions(messageBus);
        assertEquals(Result.Denied, result);

    }

    @DisplayName("regular employee does not get more than 26 days")
    @Test
    void regularEmployeeShouldNotGetMoreThan26Days() {
        //given
        when(database.findByEmployeeId(ONE)).thenReturn(new Object[]{"REGULAR", 10});

        //when
        Result result = leaveService.requestPaidDaysOff(20, ONE);

        //then
        assertEquals(Result.Denied, result);
    }

    @DisplayName("regular employee should get a nice email about denial")
    @Test
    void r2() {
        //given
        when(database.findByEmployeeId(ONE)).thenReturn(new Object[]{"REGULAR", 10});

        //when
        Result result = leaveService.requestPaidDaysOff(20, ONE);

        //then
        verifyNoInteractions(escalationManager);
        verifyNoMoreInteractions(database);
        verify(emailSender).send("next year, champ!");
        verifyNoInteractions(messageBus);
    }

    @DisplayName("Regular employee gets up to 26 days")
    @Test
    void regularEmployeeShouldGetUpTo26Days() {
        //given
        when(database.findByEmployeeId(ONE)).thenReturn(new Object[]{"REGULAR", 10});

        //when
        Result result = leaveService.requestPaidDaysOff(16, ONE);

        //then
        verifyNoInteractions(escalationManager);
        verify(database).save(new Object[]{"REGULAR", 26});
        verify(messageBus).sendEvent("request approved");
        verifyNoInteractions(emailSender);
        assertEquals(Result.Approved, result);
    }


}

//zmiana powoduje wywalke testow


//zmienic liczbe dni urlopowych i wszyastko JEB

//ciezko prztestowac w mockaxch decyzhe i efekty decyzji!

//zmina tresxci maila zmienia perfoermea slackera etc
//rozbic na 2 testy
