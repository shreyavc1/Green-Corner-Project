package com.jsp.the_green_corner.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.repository.query.Param;

@Entity
@Data
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name field cannot be left blank")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name can contain Uppercase and lowercase letters and spaces only")
    private String name;

    @NotBlank(message = "Email field cannot be left blank. Provide your emailId")
    @Email
    private String email;

    @NotBlank(message = "Subject field cannot be left blank. Provide subject")
    private String subject;

    @NotBlank(message = "Message field cannot be left blank, provide message")
    private String message;
}
