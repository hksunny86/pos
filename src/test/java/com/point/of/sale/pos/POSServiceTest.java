package com.point.of.sale.pos;

import com.point.of.sale.pos.dto.Item;
import com.point.of.sale.pos.model.Transaction;
import com.point.of.sale.pos.service.POSService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class POSServiceTest {

    @Test
    public void processTransaction() {
        POSService posService = new POSService();

        Item item1 = new Item("Wallet", 20.0, 1); //in this case wallet price is 20
        Item item2 = new Item("Belt", 40.0, 1); //in this case belt price is 40

        //customer provide amount against products and will be added to persistence layer
        Transaction transaction = posService.processSale(Arrays.asList(item1, item2), 80.0);

        //calculating total amount of products
        assertEquals(60.0, transaction.processTotalAmount(), "total amount of products " + "(" + item1.getName() + "," + item2.getName() + ")" + " should be " + transaction.getTotalAmount());

        //calculate change need to give back to customer
        assertEquals(20.0, transaction.processBalanceAfterTransaction(), "the amount that will give back to customer should be " + transaction.getChange());
    }
}
