package com.forezp.api.controller.bill;

import com.forezp.api.entity.bill.BillSon;
import com.forezp.api.entity.bill_subtable.BillSubtable;
import com.forezp.api.entity.bill_subtable.BillSubtableSon;
import com.forezp.api.service.able.BillService;
import com.forezp.api.service.able.BillSubtableService;
import com.forezp.api.util.BaseApiService;
import com.forezp.api.util.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "账单")
@RestController
public class BillController extends BaseApiService {

    @Resource
    BillSubtableService billSubtableService;
    @Resource
    BillService billService;

    @GetMapping("/selBill")
    @ApiOperation("查询默认当日账单")
    public Notice selBill() {
        List<BillSubtableSon> billSubtableYearList = billSubtableService.selSpecificDate("5", null, "10");//查询默认年
        BillSubtableSon billSubtableYear = billSubtableYearList.get(0);//取第一个年级
        List<BillSubtableSon> billSubtableMonthList = billSubtableService.selSpecificDate("4", billSubtableYear.getSubtableValue(), "10");//查询默认月
        BillSubtableSon billSubtableMonth = billSubtableMonthList.get(0);//取第一个月级
        List<BillSubtableSon> billSubtableDayList = billSubtableService.selSpecificDate("3", billSubtableMonth.getSubtableValue(), "10");//查询默认日
        BillSubtableSon billSubtableDay = billSubtableDayList.get(0);//取第一个日级
        billSubtableDay.setBillList(billService.selBill(billSubtableDay.getSubtableValue()));
        billSubtableDayList.set(0, billSubtableDay);
        billSubtableMonth.setBillSubtableDay(billSubtableDayList);
        billSubtableMonthList.set(0, billSubtableMonth);
        billSubtableYear.setBillSubtableMonth(billSubtableMonthList);
        billSubtableYearList.set(0, billSubtableYear);
        return new Notice(HttpStatus.OK, billSubtableYearList, "成功");
    }
}
