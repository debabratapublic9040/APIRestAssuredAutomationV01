
package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
	public  XSSFSheet ws;
	public  XSSFRow row;
	public  XSSFCell cell;
	public  CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String xlSheet) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheet);
		int rowCount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public int getColumnCount(String xlSheet,int rowNum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheet);
		row=ws.getRow(rowNum);
		int columnCount=row.getLastCellNum();
		wb.close();
		fi.close();
		return columnCount;
	}
	
	public  String getCellData(String xlSheet,int rowNum,int columnNum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheet);
		row=ws.getRow(rowNum);
		cell=row.getCell(columnNum);
		
		String data;
		try
		{
		//data=cell.toString();//1st option to format data
			
		//2nd option to format data	
		DataFormatter formatter=new DataFormatter();
		data=formatter.formatCellValue(cell);// Returns the formatted value of cell as a string regardless of cell type
		}
		catch(Exception e)
		{
			data="";
		}
		wb.close();
		fi.close();
		return  data;
	}
	
	public  void fillGreenColour(String xlSheet,int rowNum,int columnNum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheet);
		row=ws.getRow(rowNum);
		cell=row.getCell(columnNum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
	}
	
	public void fillRedColour(String xlSheet,int rowNum,int columnNum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheet);
		row=ws.getRow(rowNum);
		cell=row.getCell(columnNum);
		
		style = wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
	}
	
	public void setCellData(String xlSheet,int rowNum,int column,String data) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheet);
		row=ws.getRow(rowNum);
		cell=row.createCell(column);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
