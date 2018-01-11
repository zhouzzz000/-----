
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;
import java.applet.*;
import java.awt.Component;
import javax.swing.*;
public class change_pwd extends JFrame implements ActionListener
{
    private JFrame frame;
    private JTextField tname;
    private JTextField toldpwd;
    private JPasswordField tnewPwd;
    private JPasswordField tconfirmPwd;
    private JButton Jexit;
    private JButton Jconfirm;
    private JLabel labelBack;
    private ImageIcon backImg = new ImageIcon("image//change_pwd.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    Connection con;
    PreparedStatement st;
    public change_pwd()
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
        Jexit = new JButton("返回");
        Jconfirm = new JButton("确认");
        labelBack = new JLabel(backImg);
        tconfirmPwd = new JPasswordField();
        tnewPwd = new JPasswordField();
        toldpwd = new JTextField();
        tname = new JTextField();
        labelBack.setBounds(0,0,600,400);
        Jexit.setBounds(495,341,79,33);
        Jconfirm.setBounds(495,291,79,33);
        tconfirmPwd.setBounds(237,348,205,26);
        tnewPwd.setBounds(237,305,205,26);
        toldpwd.setBounds(237,262,205,26);
        tname.setBounds(237,219,205,26);
        frame.getLayeredPane().add(labelBack,new Integer(Integer.MIN_VALUE));
        cp.add(Jexit);
        cp.add(Jconfirm);
        cp.add(tconfirmPwd);
        cp.add(tname);
        cp.add(tnewPwd);
        cp.add(toldpwd);
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
        if(e.getSource() == Jconfirm)
        {
            con = connectDatabase.getConnect();
            try {
                String s1 = tname.getText();
                s1 = s1.trim();
                String s2 = toldpwd.getText();
                s2 = s2.trim();
                String s3 = tnewPwd.getText();
                s3 = s3.trim();
                String s4 = tconfirmPwd.getText();
                s4 = s4.trim();
                if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "不允许有空");
                } else {
                    String sql1 = "select * from 管理员表 where 管理员ID=? and 密码=?";
                    st = con.prepareStatement(sql1);
                    st.setString(1,s1);
                    st.setString(2,s2);
                    ResultSet rs = st.executeQuery();
                    if(rs.next())
                    {
                        if(s3.equals(s4))
                        {
                            try {
                                String sql2 = "update 管理员表 set 密码 = ? where 管理员ID=?";
                                st = con.prepareStatement(sql2);
                                st.setString(1, s3);
                                st.setString(2, s1);
                                st.executeUpdate();
                                JOptionPane.showMessageDialog(frame,"修改成功!");
                                toldpwd.setText("");
                                tnewPwd.setText("");
                                tconfirmPwd.setText("");
                                tname.setText("");
                            }catch (SQLException ex)
                            {
                                JOptionPane.showMessageDialog(frame,"修改失败!");
                                toldpwd.setText("");
                                tnewPwd.setText("");
                                tconfirmPwd.setText("");
                                ex.printStackTrace();
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(frame,"账号密码不匹配!");
                        toldpwd.setText("");
                        tnewPwd.setText("");
                        tconfirmPwd.setText("");
                    }
                }
            }catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }else if(e.getSource() == Jexit)
        {
            try {
                login LL = new login();
                frame.setVisible(false);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }

    }

    public static void main(String[] args)
    {
        change_pwd L = new change_pwd();
    }

}
