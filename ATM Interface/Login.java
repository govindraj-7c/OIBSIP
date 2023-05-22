import java.util.Scanner;
import java.sql.*;
public class Login {
	Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
	PreparedStatement pstmt = null;
    public void register() {
		Scanner sc = new Scanner(System.in);
		int acno;
        int pin;
        try{
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/atm";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
			System.out.println("---------------- Registeration ----------------");
			System.out.println();
			System.out.println("Enter Account No :");
			acno = sc.nextInt();
			System.out.println("Enter PIN : ");
			pin = sc.nextInt();
			String QryString = "INSERT INTO account(acno,pin,amount) VALUES(?,?,10000)";
			pstmt = conn.prepareStatement(QryString);
			pstmt.setInt(1, acno);
			pstmt.setInt(2, pin);
			pstmt.executeUpdate();
			System.out.println("Registered Successfully");	
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(Exception e)
        {
            System.out.println("Cannot connect to database server");
        }
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch(SQLException sqlEx) {}
                rs = null;
            }
            if((stmt != null)) {
                try {
                    stmt.close();
                } catch(SQLException sqlEx) {}
                stmt = null;
            }
            if(conn != null) {
                try{
                    conn.close();
                } catch(Exception e) {}
            }
        }
    }
	String login(){
		PreparedStatement pstmt1 = null;
		Scanner sc = new Scanner(System.in);
		boolean bool = true;
		System.out.println("---------------- Login ----------------");
		System.out.println("Enter Account No : ");
		int accno = sc.nextInt();
		System.out.println("Enter PIN : ");
		int pin1 = sc.nextInt();
		try{
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/atm";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
			stmt.execute("SELECT * FROM ACCOUNT");
			rs = stmt.getResultSet();
			while(rs.next()){
				if(accno == rs.getInt("acno") && pin1 == rs.getInt("pin")){
					bool = true;
					break;
				}
				else{
					bool = false;
				}
			}
			
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(Exception e)
        {
            System.out.println("Cannot connect to database server");
        }
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch(SQLException sqlEx) {}
                rs = null;
            }
            if((stmt != null)) {
                try {
                    stmt.close();
                } catch(SQLException sqlEx) {}
                stmt = null;
            }
            if(conn != null) {
                try{
                    conn.close();
                } catch(Exception e) {}
            }
        }
		if(bool){
				return "Login Successfully";
			}
		else{
			return "Invalid User";
		}
	}
	
	void machine(){
		Scanner sc = new Scanner(System.in);
		int no;
		while(true){
			System.out.println("1.Transaction History");
			System.out.println("2.Withdraw");
			System.out.println("3.Deposit");
			System.out.println("4.Transfer");
			System.out.println("5.Quit");
			System.out.println();
			System.out.println("Enter Your Choice");
			no = sc.nextInt();
			switch(no){
				case 1 : history(); 
					break;
				case 2 : withdraw();
					break;
				case 3 : deposit();
					break;
				case 4 : transfer();
					break;
				default : break;
			}
			if(no == 5) break;
			else if(no>5) System.out.println("Invalid Input");
		}
	}
	
	void deposit(){
		Scanner sc = new Scanner(System.in);
		PreparedStatement pstmt2 = null;
		//Statement stmt = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt1 = null;
		int original;
		System.out.println("Enter Amount to deposit");
		int amt1 = sc.nextInt();
		System.out.println("Enter Your PIN");
		int pin1 = sc.nextInt();
		try{
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/atm";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);	
			String QryString2 = "UPDATE ACCOUNT SET AMOUNT = AMOUNT + ? WHERE PIN = ?";
			pstmt2 = conn.prepareStatement(QryString2);
			pstmt2.setInt(1, amt1);
			pstmt2.setInt(2, pin1);
			pstmt2.executeUpdate();
			String s = "Your amount Deposited Successfully";
			System.out.println(s);
			String QryString3 = "INSERT INTO HISTORY(ACTION,AMOUNT,PIN) VALUES(?,?,?)";
			pstmt1 = conn.prepareStatement(QryString3);
			pstmt1.setString(1,s);
			pstmt1.setInt(2,amt1);
			pstmt1.setInt(3,pin1);
			pstmt1.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch(SQLException sqlEx) {}
                rs = null;
            }
            if((stmt != null)) {
                try {
                    stmt.close();
                } catch(SQLException sqlEx) {}
                stmt = null;
            }
            if(conn != null) {
                try{
                    conn.close();
                } catch(Exception e) {}
            }
        }
	}
	void history(){
		Scanner sc = new Scanner(System.in);
		String actionString;
		int amountString;
		PreparedStatement pstmt2 = null;
		//Statement stmt = null;
		ResultSet rs1 = null;
		System.out.println("Enter Your PIN");
		int pin1 = sc.nextInt();
		System.out.println();
		try{
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/atm";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);	
			String QryString2 = "SELECT ACTION , AMOUNT FROM HISTORY WHERE PIN = ?";
			pstmt2 = conn.prepareStatement(QryString2);
			pstmt2.setInt(1, pin1);
			rs1 = pstmt2.executeQuery();
			System.out.println("Transactions \t\t\t\t"+"Amount \n");
			while(rs1.next()) {
				actionString = rs1.getString("ACTION");
				amountString = rs1.getInt("AMOUNT");
				System.out.println(actionString+"\t\t"+amountString +"\n");
			}
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch(SQLException sqlEx) {}
                rs = null;
            }
            if((stmt != null)) {
                try {
                    stmt.close();
                } catch(SQLException sqlEx) {}
                stmt = null;
            }
            if(conn != null) {
                try{
                    conn.close();
                } catch(Exception e) {}
            }
        }
	}
	void withdraw(){
		Scanner sc = new Scanner(System.in);
		PreparedStatement pstmt2 = null;
		//Statement stmt = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt1 = null;
		System.out.println("Enter Amount to Withdraw");
		int amt1 = sc.nextInt();
		System.out.println("Enter Your PIN");
		int pin1 = sc.nextInt();
		try{
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/atm";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);	
			String QryString2 = "UPDATE ACCOUNT SET AMOUNT = AMOUNT - ? WHERE PIN = ?";
			pstmt2 = conn.prepareStatement(QryString2);
			pstmt2.setInt(1, amt1);
			pstmt2.setInt(2, pin1);
			pstmt2.executeUpdate();
			String s = "Amount Withdraw Successfully";
			System.out.println("Please Collect Cash Rs :"+amt1);
			String QryString3 = "INSERT INTO HISTORY(ACTION,AMOUNT,PIN) VALUES(?,?,?)";
			pstmt1 = conn.prepareStatement(QryString3);
			pstmt1.setString(1,s);
			pstmt1.setInt(2,amt1);
			pstmt1.setInt(3,pin1);
			pstmt1.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch(SQLException sqlEx) {}
                rs = null;
            }
            if((stmt != null)) {
                try {
                    stmt.close();
                } catch(SQLException sqlEx) {}
                stmt = null;
            }
            if(conn != null) {
                try{
                    conn.close();
                } catch(Exception e) {}
            }
        }
	}
	
	void transfer(){
		Scanner sc = new Scanner(System.in);
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		//Statement stmt = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt1 = null;
		System.out.println("Enter Amount to Transfer");
		int amt1 = sc.nextInt();
		System.out.println("Enter Account No to which you want to transfer");
		int accNo = sc.nextInt();
		System.out.println("Enter Your PIN");
		int pin1 = sc.nextInt();
		try{
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/atm";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);	
			String QryString2 = "UPDATE ACCOUNT SET AMOUNT = AMOUNT + ? WHERE acno = ?";
			pstmt2 = conn.prepareStatement(QryString2);
			pstmt2.setInt(1, amt1);
			pstmt2.setInt(2, accNo);
			pstmt2.executeUpdate();
			String QryString1 = "UPDATE ACCOUNT SET AMOUNT = AMOUNT - ? WHERE PIN = ?";
			pstmt1 = conn.prepareStatement(QryString1);
			pstmt1.setInt(1, amt1);
			pstmt1.setInt(2, pin1);
			pstmt1.executeUpdate();
			String s = "Amount Transfer Successfully";
			System.out.println(s);
			String QryString3 = "INSERT INTO HISTORY(ACTION,AMOUNT,PIN) VALUES(?,?,?)";
			pstmt3 = conn.prepareStatement(QryString3);
			pstmt3.setString(1,s);
			pstmt3.setInt(2,amt1);
			pstmt3.setInt(3,pin1);
			pstmt3.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch(SQLException sqlEx) {}
                rs = null;
            }
            if((stmt != null)) {
                try {
                    stmt.close();
                } catch(SQLException sqlEx) {}
                stmt = null;
            }
            if(conn != null) {
                try{
                    conn.close();
                } catch(Exception e) {}
            }
        }
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		Login l = new Login();
		int no;
		String s;
		while(true){
			System.out.println("1.Registration");
			System.out.println("2.Login");
			System.out.println("3.Exit");
			System.out.println();
			System.out.println("Enter Your Choice");
			no = sc.nextInt();
			switch(no){
				case 1 : l.register();
					break;
				case 2 : s = l.login();
						  if(s == "Login Successfully"){
								l.machine();
						  }
						  else{
							  System.out.println("Invalid User");
						  }
					break;
				default : break;
			}
			if(no == 3) break;
			else if(no>3) System.out.println("Invalid Input\n");
		}
		
	}

}
