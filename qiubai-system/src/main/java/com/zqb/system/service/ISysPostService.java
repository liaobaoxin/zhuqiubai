package com.zqb.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqb.system.domain.SysPost;

import java.util.List;

/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author zqb
 * @since 2018-12-23
 */
public interface ISysPostService extends IService<SysPost> {
    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    public List<SysPost> selectPostsByUserId(Long userId);

}
