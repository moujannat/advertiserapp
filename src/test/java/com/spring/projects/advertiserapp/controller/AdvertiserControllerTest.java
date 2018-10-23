package com.spring.projects.advertiserapp.controller;

import com.spring.projects.advertiserapp.model.Advertiser;
import com.spring.projects.advertiserapp.service.AdvertiserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdvertiserControllerTest {

    @Mock
    AdvertiserService advertiserService;

    @InjectMocks
    AdvertiserController advertiserController;

    @Test
    public void listOfAdvertisers_whenServiceReturnsEmptyList_shouldReturnHttpNoContent() {
        when(advertiserService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<?> responseEntity = advertiserController.listOfAdvertisers();

        assertEquals(Collections.emptyList(), responseEntity.getBody());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void listOfAdvertisers_whenServiceReturnsList_shouldReturnHttpOK() {
        List<Advertiser> expectedList = Arrays.asList(
                new Advertiser("name1", "contact1", 1000.0),
                new Advertiser("name2", "contact2", 1050.0)
        );

        when(advertiserService.findAll()).thenReturn(expectedList);

        ResponseEntity<?> responseEntity = advertiserController.listOfAdvertisers();

        assertEquals(expectedList, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getAdvertiser_shouldReturnHttpOK() {
        String advertiserName = "name1";
        Advertiser advertiser = new Advertiser("name1", "contact", 1000.0);

        when(advertiserService.findByName(advertiserName)).thenReturn(advertiser);

        ResponseEntity<?> responseEntity = advertiserController.getAdvertiser(advertiserName);

        assertEquals(advertiser, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void validateAdvertiser_whenServiceReturnsTrue_shouldReturnTrue() {
        String name = "name1";
        double amount = 100.0;

        when(advertiserService.validateCredit(name, amount)).thenReturn(true);

        ResponseEntity<?> responseEntity = advertiserController.validateAdvertiser(name, amount);

        assertEquals(true, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void validateAdvertiser_whenServiceReturnsFalse_shouldReturnFalse() {
        String name = "name1";
        double amount = 100.0;

        when(advertiserService.validateCredit(name, amount)).thenReturn(false);

        ResponseEntity<?> responseEntity = advertiserController.validateAdvertiser(name, amount);

        assertEquals(false, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateAdvertiser_shouldCallServiceAndReturnHttpOK(){
        Advertiser advertiser = new Advertiser("name1", "contact1", 100.00);

        advertiserController.updateAdvertiserInfo(advertiser);

        verify(advertiserService, times(1)).updateAdvertiser(advertiser);
    }

    @Test
    public void deductCreditLimit_shouldCallServiceAndReturnHttpOK(){
        String name = "advertiserName";
        Double amount = 19.0;

        advertiserController.deductCreditLimit(name, amount);

        verify(advertiserService, times(1)).deductCredit(name, amount);
    }

    @Test
    public void removeAdvertiser_shouldCallServiceAndReturnHttpOK(){
        String name = "advertiserName";

        advertiserController.removeAdvertiserInfo(name);

        verify(advertiserService, times(1)).deleteAdvertiser(name);
    }

    @Test
    public void createAdvertiser_shouldCallServiceAndReturnHttpOK(){
        Advertiser advertiser = new Advertiser("name1", "contact1", 100.00);

        advertiserController.createAdvertiser(advertiser);

        verify(advertiserService, times(1)).createAdvertiser(advertiser);
    }

}