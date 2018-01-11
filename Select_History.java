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

public class Select_History  extends JFrame implements ActionListener
{
    private JPanel jPane1;
    private JScrollPane jpane2;
    private JTextField textRno;
    private JTextField textDate;
    private JButton butSelect;
    private JTable table;
    private JLabel labBackground;
    private ImageIcon bacIco = new ImageIcon("image//room_history.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    private Container cont;
    private Connection con = null;
    private PreparedStatement st = null;
    private String s1[] = {"序号","姓名","身份证号","房间号","入住时间","离开时间"};
    private int[] colWidth = {40,80,113,60,110,110};
    private int rowNum = 18;
    private int rowhigh = 29;
    private  int num = 0;
    private DefaultTableModel mod;
    public Select_History()
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
        textDate = new JTextField();
        butSelect = new JButton("查  询");
        butSelect.setBounds(202,58,140,42);
        textDate.setBounds(396,19,132,26);
        textRno.setBounds(129,19,162,26);
        jPane1.setBounds(0,0,550,100);
        jpane2.setBounds(20,112,513,551);
        jPane1.setOpaque(false);
        jpane2.setOpaque(false);
        jpane2.getViewport().setOpaque(false);
        jPane1.add(butSelect);
        jPane1.add(textDate);
        jPane1.add(textRno);
        butSelect.addActionListener(this);
        cont.add(jPane1);
        cont.add(jpane2);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = this.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == butSelect) {

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
            num = setTableData();

        }
    }
    public int setTableData() {
        int count = 0;
        String rno = textRno.getText();
        String data = textDate.getText();
        if (rno.isEmpty() && data.isEmpty()) JOptionPane.showMessageDialog(this, "身份证号和日期不能都为空！");
        else {
            try {
                if (data.isEmpty()) {
                    String sql = "select 姓名,身份证号,房间号,入住时间,退房时间 from 住房历史表 where 身份证号 = ?";
                    st = con.prepareStatement(sql);
                    st.setString(1,rno);
                } else if (rno.isEmpty()) {
                    String sql = "select 姓名,身份证号,房间号,入住时间,退房时间 from 住房历史表 where 入住时间 = ?";
                    st = con.prepareStatement(sql);
                    st.setString(1,data);
                } else{
                    String sql = "select 姓名,身份证号,房间号,入住时间,退房时间 from 住房历史表 where 入住时间 = ? and 身份证号 = ?";
                    st = con.prepareStatement(sql);
                    st.setString(1,data);
                    st.setString(2,rno);
                }
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
                        table.setValueAt(rs.getNString(3).trim(), count, 3);
                        table.setValueAt(rs.getNString(4).trim(), count, 4);
                        table.setValueAt(rs.getNString(5).trim(), count, 5);
                        count++;
                    }
                }
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        Select_History h = new Select_History();
    }
}
