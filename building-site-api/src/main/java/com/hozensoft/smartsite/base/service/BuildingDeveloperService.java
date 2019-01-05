package com.hozensoft.smartsite.base.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.base.domain.BuildingDeveloper;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperItemDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperQueryDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperValueDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;

import java.util.List;

/**
 * 开发商服务类
 */
public interface BuildingDeveloperService {

    /**
     * 新增开发商
     *
     * @param buildingDeveloper
     */
    BuildingDeveloperDto addBuildingDeveloper(ContextHolder holder, BuildingDeveloperValueDto buildingDeveloper);

    /**
     * 编辑开发商信息
     *
     * @param buildingDeveloper
     */
    BuildingDeveloperDto editBuildingDeveloper(ContextHolder holder, BuildingDeveloperValueDto buildingDeveloper);

    /**
     * 根据ID删除开发商信息
     * @param buildingDeveloperId
     */
    void deleteBuildingDeveloperById(ContextHolder holder, String buildingDeveloperId);

    /**
     * 根据ID加载完整的开发商信息
     *
     * @param buildingDeveloperId
     * @return
     */
    BuildingDeveloper loadBuildingDeveloperById(ContextHolder holder, String buildingDeveloperId);

    /**
     * 根据ID查找开发商信息
     *
     * @param buildingDeveloperId
     * @return
     */
    BuildingDeveloperDto findBuildingDeveloperById(ContextHolder holder, String buildingDeveloperId);

    /**
     * 查询开发商列表信息
     *
     * @param params
     * @param limitable
     * @return
     */
    List<BuildingDeveloperItemDto> findBuildingDeveloperList(ContextHolder holder, BuildingDeveloperQueryDto params, Limitable limitable);

    /**
     * 查询开发商分页列表信息
     *
     * @param params
     * @param pageable
     * @return
     */
    PageInfo<BuildingDeveloperItemDto> findBuildingDeveloperPage(ContextHolder holder, BuildingDeveloperQueryDto params, Pageable pageable);
}
