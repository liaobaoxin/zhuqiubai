package com.zqb.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqb.system.domain.SysUserPost;

import java.util.List;

/**
 * <p>
 * 用户与岗位关联表 Mapper 接口
 * </p>
 *
 * @author zqb
 * @since 2018-12-23
 */
public interface SysUserPostMapper extends BaseMapper<SysUserPost> {

    /**
     * 批量新增用户岗位信息
     *
     * @param userPostList 用户角色列表
     * @return 结果
     */
    public int batchUserPost(List<SysUserPost> userPostList);

    /**
     * 通过用户ID删除用户和岗位关联
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserPostByUserId(Long userId);

}
