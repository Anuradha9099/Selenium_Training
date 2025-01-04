package com.datadriven.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="UserNames")
    public String[] getUsernames() throws IOException{
        String path=System.getProperty("user.dir")+"/testData/username_data.xlsx";
        XLUtility xl=new XLUtility(path);

        int rownum=xl.getRowCount("Sheet1");

        String apidata[]=new String[rownum];

        for(int i=1; i<=rownum;++i){
                apidata[i-1]=xl.getCellData("Sheet1",i,0);
        }
        return apidata;
    }
}
