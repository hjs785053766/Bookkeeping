package com.forezp.api.entity.large_classification;

import com.forezp.api.entity.small_classification.SmallClassification;
import lombok.Data;

import java.util.List;

@Data
public class LargeClassificationSon extends LargeClassification {

    private List<SmallClassification> smallClassificationList;
}
