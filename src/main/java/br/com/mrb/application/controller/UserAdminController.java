package br.com.mrb.application.controller;

import br.com.mrb.application.dto.UserResponse;
import br.com.mrb.application.service.UserAdminService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import static org.springframework.data.domain.Sort.Direction.ASC;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserAdminController {

    private final UserAdminService userAdminService;

    @GetMapping
    public Page<UserResponse> getAllUsers(
            @PageableDefault(page = 0, size = 15, sort = "name", direction = ASC) Pageable pageable) {
        return userAdminService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteUser(
            @PathVariable @Positive(message = "Id must be greater than 0") Long id) {
        return ResponseEntity.ok(userAdminService.deleteById(id));
    }

    @PatchMapping("/{id}/role")
    public UserResponse changeUserRole(
            @PathVariable @Positive(message = "Id must be greater than 0") Long id,
            @RequestParam String role) {
        return userAdminService.changeRole(id, role);
    }

}
