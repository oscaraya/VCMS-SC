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
public class BalancedNoOfDenomition implements ChangeGivingStrategy {
    private TransactionController txCtrl;


    public BalancedNoOfDenomition(TransactionController txCtrl){
        this.txCtrl=txCtrl;
    }

    @Override
    public void computeChange(int changeRequired) {
            if(changeRequired!=0){
                int changeBal=changeRequired;
                boolean skipQuanityCheck = false;
                int changeBalance = getBalancedDenomination(changeBal,skipQuanityCheck);
                if(changeBalance>0){
                    skipQuanityCheck = true;
                    changeBalance = getBalancedDenomination(changeBalance,skipQuanityCheck);
                    if(changeBal >0)
                        txCtrl.getCustomerPanel().displayChangeStatus(true);
                }
                txCtrl.getCustomerPanel().setChange(changeRequired-changeBalance);
            }

    }

    public int getBalancedDenomination(int changeBal,boolean skipQuanityCheck){
        MainController mainCtrl=txCtrl.getMainController();
        StoreController storeCtrl=mainCtrl.getStoreController();
        int cashStoreSize=storeCtrl.getStoreSize(Store.CASH);
        for(int i=cashStoreSize-1;i>=0;i--){
            StoreItem cashStoreItem=storeCtrl.getStore(Store.CASH).getStoreItem(i);
            int quantity=cashStoreItem.getQuantity();
            Coin coin=(Coin)cashStoreItem.getContent();
            int value=coin.getValue();
            int quantityRequired=0;
            int thresholdQuantity = 2;
            if(skipQuanityCheck){
                while(changeBal>0&&changeBal>=value&&quantity>0){
                    changeBal-=value;
                    quantityRequired++;
                    quantity--;
                    System.out.println("Rerun Balanced No.of Denomination Feature selected-->"+quantityRequired+"Coin given:"+value);
                }
            }else{
                while(changeBal>0&&changeBal>=value&&quantity>thresholdQuantity){
                    changeBal-=value;
                    quantityRequired++;
                    quantity--;
                    System.out.println("Balanced No.of Denomination Feature selected-->"+quantityRequired+"Coin given:"+value);
                }
            }
            try {
                txCtrl.getMainController().getMachineryController().giveChange(i,quantityRequired);
            } catch (VMCSException e) {
                e.printStackTrace();
            }
        }
        return changeBal;
    }
}
