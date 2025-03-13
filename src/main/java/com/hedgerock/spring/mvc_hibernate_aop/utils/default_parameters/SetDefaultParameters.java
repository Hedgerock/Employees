package com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.*;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.service.employee_service.EmployeeService;
import com.hedgerock.spring.mvc_hibernate_aop.service.general_info_service.GeneralInfoService;
import com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.Search;
import com.hedgerock.spring.mvc_hibernate_aop.utils.enums.TimeUnits;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdCollector;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdFinder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static com.hedgerock.spring.mvc_hibernate_aop.controller.employees_controllers.SaveCurrentEmployeeController.EMPLOYEE_ENTITY_TITLE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.DEFAULT_PAGE_SIZE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Attributes.HAS_PARAMS_ATTRIBUTE;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.DAOUtil.getCurrentDateValue;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.Redirects.*;
import static com.hedgerock.spring.mvc_hibernate_aop.utils.attributes.MessageAttribute.*;

public class SetDefaultParameters {
    protected SetDefaultParameters() {}

    public static void initModel(
            final Model model,
            final Employee employee,
            final List<Department> departmentsList,
            final List<City> citiesList,
            final List<Nationality> nationalitiesList,
            final String title
    ) {
        Map<String, String> departments = getMap(departmentsList);
        Map<String, String> cities = getMap(citiesList);
        Map<String, String> nationalities = getMap(nationalitiesList);

        setEmployee(model, employee);
        model.addAttribute("pageTitle", title);

        setCurrentSelect(model, "optionCollection", departments);
        setCurrentSelect(model, "citiesCollection", cities);
        setCurrentSelect(model, "nationalitiesCollection", nationalities);
    }

    public static <T> Map<String, String>getMap(List<T>list) {
        return list.isEmpty() ? null : getCurrentList(list);
    }

