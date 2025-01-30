package com.point.of.sale.pos;

import com.point.of.sale.pos.model.Item;
import com.point.of.sale.pos.model.Transaction;
import com.point.of.sale.pos.model.TransactionItem;
import com.point.of.sale.pos.model.repository.ItemRepository;
import com.point.of.sale.pos.service.POSService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class POSServiceTest {

    @Autowired
    private POSService posService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void processTransaction() {

        //in this case wallet price is 20
        Item wallet = new Item("Wallet", 20.0);
        itemRepository.save(wallet);

        //in this case belt price is 40
        Item belt = new Item("Belt", 40.0);
        itemRepository.save(belt);

        // Prepare transaction items
        TransactionItem item1 = new TransactionItem();
        item1.setItem(wallet);
        item1.setQuantity(2);

        TransactionItem item2 = new TransactionItem();
        item2.setItem(belt);
        item2.setQuantity(1);
        //customer provide amount against products and will be added to persistence layer
        Transaction transaction = posService.processSale(Arrays.asList(item1, item2), 80.0);

        //calculating total amount of products
        assertEquals(80.0, transaction.getTotalAmount(), "total amount of products " + "(" + wallet.getName() + "," + belt.getName() + ")" + " should be " + transaction.getTotalAmount());

        //calculate change need to give back to customer
        assertEquals(0.0, transaction.getChange(), "the amount that will give back to customer should be " + transaction.getChange());
    }
}
