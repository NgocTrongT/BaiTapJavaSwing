/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Admin
 */

public class UserDao {
    
     public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        Connection connection= JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM USERS";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                User user = new User();
                
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("NAME"));
                user.setPhone(rs.getString("PHONE"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setAbout(rs.getString("ABOUT"));
                user.setFavourites(rs.getString("FAVOURITES"));
                user.setRole(rs.getString("ROLE"));
                
                users.add(user);
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
     
     public User getUserById(int id){
         Connection connection= JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM USERS WHERE ID =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                User user = new User();
                
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("NAME"));
                user.setPhone(rs.getString("PHONE"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setAbout(rs.getString("ABOUT"));
                user.setFavourites(rs.getString("FAVOURITES"));
                user.setRole(rs.getString("ROLE"));
                
                return user;
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user){
        Connection connection= JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO USERS(NAME, PHONE, USERNAME, PASSWORD, ABOUT, FAVOURITES, ROLE)"+ "VALUES(?,?,?,?,?,?,?) ";
       
      
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5, user.getAbout());
            preparedStatement.setString(6, user.getFavourites());
            preparedStatement.setString(7, user.getRole());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void updateUser(User user){
        Connection connection= JDBCConnection.getJDBCConnection();
        String sql = "UPDATE Users SET NAME = ?, PHONE = ?, USERNAME = ?, PASSWORD = ?, ABOUT=?,"+"FAVOURITES = ?, ROLE = ? WHERE ID =?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4,user.getPassword());
   
            preparedStatement.setString(5, user.getAbout());
            preparedStatement.setString(6, user.getFavourites());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.setInt(8, user.getId());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }
    
    public void deleteUser(int id){
        Connection connection= JDBCConnection.getJDBCConnection();
        String sql = "delete from Users where id = ? ";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1,id);
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }
    
    

    /*public void deleteUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
        
}



