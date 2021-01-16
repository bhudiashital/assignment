package com.vending.machine.service;

import com.vending.machine.exception.ItemSoldOutException;
import com.vending.machine.exception.NotFullPaidException;
import com.vending.machine.factory.VendingMachineFactory;
import com.vending.machine.model.Bucket;
import com.vending.machine.model.Coin;
import com.vending.machine.model.Item;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.vending.machine.model.Coin.*;
import static org.testng.Assert.assertEquals;

public class VendingMachineTest {
    private Logger logger = Logger.getLogger(VendingMachineTest.class);

    private VendingMachine vendingMachine;

    @BeforeMethod
    public void init() {
        logger.info("preload vending machine with coin and item quantity to one");
        vendingMachine = VendingMachineFactory.createVendingMachine(1);
    }

    @Test
    public void testUserBuyACokeForGivenInsertedCoinsIsEqualToItemPrice() {
        logger.info("Verify User Buy A Coke For Given Inserted Coins Is Equal To Item Price");
        long price = vendingMachine.selectItemAndGetPrice(Item.COKE);
        assertEquals(price, 25, "Coke Price should be 25");

        vendingMachine.insertCoin(Coin.QUARTER);

        Bucket<Item, List<Coin>> bucket = vendingMachine.collectItemAndChange();

        assertEquals(Item.COKE, bucket.getItem());
        assertEquals(Collections.EMPTY_LIST, bucket.getCoins());
    }

    @Test
    public void testUserBuyACokeForGivenInsertedCoinsIsGreaterThanItemPrice() {
        logger.info("Verify User Buy A Coke For Given Inserted Coins Is Greater Than Item Price");
        long price = vendingMachine.selectItemAndGetPrice(Item.COKE);
        assertEquals(price, 25, "Coke Price should be 25");

        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(NICKLE);
        vendingMachine.insertCoin(PENNY);

        Bucket<Item, List<Coin>> bucket = vendingMachine.collectItemAndChange();

        assertEquals(Item.COKE, bucket.getItem());
        assertEquals(Arrays.asList(DIME, NICKLE, PENNY), bucket.getCoins());
    }

    @Test(expectedExceptions = NotFullPaidException.class)
    public void testUserBuyACokeForGivenInsertedCoinsIsLessThanItemPrice() {
        logger.info("Verify User Buy A Coke For Given Inserted Coins Is Less Than Item Price");
        long price = vendingMachine.selectItemAndGetPrice(Item.COKE);
        assertEquals(price, 25, "Coke Price should be 25");

        vendingMachine.insertCoin(Coin.DIME);

        vendingMachine.collectItemAndChange();
    }

    @Test
    public void testUserBuyAPepsiForGivenInsertedCoinsIsEqualToItemPrice() {
        logger.info("Verify User Buy A Pepsi For Given Inserted Coins Is Equal To Item Price");
        long price = vendingMachine.selectItemAndGetPrice(Item.PEPSI);
        assertEquals(price, 35, "Pepsi Price should be 35");

        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);

        Bucket<Item, List<Coin>> bucket = vendingMachine.collectItemAndChange();

        assertEquals(Item.PEPSI, bucket.getItem());
        assertEquals(Collections.EMPTY_LIST, bucket.getCoins());
    }

    @Test
    public void testUserBuyAPepsiForGivenInsertedCoinsIsGreaterThanItemPrice() {
        logger.info("Verify User Buy A Pepsi For Given Inserted Coins Is Greater Than Item Price");
        long price = vendingMachine.selectItemAndGetPrice(Item.PEPSI);
        assertEquals(price, 35, "Pepsi Price should be 35");

        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(NICKLE);
        vendingMachine.insertCoin(PENNY);

        Bucket<Item, List<Coin>> bucket = vendingMachine.collectItemAndChange();

        assertEquals(Item.PEPSI, bucket.getItem());
        assertEquals(Arrays.asList(NICKLE, PENNY), bucket.getCoins());
    }

    @Test(expectedExceptions = NotFullPaidException.class)
    public void testUserBuyAPepsiForGivenInsertedCoinsIsLessThanItemPrice() {
        logger.info("Verify User Buy A Pepsi For Given Inserted Coins Is less Than Item Price");
        long price = vendingMachine.selectItemAndGetPrice(Item.PEPSI);
        assertEquals(price, 35, "Pepsi Price should be 35");

        vendingMachine.insertCoin(Coin.DIME);

        vendingMachine.collectItemAndChange();
    }

    @Test
    public void testUserBuyASodaForGivenInsertedCoinsIsEqualToItemPrice() {
        logger.info("Verify User Buy A Soda For Given Inserted Coins Is Equal To Item Price");
        long price = vendingMachine.selectItemAndGetPrice(Item.SODA);
        assertEquals(price, 45, "Soda Price should be 45");

        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(NICKLE);
        vendingMachine.insertCoin(NICKLE);

        Bucket<Item, List<Coin>> bucket = vendingMachine.collectItemAndChange();

        assertEquals(Item.SODA, bucket.getItem());
        assertEquals(Collections.EMPTY_LIST, bucket.getCoins());
    }

    @Test
    public void testUserBuyASodaForGivenInsertedCoinsIsGreaterThanItemPrice() {
        logger.info("Verify User Buy A Soda For Given Inserted Coins Is Greater Than Item Price");
        long price = vendingMachine.selectItemAndGetPrice(Item.SODA);
        assertEquals(price, 45, "Soda Price should be 45");

        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(NICKLE);
        vendingMachine.insertCoin(NICKLE);
        vendingMachine.insertCoin(PENNY);

        Bucket<Item, List<Coin>> bucket = vendingMachine.collectItemAndChange();

        assertEquals(Item.SODA, bucket.getItem());
        assertEquals(Arrays.asList(PENNY), bucket.getCoins());
    }

    @Test(expectedExceptions = NotFullPaidException.class)
    public void testUserBuyASodaForGivenInsertedCoinsIsLessThanItemPrice() {
        logger.info("Verify User Buy A Soda For Given Inserted Coins Is less Than Item Price");
        long price = vendingMachine.selectItemAndGetPrice(Item.SODA);
        assertEquals(price, 45, "Soda Price should be 45");

        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(NICKLE);
        vendingMachine.insertCoin(PENNY);

        vendingMachine.collectItemAndChange();
    }

    @Test
    public void testRefund() {
        logger.info("Verify refund, its expected to get actual inserted coins after refund");
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(PENNY);

        List<Coin> coins = vendingMachine.refund();

        assertEquals(Arrays.asList(QUARTER, DIME, PENNY), coins);
    }

    @Test(expectedExceptions = ItemSoldOutException.class)
    public void testReset() {
        logger.info("Verify reset, it should throw ItemSoldOutException when request an item after reset");
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(PENNY);

        vendingMachine.reset();
        vendingMachine.selectItemAndGetPrice(Item.SODA);
    }
}