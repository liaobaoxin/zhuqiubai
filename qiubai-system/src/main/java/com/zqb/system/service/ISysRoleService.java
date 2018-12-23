package com.zqb.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqb.system.domain.SysRole;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author zqb
 * @since 2018-12-16
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
     Set<String> selectRoleKeys(Long userId);

}
