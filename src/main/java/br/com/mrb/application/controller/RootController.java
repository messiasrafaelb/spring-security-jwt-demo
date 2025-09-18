package br.com.mrb.application.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.*;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<RepresentationModel<?>> root() {
        var model = new RepresentationModel<>();
        model.add(linkTo(methodOn(AuthController.class).login(null)).withRel("login"));
        model.add(linkTo(methodOn(PostController.class).getAllPosts(null)).withRel("posts"));
        model.add(linkTo(methodOn(UserAdminController.class).getAllUsers(null)).withRel("users"));
        return ResponseEntity.status(OK).body(model);
    }
}
