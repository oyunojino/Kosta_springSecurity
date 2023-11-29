package com.example.springsecurity.service;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
  private final AccountRepository accountRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByUsername(username);
    if(account == null) {
      throw new UsernameNotFoundException(username);
    }

    return User.builder()
        .username(account.getUsername())
        .password(account.getPassword())
        .roles(account.getRoles())
        .build();
  }

  public Account addAccount(Account account) {
    validateDuplicateAccount(account);
    Account account1 = account.hashPassword(passwordEncoder);
    return accountRepository.save(account1);
  }

  private void validateDuplicateAccount(Account account) {

  }

  public boolean checkAccount(Account account) {
    if(account.checkPassword(account.getPassword(), passwordEncoder)) {
      return true;
    }
    return false;
  }
}
