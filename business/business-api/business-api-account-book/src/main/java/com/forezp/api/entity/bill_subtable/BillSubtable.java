package com.forezp.api.entity.bill_subtable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 账单子表
 * </p>
 *
 * @author hjs
 * @since 2020-06-13
 */
@TableName("bill_subtable")
public class BillSubtable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bill_subtable_id", type = IdType.AUTO)
    private Integer billSubtableId;

    /**
     * 账单表id
     */
    private Integer billId;

    /**
     * 子表类型id
     */
    private Integer subtableTypeId;

    /**
     * 参数
     */
    private String subtableValue;

    public Integer getBillSubtableId() {
        return billSubtableId;
    }

    public void setBillSubtableId(Integer billSubtableId) {
        this.billSubtableId = billSubtableId;
    }
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }
    public Integer getSubtableTypeId() {
        return subtableTypeId;
    }

    public void setSubtableTypeId(Integer subtableTypeId) {
        this.subtableTypeId = subtableTypeId;
    }
    public String getSubtableValue() {
        return subtableValue;
    }

    public void setSubtableValue(String subtableValue) {
        this.subtableValue = subtableValue;
    }

    @Override
    public String toString() {
        return "BillSubtable{" +
        "billSubtableId=" + billSubtableId +
        ", billId=" + billId +
        ", subtableTypeId=" + subtableTypeId +
        ", subtableValue=" + subtableValue +
        "}";
    }
}
