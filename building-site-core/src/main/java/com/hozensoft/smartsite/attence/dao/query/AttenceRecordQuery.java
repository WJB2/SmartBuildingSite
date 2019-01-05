package com.hozensoft.smartsite.attence.dao.query;

import com.hozensoft.smartsite.attence.dto.AttenceRecordItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceRecordQueryDto;

import java.util.List;

public interface AttenceRecordQuery {

    List<AttenceRecordItemDto> findAttenceRecordList(AttenceRecordQueryDto params);
}
