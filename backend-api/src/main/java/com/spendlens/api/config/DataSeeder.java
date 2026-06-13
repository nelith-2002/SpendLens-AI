package com.spendlens.api.config;

import com.spendlens.api.entity.ExpenseCategory;
import com.spendlens.api.repository.ExpenseCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public void run(String... args) {
        seedExpenseCategories();
    }

    private void seedExpenseCategories() {
        List<ExpenseCategory> categories = List.of(
                ExpenseCategory.builder()
                        .name("Groceries")
                        .description("Supermarket, food items, and household essentials")
                        .build(),

                ExpenseCategory.builder()
                        .name("Food & Dining")
                        .description("Restaurants, cafes, takeaways, and dining expenses")
                        .build(),

                ExpenseCategory.builder()
                        .name("Transport")
                        .description("Taxi, ride-hailing, bus, train, fuel, and travel expenses")
                        .build(),

                ExpenseCategory.builder()
                        .name("Bills")
                        .description("Utility bills, mobile bills, internet, and subscriptions")
                        .build(),

                ExpenseCategory.builder()
                        .name("Medical")
                        .description("Pharmacy, healthcare, medicine, and medical expenses")
                        .build(),

                ExpenseCategory.builder()
                        .name("Shopping")
                        .description("Clothing, accessories, online shopping, and general purchases")
                        .build(),

                ExpenseCategory.builder()
                        .name("Education")
                        .description("Books, courses, tuition, learning materials, and education fees")
                        .build(),

                ExpenseCategory.builder()
                        .name("Other")
                        .description("Expenses that do not match other categories")
                        .build()
        );

        for (ExpenseCategory category : categories) {
            if (!expenseCategoryRepository.existsByName(category.getName())) {
                expenseCategoryRepository.save(category);
            }
        }
    }
}