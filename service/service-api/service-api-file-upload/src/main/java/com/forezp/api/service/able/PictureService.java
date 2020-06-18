package com.forezp.api.service.able;

import com.forezp.api.entity.Picture;

import java.util.List;

public interface PictureService {

    Picture addPicture(Picture student);

    void deletePicture(Long id);

    Picture updatePicture(Picture student);

    Picture findPictureById(Long id);

    List<Picture> findAllPicture();

}