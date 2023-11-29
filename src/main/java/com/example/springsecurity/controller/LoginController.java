package com.example.springsecurity.controller;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
  private final AccountService accountService;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute Account account) {
    UserDetails userDetails = accountService.loadUserByUsername(account.getUsername());
    if(accountService.checkAccount(account)) {
      return "redirect:/home";
    }
    return "login";
  }

  @GetMapping("/logout")
  public String logout() {
    return "logout";
  }

  @GetMapping("/account/new")
  public String newAccount() {
    return "accountForm";
  }

  @PostMapping("/account/new")
  public String newAccount(@ModelAttribute Account account) {
    accountService.addAccount(account);
    return "redirect:/login";
  }
}
