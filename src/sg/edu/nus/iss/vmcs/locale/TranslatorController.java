package sg.edu.nus.iss.vmcs.locale;

import java.util.Locale;
import java.util.ResourceBundle;

import sg.edu.nus.iss.vmcs.system.MainController;

public class TranslatorController {
	public static final String SPANISH = "Spanish";
	public static final String ENGLISH = "English";
	public static final String PIRATE = "Pirate";
	
	/**This attribute reference to the MainController*/
	public MainController mainCtrl;
	
	private ResourceBundle bundle;
	
	public TranslatorController(String language){
		
		if (language.compareTo(SPANISH) == 0){
			Locale.setDefault(new Locale("sp", "CR"));
		}else if(language.equals(PIRATE)){
			Locale.setDefault(new Locale("en", "PI"));
		}else{
			Locale.setDefault(new Locale("en", "SG"));
		}
		
		bundle = ResourceBundle.getBundle("Labels");
	}
	
	public String Translate(String code){
		return bundle.getString(code);
	}
}
