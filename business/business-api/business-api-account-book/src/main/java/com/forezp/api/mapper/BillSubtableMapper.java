package com.forezp.api.mapper;

import com.forezp.api.entity.bill_subtable.BillSubtable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.api.entity.bill_subtable.BillSubtableSon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账单子表 Mapper 接口
 * </p>
 *
 * @author hjs
 * @since 2020-06-13
 */
public interface BillSubtableMapper extends BaseMapper<BillSubtable> {

    List<BillSubtableSon> selSpecificDate(@Param("typeId") Integer typeId, @Param("value") String value, @Param("size") Integer size);
}
