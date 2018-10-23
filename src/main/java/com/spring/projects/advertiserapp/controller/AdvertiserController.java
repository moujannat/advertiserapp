package com.spring.projects.advertiserapp.controller;

import com.spring.projects.advertiserapp.model.Advertiser;
import com.spring.projects.advertiserapp.service.AdvertiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertiser")
public class AdvertiserController {
    @Autowired
    AdvertiserService advertiserService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> listOfAdvertisers() {
        List<Advertiser> advertiserList = advertiserService.findAll();
        return advertiserList.isEmpty()
                ? new ResponseEntity<>(advertiserList, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(advertiserList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAdvertiser(@PathVariable("name") String advertiserName) {
        Advertiser advertiser = advertiserService.findByName(advertiserName);
        return new ResponseEntity<>(advertiser, HttpStatus.OK);
    }

    @RequestMapping(value = "/validate/{name}/{amount}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> validateAdvertiser(@PathVariable("name") String advertiserName, @PathVariable("amount") Double amount) {
        boolean result = advertiserService.validateCredit(advertiserName, amount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/deduct/{name}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> deductCreditLimit(@PathVariable("name") String name, @RequestBody Double credit) {
        advertiserService.deductCredit(name, credit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateAdvertiserInfo(@RequestBody Advertiser advertiser) {
        advertiserService.updateAdvertiser(advertiser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> removeAdvertiserInfo(@PathVariable("name") String advertiserName) {
        advertiserService.deleteAdvertiser(advertiserName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAdvertiser(@RequestBody Advertiser advertiser) {
        advertiserService.createAdvertiser(advertiser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
