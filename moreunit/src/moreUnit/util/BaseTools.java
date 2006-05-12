/*
 * Created on 20.02.2005 20:03:01
 * Created by Vera
 */
package moreUnit.util;

import moreUnit.MoreUnitPlugin;
import moreUnit.log.LogHandler;

import org.eclipse.core.resources.IFile;


public class BaseTools {
	
	/**
	 * This method returns the name of the testmethod for a given
	 * <code>methodName</code>. Only a prefix is used.<br>
	 * Example:<br>
	 * foo: testFoo
	 * 
	 * @param methodName
	 * @return
	 */
	public static String getTestmethodNameFromMethodName(String methodName) {
		if(methodName == null || methodName.length() == 0) {
			LogHandler.getInstance().handleWarnLog("Methodname is null or has length of 0");
			return MagicNumbers.EMPTY_STRING;
		}
		
		String firstChar = String.valueOf(methodName.charAt(0));
		methodName = methodName.replaceFirst(firstChar, firstChar.toUpperCase());
		
		String testMethodName = MagicNumbers.TEST_METHOD_PRAEFIX+methodName;
		
		return testMethodName;
	}
	
	/**
	 * Example:<br>
	 * methodNameBeforeRename: countMembers<br>
	 * methodNameAfterRename: countAllMembers<br>
	 * testMethodName: testCountMemberSpecialCase<br>
	 * returns testCountAllMemberSpecialCase
	 * 
	 * @param methodNameBeforeRename
	 * @param methodNameAfterRename
	 * @param testMethodName
	 * @return Name of testmethod performed with the same rename as the tested method
	 */
	public static String getTestMethodNameAfterRename(String methodNameBeforeRename, String methodNameAfterRename, String testMethodName) {
		String[] prefixAndSuffix = testMethodName.split(getStringWithFirstCharToUpperCase(methodNameBeforeRename));
		
		if(prefixAndSuffix.length == 0)
			return null;
		
		String prefix = prefixAndSuffix[0];
		String suffix = MagicNumbers.EMPTY_STRING;
		
		if(prefixAndSuffix.length > 1)
			suffix = prefixAndSuffix[1];
		
		return prefix+getStringWithFirstCharToUpperCase(methodNameAfterRename)+suffix;
	}
	
	/**
	 * Returns the same string and ensures that the first char of the {@link String}
	 * is an uppercase letter.<br>
	 * Example:<br>
	 * test: Test
	 * 
	 * @param string
	 * @return
	 */
	public static String getStringWithFirstCharToUpperCase(String string) {
		if(string == null || string.length() == 0)
			return string;
		
		char firstChar = string.charAt(0);
		StringBuffer result = new StringBuffer();
		result.append(Character.toUpperCase(firstChar));
		result.append(string.substring(1));
		
		return result.toString();
	}

	/**
	 * This method identifies the name of the class under test for
	 * a given name of a testcase.<br>
	 * Example (with "Test" configured as testcase suffix):<br>
	 * HelloWorldTest: HelloWorld
	 * 
	 * @param testCaseClass name of the testcase
	 * @return name of the corresponding class under test
	 */
	public static String getTestedClass(String testCaseClass) {
		if(testCaseClass == null || testCaseClass.length() < 4 || !testCaseClass.endsWith(getTestcaseSuffixFromPreferences()))
			return null;

		int lengthOfTestcaseSuffix = getTestcaseSuffixFromPreferences().length();
		return testCaseClass.substring(0, testCaseClass.length()-lengthOfTestcaseSuffix);
	}
	
	/**
	 * This method identifies the name of the tested method for a given testmethod.<br>
	 * Example:<br>
	 * testFoo: foo
	 * testFooSuffix: fooSuffix
	 * 
	 * @param testMethodName name of the testmethod
	 * @return name of the method which is tested
	 */
	public static String getTestedMethod(String testMethodName) {
		if(testMethodName == null || !testMethodName.startsWith(MagicNumbers.TEST_METHOD_PRAEFIX))
			return null;
		
		char erstesZeichen = testMethodName.charAt(4);
		StringBuffer result = new StringBuffer();
		result.append(Character.toLowerCase(erstesZeichen));
		result.append(testMethodName.substring(5));
		return result.toString();
	}
	
	public static String getNameOfTestCaseClass(IFile classToTest) {
		String fileNameWithoutExtension = classToTest.getName().replaceFirst(MagicNumbers.STRING_DOT+classToTest.getFileExtension(), MagicNumbers.EMPTY_STRING);
		return fileNameWithoutExtension+getTestcaseSuffixFromPreferences();
	}
	
	public static String getNameOfTestCaseClass(String classname) {
		String fileNameWithoutExtension = classname.replaceFirst(".java", MagicNumbers.EMPTY_STRING);
		return fileNameWithoutExtension + getTestcaseSuffixFromPreferences() + ".java";
	}
	
	private static String getTestcaseSuffixFromPreferences() {
		return MoreUnitPlugin.getDefault().getTestcaseSuffixFromPreferences();
	}
}

// $Log: not supported by cvs2svn $
// Revision 1.6  2006/04/14 17:11:11  gianasista
// Suffix for testcasename ist configurable (+Tests)
//
// Revision 1.5  2006/02/19 21:48:29  gianasista
// New method
//
// Revision 1.4  2006/01/31 19:35:35  gianasista
// Methods are now null-save and have tests.
//
// Revision 1.3  2006/01/19 21:38:32  gianasista
// Added CVS-commit-logging to all java-files
//