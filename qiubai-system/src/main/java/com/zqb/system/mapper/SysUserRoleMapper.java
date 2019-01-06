package com.zqb.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqb.system.domain.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author zqb
 * @since 2018-12-23
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {


    /**
     * 通过用户ID删除用户和角色关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserRoleByUserId(Long userId);

    /**
     * 批量删除用户和角色关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserRole(Long[] ids);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * 批量新增用户角色信息
     *
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    public int batchUserRole(List<SysUserRole> userRoleList);
}
