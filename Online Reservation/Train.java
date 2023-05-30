import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;  
import java.sql.*;
import java.lang.Math; 
import java.math.BigInteger;
import javax.swing.table.DefaultTableModel;
public class Train{
	void train1(){
		Register r = new Register();
		JFrame frame1 = new JFrame("Online Reservation System");
		frame1.setLayout(null);
		frame1.setBounds(300,100,800,500);
		JButton button1 = new JButton("REGISTER");
		button1.setBounds(330,150,130,50);  
		button1.setFont(new Font("Britannic Bold", Font.PLAIN, 22));
		button1.setForeground(Color.WHITE);
		button1.setBackground(Color.GRAY);
		frame1.add(button1);
		button1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				r.register1();
				frame1.dispose();
			}  
		});  
		JButton button2 = new JButton("LOGIN");
		button2.setBounds(330,250,130,50);  
		button2.setFont(new Font("Britannic Bold", Font.PLAIN, 24));
		button2.setForeground(Color.WHITE);
		button2.setBackground(Color.GRAY);
		frame1.add(button2);
		button2.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Login l = new Login();
				frame1.dispose();
			}  
		});  
		ImageIcon icon = new ImageIcon("Train.jpg");
		JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("Train.jpg"));	//Sets the image to be displayed as an icon
		Container c = frame1.getContentPane(); 
        //Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(0,-19,800,500);
		c.add(label);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]){
		Train t1 = new Train();
		t1.train1();
	}
}

