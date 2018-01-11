import java.awt.*;
import java.sql.Connection;
import javax.swing.*;

public class Main {
    public static  void main(String[] args)
    {
        MainFrame frame = new MainFrame();//主界面
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
        Dimension frameSize = frame.getSize();//获取主界面尺寸
        if(frameSize.height>screenSize.height)frameSize.height = screenSize.height;
        if(frameSize.width>screenSize.width)frameSize.width = screenSize.width;//主界面居中
        frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
        frame.setVisible(true);
    }
}
