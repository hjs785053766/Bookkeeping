package com.forezp.api.mapper;

import com.forezp.api.entity.bill.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.api.entity.bill.BillSon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账单表 Mapper 接口
 * </p>
 *
 * @author hjs
 * @since 2020-06-13
 */
public interface BillMapper extends BaseMapper<Bill> {

    List<BillSon> selBill(@Param("value") String value);
}
