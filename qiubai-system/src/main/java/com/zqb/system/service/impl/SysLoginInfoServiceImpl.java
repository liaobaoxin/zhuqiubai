package com.zqb.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqb.system.domain.SysLoginInfo;
import com.zqb.system.mapper.SysLoginInfoMapper;
import com.zqb.system.service.ISysLoginInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-16
 */
@Service
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfo> implements ISysLoginInfoService {

}
