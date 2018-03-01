import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

import com.tmax.tibero.jdbc.*;
import com.tmax.tibero.jdbc.ext.*;


/*written by ������ */

public class studentMenu {
	
	public static void studentReport(int stuID) throws SQLException, ClassNotFoundException{
		
		Class.forName(Main.JDBC_DRIVER);
		Connection conn=DriverManager.getConnection(Main.DATABASE_URL,Main.USERNAME,Main.PASSWORD);
		
		
		/*student(ID, name, dept_name, tot_credit)�� ���� ����ְ�, ID��  student�� super key �̹Ƿ�  �ùٸ� studentID�� �ֿ����� �������� �������� ã�� �� �ִ�.*/
		
		PreparedStatement pstmt1 = conn.prepareStatement("SELECT name, dept_name, tot_cred"+" FROM student "+"Where ID=?");
		pstmt1.setInt(1,stuID);
		ResultSet rs=pstmt1.executeQuery();
		
		/* rs(name, dept_name, tot_credit) ���� �����Ǿ��� �ְ�, get***�Լ��� �̿���  rs�� ������ �����´�.*/
		
		while(rs.next()){
			System.out.println("Welcome "+rs.getString(1));
			System.out.println("You are a member of "+rs.getString(2));
			System.out.println("You have taken total "+rs.getInt(3)+" credits");
		}
		
		System.out.println();
		System.out.println("Semester report");
		
		/*student�� ���� ������ �����ϱ� ����,
		 takes.Id=studentID�� ��� takes ��  course_id ��  natural join �ؼ� (year, semester, course_id ,title, dept_name, credits, grade)�� �̷���� result set�� �����.
		 ��, order by ������ case ������ �� Ȱ���Ͽ� (year, semester)������ �����Ѵ�. */
		
		PreparedStatement pstmt2=conn.prepareStatement("SELECT year, semester, course_id, title, dept_name, credits, grade "+"From takes natural join course "+"Where takes.ID=? "
														+"order by year desc,(case semester when 'Spring' then 4 when 'Summer' then 3 when 'Fall' then 2 when 'Winter' then 1 else 5 end)");
		pstmt2.setInt(1,stuID);
		ResultSet rs2=pstmt2.executeQuery();
		
		/*student�� �б�� gpa�� �б�� ���� ������� count�ϴ� result set�� ���ϴ� query
		 group by ������ Ȱ���Ͽ� (year, semester)�� �����ְ� case ������ Ȱ���Ͽ�  A,B,C�� �־��� grade�� ���ڷ� �ٲ۴� 
		 count(course_id) : �б�� ���� ���� �� 
		 count(grade) : �б�� ���� ���� �� ���� ���� null�� ������ ������ �� 
		 */
		
		PreparedStatement pstmt3=conn.prepareStatement("select year, semester, count(course_id),count(grade), "+"sum((case grade "
																				 +"when 'A+' then 4.3 when 'A' then 4.0 when 'A-' then 3.7 "
																				 +"when 'B+' then 3.3 when 'B' then 3.0 when 'B-' then 2.7 "
																				 +"when 'C+' then 2.3 when 'C' then 2.0 when 'C-' then 1.7 "
																				 +"when 'D+' then 1.3 when 'D' then 1.0 when 'D-' then 0.7 "
																				 +"when 'F' then 0 else NULL end)*credits)"
																				 +" / sum(credits) "
													   +"from takes natural join course "
													   +"where takes.ID=? "
													   +"group by year, semester "
													   +"order by year desc,(case semester when 'Spring' then 4 when 'Summer' then 3 when 'Fall' then 2 when 'Winter' then 1 else 5 end)");
		pstmt3.setInt(1,stuID);
		ResultSet rs3=pstmt3.executeQuery();
		
		/*rs3�� �ִ� (year, semester) �� gpa�� rs2�� ���ĵ� (year, semester) ������ ���ĵ� ����ؾ� �ϴ� �������� ��� */
		while(rs3.next()){
			int y=rs3.getInt(1);
			String sem=rs3.getString(2);
			if(rs3.getInt(3)!=rs3.getInt(4))				// count(course_id) �� count(grade)�� �ٸ���  ���� ���� �� null ���� ���� �Ѵٴ� �̾߱�									
				System.out.println("\n"+y+" "+sem+" GPA : ");
			else
				System.out.println("\n"+y+" "+sem+" GPA : "+rs3.getFloat(5));
			System.out.println("course_id		title		dept_name		credits		grade");
			/*rs3�� ����° ������ (year, semester)��  ���� ������ ���� �ִ�.
			 ��  �� ��ŭ  rs2���� ������ ������ش�
			(rs2�� rs3�� ���� table�� ���� ����� sql�̰� ������ �������� ���� �����Ƿ�  rs2�� rs3�� year�� semester�� �񱳾��ص� �ȴ�.(��Ȯ���� ���ؼ��� �ϴ°� ����.))*/
			for(int i=0;i<rs3.getInt(3);i++){						
				if(rs2.next()){
					if(rs2.getString(7)==null)
						System.out.println(rs2.getString(3)+"	"+rs2.getString(4)+"	"+rs2.getString(5)+"	"+rs2.getInt(6)+"	");
					else
						System.out.println(rs2.getString(3)+"	"+rs2.getString(4)+"	"+rs2.getString(5)+"	"+rs2.getInt(6)+"	"+rs2.getString(7));
				}
			}
		}
		System.out.println();
		
		rs3.close();
		rs2.close();
		rs.close();
		pstmt1.close();
		pstmt2.close();
		pstmt3.close();
		conn.close();
	}
	
