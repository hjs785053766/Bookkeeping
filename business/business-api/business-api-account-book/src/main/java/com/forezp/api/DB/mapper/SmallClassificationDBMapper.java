package com.forezp.api.DB.mapper;

import com.forezp.api.DB.entity.small_classification.SmallClassificationDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallClassificationDBMapper extends MongoRepository<SmallClassificationDB, String> {
}
