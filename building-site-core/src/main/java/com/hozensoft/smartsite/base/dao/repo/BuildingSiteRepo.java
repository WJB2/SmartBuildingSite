package com.hozensoft.smartsite.base.dao.repo;

import com.hozensoft.smartsite.base.domain.BuildingSite;
import org.apache.ibatis.annotations.Param;

public interface BuildingSiteRepo {

    int addBuildingSite(BuildingSite buildingSite);

    int editBuildingSite(BuildingSite buildingSite);

    int patchEditBuildingSite(BuildingSite buildingSite);

    int deleteBuildingSiteById(@Param("buildingSiteId") String buildingSiteId);

    BuildingSite loadBuildingSiteById(@Param("buildingSiteId") String buildingSiteId);
}
