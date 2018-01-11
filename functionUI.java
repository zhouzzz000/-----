import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class functionUI extends JFrame implements ActionListener
{
    private JButton emptyRoom;
    private JButton handleRoom;
    private JButton historyRoom;
    private JButton manageVip;
    private JButton exitRoom;
    private JButton setting;
    private JButton visitor;
    private JButton exit;
    private Container mainPanel;
    private JPanel jpane1;
    private JLabel labe1;
    private ImageIcon img1 = new ImageIcon("image//function.jpg");
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    public functionUI()
    {
        try{ UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        mainPanel = this.getContentPane();
        setSize(1024,795);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(icon);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = this.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        setLayout(null);
        setVisible(true);
        setTitle("管理员界面");
        jpane1 = new JPanel();
        jpane1.setSize(1024,768);
        jpane1.setBounds(0,0,1024,800);
        jpane1.setLayout(null);
        emptyRoom = new JButton(new ImageIcon("image//Button//FJCX.png",""));
        handleRoom = new JButton(new ImageIcon("image//Button//KFCL.jpg",""));
        historyRoom = new JButton(new ImageIcon("image//Button//KFLS.jpg",""));
        manageVip = new JButton(new ImageIcon("image//Button//GBGL.png",""));
        exitRoom = new JButton(new ImageIcon("image//Button//TF.jpg",""));
        setting = new JButton(new ImageIcon("image//Button//YGGL.png",""));
        exit = new JButton();
        visitor = new JButton(new ImageIcon("image//Button//FKGL.png",""));
        labe1 = new JLabel(img1);
        labe1.setBounds(0,0,1024,768);
        emptyRoom.setBounds(new Rectangle(0,154,256,192));
        handleRoom.setBounds(new Rectangle(258,173,256,192));
        historyRoom.setBounds(new Rectangle(512,173,256,192));
        manageVip.setBounds(new Rectangle(768,152,256,192));
        exitRoom.setBounds(new Rectangle(0,424,256,192));
        setting.setBounds(new Rectangle(258,405,256,192));
        exit.setBounds(new Rectangle(768,424,256,192));
        visitor.setBounds(512,405,258,187);
        jpane1.setOpaque(false);
        visitor.setOpaque(false);
        exit.setOpaque(false);
        setting.setOpaque(false);
        exit.setContentAreaFilled(false);
        this.getLayeredPane().add(labe1,new Integer(Integer.MIN_VALUE));
        jpane1.add(visitor);
        jpane1.add(emptyRoom);
        jpane1.add(handleRoom);
        jpane1.add(historyRoom);
        jpane1.add(manageVip);
        jpane1.add(exitRoom);
        jpane1.add(setting);
        jpane1.add(exit);
        mainPanel.add(jpane1);
        jpane1.setVisible(true);
        ((JPanel)mainPanel).setOpaque(false);
        (this).getLayeredPane().add(jpane1,new Integer(Integer.MAX_VALUE));
        emptyRoom.addActionListener(this);
        handleRoom.addActionListener(this);
        historyRoom.addActionListener(this);
        manageVip.addActionListener(this);
        exitRoom.addActionListener(this);
        setting.addActionListener(this);
        exit.addActionListener(this);
        visitor.addActionListener(this);
    }
    public void actionPerformed(ActionEvent en)
    {
        if(en.getSource()==emptyRoom)
        {
            Select_Room sr = new Select_Room();
        }
        if(en.getSource() == historyRoom)
        {
            Select_History sh = new Select_History();
        }
        if(en.getSource()==manageVip)
        {
            VIP_manage vip = new VIP_manage();
        }
        if(en.getSource()==exitRoom)
        {
            cancel_room ca = new cancel_room();
        }
        if(en.getSource()==setting)
        {
            Staff_manage sta = new Staff_manage();
        }
        if(en.getSource()==handleRoom)
        {
            AddCustomerInfo Add = new AddCustomerInfo();
        }
        if(en.getSource() == visitor)
        {
            Visitor vis = new Visitor();
        }
        if(en.getSource()==exit)
        {
            MainFrame M = new MainFrame();
            this.setVisible(false);
            M.setVisible(true);
        }
    }
    public static void main(String[] args)
    {
        functionUI f = new functionUI();

    }
}
