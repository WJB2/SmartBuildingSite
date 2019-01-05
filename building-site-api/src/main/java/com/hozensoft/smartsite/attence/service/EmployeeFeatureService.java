package com.hozensoft.smartsite.attence.service;

import com.hozensoft.smartsite.attence.domain.EmployeeFeature;

public interface EmployeeFeatureService {

    EmployeeFeature findEmployeeFeature(String employeeFeatureId);

    void saveEmployeeFeature(EmployeeFeature employeeFeature);

    void deleteEmployeeFeatureById(String employeeFeatureId);
}
