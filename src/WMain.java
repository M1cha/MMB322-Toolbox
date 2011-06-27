import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;


public class WMain {
	private JFrame frmMeteorittoolbox;
	private JTextField txtIP;
	private ADB adb = new ADB(this);
	private Refresher rf = new Refresher(this);
	private boolean connState = false;
	private JFileChooser fc = new JFileChooser();
	
	private JButton btnVerbinden;
	private JTextField txtKernelInfo;
	private JTextField txtFWInfo;
	private JTextField txtCmdLine;
	private JScrollPane scrollPane;
	private JTextArea txtLog;
	private JLabel lblWarning;
	private JLabel lblMarketStatus;
	private JLabel lblMarketAccess;
	private JLabel lblsubin;
	private JLabel lblSuperuser;
	private JButton btnReload;
	private JButton btnReboot;
	private JButton btnHotreboot;
	private JButton btnWipe;
	private JButton btnRecoveryreboot;
	private JButton btnInstallMarket;
	private JButton btnUninstallMarket;
	private JButton btnUnlockMarket;
	private JButton btnLockMarket;
	private JButton btnInstallSU;
	private JButton btnUninstallSU;
	private JButton btnInstallSuperuser;
	private JButton btnUninstallSuperuser;
	private final Action action = new SwingAction();
	private JPanel panelProps;
	private JScrollPane scrollPaneProps;
	private JTable propTable = new JTable();
	private JComboBox propFilePath;
	private JButton btnLoadProp;
	private JButton btnSaveProp;
	private JButton btnResetPropList;
	private String tmpPropFile = "";
	private String tmpPropFileName = "";
	private JLabel lblPropLoadedFileName;
	private JButton btnCloseProp;
	private JScrollPane scrollPane_1;
	private JButton btnOpenAdbScript;
	private JTextArea txtScriptEditor;
	private JButton btnRunAdbScript;
	private JLabel lblScript;
	private JScrollPane scrollPane_2;
	private JTextArea txtScriptOutput;
	private JLabel lblAusgabe;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WMain window = new WMain();
					window.frmMeteorittoolbox.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WMain() {
		initialize();
		rf.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMeteorittoolbox = new JFrame();
		frmMeteorittoolbox.getContentPane().setBackground(Color.WHITE);
		frmMeteorittoolbox.setResizable(false);
		frmMeteorittoolbox.setTitle("MMB322-ToolBox");
		frmMeteorittoolbox.setBounds(100, 100, 758, 485);
		frmMeteorittoolbox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMeteorittoolbox.getContentPane().setLayout(null);
		
		txtIP = new JTextField();
		txtIP.addKeyListener(new KeyAdapter() {
			// CONNECT ON ENTER
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnClick();
				}
			}
		});
		txtIP.setBounds(84, 8, 108, 20);
		frmMeteorittoolbox.getContentPane().add(txtIP);
		txtIP.setColumns(10);
		
		
		JTabbedPane tabPaneInfo = new JTabbedPane(JTabbedPane.TOP);
		tabPaneInfo.setBackground(Color.WHITE);
		tabPaneInfo.setBounds(0, 40, 752, 261);
		frmMeteorittoolbox.getContentPane().add(tabPaneInfo);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(Color.WHITE);
		tabPaneInfo.addTab("Infos", null, panelInfo, null);
		panelInfo.setLayout(null);
		
		JLabel lblCmdline = new JLabel("CmdLine:");
		lblCmdline.setBounds(12, 74, 65, 16);
		panelInfo.add(lblCmdline);
		
		JLabel lblKernel = new JLabel("Kernel:");
		lblKernel.setBounds(12, 43, 65, 16);
		panelInfo.add(lblKernel);
		
		txtKernelInfo = new JTextField();
		txtKernelInfo.setBounds(87, 41, 599, 20);
		panelInfo.add(txtKernelInfo);
		txtKernelInfo.setEditable(false);
		txtKernelInfo.setColumns(10);
		
		JLabel lblFirmware = new JLabel("Firmware:");
		lblFirmware.setBounds(12, 12, 65, 16);
		panelInfo.add(lblFirmware);
		
		txtFWInfo = new JTextField();
		txtFWInfo.setBounds(87, 10, 599, 20);
		panelInfo.add(txtFWInfo);
		txtFWInfo.setEditable(false);
		txtFWInfo.setColumns(10);
		
		txtCmdLine = new JTextField();
		txtCmdLine.setBounds(87, 72, 599, 20);
		panelInfo.add(txtCmdLine);
		txtCmdLine.setEditable(false);
		txtCmdLine.setColumns(10);
		
		JPanel panelTools = new JPanel();
		panelTools.setBackground(Color.WHITE);
		tabPaneInfo.addTab("Tools", null, panelTools, null);
		panelTools.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 11, 228, 211);
		panel_1.setBorder(new TitledBorder(null, "Google-Market", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTools.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStatus.setBounds(10, 27, 46, 14);
		panel_1.add(lblStatus);
		
		lblMarketStatus = new JLabel("");
		lblMarketStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMarketStatus.setBounds(66, 27, 135, 14);
		panel_1.add(lblMarketStatus);
		
		JLabel lblZugriff = new JLabel("Zugriff:");
		lblZugriff.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblZugriff.setBounds(10, 52, 46, 14);
		panel_1.add(lblZugriff);
		
		lblMarketAccess = new JLabel("");
		lblMarketAccess.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMarketAccess.setBounds(66, 52, 135, 14);
		panel_1.add(lblMarketAccess);
		
		btnInstallMarket = new JButton("Market installieren");
		btnInstallMarket.setEnabled(false);
		btnInstallMarket.addActionListener(action);
		btnInstallMarket.setBounds(10, 77, 208, 23);
		panel_1.add(btnInstallMarket);
		
		btnUninstallMarket = new JButton("Market deinstallieren");
		btnUninstallMarket.setEnabled(false);
		btnUninstallMarket.addActionListener(action);
		btnUninstallMarket.setBounds(10, 111, 208, 23);
		panel_1.add(btnUninstallMarket);
		
		btnUnlockMarket = new JButton("Market freischalten");
		btnUnlockMarket.setEnabled(false);
		btnUnlockMarket.addActionListener(action);
		btnUnlockMarket.setBounds(10, 145, 208, 23);
		panel_1.add(btnUnlockMarket);
		
		btnLockMarket = new JButton("Market-Freigabe zur\u00FCcksetzen");
		btnLockMarket.setEnabled(false);
		btnLockMarket.addActionListener(action);
		btnLockMarket.setBounds(10, 179, 208, 23);
		panel_1.add(btnLockMarket);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "ROOT", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(248, 11, 288, 211);
		panelTools.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblKonsolesu = new JLabel("su-Binary:");
		lblKonsolesu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKonsolesu.setBounds(10, 27, 69, 14);
		panel_2.add(lblKonsolesu);
		
		JLabel lblAppsuperuser = new JLabel("Superuser:");
		lblAppsuperuser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAppsuperuser.setBounds(10, 52, 69, 14);
		panel_2.add(lblAppsuperuser);
		
		lblsubin = new JLabel("");
		lblsubin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblsubin.setBounds(89, 27, 185, 14);
		panel_2.add(lblsubin);
		
		lblSuperuser = new JLabel("");
		lblSuperuser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSuperuser.setBounds(89, 52, 185, 14);
		panel_2.add(lblSuperuser);
		
		btnInstallSU = new JButton("su installieren");
		btnInstallSU.setEnabled(false);
		btnInstallSU.addActionListener(action);
		btnInstallSU.setBounds(10, 77, 268, 23);
		panel_2.add(btnInstallSU);
		
		btnUninstallSU = new JButton("su deinstallieren");
		btnUninstallSU.setEnabled(false);
		btnUninstallSU.addActionListener(action);
		btnUninstallSU.setBounds(10, 111, 268, 23);
		panel_2.add(btnUninstallSU);
		
		btnInstallSuperuser = new JButton("Superuser.apk installieren");
		btnInstallSuperuser.setEnabled(false);
		btnInstallSuperuser.addActionListener(action);
		btnInstallSuperuser.setBounds(10, 145, 268, 23);
		panel_2.add(btnInstallSuperuser);
		
		btnUninstallSuperuser = new JButton("Superuser.apk deinstallieren");
		btnUninstallSuperuser.setEnabled(false);
		btnUninstallSuperuser.addActionListener(action);
		btnUninstallSuperuser.setBounds(10, 179, 268, 23);
		panel_2.add(btnUninstallSuperuser);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Anderes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(546, 11, 191, 211);
		panelTools.add(panel);
		panel.setLayout(null);
		
		btnReboot = new JButton("Reboot");
		btnReboot.addActionListener(action);
		btnReboot.setEnabled(false);
		btnReboot.setBounds(26, 26, 140, 23);
		panel.add(btnReboot);
		
		btnHotreboot = new JButton("HotReboot");
		btnHotreboot.addActionListener(action);
		btnHotreboot.setEnabled(false);
		btnHotreboot.setBounds(26, 60, 140, 23);
		panel.add(btnHotreboot);
		
		btnWipe = new JButton("Wipe");
		btnWipe.addActionListener(action);
		btnWipe.setEnabled(false);
		btnWipe.setBounds(26, 94, 140, 23);
		panel.add(btnWipe);
		
		btnRecoveryreboot = new JButton("RecoveryReboot");
		btnRecoveryreboot.addActionListener(action);
		btnRecoveryreboot.setEnabled(false);
		btnRecoveryreboot.setBounds(26, 128, 140, 23);
		panel.add(btnRecoveryreboot);
		
		JPanel panelAdbScript = new JPanel();
		panelAdbScript.setBackground(Color.WHITE);
		tabPaneInfo.addTab("ADBScript", null, panelAdbScript, null);
		panelAdbScript.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 25, 259, 208);
		panelAdbScript.add(scrollPane_1);
		
		txtScriptEditor = new JTextArea();
		txtScriptEditor.setEditable(false);
		txtScriptEditor.setEnabled(false);
		txtScriptEditor.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(txtScriptEditor);
		
		btnOpenAdbScript = new JButton("ADBScript \u00F6ffnen");
		btnOpenAdbScript.setEnabled(false);
		btnOpenAdbScript.addActionListener(action);
		btnOpenAdbScript.setBounds(541, 25, 196, 23);
		panelAdbScript.add(btnOpenAdbScript);
		
		btnRunAdbScript = new JButton("ADBScript ausf\u00FChren");
		btnRunAdbScript.setEnabled(false);
		btnRunAdbScript.addActionListener(action);
		btnRunAdbScript.setBounds(541, 59, 196, 23);
		panelAdbScript.add(btnRunAdbScript);
		
		lblScript = new JLabel("Script");
		lblScript.setHorizontalAlignment(SwingConstants.CENTER);
		lblScript.setBounds(0, 11, 259, 14);
		panelAdbScript.add(lblScript);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(269, 25, 259, 208);
		panelAdbScript.add(scrollPane_2);
		
		txtScriptOutput = new JTextArea();
		txtScriptOutput.setEnabled(false);
		txtScriptOutput.setEditable(false);
		txtScriptOutput.setBackground(Color.WHITE);
		scrollPane_2.setViewportView(txtScriptOutput);
		
		lblAusgabe = new JLabel("Ausgabe");
		lblAusgabe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAusgabe.setBounds(269, 11, 259, 14);
		panelAdbScript.add(lblAusgabe);
		
		panelProps = new JPanel();
		panelProps.setBackground(Color.WHITE);
		tabPaneInfo.addTab("Prop-Editor", null, panelProps, null);
		panelProps.setLayout(null);
		
		scrollPaneProps = new JScrollPane();
		scrollPaneProps.setBounds(10, 11, 510, 191);
		panelProps.add(scrollPaneProps);
		
		propFilePath = new JComboBox();
		propFilePath.setModel(new DefaultComboBoxModel(new String[] {"/system/build.prop", "/default.prop"}));
		propFilePath.setEditable(true);
		propFilePath.setBounds(595, 11, 142, 20);
		panelProps.add(propFilePath);
		
		JLabel lblDatei = new JLabel("Datei:");
		lblDatei.setBounds(549, 14, 46, 14);
		panelProps.add(lblDatei);
		
		btnLoadProp = new JButton("Laden");
		btnLoadProp.addActionListener(action);
		btnLoadProp.setEnabled(false);
		btnLoadProp.setBounds(549, 49, 188, 23);
		panelProps.add(btnLoadProp);
		
		btnSaveProp = new JButton("Speichern");
		btnSaveProp.addActionListener(action);
		btnSaveProp.setEnabled(false);
		btnSaveProp.setBounds(549, 83, 188, 23);
		panelProps.add(btnSaveProp);
		
		btnResetPropList = new JButton("Zur\u00FCcksetzen");
		btnResetPropList.addActionListener(action);
		btnResetPropList.setEnabled(false);
		btnResetPropList.setBounds(549, 117, 188, 23);
		panelProps.add(btnResetPropList);
		
		lblPropLoadedFileName = new JLabel("");
		lblPropLoadedFileName.setBounds(10, 208, 510, 14);
		panelProps.add(lblPropLoadedFileName);
		
		btnCloseProp = new JButton("Schlie\u00DFen");
		btnCloseProp.addActionListener(action);
		btnCloseProp.setEnabled(false);
		btnCloseProp.setBounds(549, 151, 188, 23);
		panelProps.add(btnCloseProp);
		
		
		
		final JLabel lblIpadresse = new JLabel("IP-Adresse:");
		lblIpadresse.setBounds(10, 7, 71, 23);
		frmMeteorittoolbox.getContentPane().add(lblIpadresse);
		
		JMenuBar menuBar = new JMenuBar();
		frmMeteorittoolbox.setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("?");
		menuBar.add(mnHelp);
		
		// ABOUT-BOX-BUTTON
		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "MMB322-Toolbox\r\nCopyright (C) 2011 M1cha");
			}
		});
		mnHelp.add(mntmInfo);
		
		// CONNECT-BUTTON
		btnVerbinden = new JButton("Verbinden");
		btnVerbinden.addActionListener(action);
		btnVerbinden.setBounds(218, 8, 101, 21);
		frmMeteorittoolbox.getContentPane().add(btnVerbinden);
		
		txtLog = new JTextArea();
		txtLog.setEditable(false);
		txtLog.setLineWrap(true);
		txtLog.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtLog.setForeground(Color.GREEN);
		txtLog.setBackground(Color.BLACK);
		
		final JCheckBox chckbxAutoscroll = new JCheckBox("Autoscroll");
		chckbxAutoscroll.setBackground(Color.WHITE);
		chckbxAutoscroll.setSelected(true);
		chckbxAutoscroll.setBounds(0, 412, 112, 24);
		frmMeteorittoolbox.getContentPane().add(chckbxAutoscroll);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 302, 752, 109);
		scrollPane.setViewportView(txtLog);
		// AUTOSCROLL
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if(chckbxAutoscroll.isSelected()) {
					e.getAdjustable().setValue(e.getAdjustable().getMaximum());
				}
			}
		});
		frmMeteorittoolbox.getContentPane().add(scrollPane);
		
		lblWarning = new JLabel("");
		lblWarning.setForeground(Color.RED);
		lblWarning.setBounds(126, 416, 614, 16);
		frmMeteorittoolbox.getContentPane().add(lblWarning);
		
		btnReload = new JButton("Aktualisieren");
		btnReload.addActionListener(action);
		btnReload.setEnabled(false);
		btnReload.setBounds(344, 8, 119, 22);
		frmMeteorittoolbox.getContentPane().add(btnReload);
	}
	
	public void checkConnection() {
		if(adb.isConnected()) {
			if(!this.connState) {
				// RELOAD INFOS
				this.reloadInfos();
				// ENABLE CONTROLS
				btnReload.setEnabled(true);
				btnReboot.setEnabled(true);
				btnHotreboot.setEnabled(true);
				btnWipe.setEnabled(true);
				btnRecoveryreboot.setEnabled(true);
				btnInstallMarket.setEnabled(true);
				btnUninstallMarket.setEnabled(true);
				btnUnlockMarket.setEnabled(true);
				btnLockMarket.setEnabled(true);
				btnInstallSU.setEnabled(true);
				btnUninstallSU.setEnabled(true);
				btnInstallSuperuser.setEnabled(true);
				btnUninstallSuperuser.setEnabled(true);
				btnLoadProp.setEnabled(true);
				btnSaveProp.setEnabled(false);
				btnResetPropList.setEnabled(false);
				btnCloseProp.setEnabled(false);
				propFilePath.setEnabled(true);
				btnOpenAdbScript.setEnabled(true);
				txtScriptEditor.setEnabled(true);
				txtScriptOutput.setEnabled(true);
				btnRunAdbScript.setEnabled(true);
			}
			btnVerbinden.setText("Trennen");
			connState = true;
		}
		else {
			if(this.connState) {
				// RESET AND DISABLE CONTROLS
				txtKernelInfo.setText("");
				txtFWInfo.setText("");
				txtCmdLine.setText("");
				lblWarning.setText("");
				lblMarketStatus.setText("");
				lblMarketAccess.setText("");
				lblsubin.setText("");
				lblSuperuser.setText("");
				btnReload.setEnabled(false);
				btnReboot.setEnabled(false);
				btnHotreboot.setEnabled(false);
				btnWipe.setEnabled(false);
				btnRecoveryreboot.setEnabled(false);
				btnInstallMarket.setEnabled(false);
				btnUninstallMarket.setEnabled(false);
				btnUnlockMarket.setEnabled(false);
				btnLockMarket.setEnabled(false);
				btnInstallSU.setEnabled(false);
				btnUninstallSU.setEnabled(false);
				btnInstallSuperuser.setEnabled(false);
				btnUninstallSuperuser.setEnabled(false);
				btnLoadProp.setEnabled(false);
				btnOpenAdbScript.setEnabled(false);
				txtScriptEditor.setEnabled(false);
				txtScriptEditor.setText("");
				btnRunAdbScript.setEnabled(false);
				txtScriptOutput.setEnabled(false);
				txtScriptOutput.setText("");
				
				unloadPropFile();
				propFilePath.setEnabled(false);
			}
			btnVerbinden.setText("Verbinden");
			connState = false;
		}
	}
	
	public void log(String text) {
		if(this.txtLog!=null) { 
//			String[] lines = this.txtLog.getText().split("\r\n");
//			if(lines.length>199) {
//				this.txtLog.setText("");
//				for(int i=lines.length-199; i<lines.length; i++) {
//					this.txtLog.setText(this.txtLog.getText()+"\r\n"+lines[i]);
//				}
//			}
			this.txtLog.setText(this.txtLog.getText()+"\r\n"+text);
		}
	}
	
	private void btnClick() {
		// CONNECT
		if(btnVerbinden.getText().equals("Verbinden")) {
			adb.exec("connect "+txtIP.getText());
		}
		// DISCONNECT
		else {
			adb.exec("disconnect");
		}
	}
	
	private void reloadInfos() {
		txtKernelInfo.setText(adb.exec("shell busybox uname -a")[1]);
		txtFWInfo.setText(adb.exec("shell getprop ro.build.version.release")[1]);
		txtCmdLine.setText(adb.exec("shell cat /proc/cmdline")[1]);
		
		// MARKET-INSTALLSTATUS
		boolean GFramework = adb.exec("shell ls -l /system/app/GoogleServicesFramework.apk")[1].equals("ls: /system/app/GoogleServicesFramework.apk: No such file or directory") ? false : true;
		boolean GVending = adb.exec("shell ls -l /system/app/Vending.apk")[1].equals("ls: /system/app/Vending.apk: No such file or directory") ? false : true;
		if(GFramework && GVending) {
			lblMarketStatus.setText("installiert");
			lblMarketStatus.setForeground(new Color(0, 128, 0));
		}
		else if(!GFramework && GVending) {
			lblMarketStatus.setText("nur Vending");
			lblMarketStatus.setForeground(new Color(255, 140, 0));
		}
		else if(GFramework && !GVending) {
			lblMarketStatus.setText("nur Framework");
			lblMarketStatus.setForeground(new Color(255, 140, 0));
		}
		else {
			lblMarketStatus.setText("nicht installiert");
			lblMarketStatus.setForeground(Color.RED);
		}
		
		// MARKET-ACCESS
		String buildDescription = adb.exec("shell \"cat /system/build.prop | busybox grep ro.build.description=\"")[1];
		String buildFingerprint = adb.exec("shell \"cat /system/build.prop | busybox grep ro.build.description=\"")[1];
		if(buildDescription.equals("ro.build.description=berlin-eng 2.2 MASTER eng.wangshen.20110504.125543 test-keys")
		&& buildFingerprint.equals("marvell/berlin/berlin/:2.2/MASTER/eng.wangshen.20110504.125543:eng/test-keys")) {
			lblMarketAccess.setText("Standard");
			lblMarketAccess.setForeground(new Color(255, 140, 0));
		}
		else if(buildDescription.equals("ro.build.description=passion-user 2.2 FRF91 43546 release-keys")
		&& buildFingerprint.equals("google/passion/passion/mahimahi:2.2/FRF91/43546:user/release-keys")) {
			lblMarketAccess.setText("Vollständig");
			lblMarketAccess.setForeground(new Color(0, 128, 0));
		}
		else {
			lblMarketAccess.setText("Anderer/Unbekannt");
			lblMarketAccess.setForeground(Color.RED);
		}
		
		// ROOT-CHECK
		String suInfo = adb.exec("shell \"busybox md5sum /system/bin/su\"")[1];
		String superuserInfo = adb.exec("shell \"busybox md5sum /system/app/Superuser.apk\"")[1];
		boolean suExists = suInfo.equals("md5sum: can't open '/system/bin/su': No such file or directory") ? false : true;
		boolean superuserExists = superuserInfo.equals("md5sum: can't open '/system/app/Superuser.apk': No such file or directory") ? false : true;
		
		// SU
		if(suExists) {
			String suMD5 = suInfo.split(" ")[0];
			if(suMD5.equals("2242e03a09c72790994842db4ff45c48")) {
				lblsubin.setText("installiert");
				lblsubin.setForeground(new Color(0, 128, 0));
			}
			else {
				lblsubin.setText("installiert, unbekannte Version");
				lblsubin.setForeground(new Color(255, 140, 0));
			}
			
		}
		else {
			lblsubin.setText("nicht installiert");
			lblsubin.setForeground(Color.RED);
		}
		
		// SUPERUSER
		if(superuserExists) {
			String superuserMD5 = superuserInfo.split(" ")[0];
			if(superuserMD5.equals("621d622b037ec9177514f3e864aeca5c")) {
				lblSuperuser.setText("installiert");
				lblSuperuser.setForeground(new Color(0, 128, 0));
			}
			else {
				lblSuperuser.setText("installiert, unbekannte Version");
				lblSuperuser.setForeground(new Color(255, 140, 0));
			}
			
		}
		else {
			lblSuperuser.setText("nicht installiert");
			lblSuperuser.setForeground(Color.RED);
		}
		
		// SYSTEM-CHECK
		if(adb.exec("shell ls -l /init.mv88de3010.rc")[1].equals("ls: /init.mv88de3010.rc: No such file or directory")) {
			lblWarning.setText("Bei dem verbundenen Gerät handelt es sich NICHT um eine MMB-322!");
		}
		else {
			lblWarning.setText("");
		}
	}
	@SuppressWarnings("serial")
	private class SwingAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			// CONNECT_DISCONNECT
			if(e.getSource() == btnVerbinden) {
				btnClick();
			}
			
			// POWERCONTROL/WIPE
			else if (e.getSource() == btnReload) {
				reloadInfos();
			}
			else if (e.getSource() == btnReboot) {
				adb.exec("shell oureboot");
			}
			else if (e.getSource() == btnHotreboot) {
				adb.exec("shell killall system_server");
			}
			else if (e.getSource() == btnWipe) {
				int choise = JOptionPane.showConfirmDialog(null, "Dies löscht ALLE Daten und startet die Box neu!\r\nWirklich fortfahren?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(choise==0) {
					adb.exec("shell \"busybox rm -R -f /cache/*\"");
					adb.exec("shell \"busybox rm -R -f /data/*\"");
					adb.exec("shell oureboot");
				}
			}
			else if (e.getSource() == btnRecoveryreboot) {
				adb.exec("shell oureboot recovery=local");
			}
			
			// MARKET-INSTALL
			else if (e.getSource() == btnInstallMarket) {
				int choise = JOptionPane.showConfirmDialog(null, "Market wird installiert und die Box neu gestartet!\r\nWirklich fortfahren?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(choise==0) {
					// remount system RW
					adb.exec("shell mount -o rw,remount /dev/block/mtdblock5 /");
					// push files
					adb.exec("push lib/market /system");
					// set permissions
					adb.exec("shell chmod 4755 /system/app/GoogleServicesFramework.apk");
					adb.exec("shell chmod 4755 /system/app/Vending.apk");
					// reboot
					adb.exec("shell oureboot");
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			}
			else if (e.getSource() == btnUninstallMarket) {
				int choise = JOptionPane.showConfirmDialog(null, "Market wird entfernt und die Box neu gestartet!\r\nWirklich fortfahren?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(choise==0) {
					// remount system RW
					adb.exec("shell mount -o rw,remount /dev/block/mtdblock5 /");
					// delete files
					adb.exec("shell busybox rm /system/app/GoogleServicesFramework.apk");
					adb.exec("shell busybox rm /system/app/Vending.apk");
					// reboot
					adb.exec("shell oureboot");
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			}
			else if (e.getSource() == btnUnlockMarket) {
				JOptionPane.showMessageDialog(null, "Noch nicht implementiert!");
			}
			else if (e.getSource() == btnLockMarket) {
				JOptionPane.showMessageDialog(null, "Noch nicht implementiert!");
			}
			
			// ROOT-INSTALL
			else if (e.getSource() == btnInstallSU) {
				int choise = JOptionPane.showConfirmDialog(null, "SU-Binary wird installiert!\r\nWirklich fortfahren?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(choise==0) {
					// remount system RW
					adb.exec("shell mount -o rw,remount /dev/block/mtdblock5 /");
					// push files
					adb.exec("push lib/root/su /system/bin");
					// set permissions
					adb.exec("shell chmod 4755 /system/bin/su");
					// remount system RO
					adb.exec("shell mount -o ro,remount /dev/block/mtdblock5 /");
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			}
			else if (e.getSource() == btnUninstallSU) {
				int choise = JOptionPane.showConfirmDialog(null, "SU-Binary wird entfernt!\r\nWirklich fortfahren?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(choise==0) {
					// remount system RW
					adb.exec("shell mount -o rw,remount /dev/block/mtdblock5 /");
					// delete files
					adb.exec("shell busybox rm /system/bin/su");
					// remount system RO
					adb.exec("shell mount -o ro,remount /dev/block/mtdblock5 /");
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			}
			else if (e.getSource() == btnInstallSuperuser) {
				int choise = JOptionPane.showConfirmDialog(null, "Superuser.apk wird installiert!\r\nWirklich fortfahren?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(choise==0) {
					// delete old superuser
					adb.exec("uninstall koushikdutta.superuser");
					// install new superuser
					adb.exec("install lib/root/Superuser.apk");
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			}
			else if (e.getSource() == btnUninstallSuperuser) {
				int choise = JOptionPane.showConfirmDialog(null, "Superuser.apk wird entfernt!\r\nWirklich fortfahren?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(choise==0) {
					// delete old superuser
					adb.exec("uninstall koushikdutta.superuser");
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			}
			
			// PROP-EDITOR
			else if (e.getSource() == btnLoadProp) {
				loadPropFile(propFilePath.getSelectedItem().toString());
				loadPropToList();
				btnSaveProp.setEnabled(true);
				btnResetPropList.setEnabled(true);
				btnCloseProp.setEnabled(true);
			}
			else if (e.getSource() == btnSaveProp) {
				rebuildTmpString();
				savePropToTmpFile();
				uploadTmpPropToBox();
			}
			else if (e.getSource() == btnResetPropList) {
				loadPropToList();
			}
			else if (e.getSource() == btnCloseProp) {
				unloadPropFile();
			}
			
			// ADBSCRIPT
			else if (e.getSource() == btnOpenAdbScript) {
				switch(fc.showOpenDialog(null)) {
					case JFileChooser.APPROVE_OPTION:
						FileManager afm = new FileManager(fc.getSelectedFile().getPath(), "UTF-8");
						txtScriptEditor.setText(afm.read().trim());
					break;
					
					case JFileChooser.ERROR_OPTION:
					break;
					
					case JFileChooser.CANCEL_OPTION:
						
					break;
				}
			}
			else if (e.getSource() == btnRunAdbScript) {
				String[] scriptLines = txtScriptEditor.getText().split("\n");
				for(int i=0; i<scriptLines.length; i++) {
					String[] out = adb.exec(scriptLines[i].trim());
					txtScriptOutput.setText(txtScriptOutput.getText()+"> "+scriptLines[i].trim()+"\r\n"+out[1]+"\r\n");
				}
			}
			
		}
	}
	
	private void loadPropFile(String remotepath) {
		// unload old file
		unloadPropFile();
		
		// make nice filename for tmp
		String filename = remotepath.replace("/", "_");
		
		// create local copy
		adb.exec("pull "+remotepath+" tmp/"+filename)[0].equals("0");

		// read local copy
		FileManager fm = new FileManager("tmp/"+filename, "UTF-8");
		this.tmpPropFile = fm.read();
		this.tmpPropFileName = remotepath;
		this.lblPropLoadedFileName.setText(remotepath);
	}
	
	private void loadPropToList() {
		// split into lines
		String[] lines = this.tmpPropFile.split("\n");
		String[] columnNames = {"Name", "Wert"};
		ArrayList<String[]> dataAL = new ArrayList<String[]>();
		for(int i=0; i<lines.length; i++) {
			// get line
			String line = lines[i].trim();
			
			// break if it's a comment or a blank-line
			if(line.startsWith("#") || line.length()==0) {
				continue;
			}
			
			String[] name_value = line.split("=", 2);
			
			// ad row to dataAL
			String[] row = {name_value[0].trim(), name_value[1].trim()};
			dataAL.add(row);
		}
		
		// convert dataAL to array
		String[][] data = new String[dataAL.size()][];
		for(int i=0; i<dataAL.size(); i++) {
			data[i] = dataAL.get(i);
		}
		
		// create Table
		DefaultTableModel model = new DefaultTableModel(data,columnNames);
		this.propTable = new JTable(model){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int colIndex) {
				  if(colIndex==0)
					  return false;
				  else
					  return true;
			  }
		};
		scrollPaneProps.setViewportView(this.propTable);
	}
	
	private void rebuildTmpString() {
		String newFile = "";
		// split into lines
		String[] lines = this.tmpPropFile.split("\n");
		int c = 0;
		for(int i=0; i<lines.length; i++) {
			// get line
			String line = lines[i].trim();
			
			// continue if it's a comment or a blank-line
			if(line.startsWith("#") || line.length()==0) {
				newFile+=line+"\n";
				continue;
			}
			
			String[] name_value = line.split("=", 2);
			if(c<this.propTable.getRowCount() && this.propTable.getValueAt(c, 0).equals(name_value[0].trim())) {
				newFile+=name_value[0].trim()+"="+this.propTable.getValueAt(c, 1)+"\n";
			}
			else {
				newFile+=line+"\n";
			}
				
			c++;
		}
		
		this.tmpPropFile = newFile;
	}
	
	private void savePropToTmpFile() {
		// make nice filename for tmp
		String filename = this.tmpPropFileName.replace("/", "_");
		
		FileManager fm = new FileManager("tmp/"+filename, "UTF-8");
		fm.write(this.tmpPropFile);
	}
	
	private void unloadPropFile() {
		btnSaveProp.setEnabled(false);
		btnResetPropList.setEnabled(false);
		btnCloseProp.setEnabled(false);
		scrollPaneProps.setViewportView(null);
		tmpPropFile="";
		tmpPropFileName="";
		lblPropLoadedFileName.setText("");
	}
	
	private void uploadTmpPropToBox() {
		// make nice filename for tmp
		String filename = this.tmpPropFileName.replace("/", "_");
		
		// remount system RW
		adb.exec("shell mount -o rw,remount /dev/block/mtdblock5 /");
		// push new file
		adb.exec("push tmp/"+filename+" "+this.tmpPropFileName);
		// set permissions
		adb.exec("shell busybox chmod 0644 "+this.tmpPropFileName);
		// remount system RO
		adb.exec("shell mount -o ro,remount /dev/block/mtdblock5 /");
		JOptionPane.showMessageDialog(null, "Erfolgreich!");
	}
}