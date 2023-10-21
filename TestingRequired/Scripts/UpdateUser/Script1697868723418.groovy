import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

//set up global variable with value
GlobalVariable.NAME = "Kasim"
GlobalVariable.JOB = "Fisherman"

//create map vor preparation http body
Map newData = [
	"name" : GlobalVariable.NAME,
	"job" : GlobalVariable.JOB
	]

//send request and store new data with map	
updateUser = WS.sendRequest(findTestObject('UpdateUser', newData))

//Verify between respon and input data
boolean verify = WS.verifyElementPropertyValue(updateUser, "name", GlobalVariable.NAME)
if(verify) {
	KeywordUtil.logInfo("Successfully update data")
}else {
	KeywordUtil.logInfo("Failed update data")
}

//get respon ada print it
Map responBody = updateUser.getProperties()
KeywordUtil.logInfo("$responBody")