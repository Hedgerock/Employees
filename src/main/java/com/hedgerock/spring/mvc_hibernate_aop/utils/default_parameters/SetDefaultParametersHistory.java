package com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters;

import com.hedgerock.spring.mvc_hibernate_aop.service.histrory_service.HistoryService;
import com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.ShowCurrentPlaceAttributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.revisions.CurrentPlaceRevisionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.*;

public class SetDefaultParametersHistory extends SetDefaultParameters {

    public static <T> String initCurrentRevision(
            Model model,
            T currentPlace,
            String entityName,
            String idTitle,
            Pageable pageable,
            RedirectAttributes redirectAttributes,
            String path,
            HistoryService historyService
    ) {
        Long id = invokeMethod(currentPlace, "getId", Long.class);
        String name = invokeMethod(currentPlace, "getName", String.class);

        Page<CurrentPlaceRevisionDTO> revisions = historyService.getCurrentPlaceRevision(id,
                entityName, pageable);

        String status = initPagination(
                pageable, redirectAttributes, revisions, model, path);
        if (!status.equals(OK)) return status;

        model.addAttribute("revisions", revisions.getContent());
        model.addAttribute("title", String.format(
                TEMPLATE_OF_HISTORY_TITLE,
                name
        ));

        model.addAttribute("idTitle", idTitle);

        return "history/history-current-place-view";
    }

    public static void initShowPlaces(
            Model model,
            String placeId,
            Long placeIdValue,
            String entityName
    ) {
        ShowCurrentPlaceAttributes<?> showPlacesAttributes = new ShowCurrentPlaceAttributes<>();
        showPlacesAttributes.setPlaceTitle(placeId);
        showPlacesAttributes.setIdValue(placeIdValue);

        model.addAttribute("showAttributes", showPlacesAttributes);

        model.addAttribute("showRef", String.format(TEMPLATE_OF_CURRENT_PLACE_REF, entityName));
        model.addAttribute("currentId", placeIdValue);
    }

    private SetDefaultParametersHistory() {}

}
