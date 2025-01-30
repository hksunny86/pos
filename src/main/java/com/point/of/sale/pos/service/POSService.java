package com.point.of.sale.pos.service;

import com.point.of.sale.pos.dto.Item;
import com.point.of.sale.pos.model.Transaction;
import com.point.of.sale.pos.model.persistence.TransactionPersistence;

import java.util.List;

public class POSService {
    private TransactionPersistence transactionPersistence = new TransactionPersistence();

    public Transaction processSale(List<Item> items, double cashTendered) {
        Transaction transaction = new Transaction(items, cashTendered);
        saveTransaction(transaction);
        return transaction;
    }

    public void saveTransaction(Transaction transaction) {
        transactionPersistence.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionPersistence.getAllTransactions();
    }
}
