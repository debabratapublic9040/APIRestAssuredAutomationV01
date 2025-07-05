package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	//DataProvider 1
	
	@DataProvider(name="Data")
	public String [][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalRows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getColumnCount("Sheet1",1);
		
		String apiData[][]=new String[totalRows][totalcols];
		
		for(int i=1;i<=totalRows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				apiData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return apiData;
	}
	
	
	@DataProvider(name="UserNames")
	public String [] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalRows=xlutil.getRowCount("Sheet1");
		
		String userName[]=new String[totalRows];
		
		for(int i=1;i<=totalRows;i++)
		{
			userName[i-1]=xlutil.getCellData("Sheet1", i, 1);
		}
		return userName;
	}

}
