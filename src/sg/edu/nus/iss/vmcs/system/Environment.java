/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.system;

import java.io.*;
import java.util.*;

import sg.edu.nus.iss.vmcs.util.*;

/**
 * This entity object holds the environment settings used to configure the system.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class Environment {
	/**This constant attribute identify the drink property filename*/
	public final static String DRINK_PROP_FILE = "drinkFile";
	/**This constant attribute identify the cash property filename*/
	public final static String CASH_PROP_FILE = "cashFile";
	/**This constant attribute denotes the password string*/
	public final static String PASSWORD = "password";

	/**This constant attribute denotes the language string*/
	public final static String LANGUAGE = "language";
	public final static String DEFAULT_LANGUAGE = "English";

	/**This constant is for audit logger */
	public final static String AUDIT_LOG_ITEM_DISPENSE_ENABLED = "audit.log.item_dispense.enabled";
	public final static String AUDIT_LOG_PAYMENT_ENABLED = "audit.log.payment.enabled";
	public final static String AUDIT_LOG_TRANSFER_ALL_CASH_ENABLED = "audit.log.transfer_all_cash.enabled";
	public static final String DEFAULT_ENABLE_VALUE = "false";

	/**This constant is for Give Change */
	public final static String CHANGETYPE = "change.type";

	private static Properties prop;

	/**
	 * This method reads-in the environment setting from a properties file and stores them in
	 * a hash table.
	 * @param propFile the property file name.
	 * @throws VMCSException if reading property file fails.
	 */
	public static void initialize(String propFile) throws VMCSException {
		try {
			prop = new Properties();
			FileInputStream stream = new FileInputStream(propFile);
			prop.load(stream);
			stream.close();
		} catch (Exception e) {
			throw new VMCSException(
				"Environment.initialize",
				"Error loading environment properties: " + e);
		}
	}

	/**
	 * This method returns the name/ location of the DrinksStore properties file.
	 * @return the name of the DrinksStore properties file.
	 */
	public static String getDrinkPropFile() {
		return prop.getProperty(DRINK_PROP_FILE);
	}

	/**
	 * This method returns the name/ location of the CashStore properties file.
	 * @return the mane of the CashStore properties file.
	 */
	public static String getCashPropFile() {
		String fn;
		fn = prop.getProperty(CASH_PROP_FILE);
		return fn;
	}

	/**
	 * if you put password in the property file, the password is obtained here.
	 * @return the password
	 */
	public static String getPassword() {
		return prop.getProperty(PASSWORD);
	}

	public static String getLanguage(){
		return prop.getProperty(LANGUAGE, DEFAULT_LANGUAGE);
	}

	public static Boolean isAuditLogItemDispenseEnabled() {
		return Boolean.valueOf(prop.getProperty(AUDIT_LOG_ITEM_DISPENSE_ENABLED, DEFAULT_ENABLE_VALUE));
	}

	public static Boolean isAuditLogPaymentEnabled() {
		return Boolean.valueOf(prop.getProperty(AUDIT_LOG_PAYMENT_ENABLED, DEFAULT_ENABLE_VALUE));
	}

	public static Boolean isAuditLogTransferAllCashEnabled() {
		return Boolean.valueOf(prop.getProperty(AUDIT_LOG_TRANSFER_ALL_CASH_ENABLED, DEFAULT_ENABLE_VALUE));
	}

	public static String getChangetype() {
		return prop.getProperty(CHANGETYPE);
	}


}//End of class Environment