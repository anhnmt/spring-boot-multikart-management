package com.example.multikart.controller;

import com.example.multikart.domain.dto.DistrictDTO;
import com.example.multikart.domain.dto.ProvinceDTO;
import com.example.multikart.domain.dto.WardDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/")
@Slf4j
public class ApiController {
    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/dvhc/provinces")
    public List<ProvinceDTO> getProvinceList() throws IOException {
        List<ProvinceDTO> provinces = new ArrayList<>();

        var resource = resourceLoader.getResource("classpath:dvhc/province.json");
        if (!resource.exists()) {
            return provinces;
        }

        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        Map<Long, ProvinceDTO> maps = new Gson().fromJson(br, Map.class);
//        log.info("{}", maps);

        provinces.addAll(maps.values());
        return provinces;
    }

    @GetMapping("/dvhc/provinces/{code}")
    public List<DistrictDTO> getDistrictList(@PathVariable("code") String code) throws IOException {
        List<DistrictDTO> districts = new ArrayList<>();

        String url = String.format("classpath:dvhc/districts/%s.json", code);
        var resource = resourceLoader.getResource(url);
        if (!resource.exists()) {
            return districts;
        }

        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        Map<Long, DistrictDTO> maps = new Gson().fromJson(br, Map.class);
//        log.info("{}", maps);

        districts.addAll(maps.values());
        return districts;
    }

    @GetMapping("/dvhc/districts/{code}")
    public List<WardDTO> getWardList(@PathVariable("code") String code) throws IOException {
        List<WardDTO> wards = new ArrayList<>();

        String url = String.format("classpath:dvhc/wards/%s.json", code);
        var resource = resourceLoader.getResource(url);
        if (!resource.exists()) {
            return wards;
        }

        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));
        Map<Long, WardDTO> maps = new Gson().fromJson(br, Map.class);
//        log.info("{}", maps);

        wards.addAll(maps.values());
        return wards;
    }
}
