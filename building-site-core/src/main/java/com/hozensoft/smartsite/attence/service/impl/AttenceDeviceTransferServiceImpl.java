package com.hozensoft.smartsite.attence.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hozensoft.smartsite.attence.dao.query.AttenceDeviceTransferQuery;
import com.hozensoft.smartsite.attence.dao.repo.AttenceDeviceTransferRepo;
import com.hozensoft.smartsite.attence.domain.AttenceDeviceTransfer;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferQueryDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferValueDto;
import com.hozensoft.smartsite.attence.service.AttenceDeviceTransferService;
import com.hozensoft.smartsite.attence.transfermer.AttenceDeviceTransferTransformer;
import com.hozensoft.smartsite.attence.transfermer.AttenceDeviceTransformer;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttenceDeviceTransferServiceImpl implements AttenceDeviceTransferService {

    @Autowired
    private AttenceDeviceTransferRepo attenceDeviceTransferRepo;

    @Autowired
    private AttenceDeviceTransferQuery attenceDeviceTransferQuery;

    @Override
    public AttenceDeviceTransferDto addAttenceDeviceTransfer(ContextHolder holder, AttenceDeviceTransferValueDto attenceDeviceTransfer) {

        AttenceDeviceTransfer bd = AttenceDeviceTransferTransformer.transformAttenceDeviceTransferAddToDomain(attenceDeviceTransfer);
        attenceDeviceTransferRepo.addAttenceDeviceTransfer(bd);
        AttenceDeviceTransferDto outDto = findAttenceDeviceTransferById(holder, attenceDeviceTransfer.getId());

        return outDto;
    }

    @Override
    public AttenceDeviceTransferDto editAttenceDeviceTransfer(ContextHolder holder, AttenceDeviceTransferValueDto attenceDeviceTransfer) {

        AttenceDeviceTransfer old = attenceDeviceTransferRepo.loadAttenceDeviceTransferById(attenceDeviceTransfer.getId());
        AttenceDeviceTransferTransformer.mergebAttenceDeviceTransferEditToDomain(attenceDeviceTransfer, old);
        attenceDeviceTransferRepo.editAttenceDeviceTransfer(old);
        AttenceDeviceTransferDto outDto = findAttenceDeviceTransferById(holder, old.getId());

        return outDto;
    }

    @Override
    public void deleteAttenceDeviceTransferById(ContextHolder holder, String attenceDeviceTransferId) {

        attenceDeviceTransferRepo.deleteAttenceDeviceTransferById(attenceDeviceTransferId);
    }

    @Override
    public AttenceDeviceTransfer loadAttenceDeviceTransferById(ContextHolder holder, String attenceDeviceTransferId) {

        return attenceDeviceTransferRepo.loadAttenceDeviceTransferById(attenceDeviceTransferId);
    }

    @Override
    public AttenceDeviceTransferDto findAttenceDeviceTransferById(ContextHolder holder, String attenceDeviceTransferId) {

        AttenceDeviceTransferDto bdd = findAttenceDeviceTransferById(holder, attenceDeviceTransferId);

        return bdd;
    }

    @Override
    public List<AttenceDeviceTransferItemDto> findAttenceDeviceTransferList(ContextHolder holder, AttenceDeviceTransferQueryDto params, Limitable limitable) {
        params.setTenantId(holder.getTenantId());

        PageHelper.offsetPage(limitable.getOffset(), limitable.getLimit());

        return attenceDeviceTransferQuery.findAttenceDeviceTransferList(params);
    }

    @Override
    public Page<AttenceDeviceTransferItemDto> findAttenceDeviceTransferPage(ContextHolder holder, AttenceDeviceTransferQueryDto params, Pageable pageable) {
        params.setTenantId(holder.getTenantId());

        PageHelper.startPage(pageable.getPage(), pageable.getPageSize());
        List<AttenceDeviceTransferItemDto> list = attenceDeviceTransferQuery.findAttenceDeviceTransferList(params);

        return new Page<>();
    }
}
