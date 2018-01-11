import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class addVisitor extends JFrame implements ActionListener
{
    private JTextField textSname;
    private JTextField textsno2;
    private JTextField textSex;
    private JTextField textTle;
    private JTextField textRoom;
    private JButton butSave;
    private JButton butBack;
    private JLabel labBac;
    private Connection con = null;
    private PreparedStatement st = null;
    private Container cont = null;
    private ImageIcon bacIco = new ImageIcon("image//addVisitor.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    public addVisitor()
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
        textSname = new JTextField();
        textsno2 = new JTextField();
        textSex = new JTextField();
        textTle = new JTextField();
        textRoom = new JTextField();
        butBack = new JButton("返回");
        butSave = new JButton("保存");
        textTle.setBounds(164,435,323,39);
        textSex.setBounds(164,335,323,39);
        textSname.setBounds(164,135,323,39);
        textsno2.setBounds(164,235,323,39);
        textRoom.setBounds(164,535,323,39);
        labBac.setBounds(0,0,550,700);
        butSave.setBounds(150,618,98,30);
        butBack.setBounds(302,618,98,30);
        butBack.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        butSave.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textRoom.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textTle.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textSex.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textsno2.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textSname.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        textRoom.setOpaque(false);
        textsno2.setOpaque(false);
        textSname.setOpaque(false);
        textSex.setOpaque(false);
        textTle.setOpaque(false);
        cont.add(butBack);
        cont.add(butSave);
        cont.add(textsno2);
        cont.add(textRoom);
        cont.add(textSex);
        cont.add(textSname);
        cont.add(textTle);
        butSave.addActionListener(this);
        butBack.addActionListener(this);
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
        if(e.getSource() == butSave) {
            String s1 = textSname.getText();
            String s2 = textsno2.getText();
            String s3 = textSex.getText();
            String s4 = textTle.getText();
            String s5 = textRoom.getText();
            try{
                if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "不允许有空!");
                } else {
                    String sql = "insert into 访客表 values(?,?,?,?,?)";
                    st = con.prepareStatement(sql);
                    st.setString(1, s1);
                    st.setString(2, s2);
                    st.setString(3, s3);
                    st.setString(4, s4);
                    st.setString(5, s5);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(this, "新建成功!");
                    textSname.setText("");
                    textsno2.setText("");
                    textSex.setText("");
                    textTle.setText("");
                    textRoom.setText("");
                }
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this, "新建失败，访客已存在");
                ex.printStackTrace();
            }

        }
        if(e.getSource() == null) {
            Visitor vis = new Visitor();
        }

    }
    public static void main(String[] args)
    {
        addVisitor sta = new addVisitor();
    }
}
