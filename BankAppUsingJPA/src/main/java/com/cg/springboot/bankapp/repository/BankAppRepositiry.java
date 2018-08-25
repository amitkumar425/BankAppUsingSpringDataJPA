package com.cg.springboot.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.springboot.bankapp.pojo.BankAccount;

@Repository
public interface BankAppRepositiry extends JpaRepository<BankAccount, Integer>{

}
