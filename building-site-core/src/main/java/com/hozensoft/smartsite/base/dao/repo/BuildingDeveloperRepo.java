package com.hozensoft.smartsite.base.dao.repo;

import com.hozensoft.smartsite.base.domain.BuildingDeveloper;
import org.apache.ibatis.annotations.Param;

public interface BuildingDeveloperRepo {

    int addBuildingDeveloper(BuildingDeveloper buildingDeveloper);

    int editBuildingDeveloper(BuildingDeveloper buildingDeveloper);

    int patchEditBuildingDeveloper(BuildingDeveloper buildingDeveloper);

    int deleteBuildingDeveloperById(@Param("buildingDeveloperId") String buildingDeveloperId);

    BuildingDeveloper loadBuildingDeveloperById(@Param("buildingDeveloperId")String buildingDeveloperId);
}
