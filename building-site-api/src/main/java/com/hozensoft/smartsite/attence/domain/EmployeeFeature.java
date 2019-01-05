package com.hozensoft.smartsite.attence.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工特征
 */
@Getter
@Setter
public class EmployeeFeature {

    /**
     * ID
     */
    private String id;

    /**
     * 身份证号码
     */
    private String idCardNo;

    /**
     * 指纹特征码
     */
    private String fingerprint;

    /**
     * 脸部认证码
     */
    private String face;
}
