/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.Coin;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This control object manages the giving of change to the Customer.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class ChangeGiver {
	private TransactionController txCtrl; 
	private ChangeGivingStrategy changeGivingStrategy;

	/**
	 * The constructor creates an instance of the object.
	 * @param txCtrl the TransactionController
	 */
	public ChangeGiver(TransactionController txCtrl){
		this.txCtrl=txCtrl;
		//this.changeGivingStrategy = changeGivingStrategy;
		}

		public void executeStrategy(int num1){
			changeGivingStrategy.computeChange(num1);
		}

	public ChangeGiver(ChangeGivingStrategy changeGivingStrategy){
		this.changeGivingStrategy = changeGivingStrategy;
	}
	
	/**
	 * This method is used to reset the Refund/ Change Tray display on the
	 * Customer Panel.
	 */
	public void resetChange(){
		CustomerPanel custPanel=txCtrl.getCustomerPanel();
		if(custPanel!=null){
			custPanel.resetChange();
		}
	}
	
	/**
	 * This method manages the issuing of change to the Customer.
	 * @param changeRequired
	 * @return return TRUE if give change use case success, otherwise, return FALSE.
	 */
	public boolean giveChange(int changeRequired){
		if(changeRequired==0)
			return true;
			int changeBal=changeRequired;
			boolean check=false;
			System.out.println("print"+Environment.getChangetype());
			if(Environment.getChangetype().equals("large_denomination_first")) {
				ChangeGiver context = new ChangeGiver(new LargeDenominationFirst(txCtrl));
				context.executeStrategy(changeRequired);
				return true;
			}else{
				ChangeGiver context = new ChangeGiver(new BalancedNoOfDenomition(txCtrl));
				context.executeStrategy(changeRequired);
				return true;
			}
	}
	
	/**
	 * This method is used to display the appropriate message on the No Change
	 * Available Display depending on the current change availability.
	 */
	public void displayChangeStatus(){
		CustomerPanel custPanel=txCtrl.getCustomerPanel();
		if(custPanel==null)
			return;
		boolean isAnyDenoEmpty=false;
		MainController mainCtrl=txCtrl.getMainController();
		StoreController storeCtrl=mainCtrl.getStoreController();
		StoreItem[] cashStoreItems=storeCtrl.getStore(Store.CASH).getItems();
		for(int i=0;i<cashStoreItems.length;i++){
			StoreItem storeItem=cashStoreItems[i];
			CashStoreItem cashStoreItem=(CashStoreItem)storeItem;
			int quantity=cashStoreItem.getQuantity();
			if(quantity==0)
				isAnyDenoEmpty=true;
		}
		custPanel.displayChangeStatus(isAnyDenoEmpty);
	}
}//End of class ChangeGiver