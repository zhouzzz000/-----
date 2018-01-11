import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.ImageGraphicAttribute;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VIP_manage  extends JFrame implements ActionListener
{
    private JPanel jPane1;
    private JScrollPane jpane2;
    private JTextField textRno;
    private JTextField textVipID;
    private JButton butSelect;
    private JTable table;
    private JLabel labBackground;
    private JButton buttonRecharge;
    private JButton buttonDelete;
    private ImageIcon bacIco = new ImageIcon("image//VIP_manage.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    private Container cont;
    private Connection con = null;
    private PreparedStatement st = null;
    private String s1[] = {"序号","姓名","贵宾卡ID","性别","身份证号","余额"};
    private int[] colWidth = {40,80,113,40,160,80};
    private int rowNum = 16;
    private int rowhigh = 29;
    private  int num = 0;
    private DefaultTableModel mod;
    public VIP_manage()
    {
        try{ UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        con = connectDatabase.getConnect();
        setTitle("开房历史查询");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(null);
        setSize(550,728);
        setResizable(false);
        setIconImage(icon);
        cont = this.getContentPane();
        ((JPanel)cont).setOpaque(false);
        labBackground = new JLabel(bacIco);
        labBackground.setBounds(0,0,550,700);
        this.getLayeredPane().add(labBackground,new Integer(Integer.MIN_VALUE));
        jPane1 = new JPanel();
        jPane1.setLayout(null);
        table_test ta = new table_test();
        table = ta.getTable(s1,colWidth,rowNum,rowhigh);
        mod = (DefaultTableModel) table.getModel();
        jpane2 = new JScrollPane(table);
        textRno = new JTextField();
        textVipID = new JTextField();
        butSelect = new JButton("查  询");
        buttonDelete = new JButton("删除");
        buttonRecharge = new JButton("充值");
        buttonDelete.setBounds(302,618,98,30);
        buttonRecharge.setBounds(150,618,98,30);
        butSelect.setBounds(202,58,140,42);
        textVipID.setBounds(396,19,132,26);
        textRno.setBounds(121,19,162,26);
        jPane1.setBounds(0,0,550,100);
        jpane2.setBounds(20,112,513,492);
        jPane1.setOpaque(false);
        jpane2.setOpaque(false);
        jpane2.getViewport().setOpaque(false);
        jPane1.add(butSelect);
        jPane1.add(textVipID);
        jPane1.add(textRno);
        butSelect.addActionListener(this);
        buttonRecharge.addActionListener(this);
        buttonDelete.addActionListener(this);
        cont.add(jPane1);
        cont.add(jpane2);
        cont.add(buttonDelete);
        cont.add(buttonRecharge);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = this.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        num = setTableData();
        setVisible(true);

    }
    private void tableCon()
    {

        if(num!=0)
        {

            while (num>rowNum)
            {
                mod.removeRow(num-1);
                num--;
            }
            while(num-->0)
            {
                table.setValueAt("",num,0);
                table.setValueAt("",num,1);
                table.setValueAt("",num,2);
                table.setValueAt("",num,3);
                table.setValueAt("",num,4);
                table.setValueAt("", num, 5);
            }
        }
    }

    public int setTableData() {
        int count = 0;

            try {

                String sql = "select 姓名,贵宾ID,性别,身份证号,贵宾卡余额 from 贵宾表";
                st = con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while(rs.next())
                {
                    if(count+1>rowNum)
                    {
                        String[] s = {String.valueOf(count+1).trim(),rs.getString(1).trim(),rs.getString(2).trim(),rs.getString(3).trim(),rs.getString(4).trim(),rs.getString(5).trim()};
                        mod.addRow(s);
                        count++;
                    }else {
                        table.setValueAt(count + 1, count, 0);
                        table.setValueAt(rs.getNString(1).trim(), count, 1);
                        table.setValueAt(rs.getNString(2).trim(), count, 2);
                        table.setValueAt(rs.getString(3).trim(), count, 3);
                        table.setValueAt(rs.getNString(4).trim(), count, 4);
                        table.setValueAt(rs.getInt(5), count, 5);
                        count++;
                    }
                }
            }catch (SQLException e)
            {
                e.printStackTrace();
            }

        return count;
    }
    public void actionPerformed(ActionEvent e)
    {


        if(e.getSource() == butSelect) {
            try {
                String s1 = textRno.getText();
                String s2 = textVipID.getText();
                if (s1.isEmpty() && s2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "身份证和贵宾卡ID不允许都为空!");
                    num = setTableData();
                } else {
                    String sql;
                    if (s1.isEmpty()) {
                        sql = "select 姓名,贵宾ID,性别,身份证号,贵宾卡余额 from 贵宾表 where 贵宾ID = ?";
                        st = con.prepareStatement(sql);
                        st.setString(1,s2);
                    }
                    else if (s2.isEmpty())
                    {
                        sql = "select 姓名,贵宾ID,性别,身份证号,贵宾卡余额 from 贵宾表 where 身份证号 = ?";
                        st = con.prepareStatement(sql);
                        st.setString(1,s1);
                    }
                    ResultSet rs = st.executeQuery();
                    if(rs.next())
                    {
                        tableCon();
                        table.setValueAt(1, 0, 0);
                        table.setValueAt(rs.getNString(1).trim(), 0, 1);
                        table.setValueAt(rs.getNString(2).trim(), 0, 2);
                        table.setValueAt(rs.getString(3).trim(), 0, 3);
                        table.setValueAt(rs.getNString(4).trim(), 0, 4);
                        table.setValueAt(rs.getInt(5), 0, 5);
                        num = 1;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"没有找到此人");
                        num = setTableData();
                    }
                }
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == buttonDelete)
        {
            try {
                   int index = table.getSelectedRow();
                   String s1 = table.getValueAt(index,2).toString();
                   String sql = "delete from 贵宾表 where 贵宾ID = ?";
                   String s2 = "是否删除贵宾:"+s1+"?";
                   int t = JOptionPane.showConfirmDialog(null,s2,"提示",JOptionPane.YES_NO_OPTION);
                   if(t == JOptionPane.YES_OPTION)
                    {
                        st = con.prepareStatement(sql);
                        st.setString(1, s1);
                        st.executeUpdate();
                        JOptionPane.showMessageDialog(this,"删除成功!");
                        tableCon();
                        num = setTableData();
                    }else if(t == JOptionPane.NO_OPTION)
                    {
                        JOptionPane.showMessageDialog(this,"删除取消!");
                    }


            }catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this,"删除失败!");
                ex.printStackTrace();
            }
        }
        if(e.getSource() == buttonRecharge)
        {
            try {
                con.close();
                recharge_ui rec = new recharge_ui();
                this.setVisible(false);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    }
    public static void main(String[] args)
    {
        VIP_manage vv = new VIP_manage();
    }
}
