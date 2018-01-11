import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
public class login extends JFrame implements ActionListener
{
    private JFrame frame;
    private JTextField Tname;
    private JPasswordField Tpwd;
    private JButton Jexit;
    private JButton Jlogin;
    private JLabel labelBack;
    private ImageIcon backImg = new ImageIcon("image//login.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    private JButton changePwd;
    private JButton addAdmin;
    Connection con;
    Statement st;
    public login()
    {
        initData();
    }
    public void initData()
    {

        try{ UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        frame = new JFrame("登录");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(icon);
        frame.setSize(600, 440);
        frame.setResizable(false);
        frame.setLayout(null);
        Container cp = frame.getContentPane();
        cp.setLayout(null);
        ((JPanel)cp).setOpaque(false);
        labelBack = new JLabel(backImg);
        Tname = new JTextField(15);
        Tpwd = new JPasswordField(15);
        Jexit = new JButton("退出");
        Jlogin = new JButton("登陆");
        changePwd = new JButton("更改密码");
        addAdmin = new JButton("新增管理");
        changePwd.setBounds(478,297,90,20);
        addAdmin.setBounds(476,240,90,20);
        labelBack.setBounds(0,0,600,400);
        Tname.setBounds(new Rectangle(253,228,205,39));
        Tpwd.setBounds(new Rectangle(253,288,205,39));
        Jlogin.setBounds(new Rectangle(252,343,79,33));
        Jexit.setBounds(new Rectangle(379,343,79,33));
        Jlogin.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        Tpwd.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        Jexit.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        Tname.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        changePwd.setForeground(Color.BLUE);
        addAdmin.setForeground(Color.BLUE);
        changePwd.setContentAreaFilled(false);
        addAdmin.setContentAreaFilled(false);
        frame.getLayeredPane().add(labelBack,new Integer(Integer.MIN_VALUE));
        cp.add(changePwd);
        cp.add(addAdmin);
        cp.add(Tpwd);
        cp.add(Tname);
        cp.add(Jexit);
        cp.add(Jlogin);
        changePwd.addActionListener(this);
        addAdmin.addActionListener(this);
        Jexit.addActionListener(this);
        Jlogin.addActionListener(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = frame.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == Jlogin) {
            String Sname = Tname.getText();
            char[] Spwd = Tpwd.getPassword();
            String s = new String(Spwd);
            System.out.println(s);
            boolean isCorrrct = false;
            if (Sname.isEmpty() || s.isEmpty() ) {
                JOptionPane.showMessageDialog(frame, "不允许有空！");
            } else {
                try {
                    con = connectDatabase.getConnect();
                    String  sql="select * from 管理员表 where 管理员ID='"+Sname+"'"+"and 密码="+"'"+s+"'";
                    st = con.createStatement();
                    ResultSet res = st.executeQuery(sql);
                    if (res.next()) isCorrrct = true;
                    else isCorrrct = false;
                    res.close();
                    st.close();
                    con.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                if (isCorrrct)
                {
                    JOptionPane.showMessageDialog(frame, "登陆成功！");
                    frame.setVisible(false);
                    functionUI f = new functionUI();
                    f.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "用户名或者密码错误！");
                    Tpwd.setText("");
                }

            }
        }
        else if(e.getSource() == Jexit)
        {
            try {
                frame.setVisible(false);
                MainFrame m = new MainFrame();
                m.setVisible(true);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == changePwd){
            change_pwd cc = new change_pwd();
            frame.setVisible(false);
        }
        else if(e.getSource() == addAdmin)
        {
            frame.setVisible(false);
            confirm_addmin cc = new confirm_addmin();
        }
    }

    public static void main(String[] args)
    {
        login L = new login();
    }

}
