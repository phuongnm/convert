package com.pnm.convert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
/*    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        
        
        
        
    }*/
	
	
	
	 public static void main(String[] args) throws IOException
	    {
	        Database db = null;
	        TableBuilder mytabbuild = null;
	        Table mytable = null;
	        
	        /*Table table = DatabaseBuilder.open(new File("D:\\Repo\\REPORT2000.mdb")).getTable("Brick");
	        for(Row row : table) {
	          System.out.println(row);
	          for(Column column : table.getColumns()) {
		            String columnName = column.getName();
		            Object value = row.get(columnName);
		            System.out.println("Column " + columnName + "(" + column.getType() + "): " 
		                               + value );
		          }
	          
	        }
	        */
	       Set<String> setTables= DatabaseBuilder.open(new File("D:\\Repo\\REPORT2000.mdb")).getTableNames();
	       for(String s: setTables){
	    	   System.out.println(s);
	    	   Table table = DatabaseBuilder.open(new File("D:\\Repo\\REPORT2000.mdb")).getTable(s);
	    	   createTableStructure(table);
	    	   
	    	   convertData(table);
	    	   
	       }
	        
	        
	        
	        
	       
}
	 public static void createTableStructure(Table table){
		 
	 }
	 
	 public static void convertData(Table table){
		 
	 }
	 
	 
}
