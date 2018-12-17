package cn.jf.common.shiro;

import cn.jf.model.staff.Staff;
import cn.jf.service.staff.StaffService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private StaffService staffService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String)principalCollection.getPrimaryPrincipal();
        Staff staff = staffService.findStaffByPhone(userName);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.setRoles(userService.findRolesByUserId(user.getId()));
        Set<String> roles=new HashSet<>();
        roles.add("1");
        authorizationInfo.setRoles(roles);
        Set<String> permissions=new HashSet<>();
        permissions.add("1");
        authorizationInfo.setStringPermissions(permissions);
        //authorizationInfo.setStringPermissions(userService.findPermissionsByUserId(user.getId()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName= (String)authenticationToken.getPrincipal();
        Staff staff = staffService.findStaffByPhone(userName);
        if(staff == null) {
            throw new UnknownAccountException();//没找到账户
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                staff.getPhone(), // 用户名
                staff.getPwd(), // 密码
                //ByteSource.Util.bytes(user.getCredentialsSalt()),    // salt
                getName()  // realm name
        );
        return authenticationInfo;
    }
}
