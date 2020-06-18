package com.forezp.api.entity.bill;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 账单表
 * </p>
 *
 * @author hjs
 * @since 2020-06-13
 */
@TableName("bill")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bill_id", type = IdType.AUTO)
    private Integer billId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 账单类型（1：支出，2：收入，3：转账）
     */
    private Integer billType;

    /**
     * 金额（分）
     */
    private Long money;

    /**
     * 大分类id(类型等于3转账的时候为空)
     */
    private String largeClassificationId;

    /**
     * 小分类id(类型等于3转账的时候为空)
     */
    private String smallClassificationId;

    /**
     * 出账钱包id
     */
    private Integer outgoingWalletId;

    /**
     * 入账钱包id(类型等于1支出的时候为空)
     */
    private Integer entryWalletId;

    /**
     * 账单时间
     */
    private Date billTime;

    /**
     * 账单备注
     */
    private String billRemarks;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getLargeClassificationId() {
        return largeClassificationId;
    }

    public void setLargeClassificationId(String largeClassificationId) {
        this.largeClassificationId = largeClassificationId;
    }

    public String getSmallClassificationId() {
        return smallClassificationId;
    }

    public void setSmallClassificationId(String smallClassificationId) {
        this.smallClassificationId = smallClassificationId;
    }

    public Integer getOutgoingWalletId() {
        return outgoingWalletId;
    }

    public void setOutgoingWalletId(Integer outgoingWalletId) {
        this.outgoingWalletId = outgoingWalletId;
    }

    public Integer getEntryWalletId() {
        return entryWalletId;
    }

    public void setEntryWalletId(Integer entryWalletId) {
        this.entryWalletId = entryWalletId;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }

    public String getBillRemarks() {
        return billRemarks;
    }

    public void setBillRemarks(String billRemarks) {
        this.billRemarks = billRemarks;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", userId=" + userId +
                ", billType=" + billType +
                ", money=" + money +
                ", largeClassificationId=" + largeClassificationId +
                ", smallClassificationId=" + smallClassificationId +
                ", outgoingWalletId=" + outgoingWalletId +
                ", entryWalletId=" + entryWalletId +
                ", billTime=" + billTime +
                ", billRemarks=" + billRemarks +
                "}";
    }
}
