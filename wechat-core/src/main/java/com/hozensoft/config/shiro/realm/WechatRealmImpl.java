package com.hozensoft.config.shiro.realm;

import com.hozensoft.config.shiro.authc.WechatOpenIDToken;
import com.hozensoft.global.core.dto.PermissionItemDto;
import com.hozensoft.smartsite.attence.dto.EmployeeDto;
import com.hozensoft.smartsite.attence.service.EmployeeService;
import com.hozensoft.system.authorization.service.StaffAuthorizationService;
import com.hozensoft.system.core.dto.PositionDto;
import com.hozensoft.system.core.dto.StaffDto;
import com.hozensoft.system.core.service.StaffService;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.wechat.common.dao.repo.WxStaffLinkRepo;
import com.hozensoft.wechat.common.domain.WxStaffLink;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class WechatRealmImpl extends AuthorizingRealm implements WechatRealm {

    @Autowired
    private StaffAuthorizationService staffAuthorizationService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private EmployeeService employeeService;

    public boolean supports(AuthenticationToken token) {

        return token instanceof WechatOpenIDToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        PositionDto position = (PositionDto) SecurityUtils.getSubject().getSession().getAttribute(ContextUtils.CURRENT_POSITION);

        List<PermissionItemDto> permissions = staffAuthorizationService.findStaffAuthorizedPermissionList(ContextUtils.buildHolder(position), position.getStaffId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.setStringPermissions(permissions.stream().map((item)->( item.getWildcard())).collect(Collectors.toSet()));

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        WechatOpenIDToken openIDToken = (WechatOpenIDToken)token;

        EmployeeDto emp = employeeService.findEmployeeById(ContextHolder.builder().tenantId(openIDToken.getTenantId()).build(), openIDToken.getStaffId());

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(emp.getId(), "NULL", getName());

        return info;
    }
}
