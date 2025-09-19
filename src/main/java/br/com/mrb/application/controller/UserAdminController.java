package br.com.mrb.application.controller;

import br.com.mrb.application.assembler.UserAdminModelAssembler;
import br.com.mrb.application.dto.UserResponse;
import br.com.mrb.application.service.UserAdminService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserAdminController {

    private final UserAdminService userAdminService;
    private final UserAdminModelAssembler assembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UserResponse>>> getAllUsers(
            @PageableDefault(page = 0, size = 15, sort = "name", direction = ASC) Pageable pageable) {
        Page<UserResponse> page = userAdminService.findAll(pageable);
        var pagedModel = assembler.toPagedModel(page, pageable);
        return ResponseEntity.status(OK).body(pagedModel);
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<EntityModel<UserResponse>> changeUserRole(
            @PathVariable @Positive(message = "Id must be greater than 0") Long id,
            @RequestParam String role) {
        var pageable = PageRequest.of(0, 15, ASC);
        var user = userAdminService.changeRole(id, role);
        var model = assembler.toModel(user, pageable);
        return ResponseEntity.status(OK).body(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<Map<String,String>>> deleteUser(
            @PathVariable @Positive(message = "Id must be greater than 0") Long id) {
        var pageable = PageRequest.of(0, 15, ASC);
        var map = userAdminService.deleteById(id);
        var model = assembler.deletedResponse(map, pageable);
        return ResponseEntity.status(OK).body(model);
    }

}
