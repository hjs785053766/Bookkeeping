package com.forezp.api.service.able;

import com.forezp.api.entity.bill.Bill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forezp.api.entity.bill.BillSon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账单表 服务类
 * </p>
 *
 * @author hjs
 * @since 2020-06-13
 */
public interface BillService extends IService<Bill> {
    List<BillSon> selBill(String value);
}
