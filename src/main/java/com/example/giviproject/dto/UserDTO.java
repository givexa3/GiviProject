package com.example.giviproject.dto;

import lombok.Builder;
import lombok.Data;


//check exactly what @Data does do we only need getters and setters?
@Data
@Builder
public class UserDTO {
    private int age;
    private String name;
}
