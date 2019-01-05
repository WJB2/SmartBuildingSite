package com.hozensoft.smartsite.base.dao.query;

import com.hozensoft.smartsite.base.domain.WorkType;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteQueryDto;
import com.hozensoft.smartsite.base.dto.WorkTypeDto;
import com.hozensoft.smartsite.base.dto.WorkTypeQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkTypeQuery {

    List<WorkTypeDto> findWorkTypeList(WorkTypeQueryDto params);

    WorkTypeDto findWorkTypeById(@Param("workTypeId")String workType);
}
