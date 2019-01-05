package com.hozensoft.smartsite.base.transformer;

import com.hozensoft.smartsite.base.domain.BuildingDeveloper;

import com.hozensoft.smartsite.base.dto.BuildingDeveloperValueDto;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

public class BuildingDeveloperTransformer {
    public static BuildingDeveloper transformBuildingDeveloperAddtoDomain(BuildingDeveloperValueDto buildingDeveloper) {

        BuildingDeveloper domain = new BuildingDeveloper();

        BeanUtils.copyProperties(buildingDeveloper, domain);

        if(StringUtils.isBlank(domain.getId())){
            domain.setId(IdGen.generate());
        }

        if(domain.getDeletedFlag()!=null){
            domain.setDeletedFlag(false);
        }

        return domain;
    }

    public static BuildingDeveloper transforBuildingDeveloperEdittoDomain(BuildingDeveloperValueDto buildingDeveloper) {

        BuildingDeveloper domain = new BuildingDeveloper();

        BeanUtils.copyProperties(buildingDeveloper, domain);

        return domain;
    }

}