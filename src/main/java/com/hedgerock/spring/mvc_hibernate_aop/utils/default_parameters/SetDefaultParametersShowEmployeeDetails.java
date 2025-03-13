package com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.enums.Months;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.TimeCarrier;
import jakarta.servlet.ServletContext;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects.REDIRECT_TO_NOT_FOUND_PAGE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.RELATIVE_PATH;

public class SetDefaultParametersShowEmployeeDetails extends SetDefaultParameters {
    private static final String TEMPLATE_OF_EDITED_DATE_FORMAT = "%s %s %s";

    public static Long getDifference(ChronoUnit units, LocalDate comparingDate) {
        return units.between(comparingDate, LocalDate.now());
    }

    public static<T> List<T> getSiblings(List<T> employees, Long id, Class<T>curClass) {
        List<T> siblings = employees
                .stream()
                .filter(el -> !invokeMethod(el, "getId", Long.class).equals(id))
                .collect(Collectors.toList());

        int size = siblings.size();
        Long currentId = getCurrentId(siblings, size);

        updateSiblings(size, siblings, currentId, id, curClass);

        return siblings;
    }

    public static <T> void updateSiblings(int size, List<T>siblings, Long currentId, Long id, Class<T> curClass) {
        try {
            T emptyEntity = curClass.getDeclaredConstructor().newInstance();
            if (size == 0) {
                siblings.add(emptyEntity);
                siblings.add(emptyEntity);
            } else if (size == 1 && currentId > id) {
                siblings.add(0, emptyEntity);
            } else if (size == 1 && currentId < id) {
                siblings.add(emptyEntity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static<T> Long getCurrentId(List<T>siblings, int size) {
        if (size == 0) return ((Number) 0).longValue();
        T value = siblings.get(0);

        return invokeMethod(value, "getId", Long.class);
    }

    public static String getModifiedDate(final LocalDate localDate) {
        if (localDate == null) {
            return "";
        }

        String[] arr = localDate.toString().split("-");
        String date = arr[2];
        String rowMonth = Months.values()[Integer.parseInt(arr[1]) - 1].toString();
        String month = rowMonth.charAt(0) + rowMonth.substring(1).toLowerCase() ;
        String year = arr[0];

        return String.format(TEMPLATE_OF_EDITED_DATE_FORMAT, date, month, year);
    }

    public static void addAttributes(
            Model model,
            Employee currentEmployee,
            Long depId,
            Long cityId,
            Long natId,
            ServletContext servletContext
    ) {
        LocalDate hireDateValue = currentEmployee.getHireDate();
        TimeCarrier timeCarrier = initTimeCarrier(hireDateValue, "setHireDate");

        String rawEmployeeDescription = currentEmployee.getEmployeeDetails().getEmployeeDescription().getValue();

        if (rawEmployeeDescription != null) {
            String formattedEmpDesc = rawEmployeeDescription
                    .replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;");
            model.addAttribute("employeeDescriptionAttribute", formattedEmpDesc);
        }

        model.addAttribute("page", "showCurrentEmployeeDetails");
        model.addAttribute("depId", depId);
        model.addAttribute("cityId", cityId);
        model.addAttribute("natId", natId);
        model.addAttribute("timeCarrier", timeCarrier);

        EmployeeDetails employeeDetails = currentEmployee.getEmployeeDetails();

        setInfoForBirthday(model, employeeDetails);
        checkPicture(employeeDetails, servletContext, model);
    }

    public static void checkPicture(EmployeeDetails employeeDetails, ServletContext servletContext, Model model) {
        if (employeeDetails != null && employeeDetails.getPicture() != null) {
            String src = employeeDetails.getPicture().getPictureSrc();
            Boolean imageExists = checkImageExists(src, servletContext);
            model.addAttribute("imageExists", imageExists);
        }
    }

    public static boolean checkImageExists(String src, ServletContext servletContext) {
        String relativePath = RELATIVE_PATH + src;
        String absolutePath = servletContext.getRealPath(relativePath);
        Path path = Paths.get(absolutePath);

        return Files.exists(path);
    }

    private static void setInfoForBirthday(Model model, EmployeeDetails employeeDetails) {

        if (employeeDetails != null && employeeDetails.getDateOfBirth() != null) {
            LocalDate birthday = employeeDetails.getDateOfBirth();
            String updatedDateOfBirth = getModifiedDate(birthday);
            Long yearsOld = getDifference(ChronoUnit.YEARS, birthday);

            model.addAttribute("birthday", updatedDateOfBirth);
            model.addAttribute("yearsOld", yearsOld);
        }
    }

    public static <T> Optional<T> getEmployeeById(List<T> entities, Long id, Class<T> tClass) {
        return entities
                .stream()
                .filter(el -> invokeMethod(el, "getId", Long.class).equals(id))
                .findFirst()
                .map(Optional::of)
                .orElseGet(() -> {
                    try {
                        return Optional.of(tClass.getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
        );
    }

    public static <T> T initSiblings(List<T>entities, Long id, Class<T>tClass, Model model, String entityName) {

        if (entities.isEmpty()) {
            return null;
        }

        Optional<T> optionalEntity = getEmployeeById(entities, id, tClass);

        if (optionalEntity.isEmpty()) {
            return null;
        }

        List<T> siblings = getSiblings(entities, id, tClass);
        T entity = optionalEntity.get();

        if (invokeMethod(entity, "getId", Long.class) == null) {
            return null;
        }

        System.out.println(siblings);

        model.addAttribute("siblings", siblings);
        model.addAttribute(entityName, entity);

        return entity;
    }

    public static boolean hasNullFields(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field: fields) {
                field.setAccessible(true);
                Object value = field.get(object);

                if (value == null) {
                    return true;
                } else if (value instanceof Collection) {
                    return ((Collection<?>) value).isEmpty();
                }

            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }

        return false;
    }

    public static String validateEntities(
            Employee employee, RedirectAttributes redirectAttributes, Long depId, Long cityId, Long natId) {
        Department department = employee.getDepartment();
        City city = employee.getCity();
        Nationality nationality = employee.getNationality();

        if (cityId != null && city != null && !Objects.equals(city.getId(), cityId)) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "City");
            return REDIRECT_TO_NOT_FOUND_PAGE;
        }

        if (depId != null && department != null && !Objects.equals(department.getId(), depId)) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Department");
            return REDIRECT_TO_NOT_FOUND_PAGE;
        }

        if (natId != null && nationality != null && !Objects.equals(nationality.getId(), natId)) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Nationality");
            return REDIRECT_TO_NOT_FOUND_PAGE;
        }

        return Attributes.OK;
    }

    public static String validateDetails(
            Employee employee, RedirectAttributes redirectAttributes, Long empDetId, Long depId, Long cityId, Long natId
    ) {
        if (employee == null) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee");
            return REDIRECT_TO_NOT_FOUND_PAGE;
        }

        if (!Objects.equals(employee.getEmployeeDetails().getId(), empDetId)) {
            SetDefaultParameters.initNotFoundFlashAttr(redirectAttributes, "Employee details");
            return REDIRECT_TO_NOT_FOUND_PAGE;
        }

        String result = validateEntities(employee, redirectAttributes, depId, cityId, natId);

        return result.equals(Attributes.OK) ? Attributes.OK : result;
    }

    public static TimeCarrier initTimeCarrier(LocalDate localDate, String setterName) {
        TimeCarrier timeCarrier = new TimeCarrier();
        String dayDifference = getActualTimeValue(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                timeCarrier);
        Long difference = getDifference(ChronoUnit.DAYS, localDate);
        String result = getModifiedDate(localDate);

        timeCarrier.setDifference(difference);
        timeCarrier.setDateDifference(dayDifference);

        try {
            Method method = timeCarrier.getClass().getDeclaredMethod(setterName, String.class);
            method.invoke(timeCarrier, result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return timeCarrier;
    }
}
