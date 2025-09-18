package br.com.mrb.application.assembler;

import br.com.mrb.application.controller.AuthController;
import br.com.mrb.application.controller.PostController;
import br.com.mrb.application.controller.UserAdminController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RootModel {
    public RepresentationModel<?> toModel() {
        RepresentationModel<?> model = new RepresentationModel<>();
        model.add(linkTo(methodOn(AuthController.class).login(null)).withRel("login"));
        model.add(linkTo(methodOn(PostController.class).getAllPosts(null)).withRel("posts"));
        model.add(linkTo(methodOn(UserAdminController.class).getAllUsers(null)).withRel("users"));
        return model;
    }
}
