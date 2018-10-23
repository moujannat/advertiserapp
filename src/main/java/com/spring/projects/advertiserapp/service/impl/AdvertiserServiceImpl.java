package com.spring.projects.advertiserapp.service.impl;

import com.spring.projects.advertiserapp.model.Advertiser;
import com.spring.projects.advertiserapp.repository.AdvertiserRepository;
import com.spring.projects.advertiserapp.service.AdvertiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdvertiserServiceImpl implements AdvertiserService {
    @Autowired
    AdvertiserRepository advertiserRepository;

    @Override
    public List<Advertiser> findAll() {
        List<Advertiser> advertisers = advertiserRepository.findAll();
        return Optional.ofNullable(advertisers).orElseGet(ArrayList::new);
    }

    @Override
    public Advertiser findByName(String advertiserName) {
        return advertiserRepository.findByName(advertiserName);
    }

    @Override
    public void updateAdvertiser(Advertiser advertiser) {
        advertiserRepository.update(advertiser);
    }

    @Override
    public void createAdvertiser(Advertiser advertiser) {
        advertiserRepository.insert(advertiser);
    }

    @Override
    public void deleteAdvertiser(String advertiserName) {
        advertiserRepository.deleteByName(advertiserName);
    }

    @Override
    public boolean validateCredit(String advertiserName, double requestedAmount) {
        Advertiser advertiser = findByName(advertiserName);
        return advertiser.getCreditLimit() >= requestedAmount;
    }

    @Override
    public void deductCredit(String name, double credit) {
        Advertiser advertiser = advertiserRepository.findByName(name);
        double remainingCredit = advertiser.getCreditLimit() - credit;
        advertiser.setCreditLimit(remainingCredit);
        updateAdvertiser(advertiser);
    }
}
