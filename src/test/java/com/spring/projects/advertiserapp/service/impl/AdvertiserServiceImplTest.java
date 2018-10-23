package com.spring.projects.advertiserapp.service.impl;

import com.spring.projects.advertiserapp.model.Advertiser;
import com.spring.projects.advertiserapp.repository.AdvertiserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdvertiserServiceImplTest {

    @Mock
    AdvertiserRepository advertiserRepository;

    @InjectMocks
    AdvertiserServiceImpl advertiserService;

    @Test
    public void findAll_whenRepositoryReturnsNull_shouldReturnNewArrayList() {
        when(advertiserRepository.findAll()).thenReturn(null);

        assertEquals(Collections.emptyList(), advertiserService.findAll());
    }

    @Test
    public void findAll_whenRepositoryReturnList_shouldReturnList() {
        List<Advertiser> expectedList = Arrays.asList(
                new Advertiser("name1", "contact1", 1000.0),
                new Advertiser("name2", "contact2", 1050.0)
        );

        when(advertiserRepository.findAll()).thenReturn(expectedList);

        assertEquals(expectedList, advertiserService.findAll());
    }

    @Test
    public void findByName_shouldReturnAdvertiserFromRepository() {
        String advertiserName = "name`";
        Advertiser expectedAdvertiser = new Advertiser("name1", "contact1", 1000.0);

        when(advertiserRepository.findByName(advertiserName)).thenReturn(expectedAdvertiser);

        assertEquals(expectedAdvertiser, advertiserService.findByName(advertiserName));
    }

    @Test
    public void updateAdvertiser_shouldUpdateAdvertiserInRepository() {
        Advertiser advertiserToUpdate = new Advertiser("name1", "contact1", 1000.0);

        advertiserService.updateAdvertiser(advertiserToUpdate);

        verify(advertiserRepository, times(1)).update(advertiserToUpdate);
    }

    @Test
    public void createAdvertiser_shouldInsertAdvertiserInRepository() {
        Advertiser advertiserToInsert = new Advertiser("name1", "contact1", 1000.0);

        advertiserService.createAdvertiser(advertiserToInsert);

        verify(advertiserRepository, times(1)).insert(advertiserToInsert);
    }

    @Test
    public void deleteAdvertiser_shouldDeleteAdvertiserInRepository() {
        String advertiserToDelete = "name1";

        advertiserService.deleteAdvertiser(advertiserToDelete);

        verify(advertiserRepository, times(1)).deleteByName(advertiserToDelete);
    }

    @Test
    public void validateCredit_whenInsufficientCredit_shouldReturnFalse() {
        String advertiserName = "name`";
        Advertiser expectedAdvertiser = new Advertiser("name1", "contact1", 1000.0);

        when(advertiserRepository.findByName(advertiserName)).thenReturn(expectedAdvertiser);

        boolean result = advertiserService.validateCredit(advertiserName, 1001);

        assertFalse(result);
    }

    @Test
    public void validateCredit_whenSufficientCredit_shouldReturnTrue() {
        String advertiserName = "name`";
        Advertiser expectedAdvertiser = new Advertiser("name1", "contact1", 1000.0);

        when(advertiserRepository.findByName(advertiserName)).thenReturn(expectedAdvertiser);

        boolean result = advertiserService.validateCredit(advertiserName, 999);

        assertTrue(result);
    }

    @Test
    public void deductCredit_shouldUpdateCorrectCreditLimitInRepository() {
        String advertiserName = "name`";
        Advertiser advertiser = new Advertiser("name1", "contact1", 1000.0);

        when(advertiserRepository.findByName(advertiserName)).thenReturn(advertiser);

        advertiserService.deductCredit(advertiserName, 400);

        Advertiser updatedAdvertiser = new Advertiser("name1", "contact1", 600.0);

        verify(advertiserRepository, times(1)).update(updatedAdvertiser);
    }
}
