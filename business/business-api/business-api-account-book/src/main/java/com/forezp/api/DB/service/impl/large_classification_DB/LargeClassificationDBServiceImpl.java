package com.forezp.api.DB.service.impl.large_classification_DB;

import com.forezp.api.DB.entity.large_classification.LargeClassificationDB;
import com.forezp.api.DB.mapper.LargeClassificationDBMapper;
import com.forezp.api.DB.service.able.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LargeClassificationDBServiceImpl implements PublicService<LargeClassificationDB> {

    @Autowired
    private LargeClassificationDBMapper largeClassificationDBMapper;

    /**
     * @param largeClassificationDB
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LargeClassificationDB add(LargeClassificationDB largeClassificationDB) {
        return largeClassificationDBMapper.save(largeClassificationDB);
    }

    /**
     * @param id
     */
    @Override
    public void delete(String id) {
        largeClassificationDBMapper.deleteById(id);
    }

    /**
     * @param largeClassificationDB
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LargeClassificationDB update(LargeClassificationDB largeClassificationDB) {
        LargeClassificationDB oldStudent = this.findById(largeClassificationDB.getId());
        if (oldStudent != null) {
            oldStudent.setLargeClassificationName(largeClassificationDB.getLargeClassificationName());
            oldStudent.setLargeClassificationType(largeClassificationDB.getLargeClassificationType());
            oldStudent.setUserId(largeClassificationDB.getUserId());
            oldStudent.setAccumulatedAmount(largeClassificationDB.getAccumulatedAmount());
            oldStudent.setLargeClassificationIcon(largeClassificationDB.getLargeClassificationIcon());
            return largeClassificationDBMapper.save(oldStudent);
        } else {
            return null;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public LargeClassificationDB findById(String id) {
        return largeClassificationDBMapper.findById(id).get();
    }

    /**
     * @return
     */
    @Override
    public List<LargeClassificationDB> findAll() {
        return largeClassificationDBMapper.findAll();
    }
}
