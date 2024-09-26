package core.basesyntax.handler.impl;

import core.basesyntax.handler.OperationHandler;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.storage.Storage;

public class PurchaseOperationHandler implements OperationHandler {
    @Override
    public void apply(FruitTransaction transaction) {
        int currentQuantity = Storage.getFruitStorage()
                .getOrDefault(transaction.getFruit(), 0);
        if (currentQuantity < transaction.getQuantity()) {
            throw new RuntimeException("Not enough stock for " + transaction.getFruit());
        }
        Storage.getFruitStorage()
                .put(transaction.getFruit(), currentQuantity - transaction.getQuantity());
    }
}
