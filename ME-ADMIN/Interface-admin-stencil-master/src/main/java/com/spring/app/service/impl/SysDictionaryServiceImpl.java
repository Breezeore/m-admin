package com.spring.app.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.spring.app.mapper.system.SysDictDataMapper;
import com.spring.app.mapper.system.SysDictTypeMapper;
import com.spring.app.pojo.table.SysDictData;
import com.spring.app.pojo.table.SysDictType;
import com.spring.app.response.RespData;
import com.spring.app.response.RespMethod;
import com.spring.app.response.other.Pagination;
import com.spring.app.response.other.PaginationUtil;
import com.spring.app.service.SysDictionaryService;
import com.spring.app.utils.DataProcessing.GenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.spring.app.pojo.table.table.SysDictDataTableDef.SYS_DICT_DATA;
import static com.spring.app.pojo.table.table.SysDictTypeTableDef.SYS_DICT_TYPE;

/**
 * @ClassName: SysDictionaryServiceImpl
 * @Date: 2023/11/5 23:38
 * @Description:
 **/
@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {
    @Autowired
    private RespMethod respMethod;

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 获取字典的分页数据
     * @param pagination
     * @return
     */
    @Override
    public RespData getDictList(Pagination pagination) {
        return respMethod.inquireSuccess(sysDictTypeMapper.paginate(pagination.getPage(),pagination.getLimit(), PaginationUtil.getMapper(SYS_DICT_TYPE,pagination)));
    }

    /**
     * 获取单个字典的详细数据
     * @param type
     * @return
     */
    @Override
    public RespData getDictData(String type) {
        //如果字典被禁用就不返回数据
        SysDictType sysDictType = sysDictTypeMapper.selectOneByQuery(QueryWrapper.create()
                .select(SYS_DICT_TYPE.ALL_COLUMNS)
                .where(SYS_DICT_TYPE.DICT_TYPE.eq(type)));
        if (sysDictType.getStatus().equals("1")) {
            //返回错误字典,给出默认的错误字典信息
            SysDictData sysDictData = new SysDictData();
            sysDictData.setDict_value("-9999");
            sysDictData.setDict_label("该字典已被禁用");
            sysDictData.setInfo("error");
            List<SysDictData> sysDictDataList = new ArrayList<>();
            sysDictDataList.add(sysDictData);
            return respMethod.inquireSuccess(sysDictDataList);
        }

        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(SYS_DICT_DATA.ALL_COLUMNS)
                .where(SYS_DICT_DATA.DICT_TYPE.eq(type))
                .and(SYS_DICT_DATA.STATUS.eq(0));
        return respMethod.inquireSuccess(sysDictDataMapper.selectListByQuery(queryWrapper));
    }

    /**
     * 新增数据字典
     * @param sysDictType
     * @return
     */
    @Override
    public RespData addDict(SysDictType sysDictType) {
        //查询是否有重复的字典类型
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_DICT_TYPE.DICT_TYPE.eq(sysDictType.getDict_type()));
        if (sysDictTypeMapper.selectOneByQuery(queryWrapper) != null) {
            return respMethod.operateErrorByMessage("字典标识已存在");
        }


        //获取创建时间
        sysDictType.setCreate_time(GenerateUtils.getNowDateTime());
        //没有状态就默认为0
        if (sysDictType.getStatus() == null) {
            sysDictType.setStatus("0");
        }
        return respMethod.insert(sysDictTypeMapper.insert(sysDictType));
    }

    /**
     * 修改数据字典
     * @param sysDictType
     * @return
     */
    @Override
    public RespData upDict(SysDictType sysDictType) {
        // 修改的数据需要排除它本身，在其他数据中也不存在，才可以修改
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_DICT_TYPE.DICT_TYPE.eq(sysDictType.getDict_type()));
        List<SysDictType> sysDictTypes = sysDictTypeMapper.selectListByQuery(queryWrapper);

        //如果查询到的数据大于1就说明有重复的字典标识
        if (sysDictTypes.size() > 1) {
            return respMethod.operateErrorByMessage("字典标识已存在");
        } else if (sysDictTypes.size() == 1) {
            //如果查询到的数据等于1就说明有重复的字典标识
            if (!sysDictTypes.get(0).getId().equals(sysDictType.getId())) {
                return respMethod.operateErrorByMessage("字典标识已存在");
            }
        }

        //创建时间不可以修改
        sysDictType.setCreate_time(null);

        return respMethod.update(sysDictTypeMapper.update(sysDictType));
    }

    /**
     * 删除数据字典
     * @param id
     * @return
     */
    @Override
    public RespData delDict(String type) {
        //清空数据字典对应字典里面的数据 | 避免产生垃圾数据
        QueryWrapper dictDataQueryWrapper = QueryWrapper.create()
                .where(SYS_DICT_DATA.DICT_TYPE.eq(type));
        sysDictDataMapper.deleteByQuery(dictDataQueryWrapper);

        //删除数据字典
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_DICT_TYPE.DICT_TYPE.eq(type));
        return respMethod.delete(sysDictTypeMapper.deleteByQuery(queryWrapper));
    }

}
