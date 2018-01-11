import com.sun.java.swing.plaf.windows.WindowsButtonUI;
import com.sun.java.swing.plaf.windows.WindowsTableHeaderUI;

import javax.swing.table.*;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
public class table_test extends JFrame {
    public JTable table=null;
    public JScrollPane jp;

    //返回表格
    public JTable getTable(String[] s,int[] columnWidth,int colnum,int rowhigh){
        try{ UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        if(table==null){
            table=new JTable();
            DefaultTableModel model=new DefaultTableModel(s,colnum);
            table.setModel(model);
            TableColumnModel columnModel=table.getColumnModel();
            int count=columnModel.getColumnCount();
            for(int i=0;i<count;i++){
                javax.swing.table.TableColumn column=columnModel.getColumn(i);
                column.setPreferredWidth(columnWidth[i]);
            }

            renderer.setOpaque(false);//render单元格的属性
//遍历表格中所有列，将其渲染器设置为renderer
            for(int i = 0 ; i < s.length; i ++)
            {
                table.getColumn(s[i]).setCellRenderer(renderer);
            }
            table.setRowHeight(rowhigh);
            table.setOpaque(false);
        }
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        JTableHeader myt = table.getTableHeader();
        DefaultTableCellRenderer hr = (DefaultTableCellRenderer) myt.getDefaultRenderer();
        hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        myt.setOpaque(false);
        // myt.setUI(new WindowsTableHeaderUI());
        myt.setReorderingAllowed(false);
        myt.setResizingAllowed(false);
        return table;
    }

    //返回JPanel
    public JScrollPane getJpanel(String[] s,int[] columnWidth,int colnum,int rowhigh){
        try{ UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        if(table==null){
            table=new JTable();
            DefaultTableModel model=new DefaultTableModel(s,colnum);
            table.setModel(model);
            TableColumnModel columnModel=table.getColumnModel();
            int count=columnModel.getColumnCount();
            for(int i=0;i<count;i++){
                javax.swing.table.TableColumn column=columnModel.getColumn(i);
                column.setPreferredWidth(columnWidth[i]);
            }
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setOpaque(false);//render单元格的属性
//遍历表格中所有列，将其渲染器设置为renderer
            for(int i = 0 ; i < s.length; i ++)
            {
                table.getColumn(s[i]).setCellRenderer(renderer);
            }
            table.setRowHeight(rowhigh);
            table.setOpaque(false);
        }
        jp =new JScrollPane(table);
        JTableHeader myt = table.getTableHeader();
        DefaultTableCellRenderer hr = (DefaultTableCellRenderer) myt.getDefaultRenderer();
        hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        myt.setOpaque(false);
       // myt.setUI(new WindowsTableHeaderUI());
        myt.setReorderingAllowed(false);
        myt.setResizingAllowed(false);
        jp.add(myt);
        jp.setOpaque(false);
        jp.getViewport().setOpaque(false);
        return jp;
    }


}