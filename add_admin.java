
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.applet.*;
import java.awt.Component;
import javax.swing.*;
public class add_admin extends JFrame implements ActionListener
{
    private JFrame frame;
    private JTextField Tnewname;
    private JPasswordField Tpwd;
    private JPasswordField TconfirmPwd;
    private JButton Jexit;
    private JButton Jconfirm;
    private JLabel labelBack;
    private ImageIcon backImg = new ImageIcon("image//add_admin.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    Connection con;
    PreparedStatement st;
    public add_admin()
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
        frame = new JFrame("新增管理员");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(icon);
        frame.setSize(600, 440);
        frame.setResizable(false);
        frame.setLayout(null);
        Container cp = frame.getContentPane();
        cp.setLayout(null);
        ((JPanel)cp).setOpaque(false);
        labelBack = new JLabel(backImg);
        Tnewname = new JTextField(15);
        Tpwd = new JPasswordField(15);
        Jexit = new JButton("返回");
        Jconfirm = new JButton("确认");
        TconfirmPwd = new JPasswordField(15);
        TconfirmPwd.setBounds(new Rectangle(237,306,205,26));
        labelBack.setBounds(0,0,600,400);
        Tnewname.setBounds(new Rectangle(237,217,205,26));
        Tpwd.setBounds(new Rectangle(237,262,205,26));
        Jconfirm.setBounds(new Rectangle(237,351,79,33));
        Jexit.setBounds(new Rectangle(363,351,79,33));
        Jconfirm.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        Tpwd.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        Jexit.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        Tnewname.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        TconfirmPwd.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        frame.getLayeredPane().add(labelBack,new Integer(Integer.MIN_VALUE));
        cp.add(TconfirmPwd);
        cp.add(Tpwd);
        cp.add(Tnewname);
        cp.add(Jexit);
        cp.add(Jconfirm);
        Jexit.addActionListener(this);
        Jconfirm.addActionListener(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = frame.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        con = connectDatabase.getConnect();
        if(e.getSource() == Jconfirm) {
            try{
                String s1 = Tnewname.getText();
                char[] c1 = Tpwd.getPassword();
                String s2 = String.valueOf(c1);
                char[] c2 = TconfirmPwd.getPassword();
                String s3 = String.valueOf(c2);
                s1 = s1.trim();
                s2 = s2.trim();
                s3 = s3.trim();
                if(s3.isEmpty()||s2.isEmpty()||s1.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame,"不允许有空");
                }else {
                    if(!s3.equals(s2))
                    {
                        JOptionPane.showMessageDialog(frame,"密码不一致");
                    }
                    else{
                        String sql = "insert 管理员表 values(?,?)";
                        st = con.prepareStatement(sql);
                        st.setString(1,s1);
                        st.setString(2,s2);
                        st.executeUpdate();
                        JOptionPane.showMessageDialog(frame,"添加成功");
                        TconfirmPwd.setText("");
                        Tnewname.setText("");
                        Tpwd.setText("");
                        st.close();
                    }
                }
                con.close();
            }catch (Exception ex)
            {
                JOptionPane.showMessageDialog(frame,"添加失败");
                ex.printStackTrace();
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
        add_admin L = new add_admin();
    }

}
