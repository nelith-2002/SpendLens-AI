package com.spendlens.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The receipt belongs to one user
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    // One receipt can create one expense
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id", unique = true)
    private Expense expense;

    // We store OCR text, not the original image/PDF
    @Column(name = "ocr_text", columnDefinition = "TEXT")
    private String ocrText;

    @Column(name = "extracted_shop_name", length = 150)
    private String extractedShopName;

    @Column(name = "extracted_date")
    private LocalDate extractedDate;

    @Column(name = "extracted_total", precision = 10, scale = 2)
    private BigDecimal extractedTotal;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}