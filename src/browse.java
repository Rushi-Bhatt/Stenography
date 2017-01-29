import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class browse implements ActionListener {

	JButton jb,jb_en,jb_de;
	JFileChooser jfc;
	JFrame jf;
	FileNameExtensionFilter filter1,filter2,filter3;
	File file;
	BufferedImage image;
	JLabel jlb1;
	String path,file_name;
	JTextArea jta_in,jta_out;
	JScrollPane jsp1,jsp2;
	char[] ext=new char[3];
	
	browse()
	{		
		jf=new JFrame();	
	
		jta_in=new JTextArea("input here");
		
		jta_out=new JTextArea();
		jta_out.setEditable(false);
		jta_in.setColumns(20);
		jta_out.setColumns(20);
		
		jsp1=new JScrollPane(jta_in);
		jsp2=new JScrollPane(jta_out);
		jsp1.setPreferredSize(new Dimension(250, 100));
		jsp2.setPreferredSize(new Dimension(250, 100));
		
		jb=new JButton("browse");
		jb_en=new JButton("encrypt");
		jb_de=new JButton("decrypt");
		jb.addActionListener(this);
		jb_en.addActionListener(this);
		jb_de.addActionListener(this);
		
		jlb1=new JLabel("",null,JLabel.CENTER);
		
		jlb1.setPreferredSize(new Dimension(450,450));
		jlb1.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		
		 
		filter1 = new FileNameExtensionFilter("PNG", "png" );
		filter2 = new FileNameExtensionFilter("BMP", "bmp" );
		filter3 = new FileNameExtensionFilter("PNG & BMP ","png","bmp");
		
		jfc=new JFileChooser();
		jfc.setFileFilter(filter1);
		jfc.setFileFilter(filter2);
		jfc.setFileFilter(filter3);
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		jf.setSize(900,700);
		jf.setResizable(false);
		jf.setLayout(new FlowLayout());
		jf.add(jb);
		jf.add(jlb1);
		jf.add(jb_en);
		jf.add(jb_de);
		jf.add(jsp1);
		jf.add(jsp2);
		
		jf.setResizable(false);
		jf.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getActionCommand()=="browse")
		{
			 int returnVal = jfc.showOpenDialog(jf);
		
			 if (returnVal == JFileChooser.APPROVE_OPTION)
			 {
				 File file = jfc.getSelectedFile();
				 path = file.getPath();
				 file_name=file.getName();
				 file_name.getChars(file_name.indexOf('.')+1, file_name.length(), ext, 0);
				 
				 
				 path=path.replace("\\", "/");
				// System.out.println(path+"  "+String.copyValueOf(ext));
				 
				 file=new File(path);
				
				 try
				 {
					image=ImageIO.read(file);
				 }
				 catch(IOException io)
				 {}
				 
					
				 if(image.getWidth()>jlb1.getWidth() || image.getHeight()>jlb1.getHeight())
					 jlb1.setIcon(new ImageIcon(image.getScaledInstance(jlb1.getWidth(), jlb1.getHeight(),Image.SCALE_SMOOTH)));
				 
				 else
					 jlb1.setIcon(new ImageIcon(image));
				
				
			 }
		}
		else if(ae.getActionCommand()=="encrypt")
		{	 
				if(path!=null)
			    try 
			    {
					new steno(path,String.copyValueOf(ext),jta_in.getText());
				}
			    catch (IOException e) {}
		}	
		
		else if(ae.getActionCommand()=="decrypt")
		{
			    if(path!=null)
			    try 
				{	 
			    	
					decrypt d=new decrypt(String.copyValueOf(ext));
					jta_out.setText(d.output);
				} 
				catch (IOException e) {}
		}
	}
	
	public static void main(String args[])
	{
		new browse();
	}
}
