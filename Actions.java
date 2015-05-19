package libraries;

import iPhoneReusables.RE_ProfileCapture_SeekAge;
import iPhoneReusables.RE_ViewProfile;
import io.appium.java_client.AppiumDriver;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.LogRecord;

import javax.swing.text.StyledEditorKit.BoldAction;
dfsgdsgdfgdsghfg
import libraries.*;
import mx4j.log.Log4JLogger;

import org.apache.commons.collections.map.StaticBucketMap;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.server.handler.interactions.touch.Scroll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import libraries.Log4JPropertiesTest;


public class Actions extends Log4JPropertiesTest implements IStopWatch
{

	

	public static Logger log= Logger.getLogger("Log4JPropertiesTest.class");

	/**
	 * Purpose: Created Dictionary to set and get data from it, where Key and Value as string.
	 */
	public static Hashtable<String, String> dict =new Hashtable<String, String>();

	

	private  HashMap<String, Long> startingTimes;
	private  HashMap<String, Long> measures;
	public static IStopWatch lastCreated = new Actions();
	 
	
	/**
	 * Action Constructor that will initiate two hashmap Starting Times and Measures.
	 */
	public Actions() {
	this.startingTimes = new HashMap<String,Long>();
	this.measures = new HashMap<String,Long>();
	lastCreated = this;
	}
	
	/**
	 * Interface Implementation of Start Function declaration of StopWatch Interface.
	 */
	public IStopWatch start(String label) {
		Calendar cal = Calendar.getInstance();
    	System.out.println("Start Time: " +cal.getTime());
    	startingTimes.put(label, System.currentTimeMillis());
    	
    	
    	
    	
	if (measures.get(label) == null) {
	measures.put(label, 0L);
	}

	return this;
	}
	 
	/**
	 * Interface Implementation of Pause Function declaration of StopWatch Interface.
	 */
	public IStopWatch pause(String label) {
	Long start = startingTimes.get(label);
	if (start == null) {
	throw new RuntimeException("Calling pause(\"" + label + "\" without calling start() at least once.");
	}
	long newMeasure = System.currentTimeMillis() - startingTimes.get(label);
	measures.put(label, measures.get(label) + newMeasure);
startingTimes.remove(label);
	return this;
	}
	 
