package com.hozensoft.smartsite.attence.dao.repo;

import com.hozensoft.smartsite.attence.domain.EmployeeFeature;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeFeatureRepo {

    int insertEmployeeFeature(EmployeeFeature employeeFeature);

    int updateEmployeeFingerprint(@Param("idCardNo") String idCardNo,
                                  @Param("fingerprint") String fingerprint);

    int updateEmployeeFace(@Param("idCardNo") String idCardNo,
                           @Param("face") String face);

    List<EmployeeFeature> loadEmployeeFeatureList(@Param("idCardNoList") List<String> idCardNoList);
}
