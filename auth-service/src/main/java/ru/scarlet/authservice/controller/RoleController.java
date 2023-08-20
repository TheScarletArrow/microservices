package ru.scarlet.authservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;
import ru.scarlet.authservice.dto.RoleRequest;
import ru.scarlet.authservice.dto.RoleResponse;
import ru.scarlet.authservice.entity.Role;
import ru.scarlet.authservice.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;


    @GetMapping("/")
@   Operation(description = "Get all roles", summary = "Get all roles")
    public List<RoleResponse> getRoles(){
        log.info("{} getRoles()", MDC.get("Corr"));
        return roleService.getAll();
    }

    @PostMapping("/")
    @Operation(description = "Create new role", summary = "Create new role")
    public Role addRole(@RequestBody RoleRequest roleRequest){
        log.info("{} addRole({})", MDC.get("Corr"), roleRequest);
        return roleService.addRole(roleRequest);
    }
}
