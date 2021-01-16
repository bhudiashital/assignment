package com.vending.machine.service;

import com.vending.machine.exception.ItemSoldOutException;
import com.vending.machine.exception.NotFullPaidException;
import com.vending.machine.factory.VendingMachineFactory;
import com.vending.machine.model.Bucket;
import com.vending.machine.model.Coin;
import com.vending.machine.model.Item;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static com.vending.machine.model.Coin.*;
import static org.testng.Assert.*;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeMethod
    public void init() {
        vendingMachine = VendingMachineFactory.createVendingMachine(1);
    }

    @Test
    public void testUserBuyACokeForGivenInsertedCoinsIsEqualToItemPrice() {
        long price = vendingMachine.selectItemAndGetPrice(Item.COKE);
        assertEquals(price, 25, "Coke Price should be 25");

        vendingMachine.insertCoin(Coin.QUARTER);

        Bucket<Item, List<Coin>> bucket = vendingMachine.collectItemAndChange();

        assertEquals(Item.COKE, bucket.getItem());
        assertEquals(Collections.EMPTY_LIST, bucket.getCoins());
    }

    @Test
    public void testUserBuyACokeForGivenInsertedCoinsIsGreaterThanItemPrice() {
        long price = vendingMachine.selectItemAndGetPrice(Item.COKE);
        assertEquals(price, 25,"Coke Price should be 25");

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
        long price = vendingMachine.selectItemAndGetPrice(Item.COKE);
        assertEquals(price, 25,  "Coke Price should be 25");

        vendingMachine.insertCoin(Coin.DIME);

        vendingMachine.collectItemAndChange();
    }

    @Test
    public void testUserBuyAPepsiForGivenInsertedCoinsIsEqualToItemPrice() {
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
        long price = vendingMachine.selectItemAndGetPrice(Item.PEPSI);
        assertEquals(price, 35, "Pepsi Price should be 35");

        vendingMachine.insertCoin(Coin.DIME);

        vendingMachine.collectItemAndChange();
    }

    @Test
    public void testUserBuyASodaForGivenInsertedCoinsIsEqualToItemPrice() {
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
        long price = vendingMachine.selectItemAndGetPrice(Item.SODA);
        assertEquals(price, 45, "Soda Price should be 45");

        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(NICKLE);
        vendingMachine.insertCoin(PENNY);

        vendingMachine.collectItemAndChange();
    }

    @Test
    public void testRefund() {
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(PENNY);

        List<Coin> coins = vendingMachine.refund();

        assertEquals(Arrays.asList(QUARTER, DIME, PENNY), coins);
    }

    @Test(expectedExceptions = ItemSoldOutException.class)
    public void testReset() {
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(PENNY);

        vendingMachine.reset();
        vendingMachine.selectItemAndGetPrice(Item.SODA);
    }
}