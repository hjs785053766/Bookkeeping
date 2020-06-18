package com.forezp.api.service.impl.bill_subtable;

import com.forezp.api.entity.bill_subtable.BillSubtable;
import com.forezp.api.entity.bill_subtable.BillSubtableSon;
import com.forezp.api.mapper.BillSubtableMapper;
import com.forezp.api.service.able.BillSubtableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 账单子表 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-06-13
 */
@Service
public class BillSubtableServiceImpl extends ServiceImpl<BillSubtableMapper, BillSubtable> implements BillSubtableService {

    @Override
    public List<BillSubtableSon> selSpecificDate(String typeId, String value, String size) {
        return getBaseMapper().selSpecificDate(Integer.parseInt(typeId), value, Integer.parseInt(size));
    }
}
