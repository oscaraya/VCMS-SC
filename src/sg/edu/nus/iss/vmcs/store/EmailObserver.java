package sg.edu.nus.iss.vmcs.store;

import sg.edu.nus.iss.vmcs.system.Environment;

/**
 * Created by jdon on 4/26/2017.
 */
public class EmailObserver extends Observer {
    public EmailObserver(StoreController storeController){
        this.storeController = storeController;
        this.storeController.attach(this);
    }

    @Override
    public void updateDrink(String name) {
        if(Environment.getDrinkEnable()) {
            UpdateLowStockContext context = new UpdateLowStockContext(new UpdateDrinkLowStock());
            context.executeStrategy("Email",name);
        }
    }

    @Override
    public void updateCoin(String name) {
        if(Environment.getCoinEnable()) {
            UpdateLowStockContext context = new UpdateLowStockContext(new UpdateCoinLowStock());
            context.executeStrategy("Email",name);
        }
    }
}
