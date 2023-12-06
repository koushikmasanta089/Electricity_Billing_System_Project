import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton Login, Cancel, Signup;
    JTextField usernsme, password;
    Choice logginin;
    Login(){
        super("Login Page");
        getContentPane().setBackground(Color.white);
        setLayout(null);

        // JLabel is used for write something in Frame.
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300,20,100,20);
        add(lblusername);

        usernsme = new JTextField();
        usernsme.setBounds(400,20,150,20);
        add(usernsme);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300,60,100,20);
        add(lblpassword);

        password = new JTextField();
        password.setBounds(400,60,150,20);
        add(password);

        JLabel logininas = new JLabel("Login in as");
        logininas.setBounds(300,100,100,20);
        add(logininas);

        logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(400,100,150,20);
        add(logginin);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        Login = new JButton("Login" ,new ImageIcon(i2));
        Login.setBounds(330,160,100,20);
        Login.addActionListener(this);
        add(Login);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        Cancel = new JButton("Cancel", new ImageIcon(i4));
        Cancel .setBounds(450,160,100,20);
        Cancel.addActionListener(this);
        add(Cancel);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        Signup = new JButton("Sign up", new ImageIcon(i6));
        Signup.setBounds(380,200,100,20);
        Signup.addActionListener(this);
        add(Signup);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0,0,250,250);
        add(image);


        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == Login){
            String susername = usernsme.getText();
            String spassword = password.getText();
            String user = logginin.getSelectedItem();

            try {
                conn c = new conn();
                String query = "select * from login where username = '"+susername+"' and password  = '"+spassword+"' and user = '"+user+"'";

                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()){
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(user, meter);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    usernsme.setText("");
                    password.setText("");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == Cancel){
            setVisible(false);
        }
        else if (ae.getSource() == Signup){
            setVisible(false);
            new Signup();
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}