    public static <T, E> E invokeMethod(T value, String methodName, Class<E> eClass) {
        try {
            Method method = value.getClass().getDeclaredMethod(methodName);
            return eClass.cast(method.invoke(value));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void setEmployee(Model model, Employee employee) {
        if (employee.getDepartment() != null) {
            employee.getSelectionCollector().setDepartmentName(employee.getDepartment().getName());
        }

        if (employee.getCity() != null) {
            employee.getSelectionCollector().setCityName(employee.getCity().getName());
        }

        if (employee.getNationality() != null) {
            employee.getSelectionCollector().setNationalityName(employee.getNationality().getName());
        }

        model.addAttribute("currentEmployee", employee);
    }

    public static void setDepartment(Model model, Department department) {
        model.addAttribute("currentDepartment", department);
    }

    private static <T> Map<String, String> getCurrentList(final List<T>currentList) {
        final Map<String, String> result = new LinkedHashMap<>();

        currentList.forEach(element -> {
            String name = getName(element);
            result.put(name, name);
        });

        return result;
    }

    private static <T> String getName(T element) {
        try {
            Method getter = element.getClass().getDeclaredMethod("getName");
            return (String) getter.invoke(element);
        } catch (Exception e) {
            throw new RuntimeException("Wrong data");
        }
    }

    public static void setCurrentSelect(Model model, String attrName, Map<String, String>currentSelect) {
        model.addAttribute(attrName, currentSelect);
    }

    public static void setSearch(
            String searchTitle, Model model, String search) {
        model.addAttribute(HAS_PARAMS_ATTRIBUTE, search != null);
        Search currentSearch = new Search();

        if (search != null) {
            currentSearch.setSearchParams(search);
        }

        model.addAttribute(searchTitle, currentSearch);
    }

    public static Page<Employee> getEmployees(EmployeeService employeeService, String search, Pageable pageable) {
        Page<Employee> employees;
        if (search != null && !search.isEmpty()) {
            String searchParam = search.toLowerCase();
            employees = employeeService.getSearchedEmployees(searchParam, pageable);
        } else {
            employees = employeeService.getAllEmployees(pageable);
        }

        validateEmployeeDetails(employees);

        return employees;
    }

    public static void validateEmployeeDetails(Page<Employee> employees) {
        employees.forEach(employee -> {
            if (employee.getEmployeeDetails() != null) {
                employee
                        .getEmployeeDetails()
                        .setHasNullFields(SetDefaultParametersShowEmployeeDetails
                                .hasNullFields(employee.getEmployeeDetails()
                                )
                        );
            }
        });
    }

    public static Page<Employee> getEmployees(
            EmployeeService employeeService, String search, Pageable pageable, boolean isLookingForFired) {
        Page<Employee> employees;

        if (!isLookingForFired) {
            return getEmployees(employeeService, search, pageable);
        }

        if (search != null && !search.isEmpty()) {
            String searchParam = search.toLowerCase();
            employees = employeeService.getSearchedEmployees(searchParam, pageable, true);
        } else {
            employees = employeeService.getAllEmployees(pageable,true);
        }

        validateEmployeeDetails(employees);

        return employees;
    }

    public static void saveEmailsAndPhones(
            final List<String> emails, final List<String> phones, Employee employee,
            EmployeeDetails employeeDetails, GeneralInfoService generalInfoService) {

        List<PhoneNumber>curPhoneNumbers = employeeDetails.getPhoneNumbers().stream().toList();
        List<Email>curEmails = employeeDetails.getEmails().stream().toList();

        Set<PhoneNumber> currentPhones = getCurrentCollection(
                phones, curPhoneNumbers, PhoneNumber.class
        );

        Set<Email>currentEmails = getCurrentCollection(
                emails, curEmails, Email.class);

        employee.setEmployeeDetails(employeeDetails);
        employeeDetails.setEmployee(employee);
        employeeDetails.addEmails(currentEmails);
        employeeDetails.addPhoneNumbers(currentPhones);

        generalInfoService.saveCurrentEntity(employeeDetails);
    }

    public static <T> Set<T> getCurrentCollection(
            List<String>values, List<T>entitiesList, Class<T>curClass) {
        return IntStream.range(0, values.size()).mapToObj(i -> {
            final String value = values.get(i);

            try {
                T entity = i < entitiesList.size() && entitiesList.get(i) != null
                        ? entitiesList.get(i)
                        : curClass.getDeclaredConstructor().newInstance();
                Method method = entity.getClass().getDeclaredMethod("setValue", String.class);
                method.invoke(entity, value);

                return entity;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }).collect(Collectors.toCollection(HashSet::new));
    }

    public static <T> void addEmployeesToCurrentPlace(
            List<Long> ids, EmployeeService employeeService, String method, T entity, Class<T>curClass) {
        List<Employee>employees = employeeService.getSelectedEmployees(ids);

        try {
            for (Employee employee: employees) {
                Method curMethod = employee.getClass().getMethod(method, curClass);
                curMethod.invoke(employee, entity);
            }
            employeeService.saveAllEmployees(employees);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }

    public static <T, E> List<E> getCurrentList(
            Long id, T service, String firstMethod, String secondMethod, Class<E>curClass) {
        final List<E> entityClasses = new ArrayList<>();

        try {
            Method method;
            if (id == null) {
                method = service.getClass().getMethod(firstMethod);
                List<?>result = (List<?>) method.invoke(service);
                validateCollection(result, entityClasses, curClass);
            } else {
                method = service.getClass().getMethod(secondMethod, Long.class);
                Optional<?> optionalResult = (Optional<?>) method.invoke(service, id);
                optionalResult.ifPresent(res -> entityClasses.add(curClass.cast(res)));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }

        return entityClasses;
    }

    public static <E> void validateCollection(List<?>result, List<E>collectionConsumer, Class<E>curClass) {
        result.forEach(el -> collectionConsumer.add(curClass.cast(el)));
    }

    public static String getActualRedirect(IdFinder idFinder, Long id) {
        Long depId = idFinder.getDepartmentId();
        Long cityId = idFinder.getCityId();
        Long natId = idFinder.getNationalityId();

        final String redirectTitle = "showCurrentEmployeeDetails";
        final String parameterName = "empId";

        String departmentRedirect = String
                .format(REDIRECT_FOR_SPECIFIC_PAGE_EXTENDED, redirectTitle, parameterName, id, "depId", depId);
        String cityRedirect = String
                .format(REDIRECT_FOR_SPECIFIC_PAGE_EXTENDED, redirectTitle, parameterName, id, "cityId", cityId);
        String nationalityRedirect = String
                .format(REDIRECT_FOR_SPECIFIC_PAGE_EXTENDED, redirectTitle, parameterName, id, "natId", natId);

        String defaultRedirect = String
                .format(REDIRECT_FOR_SPECIFIC_PAGE, redirectTitle, parameterName, id);

        if (depId != null) {
            return departmentRedirect;
        } else if (cityId != null) {
            return cityRedirect;
        } else if (natId != null) {
            return nationalityRedirect;
        } else {
            return defaultRedirect;
        }
    }

    public static void initSuccessFlashAttr(
            RedirectAttributes redirectAttributes,
            String entity,
            String operation
    ) {
        redirectAttributes.addFlashAttribute(
                MESSAGE_ATTR_TITLE,
                String.format(TEMPLATE_OF_SUCCESS_OPERATION, entity, operation)
        );
        redirectAttributes.addFlashAttribute("status", "success");
    }

    public static <T> void initIdFinder(T entity, Long depId, Long cityId, Long natId) {
        try {
            Method method = entity.getClass().getDeclaredMethod("getIdFinder");
            IdFinder idFinder = (IdFinder) method.invoke(entity);

            idFinder.setDepartmentId(depId);
            idFinder.setCityId(cityId);
            idFinder.setNationalityId(natId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void initFailedFlashAttr(
            RedirectAttributes redirectAttributes,
            String entity,
            String operation,
            Exception e
    ) {
        redirectAttributes.addFlashAttribute(
                MESSAGE_ATTR_TITLE,
                String.format(TEMPLATE_OF_FAILED_OPERATION, operation, entity, e.getMessage())
        );
        redirectAttributes.addFlashAttribute("status", "error");
    }

    public static void initNotFoundFlashAttr(
            RedirectAttributes redirectAttributes,
            String entity
    ) {
        redirectAttributes.addFlashAttribute(
                MESSAGE_ATTR_TITLE,
                String.format(TEMPLATE_OF_NOT_FOUND, entity)
        );
        redirectAttributes.addFlashAttribute("status", "error");
    }

    public static <T> void initSuccessFlashAttrMethod(
            String title, T entity, RedirectAttributes redirectAttributes, String operation) {
        try {
            String getter = invokeMethod(entity, "getName", String.class);
            String entityName = title + " " + getter;

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, entityName, operation);
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entity.getClass().getName(), operation, e);
        }
    }

    public static <T> void initSuccessFlashAttrMethod(
            String title, T entity, RedirectAttributes redirectAttributes, String operation, String methodName) {
        try {
            String getter = invokeMethod(entity, methodName, String.class);
            String entityName = title + " " + getter;

            SetDefaultParameters.initSuccessFlashAttr(redirectAttributes, entityName, operation);
        } catch (Exception e) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entity.getClass().getName(), operation, e);
        }
    }


    public static <T> void initFailedFlashAttrMethod(
            String title, T entity, RedirectAttributes redirectAttributes, String operation, Exception e
    ) {
        try {
            String getter = invokeMethod(entity, "getName", String.class);
            String entityName = title + " " + getter;
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entityName, operation, e);
        } catch (Exception exception) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entity.getClass().getName(), operation, exception);
        }
    }

    public static <T> void initFailedFlashAttrMethod(
            String title, T entity, RedirectAttributes redirectAttributes, String operation, Exception e, String methodName
    ) {
        try {
            String getter = invokeMethod(entity, methodName, String.class);
            String entityName = title + " " + getter;
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entityName, operation, e);
        } catch (Exception exception) {
            SetDefaultParameters.initFailedFlashAttr(redirectAttributes, entity.getClass().getName(), operation, exception);
        }
    }

