package com.hozensoft.smartsite.base.transformer;

import com.hozensoft.smartsite.base.domain.WorkType;
import com.hozensoft.smartsite.base.dto.WorkTypeDto;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

public class WorkTypeTransformer {

    public  static WorkType transformWorkTypeAddDto2Domain(WorkTypeDto workType){

        WorkType domain = new WorkType();
        BeanUtils.copyProperties(workType,domain);

        if(StringUtils.isBlank(domain.getId())){
            domain.setId(IdGen.generate());
        }

        return  domain;
    }

    public static  WorkType mergeWorkTypeDto2Domain(WorkTypeDto workType,WorkType domain ){

        BeanUtils.copyProperties(workType,domain);

       return  domain;

    }

}
