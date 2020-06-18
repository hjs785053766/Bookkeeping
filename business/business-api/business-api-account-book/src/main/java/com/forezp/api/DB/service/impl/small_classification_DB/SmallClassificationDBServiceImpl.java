package com.forezp.api.DB.service.impl.small_classification_DB;

import com.forezp.api.DB.entity.small_classification.SmallClassificationDB;
import com.forezp.api.DB.mapper.SmallClassificationDBMapper;
import com.forezp.api.DB.service.able.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmallClassificationDBServiceImpl implements PublicService<SmallClassificationDB> {

    @Autowired
    private SmallClassificationDBMapper smallClassificationDBMapper;

    /**
     * @param DB
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SmallClassificationDB add(SmallClassificationDB DB) {
        return smallClassificationDBMapper.save(DB);
    }

    /**
     * @param id
     */
    @Override
    public void delete(String id) {
        smallClassificationDBMapper.deleteById(id);
    }

    /**
     * @param DB
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SmallClassificationDB update(SmallClassificationDB DB) {
        SmallClassificationDB oldStudent = this.findById(DB.getId());
        if (oldStudent != null) {
            oldStudent.setLargeClassificationId(oldStudent.getLargeClassificationId());
            oldStudent.setSmallClassificationName(oldStudent.getSmallClassificationName());
            oldStudent.setAccumulatedAmount(oldStudent.getAccumulatedAmount());
            oldStudent.setSmallClassificationIcon(oldStudent.getSmallClassificationIcon());
            return smallClassificationDBMapper.save(oldStudent);
        } else {
            return null;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SmallClassificationDB findById(String id) {
        return smallClassificationDBMapper.findById(id).get();
    }

    /**
     * @return
     */
    @Override
    public List<SmallClassificationDB> findAll() {
        return smallClassificationDBMapper.findAll();
    }
}
