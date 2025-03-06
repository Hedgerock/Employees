package com.hedgerock.spring.mvc_hibernate_aop.dao.picture_dao;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Picture;

public interface PictureDAO {

    Picture findPicture(Long id);
    Picture savePicture(Picture picture);

}
