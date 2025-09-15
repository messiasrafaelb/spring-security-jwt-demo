package br.com.mrb.application.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private Long id;
    private String name;
    private String email;
}
