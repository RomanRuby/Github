package methods_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by PH on 30.10.2016.
 */
public class AboutFrame extends JFrame {
    static final int WIDTH=100;
    static final int HEIGHT=100;

    public AboutFrame(){
        super("О программе");
        setSize(WIDTH,HEIGHT);
        this.setResizable(false);// no one change size
        Toolkit kit=Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);

        Box hBoxClose=Box.createHorizontalBox();
        JButton btnClose= new JButton("Закрыть");
        btnClose.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent arg0) {
             setVisible(false);
         }
                                   }
        );
        hBoxClose.add(Box.createHorizontalGlue());
        hBoxClose.add(btnClose);
        hBoxClose.add(Box.createHorizontalGlue());

        Box hBoxData=Box.createVerticalBox();
        hBoxData.add(Box.createVerticalGlue());
        JLabel lblname= new JLabel("Nahibau");
        JLabel lblgroup= new JLabel("Group 7");
        JLabel lblimage= new JLabel("");
        Image img=kit.getImage("");
        ImageIcon icon =new ImageIcon();
        icon.setImage(img);
        lblimage.setIcon(icon);
        hBoxData.add(lblimage);
        hBoxData.add(lblname);
        hBoxData.add(lblgroup);
        hBoxData.add(Box.createVerticalGlue());

        Box hBoxContent=Box.createVerticalBox();
        hBoxContent.add(hBoxData);
        hBoxContent.add(hBoxClose);
        getContentPane().add(hBoxContent, BorderLayout.CENTER);
    }
}
