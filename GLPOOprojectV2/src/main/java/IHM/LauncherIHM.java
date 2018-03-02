package IHM;


import java.awt.EventQueue;

import org.apache.log4j.Logger;

import IHM.frame.TirageJFrame;
import IHM.frame.WelcomeJFrame;
import IHM.frame.otherWelcomeJFrame;

public class LauncherIHM {
	private static final Logger LOGGER = Logger.getLogger(LauncherIHM.class);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeJFrame window = new WelcomeJFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
