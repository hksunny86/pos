package com.point.of.sale.pos.service;

import com.point.of.sale.pos.model.Transaction;


import com.point.of.sale.pos.model.TransactionItem;
import com.point.of.sale.pos.model.repository.ItemRepository;
import com.point.of.sale.pos.model.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class POSService {

    private final ItemRepository itemRepository;
    private final TransactionRepository transactionRepository;

    public POSService(ItemRepository itemRepository, TransactionRepository transactionRepository) {
        this.itemRepository = itemRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction processSale(List<TransactionItem> transactionItems, double cashTendered) {
        // Calculate total amount using streams
        double totalAmount = transactionItems.stream()
                .peek(item -> item.setTotalPrice(item.getItem().getPrice() * item.getQuantity()))
                .mapToDouble(TransactionItem::getTotalPrice)
                .sum();

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setItems(transactionItems);
        transaction.setTotalAmount(totalAmount);
        transaction.setCashTendered(cashTendered);
        transaction.setChange(cashTendered - totalAmount);

        transactionItems.forEach(item -> item.setTransaction(transaction));
        return transactionRepository.save(transaction);
    }
}
