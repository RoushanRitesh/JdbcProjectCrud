package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

class EmpDao {
public static Connection getConnection()
{
	Connection con=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mytab","root","root");
		
	} catch (Exception e) {
		System.out.println(e);
	}
	return con;
	}

public static int save(Emp e){  
    int status=0;  
    try{  
        Connection con=EmpDao.getConnection();  
        PreparedStatement ps=con.prepareStatement(  
                     "insert into emp(name,password,email,country,id) values (?,?,?,?,?)");  
        ps.setString(1,e.getName());  
        ps.setString(2,e.getPassword());  
        ps.setString(3,e.getEmail());  
        ps.setString(4,e.getCountry());  
        ps.setInt(5, e.getId());  
        status=ps.executeUpdate();  
          
        con.close();  
    }catch(Exception ex){ex.printStackTrace();}  
      
    return status;  
} 
public static int update(Emp e1){  
    int status=0;  
    try{  
        Connection con=EmpDao.getConnection();  
        PreparedStatement ps=con.prepareStatement(  
                     "update emp set name=?,password=?,email=?,country=? where id=?");  
        ps.setString(1,e1.getName());  
        ps.setString(2,e1.getPassword());  
        ps.setString(3,e1.getEmail());  
        ps.setString(4,e1.getCountry());  
        ps.setInt(5,e1.getId());  
          
        status=ps.executeUpdate();  
          
        con.close();  
    }catch(Exception ex){ex.printStackTrace();}  
      
    return status;  
}  
public static int delete(int id){  
    int status=0;  
    try{  
        Connection con=EmpDao.getConnection();  
        PreparedStatement ps=con.prepareStatement("delete from emp where id=?");  
        ps.setInt(1,id);  
        status=ps.executeUpdate();  
          
        con.close();  
    }catch(Exception e){e.printStackTrace();}  
      
    return status;  
}  
public static Emp getEmployeeById(int id){  
    Emp e1=new Emp();  
      
    try{  
        Connection con=EmpDao.getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from emp where id=?");  
        ps.setInt(1,id);  
        ResultSet rs=ps.executeQuery();  
        if(rs.next()){  
            e1.setId(rs.getInt(1));  
            e1.setName(rs.getString(2));  
            e1.setPassword(rs.getString(3));  
            e1.setEmail(rs.getString(4));  
            e1.setCountry(rs.getString(5));  
        }  
        con.close();  
    }catch(Exception ex){ex.printStackTrace();}  
      
    return e1;  
}  
public static List<Emp> getAllEmployees(){  
    List<Emp> list=new ArrayList<Emp>();  
      
    try{  
        Connection con=EmpDao.getConnection();  
        PreparedStatement ps=con.prepareStatement("select * from emp");  
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){  
            Emp e1=new Emp();  
            e1.setId(rs.getInt(1));  
            e1.setName(rs.getString(2));  
            e1.setPassword(rs.getString(3));  
            e1.setEmail(rs.getString(4));  
            e1.setCountry(rs.getString(5));  
            list.add(e1);  
        }  
        con.close();  
    }catch(Exception e){e.printStackTrace();}  
      
    return list;  
}
 
}  

