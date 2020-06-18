package com.forezp.api.service.able;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forezp.api.entity.bill_subtable.BillSubtable;
import com.forezp.api.entity.bill_subtable.BillSubtableSon;

import java.util.List;

/**
 * <p>
 * 账单子表 服务类
 * </p>
 *
 * @author hjs
 * @since 2020-06-13
 */
public interface BillSubtableService extends IService<BillSubtable> {

    List<BillSubtableSon> selSpecificDate(String typeId, String value, String size);
}
