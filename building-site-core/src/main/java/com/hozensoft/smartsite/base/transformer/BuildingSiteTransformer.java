package com.hozensoft.smartsite.base.transformer;

import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.dto.BuildingSiteValueDto;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

public class BuildingSiteTransformer {

    public static BuildingSite transformBuildingSiteAddDto2Domain(BuildingSiteValueDto buildingSiteDto){

        BuildingSite buildingSite=new BuildingSite();

        BeanUtils.copyProperties(buildingSiteDto,buildingSite);

        if(StringUtils.isBlank(buildingSite.getId())){
            buildingSite.setId(IdGen.generate());
        }

        if(buildingSite.getDeletedFlag() == null) {
            buildingSite.setDeletedFlag(false);
        }

        return buildingSite;
    }

    public static BuildingSite transformBuildingSiteEditDto2Domain(BuildingSiteValueDto buildingSiteDto){

        BuildingSite buildingSite = new BuildingSite();

        BeanUtils.copyProperties(buildingSiteDto, buildingSite);

        return buildingSite;
    }
}
