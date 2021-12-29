package com.pom.utilities;

//import org.apache.poi.xssf.usermodel.XSSFDateUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.*;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.*;

public class ExcelApiTest4 {
 
	public FileInputStream fis = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
    public FileOutputStream fout=null;
    

    
    public static void main (String args[])
  {
    	
    	try {
        
    	int RowCount;
    	
    	ExcelApiTest4 eat=new ExcelApiTest4();
      
        RowCount=eat.getRowCount("C:\\Users\\sayantan_bose\\Desktop\\TC02_Photo_add.xlsx","Sheet1");
 		System.out.println("Row Count  :"+RowCount);

 		
       }catch(Exception e) {
    	  System.out.println(e.getMessage());
     }  
    	
 }
    
    
    public void clearsheetdata(String xlFilePath, String sheetName) throws Exception
    {
    	

    	fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        
        for(int i=workbook.getNumberOfSheets()-1;i>=0;i--){
            XSSFSheet tmpSheet =workbook.getSheetAt(i);
            if(tmpSheet.getSheetName().equals(sheetName)){
            	workbook.removeSheetAt(i);
            }
        }       

        
        fout= new FileOutputStream(xlFilePath);
    	workbook.write(fout);
     
        fout.flush();
        fout.close();
        workbook.close();
        fis.close();
        
        
    	fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        workbook.createSheet(sheetName);
        
        fout= new FileOutputStream(xlFilePath);
    	workbook.write(fout);
     
        fout.flush();
        fout.close();
        workbook.close();
        fis.close();
        
        
        
    }
    

    
    public void CopyWorkBook(String xlFilePathcurr,String xlFilePathnew) throws Exception
    {
    	//input source excel file which contains sheets to be copied
    	System.out.println("started : ");
    	fis = new FileInputStream(xlFilePathcurr);
    	workbook = new XSSFWorkbook(fis);
    	
    	XSSFWorkbook workbookoutput=workbook;
    	
    	//fout= new FileOutputStream(xlFilePath);  
    	fout= new FileOutputStream(xlFilePathnew);
    	workbookoutput.write(fout);

    	//output new excel file to which we need to copy the above sheets
    	//this would copy entire workbook from source
    	//XSSFWorkbook workbookoutput=workbook;
    	

        fout.flush();
        fout.close();
        workbook.close();
        fis.close();
        
    	System.out.println("completed : ");
    }
    
    
  //getRow count starts
 
    public int getRowCount(String xlFilePath, String sheetName) throws Exception
    {
    	
        fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
       
       
       // int colNum = row.getLastCellNum();
      //  System.out.println("Total Number of Columns in the excel is : "+colNum);
        int RowNum = sheet.getLastRowNum()+1;
        System.out.println("Total Number of Rows in the excel is : "+RowNum);
        
        workbook.close();
    	fis.close();
        
        return RowNum;
        
    
    }
    
    //getRow count ends
    
    
    
    //getColumn count starts
   
      public int getColumnCount(String xlFilePath, String sheetName) throws Exception
      {
      	
          fis = new FileInputStream(xlFilePath);
          workbook = new XSSFWorkbook(fis);
          sheet = workbook.getSheet(sheetName);
          row = sheet.getRow(0);
         
         
         int colNum = row.getLastCellNum();
         System.out.println("Total Number of Columns in the excel is : "+colNum);
        // int RowNum = sheet.getLastRowNum()+1;
        //  System.out.println("Total Number of Rows in the excel is : "+RowNum);
          
         workbook.close();
      	 fis.close();
          
          return colNum;
          
      
      }
      
      //getColumn count ends
    
    
 
    public synchronized String getCellData(String xlFilePath, String sheetName,int rowNum,int column) throws Exception
    {
    	fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(column);
       
        if(cell.getCellTypeEnum() == CellType.STRING)
        {	
        	String str6=cell.getStringCellValue();
        	workbook.close();
        	fis.close();
            return str6;
        }
        else if(cell.getCellTypeEnum() == CellType.NUMERIC)
        {	
        	//int str6=cell.getStringCellValue();
        	int i = (int)cell.getNumericCellValue(); 
        	String str6 = String.valueOf(i); 
        	workbook.close();
        	fis.close();
            return str6;
        }
        
        
        else 
        {
        	String str6=cell.getStringCellValue();
        	workbook.close();
        	fis.close();
            return str6;
        }
        
    } 
    
    
    
