package br.com.mrb.application.controller;

import br.com.mrb.application.assembler.RootModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class RootController {

    private final RootModelAssembler assembler;

    @GetMapping("/")
    public ResponseEntity<RepresentationModel<?>> root() {
        return ResponseEntity.status(OK).body(assembler.toModel());
    }
}
