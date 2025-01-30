package com.point.of.sale.pos.model;

import com.point.of.sale.pos.dto.Item;

import java.util.List;

public class Transaction {
    private List<Item> items;
    private double totalAmount;
    private double cashTendered;
    private double change;

    public Transaction(List<Item> items, double cashTendered) {
        this.items = items;
        this.cashTendered = cashTendered;
        this.totalAmount = processTotalAmount();
        this.change = processBalanceAfterTransaction();
    }

    public double processTotalAmount() {
        return items.stream().mapToDouble(Item::getTotalPrice).sum();
    }

    public double processBalanceAfterTransaction() {
        return cashTendered - totalAmount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCashTendered() {
        return cashTendered;
    }

    public void setCashTendered(double cashTendered) {
        this.cashTendered = cashTendered;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }
}
