package com.spring.projects.advertiserapp.service;

import com.spring.projects.advertiserapp.model.Advertiser;

import java.util.List;

public interface AdvertiserService {

    public List<Advertiser> findAll();

    public Advertiser findByName(String advertiserName);

    public void updateAdvertiser(Advertiser advertiser);

    public void createAdvertiser(Advertiser advertiser);

    public void deleteAdvertiser(String advertiserName);

    public boolean validateCredit(String advertiserName, double requestedAmount);

    public void deductCredit(String name, double credit);

}
