package com.hozensoft.smartsite.base.dao.query;

import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperItemDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperQueryDto;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface BuildingDeveloperQuery {

    BuildingDeveloperDto findBuildingDeveloperById(@Param("buildingDeveloperId")String buildingDeveloperId);

    List<BuildingDeveloperItemDto> findBuildingDeveloperList(BuildingDeveloperQueryDto params);
}
