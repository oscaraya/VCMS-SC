package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * Created by debas on 27/4/2017.
 */
public class LargeDenominationFirst implements ChangeGivingStrategy {
    private TransactionController txCtrl;


    public LargeDenominationFirst(TransactionController txCtrl){
        this.txCtrl=txCtrl;
    }
    @Override
    public void computeChange(int changeRequired) {
            if(changeRequired!=0){
                int changeBal=changeRequired;
                MainController mainCtrl=txCtrl.getMainController();
                StoreController storeCtrl=mainCtrl.getStoreController();
                int cashStoreSize=storeCtrl.getStoreSize(Store.CASH);
                for(int i=cashStoreSize-1;i>=0;i--){
                    StoreItem cashStoreItem=storeCtrl.getStore(Store.CASH).getStoreItem(i);
                    int quantity=cashStoreItem.getQuantity();
                    Coin coin=(Coin)cashStoreItem.getContent();
                    int value=coin.getValue();
                    int quantityRequired=0;
                    while(changeBal>0&&changeBal>=value&&quantity>0){
                        changeBal-=value;
                        quantityRequired++;
                        quantity--;
                        System.out.println("Large Denomination -->"+quantityRequired+"Coin given:"+value);
                    }
                    try {
                        txCtrl.getMainController().getMachineryController().giveChange(i,quantityRequired);
                    } catch (VMCSException e) {
                        e.printStackTrace();
                    }
                }
                txCtrl.getCustomerPanel().setChange(changeRequired-changeBal);
                if(changeBal>0)
                    txCtrl.getCustomerPanel().displayChangeStatus(true);
            }

    }

}
