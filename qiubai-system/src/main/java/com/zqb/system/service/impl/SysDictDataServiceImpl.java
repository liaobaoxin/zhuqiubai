package com.zqb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqb.system.domain.SysDictData;
import com.zqb.system.mapper.SysDictDataMapper;
import com.zqb.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author zqb
 * @since 2019-01-01
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService {


    @Autowired
    SysDictDataMapper sysDictDataMapper;

    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<SysDictData> data = baseMapper.selectList(new QueryWrapper<SysDictData>().lambda().eq(SysDictData::getDictType, dictType));
        return data;
    }

    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        SysDictData dictData = sysDictDataMapper.selectOne(new QueryWrapper<SysDictData>().lambda().eq(SysDictData::getDictType, dictType).eq(SysDictData::getDictValue,dictValue));
        return dictData.getDictLabel();
    }
}
