package com.electrolux.pushnotification.registrationservice.controller;

//import com.electrolux.pushnotification.registrationservice.dto.BrandsDTO;
//import com.electrolux.pushnotification.registrationservice.service.BrandsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/api")
//public class BrandsController {
/*
    private final Logger logger = LoggerFactory.getLogger(BrandsController.class);

    private final BrandsService brandsService;

    public BrandsController(BrandsService brandsService) {
        this.brandsService = brandsService;
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandsDTO>> getAllBrands() {
        logger.info("REST request to getAllBrands");
        List<BrandsDTO> brandsDTOS = brandsService.findAll();
        return ResponseEntity.ok().body(brandsDTOS);
    }

    @GetMapping("/register")
    public String registerDeviceWithToken(@RequestParam(name = "id") String deviceToken) {

        return "device registered successfully";
    }

    @GetMapping("/notify")
    public String sendNotification(@RequestParam String id, @RequestParam(required = false) String pns) {

        return "Notification sent successfully";
    }

 */
//}
