package com.hozensoft.smartsite.base.dao.query;

import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuildingSiteQuery {

    BuildingSiteDto findBuildingSiteById(@Param("buildingSiteId")String buildingSiteId);

    List<BuildingSiteItemDto> findBuildingSiteList(BuildingSiteQueryDto params);
}
