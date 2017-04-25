package sg.edu.nus.iss.vmcs.locale;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TranslatorControllerTest {
	private static final String LANGUAGE_NAME_CODE = "Language_Name";
	
	@Before
	public void setup() throws Exception{
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testEnglish() {
		TranslatorController trCtrl = new TranslatorController(TranslatorController.ENGLISH);
		String language = trCtrl.Translate(LANGUAGE_NAME_CODE);
		
		assertEquals("English", language);
	}
	
	@Test
	public void testSpanish() {
		TranslatorController trCtrl = new TranslatorController(TranslatorController.SPANISH);
		String language = trCtrl.Translate(LANGUAGE_NAME_CODE);
		
		assertEquals("Spanish", language);
	}
	
	@Test
	public void testDefault() {
		TranslatorController trCtrl = new TranslatorController("Mandarin");
		String language = trCtrl.Translate(LANGUAGE_NAME_CODE);
		
		assertEquals("English", language);
	}

}
