package com.zqb.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqb.system.domain.SysDictData;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author zqb
 * @since 2019-01-01
 */
public interface ISysDictDataService extends IService<SysDictData> {

    List<SysDictData> selectDictDataByType(String dictType);

    String selectDictLabel(String dictType, String dictValue);
}
