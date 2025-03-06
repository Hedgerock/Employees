package com.hedgerock.spring.mvc_hibernate_aop.service.picture_service;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Picture;

public interface PictureService {
    Picture findPicture(Long id);
    Picture savePicture(Picture picture);

}
