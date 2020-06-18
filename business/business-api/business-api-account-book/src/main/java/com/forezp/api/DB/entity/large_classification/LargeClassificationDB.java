package com.forezp.api.DB.entity.large_classification;

import com.forezp.api.entity.large_classification.LargeClassification;
import com.forezp.api.entity.small_classification.SmallClassification;
import lombok.Data;

import java.util.List;

@Data
public class LargeClassificationDB extends LargeClassification {

    private List<SmallClassification> smallClassificationList;
}
