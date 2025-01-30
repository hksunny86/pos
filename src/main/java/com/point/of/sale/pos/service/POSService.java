package com.point.of.sale.pos.service;

import com.point.of.sale.pos.dto.Item;
import com.point.of.sale.pos.model.Transaction;
import com.point.of.sale.pos.model.persistence.TransactionPersistence;

import java.util.ArrayList;
import java.util.List;

public class POSService {
    private List<Transaction> transactions = new ArrayList<>();
    private TransactionPersistence transactionPersistence = new TransactionPersistence();

    public Transaction processSale(List<Item> items, double cashTendered) {
        Transaction transaction = new Transaction(items, cashTendered);
        saveTransaction(transaction);
        return transaction;
    }

    public void saveTransaction(Transaction transaction) {
        transactionPersistence.save(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public TransactionPersistence getTransactionPersistence() {
        return transactionPersistence;
    }

    public void setTransactionPersistence(TransactionPersistence transactionPersistence) {
        this.transactionPersistence = transactionPersistence;
    }
}
