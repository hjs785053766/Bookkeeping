package com.forezp.api.mapper;

import com.forezp.api.entity.Picture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureMapper extends MongoRepository<Picture, Long> {
}