package br.com.mrb.application.assembler;

import br.com.mrb.application.controller.UserAdminController;
import br.com.mrb.application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static br.com.mrb.application.model.Role.RoleName.ROLE_ADMIN;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class UserAdminModelAssembler {

    private final PagedResourcesAssembler<UserResponse> pagedResourcesAssembler;

    private List<Link> baseLinks(UserResponse user, Pageable pageable) {
        var links = new ArrayList<Link>();
        links.add(linkTo(methodOn(UserAdminController.class).getAllUsers(pageable)).withRel("users"));
        links.add(linkTo(methodOn(UserAdminController.class).deleteUser(user.id())).withRel("delete"));
        if (!user.roleSet().contains(ROLE_ADMIN))
            links.add(linkTo(methodOn(UserAdminController.class).changeUserRole(user.id(), ROLE_ADMIN.name())).withRel("make-admin"));
        return links;
    }

    public EntityModel<UserResponse> toModel(UserResponse user, Pageable pageable) {
        return EntityModel.of(user, baseLinks(user, pageable));
    }

    public PagedModel<EntityModel<UserResponse>> toPagedModel(Page<UserResponse> page,Pageable pageable) {
        return pagedResourcesAssembler.toModel(page, user -> toModel(user, pageable));
    }

    public EntityModel<Map<String, String>> deletedResponse(Map<String, String> map, Pageable pageable) {
        return EntityModel.of(map, linkTo(methodOn(UserAdminController.class).getAllUsers(pageable)).withRel("users"));
    }

}
