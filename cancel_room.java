import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cancel_room extends JFrame implements ActionListener
{
    private JTextField textRno1;
    private JTextField textRoom1;
    private JButton buttonSelect;
    private JTextField textName;
    private JTextField textSex;
    private JTextField textRno2;
    private JTextField textRoom2;
    private JTextField textComeTime;
    private JButton buttonCancel;
    private ImageIcon bacIco = new ImageIcon("image//cancel_room.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    private Container cont;
    private JLabel labelBac;
    private Connection con;
    private PreparedStatement st;
    public cancel_room()
    {
        try{ UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        setSize(550,725);
        setResizable(false);
        setIconImage(icon);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("退房处理");
        setLayout(null);
        cont = this.getContentPane();
        labelBac = new JLabel(bacIco);
        textRno1 = new JTextField();
        textRoom1 = new JTextField();
        textName = new JTextField();
        textSex = new JTextField();
        textRno2 = new JTextField();
        textRoom2 = new JTextField();
        textComeTime = new JTextField();
        buttonSelect = new JButton("查询");
        buttonCancel = new JButton("退订");
        buttonCancel.setBounds(225,618,98,30);
        buttonSelect.setBounds(202,58,160,42);
        textComeTime.setBounds(164,535,323,39);
        textRoom2.setBounds(164,435,323,39);
        textRno2.setBounds(164,335,323,39);
        textSex.setBounds(164,235,323,39);
        textName.setBounds(164,135,323,39);
        textRoom1.setBounds(396,19,132,26);
        textRno1.setBounds(129,19,162,26);
        labelBac.setBounds(0,0,550,700);
        buttonCancel.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        buttonSelect.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textComeTime.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textRoom2.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textRno2.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textSex.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textName.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textRoom1.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textRno1.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        ((JPanel)cont).setOpaque(false);
        textComeTime.setOpaque(false);
        textRoom2.setOpaque(false);
        textRno2.setOpaque(false);
        textSex.setOpaque(false);
        textName.setOpaque(false);
        buttonSelect.addActionListener(this);
        buttonCancel.addActionListener(this);
        cont.add(textRoom1);
        cont.add(textRno1);
        cont.add(textRno2);
        cont.add(textName);
        cont.add(textComeTime);
        cont.add(textRoom2);
        cont.add(textSex);
        cont.add(buttonCancel);
        cont.add(buttonSelect);
        this.getLayeredPane().add(labelBac,new Integer(Integer.MIN_VALUE));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = this.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        con = connectDatabase.getConnect();
        if(e.getSource() == buttonSelect)
        {
            try {
                String s1 = textRno1.getText();
                String s2 = textRoom1.getText();
                if(!s1.isEmpty()||!s2.isEmpty()) {
                    if (!s1.isEmpty()) {
                        String sql = "select 姓名,性别,客户身份证号,房间号,入住时间 from room_customer where 客户身份证号 = ?";
                        st = con.prepareStatement(sql);
                        st.setString(1, s1);
                    } else if (!s2.isEmpty()) {
                        String sql = "select 姓名,性别,客户身份证号,房间号,入住时间 from room_customer where 房间号 = ?";
                        st = con.prepareStatement(sql);
                        st.setString(1, s2);
                    }
                    ResultSet rs = st.executeQuery();
                    if(rs.next())
                    {
                        textName.setText(rs.getString(1));
                        textSex.setText(rs.getString(2));
                        textRno2.setText(rs.getString(3));
                        textRoom2.setText(rs.getString(4));
                        textComeTime.setText(rs.getString(5));
                    }else{
                        JOptionPane.showMessageDialog(this,"不存在此开房记录！");
                    }
                }else{
                    JOptionPane.showMessageDialog(this,"身份证号和房间号不允许都为空！");
                }

            }catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==buttonCancel)
        {
            try{
                String s1 = textRno2.getText();
                s1 = s1.trim();
                if(s1.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,"不存在此开房记录！");
                }
                else {
                    String ss = "确认退掉房间："+s1+"";
                    int t = JOptionPane.showConfirmDialog(null,ss,"提示",JOptionPane.YES_NO_OPTION);
                    if(t == JOptionPane.YES_OPTION) {
                        String sql1 = "delete from 房间使用表 where 客户身份证号 = ?";
                        String sql2 = "delete from 客户表 where 身份证号 = ?";
                        st = con.prepareStatement(sql1);
                        st.setString(1, s1);
                        st.executeUpdate();
                        st = con.prepareStatement(sql2);
                        st.setString(1, s1);
                        st.executeUpdate();
                        JOptionPane.showMessageDialog(this, "退房成功");
                        textName.setText("");
                        textSex.setText("");
                        textRno2.setText("");
                        textRoom2.setText("");
                        textComeTime.setText("");
                    }else if(t == JOptionPane.NO_OPTION)
                    {
                        JOptionPane.showMessageDialog(this, "退房取消");
                    }
                }
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
            try
            {
                con.close();
            }catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }

    }
    public static void main(String[] args)
    {
        cancel_room c = new cancel_room();
    }
}
