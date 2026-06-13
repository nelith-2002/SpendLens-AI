package com.spendlens.api.repository;

import com.spendlens.api.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

    Optional<ExpenseCategory> findByName(String name);

    boolean existsByName(String name);
}