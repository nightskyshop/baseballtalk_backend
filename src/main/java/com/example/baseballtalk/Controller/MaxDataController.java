package com.example.baseballtalk.Controller;

import com.example.baseballtalk.DTO.MaxDataDTO;
import com.example.baseballtalk.Entity.MaxDataEntity;
import com.example.baseballtalk.Service.MaxDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maxData")
public class MaxDataController {
    @Autowired
    private MaxDataService service;

    @GetMapping
    public MaxDataEntity getMaxData() { return service.getMaxData(); }

    @PostMapping("")
    public void createOrUpdateMaxData(@RequestBody MaxDataDTO maxData) { service.createOrUpdateMaxData(maxData); }
}
