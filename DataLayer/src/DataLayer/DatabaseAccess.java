/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import ApplicationVariables.AppVariables;
import ClassLayer.Actor;
import ClassLayer.Director;
import ClassLayer.Film;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mqul
 */
public class DatabaseAccess{
    
    String message = "";
    
    public boolean putActorData(Connection conn, String filmID, Actor actor) throws SQLException{
        
        boolean isSuccess = false;
        
        String actorFirstname;
        String actorLastname;
        if(actor.personName.contains(" ")){
            String[] actorName = actor.personName.split(" ", 2);
            actorFirstname = actorName[0];
            actorLastname = actorName[1]; 
        }else{
            actorFirstname = actor.personName;
            actorLastname = "";
        }
        
        try(CallableStatement cs = conn.prepareCall("{CALL insertOrUpdateActor(?,?,?,?)}")){
            cs.setString(1, actorFirstname);
            cs.setString(2, actorLastname);
            cs.setString(3, actor.personID);
            cs.setString(4, filmID);
            
            cs.execute(); //execute stored procedure
            
            //retrieve data from the resultset
            try(ResultSet rs = cs.getResultSet()){
                while(rs.next()){
                    //String result = rs.getString("resultCode"); 
                    String dbMessage = rs.getString(1);
                    
                    if(dbMessage.contains("error")){
                        isSuccess = false;
                        message = dbMessage;
                    }else{
                        isSuccess = true;
                        message = dbMessage;
                    } 
                }
            }  
        }
        
        return isSuccess;
    }
    
    public boolean putDirectorData(Connection conn, String filmID, Director director) throws SQLException{
        
        boolean isSuccess = false;
        
        String directorFirstname;
        String directorLastname;
        if(director.personName.contains(" ")){
            String[] directorName = director.personName.split(" ", 2);
            directorFirstname = directorName[0];
            directorLastname = directorName[1]; 
        }else{
            directorFirstname = director.personName;
            directorLastname = "";
        }
        
        try(CallableStatement cs = conn.prepareCall("{CALL insertOrUpdateDirector(?,?,?,?)}")){
            cs.setString(1, directorFirstname);
            cs.setString(2, directorLastname);
            cs.setString(3, director.personID);
            cs.setString(4, filmID);
            
            cs.execute(); //execute stored procedure
            
            //retrieve data from the resultset
            try(ResultSet rs = cs.getResultSet()){
                while(rs.next()){
                    //String result = rs.getString("resultCode"); 
                    String dbMessage = rs.getString(1);
                    
                    if(dbMessage.contains("error")){
                        isSuccess = false;
                        message = dbMessage;
                    }else{
                        isSuccess = true;
                        message = dbMessage;
                    } 
                }
            }  
        }
        
        return isSuccess;
    }
    
    public boolean putMovieData(Connection conn, Film film) throws SQLException{
        
        boolean isSuccess = false;
        
        try(CallableStatement cs = conn.prepareCall("{CALL insertOrUpdateFilm(?,?,?,?)}")){
            cs.setString(1, film.filmName);
            cs.setString(2, film.filmYear);
            cs.setString(3, film.filmID);
            cs.setString(4, film.imdbRating);
            
            cs.execute(); //execute stored procedure
            
            //retrieve data from the resultset
            try(ResultSet rs = cs.getResultSet()){
                while(rs.next()){
                    //String result = rs.getString("resultCode"); 
                    String dbMessage = rs.getString(1);
                    
                    if(dbMessage.contains("error")){
                        isSuccess = false;
                        message = dbMessage;
                    }else{
                        isSuccess = true;
                        message = dbMessage;
                    } 
                }
            }  
        }
        
        return isSuccess;
    }
    
    //read data from database into objects
    public boolean putFilmData(Connection conn, Film film, Actor actor, Director director) throws SQLException{
        
        boolean isSuccess = false;
        
        String actorFirstname;
        String actorLastname;
        if(actor.personName.contains(" ")){
            String[] actorName = actor.personName.split(" ", 2);
            actorFirstname = actorName[0];
            actorLastname = actorName[1]; 
        }else{
            actorFirstname = actor.personName;
            actorLastname = "";
        }
        
        String directorFirstname;
        String directorLastname;
        if(director.personName.contains(" ")){
            String[] directorName = director.personName.split(" ", 2);
            directorFirstname = directorName[0];
            directorLastname = directorName[1];  
        }else{
            directorFirstname = director.personName;
            directorLastname = "";
        }
        
        try(CallableStatement cs = conn.prepareCall("{CALL insertDataset(?,?,?,?,?,?,?,?,?,?)}")){
            cs.setString(1, film.filmName);
            cs.setString(2, film.filmYear);
            cs.setString(3, film.filmID);
            cs.setString(4, film.imdbRating);
            cs.setString(5, actorFirstname);
            cs.setString(6, actorLastname);
            cs.setString(7, actor.personID);
            cs.setString(8, directorFirstname);
            cs.setString(9, directorLastname);
            cs.setString(10,director.personID);
            
            cs.execute(); //execute stored procedure
            
            //retrieve data from the resultset
            try(ResultSet rs = cs.getResultSet()){
                while(rs.next()){
                    String result = rs.getString("resultCode"); 
                    String dbMessage = rs.getString("resultMessage");
                    
                    switch(Integer.parseInt(result)){
                        case AppVariables.Database.DatabaseCodes.success:
                            isSuccess = true; 
                            message = dbMessage;
                            break;
                        case AppVariables.Database.DatabaseCodes.lookupLinksExist:
                            isSuccess = true;
                            message = dbMessage;
                            break;
                        case AppVariables.Database.DatabaseCodes.error:
                            isSuccess = false;
                            message = dbMessage;
                    }
                    
                  /*  if(result.contains("Success")){
                        isSuccess = true;
                    }else if(result.contains(result)){
                        isSuccess = false;
                        message = result;
                   }*/
/*                    switch(result){
                        case "commit": isSuccess = true; break;
                        case "rollback": isSuccess = false; break;
                        case "Error, data already exists": isSuccess = false; message = result; 
                        case "Error occured": isSuccess = false; message = result;
                        default: isSuccess = false;
                    }  */
                }
            }  
        }catch(SQLException e){
            isSuccess = false;
            StringBuilder sb = new StringBuilder();
            sb.append("Message: " + e.getMessage());
            sb.append("SQL State: " + e.getSQLState());
            sb.append("Error code: "+ e.getErrorCode());
            message = sb.toString();
        }finally{
            //UNCOMMENT conn.close(); //close connection to db once try block is complete
        }
        
        return isSuccess;
    }
    
    public String getResultMessage(){
        return message;
    }
}