	/**
	 * Interface Implementation of Measure Function declaration of StopWatch Interface.
	 */
	public IStopWatch measure(String label) {
	if (startingTimes.containsKey(label)) {
		Calendar cal = Calendar.getInstance();
    	System.out.println("End Time: "+cal.getTime());
	pause(label);
	}
	//System.out.println("STOPWATCH " + label + ": " + measures.get(label));
	long yourmilliseconds=measures.get(label);
	
	
	String totalExecutionTime=String.format("%d hour, %d min, %d sec", TimeUnit.MILLISECONDS.toHours(yourmilliseconds),
		    TimeUnit.MILLISECONDS.toMinutes(yourmilliseconds),
		    TimeUnit.MILLISECONDS.toSeconds(yourmilliseconds) - 
		    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(yourmilliseconds))
		);
	//System.out.println(totalExecutionTime);
	Actions.SetVariable(label , totalExecutionTime);
	return this;
	}
	 
	/**
	 * Interface Implementation of reset Function declaration of StopWatch Interface that will reset stopwatch Time.
	 */
	public IStopWatch reset(String label) {
	measures.remove(label);
	return this;
	}



	
	/**
	 * Purpose: Clicks on identifier 
	 * @param identifier: Pass the identifier to be clicked on.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static void Click(String identifier)
	{
		int ErrorMessageCount = 0;
		String objName = null;
		String objProperty=null;
		String identity=null;
		try
		{
		String[] oRBreak=identifier.split("=");
		
		int oRLength= oRBreak.length;
		if(oRLength>3)
		{
			objName=oRBreak[oRLength-4];
			identity=oRBreak[oRLength-3];
			objProperty=oRBreak[oRLength-2].toString()+"="+oRBreak[oRLength-1].toString();
		}
		else
		{
		objName=oRBreak[oRLength-3];
		
		identity=oRBreak[oRLength-2];
		objProperty=oRBreak[oRLength-1];
		}
		if((objName.isEmpty())|| (objName==null))
		{
			objName=objProperty;
		}

		//identifier=identity+"="+objProperty;
			System.out.println(identity+"="+objProperty);
			SetAppium.driver.findElement(ToBy(identifier)).click();
			//System.out.println("Object Name : " + SetAppium.driver.findElement(ToBy(identifier)).getAttribute(identifier));
			pageSync();
		}
		catch (Exception e ) 
		{
			ErrorMessageCount = 1;
			log.info(e.getMessage());
			//	ErrorMessage = e.getMessage();
<<<<<<< .mine

		}

		if (ErrorMessageCount == 0)
		{
			log.info("Passed- Clicked on '" + objName + "' object");
		} else {
			log.info("Failed- Unable to locate element on a page with property: " + objProperty + " having object name -'"+ objName+"'");

		}
		pageSync();

		
=======
				
			}
			
			if (ErrorMessageCount == 0)
			{
//				log.info("Passed- Clickd on '" + identifier + "' object");
				Report.Remarks("Clickd on '" + identifier + "' object", "Pass", "");
			} else {
			//	log.info("Failed- Unable to locate element on page with property: " + identifier + "");
				Report.Remarks("Unable to locate element on page with property: " + identifier + "", "Fail", "");
			}
			pageSync();
			
			
>>>>>>> .r108
		/*
			try{
				Boolean value=WaitForObject(identifier, Integer.parseInt(GetVariable("GlobalObjectTimeOut")));
				if (value) {
				pageSync();
			 SetAppium.driver.findElement(ToBy(identifier)).click();
			 Thread.sleep(2000);
				}

				else {

					scroll("D-U");
					Boolean disp=SetAppium.driver.findElement(ToBy(identifier)).isDisplayed();
					if(disp)
					{
						SetAppium.driver.findElement(ToBy(identifier)).click();
					}
					else{
						scroll("U-D");
						Boolean displayed=SetAppium.driver.findElement(ToBy(identifier)).isDisplayed();
						if(displayed)
						{
							SetAppium.driver.findElement(ToBy(identifier)).click();
						}
						else
						{
							System.out.println("Elemnt not found");
						}
					}

				}




			 log.finer("Click on " + identifier);

			}catch(Exception ex){
				log.info(ex.getMessage());
			}
		 */
	}




	/**
	 * Purpose: Object identification(type as css,xpath,id,name,classname) from object repository. 
	 * @param identifier: Pass assigned identifier variable from object repository.
	 * @return: Returns the identifier with particular type.
	 */
	public static By ToBy(String identifier)
	{
		try{
			
		
		String sIdentifier;
		String objectProperty;
		String[] breakIdentifier=identifier.split("=");
		int brkIdentifierSize=breakIdentifier.length;
		
		if (brkIdentifierSize>3)
		{
			sIdentifier= breakIdentifier[1];
			objectProperty=breakIdentifier[2].toString()+"="+breakIdentifier[3].toString();
		}
		else
		{
sIdentifier= breakIdentifier[1];
objectProperty=breakIdentifier[2];
		}
		switch (sIdentifier.toLowerCase().trim())
		{
		case "xpath":
			return By.xpath(objectProperty);
		case "css":
			return By.cssSelector(objectProperty);
		case "id":
			return By.id(objectProperty);
		case "name":
			return By.name(objectProperty);

		case "classname":
			return By.className(objectProperty);


		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		
		
	}

	/**
	 * Purpose: Synchronize the page when switches from one page to another.
	 */
	public static void pageSync()
	{
		try {
			SetAppium.driver.manage().timeouts().implicitlyWait(Integer.parseInt(GetVariable("pageLoadTimeOut")), TimeUnit.SECONDS );
			//System.out.println("inside sync");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Purpose: Scroll the page from down to upward and vice-versa. 
	 * @param direction: Pass D-U to scroll screen from Down to upward. Pass U-D to scroll Up to Downward.
	 */
	@SuppressWarnings("null")
	public static void scroll(String direction){
		Double startX, startY, endX, endY;
		org.openqa.selenium.Dimension size=SetAppium.driver.manage().window().getSize();

		HashMap<String, Double> swipeObject=null;
		switch (direction.toUpperCase()) {
		case "D-U":
			startX = (double) (size.width - 50);
			startY = (double) ((size.height) / 2);
			endX = startX;
			endY = startY - 100;
			try

			{

				swipeObject=new HashMap<String, Double>();
				swipeObject.put("endX",startX );
				swipeObject.put("endY",startY);
				swipeObject.put("startX",endX);
				swipeObject.put("startY",endY);
				SetAppium.driver.executeScript("mobile: scroll", swipeObject);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		case "U-D":
			startX = (double) (size.width - 50);
			startY = (double) ((size.height) / 2);
			endX = startX;
			endY = startY + 200;

			try{
				swipeObject= new HashMap<String, Double>();
				swipeObject.put("endX",startX );
				swipeObject.put("endY",startY);
				swipeObject.put("startX",endX);
				swipeObject.put("startY",endY);
				SetAppium.driver.executeScript("mobile: scroll", swipeObject);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * Purpose: Enter the data in a text fields.
	 * @param identifier: Pass the identifier from OR where data to be insert.
	 * @param data: Pass the data to be enter.
	 */
	public static void EnterData(String identifier, String data)
	{

		//	String ErrorMessage = "";

		int ErrorMessageCount = 0;


		try
		{
			String Data = Actions.ReplaceVariables(data);
			SetAppium.driver.findElement(ToBy(identifier)).sendKeys(Data);
			pageSync();
		}
		catch (Exception e ) 
		{
			ErrorMessageCount = 1;
			//	ErrorMessage = e.getMessage();
<<<<<<< .mine

		}

		if (ErrorMessageCount == 0)
		{
			log.info("Passed- Entered text '" + data + "' in '" + identifier + "'");
		} else {
			log.info("Failed- Unable to locate element on page with property: " + identifier + "");
		}
		pageSync();




		/*
=======
				
			}
			
			if (ErrorMessageCount == 0)
			{
			//	log.info("Passed- Entered text '" + data + "' in '" + identifier + "'");
				Report.Remarks("Entered text '" + data + "' in '" + identifier + "'", "Pass", "");
				
			} else {
			//	log.info("Failed- Unable to locate element on page with property: " + identifier + "");
				Report.Remarks("Unable to locate element on page with property: " + identifier + "", "Fail", "");	
			}
			pageSync();
			
			
			
			
			/*
>>>>>>> .r108
			try{
				Boolean value=WaitForObject(identifier, Integer.parseInt(GetVariable("GlobalObjectTimeOut")));
				if (value) {
					SetAppium.driver.findElement(ToBy(identifier)).sendKeys(data);
				}
				else {
					//scroll();
					SetAppium.driver.findElement(ToBy(identifier)).sendKeys(data);
				}

		}catch(Exception ex){
			log.info(ex.getMessage());
		}
		 */

	}

	//public static Hashtable<String, String> filedict =new Hashtable<String, String>();  //dataDictionary for adding file data

	/**
	 * Purpose: Load Parameters file in data dictionary
	 * @param filePath: Pass the File Path for the file to be loaded.
	 * @return : Returns the dictionary with loaded Key value pair.
	 */
	public static Hashtable<String, String> load(String filePath)
	{

		try {
			BufferedReader bReader= new BufferedReader(new FileReader(filePath));



			String strLine;
			while((strLine=bReader.readLine())!=null)

			{

				String[] objects=strLine.split("=");
				dict.put((String)objects[0].toLowerCase(),(String)objects[1]);
				//System.out.println("inside file");
			}
			bReader.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dict;



	}


	/**
	 * Purpose: wait for specified time.
	 * @param timeInSec: Enter Time in Second to be wait for.
	 */
	public static void Wait(int timeInSec)
	{
		SetAppium.driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);

		//$(objectLocator).waitUntil(Condition.exist, libraries.MatchConfig.getTimeOut());
	}

	/**
	 * Purpose: Wait for specified time, until the Expected condition of visibility of identifier is met.
	 * @param identifier: Pass the identifier from OR for which wait is applied.
	 * @param timeInSec: Pass Time in Seconds.
	 * @return
	 */
	public static boolean WaitForObject(String identifier,int timeInSec)
	{
		boolean value = false;
		try{
		WebDriverWait wait= new WebDriverWait(SetAppium.driver, timeInSec);

		WebElement val=wait.until(ExpectedConditions.visibilityOfElementLocated(Actions.ToBy(identifier)));
		value=val.isDisplayed();
		}
		catch(Exception ex)
		{
		log.info(ex.getMessage());
		}
		return value;
	}

	public static WebElement GetObject(String identifier)
	{
		WebElement val = null;
		try
		{
		val=SetAppium.driver.findElement(Actions.ToBy(identifier));
		}
		catch(Exception ex)
		{
			
		}
		return val;
	}
	/**
	 * Purpose: Get the text from page.
	 * @param identifier: Pass the identifier for which text to get.
	 * @return: Returns the text as a string.
	 */
	public static String GetText(String identifier)
	{
		String data=libraries.SetAppium.driver.findElement(ToBy(identifier)).getText();

		return data;

	}

	/**
	 *Purpose: Clears the text from text fields.
	 * @param identifier: Pass the identifier of text field from where text to be clear.
	 */
	public static void ClearText(String identifier)
	{
		
		SetAppium.driver.findElement(ToBy(identifier)).clear();
	}


	/**
	 *Purpose: Verify two strings are equal of not.
	 * @param actual: Actual value from page.
	 * @param expected: Expected value from user.
	 * @return: Function will returns true if the values are equal, else false.
	 */
	public static boolean IsEqual(String actual, String expected)
	{


		actual=actual.trim();
		expected=expected.trim();
		if(actual.equals(expected))
		{

			log.info("Passed - Values are equal after trimming spaces from Start and End. Expected='"+expected+"' and Actual="+actual);
			return true;
		}
		log.info("Failed - Value are not equal. Expected="+expected+" and Actual="+actual);
		return false;

	}

	/**
	 * Purpose: Accepts the Page Pop-Up. 
	 */
	public static void AcceptPopUp()
	{
		Alert alert=SetAppium.driver.switchTo().alert();
		alert.accept();
	}

	/**
	 *Purpose: Verifies Error message on Page.
	 * @param identifier: Error message Identifier.
	 * @param expectedText: Expected Text to be verified.
	 */
	public static void VerifyErrorMessage(String identifier, String expectedText)
	{
		String errorMessage=GetText(identifier);
		Actions.IsEqual(errorMessage, expectedText);

	}

	/**
	 * Purpose: Set the value for particular key in a data dictionary
	 * @param varKey: Key in data dictionary.
	 * @param varValue: Value for particular key in data dictionary.
	 */
	public static void SetVariable(String varKey, String varValue)
	{
		varKey= varKey.toString().toLowerCase().trim();
		//varValue = varValue.toString();

		if (varValue==null)
		{
			varValue = "";
		}

		if(dict.containsKey(varKey))
		{
			dict.remove(varKey);
			dict.put(varKey, varValue);
		}
		else
			dict.put(varKey,varValue);

		log.info("Passed - Store '" + varValue + "' value in '" + varKey + "' variable.");


	}

	/**
	 * Purpose: Get the value from data dictionary for particular key.
	 * @param varKey: Key for which data to be fetch from data dictionary.
	 * @return : Returns value for a key if it exist, else return empty string.
	 */
	public static String GetVariable(String varKey)
	{
		varKey = varKey.toString().toLowerCase();

		if (dict.containsKey(varKey))
		{
			if (dict.get(varKey).isEmpty()){
				return null;
			}
			else
			{
<<<<<<< .mine
				String VarValue=dict.get(varKey);
				return VarValue.toString();
=======
				
			//	log.info("Passed - Values are equal after trimming spaces from Start and End. Expected='"+expected+"' and Actual="+actual);
				Report.Remarks("Values are equal after trimming spaces from Start and End. Expected='"+expected+"' and Actual="+actual, "Pass", "");
				return true;
>>>>>>> .r108
			}
<<<<<<< .mine

		}  

		else
=======
			//log.info("Failed - Value are not equal. Expected="+expected+" and Actual="+actual);
			Report.Remarks("Value are not equal. Expected="+expected+" and Actual="+actual, "Fail", "");
			return false;
			
			}
		
		public static void AcceptPopUp()
>>>>>>> .r108
		{
			log.info("Failed - Key '"+varKey+"' Does not exist in data dictionary");
			return "";
		}
	}

	/**
	 * Purpose: Replace the variable from string.
	 * @param txtString: String Text from which text to replace.
	 * @return: Returns replaced text.
	 * @throws IOException
	 */
	public static String ReplaceVariables(String txtString) throws IOException
	{

		String txtToReplace =  txtString ; 
		int VarStartingPosition ;
		int VarEndPosition ;
		String VarToReplace;
		int variableExist = -1;

		do 
		{
			VarStartingPosition = 0;
			VarEndPosition = 0;

			VarStartingPosition = txtToReplace.indexOf("{$");

			if (VarStartingPosition == -1 )
			{
				VarStartingPosition = 0;
			}
			String RightText  = txtToReplace.substring(VarStartingPosition); 
			VarEndPosition = RightText.indexOf("}");

			if (VarStartingPosition > 0 || VarEndPosition > 0 )
			{
				String VarName = txtToReplace.substring(VarStartingPosition+2, VarStartingPosition + VarEndPosition);
				VarToReplace = ("{$" + VarName + "}");  
				String Variablevalue = Actions.GetVariable(VarName);
				txtToReplace =  txtToReplace.replace(VarToReplace, Variablevalue);
				variableExist = txtToReplace.indexOf("{$");
			}
<<<<<<< .mine
		}  while (variableExist!=-1);

		return txtToReplace;
	}

	/**
	 * Purpose: Verify object is displayed on a screen.
	 * @param identifier: Identifier to check weather object present or not.
	 * @return: Returns true if object is displayed on a screen, else false.
	 */
	public static String VerifyObjectPresent(String identifier)
	{
		boolean isObjectExists = true;

		try
		{
			isObjectExists=libraries.SetAppium.driver.findElement(ToBy(identifier)).isDisplayed();
		} catch (Exception e)
		{
			isObjectExists = false;
=======
			else
				dict.put(varKey,varValue);
			
		//	log.info("Passed - Store '" + varValue + "' value in '" + varKey + "' variable.");
			
			
>>>>>>> .r108
		}
<<<<<<< .mine

		if ( isObjectExists == true )
=======
		
		
		public static String GetVariable(String varKey)
	    {
		    varKey = varKey.toString().toLowerCase();
	        
	        if (dict.containsKey(varKey))
	        {
	        	if (dict.get(varKey).isEmpty()){
	        		return null;
	        	}
	        	else
	        	{
	        		String VarValue=dict.get(varKey);
	        		return VarValue.toString();
	        	}
	        	
	        }  
	    
	        else
	        {
	        	//log.info("Failed - Key '"+varKey+"' Does not exist in data dictionary");
	        	Report.Remarks("Key '"+varKey+"' Does not exist in data dictionary", "Fail", "");
	    		return "";
	        }
			}
			
		public static String ReplaceVariables(String txtString) throws IOException
>>>>>>> .r108
		{
			log.info("Passed - '" + identifier + "' object exists on page");

		} else
		{
			log.info("Failed- Unable to locate element on page with property: " + identifier + "");
		}
		return null;
	}

	/**
	 * 
	 * @param identifier
	 * @return
	 */
	public static String VerifyObjectNotPresent(String identifier)
	{
		boolean isObjectExists = true;

		try
		{
<<<<<<< .mine
			isObjectExists=libraries.SetAppium.driver.findElement(ToBy(identifier)).isDisplayed();
		} catch (Exception e)
		{
			isObjectExists = false;
=======
			boolean isObjectExists = true;
			
			try
			{
				isObjectExists=libraries.SetAppium.driver.findElement(ToBy(identifier)).isDisplayed();
			} catch (Exception e)
			{
				isObjectExists = false;
			}
			
			if ( isObjectExists == true )
					{
			//	log.info("Passed - '" + identifier + "' object exists on page");
				Report.Remarks("'" + identifier + "' object exists on page", "Pass", "");
					} else
					{
			//	log.info("Failed- Unable to locate element on page with property: " + identifier + "");
				Report.Remarks("Unable to locate element on page with property: " + identifier + "", "Fail", "");
					}
			return null;
>>>>>>> .r108
		}

		if ( isObjectExists == true )
		{
<<<<<<< .mine
			log.info("Failed- Unable to locate element on page with property: " + identifier + "");

		} else
		{
			log.info("Passed - '" + identifier + "' object doesn't exist on page");
=======
			boolean isObjectExists = true;
			
			try
			{
				isObjectExists=libraries.SetAppium.driver.findElement(ToBy(identifier)).isDisplayed();
			} catch (Exception e)
			{
				isObjectExists = false;
			}
			
			if ( isObjectExists == true )
					{
			//	log.info("Failed- Unable to locate element on page with property: " + identifier + "");
				Report.Remarks("Unable to locate element on page with property: " + identifier + "", "Fail", "");	
					} else
					{
			//	log.info("Passed - '" + identifier + "' object doesn't exist on page");
				Report.Remarks("'" + identifier + "' object doesn't exist on page", "Pass", "");
					}
			return null;
>>>>>>> .r108
		}
		return null;
	}


	/**
	 * Purpose: Verify Object Property .i.e Is Enabled or Disabled.
	 * E.g. Call Function like: VerifyObjectProperty(RE_ViewProfile.btnWink, "isenabled", "false"); 
	 * @param identifier: Pass the identifier for which Property to be identified.
	 * @param obJProprty: Pass object property i.e. isEnabled, Name etc
	 * @param ObjPropertyValue: Pass Expected result as True or False.
	 * @return
	 * @throws IOException 
	 */
	public static void VerifyObjectProperty(String identifier, String obJProperty, String objPropertyValue)
	{
		try
		{

			String remarks = "";

			//String ExpectedText = Actions.ReplaceVariables(ObjPropertyValue);
            String expectedText=objPropertyValue.toLowerCase();
			switch (obJProperty.toLowerCase().trim())
			{
<<<<<<< .mine
			case "isenabled":
				boolean enableProperty = libraries.SetAppium.driver.findElement(ToBy(identifier)).isEnabled();
				if (expectedText.toLowerCase() == String.valueOf(enableProperty).toLowerCase() )
				{
					remarks = "Passed- Actual iSEnabled object Property '" +enableProperty + "' matched with expected property-" + expectedText;
				} else {
					remarks = "Failed- Actual iSEnabled object Property '" +enableProperty + "' is not matched with expected property-" + expectedText + "'";
				}
=======
				
			String remarks = ""; 
			String Status = "";
			
			String ExpectedText = Actions.ReplaceVariables(ObjPropertyValue);
			
			switch (obJProprty.toLowerCase().trim())
			{
				case "isenabled":
					boolean enableProperty = libraries.SetAppium.driver.findElement(ToBy(identifier)).isEnabled();
					if (ExpectedText.toLowerCase() == String.valueOf(enableProperty).toLowerCase() )
							{
						//remarks = "Passed- Actual iSEnabled object Proprty '" +enableProperty + "' equal with expacted property";
						remarks = "Actual iSEnabled object Proprty '" +enableProperty + "' equal with expacted property";
						Status = "Pass";
							} else {
						//remarks = "Failed- Actual iSEnabled object Proprty '" +enableProperty + "' is not equal with expacted property" + ExpectedText + "'";
						remarks = "Actual iSEnabled object Proprty '" +enableProperty + "' is not equal with expacted property" + ExpectedText + "'";
						Status = "Fail";
							}
>>>>>>> .r108
				break;
<<<<<<< .mine
			case "name":

				String ActualNameProperty = libraries.SetAppium.driver.findElement(ToBy(identifier)).getAttribute("name");
				if (expectedText.equals(ActualNameProperty))
				{
					remarks = "Passed- Expected name '" + expectedText + "'  of object matched with actual '" + ActualNameProperty + "' property";
				} else {
					remarks = "Failed- Actual name '" + ActualNameProperty + "' of object does not matches with property value '" + expectedText + "'";
				}		
=======
				case "name":
					
					String ActualNameProperty = libraries.SetAppium.driver.findElement(ToBy(identifier)).getAttribute("name");
					
					
					if (ExpectedText.equals(ActualNameProperty))
							{
						//remarks = "Passed- Expected name '" + ExpectedText + "'  of object matched with actual '" + ActualNameProperty + "' property";
						remarks = "Expected name '" + ExpectedText + "'  of object matched with actual '" + ActualNameProperty + "' property";
						Status = "Pass";
							} else {
					//	remarks = "Failed- Actual name '" + ActualNameProperty + "' of object does not matches with property value '" + ExpectedText + "'";
						remarks = "Actual name '" + ActualNameProperty + "' of object does not matches with property value '" + ExpectedText + "'";
						Status = "Fail";	
							}		
>>>>>>> .r108
			}

			//log.info( remarks );
			Report.Remarks(remarks, Status, "");

		} catch (Exception e)
		{
<<<<<<< .mine
			log.info(e.getMessage());
=======
			String remarks = ""; 
			String Status = "";
			
			switch (obJProprtyName.toLowerCase().trim())
			{
				case "name":
					String ObjPropertyValue = libraries.SetAppium.driver.findElement(ToBy(identifier)).getAttribute("name");
					
					Actions.SetVariable(VariableNameToStore, ObjPropertyValue);
				//	remarks = "Passed -'" + ObjPropertyValue + "' value stored in '" + VariableNameToStore + "' variable name";
					remarks = "'" + ObjPropertyValue + "' value stored in '" + VariableNameToStore + "' variable name";
					Status = "Pass";
			}
			
			//log.info( remarks );
			Report.Remarks(remarks, Status, "");
			
>>>>>>> .r108
		}

	}


/**
 * Purpose: Gets the Object Property for identifier which is passed.
 * @param identifier: Pass the Identifier for which Property to be get.
 * @param obJProprtyName: Pass the Attribute value to get.
 * @param VariableNameToStore: Assign the name in which object property value to be saved.
 */
	public static void GetObjectProperty(String identifier, String obJProprtyName, String VariableNameToStore)
	{
		String remarks = ""; 

		switch (obJProprtyName.toLowerCase().trim())
		{
		case "name":
			String ObjPropertyValue = libraries.SetAppium.driver.findElement(ToBy(identifier)).getAttribute("name");

			Actions.SetVariable(VariableNameToStore, ObjPropertyValue);
			remarks = "Passed -'" + ObjPropertyValue + "' value stored in '" + VariableNameToStore + "' variable name";
		}

		log.info( remarks );

	}


	

	/**
	 * Purpose: Get the system Current Date and time.
	 * @return: Returns current system date and time.
	 */
	public static String getCurrentTime()
	{
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH); // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		//int millis = now.get(Calendar.MILLISECOND);
		return String.valueOf(month+1) + "/" + String.valueOf(day) + "/" + String.valueOf(year) + " " 
		+ String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second);

	}


}


