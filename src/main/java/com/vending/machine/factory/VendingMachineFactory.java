package com.vending.machine.factory;

import com.vending.machine.service.VendingMachine;
import com.vending.machine.service.impl.VendingMachineImpl;

public class VendingMachineFactory {
    public static VendingMachine createVendingMachine(int initialQuantityToFill) {
        return (VendingMachine) new VendingMachineImpl(initialQuantityToFill);
    }
}
