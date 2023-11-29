package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class MainController {

  @GetMapping("/home")
  @ResponseBody
  public String home() {
    return "모든 사용자가 접속 가능합니다.";
  }

  @GetMapping("/products")
  public String getProducts(Model model, Principal principal) {
    String name = "";
    if (principal != null) {
      name = principal.getName();
    }
    model.addAttribute("name", name);
    return "products";
  }

  @GetMapping("/orders")
  @ResponseBody
  public String getOrders(Principal principal) {
    String name = principal.getName();
    return "로그인할 사용자만 접속 가능합니다. " + name;
  }
}
