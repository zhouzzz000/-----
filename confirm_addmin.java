import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
public class confirm_addmin extends JFrame implements ActionListener
{
    private JFrame frame;
    private JTextField Tname;
    private JPasswordField Tpwd;
    private JButton Jexit;
    private JButton Jlogin;
    private JLabel labelBack;
    private ImageIcon backImg = new ImageIcon("image//confirm_changepwd.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    Connection con;
    Statement st;
    public confirm_addmin()
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
        frame = new JFrame("管理员账户确认");
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
        Jexit = new JButton("返回");
        Jlogin = new JButton("确认");
        labelBack.setBounds(0,0,600,400);
        Tname.setBounds(new Rectangle(234,228,205,39));
        Tpwd.setBounds(new Rectangle(234,288,205,39));
        Jlogin.setBounds(new Rectangle(233,343,79,33));
        Jexit.setBounds(new Rectangle(360,343,79,33));
        Jlogin.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        Tpwd.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        Jexit.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        Tname.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        frame.getLayeredPane().add(labelBack,new Integer(Integer.MIN_VALUE));
        cp.add(Tpwd);
        cp.add(Tname);
        cp.add(Jexit);
        cp.add(Jlogin);
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
                    JOptionPane.showMessageDialog(frame, "管理员确认成功！");
                    frame.setVisible(false);
                    add_admin f = new add_admin();
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
            frame.setVisible(false);
            login m = new login() ;
        }
    }

    public static void main(String[] args)
    {
        confirm_addmin L = new confirm_addmin();
    }

}
