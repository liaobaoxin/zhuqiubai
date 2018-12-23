package com.zqb.framework.shiro.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zqb.common.constant.Constants;
import com.zqb.common.constant.ShiroConstants;
import com.zqb.common.constant.UserConstants;
import com.zqb.common.enums.UserStatus;
import com.zqb.common.utils.DateUtils;
import com.zqb.framework.manager.AsyncManager;
import com.zqb.framework.manager.factory.AsyncFactory;
import com.zqb.framework.util.MessageUtils;
import com.zqb.framework.util.RecordUtils;
import com.zqb.framework.util.ServletUtils;
import com.zqb.framework.util.ShiroUtils;
import com.zqb.framework.web.exection.user.*;
import com.zqb.system.domain.SysUser;
import com.zqb.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService {
    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysUserService userService;

    /**
     * 登录
     */
    public SysUser login(String username, String password) {
        // 验证码校验
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            RecordUtils.LoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            RecordUtils.LoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null"));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            RecordUtils.LoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            RecordUtils.LoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getLoginName, username);
        SysUser user = userService.getOne(wrapper);

        if (user == null && maybeMobilePhoneNumber(username)) {
            wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(SysUser::getPhonenumber, username);
            user = userService.getOne(wrapper);
        }

        if (user == null && maybeEmail(username)) {
            wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(SysUser::getPhonenumber, username);
            user = userService.getOne(wrapper);
        }

        if (user == null) {
            RecordUtils.LoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            RecordUtils.LoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete"));
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            RecordUtils.LoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark()));
            throw new UserBlockedException(user.getRemark());
        }

        passwordService.validate(user, password);

        RecordUtils.LoginInfo(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        recordLoginInfo(user);
        return user;
    }

    private boolean maybeEmail(String username) {
        if (!username.matches(UserConstants.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(LocalDateTime.now());
        userService.saveOrUpdate(user);
    }
}
