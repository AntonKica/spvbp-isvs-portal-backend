package com.skica.spvbpisvsportalbackend.controller;

import com.skica.spvbpisvsportalbackend.entity.Asset;
import com.skica.spvbpisvsportalbackend.generic.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asset")
public class AssetController extends GenericController<Asset> {
    public AssetController() {
        super(Asset.class);
    }
}
