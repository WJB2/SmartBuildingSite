package com.hozensoft.smartsite.attence.service;

import com.github.pagehelper.Page;
import com.hozensoft.smartsite.attence.domain.AttenceDeviceTransfer;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferQueryDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferValueDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;

import java.util.List;

/**
 * 考勤设备移机记录服务类
 */
public interface AttenceDeviceTransferService {

    /**
     * 新增考勤设备移机记录
     *a
     * @param buildingDeveloper
     */
    AttenceDeviceTransferDto addAttenceDeviceTransfer(ContextHolder holder, AttenceDeviceTransferValueDto buildingDeveloper);

    /**
     * 编辑考勤设备移机记录信息
     *
     * @param buildingDeveloper
     */
    AttenceDeviceTransferDto editAttenceDeviceTransfer(ContextHolder holder,AttenceDeviceTransferValueDto buildingDeveloper);

    /**
     * 根据ID删除考勤设备移机记录信息
     * @param buildingDeveloperId
     */
    void deleteAttenceDeviceTransferById(ContextHolder holder,String buildingDeveloperId);

    /**
     * 根据ID加载完整的考勤设备移机记录信息
     *
     * @param buildingDeveloperId
     * @return
     */
    AttenceDeviceTransfer loadAttenceDeviceTransferById(ContextHolder holder,String buildingDeveloperId);

    /**
     * 根据ID查找考勤设备移机记录信息
     *
     * @param buildingDeveloperId
     * @return
     */
    AttenceDeviceTransferDto findAttenceDeviceTransferById(ContextHolder holder,String buildingDeveloperId);

    /**
     * 查询考勤设备移机记录列表信息
     *
     * @param params
     * @param limitable
     * @return
     */
    List<AttenceDeviceTransferItemDto> findAttenceDeviceTransferList(ContextHolder holder,AttenceDeviceTransferQueryDto params, Limitable limitable);

    /**
     * 查询考勤设备移机记录分页列表信息
     *
     * @param params
     * @param pageable
     * @return
     */
    Page<AttenceDeviceTransferItemDto> findAttenceDeviceTransferPage(ContextHolder holder,AttenceDeviceTransferQueryDto params, Pageable pageable);



}
