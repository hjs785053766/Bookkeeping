package com.forezp.api.DB.mapper;

import com.forezp.api.DB.entity.large_classification.LargeClassificationDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeClassificationDBMapper extends MongoRepository<LargeClassificationDB, String> {
}