    public  void PutCellData(String xlFilePath,String sheetName,int rowNum,int column,String Text)
    		throws Exception
    {
    	FileInputStream fis = null;
	   	XSSFWorkbook workbook = null;
	   	XSSFSheet sheet = null;
	   	XSSFRow row = null;
	    XSSFCell cell = null;
	   	FileOutputStream fout=null;
   	 
   	 	fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
    	sheet = workbook.getSheet(sheetName);
    	
    	if(sheet.getRow(rowNum)==null)
    	{
    		row=sheet.createRow(rowNum);
    	}
    	else
    	{
    		row=sheet.getRow(rowNum);
    	}
    	
    	
    	if(row.getCell(column)==null)
    	{
    		cell=row.createCell(column);
    	}
    	else
    	{
    		cell=row.getCell(column);
    	}
    	
    	//cell.getSheet();
    	//Cell cell = null; // declare a Cell object
    	
    	// Access the second cell in second row to update the value
    	
    	
    	cell = sheet.getRow(rowNum).getCell(column);  
    	cell.setCellValue(Text);
    	
    	/*
    	CellStyle backgroundStyle = workbook.createCellStyle();
        backgroundStyle.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        backgroundStyle.setBottomBorderColor(IndexedColors.RED.getIndex());
         cell.setCellStyle(backgroundStyle);
      */
         
         CellStyle cs1 = workbook.createCellStyle(); 
         cs1.setFillForegroundColor(IndexedColors.WHITE.getIndex()); 
         cs1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
     
         Font font = workbook.createFont();
         font.setColor(IndexedColors.BLUE.getIndex());
         font.setBold(false);
         cs1.setFont(font);
   
    	
    	System.out.println("Text:"+Text);
    	cell.setCellStyle(cs1);
    	
    	cell.setCellValue(Text);
    	
    	

    	//fout= new FileOutputStream(xlFilePath); 
    	
    	fout= new FileOutputStream(xlFilePath);
    	workbook.write(fout);
     
        fout.flush();
        fout.close();
        workbook.close();
        fis.close();
        
    }
    
    
    public  void PutCellFormula(String xlFilePath,String sheetName,int rowNum,int column,String Text)throws Exception
    {
    	 FileInputStream fis = null;
       	 XSSFWorkbook workbook = null;
       	 XSSFSheet sheet = null;
         XSSFRow row = null;
         XSSFCell cell = null;
       	 FileOutputStream fout=null;
       	 
         fis = new FileInputStream(xlFilePath);
            workbook = new XSSFWorkbook(fis);
        	sheet = workbook.getSheet(sheetName);
        	
        	CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle hlink_style = workbook.createCellStyle();
            Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);
            link.setAddress(Text);
        	
        	if(sheet.getRow(rowNum)==null)
        	{
        	row=sheet.createRow(rowNum);
        	}
        	else
        	{
        		row=sheet.getRow(rowNum);
        	}
        	
        	
        	
        	
        	if(row.getCell(column)==null)
        	{
        	cell=row.createCell(column);
        	}
        	else
        	{
        		cell=row.getCell(column);
        	}
        	
        	//cell.getSheet();
        	//Cell cell = null; // declare a Cell object
        	cell = sheet.getRow(rowNum).getCell(column);   // Access the second cell in second row to update the value
        	cell.setCellValue(Text);
        	
        //	System.out.println("Text:"+Text);
        	
        	cell.setHyperlink(link);
            cell.setCellStyle(hlink_style);
        	cell.setCellValue(Text);

        	//fout= new FileOutputStream(xlFilePath);  
        	fout= new FileOutputStream(xlFilePath);
        	workbook.write(fout);
         