class Register{
	Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
	PreparedStatement pstmt = null;
	void register1(){
		Train t = new Train();
		JFrame reg = new JFrame("Register");
		reg.setBounds(300,100,800,490);
		JPanel p = new JPanel();
        LayoutManager overlay = new OverlayLayout(p);
        p.setLayout(overlay);
		p.setLayout(null);
		JButton back = new JButton(new ImageIcon("Back.jpg"));
		back.setBounds(20,20,100,40);
		back.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				t.train1();
				reg.dispose();
			}  
		});  
		p.add(back);
		JLabel l3 = new JLabel("EmailId :");
		l3.setFont(new Font("Elephant", Font.PLAIN, 20));
		l3.setBounds(200,-200,700,700);
		p.add(l3);
		JTextField t1 = new JTextField();
		t1.setFont(new Font("Arial", Font.PLAIN, 18));
		t1.setBounds(310,135,300,30);
		p.add(t1);
		JLabel l4 = new JLabel("Username :");
		l4.setFont(new Font("Elephant", Font.PLAIN, 20));
		l4.setBounds(183,-130,700,700);
		p.add(l4);
		JTextField t2 = new JTextField();
		t2.setFont(new Font("Arial", Font.PLAIN, 18));
		t2.setBounds(310,205,230,30);
		p.add(t2);
		JLabel l5 = new JLabel("Password :");
		l5.setFont(new Font("Elephant", Font.PLAIN, 20));
		l5.setBounds(189,-60,700,700);
		p.add(l5);
		JPasswordField pass = new JPasswordField();
		pass.setFont(new Font("Elephant", Font.PLAIN, 18));
		pass.setBounds(310,275,230,30);
		p.add(pass);
		JButton b = new JButton("Register");
		b.setBounds(300,355,130,50);  
		b.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		b.setForeground(Color.GRAY);
		b.setBackground(Color.BLACK);
		p.add(b);
		JLabel l1 = new JLabel("<html><u>REGISTRATION</u></html>");
		l1.setFont(new Font("Britannic Bold", Font.PLAIN, 50));
		l1.setBounds(250,-300,700,700);
		p.add(l1);
		JLabel l2 = new JLabel(new ImageIcon("TrainR.jpg"));
		l2.setBounds(0,-20,800,500);
		p.add(l2);
		reg.add(p);
		reg.setVisible(true);
		reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		b.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				String e1 = t1.getText();
				String u = t2.getText();
				String pas = new String(pass.getPassword());
				if(e1.equals("")){
					JOptionPane.showMessageDialog(reg, "Please Enter Email");
				}
				else if(u.equals("")){
					JOptionPane.showMessageDialog(reg, "Please Enter Username");
				}
				else if(pas.equals("")){
					JOptionPane.showMessageDialog(reg, "Please Enter Password");
				}
				else{
					register2(e1,u,pas);
					JOptionPane.showMessageDialog(reg, "Register Successfully");
					t.train1();
					reg.dispose();
				}	
			}
		});
	}
	void register2(String e1, String u, String pas){
		try{
			String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/train";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
			String QryString1 = "INSERT INTO ACCOUNT(email,uname,password) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(QryString1);
			pstmt.setString(1, e1);
			pstmt.setString(2, u);
			pstmt.setString(3, pas);
			pstmt.executeUpdate();				
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
}

class Login{
	Connection conn = null;
    Statement stmt = null;
    ResultSet rs1 = null;
	JFrame log = new JFrame("Login");
	Login(){
		Dash d = new Dash();
		log.setBounds(300,100,800,490);
		JPanel p = new JPanel();
        LayoutManager overlay = new OverlayLayout(p);
        p.setLayout(overlay);
		p.setLayout(null);
		JButton back = new JButton(new ImageIcon("Back.jpg"));
		back.setBounds(20,20,100,40);
		back.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Train t = new Train();
				t.train1();
				log.dispose();
			}  
		});  
		p.add(back);
		JLabel l4 = new JLabel("Username :");
		l4.setFont(new Font("Elephant", Font.PLAIN, 20));
		l4.setBounds(183,-180,700,700);
		p.add(l4);
		JTextField t2 = new JTextField();
		t2.setFont(new Font("Arial", Font.PLAIN, 18));
		t2.setBounds(310,155,230,30);
		p.add(t2);
		JLabel l5 = new JLabel("Password :");
		l5.setFont(new Font("Elephant", Font.PLAIN, 20));
		l5.setBounds(189,-110,700,700);
		p.add(l5);
		JPasswordField pass = new JPasswordField();
		pass.setFont(new Font("Elephant", Font.PLAIN, 18));
		pass.setBounds(310,225,230,30);
		p.add(pass);
		JButton b = new JButton("Login");
		b.setBounds(300,300,130,50);  
		b.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		b.setForeground(Color.GRAY);
		b.setBackground(Color.BLACK);
		p.add(b);
		JLabel l1 = new JLabel("<html><u>LOGIN</u></html>");
		l1.setFont(new Font("Britannic Bold", Font.PLAIN, 50));
		l1.setBounds(300,-300,700,700);
		p.add(l1);
		JLabel l2 = new JLabel(new ImageIcon("TrainR.jpg"));
		l2.setBounds(0,-20,800,500);
		p.add(l2);
		log.add(p);
		log.setVisible(true);
		log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				String u = t2.getText();
				String pas = new String(pass.getPassword());
				int bool1 = login2(u,pas);
				if(bool1 == 1){
					d.dash1();
					log.dispose();
				}
				else if(bool1 == 2){
					JOptionPane.showMessageDialog(log, "Wrong Password");
				}
				else if(bool1 == 3){
					JOptionPane.showMessageDialog(log, "Incorrect Username");
				}
				else{
					JOptionPane.showMessageDialog(log, "Incorrect Username and Password");
				}
			}  
		});
	}
	int login2(String u1, String pas1){
		int bool = 0;
		try{
			String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/train";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
			stmt.execute("SELECT * FROM ACCOUNT");
			rs1 = stmt.getResultSet();
			while(rs1.next()){
				if(u1.equals(rs1.getString("uname")) && pas1.equals(rs1.getString("password"))){
					bool = 1;
					break;
				} 
				else if(u1.equals(rs1.getString("uname")) && pas1 != rs1.getString("password")){
					bool = 2;
				}
				else if(u1 != rs1.getString("uname") && pas1.equals(rs1.getString("password"))){
					bool = 3;
				}
				else{
					bool = 4;
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
            if(rs1 != null) {
                try {
                    rs1.close();
                } catch(SQLException sqlEx) {}
                rs1 = null;
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
		return bool;
	}
}

class Dash{
	void dash1(){
		Booking b = new Booking();
		JFrame f1 = new JFrame("Dashboard");
		f1.setBounds(300,100,800,490);
		JPanel p1 = new JPanel();
		LayoutManager overlay = new OverlayLayout(p1);
        p1.setLayout(overlay);
		p1.setLayout(null);
		JButton logout = new JButton("Logout");
		logout.setBounds(690,10,80,40);
		logout.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		logout.setForeground(Color.WHITE);
		logout.setBackground(Color.BLUE);
		p1.add(logout);
		logout.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				Train t = new Train();
				t.train1();
				f1.dispose();
			}  
		});
		JButton book = new JButton("Book Ticket");
		book.setBounds(130,110,200,50);
		book.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		book.setForeground(Color.WHITE);
		book.setBackground(Color.GREEN);
		book.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				b.booking1();
				f1.dispose();
			}  
		});  
		p1.add(book);
		JButton cancel = new JButton("Cancel Ticket");
		cancel.setBounds(450,110,200,50);
		cancel.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Color.RED);
		p1.add(cancel);
		cancel.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Cancel c = new Cancel();
				c.cancel1();
				f1.dispose();
			}  
		});  
		JButton check = new JButton("Check Trains");
		check.setBounds(130,250,200,50);
		check.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		check.setForeground(Color.BLACK);
		check.setBackground(Color.YELLOW);
		p1.add(check);
		check.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Ctrain c1 = new Ctrain();
				c1.ctrain1();
				f1.dispose();
			}  
		});  
		JButton my = new JButton("My Bookings");
		my.setBounds(450,250,200,50);
		my.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		my.setForeground(Color.BLACK);
		my.setBackground(Color.PINK);
		p1.add(my);
		my.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Mybook m = new Mybook();
				m.mybook1();
				f1.dispose();
			}  
		});  
		JLabel j1 = new JLabel(new ImageIcon("TrainB.jpg"));
		j1.setBounds(0,-20,800,500);
		p1.add(j1);
		f1.add(p1);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Booking{
	Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
	PreparedStatement pstmt = null;
	void booking1(){
		Dash d = new Dash();
		JFrame j1 = new JFrame("Book Ticket");
		j1.setBounds(300,100,800,490);
		JPanel p1 = new JPanel();
		LayoutManager overlay = new OverlayLayout(p1);
        p1.setLayout(overlay);
		p1.setLayout(null);
		JButton back = new JButton(new ImageIcon("Back.jpg"));
		back.setBounds(20,20,100,40);
		back.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				d.dash1();
				j1.dispose();
			}  
		});  
		p1.add(back);
		JButton logout = new JButton("Logout");
		logout.setBounds(690,10,80,40);
		logout.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		logout.setForeground(Color.WHITE);
		logout.setBackground(Color.BLUE);
		p1.add(logout);
		logout.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Train t = new Train();
				t.train1();
				j1.dispose();
			}  
		});
		JLabel l1 = new JLabel("Name:");
		l1.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		l1.setBounds(80,50,80,80);
		l1.setForeground(Color.YELLOW);
		p1.add(l1);
		JTextField t1 = new JTextField();
		t1.setFont(new Font("Arial", Font.PLAIN, 18));
		t1.setBounds(80,105,250,30);
		p1.add(t1);
		JLabel l2 = new JLabel("Age:");
		l2.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		l2.setBounds(80,135,80,80);
		l2.setForeground(Color.YELLOW);
		p1.add(l2);
		JTextField t2 = new JTextField();
		t2.setFont(new Font("Arial", Font.PLAIN, 18));
		t2.setBounds(80,190,60,30);
		p1.add(t2);
		JLabel l3 = new JLabel("From:");
		l3.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		l3.setBounds(80,225,80,80);
		l3.setForeground(Color.YELLOW);
		p1.add(l3);
		JTextField t3 = new JTextField();
		t3.setFont(new Font("Arial", Font.PLAIN, 18));
		t3.setBounds(80,280,180,30);
		p1.add(t3);
		JLabel l4 = new JLabel("Train No:");
		l4.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		l4.setBounds(80,315,80,80);
		l4.setForeground(Color.YELLOW);
		p1.add(l4);
		JTextField t4 = new JTextField();
		t4.setFont(new Font("Arial", Font.PLAIN, 18));
		t4.setBounds(80,370,180,30);
		p1.add(t4);
		JLabel l5 = new JLabel("Date:");
		l5.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		l5.setBounds(450,50,80,80);
		l5.setForeground(Color.YELLOW);
		p1.add(l5);
		JTextField t5 = new JTextField();
		t5.setFont(new Font("Arial", Font.PLAIN, 18));
		t5.setBounds(450,105,120,30);
		p1.add(t5);
		JLabel l6 = new JLabel("Class:");
		l6.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		l6.setBounds(450,135,80,80);
		l6.setForeground(Color.YELLOW);
		p1.add(l6);
		String c[] = {"<select>","AC","1S","2S","General"};
		JComboBox cb = new JComboBox(c);
		cb.setFont(new Font("Arial", Font.PLAIN, 18));
		cb.setBounds(450,190,120,30);
		p1.add(cb);
		JLabel l7 = new JLabel("Destination:");
		l7.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		l7.setBounds(450,225,130,80);
		l7.setForeground(Color.YELLOW);
		p1.add(l7);
		JTextField t7 = new JTextField();
		t7.setFont(new Font("Arial", Font.PLAIN, 18));
		t7.setBounds(450,280,210,30);
		p1.add(t7);
		JLabel l8 = new JLabel("Train Name:");
		l8.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		l8.setBounds(450,315,130,80);
		l8.setForeground(Color.YELLOW);
		p1.add(l8);
		JTextField t8 = new JTextField();
		t8.setFont(new Font("Arial", Font.PLAIN, 18));
		t8.setBounds(450,370,210,30);
		p1.add(t8);
		JButton submit = new JButton("Book");
		submit.setBounds(305,390,100,30);
		submit.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
		submit.setBackground(Color.GREEN);
		p1.add(submit);
		JLabel l9 = new JLabel("<html><u>Ticket Booking</u></html>");
		l9.setFont(new Font("Britannic Bold", Font.PLAIN, 40));
		l9.setBounds(250,-320,700,700);
		p1.add(l9);
		JLabel i1 = new JLabel(new ImageIcon("Book.jpg"));
		i1.setBounds(0,-20,800,500);
		p1.add(i1);
		j1.add(p1);
		j1.setVisible(true);
		j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				String name = t1.getText();
				String age = t2.getText();
				String from = t3.getText();
				String tno = t4.getText();
				String dat = t5.getText();
				String cls = cb.getSelectedItem().toString();
				String dest = t7.getText();
				String tname = t8.getText();
				long no = random();
				BigInteger val = BigInteger.valueOf(no);
				booking2(from,tno,dat,cls,dest,tname,val);
				JOptionPane.showMessageDialog(j1, "Booking Done Successfully\nYour PNR No is  "+no);
				d.dash1();
				j1.dispose();
			}  
		});  
		
	}
	void booking2(String start1, String tno1, String dt1, String seat1, String dest1, String tname1, BigInteger pnr1){
		try{
			String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/train";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
			String QryString1 = "INSERT INTO BOOKING(start,tno,dt,seat,dest,tname,pnr) VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(QryString1);
			pstmt.setString(1, start1);
			pstmt.setString(2, tno1);
			pstmt.setString(3, dt1);
			pstmt.setString(4, seat1);
			pstmt.setString(5, dest1);
			pstmt.setString(6, tname1);
			pstmt.setObject(7, pnr1, Types.BIGINT);
			pstmt.executeUpdate();	
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
	long random(){
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return number;
	}
}

class Ctrain{
	Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
	void ctrain1(){
		Dash d = new Dash();
		JFrame j = new JFrame("Train List");
		LayoutManager overlay = new OverlayLayout(j);
        j.setLayout(overlay);
		j.setLayout(null);
		JTable jt = new JTable();;
		JScrollPane sp=new JScrollPane(jt);
		jt.setBounds(0,0,300,300);   
		sp.setBounds(110,120,600,180);
		j.add(sp);
		j.setBounds(300,100,800,490);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/train";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
			//String s1[] = {"start","dest","tno","tname"};
			stmt.execute("SELECT * FROM TLIST");
			rs = stmt.getResultSet();
			DefaultTableModel model = new DefaultTableModel();
			//String colName[] = new String[columnCount];
			ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }
			while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                     rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
			}
			 jt.setModel(model);
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
		JButton back = new JButton(new ImageIcon("Back.jpg"));
		back.setBounds(20,20,100,40);
		j.add(back);
		back.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				d.dash1();
				j.dispose();
			}  
		});  
		JButton logout = new JButton("Logout");
		logout.setBounds(690,10,80,40);
		logout.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		logout.setForeground(Color.WHITE);
		logout.setBackground(Color.BLUE);
		j.add(logout);
		logout.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Train t = new Train();
				t.train1();
				j.dispose();
			}  
		});  
		JLabel i1 = new JLabel(new ImageIcon("Tlist.jpg"));
		i1.setBounds(0,-20,800,500);
		j.add(i1);
	}
}

