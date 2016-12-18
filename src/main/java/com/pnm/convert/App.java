package com.pnm.convert;

import java.io.File;
import java.io.IOException;
import java.sql.SQLType;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.PropertyMap;
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
	        
	        
	        db =DatabaseBuilder.open(new File("D:\\Repo\\REPORT2000.mdb"));
	        
	       Set<String> setTables= db.getTableNames();
	       StringBuilder str_create_structure = new StringBuilder();
	       StringBuilder str_insert = new StringBuilder();
	       
	       Iterator<String> iterator = setTables.iterator();
	       while(iterator.hasNext()){
	    	   String tableName = iterator.next();
	    	   if(!tableName.equals("Forms")){
	    		   Table table = db.getTable(tableName);
		    	   String tmp =createTableStructure(table);
		    	   str_create_structure.append(tmp +"\n");
		    	   String insert = convertData(table);		    	    
		    	   FileUtils.writeStringToFile(new File("D:\\convert_result\\insert_"+table.getName()+".txt"), insert);		    	   
	    	   }
	    	   
	       }

	       
	       FileUtils.writeStringToFile(new File("D:\\convert_result\\create_all_tbl.txt"), str_create_structure.toString());
	       
/*	       String insert = convertData(db.getTable("Forms"));
	        System.out.println(insert);*/
	        
	       
}
	 public static String createTableStructure(Table table){
		 String sql_create_table = "CREATE TABLE ";
		 StringBuilder st = new StringBuilder();
		 st.append(sql_create_table);
		 st.append(table.getName() +" (");
		 
		 
		 for(Column col:table.getColumns()){
			 /*System.out.println("colname = "+col.getName());
			 System.out.println("col type = "+col.getType());*/
			 try{
			 /*System.out.println("col type = "+col.getType().getSQLType());*/
			 }catch(Exception e){
				 
			 }
			 st.append(col.getName());
			 
			 try{			 
				 //System.out.println(col.getType());
				 switch (col.getType().getSQLType()) {
				case 4: // LONG  --> bigint
					st.append(" bigint, ");
					
					break;
				case 0: //boolean ---> bit
					st.append(" bit ,");
					
					break;
					
				case 3 : // int	---> int
					st.append(" int ,");
					//st.append("(" + col.getLengthInUnits()+" ),");					
					break;
				case 5 : // int	---> int
					st.append(" int ,");
					//st.append("(" + col.getLengthInUnits()+" ),");					
					break;	
					
				case 12: // text ---> varchar
					st.append(" text ,");
					
					break;
				case 93: // Short_date_time -->> timestamp	
					st.append(" datetime ,");
					break;
				default:
					st.append(" text , ");
									
					break;
				}
				 
				
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
			 
			 
			 
		 }
		 String query = st.substring(0, st.length()-1) +");";
		 
		 //System.out.print(query);
		 return query;
		 
	 }
	 
	 public static String convertData(Table table){
		 String sql_create_table = "INSERT INTO  ";
		 StringBuilder st = new StringBuilder();
		 st.append(sql_create_table);
		 st.append(table.getName() +" (");
		 
	        
		          //System.out.println(row);
		          for(Column column : table.getColumns()) {
			            String columnName = column.getName();
			            st.append(columnName+",");
			          }
		          
		 String stm1 = st.substring(0, st.length()-1)+") values ( ";

		 
	     int rowcout = table.getRowCount();
	     int colcount = table.getColumnCount();
	     

	     System.out.println(rowcout);
	     System.out.println(colcount);
	     SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zz yyyy");
	     SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     
	     StringBuilder sttmp=new StringBuilder();
	     for(Row row :table){
	    	 sttmp.append(stm1);
	    	 String strow="";
	    	 for(Column col:table.getColumns()){	    		 
	    		 String colname = col.getName();
	    		 Object val =row.get(colname);
	    		 try{			 
					 //System.out.println(col.getType());
					 switch (col.getType().getSQLType()) {
					case 4: // LONG  --> bigint
						if(val!=null){
							strow = strow+ val.toString()+",";
						}else{
							strow = strow+"0,";
						}
						
						break;
					case 0: //boolean ---> bit
						if(val!=null){
							strow = strow+ val.toString()+",";
						}else{
							strow = strow+"0,";
						}
						
						break;
						
					case 3 : // int	---> int
						if(val!=null){
							strow = strow+ val.toString()+",";
						}else{
							strow = strow+"0,";
						}					
						break;
					case 5 : // int	---> int
						if(val!=null){
							strow = strow+ val.toString()+",";
						}else{
							strow = strow+"0,";
						}					
						break;	
						
					case 12: // text ---> varchar
						if(val!=null){
							strow = strow+ "'"+val.toString()+"'"+",";
						}else{
							strow = strow+"' ',";
						}
						break;
					case 93: // Short_date_time -->> timestamp	
						if(val!=null){
							try{
								Date d = sdf.parse(val.toString());
								strow = strow+ "'"+sdf2.format(d)+"'"+",";
							}catch(Exception ee){
								strow = strow+ "' '"+",";
							}
							
						}else{
							strow = strow+"' ',";
						}
						break;
					default:
						//st.append(" default ,");
						if(val!=null){
							strow = strow+ "'"+val.toString()+"'"+",";
						}else{
							strow = strow+"' ',";
						}
						break;
					}
					 
					
				 }catch(Exception e){
					 e.printStackTrace();
				 }
	    		 
	    		 
	    	 }
	    	 strow = strow.substring(0, strow.length()-1);
	    	 sttmp.append(strow+");\n");
	    	 //System.out.println(sttmp.toString());
	    	 
	     }
	     //System.out.println(sttmp.toString());
	
		 
		 return sttmp.toString();
		 
	 }
	 
	 
	 
	 
}
