package com.hedgerock.spring.mvc_hibernate_aop.service.histrory_service;

import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions.CurrentPlaceRevisionDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element.CurrentPlaceDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element.EmployeeDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions.EmployeeRevisionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryService {
    Page<EmployeeRevisionDTO> getEmployeeRevisions(Long id, Pageable pageable, boolean isLookingForFired);
    Page<CurrentPlaceRevisionDTO> getCurrentPlaceRevision(Long currentPlaceId, String entityName, Pageable pageable);
    Page<EmployeeDTO>getEmployeesWithHistory(Pageable pageable);
    Page<CurrentPlaceDTO>getCurrentPlaceHistory(String entityName, Pageable pageable);
}
