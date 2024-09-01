package com.userservice.authorization.controller;

import com.userservice.authorization.model.dto.ScopeDTO;
import com.userservice.authorization.model.result.AppResult;
import com.userservice.authorization.service.ScopeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/V1/scope")
public class ScopeController {
    private final ScopeService scopeService;

    public ScopeController(ScopeService scopeService) {
        this.scopeService = scopeService;
    }

    @RequestMapping(
            path = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getAllScopes() {
        List<ScopeDTO> scopes = scopeService.getAllScopes();
        return AppResult.success("", scopes);
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<AppResult> getScopeByID(@PathVariable UUID id) {
        ScopeDTO scope = scopeService.getScopeByID(id);
        return AppResult.success("", scope);
    }

    @RequestMapping(
            path = "/",
            method = RequestMethod.POST
    )
    public ResponseEntity<AppResult> addNewScope(@RequestBody ScopeDTO request) {
        ScopeDTO scope = scopeService.addNewScope(request);
        return AppResult.success("", scope);
    }
}
