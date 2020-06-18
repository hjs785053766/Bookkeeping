package com.forezp.api.service.impl;

import com.forezp.api.mapper.PictureMapper;
import com.forezp.api.entity.Picture;
import com.forezp.api.service.able.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    /**
     * @param picture
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Picture addPicture(Picture picture) {
        return pictureMapper.save(picture);
    }

    /**
     * @param id
     */
    @Override
    public void deletePicture(Long id) {
        pictureMapper.deleteById(id);
    }

    /**
     * @param picture
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Picture updatePicture(Picture picture) {
        Picture oldStudent = this.findPictureById(picture.getId());
        if (oldStudent != null) {
            oldStudent.setPictureAddressl(picture.getPictureAddressl());
            oldStudent.setThumbnailAddress(picture.getThumbnailAddress());
            return pictureMapper.save(oldStudent);
        } else {
            return null;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Picture findPictureById(Long id) {
        return pictureMapper.findById(id).get();
    }

    /**
     * @return
     */
    @Override
    public List<Picture> findAllPicture() {
        return pictureMapper.findAll();
    }
}
