import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.sql.*;

 

public class DB extends JFrame implements ActionListener

{

    JLabel l1, l2, l3;

    JTextField tf1;

    JButton btn1,btn2;

    JPasswordField p1;

 

    DB()

    {

        setTitle("Login Form");

        setVisible(true);

        setSize(800, 800);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 

        l1 = new JLabel("Login Form");

        l1.setForeground(Color.blue);

        l1.setFont(new Font("Serif", Font.BOLD, 20));

 

        l2 = new JLabel("Enter Email:");

        l3 = new JLabel("Enter Password:");

        tf1 = new JTextField();

        p1 = new JPasswordField();

        btn1 = new JButton("Submit");
        btn2 = new JButton("Login");

 

        l1.setBounds(100, 30, 400, 30);

        l2.setBounds(80, 70, 200, 30);

        l3.setBounds(80, 110, 200, 30);

        tf1.setBounds(300, 70, 200, 30);

        p1.setBounds(300, 110, 200, 30);

        btn1.setBounds(150, 160, 100, 30);
	btn2.setBounds(300, 160, 100, 30);

 

        add(l1);

        add(l2);

        add(tf1);

        add(l3);

        add(p1);

        add(btn1);
	add(btn2);

        btn1.addActionListener(this);
	btn2.addActionListener(this);

    }

 

    public void actionPerformed(ActionEvent e)

    {
	if(e.getSource()==btn1)
	{
		showData1();
	}
	if(e.getSource()==btn2)
	{
		showData();
	}
	

    }

 

    public void showData()

    {

        JFrame f1 = new JFrame();

        JLabel l, l0;

 

        String str1 = tf1.getText();

        char[] p = p1.getPassword();

        String str2 = new String(p);

        try

        {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");
	   

            PreparedStatement ps = con.prepareStatement("select name from info where name=? and password=?");

            ps.setString(1, str1);

            ps.setString(2, str2);

            ResultSet rs = ps.executeQuery();

            if (rs.next())

            {

                f1.setVisible(true);

                f1.setSize(600, 600);

                f1.setLayout(null);

                l = new JLabel();

                l0 = new JLabel("you are succefully logged in..");

                l0.setForeground(Color.blue);

                l0.setFont(new Font("Serif", Font.BOLD, 30));

                l.setBounds(60, 50, 400, 30);

                l0.setBounds(60, 100, 400, 40);

 

                f1.add(l);

                f1.add(l0);

                l.setText("Welcome " + rs.getString(1));

                l.setForeground(Color.red);

                l.setFont(new Font("Serif", Font.BOLD, 30));

 

            } else

            {

                JOptionPane.showMessageDialog(null,

                   "Incorrect email-Id or password..Try Again with correct detail");

            }

        }

        catch (Exception ex)

        {

            System.out.println(ex);

        }

    }
 public void showData1()

    {

        JFrame f1 = new JFrame();

        JLabel l, l0;

 

        String str1 = tf1.getText();

        char[] p = p1.getPassword();

        String str2 = new String(p);

        try

        {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");
	   
	   String sql ="INSERT INTO info(name,password) VALUES(?,?)";
 	   PreparedStatement ps = con.prepareStatement(sql);
	   ps.setString(1, str1);

            ps.setString(2, str2);
  ps.execute();
con.close();


           }
	catch(Exception ae)
{
System.out.println("Error in Inserting");
}

}


           

    

 

    public static void main(String arr[])

    {

        new DB();

    }

}