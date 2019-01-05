package com.hozensoft.smartsite.base.dao.repo;

import com.hozensoft.smartsite.base.domain.WorkType;
import org.apache.ibatis.annotations.Param;

public interface WorkTypeRepo {

    int addWorkType(WorkType workType);

    int editWorkType(WorkType workType);

    int patchEditWorkType(WorkType workType);

    int deleteWorkTypeById(@Param("workTypeId") String workTypeId);

    WorkType loadWorkTypeById(@Param("workTypeId") String workTypeId);
}
