package com.hediyesilaozyurt.controller.controller.impl;

import com.hediyesilaozyurt.controller.base.RestBaseController;
import com.hediyesilaozyurt.controller.controller.IAccountController;
import com.hediyesilaozyurt.dto.dto.account.AccountRequestDto;
import com.hediyesilaozyurt.dto.dto.account.AccountResponseDto;
import com.hediyesilaozyurt.dto.dto.account.AccountUpdateDto;
import com.hediyesilaozyurt.dto.utils.PageResponse;
import com.hediyesilaozyurt.entities.authEntities.User;
import com.hediyesilaozyurt.entities.base.RootEntity;
import com.hediyesilaozyurt.services.services.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/account")
public class AccountControllerImpl extends RestBaseController implements IAccountController {

    @Autowired
    private IAccountService accountService;

    @Override
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<AccountResponseDto>> save(@RequestBody @Valid AccountRequestDto dto) {
        return respond(HttpStatus.CREATED,accountService.save(dto));
    }

    @Override
    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<AccountResponseDto>> findById(@PathVariable Long id) {
        return respond(HttpStatus.OK,accountService.findById(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<Void>> delete(@PathVariable Long id) {
        accountService.delete(id);
        return respond(HttpStatus.NO_CONTENT,null);
    }

    @Override
    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<AccountResponseDto>> update(@PathVariable Long id,@RequestBody @Valid AccountUpdateDto dto) {
        return respond(HttpStatus.OK,accountService.update(id,dto));
    }

    @Override
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RootEntity<PageResponse<AccountResponseDto>>> findAll(Pageable pageable) {
        return respond(HttpStatus.OK,accountService.findAll(pageable));
    }



    @Override
    @GetMapping("/my-accounts")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<RootEntity<List<AccountResponseDto>>> getMyAccounts(@AuthenticationPrincipal User user) {
        return respond(HttpStatus.OK,accountService.getMyAccounts(user));
    }

    @Override
    @PostMapping("/my-accounts/add")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<RootEntity<AccountResponseDto>> addMyAccount(@AuthenticationPrincipal User user,@RequestBody @Valid AccountRequestDto dto) {
        return respond(HttpStatus.CREATED,accountService.addMyAccount(user,dto));
    }

    @Override
    @PatchMapping("/my-accounts/update/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<RootEntity<AccountResponseDto>> updateMyAccount(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestBody @Valid AccountUpdateDto dto) {
        return respond(HttpStatus.OK,accountService.updateMyAccount(user,id,dto));
    }

    @Override
    @DeleteMapping("/my-accounts/delete/{id}")
    public ResponseEntity<RootEntity<Void>> deleteMyAccount(@AuthenticationPrincipal User user,@PathVariable Long id) {
        accountService.deleteMyAccount(user,id);
        return respond(HttpStatus.NO_CONTENT,null);
    }
}
