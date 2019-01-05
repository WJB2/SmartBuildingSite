package com.hozensoft.smartsite.attence.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.attence.domain.AttenceDevice;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceQueryDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceValueDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;

import java.util.List;

/**
 * 考勤设备服务类
 */
public interface AttenceDeviceService {

    /**
     * 新增考勤设备
     *
     * @param buildingDeveloper
     */
    AttenceDeviceDto addAttenceDevice(ContextHolder holder, AttenceDeviceValueDto buildingDeveloper);

    /**
     * 编辑考勤设备信息
     *
     * @param buildingDeveloper
     */
    AttenceDeviceDto editAttenceDevice(ContextHolder holder, AttenceDeviceValueDto buildingDeveloper);

    /**
     * 根据ID删除考勤设备信息
     * @param buildingDeveloperId
     */
    void deleteAttenceDeviceById(ContextHolder holder, String buildingDeveloperId);

    /**
     * 根据ID加载完整的考勤设备信息
     *
     * @param buildingDeveloperId
     * @return
     */
    AttenceDevice loadAttenceDeviceById(ContextHolder holder, String buildingDeveloperId);

    AttenceDevice loadAttenceDeviceBySn(String sn);

    /**
     * 根据ID查找考勤设备信息
     *
     * @param buildingDeveloperId
     * @return
     */
    AttenceDeviceDto findAttenceDeviceById(ContextHolder holder, String buildingDeveloperId);

    /**
     * 查询考勤设备列表信息
     *
     * @param params
     * @param limitable
     * @return
     */
    List<AttenceDeviceItemDto> findAttenceDeviceList(ContextHolder holder, AttenceDeviceQueryDto params, Limitable limitable);

    /**
     * 查询考勤设备分页列表信息
     *
     * @param params
     * @param pageable
     * @return
     */
    PageInfo<AttenceDeviceItemDto> findAttenceDevicePage(ContextHolder holder, AttenceDeviceQueryDto params, Pageable pageable);
}
