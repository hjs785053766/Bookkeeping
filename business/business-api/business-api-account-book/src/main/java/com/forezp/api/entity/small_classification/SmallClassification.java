package com.forezp.api.entity.small_classification;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 小分类表
 * </p>
 *
 * @author hjs
 * @since 2020-06-12
 */
@TableName("small_classification")
public class SmallClassification implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 排序号
     */
    @TableId(value = "sort_number", type = IdType.AUTO)
    private Integer sortNumber;

    /**
     * 大分类id
     */
    private String largeClassificationId;

    /**
     * 小分类名
     */
    private String smallClassificationName;

    /**
     * 小分类图标地址
     */
    @TableField("small_classification_Icon")
    private String smallClassificationIcon;

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
    public String getLargeClassificationId() {
        return largeClassificationId;
    }

    public void setLargeClassificationId(String largeClassificationId) {
        this.largeClassificationId = largeClassificationId;
    }
    public String getSmallClassificationName() {
        return smallClassificationName;
    }

    public void setSmallClassificationName(String smallClassificationName) {
        this.smallClassificationName = smallClassificationName;
    }
    public String getSmallClassificationIcon() {
        return smallClassificationIcon;
    }

    public void setSmallClassificationIcon(String smallClassificationIcon) {
        this.smallClassificationIcon = smallClassificationIcon;
    }
    public Long getAccumulatedAmount() {
        return accumulatedAmount;
    }

    public void setAccumulatedAmount(Long accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }

    @Override
    public String toString() {
        return "SmallClassification{" +
        "id=" + id +
        ", sortNumber=" + sortNumber +
        ", largeClassificationId=" + largeClassificationId +
        ", smallClassificationName=" + smallClassificationName +
        ", smallClassificationIcon=" + smallClassificationIcon +
        ", accumulatedAmount=" + accumulatedAmount +
        "}";
    }
}
