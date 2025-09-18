package br.com.mrb.application.controller;

import br.com.mrb.application.assembler.RootModel;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RootController {

    private final RootModel assembler;

    @GetMapping("/")
    public RepresentationModel<?> root() {
        return assembler.toModel();
    }
}
