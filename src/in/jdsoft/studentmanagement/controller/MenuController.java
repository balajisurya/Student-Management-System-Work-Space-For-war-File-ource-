package in.jdsoft.studentmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import in.jdsoft.studentmanagement.database.DBConnection;
import in.jdsoft.studentmanagement.model.Menu;

public class MenuController{

	static ServletContext sc;
	
	
	 public void getDbAccess(ServletContext sc){
		 MenuController.sc=sc;
	 }

	 
	public List<Menu> getMenuList(String UserRole) {
		
		ArrayList<Menu> menucontent=new ArrayList<Menu>();
		Multimap<Integer, Menu> menuArray=ArrayListMultimap.create();
		HashSet<Integer> menuCount= new HashSet<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			DBConnection sctest=(DBConnection) sc.getAttribute("dbConn");
			conn=sctest.getDBConnection();
			stmt = conn.createStatement();
		    rs = stmt.executeQuery("select * from tbl_menu_level1");
		    
		    
		    while(rs.next()){
		    		if(rs.getString("user_type")!="" && rs.getString("user_type").contains(UserRole)){
			    		Menu menuobj=new Menu();
				    	menuobj.setMenuId(rs.getInt("level1_id"));
				    	menuobj.setMenuName(rs.getString("menu_name"));
				    	menuobj.setMenuOrder(rs.getInt("order_no"));
				    	menuobj.setMenuLink(rs.getString("link"));
				    	menuobj.setMenuUser(rs.getString("user_type"));
				    	menuobj.setMenuIcon(rs.getString("icon"));
				    	menuArray.put(rs.getInt("order_no"),menuobj);
				    	menuCount.add(rs.getInt("order_no"));		
		          }          
		      }
		    
		    	Iterator<Integer> itr=menuCount.iterator();
		    	while(itr.hasNext()){
		    		List<Menu> menu= (List<Menu>) menuArray.get(itr.next());	
		    		
		    		for(Menu m:menu){
		    			menucontent.add(m);
		    		}	
		    	}
	    		    
		}catch(Exception e){
			
			System.out.print("In getMenu :"+e);
		}
		finally {
			try{
				conn.close();
				stmt.close();
				rs.close();
		
			}catch(Exception e){
				
			}
		}
		 return menucontent;
	}
	
	
	public List<Menu> getSubMenuList(int menuId,String submenuUser) {
	
		Multimap<Integer, Menu> subMenuArray=ArrayListMultimap.create();
		HashSet<Integer> submenuCount= new HashSet<>();
		ArrayList<Menu> submenucontent=new ArrayList<Menu>();
		Connection subMenuconn=null;
		PreparedStatement submenu=null;
		ResultSet submenures=null;
		try{
			DBConnection subMenuSc=(DBConnection) sc.getAttribute("dbConn");
			subMenuconn=subMenuSc.getDBConnection();
			submenu=subMenuconn.prepareStatement("SELECT * FROM tbl_menu_level2 where level1_id=?");
			submenu.setInt(1,menuId);
			submenures=submenu.executeQuery();
			   while(submenures.next()){
				   if(submenures.getString("user_type")!="" && submenures.getString("user_type").contains(submenuUser)){
					   Menu submenuobj=new Menu();
					   submenuobj.setSubMenuId(submenures.getInt("level2_id"));
					   submenuobj.setSubMenuName(submenures.getString("menu_name"));
					   submenuobj.setSubMenuOrder(submenures.getInt("order_no"));
					   submenuobj.setSubMenuLink(submenures.getString("link"));
					   submenuobj.setSubMenuUser(submenures.getString("user_type"));
					   subMenuArray.put(submenures.getInt("order_no"),submenuobj);
					   submenuCount.add(submenures.getInt("order_no"));
				   }
	 
	           }
			   
			   Iterator<Integer> submenuIterator=submenuCount.iterator();
		    	while(submenuIterator.hasNext()){
		    		List<Menu> menu= (List<Menu>) subMenuArray.get(submenuIterator.next());	
		    		
		    		for(Menu m:menu){
		    			submenucontent.add(m);
		    		}	
		    	}
		
		}catch(Exception e){
			System.out.print("In getSubMenu :"+e);
		}finally {
			try{
				subMenuconn.close();
				submenu.close();
				submenures.close();
			}catch(Exception e){
				
			}	
		}
	   return submenucontent;
	}
}
