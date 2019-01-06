package com.zqb.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqb.system.domain.SysPost;
import com.zqb.system.mapper.SysPostMapper;
import com.zqb.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author zqb
 * @since 2018-12-23
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements ISysPostService {
    @Autowired
    private SysPostMapper postMapper;

    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostsByUserId(Long userId)
    {
        List<SysPost> userPosts = postMapper.selectPostsByUserId(userId);
        List<SysPost> posts = postMapper.selectPostAll();
        for (SysPost post : posts)
        {
            for (SysPost userRole : userPosts)
            {
                if (post.getPostId().longValue() == userRole.getPostId().longValue())
                {
                    post.setFlag(true);
                    break;
                }
            }
        }
        return posts;
    }
}
