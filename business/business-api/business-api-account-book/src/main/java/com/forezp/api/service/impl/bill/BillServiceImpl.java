package com.forezp.api.service.impl.bill;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.api.entity.bill.Bill;
import com.forezp.api.entity.bill.BillSon;
import com.forezp.api.entity.bill_subtable.BillSubtable;
import com.forezp.api.mapper.BillMapper;
import com.forezp.api.service.able.BillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forezp.api.service.able.BillSubtableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 账单表 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-06-13
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {

    @Resource
    BillSubtableService billSubtableService;

    @Override
    public List<BillSon> selBill(String value) {
        List<BillSon> billSonList = new ArrayList<>();
        List<BillSon> billList = getBaseMapper().selBill(value);
        for (BillSon billSon : billList) {
            QueryWrapper<BillSubtable> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                    .eq(BillSubtable::getBillId, billSon.getBillId());
            billSon.setBillSubtableList(billSubtableService.list(queryWrapper));
            billSonList.add(billSon);
        }
        return billSonList;
    }
}
