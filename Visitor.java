import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Visitor extends JFrame implements ActionListener
{
    private JTextField textsno;
    private JButton butSelect;
    private JTextField textSname;
    private JTextField textsno2;
    private JTextField textSex;
    private JTextField textTle;
    private JTextField textRoom;
    private JButton butChange;
    private JButton butDelete;
    private JLabel labBac;
    private JButton butAdd;
    private Connection con = null;
    private PreparedStatement st = null;
    private Container cont = null;
    private ImageIcon bacIco = new ImageIcon("image//Visitor.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    public Visitor()
    {
        try{ UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        setTitle("访客管理");
        con = connectDatabase.getConnect();
        cont = this.getContentPane();
        ((JPanel)cont).setOpaque(false);
        setSize(550,725);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLayout(null);
        this.setIconImage(icon);
        labBac = new JLabel(bacIco);
        textsno = new JTextField();
        textSname = new JTextField();
        textsno2 = new JTextField();
        textSex = new JTextField();
        textTle = new JTextField();
        textRoom = new JTextField();
        butSelect = new JButton("查询");
        butChange = new JButton("确认更改");
        butDelete = new JButton("删除");
        butAdd = new JButton("增加访客");
        butAdd.setBounds(398,68,89,19);
        butSelect.setBounds(202,58,140,42);
        textTle.setBounds(164,435,323,39);
        butChange.setBounds(150,618,110,30);
        butDelete.setBounds(302,618,98,30);
        textSex.setBounds(164,335,323,39);
        textSname.setBounds(164,135,323,39);
        textsno2.setBounds(164,235,323,39);
        textsno.setBounds(277,19,162,26);
        textRoom.setBounds(164,535,323,39);
        labBac.setBounds(0,0,550,700);
        butSelect.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        butChange.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        butDelete.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textRoom.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textTle.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textSex.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textsno2.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textsno.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textSname.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        butAdd.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,14));
        butAdd.setForeground(Color.blue);
        butAdd.addActionListener(this);
        butDelete.addActionListener(this);
        butChange.addActionListener(this);
        butSelect.addActionListener(this);
        butAdd.setContentAreaFilled(false);
        textRoom.setOpaque(false);
        textsno2.setOpaque(false);
        textSname.setOpaque(false);
        textSex.setOpaque(false);
        textTle.setOpaque(false);
        cont.add(butChange);
        cont.add(butDelete);
        cont.add(butSelect);
        cont.add(textsno);
        cont.add(textsno2);
        cont.add(textRoom);
        cont.add(textSex);
        cont.add(textSname);
        cont.add(textTle);
        cont.add(butAdd);
        this.getLayeredPane().add(labBac,new Integer(Integer.MIN_VALUE));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = this.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        setVisible(true);
    }
    public void  actionPerformed(ActionEvent e)
    {
        if(e.getSource() == butSelect)
        {
            try {
                String s1 = textsno.getText();
                if (s1.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "身份证号不允许为空！");
                } else {
                    String sql = "select * from 访客表 where 身份证号 = ?";
                    st = con.prepareStatement(sql);
                    st.setString(1,s1);
                    ResultSet rs = st.executeQuery();
                    if(rs.next())
                    {
                        textSname.setText(rs.getString(1).trim());
                        textsno2.setText(rs.getString(2).trim());
                        textSex.setText(rs.getString(3).trim());
                        textTle.setText(rs.getString(4).trim());
                        textRoom.setText(rs.getString(5).trim());
                    }else
                    {
                        JOptionPane.showMessageDialog(this,"无此访客!");
                        textsno.setText("");
                        textSname.setText("");
                        textsno2.setText("");
                        textSex.setText("");
                        textTle.setText("");
                        textRoom.setText("");
                    }
                }
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == butChange)
        {
            try
            {
                String s0 = textsno.getText();
                String s1 = textSname.getText();
                String s2 = textsno2.getText();
                String s3 = textSex.getText();
                String s4 = textTle.getText();
                String s5 = textRoom.getText();
                s0 = s0.trim();
                s1 = s1.trim();
                s2 = s2.trim();
                s3 = s3.trim();
                s4 = s4.trim();
                s5 = s5.trim();
                if(!s0.equals(s2))
                {
                    JOptionPane.showMessageDialog(this,"身份证号不允许更改!");
                }
                else {
                    String sql = "update 访客表 set 姓名 = ?,性别 = ?,电话 = ?,到访房间 = ? where 身份证号 = ?";
                    st = con.prepareStatement(sql);
                    st.setString(1, s1);
                    st.setString(2, s3);
                    st.setString(3, s4);
                    st.setString(4, s5);
                    st.setString(5, s0);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(this, "更改成功!");
                    textsno.setText("");
                    textSname.setText("");
                    textsno2.setText("");
                    textSex.setText("");
                    textTle.setText("");
                    textRoom.setText("");
                }

            }catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this,"更改失败!");
                ex.printStackTrace();
            }
        }
        if(e.getSource() == butAdd)
        {
            this.setVisible(false);
            addVisitor adds = new addVisitor();
        }
        if(e.getSource() == butDelete)
        {
            try
            {
                String s1 = textsno2.getText();
                String s2 = textSname.getText();
                s1 = s1.trim();
                if(s1.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,"身份证号不允许为空!");
                }
                else
                {
                    String ss = "是否删除访客:"+s2+"?";
                    int t = JOptionPane.showConfirmDialog(null,ss,"提示",JOptionPane.YES_NO_OPTION);
                    if(t == JOptionPane.YES_OPTION) {
                        String sql = "delete from 访客表 where 身份证号 = ?";
                        st = con.prepareStatement(sql);
                        st.setString(1, s1);
                        st.executeUpdate();
                        JOptionPane.showMessageDialog(this, "删除成功！");
                        textsno.setText("");
                        textSname.setText("");
                        textsno2.setText("");
                        textSex.setText("");
                        textTle.setText("");
                        textRoom.setText("");
                    }else if(t == JOptionPane.NO_OPTION)
                    {
                        JOptionPane.showMessageDialog(this, "删除取消！");
                    }
                }
            }catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "删除失败！");
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args)
    {
        Visitor sta = new Visitor();
    }
}
