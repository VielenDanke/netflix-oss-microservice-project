package kz.danke.account.management.ms.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-management")
public class AccountManagementController {

    @GetMapping("/status/check")
    public String getStatus() {
        return "Working";
    }
}
