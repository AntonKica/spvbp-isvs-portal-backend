package com.skica.spvbpisvsportalbackend.controller;

import com.skica.spvbpisvsportalbackend.entity.BusinessProcess;
import com.skica.spvbpisvsportalbackend.generic.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/businessProcess")
public class BusinessProcessController extends GenericController<BusinessProcess> {
    public BusinessProcessController() {
        super(BusinessProcess.class);
    }
}
