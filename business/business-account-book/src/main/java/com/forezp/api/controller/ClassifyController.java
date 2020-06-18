package com.forezp.api.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.api.DB.entity.large_classification.LargeClassificationDB;
import com.forezp.api.DB.entity.small_classification.SmallClassificationDB;
import com.forezp.api.DB.service.able.PublicService;
import com.forezp.api.entity.bill.Bill;
import com.forezp.api.entity.bill.BillSon;
import com.forezp.api.entity.bill_subtable.BillSubtable;
import com.forezp.api.entity.large_classification.LargeClassification;
import com.forezp.api.entity.large_classification.LargeClassificationSon;
import com.forezp.api.entity.small_classification.SmallClassification;
import com.forezp.api.service.able.BillService;
import com.forezp.api.service.able.BillSubtableService;
import com.forezp.api.service.able.LargeClassificationService;
import com.forezp.api.service.able.SmallClassificationService;
import com.forezp.api.DB.service.able.PublicService;
import com.forezp.api.util.BaseApiService;
import com.forezp.api.util.Notice;
import com.forezp.api.utils.ReadExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "测试读取大小类接口")
@RestController
public class ClassifyController extends BaseApiService {

    @Resource
    LargeClassificationService largeClassificationService;
    @Resource
    SmallClassificationService smallClassificationService;
    @Resource
    BillService billService;
    @Resource
    BillSubtableService billSubtableService;

    @Resource
    PublicService<LargeClassificationDB> largeClassificationDBService;
    @Resource
    PublicService<SmallClassificationDB> smallClassificationDBService;

    @GetMapping("/selClassification")
    @ApiOperation("向DB写入分类")
    public Notice selClassification() {
        System.out.println(getUserId());
        List<LargeClassification> list = largeClassificationService.list();
        List<LargeClassificationDB> listDB = new ArrayList<>();
        LargeClassificationDB largeClassificationDB = null;
        for (LargeClassification largeClassification : list) {
            largeClassificationDB = new LargeClassificationDB();

            largeClassificationDB.setLargeClassificationName(largeClassification.getLargeClassificationName());
            largeClassificationDB.setLargeClassificationType(largeClassification.getLargeClassificationType());
            largeClassificationDB.setUserId(Integer.parseInt(getUserId()));
            largeClassificationDB.setAccumulatedAmount(largeClassification.getAccumulatedAmount());
            largeClassificationDB.setLargeClassificationIcon(largeClassification.getLargeClassificationIcon());
            largeClassificationDB.setSortNumber(largeClassification.getSortNumber());

            QueryWrapper<SmallClassification> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                    .eq(SmallClassification::getLargeClassificationId, largeClassification.getId());
            largeClassificationDB.setSmallClassificationList(smallClassificationService.list(queryWrapper));
            listDB.add(largeClassificationDB);
        }
        List<SmallClassification> smallClassificationList = null;
        SmallClassificationDB smallClassificationDB = null;
        for (LargeClassificationDB large : listDB) {
            large.setId(IdUtil.simpleUUID());
            smallClassificationList = large.getSmallClassificationList();
            large.setSmallClassificationList(null);
            largeClassificationDBService.add(large);
            for (SmallClassification smallClassification : smallClassificationList) {
                smallClassificationDB = new SmallClassificationDB();
                smallClassificationDB.setId(IdUtil.simpleUUID());
                smallClassificationDB.setLargeClassificationId(large.getId());
                smallClassificationDB.setSmallClassificationName(smallClassification.getSmallClassificationName());
                smallClassificationDB.setSortNumber(smallClassification.getSortNumber());
                smallClassificationDB.setAccumulatedAmount(smallClassification.getAccumulatedAmount());
                smallClassificationDB.setSmallClassificationIcon(smallClassification.getSmallClassificationIcon());
                smallClassificationDBService.add(smallClassificationDB);
            }
        }
        return new Notice(HttpStatus.OK, "成功");
    }

    @GetMapping("/selXls")
    @ApiOperation("写入xls内容")
    public Notice selXls() throws ParseException {
        for (int i = 0; i < 3; i++) {
            List<BillSon> list = ReadExcel.getXlsExcelData(new File("C:\\Users\\hjs78\\Desktop\\1.xls"), i);
            for (BillSon billSon : list) {
                billSon.setUserId(Integer.parseInt(getUserId()));
                billService.save(billSon);
                for (BillSubtable billSubtable : billSon.getBillSubtableList()) {
                    billSubtable.setBillId(billSon.getBillId());
                    billSubtableService.save(billSubtable);
                }
            }
        }
        return new Notice(HttpStatus.OK, "成功");
    }
}
