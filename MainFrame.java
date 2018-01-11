import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainFrame extends JFrame implements ActionListener
{
    private JPanel contentPane;//主面板
    private JMenuBar jMenuBar1 ;
    private JMenu jMenuFile ;
    private JMenuItem jMenuFileExit ;
    private JMenu jMenu1;
    private JMenuItem jMenuItem1;
    private JMenu jMenu2 ;
    //上面创建菜单栏
    private JLabel jLabel ;
    private JLabel jLabe2 ;
    private Image icon = Toolkit.getDefaultToolkit().getImage("image//logo.jpg");
    private ImageIcon img1 = new ImageIcon("image//mainframe.jpg");
    private JLabel labImg;
    public MainFrame()
    {
        try{ UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.white);
        setSize(new Dimension(500,430));//框架大小
        setTitle("宾馆管理系统");//框架标题
        setResizable(false);
        super.setBackground(Color.white);
        super.setIconImage(icon);
       jMenuBar1 = new JMenuBar();
       jMenuFile = new JMenu("文件");
       jMenuFileExit = new JMenuItem("退出");
       jMenu1 = new JMenu("宾馆管理");
       jMenuItem1 = new JMenuItem("管理员登陆");
       jMenu2 = new JMenu("住房查询");
        //上面创建菜单栏
       jLabel = new JLabel("宾馆管理系统");
       jLabe2 = new JLabel("2017年12月７日");
       labImg = new JLabel(img1);
       labImg.setBounds(0,0,500,400);
       contentPane.setOpaque(false);
       super.getLayeredPane().add(labImg,new Integer(Integer.MIN_VALUE));
        try{
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Init();
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }


    private void Init() throws Exception
    {

        //创建内容面板和其布局

        //添加监听器
        jMenuItem1.addActionListener(this);
        jMenuFileExit.addActionListener(this);
        setJMenuBar(jMenuBar1);//添加菜单条
        //添加菜单组件到菜单条
        jMenuBar1.add(jMenuFile);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenuFileExit);
        //添加选项到菜单组件
        jMenuFile.add(jMenuFileExit);
        jMenu1.add(jMenuItem1);
        //添加标签到内容面板
        contentPane.add(jLabel);
        contentPane.add(jLabe2);
        //设置标签大小和字体
        jLabel.setFont(new java.awt.Font("宋体",Font.BOLD,25));
        jLabel.setBounds(new Rectangle(140,135,275,55));
        jLabe2.setFont(new java.awt.Font("宋体",Font.BOLD,20));
        jLabe2.setBounds(new Rectangle(160,190,200,35));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = this.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        this.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
    }
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == jMenuFileExit)//点击的菜单下的“退出”菜单项
        {
            System.exit(0);
        }
        if(actionEvent.getSource() == jMenuItem1)//点击添加学生菜单项
        {
            login ff = new login();
            this.setVisible(false);
        }
    }
}
