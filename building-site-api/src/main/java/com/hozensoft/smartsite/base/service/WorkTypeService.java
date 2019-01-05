package com.hozensoft.smartsite.base.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.base.domain.WorkType;
import com.hozensoft.smartsite.base.dto.WorkTypeDto;
import com.hozensoft.smartsite.base.dto.WorkTypeQueryDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;

import java.util.List;

/**
 * 开发商服务类
 */
public interface WorkTypeService {

    /**
     * 新增开发商
     *
     * @param workType
     */
    WorkTypeDto addWorkType(ContextHolder holder, WorkTypeDto workType);

    /**
     * 编辑开发商信息
     *
     * @param workType
     */
    WorkTypeDto editWorkType(ContextHolder holder, WorkTypeDto workType);

    /**
     * 根据ID删除开发商信息
     * @param workTypeId
     */
    void deleteWorkTypeById(ContextHolder holder, String workTypeId);

    /**
     * 根据ID加载完整的开发商信息
     *
     * @param workTypeId
     * @return
     */
    WorkType loadWorkTypeById(ContextHolder holder, String workTypeId);

    /**
     * 根据ID查找开发商信息
     *
     * @param workTypeId
     * @return
     */
    WorkTypeDto findWorkTypeById(ContextHolder holder, String workTypeId);

    /**
     * 查询开发商列表信息
     *
     * @param params
     * @param limitable
     * @return
     */
    List<WorkTypeDto> findWorkTypeList(ContextHolder holder, WorkTypeQueryDto params, Limitable limitable);

    /**
     * 查询开发商分页列表信息
     *
     * @param params
     * @param pageable
     * @return
     */
    PageInfo<WorkTypeDto> findWorkTypePage(ContextHolder holder, WorkTypeQueryDto params, Pageable pageable);
}
