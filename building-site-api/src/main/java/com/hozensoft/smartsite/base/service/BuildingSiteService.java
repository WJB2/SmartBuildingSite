package com.hozensoft.smartsite.base.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteQueryDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteValueDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;

import java.util.List;

/**
 * 开发商服务类
 */
public interface BuildingSiteService {

    /**
     * 新增开发商
     *
     * @param buildingSite
     */
    BuildingSiteDto addBuildingSite(ContextHolder holder, BuildingSiteValueDto buildingSite);

    /**
     * 编辑开发商信息
     *
     * @param buildingSite
     */
    BuildingSiteDto editBuildingSite(ContextHolder holder, BuildingSiteValueDto buildingSite);

    /**
     * 根据ID删除开发商信息
     * @param buildingSiteId
     */
    void deleteBuildingSiteById(ContextHolder holder, String buildingSiteId);

    /**
     * 根据ID加载完整的开发商信息
     *
     * @param buildingSiteId
     * @return
     */
    BuildingSite loadBuildingSiteById(ContextHolder holder, String buildingSiteId);

    /**
     * 根据ID查找开发商信息
     *
     * @param buildingSiteId
     * @return
     */
    BuildingSiteDto findBuildingSiteById(ContextHolder holder, String buildingSiteId);

    BuildingSiteDto findBuildingSiteByDeviceSn(String deviceSn);

    /**
     * 查询开发商列表信息
     *
     * @param params
     * @param limitable
     * @return
     */
    List<BuildingSiteItemDto> findBuildingSiteList(ContextHolder holder, BuildingSiteQueryDto params, Limitable limitable);

    /**
     * 查询开发商分页列表信息
     *
     * @param params
     * @param pageable
     * @return
     */
    PageInfo<BuildingSiteItemDto> findBuildingSitePage(ContextHolder holder, BuildingSiteQueryDto params, Pageable pageable);
}
