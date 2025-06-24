package com.hedgerock.spring.mvc_hibernate_aop.service.picture_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.picture_dao.PictureDAO;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Picture;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureDAO pictureDAO;

    public PictureServiceImpl(PictureDAO pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    @Override
    @Transactional
    public Picture savePicture(Picture picture) {
        return this.pictureDAO.savePicture(picture);
    }

    @Override
    @Transactional
    public Picture findPicture(Long id) {
        return this.pictureDAO.findPicture(id);
    }
}
