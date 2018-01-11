import com.sun.org.apache.xml.internal.security.Init;
import jdk.nashorn.internal.runtime.ECMAException;
import jdk.nashorn.internal.scripts.JO;

import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
public class AddCustomerInfo extends JFrame implements ActionListener
{
    Connection con;//连接数据库对象;
    Statement st;//用于发送sql语;
    private JFrame frame;
    private JLabel jLabsno;
    private JLabel jLabsname;
    private JLabel jLabsex;
    private JLabel jLabcome;
    private JLabel jLabform;
    private JTextField jTextf1;//文本框\
    private JTextField jTextf2;
    private JTextField jTextf3;
    private JTextField jTextf4;
    private JRadioButton jRadioman;//单选按钮
    private JRadioButton jRadiowoman;
    private ButtonGroup buttonGroup1;//按钮组
    private JButton jButtoncommit;
    private JRadioButton Room1;
    private JRadioButton Room2;
    private JRadioButton Room3;
    private ButtonGroup buttonGroup2;
    private JButton buttonBack;
    private JLabel age;
    private JLabel leaveTime;
    private JTextField jTextf5;
    private JLabel labback;
    private JLabel jLabDays;
    private JTextField jTextf6;
    private JLabel labDay;
    private ImageIcon img1 = new ImageIcon("image//handleRoom.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//.jpg");
    public AddCustomerInfo() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
         frame = new JFrame("客户信息录入:");
         jLabsno = new JLabel("身份证号:");//标注
         jLabsname = new JLabel("姓    名:");
         jLabsex = new JLabel("性    别:");
         jLabcome = new JLabel("入房时间:");
         jLabform = new JLabel("房间类型:");
         jTextf1 = new JTextField();//文本框
         jTextf2 = new JTextField();
         jTextf3 = new JTextField();
         jTextf4 = new JTextField();
         labDay = new JLabel("天");
         jRadioman = new JRadioButton("男");//单选按钮
         jRadiowoman = new JRadioButton("女");
         buttonGroup1 = new ButtonGroup();//按钮组
         jButtoncommit = new JButton("保存");//按钮
         Room1 = new JRadioButton("单人房");
         Room2 = new JRadioButton("多人房");
         Room3 = new JRadioButton("总统房");
         buttonGroup2 = new ButtonGroup();
         buttonBack = new JButton("返回");
         age = new JLabel("年    龄:");
         jTextf5 = new JTextField();
         leaveTime = new JLabel("离开时间:");
         jLabDays = new JLabel("入住天数:");
         jTextf6 = new JTextField();
         labback = new JLabel(img1);
         Init();
    }
    public void Init()
    {
        frame.setResizable(false);
        frame.setSize(450,500);
        frame.setIconImage(icon);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = frame.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        jLabsno.setBounds(new Rectangle(50,30,64,24));
        jLabsname.setBounds(new Rectangle(50,71,61,27));
        jLabsex.setBounds(new Rectangle(50,106,61,27));
        jLabcome.setBounds(new Rectangle(50,185,59,25));
        jLabform.setBounds(new Rectangle(50,313,54,24));
        jTextf1.setBounds(new Rectangle(150,30,200,30));
        jTextf2.setBounds(new Rectangle(150,69,200,30));
        jTextf4.setBounds(new Rectangle(150,185,200,29));
        jTextf3.setBounds(new Rectangle(150,143,200,29));
        jTextf6.setBounds(new Rectangle(150,230,200,29));
        jLabDays.setBounds(new Rectangle(50,233,54,24));
        labDay.setBounds(new Rectangle(370,233,30,20));
        age.setBounds(new Rectangle(50,143,59,25));
        jRadioman.setBounds(new Rectangle(150,103,65,30));
        jRadiowoman.setBounds(new Rectangle(250,103,85,30));
        Room1.setBounds(new Rectangle(150,313,80,30));
        Room2.setBounds(new Rectangle(230,313,80,30));
        Room3.setBounds(new Rectangle(310,313,80,30));
        jButtoncommit.setBounds(new Rectangle(100,370,70,30));
        buttonBack.setBounds(new Rectangle(250,370,70,30));
        leaveTime.setBounds(new Rectangle(50,273,59,29));
        jTextf5.setBounds(new Rectangle(150,273,200,29));
        labback.setBounds(0,0,450,470);
        Room1.setOpaque(false);
        Room2.setOpaque(false);
        Room3.setOpaque(false);
        jRadiowoman.setOpaque(false);
        jRadioman.setOpaque(false);
        frame.getLayeredPane().add(labback,new Integer(Integer.MIN_VALUE));
        ((JPanel)frame.getContentPane()).setOpaque(false);
        jRadioman.setSelected(true);//默认被选中
        Room1.setSelected(true);
        jButtoncommit.addActionListener(this);
        buttonBack.addActionListener(this);
        frame.setLayout(null);
        frame.add(labDay);
        frame.add(jLabDays);
        frame.add(jTextf6);
        frame.add(age);
        frame.add(jTextf4);
        frame.add(jLabsname);
        frame.add(jTextf1);
        frame.add(jLabsno);
        frame.add(jTextf2);
        frame.add(jLabsex);
        frame.add(jRadioman);
        frame.add(jRadiowoman);
        frame.add(jLabcome);
        frame.add(jTextf3);
        frame.add(jLabform);
        frame.add(Room1);
        frame.add(Room2);
        frame.add(Room3);
        frame.add(leaveTime);
        frame.add(jTextf5);
        frame.add(jButtoncommit);
        frame.add(buttonBack);
        buttonGroup1.add(jRadioman);//单选按钮加入按钮组中
        buttonGroup1.add(jRadiowoman);
        buttonGroup2.add(Room1);
        buttonGroup2.add(Room2);
        buttonGroup2.add(Room3);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == jButtoncommit) {

            String sno = jTextf1.getText();
            String sname = jTextf2.getText();
            String sex = "";
            String sage = jTextf3.getText();
            String scome = jTextf4.getText();
            String sleave = jTextf5.getText();
            String days = jTextf6.getText();
            String sform = "";
            days = days.trim();
            sno = sno.trim();
            if(sno.isEmpty()||sname.isEmpty()||sage.isEmpty()||scome.isEmpty()||sleave.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"不允许有空");
            }
            else if(sno.length()!=18)
            {
                JOptionPane.showMessageDialog(this,"身份证号输入有误");
            }
            else {
                int days_num = Integer.parseInt(days);
                if (jRadioman.isSelected()) sex += "男";
                if (jRadiowoman.isSelected()) sex += "女";
                if (Room1.isSelected()) sform += "单人间";
                if (Room2.isSelected()) sform += "双人间";
                if (Room3.isSelected()) sform += "总统间";
                con = connectDatabase.getConnect();
                try {
                    st = con.createStatement();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    String sql = "select* from Empty_room where 类型 = '" + sform + "'";
                    ResultSet rs = st.executeQuery(sql);
                    if(rs.next()) {
                        int days_values = days_num*rs.getInt(3);
                        String s = "房间号为："+rs.getString(1)+"\n价格为："+days_values+"";
                        System.out.println(s);
                        int t = JOptionPane.showConfirmDialog(null,s,"提示",JOptionPane.YES_NO_OPTION);
                        if(t == JOptionPane.YES_OPTION) {
                            String sql1 = "insert into 客户表 values('" + sno + "','" + sname + "','" + sex + "','" + sage + "','" + scome + "','" + sleave + "')";
                            String sql2 = "insert into 房间使用表 values('"+rs.getString(1)+"','"+sno+"')";
                            String sql3 = "insert into 住房历史表 values('" + sname + "','" + sno + "','" + scome + "','" + sleave + "','"+rs.getString(1)+"')";
                            String sq14 = "insert into 资金收支历史 values("+days_values+",'"+scome+"','"+sno+"','"+rs.getString(1)+"')";
                            st.executeUpdate(sq14);
                            st.executeUpdate(sql3);
                            st.executeUpdate(sql1);
                            st.executeUpdate(sql2);
                            jTextf6.setText("");
                            jTextf1.setText("");
                            jTextf2.setText("");
                            jTextf3.setText("");
                            jTextf4.setText("");
                            jTextf5.setText("");
                            JOptionPane.showMessageDialog(this, "保存成功!");
                        }
                        else if(t == JOptionPane.NO_OPTION)
                        {
                            JOptionPane.showMessageDialog(this, "取消保存!");
                        }

                    }
                    else {
                        JOptionPane.showMessageDialog(this, "暂无空余房间,保存失败!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "保存失败!");
                    ex.printStackTrace();
                }
            }

        }
        if(e.getSource() == buttonBack)
        {
            frame.setVisible(false);
        }

    }
    public static void main(String[] args)
    {
        AddCustomerInfo x = new AddCustomerInfo();
    }
}



