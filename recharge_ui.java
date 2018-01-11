import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class recharge_ui extends JFrame implements ActionListener
{
    private JTextField textRno1;
    private JTextField textvipID1;
    private JButton buttonSelect;
    private JTextField textName;
    private JTextField textSex;
    private JTextField textRno2;
    private JTextField textvipID2;
    private JTextField textbalance;
    private JTextField textrecharge;
    private JButton buttonRecharge;
    private ImageIcon bacIco = new ImageIcon("image//recharge.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    private Container cont;
    private JLabel labelBac;
    private Connection con;
    private PreparedStatement st;
    public recharge_ui()
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
        setTitle("贵宾卡充值");
        setLayout(null);
        cont = this.getContentPane();
        labelBac = new JLabel(bacIco);
        textRno1 = new JTextField();
        textvipID1 = new JTextField();
        textName = new JTextField();
        textSex = new JTextField();
        textRno2 = new JTextField();
        textvipID2 = new JTextField();
        textbalance = new JTextField();
        textrecharge = new JTextField();
        buttonSelect = new JButton("查询");
        buttonRecharge = new JButton("确认充值");
        buttonRecharge.setBounds(225,618,110,42);
        buttonSelect.setBounds(202,58,160,42);
        textrecharge.setBounds(164,545,323,39);
        textbalance.setBounds(164,463,323,39);
        textRno2.setBounds(164,299,323,39);
        textSex.setBounds(165,217,323,39);
        textName.setBounds(164,135,323,39);
        textvipID1.setBounds(396,19,132,26);
        textRno1.setBounds(119,19,162,26);
        labelBac.setBounds(0,0,550,700);
        textvipID2.setBounds(164,381,323,39);
        buttonRecharge.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        buttonSelect.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textrecharge.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textbalance.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textRno2.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textSex.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textName.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textvipID1.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textRno1.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        textvipID2.setFont(new java.awt.Font("宋体",Font.PLAIN,18));
        ((JPanel)cont).setOpaque(false);
        textvipID2.setOpaque(false);
        textrecharge.setOpaque(false);
        textbalance.setOpaque(false);
        textRno2.setOpaque(false);
        textSex.setOpaque(false);
        textName.setOpaque(false);
        buttonSelect.addActionListener(this);
        buttonRecharge.addActionListener(this);
        cont.add(textvipID2);
        cont.add(textvipID1);
        cont.add(textRno1);
        cont.add(textRno2);
        cont.add(textName);
        cont.add(textrecharge);
        cont.add(textbalance);
        cont.add(textSex);
        cont.add(buttonRecharge);
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
                String s2 = textvipID1.getText();
                if(!s1.isEmpty()||!s2.isEmpty()) {
                    if (!s1.isEmpty()) {
                        String sql = "select 姓名,性别,身份证号,贵宾ID,贵宾卡余额 from VIP where 身份证号 = ?";
                        st = con.prepareStatement(sql);
                        st.setString(1, s1);
                    } else if (!s2.isEmpty()) {
                        String sql = "select 姓名,性别,身份证号,贵宾ID,贵宾卡余额 from VIP where 贵宾ID = ?";
                        st = con.prepareStatement(sql);
                        st.setString(1, s2);
                    }
                    ResultSet rs = st.executeQuery();
                    if(rs.next())
                    {
                        textName.setText(rs.getString(1));
                        textSex.setText(rs.getString(2));
                        textRno2.setText(rs.getString(3));
                        textvipID2.setText(rs.getString(4));
                        textbalance.setText(rs.getString(5));
                    }
                }else{
                    JOptionPane.showMessageDialog(this,"身份证号和贵宾卡ID不允许都为空！");
                }

            }catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==buttonRecharge)
        {
            try{
                String s1 = textrecharge.getText();
                String s2 = textvipID2.getText();
                s1 = s1.trim();
                int x1 = Integer.parseInt(s1);
                if(s1.isEmpty()||s2.isEmpty())
                {
                    if(s1.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "充值金额不能为空!");
                    }else
                    {
                        JOptionPane.showMessageDialog(this, "无此贵宾!");
                    }
                }
                else {
                    String sql1 = "update 贵宾表 set 贵宾卡余额 = 贵宾卡余额+? where 贵宾ID = ?";
                    st = con.prepareStatement(sql1);
                    st.setInt(1,x1);
                    st.setString(2,s2);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(this,"充值成功！");
                }
            }catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this,"充值失败！");
                ex.printStackTrace();
            }
        }

    }
    public static void main(String[] args)
    {
        recharge_ui c = new recharge_ui();
    }
}