	public static void viewTimeTable(int stuID) throws ClassNotFoundException, SQLException{
		int i=1,flag=0, year;
		String seme;
		Scanner sc = new Scanner(System.in);
		Class.forName(Main.JDBC_DRIVER);
		Connection conn=DriverManager.getConnection(Main.DATABASE_URL,Main.USERNAME,Main.PASSWORD);
		
		System.out.println("Please select semester to view");
		
		/*stuid�� ������ takes���� (year, semester) distinct�ϰ� ���� */
		PreparedStatement pstmt1 = conn.prepareStatement("select distinct year, semester "
				+"from takes "
				+"where takes.ID=? "
				+"order by year desc, (case semester when 'Spring' then 4 when 'Summer' then 3 "
										+"when 'Fall' then 2 when 'Winter' then 1 else 5 end)");
		pstmt1.setInt(1,stuID);
		ResultSet rs1=pstmt1.executeQuery();
		/*������ (year,semester)�� ���*/
		while(rs1.next()){
			System.out.println((i++)+") "+rs1.getInt(1)+"   "+rs1.getString(2));
		}
		System.out.print(">> ");
		
		flag = sc.nextInt();
		
		if(flag<i){ 			// �־��� ���� ��ȣ�� �ƴ� �ٸ� ��ȣ�� �� ��� ó��, ������� �پ��ϰ� ����� �ϴµ� �׳� �� ū ��ȣ�� �Է����� ���� ó��
		
			/*Result set rs1�� Ŀ���� �ٽ� ù row�� ����Ű�� ���� �ȵǼ� ���Ӱ� result set�� ������*/
			rs1.close();
			rs1=pstmt1.executeQuery();
			
			System.out.println("course_id	title	day		start_time	end_time");
			
			/*result set �� ������ row�� ����Ű�� ����� �ȵǹǷ� ����ڰ� ������ row���� rs�� Ŀ�� �̵� */
			for(int j=0;j<flag;j++)
				rs1.next();
			
			year=rs1.getInt(1);
			seme=rs1.getString(2);														//
			/*(year,semester)�� �־����� �� (course_id, title, day, start_time, end_time)�� ������ ����ϰ� ���� ��
			(course_id, title) �� course���� course_id�� super key �̹Ƿ� course_id�� �˸� ���Ⱑ��
			(day, start_time, end_time)�� time_slot ���� time_slot_id�� super key�̹Ƿ� ���Ⱑ��
			�׷��� takes�� section�� natural join�Ͽ� course_id�� time_slot_id�� ����*/
			PreparedStatement pstmt2=conn.prepareStatement("select course_id, time_slot_id "
					+"from takes natural join section "
					+"where (takes.ID,year,semester)=(?,?,?)");
			pstmt2.setInt(1,stuID);
			pstmt2.setInt(2,year);
			pstmt2.setString(3,seme);
			
			ResultSet rs2=pstmt2.executeQuery();
			
		
			PreparedStatement pstmt3=conn.prepareStatement("select title from course where course_id = ?");
			PreparedStatement pstmt4=conn.prepareStatement("select day, start_hr,start_min,end_hr, end_min "
															+ "from time_slot where time_slot_id = ?");
			String course, tsid,title;
			
			ResultSet rs3, rs4;
			while(rs2.next()){
				course=rs2.getString(1);
				tsid=rs2.getString(2);
				/*course_id�� �̿��� course_id�� title ����*/
				
				pstmt3.setString(1,course);
				pstmt4.setString(1,tsid);
				rs3=pstmt3.executeQuery();
				rs4=pstmt4.executeQuery();
				
				/*time slot id�� �̿��� (day, start_time, end_time) ����*/
				rs3.next();
				title=rs3.getString(1);
				while(rs4.next()){
					System.out.println(course+"	"+title+"	"+rs4.getString(1)+"	"+rs4.getInt(2)+" : "+rs4.getInt(3)+"		"+rs4.getInt(4)+" : "+rs4.getInt(5));
				}	
				rs3.close();
				rs4.close();
			}
			rs2.close();
			pstmt2.close();
			pstmt3.close();
			pstmt4.close();
		}
		System.out.println();
		
		rs1.close();
		pstmt1.close();
		conn.close();
	}
}