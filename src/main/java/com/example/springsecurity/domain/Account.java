package com.example.springsecurity.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 8, unique = true)
  private String username;
  private String password;
  @Column(length = 10)
  private String roles;

  /*
  * 비밀번호 암호화
  * @param passwordEncoder
  * @return
  */
  public Account hashPassword(PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(this.password);
    return this;
  }

  /*
  * 비밀번호 체크(입력한 암호화값과 암호화되어있는 암호값과 비교)
  * @param boolean
  * @return
  */
  public boolean checkPassword(String password, PasswordEncoder passwordEncoder) {
    return passwordEncoder.matches(password, this.password);
  }
}
