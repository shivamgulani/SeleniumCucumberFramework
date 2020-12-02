package com.testautomation.TestRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;

public class Runner {
	
	
	public static String Feature_File_To_Execute_Path="";

	
	public static HashMap<Integer,TestClassTemplate>Execution_TestCases=new HashMap<Integer,TestClassTemplate>();
	
	public static Logger logger=org.apache.logging.log4j.LogManager.getLogger(Runner.class);
	
	public static void main(String args[])
	{
		try
		{
		FileInputStream fis=new FileInputStream(new File("C:\\Users\\Shivam Gulani\\Desktop\\Framework\\TestScript.xlsx"));
		XSSFWorkbook test_script_workbook= new XSSFWorkbook(fis);
		XSSFSheet test_script_worksheet=test_script_workbook.getSheet("TestScript");
		
		logger.error("--- Execution Started ---");
		
		System.out.println(test_script_worksheet.getLastRowNum());
		
		int counter=1;
		for(int i=1;i<=test_script_worksheet.getLastRowNum();i++)
		{
			XSSFRow row=test_script_worksheet.getRow(i);
			String TestCaseName=row.getCell(0).getStringCellValue();
			String BDD=row.getCell(1).getStringCellValue();
			String FeatureFile=row.getCell(2).getStringCellValue();
			String Execute=row.getCell(3).getStringCellValue();
			String DataSheet=row.getCell(4).getStringCellValue();
			String Scenario=row.getCell(5).getStringCellValue();
			
			if(Execute.equalsIgnoreCase("YES"))
			{
				TestClassTemplate objExec=new TestClassTemplate();
				objExec.setBDD(BDD);
				objExec.setDataSheet(DataSheet);
				objExec.setFeatureFile(FeatureFile);
				objExec.setTestCaseName(TestCaseName);
				objExec.setScenario(Scenario);
				Execution_TestCases.put(counter, objExec);
				counter++;
			}
			
			
			
			System.out.println(TestCaseName+" "+BDD+" "+FeatureFile+" "+Execute+" "+DataSheet+" "+Scenario);
		}
		for(int i=1;i<=Execution_TestCases.size();i++)
		{
			System.out.println(Execution_TestCases.get(i).getTestCaseName());
		}
		
		File directoryPathofFeatures = new File(System.getProperty("user.dir")+"\\FrameWorkGeneratedFeature");
	      //List of all files and directories
	      String featurescontents[] = directoryPathofFeatures.list();
	      
	      if(featurescontents.length>0)
	      {
	    	  for(int i=0; i<featurescontents.length; i++) {
	    	  
	    		  Path  path = FileSystems.getDefault().getPath(directoryPathofFeatures+"\\"+featurescontents[i]);
	    	  Files.deleteIfExists(path);
	    	  }
	      }
		
		System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\io\\cucumber\\skeleton");
		String final_feature_file="Feature: 1";
		String FeatureFileDirectory=System.getProperty("user.dir")+"\\features";
		File directoryPath = new File(System.getProperty("user.dir")+"\\features");
	      //List of all files and directories
	      String contents[] = directoryPath.list();
	      int scenario_worksheet_counter=1;
	      System.out.println("List of files and directories in the specified directory:");
	      for(int i=0; i<contents.length; i++) {
	    	  String data ="";
	         System.out.println(contents[i]);
	         try {
	             File myObj = new File(FeatureFileDirectory+"\\"+contents[i]);
	             System.out.println("Reading File -- > "+FeatureFileDirectory+"\\"+contents[i]);
	             Scanner myReader = new Scanner(myObj);
	             while (myReader.hasNextLine()) {
	               data = data+ "\r\n" +myReader.nextLine();
	             }
	             System.out.println(data);
	             
	             String arr[]=data.split("#");
	             if(arr.length>0)
	             {
	            	 for(int k=0;k<arr.length;k++)
	            	 {
	            		 System.out.println("This is split of actual feature file - > "+arr[k]);
	            		 Pattern pattern = Pattern.compile("Scenario:(.*?)Given", Pattern.DOTALL);
         				Matcher matcher = pattern.matcher(arr[k]);
         				String Feature_File_Scenario="";
         				while (matcher.find()) {
         					Feature_File_Scenario=matcher.group(1);
         				    System.out.println("Found "+Feature_File_Scenario);
         				}
	            		 for(int x=1;x<=Execution_TestCases.size();x++)
	            			{
	            			 
	            			 if(scenario_worksheet_counter>Execution_TestCases.size())
	            				 break;
	            			 else
	            			 {
	            			 System.out.println(i+" "+Execution_TestCases.get(scenario_worksheet_counter).getScenario().trim());
	            				if(Feature_File_Scenario.trim().equalsIgnoreCase(Execution_TestCases.get(scenario_worksheet_counter).getScenario().trim()))
	            				{
	            					System.out.println("match found");
//	            					if(scenario_worksheet_counter==Execution_TestCases.size())
//	            						break;
//	            					else
//	            					scenario_worksheet_counter++;
	            					System.out.println("ARR[K] is -> "+arr[k].trim());
	            					
	            					final_feature_file=final_feature_file+"\r\n"+ "@TestAutomation\r\n"+arr[k].trim()+"\r\n";
	            					scenario_worksheet_counter++;
	            				}
	            				
	            			 }
	            				
	            				
	            			}
	            		 }
	            		  
	            		
	            	 
	            	 
	             }
	             
	             
	             myReader.close();
	           } catch (FileNotFoundException e) {
	             System.out.println("An error occurred.");
	             e.printStackTrace();
	           }
	      }
		
	      
	      System.out.println("This is final Feature file ");
	      System.out.println();
	      System.out.println(final_feature_file);
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");  
	      LocalDateTime now = LocalDateTime.now();  
	      System.out.println(dtf.format(now));  
	      FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"\\FrameWorkGeneratedFeature\\Feature.feature");
	      myWriter.write(final_feature_file);
	      myWriter.close();
	      Feature_File_To_Execute_Path=System.getProperty("user.dir")+"\\FrameWorkGeneratedFeature\\Feature.feature";
	      System.out.println("Successfully wrote to the file.-> "+Feature_File_To_Execute_Path);
	      
	      
	      TestNG runner=new TestNG();
	
	   List<String> suitefiles=new ArrayList<String>();
	  
	   suitefiles.add(System.getProperty("user.dir")+"\\testng.xml");
	    
	 
	   runner.setTestSuites(suitefiles);
	  
	   runner.run();
	      
//	      TestListenerAdapter tla = new TestListenerAdapter();
//	      TestNG testng = new TestNG();
//	      List<String> suites = Lists.newArrayList();
//	      suites.add("C:\\Users\\Shivam Gulani\\Desktop\\cucumber-java-skeleton-main\\testng.xml");//path to xml..
//	    
//	      testng.setTestSuites(suites);
//	      testng.run();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
