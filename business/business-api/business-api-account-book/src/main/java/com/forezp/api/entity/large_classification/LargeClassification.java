package com.forezp.api.entity.large_classification;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 大分类表
 * </p>
 *
 * @author hjs
 * @since 2020-06-12
 */
@TableName("large_classification")
public class LargeClassification implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 排序号
     */
    @TableId(value = "sort_number", type = IdType.AUTO)
    private Integer sortNumber;

    /**
     * 所属人用户id
     */
    private Integer userId;

    /**
     * 分类（1：支出，2：收入）
     */
    private Integer largeClassificationType;

    /**
     * 分类名
     */
    private String largeClassificationName;

    /**
     * 大分类图标地址
     */
    @TableField("large_classification_Icon")
    private String largeClassificationIcon;

    /**
     * 累计金额
     */
    private Long accumulatedAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getLargeClassificationType() {
        return largeClassificationType;
    }

    public void setLargeClassificationType(Integer largeClassificationType) {
        this.largeClassificationType = largeClassificationType;
    }
    public String getLargeClassificationName() {
        return largeClassificationName;
    }

    public void setLargeClassificationName(String largeClassificationName) {
        this.largeClassificationName = largeClassificationName;
    }
    public String getLargeClassificationIcon() {
        return largeClassificationIcon;
    }

    public void setLargeClassificationIcon(String largeClassificationIcon) {
        this.largeClassificationIcon = largeClassificationIcon;
    }
    public Long getAccumulatedAmount() {
        return accumulatedAmount;
    }

    public void setAccumulatedAmount(Long accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }

    @Override
    public String toString() {
        return "LargeClassification{" +
        "id=" + id +
        ", sortNumber=" + sortNumber +
        ", userId=" + userId +
        ", largeClassificationType=" + largeClassificationType +
        ", largeClassificationName=" + largeClassificationName +
        ", largeClassificationIcon=" + largeClassificationIcon +
        ", accumulatedAmount=" + accumulatedAmount +
        "}";
    }
}
