package project3;

import java.sql.*;
import com.tmax.tibero.jdbc.ext.TbDataSource;
import java.util.Scanner;
/**
 * @author Baraka
 */
public class Project3 {

    static final String JDBC_DRIVER = "com.tmax.tibero.jdbc.TbDriver";
    static final String DB_NAME = "tibero";
    static final String DB_URL = "jdbc:tibero:thin:@127.0.0.1:8629:" + DB_NAME;
    
    /*DB_USER - username to connect to database
    * DB_PASS - password to connect to database
    * Change them to your own database username and password
    */
    static final String DB_USER = "tibero3";
    static final String DB_PASS = "tibero";
    
    static Scanner in;
    static Connection conn; 
    
    public static void main(String[] args) {
        
        TbDataSource tds = new TbDataSource();
        in = new Scanner(System.in);
        
        try{
            tds.setURL(DB_URL);
            tds.setUser(DB_USER);
            tds.setPassword(DB_PASS);
            
            conn = tds.getConnection();
            System.out.println("Welcome\n");
        
            //Looping forever when the use enters a wrong input
            while(true){
                int input_id;
                String input_name;

                System.out.println("Please sign in");
                System.out.print("ID\t: ");
                input_id = in.nextInt();
                System.out.print("Name\t: ");
                input_name = in.next();
                System.out.println();
                
                if(authenticateStudent(input_id, input_name)) {
                    studentMenu(new Student(input_id, input_name));
                    break;
                    //If student is authenticated the loop will break;
                }
                else if(authenticateInstructor(input_id, input_name)) {
                    instructorMenu(new Instructor(input_id, input_name));
                    break;
                    //If instructor is authenticated the loop will break;
                }
                else System.out.println("Wrong authentication.");
            }
            
            in.close();
            conn.close();
             
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    static boolean authenticateStudent (int input_id, String input_name) throws SQLException{
        Boolean authenticated = false;
     
        PreparedStatement statement = conn.prepareStatement("SELECT id, name FROM student WHERE id = ?");
        statement.setInt(1, input_id);

        ResultSet result = statement.executeQuery();
        while(result.next()){
            String name = result.getString("NAME"); 
            
            //Comparing if input name is equal to one stored in the database
            if(name.equals(input_name)) authenticated = true;
        }
 
        statement.close();
        result.close();
        
        return authenticated;
    }
    
    static boolean authenticateInstructor (int input_id, String input_name) throws SQLException{
        Boolean authenticated = false;
      
        PreparedStatement statement = conn.prepareStatement("SELECT id, name FROM instructor WHERE id = ?");
        statement.setInt(1, input_id);

        ResultSet result = statement.executeQuery();
        while(result.next()){
            String name = result.getString("NAME");      
            
            //Comparing if input name is equal to one stored in the database
            if(name.equals(input_name)) authenticated = true;
        }
       
        statement.close();
        result.close();
                
        return authenticated;
    }
    
    static void studentMenu(Student student){       
        OUTER:
        while (true) {
            System.out.println("Please select student menu");
            System.out.println("1) Student report");
            System.out.println("2) View time table");
            System.out.println("0) Exit");
            
            int action = in.nextInt();
            switch (action) {
                case 0: break OUTER;
                case 1: studentReport(student); break;
                case 2: studentTimeTable(student); break;
                default: break;
            }
        }
    }
    
    static void instructorMenu(Instructor instructor){       
        OUTER:
        while (true) {
            System.out.println("Please select instructor menu");
            System.out.println("1) Course report");
            System.out.println("2) Advisee report");
            System.out.println("0) Exit");
            
            int action = in.nextInt();
            switch (action) {
                case 0: break OUTER;
                case 1: instructorCourseReport(instructor); break;
                case 2: instructorAdviseeReport(instructor); break;
                default: break;
            }
        }
    }
    
    static void studentReport(Student student){
        //TODO Handle student report
        System.out.println("Printing student report......\n");
    }
    
    static void studentTimeTable(Student student){
        //TODO Handle student timetable
        System.out.println("Printing student timetable......\n");
    }
    
    static void instructorCourseReport(Instructor instructor){
        //TODO Handle Instructor course report
        System.out.println("Printing Instructor report......\n");
    }
    
    static void instructorAdviseeReport(Instructor instructor){
        //TODO Handle Instructor Advisee Report
        System.out.println("Printing Instructor advisee report......\n");
    }    
    
    
}
