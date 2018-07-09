package gui;

import java.awt.*;

import javax.swing.*;

import models.Ticket;


public class MainWindow extends JFrame
{

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private Dimension windowSize = new Dimension(550, 800);

	public MainWindow()
	{
		super("BugTrackerView - Master View");
		setSize(windowSize.height, windowSize.width);
		// setLocation(((screenSize.height/2)-(windowSize.height/2)),
		// ((screenSize.width/2)-(windowSize.width/2)));
		setLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(desktopPane);

		JLabel lblPrjs = new JLabel("Project:");
		lblPrjs.setBounds(10, 5, 61, 15);
		desktopPane.add(lblPrjs);

		// TODO -- use core to import list of projects using Hashmaps
		Choice choice = new Choice();
		for( int i = 0; i < 10; i++ )
		{
			choice.add("" + i);
		}
		choice.setBounds(10, 20, 200, 25);
		desktopPane.add(choice);

		JLabel lblTkts = new JLabel("Tickets");
		lblTkts.setBounds(10, 60, 61, 15);
		desktopPane.add(lblTkts);

		// TODO -- use core to import list of tickets on selected project from 'choice'
		// possibly with Hashmaps
		List list = new List();
		for( int i = 0; i < 10; i++ )
		{
			list.add("" + i);
		}
		list.setBackground(Color.WHITE);
		list.setBounds(10, 75, 200, 443);
		desktopPane.add(list);

		JTextPane txtpnTestInformation = new JTextPane();
		txtpnTestInformation.setText("Test Information, Description will go here");
		txtpnTestInformation.setBounds(236, 47, 479, 59);
		desktopPane.add(txtpnTestInformation);

		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(70, 385, 1, 1);
		desktopPane.add(verticalGlue);

		/*
		 * TODO - add panes and components
		 */
	}

	private void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch ( Exception e )
		{
			System.out.println("Failed to load 'look and feel'");
		}
	}

	BorderLayout l1 = new BorderLayout();

	public static void main(String[] args)
	{
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
	}
}
