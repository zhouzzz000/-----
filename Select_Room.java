import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Select_Room extends JFrame implements ActionListener{
    private JTextField textRoom;
    private JPanel jPane1;
    private JScrollPane jPane2;
    private JButton butSelect;
    private JLabel backLab;
    private ImageIcon img1;
    private Container content;
    private JComboBox com1;
    private JComboBox com2;
    private JTable table;
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    private Connection con = null;
    private PreparedStatement st = null;
    private String[] s2 = {"是","否","全部"};
    private String[] s1 = {"单人间","双人间","总统间"};
    private String[] s3 = {"序号","房间号","房间类型","价格","身份证号"};
    int rownum = 18;
    int[] colWidth = {50,100,100,100,163};
    int rowhigh = 29;
    int num = 0;
    private DefaultTableModel mod ;
    public Select_Room()
    {

        try{ UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        setTitle("房间查询");
        con = connectDatabase.getConnect();
        content = this.getContentPane();
        setSize(550,728);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLayout(null);
        this.setIconImage(icon);
        com1 = new JComboBox(s1);
        com2 = new JComboBox(s2);
        jPane1 = new JPanel();
        jPane1.setLayout(null);
        table_test ta = new table_test();
        table = ta.getTable(s3,colWidth,rownum,rowhigh);
        mod = (DefaultTableModel) table.getModel();
        jPane2 = new JScrollPane(table);
        butSelect = new JButton("查询");
        textRoom = new JTextField();
        butSelect.setBounds(new Rectangle(202,58,140,42));
        com2.setBounds(new Rectangle(465,19,68,26));
        com1.setBounds(new Rectangle(270,19,85,26));
        textRoom.setBounds(new Rectangle(93,19,63,26));
        jPane1.setBounds(0,0,550,100);
        jPane2.setBounds(20,112,513,551);
        com1.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        com2.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        img1 = new ImageIcon("image//select_room.jpg");
        backLab = new JLabel(img1);
        butSelect.setFont(new java.awt.Font("方正幼圆",Font.PLAIN,18));
        backLab.setBounds(0,0,550,700);
        ((JPanel)content).setOpaque(false);
        this.getLayeredPane().add(backLab,new Integer(Integer.MIN_VALUE));
        jPane2.add(table.getTableHeader());
        jPane2.setOpaque(false);
        jPane2.getViewport().setOpaque(false);
        table.setOpaque(false);
        jPane1.setOpaque(false);
        jPane1.add(butSelect);
        jPane1.add(com1);
        jPane1.add(com2);
        jPane1.add(textRoom);
        content.add(jPane1);
        content.add(jPane2);
        butSelect.addActionListener(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = this.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        setVisible(true);
    }
    public void actionPerformed (ActionEvent e)
    {

        if(e.getSource() ==  butSelect)
        {
            if(num!=0)
            {

                while (num>rownum)
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
                }
            }
            if(!textRoom.getText().isEmpty())
            {
               num = setTableData_rno();
            }
            else{
               num = setTableData_else();
            }

        }


    }
    private int setTableData_rno()
    {
        String rno = textRoom.getText();
        textRoom.setText("");
        int row = 0;
        if(!rno.isEmpty())
        {
            boolean t = false;
            try {
                String sql = "Select 房间表.房间号,类型,价格,客户身份证号 from 房间表,房间使用表 where 房间表.房间号= ? and 房间使用表.房间号 = 房间表.房间号";
                String sql1 = "Select 房间表.房间号,类型,价格 from 房间表 where 房间表.房间号= ? ";
                st = con.prepareStatement(sql);
                st.setString(1,rno);
                ResultSet rs = st.executeQuery();
                if(rs.next()) {
                        t = true;
                        table.setValueAt("1", 0, 0);
                        table.setValueAt(rs.getString(1).trim(), 0, 1);
                        table.setValueAt(rs.getString(2).trim(), 0, 2);
                        table.setValueAt(String.valueOf(rs.getInt(3)).trim(), 0, 3);
                        table.setValueAt(rs.getString(4).trim(), 0, 4);

                }
                else{
                    st = con.prepareStatement(sql1);
                    st.setString(1,rno);
                    rs = st.executeQuery();
                    if (rs.next())
                    {
                        t = true;
                        table.setValueAt("1", 0, 0);
                        table.setValueAt(rs.getString(1).trim(), 0, 1);
                        table.setValueAt(rs.getString(2).trim(), 0, 2);
                        table.setValueAt(String.valueOf(rs.getInt(3)).trim(), 0, 3);
                    }

                }
                if(!t)
                {
                    JOptionPane.showMessageDialog(this,"房间号不存在!");

                }
                rs.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return 1;
    }
    private int setTableData_else()
    {
        boolean t = false;
        int count = 0;
        try{
            String sql,sql1;
            String form = (String)com1.getSelectedItem();
            String is_empty = (String)com2.getSelectedItem();
            if(is_empty!="全部")
            {
                if(is_empty=="是"){
                    sql = "select 房间表.房间号,类型,价格 from 房间表 where 类型 = ? and 房间表.房间号 not in(select 房间号 from 房间使用表)";
                    st = con.prepareStatement(sql);
                    st.setString(1,form);
                    ResultSet rs1 = st.executeQuery();
                    st.clearParameters();
                    while (rs1.next())
                    {
                        if(rs1.getRow()>rownum)
                        {
                            String[] s = {String.valueOf(count+1).trim(),rs1.getString(1).trim(),rs1.getString(2).trim(),rs1.getString(3).trim()};
                            mod.addRow(s);
                            count++;
                        }else {
                            table.setValueAt(count + 1, count, 0);
                            table.setValueAt(rs1.getString(1).trim(), count, 1);
                            table.setValueAt(rs1.getString(2).trim(), count, 2);
                            table.setValueAt(String.valueOf(rs1.getInt(3)).trim(), count, 3);
                            count++;
                        }

                    }
                    rs1.close();
                }
                else{
                    sql = "select 房间表.房间号,类型,价格,客户身份证号 from 房间表,房间使用表 where 类型 = ? and 房间表.房间号 = 房间使用表.房间号";
                    st = con.prepareStatement(sql);
                    st.setString(1,form);
                    ResultSet rs2 = st.executeQuery();
                    while (rs2.next())
                    {
                        if(count+1>rownum)
                        {
                            String[] s = {String.valueOf(count+1).trim(),rs2.getString(1).trim(),rs2.getString(2).trim(),rs2.getString(3).trim(),rs2.getString(4).trim()};
                            mod.addRow(s);
                            count++;
                        }else {
                            table.setValueAt(count + 1, count, 0);
                            table.setValueAt(rs2.getNString(1).trim(), count, 1);
                            table.setValueAt(rs2.getNString(2).trim(), count, 2);
                            table.setValueAt(String.valueOf(rs2.getInt(3)).trim(), count, 3);
                            table.setValueAt(rs2.getNString(4).trim(), count, 4);
                            count++;
                        }
                    }
                    rs2.close();
                }
            }
            else {
                sql = "select 房间表.房间号,类型,价格 from 房间表 where 类型 = ? and 房间表.房间号 not in(select 房间号 from 房间使用表)";
                sql1 = "select 房间表.房间号,类型,价格,客户身份证号 from 房间表,房间使用表 where 类型 = ? and 房间表.房间号 = 房间使用表.房间号";
                st = con.prepareStatement(sql1);
                st.setString(1,form);
                ResultSet rs4 = st.executeQuery();
                while (rs4.next())
                {
                    if(count+1>rownum)
                    {
                        String[] s = {String.valueOf(count+1).trim(),rs4.getString(1).trim(),rs4.getString(2).trim(),rs4.getString(3).trim(),rs4.getString(4).trim()};
                        mod.addRow(s);
                        count++;
                    }else {
                        table.setValueAt(count + 1, count, 0);
                        table.setValueAt(rs4.getString(1).trim(), count, 1);
                        table.setValueAt(rs4.getString(2).trim(), count, 2);
                        table.setValueAt(String.valueOf(rs4.getInt(3)), count, 3);
                        table.setValueAt(rs4.getString(4).trim(), count, 4);
                        count++;
                    }
                }
                rs4.close();
                st = con.prepareStatement(sql);
                st.setString(1,form);
                ResultSet rs3 = st.executeQuery();
                while (rs3.next())
                {
                    if(count+1>rownum)
                    {
                        String[] s = {String.valueOf(count+1).trim(),rs3.getString(1).trim(),rs3.getString(2).trim(),rs3.getString(3).trim(),""};
                        mod.addRow(s);
                        count++;
                    }
                    else {
                        table.setValueAt(count + 1, count, 0);
                        table.setValueAt(rs3.getString(1).trim(), count, 1);
                        table.setValueAt(rs3.getString(2).trim(), count, 2);
                        table.setValueAt(String.valueOf(rs3.getInt(3)).trim(), count, 3);
                        table.setValueAt("", count, 4);
                        count++;
                    }
                }
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return count;
    }
    public void finalize()
    {
        try
        {
            con.close();
            st.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
            Select_Room test0 = new Select_Room();

        }
}

