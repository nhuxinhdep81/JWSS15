package com.tien.dto;

import com.tien.validation.ValidEmail;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResumeDTO {
    private int id;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String fullName;

    @NotBlank(message ="Email không được để trống")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    ,message = "Email không đúng định dạng")
    @ValidEmail
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại phải có 10 số")
    private String phoneNumber;

    private String education;

    private String experience;

    private String skills;
}
