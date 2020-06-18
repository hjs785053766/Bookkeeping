package com.forezp.api.utils;


import com.forezp.api.entity.bill.Bill;
import com.forezp.api.entity.bill.BillSon;
import com.forezp.api.entity.bill_subtable.BillSubtable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    public static void getXlsxExcelData(File file) {
        InputStream is;
        try {
            is = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
//取每一个工作薄
            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                // 获取当前工作薄的每一行
                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                        //这里需要注意 虽然是第一列数据 但是用这个输出的话得不到表格的数据而是返回 1.0 2.0 这样是数字
                        XSSFCell one = xssfRow.getCell(0);
                        System.out.println("第" + rowNum + "行" + "第1列" + xssfRow.getCell(0));//这样才能输出表格内的内容
                        //第二列数据
                        XSSFCell two = xssfRow.getCell(1);
                        System.out.println("第" + rowNum + "行" + "第2列" + xssfRow.getCell(1));
                        //第三列数据
                        XSSFCell three = xssfRow.getCell(2);
                        System.out.println("第" + rowNum + "行" + "第3列" + xssfRow.getCell(2));


                    }
                }
            }
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 获
    }


    public static List<BillSon> getXlsExcelData(File file, int c) throws ParseException {
        InputStream is;
        List<BillSon> list = new ArrayList<>();
        try {
            is = new FileInputStream(file);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            // 获取每一个工作薄
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {

            }
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(c);
            // 获取当前工作薄的每一行
            int i = 0;
            List<BillSubtable> billSubtableList = null;
            BillSon billSon = null;
            BillSubtable billSubtable = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyy");
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                i = 0;
                billSon = new BillSon();
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
//                    //第一列数据
//                    while (hssfRow.getCell(i) != null) {
//                        System.out.print(hssfRow.getCell(i) + "\t\t\t");
//                        i++;
//                    }
//                    System.out.println();

                    if (rowNum != 0) {
                        billSon.setBillTime(simpleDateFormat.parse(hssfRow.getCell(0) + " " + hssfRow.getCell(1)));
                        billSon.setBillType(hssfRow.getCell(2).toString().equals("支出") ? 1 : hssfRow.getCell(2).toString().equals("收入") ? 2 : hssfRow.getCell(2).toString().equals("转出") || hssfRow.getCell(2).toString().equals("转入") ? 3 : null);
                        if (hssfRow.getCell(5) != null) {
                            switch (hssfRow.getCell(5).toString()) {
                                case "购":
                                    billSon.setLargeClassificationId("0f41d8c6c8cd4dc096b7afb6afde81ff");
                                    switch (hssfRow.getCell(6).toString()) {
                                        case "服饰":
                                        case "鞋子":
                                            billSon.setSmallClassificationId("22e7fe37330645b391cbb31fcd733132");
                                            break;
                                        case "购物":
                                        case "京东":
                                        case "淘宝":
                                            billSon.setSmallClassificationId("f8879af07dab4809800ccf062dd39a66");
                                            break;
                                        case "生活用品":
                                            billSon.setSmallClassificationId("50abd39f2e614747851330fc415fb295");
                                            break;
                                        case "":
                                            billSon.setSmallClassificationId("");
                                            break;
                                    }
                                    break;
                                case "行":
                                    billSon.setLargeClassificationId("c1f0c04a96c74fba889937b0cbdcecb3");
                                    switch (hssfRow.getCell(6).toString()) {
                                        case "打车":
                                        case "交通":
                                        case "汽车票":
                                            billSon.setSmallClassificationId("e04c667cf8b64e439d38f576bdea657e");
                                            break;
                                        case "地铁":
                                            billSon.setSmallClassificationId("9ad336ebdedb431f820350f151d87822");
                                            break;
                                        case "公交车":
                                            billSon.setSmallClassificationId("2f5c08fd8bf24e64980296154904246c");
                                            break;
                                        case "共享单车":
                                            billSon.setSmallClassificationId("181bff0b46a94333a93f91d62cf77396");
                                            break;
                                        case "火车":
                                            billSon.setSmallClassificationId("1fe3d4c009b84a9f82349f14a653ceb4");
                                            break;
                                        case "":
                                            billSon.setSmallClassificationId("");
                                            break;
                                    }
                                    break;
                                case "乐":
                                    billSon.setLargeClassificationId("4d691ec9e82442ca857274ae1c5471e0");
                                    switch (hssfRow.getCell(6).toString()) {
                                        case "KTV":
                                            billSon.setSmallClassificationId("772bf444d5134ff3b80b9ebab3085226");
                                            break;
                                        case "打牌":
                                            billSon.setSmallClassificationId("a18cb51d3817423882f3fbb1ffea0d8a");
                                            break;
                                        case "电影":
                                            billSon.setSmallClassificationId("0e1b21a411eb4766956c8976c30255ff");
                                            break;
                                        case "酒吧":
                                        case "旅游":
                                        case "游戏":
                                            billSon.setSmallClassificationId("6e8f19c175a34ff5810c3b68337f1718");
                                            break;
                                        case "":
                                            billSon.setSmallClassificationId("");
                                            break;
                                    }
                                    break;
                                case "其":
                                    billSon.setLargeClassificationId("3d526b187a4f4f01afe7ebd98662f125");
                                    switch (hssfRow.getCell(6).toString()) {
                                        case "其他":
                                            billSon.setSmallClassificationId("fc1c22dfb4b44f35978a4c18c9cf83dc");
                                            break;
                                        case "饿了么会员":
                                            billSon.setLargeClassificationId("a7137a4723a14f179984ef4b8f32160e");
                                            billSon.setSmallClassificationId("e145f5df983848f787b41fe58663e51c");
                                            break;
                                        case "饿了么会员叠加卷":
                                            billSon.setLargeClassificationId("a7137a4723a14f179984ef4b8f32160e");
                                            billSon.setSmallClassificationId("2e82a9bc34974341a7aa63f383e62af7");
                                            break;
                                        case "美团会员叠加卷":
                                            billSon.setLargeClassificationId("a7137a4723a14f179984ef4b8f32160e");
                                            billSon.setSmallClassificationId("1c45363fdf334aa287d14789a92c23d3");
                                            break;
                                        case "":
                                            billSon.setSmallClassificationId("");
                                            break;
                                    }
                                    break;
                                case "情":
                                    billSon.setLargeClassificationId("2430eaad70cc471888fbe353090602ba");
                                    switch (hssfRow.getCell(6).toString()) {
                                        case "菜钱":
                                        case "饭钱":
                                        case "还花呗":
                                        case "还钱":
                                        case "借出":
                                        case "礼物":
                                        case "人情世故":
                                            billSon.setLargeClassificationId("3d526b187a4f4f01afe7ebd98662f125");
                                            billSon.setSmallClassificationId("fc1c22dfb4b44f35978a4c18c9cf83dc");
                                            break;
                                        case "红包":
                                            billSon.setSmallClassificationId("e306b5c46a884638ae05d29b6405de3e");
                                            break;
                                        case "":
                                            billSon.setSmallClassificationId("");
                                            break;
                                    }
                                    break;
                                case "食":
                                    billSon.setLargeClassificationId("594bb9f5be624db8b260aedd392b4892");
                                    switch (hssfRow.getCell(6).toString()) {
                                        case "酒水":
                                            billSon.setSmallClassificationId("d76f4946e0c64b3baae56e13e3c8038e");
                                            break;
                                        case "零食":
                                            billSon.setSmallClassificationId("fd159734cbaf4a828e8e7f797bcd12d6");
                                            break;
                                        case "买菜":
                                            billSon.setSmallClassificationId("25b3eff6f1224578a68eba63326cb933");
                                            break;
                                        case "其他支出":
                                        case "桶装水":
                                            billSon.setLargeClassificationId("3d526b187a4f4f01afe7ebd98662f125");
                                            billSon.setSmallClassificationId("fc1c22dfb4b44f35978a4c18c9cf83dc");
                                            break;
                                        case "水果":
                                            billSon.setSmallClassificationId("9f32587d3a684a898cb0e0061650d328");
                                            break;
                                        case "晚餐":
                                            billSon.setSmallClassificationId("b7b7c5f581774483995f3890e07902de");
                                            break;
                                        case "午餐":
                                            billSon.setSmallClassificationId("bb8cfe9eb636406aa2101864153cbd65");
                                            break;
                                        case "饮料":
                                            billSon.setSmallClassificationId("d76f4946e0c64b3baae56e13e3c8038e");
                                            break;
                                        case "早餐":
                                            billSon.setSmallClassificationId("00bde43bac8541048a76b7234118eb37");
                                            break;
                                        case "":
                                            billSon.setSmallClassificationId("");
                                            break;
                                    }
                                    break;
                                case "医":
                                    billSon.setLargeClassificationId("0d1073fcea704f4682b6506697b0d45f");
                                    switch (hssfRow.getCell(6).toString()) {
                                        case "药品":
                                            billSon.setSmallClassificationId("17b322bffd6c484d8d61c5b08b052622");
                                            break;
                                        case "":
                                            billSon.setSmallClassificationId("");
                                            break;
                                    }
                                    break;
                                case "住":
                                    billSon.setLargeClassificationId("23e8157501b34142b99d0cbdbc6ebd94");
                                    switch (hssfRow.getCell(6).toString()) {
                                        case "电费":
                                            billSon.setSmallClassificationId("7007c6f8b8474c52aad3013204ba28c4");
                                            break;
                                        case "房租":
                                            billSon.setSmallClassificationId("259f26ef7cfe494d919d34afb65f3377");
                                            break;
                                        case "话费":
                                            billSon.setLargeClassificationId("3e0c7b8142ff4c6f936c4206c0d4cc24");
                                            billSon.setSmallClassificationId("95b9d60ab01546edb24c208c55c0f932");
                                            break;
                                        case "家居":
                                            billSon.setLargeClassificationId("0f41d8c6c8cd4dc096b7afb6afde81ff");
                                            billSon.setSmallClassificationId("659828a710fe490e927d6cf79b21c947");
                                            break;
                                        case "理发":
                                            billSon.setLargeClassificationId("3d526b187a4f4f01afe7ebd98662f125");
                                            billSon.setSmallClassificationId("fc1c22dfb4b44f35978a4c18c9cf83dc");
                                            break;
                                        case "燃气费":
                                            billSon.setSmallClassificationId("e440aedb02b1430c9646d176bfab5f1a");
                                            break;
                                        case "水费":
                                            billSon.setSmallClassificationId("7c71a6c24ece4d7d9c297f1ddc9d9c9d");
                                            break;
                                        case "网络费":
                                            billSon.setLargeClassificationId("3d526b187a4f4f01afe7ebd98662f125");
                                            billSon.setSmallClassificationId("fc1c22dfb4b44f35978a4c18c9cf83dc");
                                            break;
                                        case "物业费":
                                            billSon.setSmallClassificationId("fb2807e1613348dfbf7a4b6c0fda4b39");
                                            break;
                                        case "":
                                            billSon.setSmallClassificationId("");
                                            break;
                                    }
                                    break;
                                case "":
                                    billSon.setLargeClassificationId("");
                                    break;
                            }
                        }
                        String money = ((Double.parseDouble(hssfRow.getCell(7).toString()) * 100) + "");
                        money = money.substring(0, money.lastIndexOf("."));
                        billSon.setMoney(Long.parseLong(money));
                        billSon.setBillRemarks(hssfRow.getCell(9) != null ? hssfRow.getCell(9).toString() : null);
                        billSubtableList = new ArrayList<>();

                        if (hssfRow.getCell(4) != null) {
                            billSubtable = new BillSubtable();
                            billSubtable.setSubtableTypeId(SubtableType.walletMame);
                            billSubtable.setSubtableValue(hssfRow.getCell(4).toString());
                            billSubtableList.add(billSubtable);
                        }

                        if (hssfRow.getCell(5) != null) {
                            billSubtable = new BillSubtable();
                            billSubtable.setSubtableTypeId(SubtableType.largeClassificationName);
                            billSubtable.setSubtableValue(hssfRow.getCell(5).toString());
                            billSubtableList.add(billSubtable);
                        }

                        if (hssfRow.getCell(6) != null) {
                            billSubtable = new BillSubtable();
                            billSubtable.setSubtableTypeId(SubtableType.SubcategoryName);
                            billSubtable.setSubtableValue(hssfRow.getCell(6).toString());
                            billSubtableList.add(billSubtable);
                        }
                        if (billSon.getBillTime() != null) {
                            billSubtable = new BillSubtable();
                            billSubtable.setSubtableTypeId(SubtableType.SpecificDate);
                            billSubtable.setSubtableValue(simpleDateFormat2.format(billSon.getBillTime()));
                            billSubtableList.add(billSubtable);
                            billSubtable = new BillSubtable();
                            billSubtable.setSubtableTypeId(SubtableType.years);
                            billSubtable.setSubtableValue(simpleDateFormat3.format(billSon.getBillTime()));
                            billSubtableList.add(billSubtable);
                            billSubtable = new BillSubtable();
                            billSubtable.setSubtableTypeId(SubtableType.year);
                            billSubtable.setSubtableValue(simpleDateFormat4.format(billSon.getBillTime()));
                            billSubtableList.add(billSubtable);
                        }
                        billSon.setBillSubtableList(billSubtableList);
                        list.add(billSon);
                    }
                }
            }
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String arfs[]) {
//        ReadExcel.getXlsxExcelData(new File("C:\\Users\\hjs78\\Desktop\\1.xls"));

        System.out.println("----------------------------------");
        try {
            ReadExcel.getXlsExcelData(new File("C:\\Users\\hjs78\\Desktop\\1.xls"), 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
