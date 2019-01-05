package com.hozensoft.smartsite.base.rest;

import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.service.BuildingSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/open/building-site")
public class BuildingSiteOpenRest {

    @Autowired
    private BuildingSiteService buildingSiteService;

    @GetMapping("/device-sn")
    public @ResponseBody
    Map<String, String> findBuildingSiteByDeviceSn(@RequestParam("deviceSn")String deviceSn){

        BuildingSiteDto buildingSiteDto = buildingSiteService.findBuildingSiteByDeviceSn(deviceSn.trim());

        Map<String, String> result = new HashMap<String, String>(){{
            put("buildingSiteName", buildingSiteDto.getName());
            put("buildingDeveloperName", buildingSiteDto.getBuildingDeveloper().getName());
        }};

        return result;
    }


}
