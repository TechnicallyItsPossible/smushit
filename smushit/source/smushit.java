import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


//compression libraries

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.*;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;


public class smushit
{
    public static void main(String args[])
    {
        x1 obj=new x1();
    }
}
class x1 extends JFrame
{

    


    JTextField p,f;
    JLabel l,h1,h2;
    JButton b;
    public x1()
    {   
        h1=new JLabel("Filename(.jpg)"); 
        h2=new JLabel("Quality factor (in decimal)"); 
        p=new JTextField(25);
        f=new JTextField(25);
        l=new JLabel(" "); 
        b=new JButton("Smushit");
        l.setForeground(Color.white);
        h1.setForeground(Color.white);
        h2.setForeground(Color.white);
        b.setBackground(Color.darkGray);
        b.setForeground(Color.white);
        f.setBackground(Color.darkGray);
        f.setForeground(Color.white);
        p.setBackground(Color.darkGray);
        p.setForeground(Color.white);
        add(h1);
        add(p);
        add(h2);
        add(f);
        add(l);
        add(b);
        ActionListener al=new ActionListener()
        {
          public void actionPerformed(ActionEvent ae)
          {
              try
              {
            String path=p.getText();
            Float factor=Float.parseFloat(f.getText());
            //Compression code called
            File imageFile = new File(path);
        File compressedImageFile = new File(path+"compressed.jpg");
 
        InputStream is = new FileInputStream(imageFile);
        OutputStream os = new FileOutputStream(compressedImageFile);
 
        float quality = factor;
 
        // create a BufferedImage as the result of decoding the supplied InputStream
        BufferedImage image = ImageIO.read(is);
 
        // get all image writers for JPG format
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
 
        if (!writers.hasNext())
            throw new IllegalStateException("No writers found");
 
        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);
 
        ImageWriteParam param = writer.getDefaultWriteParam();
 
        // compress to a given quality
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);
 
        // appends a complete image stream containing a single image and
        //associated stream and image metadata and thumbnails to the output
        writer.write(null, new IIOImage(image, null, null), param);
 
        // close all streams
        is.close();
        os.close();
        ios.close();
        writer.dispose();
           }
        
           catch(Exception e)
           {
               System.out.println();
           }
            l.setText("Smushed");
          } 
        };
        b.addActionListener(al);
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
    }
    
}