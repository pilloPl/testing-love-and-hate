package com.example.demo.complex;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @DisplayName("Performer employee gets more than 26 days")
    @Test
    void performerShouldGetExtraDaysOff() {
    }

    @DisplayName("Performer employee does not get more than 45 days")
    @Test
    void performerShouldGetNoMoreThan45Days() {

    }

    @DisplayName("Slacker does not get any days")
    @Test
    void slackerShouldNotGetAnyDays() {

    }

    @DisplayName("Regular employee gets up to 26 days")
    @Test
    void regularEmployeeShouldGetUpTo26Days() {

    }

    @DisplayName("regular employee does not get more than 26 days")
    @Test
    void regularEmployeeShouldNotGetMoreThan26Days() {

    }



}

//zmiana powoduje wywalke testow


//zmienic liczbe dni urlopowych i wszyastko JEB

//ciezko prztestowac w mockaxch decyzhe i efekty decyzji!

//zmina tresxci maila zmienia perfoermea slackera etc
//rozbic na 2 testy