            fout.flush();
            fout.close();
            workbook.close();
            fis.close();
         

    }
    
    
    public  void PassPutCellData(String xlFilePath,String sheetName,int rowNum,int column,String Text)throws Exception
    {
    	
     FileInputStream fis = null;
   	 XSSFWorkbook workbook = null;
   	 XSSFSheet sheet = null;
   	 XSSFRow row = null;
     XSSFCell cell = null;
   	 FileOutputStream fout=null;
   	 
    fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
    	sheet = workbook.getSheet(sheetName);
    	
    	if(sheet.getRow(rowNum)==null)
    	{
    	row=sheet.createRow(rowNum);
    	}
    	else
    	{
    		row=sheet.getRow(rowNum);
    	}
    	
    	
    	
    	
    	if(row.getCell(column)==null)
    	{
    	cell=row.createCell(column);
    	}
    	else
    	{
    		cell=row.getCell(column);
    	}
    	
    	//cell.getSheet();
    	//Cell cell = null; // declare a Cell object
    	cell = sheet.getRow(rowNum).getCell(column);   // Access the second cell in second row to update the value
    	cell.setCellValue(Text);
    	         
         CellStyle cs1 = workbook.createCellStyle(); 
         cs1.setFillForegroundColor(IndexedColors.GREEN.getIndex()); 
         cs1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
         //cs1.setFillPattern(CellStyle.SOLID_FOREGROUND); 
         
         Font font = workbook.createFont();
         font.setColor(IndexedColors.WHITE.getIndex());
         font.setBold(true);
         cs1.setFont(font);
   
    	
    	System.out.println("Text:"+Text);
    	cell.setCellStyle(cs1);
    	cell.setCellValue(Text);

    	//fout= new FileOutputStream(xlFilePath);  
    	fout= new FileOutputStream(xlFilePath);
    	workbook.write(fout);
     
        fout.flush();
        fout.close();
        workbook.close();
        fis.close();
        
        
    } 
    
    
    
    public  void FailPutCellData(String xlFilePath,String sheetName,int rowNum,int column,String Text)throws Exception
    {
    	
     FileInputStream fis = null;
   	 XSSFWorkbook workbook = null;
   	 XSSFSheet sheet = null;
   	 XSSFRow row = null;
     XSSFCell cell = null;
   	 FileOutputStream fout=null;
   	 
     fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
    	sheet = workbook.getSheet(sheetName);
    	
    	if(sheet.getRow(rowNum)==null)
    	{
    	row=sheet.createRow(rowNum);
    	}
    	else
    	{
    		row=sheet.getRow(rowNum);
    	}
    	
    	
    	
    	
    	if(row.getCell(column)==null)
    	{
    	cell=row.createCell(column);
    	}
    	else
    	{
    		cell=row.getCell(column);
    	}
    	
    	//cell.getSheet();
    	//Cell cell = null; // declare a Cell object
    	cell = sheet.getRow(rowNum).getCell(column);   // Access the second cell in second row to update the value
    	cell.setCellValue(Text);
    	 
         CellStyle cs1 = workbook.createCellStyle(); 
         cs1.setFillForegroundColor(IndexedColors.RED.getIndex()); 
         cs1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
         //cs1.setFillPattern(CellStyle.SOLID_FOREGROUND); 
         
         Font font = workbook.createFont();
         font.setColor(IndexedColors.WHITE.getIndex());
         font.setBold(true);
         cs1.setFont(font);
   
    	
    	System.out.println("Text:"+Text);
    	cell.setCellStyle(cs1);
    	cell.setCellValue(Text);

    	//fout= new FileOutputStream(xlFilePath);  
    	fout= new FileOutputStream(xlFilePath);
    	workbook.write(fout);
     
        fout.flush();
        fout.close();
        workbook.close();
        fis.close();
        
     
    }
    
  
    ////////////////////// Data Provider starts///////////////////////////////////////
    
    
    public  Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   
    	 
        
      String[][] tabArray = null;
        
      try { 
     
        FileInputStream fis = new FileInputStream(FilePath);
     
        // Access the required test data sheet
     
        //ExcelWBook = new HSSFWorkbook(ExcelFile);
        workbook = new XSSFWorkbook(fis);
     
       // ExcelWSheet = ExcelWBook.getSheet(SheetName);
        sheet = workbook.getSheet(SheetName);
        
      //  System.out.println("FilePath3 :"+FilePath);
    	//System.out.println("SheetNam3 :"+SheetName);
    	System.out.println("Sheet 1 ,1 value :" +getCellData(1,1));
     
        int startRow = 1;
     
        int startCol = 0;
     
        int ci,cj;
     
        int totalRows = sheet.getLastRowNum();
        int totalCols = getColumnCount(FilePath,SheetName);
        
        System.out.println("totalRows0 :"+totalRows);
        System.out.println("totalCols0 :"+totalCols);
     
        tabArray=new String[totalRows][totalCols];
     
     
         ci=0;
        
        for (int i=startRow;i<=totalRows;i++, ci++) {              
     
         cj=0;
     
        for (int j=startCol;j<totalCols;j++, cj++){
     
        tabArray[ci][cj]=getCellData(i,j);
     // System.out.println(tabArray[ci][cj]);  
   }
 }
     
 }catch (FileNotFoundException e){
     
     System.out.println("File not Found Exception");
     e.printStackTrace();
     
   }
     
  catch (IOException e){
     
     System.out.println("Could not read the Excel sheet");
     e.printStackTrace();
     
   }
     
   return(tabArray);
     
 }
     
public  String getCellData(int RowNum, int ColNum) throws Exception {
     
	try{
   	 
 	   cell = sheet.getRow(RowNum).getCell(ColNum);
		 
		 // String CellData = cell.getStringCellValue();
 	   if(cell.getCellTypeEnum() == CellType.STRING)
        {	
        	   String str6=cell.getStringCellValue();
        	   workbook.close();
        	   fis.close();
               return str6;
        }
        
        else if(cell.getCellTypeEnum() == CellType.NUMERIC)
        {	
        	
        	   int i = (int)cell.getNumericCellValue(); 
        	   String str6 = String.valueOf(i); 
        	   workbook.close();
        	   fis.close();
               return str6;
        }
        else 
        {
        	    String str6=cell.getStringCellValue();
        	    workbook.close();
             	fis.close();
                return str6;
        } 
	
        }catch (Exception e){
  
           System.out.println(e.getMessage());
           throw (e);
           
        }
     }
     
  }
 

 
     //////////////////////////////////////Data Provider ends//////////////////////
    
    

