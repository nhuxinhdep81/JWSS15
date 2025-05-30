package com.tien.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "ten khong duoc de trong")
    private String name;

    @NotBlank(message = "email khong duoc de trong")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "email khong dung dinh dang")
    private String email;

    @NotBlank(message = "mat khau khong duoc de trong")
    @Size(min = 6, message = "mat khau phai co it nhat 6 ky tu")
    private String password;
}
