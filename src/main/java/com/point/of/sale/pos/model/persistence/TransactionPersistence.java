package com.point.of.sale.pos.model.persistence;

import com.point.of.sale.pos.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionPersistence {
    private List<Transaction> transactionRecords = new ArrayList<>();

    public void save(Transaction transaction) {
        transactionRecords.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRecords;
    }
}
