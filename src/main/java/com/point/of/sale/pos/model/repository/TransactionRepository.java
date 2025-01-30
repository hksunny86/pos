package com.point.of.sale.pos.model.repository;

import com.point.of.sale.pos.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
