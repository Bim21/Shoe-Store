package com.fpt.niceshoes.controller.admin;

import com.fpt.niceshoes.entity.Account;
import com.fpt.niceshoes.infrastructure.common.PageableObject;
import com.fpt.niceshoes.infrastructure.common.ResponseObject;
import com.fpt.niceshoes.dto.request.AccountRequest;
import com.fpt.niceshoes.dto.response.AccountResponse;
import com.fpt.niceshoes.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private static String ROLE = "Nhân viên";
    @Autowired
    private AccountService accountService;

    @GetMapping
    public PageableObject<AccountResponse> getAll(AccountRequest request) {
        request.setRoleName(ROLE);
        return accountService.getAll(request);
    }

    @GetMapping("/{id}")
    public Account getOne(@PathVariable Long id) {
        return accountService.getOne(id, ROLE);
    }

    @PostMapping
    public ResponseObject create(@ModelAttribute @Valid AccountRequest request) {
        return new ResponseObject(accountService.create(request, ROLE));
    }

    @PutMapping("/{id}")
    public ResponseObject update(@PathVariable Long id,
                                 @ModelAttribute @Valid AccountRequest request) {
        return new ResponseObject(accountService.update(id, request));
    }


}
