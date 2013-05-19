package com.base64;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class Demo implements ClipboardOwner {

	private JFrame frame;
	private JPanel panelBanner;
	private JButton btnSelectFile;
	private JPanel panelFooter;
	private JButton btnEncode;
	private JButton btnCopy;
	private JLabel lblFileName;
	
	private String fName;
	private JScrollPane scrollPaneCenter;
	private JTextArea textAreaCipher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					Demo window = new Demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Demo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.frame = new JFrame();
		this.frame.setTitle("Base64 Encoder");
		this.frame.setBounds(100, 100, 800, 600);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.panelBanner = new JPanel();
		FlowLayout fl_panelBanner = (FlowLayout) this.panelBanner.getLayout();
		fl_panelBanner.setAlignment(FlowLayout.LEFT);
		this.frame.getContentPane().add(this.panelBanner, BorderLayout.NORTH);
		
		this.btnSelectFile = new JButton("Select File");
		this.btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				int return_val = jfc.showOpenDialog(Demo.this.frame);
				if(return_val == JFileChooser.APPROVE_OPTION) {
					fName = jfc.getSelectedFile().getAbsolutePath();
				}				
				lblFileName.setText(fName);				
			}
		});
		this.panelBanner.add(this.btnSelectFile);
		
		this.lblFileName = new JLabel("");
		this.panelBanner.add(this.lblFileName);
		
		this.panelFooter = new JPanel();
		this.frame.getContentPane().add(this.panelFooter, BorderLayout.SOUTH);
		
		this.btnEncode = new JButton("Encode");
		this.btnEncode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Base64Encoder benc = null;
				try {
					benc = Base64Encoder.getInstance(new FileInputStream(fName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				textAreaCipher.setText(benc.getBase64Value());
			}
		});
		this.panelFooter.add(this.btnEncode);
		
		this.btnCopy = new JButton("Copy to clipboard");
		this.btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				cb.setContents(new StringSelection(textAreaCipher.getText()), Demo.this);
				JOptionPane.showMessageDialog(Demo.this.frame, "Cipher copied to clipboard");
			}
		});
		this.panelFooter.add(this.btnCopy);
		
		this.scrollPaneCenter = new JScrollPane();
		this.scrollPaneCenter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.scrollPaneCenter.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.frame.getContentPane().add(this.scrollPaneCenter, BorderLayout.CENTER);
		
		this.textAreaCipher = new JTextArea();
		this.textAreaCipher.setLineWrap(true);
		this.scrollPaneCenter.setViewportView(this.textAreaCipher);
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
		
	}

}
