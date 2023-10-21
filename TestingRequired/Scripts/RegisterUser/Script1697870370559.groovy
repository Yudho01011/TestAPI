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
import groovy.json.JsonSlurper

//Get email
responBody = WS.sendRequest(findTestObject("SingleUser"))
WS.verifyResponseStatusCode(responBody, 200)


Map user = responBody.getProperties()

def jsonPlur = new JsonSlurper()
def bodyRespon = jsonPlur.parseText(user['responseText'])

GlobalVariable.ID = bodyRespon['data']['id']
GlobalVariable.EMAIL = bodyRespon['data']['email']

Map newRegis = [
	"email" : GlobalVariable.EMAIL,
	"password" : "Paku"]

registerUser = WS.sendRequest(findTestObject('Register successfull', newRegis))
WS.verifyResponseStatusCode(registerUser, 200)

boolean verify = WS.verifyElementPropertyValue(registerUser, "id", GlobalVariable.ID)
if(verify) {
	KeywordUtil.logInfo("Successfully register")
}else {
	KeywordUtil.logInfo("Failed register")
}