    public static <T, V> String initOperation(
            String entityTitle,
            EmployeeService employeeService,
            T entity,
            V service,
            IdCollector idCollector,
            RedirectAttributes redirectAttributes,
            String redirect,
            String redirectTemplate,
            String methodName,
            String setter,
            Class<T> curClass,
            boolean isNew
    ) {
        String operation = isNew ? "created" : "updated";

        try {
            T currentPlace = updateCurrentPlace(
                    employeeService,
                    entity,
                    idCollector,
                    service,
                    methodName,
                    setter,
                    curClass
            );

            initSuccessFlashAttrMethod(entityTitle, currentPlace, redirectAttributes, operation);
            Long id = invokeMethod(currentPlace, "getId", Long.class);
            return getRedirect(id, redirectTemplate);
        } catch (Exception e) {
            String failedOperation = operation.substring(0, operation.length() - 1);;
            initFailedFlashAttrMethod(entityTitle, entity, redirectAttributes, failedOperation, e);
            return redirect;
        }

    }

    public static String getRedirect(Long id, String template) {
        return template + id;
    }

    public static <T, V> T updateCurrentPlace(
            EmployeeService employeeService,
            T entity,
            IdCollector idCollector,
            V service,
            String methodName,
            String setter,
            Class<T>curClass
    ) {
        try {
            Method method = service.getClass().getDeclaredMethod(methodName, entity.getClass());
            T newEntity = curClass.cast(method.invoke(service, entity));
            List<Long> ids = idCollector.getIdCollection();

            if (ids != null && !ids.isEmpty()) {
                addEmployeesToCurrentPlace(
                        ids, employeeService, setter, newEntity, curClass
                );
            }

            return newEntity;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> T getCurrentValue(Long id, String entityName, Class<T>tClass, GeneralInfoService generalInfoService) {
        Optional<T> optionalT = generalInfoService.findCurrentEntity(id, entityName, tClass);

        return optionalT.orElse(null);
    }

    public static <T> T getCurrentValue(
            Long id, String entityName, Class<T>tClass, GeneralInfoService generalInfoService, boolean isLookingForFired) {
        if (!isLookingForFired) {
            return getCurrentValue(id, entityName, tClass, generalInfoService);
        }

        Optional<T> optionalT = generalInfoService.findCurrentEntity(id, entityName, tClass, true);

        return optionalT.orElse(null);
    }

    public static String initChanges(
            Employee employee,
            RedirectAttributes redirectAttributes,
            String redirect,
            String operation,
            GeneralInfoService generalInfoService,
            EmployeeService employeeService) {
        try {
            String departmentName = employee.getSelectionCollector().getDepartmentName();
            String cityName = employee.getSelectionCollector().getCityName();
            String nationalityName = employee.getSelectionCollector().getNationalityName();

            Optional<Department> department = generalInfoService
                    .findCurrentEntity(departmentName, "Department", Department.class);

            Optional<City> city = generalInfoService
                    .findCurrentEntity(cityName, "City", City.class);

            Optional<Nationality> nationality = generalInfoService
                    .findCurrentEntity(nationalityName, "Nationality", Nationality.class);

            department.ifPresent(employee::setDepartment);
            city.ifPresent(employee::setCity);
            nationality.ifPresent(employee::setNationality);

            Employee currentEmployee = employeeService.saveCurrentEmployee(
                    employee, employee.getDepId(), employee.getCityId(), employee.getNationalityId());

            Long id = currentEmployee.getId();
            IdFinder idFinder = employee.getIdFinder();

            initSuccessFlashAttrMethod(EMPLOYEE_ENTITY_TITLE, currentEmployee, redirectAttributes, operation, "getFirstName");
            return getActualRedirect(idFinder, id);
        } catch (Exception e) {
            initFailedFlashAttrMethod(EMPLOYEE_ENTITY_TITLE, employee, redirectAttributes, operation, e, "getFirstName");
            return redirect;
        }

    }

    public static <T> String getActualTimeValue(Long time, T dto) {
        Instant now = Instant.now();
        Instant previousChangesInstant = Instant.ofEpochMilli(time);
        Duration duration = Duration.between(previousChangesInstant, now);

        String rawPreviousChanges = getCurrentDateValue(duration);
        String previousChanges = Arrays
                .stream(rawPreviousChanges.split(":"))
                .dropWhile(el -> el.equals("00"))
                .collect(Collectors.joining(":"));

        String[] arr = previousChanges.split(":");
        int length = arr.length;

        if (dto != null) {
            try {
                Class<?> tClass = dto.getClass();

                Method prevMethod = tClass.getDeclaredMethod("setPreviousChanges", String.class);
                Method timeStampDiffMethod = tClass.getDeclaredMethod("setTimeStampDiff", Duration.class);

                prevMethod.invoke(dto, getFullTime(previousChanges));
                timeStampDiffMethod.invoke(dto, duration);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }


        int index = length - 1;
        return getSingleTime(arr[0], index);
    }

    private static String getFullTime(String value) {
        String[] arr = value.split(":");
        return IntStream
                .range(0, arr.length)
                .mapToObj(i -> {
                    int index = arr.length - 1 - i;
                    return getSingleTime(arr[i], index);
                })
                .collect(Collectors.joining(" "));
    }

    private static String getSingleTime(String value, int index) {
        String rawUnitValue = TimeUnits.values()[index].toString();
        String unitValue = value.equals("01") ? rawUnitValue.substring(0, rawUnitValue.length() - 1) : rawUnitValue;

        return value + " " + unitValue;
    }

    public static <T> String initPagination(
            Pageable pageable, RedirectAttributes redirectAttributes, Page<T> pageList, Model model, String path) {
        final int page = pageable.getPageNumber();
        final int size = pageable.getPageSize();

        if (size != Integer.parseInt(DEFAULT_PAGE_SIZE)) {
            redirectAttributes.addFlashAttribute("message",
                    "This param is not changeable");

            String end = page > 0 ? "?page=" + page : "";
            return "redirect:" + path + end;
        }

        int totalPages = pageList.getTotalPages();

        if (page > totalPages) {
            redirectAttributes.addFlashAttribute("message",
                    "There is no so many pages");

            return path;
        }

        model.addAttribute("paginationCurrentPage", page);
        model.addAttribute("total", totalPages);
        model.addAttribute("path", path);
        model.addAttribute("pageSize", size);

        return Attributes.OK;
    }

    public static String initBindingErrors(
            BindingResult bindingResult, RedirectAttributes redirectAttributes, String redirect) {
        String errors = bindingResult
                .getAllErrors()
                .stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("<br>"));
        redirectAttributes.addFlashAttribute("message", errors);
        redirectAttributes.addFlashAttribute("status", "error");
        return redirect;
    }

    public static void initSlider(EmployeeDetails employeeDetails, Model model) {
        List<String> slider = new ArrayList<>();
        Picture picture = employeeDetails.getPicture();

        if (picture != null) {
            slider = picture.getPictures().stream()
                    .sorted((a, b) -> b.getId().compareTo(a.getId()))
                    .map(PrevPicture::getPictureSrc)
                    .collect(Collectors.toList());

            if (picture.getPictureSrc() != null) {
                slider.add(0, picture.getPictureSrc());
            }
        }

        if (!slider.isEmpty()) {
            model.addAttribute("slider", slider);
        }
    }


    public static Integer getCurrentNum(Integer page, Integer pageCapacity) {
        return (pageCapacity - 1) == 0 ? page - 1 : page;
    }

    public static String getCurrentRedirect(
            String pageName, Long depId, Long cityId, Long natId, Integer pageNumber) {
        if (pageName == null) pageName = "";

        final boolean isMoreThanZero = pageNumber > 0;

        String pageValue = isMoreThanZero ? String.format("page=%d", pageNumber) : "";
        String questionMark = isMoreThanZero ? "?" : "";
        String ampersand = isMoreThanZero ? "&" : "";

        return switch (pageName) {
            case "showCurrentDepartment" ->
                    String.format(REDIRECT_FOR_SPECIFIC_PAGE, "showCurrentDepartment", "depId", depId)
                            + ampersand + pageValue;
            case "showCurrentCity" ->
                    String.format(REDIRECT_FOR_SPECIFIC_PAGE, "showCurrentCity", "cityId", cityId)
                            + ampersand + pageValue;
            case "showCurrentNationality" ->
                    String.format(REDIRECT_FOR_SPECIFIC_PAGE, "showCurrentNationality", "natId", natId)
                            + ampersand + pageValue;
            default -> REDIRECT_TO_THE_MAIN_PAGE + questionMark + pageValue;
        };
    }

    public static String capitalizeIt(String value) {
        return value.substring(0, 1).toUpperCase()
                + value.substring(1).toLowerCase();
    }

    public static Pageable initPageable(Model model) {
        Pageable pageable = (Pageable) model.getAttribute("pageable");
        return pageable == null ? PageRequest.of(0, Integer.parseInt(DEFAULT_PAGE_SIZE)) : pageable;
    }

    public static String initSearch(Model model) {
        String search = (String) model.getAttribute("searchParams");

        return search == null ? "" : search;
    }

}
