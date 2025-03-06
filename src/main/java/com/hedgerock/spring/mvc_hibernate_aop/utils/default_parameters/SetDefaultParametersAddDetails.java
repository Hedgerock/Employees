package com.hedgerock.spring.mvc_hibernate_aop.utils.default_parameters;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.Picture;
import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.PrevPicture;
import jakarta.servlet.ServletContext;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.hedgerock.spring.mvc_hibernate_aop.utils.Views.RELATIVE_PATH;

public class SetDefaultParametersAddDetails extends SetDefaultParameters {

    public static void addAttributes(Model model, EmployeeDetails employeeDetails, Employee employee) {
        model.addAttribute("currentEmployeeDetails",employeeDetails);
        model.addAttribute("title", String.format(
                "Adding details for employee %s", employee.getFirstName() + " " + employee.getLastName()
        ));
    }

    public static void saveFile(MultipartFile file, String fileName, ServletContext servletContext) throws IOException {
        String absolutePath = servletContext.getRealPath(RELATIVE_PATH);
        Path path = Paths.get(absolutePath + fileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    }

    public static Picture initPicture(EmployeeDetails employeeDetails) {
        Picture picture;

        if (employeeDetails.getPicture() != null) {
            picture = employeeDetails.getPicture();
            PrevPicture prevPicture = new PrevPicture();
            prevPicture.setPictureSrc(picture.getPictureSrc());
            prevPicture.setPicture(picture);
            picture.addPicture(prevPicture);
        } else {
            picture = new Picture();
        }

        return picture;
    }
}
