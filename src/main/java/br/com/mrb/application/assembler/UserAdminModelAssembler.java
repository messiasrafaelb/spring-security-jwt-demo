package br.com.mrb.application.assembler;

import br.com.mrb.application.controller.UserAdminController;
import br.com.mrb.application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.Map;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class UserAdminModelAssembler {

    private final PagedResourcesAssembler<UserResponse> pagedResourcesAssembler;

    public EntityModel<UserResponse> addPatchLink(UserResponse user) {
        var page = PageRequest.of(0, 15, Sort.by("name").ascending());
        return EntityModel.of(user,
                linkTo(methodOn(UserAdminController.class).getAllUsers(page)).withRel("users"),
                linkTo(methodOn(UserAdminController.class).deleteUser(user.id())).withRel("delete"));
    }

    public EntityModel<Map<String, String>> addDeleteLink(Map<String, String> map) {
        var page = PageRequest.of(0, 15, Sort.by("name").ascending());
        return EntityModel.of(map, linkTo(methodOn(UserAdminController.class).getAllUsers(page)).withRel("users"));
    }

    public PagedModel<EntityModel<UserResponse>> addGetAllLink(Page<UserResponse> page) {
        return pagedResourcesAssembler.toModel(page, user -> EntityModel.of(user,
                linkTo(methodOn(UserAdminController.class).changeUserRole(user.id(), "ADMIN")).withRel("make-admin"),
                linkTo(methodOn(UserAdminController.class).changeUserRole(user.id(), "USER")).withRel("make-user"),
                linkTo(methodOn(UserAdminController.class).deleteUser(user.id())).withRel("delete")));
    }

}
