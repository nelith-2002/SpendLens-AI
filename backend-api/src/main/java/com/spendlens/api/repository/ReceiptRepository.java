package com.spendlens.api.repository;

import com.spendlens.api.entity.AppUser;
import com.spendlens.api.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByUserOrderByCreatedAtDesc(AppUser user);
}