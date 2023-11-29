package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

  @GetMapping("/home")
  public String home() {
    return "모든 사용자가 접속 가능합니다.";
  }

  @GetMapping("/products")
  public String getProducts(Principal principal) {
    String name = principal.getName();
    return "관리자만 접속 가능합니다." + name;
  }

  @GetMapping("/orders")
  public String getOrders(Principal principal) {
    String name = principal.getName();
    return "로그인할 사용자만 접속 가능합니다." + name;
  }
}
