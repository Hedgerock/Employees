package com.hedgerock.spring.mvc_hibernate_aop.service.histrory_service;

import com.hedgerock.spring.mvc_hibernate_aop.dao.history_dao.HistoryDAO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions.CurrentPlaceRevisionDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions.EmployeeRevisionDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element.CurrentPlaceDTO;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.single_element.EmployeeDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryDAO historyDAO;

    public HistoryServiceImpl(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    @Override
    @Transactional
    public Page<EmployeeRevisionDTO> getEmployeeRevisions(Long id, Pageable pageable, boolean isLookingForFired) {
        return this.historyDAO.getEmployeeRevisions(id, pageable, isLookingForFired);
    }

    @Override
    @Transactional
    public Page<CurrentPlaceRevisionDTO> getCurrentPlaceRevision(Long currentPlaceId, String entityName, Pageable pageable) {
        return this.historyDAO.getCurrentPlaceRevision(currentPlaceId, entityName, pageable);
    }

    @Override
    @Transactional
    public Page<EmployeeDTO> getEmployeesWithHistory(Pageable pageable) {
        return this.historyDAO.getEmployeesWithHistory(pageable);
    }

    @Override
    @Transactional
    public Page<CurrentPlaceDTO> getCurrentPlaceHistory(String entityName, Pageable pageable) {
        return this.historyDAO.getCurrentPlaceHistory(entityName, pageable);
    }

}