class Cancel{
	Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
	PreparedStatement pstmt = null;
	void cancel1(){
		Dash d = new Dash();
		JFrame can1 = new JFrame("Cancel Ticket");
		can1.setBounds(300,100,800,490);
		JPanel p1 = new JPanel();
		LayoutManager overlay = new OverlayLayout(p1);
        p1.setLayout(overlay);
		p1.setLayout(null);
		JLabel l2 = new JLabel("Enter Your PNR No: ");
		l2.setFont(new Font("Britannic Bold", Font.PLAIN, 30));
		l2.setBounds(260,130,300,80);
		l2.setForeground(Color.BLACK);
		p1.add(l2);
		JTextField t1 = new JTextField();
		t1.setFont(new Font("Arial", Font.PLAIN, 18));
		t1.setBounds(280,210,200,30);
		p1.add(t1);
		JButton b1 = new JButton("Cancel");
		b1.setFont(new Font("Britannic Bold", Font.PLAIN, 22));
		b1.setBounds(310,280,130,40);
		b1.setBackground(Color.RED);
		b1.setForeground(Color.WHITE);
		p1.add(b1);
		JButton back = new JButton(new ImageIcon("Back.jpg"));
		back.setBounds(20,20,100,40);
		p1.add(back);
		back.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				d.dash1();
				can1.dispose();
			}  
		});  
		JButton logout = new JButton("Logout");
		logout.setBounds(690,10,80,40);
		logout.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		logout.setForeground(Color.WHITE);
		logout.setBackground(Color.BLUE);
		p1.add(logout);
		logout.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Train t = new Train();
				t.train1();
				can1.dispose();
			}  
		});  
		JLabel l1 = new JLabel("<html><u>Cancel Booking</u></html>");
		l1.setFont(new Font("Britannic Bold", Font.PLAIN, 40));
		l1.setBounds(250,-320,700,700);
		p1.add(l1);
		JLabel i1 = new JLabel(new ImageIcon("Book.jpg"));
		i1.setBounds(0,-20,800,500);
		p1.add(i1);
		can1.add(p1);
		can1.setVisible(true);
		can1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b1.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				String s1 = t1.getText();
				BigInteger pnr =new BigInteger(s1);
				cancel2(pnr);
				JOptionPane.showMessageDialog(can1, "Your Booking Cancelled Successfully");
				d.dash1();
				can1.dispose();
			}
		}); 
	}
	void cancel2(BigInteger pnr1){
		try{
			String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/train";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
			String QryString1 = "DELETE FROM BOOKING WHERE pnr = ?";
			pstmt = conn.prepareStatement(QryString1);
			pstmt.setObject(1, pnr1, Types.BIGINT);
			pstmt.executeUpdate();
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
}

class Mybook{
	Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
	void mybook1(){
		Dash d = new Dash();
		JFrame j = new JFrame("My Bookings");
		LayoutManager overlay = new OverlayLayout(j);
        j.setLayout(overlay);
		j.setLayout(null);
		JTable jt = new JTable();;
		JScrollPane sp=new JScrollPane(jt);
		jt.setBounds(0,0,300,300);   
		sp.setBounds(110,120,600,180);
		j.add(sp);
		j.setBounds(300,100,800,490);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/train";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
			//String s1[] = {"start","dest","tno","tname"};
			stmt.execute("SELECT * FROM BOOKING");
			rs = stmt.getResultSet();
			DefaultTableModel model = new DefaultTableModel();
			//String colName[] = new String[columnCount];
			ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }
			while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                     rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
			}
			 jt.setModel(model);
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
		JButton back = new JButton(new ImageIcon("Back.jpg"));
		back.setBounds(20,20,100,40);
		j.add(back);
		back.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				d.dash1();
				j.dispose();
			}  
		});  
		JButton logout = new JButton("Logout");
		logout.setBounds(690,10,80,40);
		logout.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		logout.setForeground(Color.WHITE);
		logout.setBackground(Color.BLUE);
		j.add(logout);
		logout.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Train t = new Train();
				t.train1();
				j.dispose();
			}  
		});  
		JLabel i1 = new JLabel(new ImageIcon("Tlist.jpg"));
		i1.setBounds(0,-20,800,500);
		j.add(i1);
	}
}
