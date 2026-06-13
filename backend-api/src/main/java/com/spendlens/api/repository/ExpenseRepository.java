package com.spendlens.api.repository;

import com.spendlens.api.entity.AppUser;
import com.spendlens.api.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserOrderByExpenseDateDesc(AppUser user);

    List<Expense> findByUserAndExpenseDateBetweenOrderByExpenseDateDesc(
            AppUser user,
            LocalDate startDate,
            LocalDate endDate
    );
}