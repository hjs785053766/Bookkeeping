package com.forezp.api.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Picture {
    Long id;
    String pictureAddressl;
    String thumbnailAddress;
    String pictureName;
    String pictureSize;
    Date creationTime;
}
