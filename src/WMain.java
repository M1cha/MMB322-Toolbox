import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class WMain {
	private JFrame frmMeteorittoolbox;
	private JTextField txtIP;
	private JButton btnVerbinden;
	private JTextField txtKernelInfo;
	private JScrollPane spTxtInfo;
	private JTextArea txtFWInfo;
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
	private JPanel panelProps;
	private JScrollPane scrollPaneProps;
	private JTable propTable = new JTable();
	private JComboBox propFilePath;
	private JButton btnLoadProp;
	private JButton btnSaveProp;
	private JButton btnResetPropList;
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
	private JFileChooser fc = new JFileChooser();

	private Main main = new Main(this);
	private Refresher rf = new Refresher(this);
	private boolean connState = false;
	private String tmpPropTxt = "";
	private String tmpPropFileName = "";

	private final Action action = new WMain.SwingAction();

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
		this.rf.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmMeteorittoolbox = new JFrame();
		this.frmMeteorittoolbox.setFont(new Font("Serif", 0, 12));
		this.frmMeteorittoolbox.getContentPane().setBackground(Color.WHITE);
		this.frmMeteorittoolbox.setResizable(false);
		this.frmMeteorittoolbox.setTitle("MMB322-ToolBox Alpha2.1");
		this.frmMeteorittoolbox.setBounds(100, 100, 758, 490);
		this.frmMeteorittoolbox.setDefaultCloseOperation(3);
		this.frmMeteorittoolbox.getContentPane().setLayout(null);

		this.txtIP = new JTextField();
		this.txtIP.addKeyListener(new KeyAdapter() {
			// CONNECT ON ENTER
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
					WMain.this.btnClick();
			}
		});
		this.txtIP.setBounds(94, 9, 108, 20);
		this.frmMeteorittoolbox.getContentPane().add(this.txtIP);
		this.txtIP.setColumns(10);

		JTabbedPane tabPaneInfo = new JTabbedPane(1);
		tabPaneInfo.setFont(new Font("Dialog", 1, 12));
		tabPaneInfo.setBackground(Color.WHITE);
		tabPaneInfo.setBounds(0, 40, 752, 261);
		this.frmMeteorittoolbox.getContentPane().add(tabPaneInfo);

		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(Color.WHITE);
		tabPaneInfo.addTab("Infos", null, panelInfo, null);
		panelInfo.setLayout(null);

		JLabel lblCmdline = new JLabel("CmdLine:");
		lblCmdline.setFont(new Font("Dialog", 0, 12));
		lblCmdline.setBounds(12, 174, 92, 16);
		panelInfo.add(lblCmdline);

		JLabel lblKernel = new JLabel("Kernel:");
		lblKernel.setFont(new Font("Dialog", 0, 12));
		lblKernel.setBounds(12, 143, 92, 16);
		panelInfo.add(lblKernel);

		this.txtKernelInfo = new JTextField();
		this.txtKernelInfo.setFont(new Font("Monospaced", 0, 12));
		this.txtKernelInfo.setBounds(90, 141, 596, 20);
		panelInfo.add(this.txtKernelInfo);
		this.txtKernelInfo.setEditable(false);
		this.txtKernelInfo.setColumns(10);

		JLabel lblFirmware = new JLabel("Firmware:");
		lblFirmware.setFont(new Font("Dialog", 0, 12));
		lblFirmware.setBounds(12, 12, 92, 16);
		panelInfo.add(lblFirmware);

		this.spTxtInfo = new JScrollPane();
		this.spTxtInfo.setBackground(UIManager.getColor("CheckBox.background"));
		this.spTxtInfo.setFont(new Font("Monospaced", 0, 12));
		this.spTxtInfo.setBounds(90, 10, 596, 121);
		panelInfo.add(this.spTxtInfo);

		this.txtFWInfo = new JTextArea();
		this.txtFWInfo.setBackground(UIManager.getColor("CheckBox.background"));
		this.spTxtInfo.setViewportView(this.txtFWInfo);
		this.txtFWInfo.setEditable(false);
		this.txtFWInfo.setColumns(10);

		this.txtCmdLine = new JTextField();
		this.txtCmdLine.setFont(new Font("Monospaced", 0, 12));
		this.txtCmdLine.setBounds(90, 172, 596, 20);
		panelInfo.add(this.txtCmdLine);
		this.txtCmdLine.setEditable(false);
		this.txtCmdLine.setColumns(10);

		JPanel panelTools = new JPanel();
		panelTools.setBackground(Color.WHITE);
		tabPaneInfo.addTab("Tools", null, panelTools, null);
		panelTools.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 11, 228, 211);
		panel_1.setBorder(new TitledBorder(null, "Google-Market", 4, 2, null,
				null));
		panelTools.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Dialog", 0, 12));
		lblStatus.setBounds(10, 27, 46, 14);
		panel_1.add(lblStatus);

		this.lblMarketStatus = new JLabel("");
		this.lblMarketStatus.setFont(new Font("Dialog", 0, 12));
		this.lblMarketStatus.setBounds(66, 27, 135, 14);
		panel_1.add(this.lblMarketStatus);

		JLabel lblZugriff = new JLabel("Zugriff:");
		lblZugriff.setFont(new Font("Dialog", 0, 12));
		lblZugriff.setBounds(10, 52, 46, 14);
		panel_1.add(lblZugriff);

		this.lblMarketAccess = new JLabel("");
		this.lblMarketAccess.setFont(new Font("Dialog", 0, 12));
		this.lblMarketAccess.setBounds(66, 52, 135, 14);
		panel_1.add(this.lblMarketAccess);

		this.btnInstallMarket = new JButton("Market installieren");
		this.btnInstallMarket.setFont(new Font("Dialog", 0, 12));
		this.btnInstallMarket.setEnabled(false);
		this.btnInstallMarket.addActionListener(this.action);
		this.btnInstallMarket.setBounds(10, 77, 208, 23);
		panel_1.add(this.btnInstallMarket);

		this.btnUninstallMarket = new JButton("Market deinstallieren");
		this.btnUninstallMarket.setFont(new Font("Dialog", 0, 12));
		this.btnUninstallMarket.setEnabled(false);
		this.btnUninstallMarket.addActionListener(this.action);
		this.btnUninstallMarket.setBounds(10, 111, 208, 23);
		panel_1.add(this.btnUninstallMarket);

		this.btnUnlockMarket = new JButton("Market freischalten");
		this.btnUnlockMarket.setFont(new Font("Dialog", 0, 12));
		this.btnUnlockMarket.setEnabled(false);
		this.btnUnlockMarket.addActionListener(this.action);
		this.btnUnlockMarket.setBounds(10, 145, 208, 23);
		panel_1.add(this.btnUnlockMarket);

		this.btnLockMarket = new JButton("Market-Freigabe zurücksetzen");
		this.btnLockMarket.setFont(new Font("Dialog", 0, 12));
		this.btnLockMarket.setEnabled(false);
		this.btnLockMarket.addActionListener(this.action);
		this.btnLockMarket.setBounds(10, 179, 208, 23);
		panel_1.add(this.btnLockMarket);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "ROOT", 4, 2, null, null));
		panel_2.setBounds(242, 11, 300, 211);
		panelTools.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblKonsolesu = new JLabel("su-Binary:");
		lblKonsolesu.setFont(new Font("Dialog", 0, 12));
		lblKonsolesu.setBounds(10, 27, 69, 14);
		panel_2.add(lblKonsolesu);

		JLabel lblAppsuperuser = new JLabel("Superuser:");
		lblAppsuperuser.setFont(new Font("Dialog", 0, 12));
		lblAppsuperuser.setBounds(10, 52, 69, 14);
		panel_2.add(lblAppsuperuser);

		this.lblsubin = new JLabel("");
		this.lblsubin.setFont(new Font("Dialog", 0, 12));
		this.lblsubin.setBounds(91, 27, 197, 14);
		panel_2.add(this.lblsubin);

		this.lblSuperuser = new JLabel("");
		this.lblSuperuser.setFont(new Font("Dialog", 0, 12));
		this.lblSuperuser.setBounds(91, 52, 197, 14);
		panel_2.add(this.lblSuperuser);

		this.btnInstallSU = new JButton("su installieren");
		this.btnInstallSU.setFont(new Font("Dialog", 0, 12));
		this.btnInstallSU.setEnabled(false);
		this.btnInstallSU.addActionListener(this.action);
		this.btnInstallSU.setBounds(10, 77, 278, 23);
		panel_2.add(this.btnInstallSU);

		this.btnUninstallSU = new JButton("su deinstallieren");
		this.btnUninstallSU.setFont(new Font("Dialog", 0, 12));
		this.btnUninstallSU.setEnabled(false);
		this.btnUninstallSU.addActionListener(this.action);
		this.btnUninstallSU.setBounds(10, 111, 278, 23);
		panel_2.add(this.btnUninstallSU);

		this.btnInstallSuperuser = new JButton("Superuser.apk installieren");
		this.btnInstallSuperuser.setFont(new Font("Dialog", 0, 12));
		this.btnInstallSuperuser.setEnabled(false);
		this.btnInstallSuperuser.addActionListener(this.action);
		this.btnInstallSuperuser.setBounds(10, 145, 278, 23);
		panel_2.add(this.btnInstallSuperuser);

		this.btnUninstallSuperuser = new JButton("Superuser.apk deinstallieren");
		this.btnUninstallSuperuser.setFont(new Font("Dialog", 0, 12));
		this.btnUninstallSuperuser.setEnabled(false);
		this.btnUninstallSuperuser.addActionListener(this.action);
		this.btnUninstallSuperuser.setBounds(10, 179, 278, 23);
		panel_2.add(this.btnUninstallSuperuser);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Anderes", 4, 2, null, null));
		panel.setBounds(546, 11, 191, 211);
		panelTools.add(panel);
		panel.setLayout(null);

		this.btnReboot = new JButton("Reboot");
		this.btnReboot.setFont(new Font("Dialog", 0, 12));
		this.btnReboot.addActionListener(this.action);
		this.btnReboot.setEnabled(false);
		this.btnReboot.setBounds(12, 26, 167, 23);
		panel.add(this.btnReboot);

		this.btnHotreboot = new JButton("HotReboot");
		this.btnHotreboot.setFont(new Font("Dialog", 0, 12));
		this.btnHotreboot.addActionListener(this.action);
		this.btnHotreboot.setEnabled(false);
		this.btnHotreboot.setBounds(12, 60, 167, 23);
		panel.add(this.btnHotreboot);

		this.btnWipe = new JButton("Wipe");
		this.btnWipe.setFont(new Font("Dialog", 0, 12));
		this.btnWipe.addActionListener(this.action);
		this.btnWipe.setEnabled(false);
		this.btnWipe.setBounds(12, 94, 167, 23);
		panel.add(this.btnWipe);

		this.btnRecoveryreboot = new JButton("RecoveryReboot");
		this.btnRecoveryreboot.setFont(new Font("Dialog", 0, 12));
		this.btnRecoveryreboot.addActionListener(this.action);
		this.btnRecoveryreboot.setEnabled(false);
		this.btnRecoveryreboot.setBounds(12, 128, 167, 23);
		panel.add(this.btnRecoveryreboot);

		JPanel panelAdbScript = new JPanel();
		panelAdbScript.setBackground(Color.WHITE);
		tabPaneInfo.addTab("ADBScript", null, panelAdbScript, null);
		panelAdbScript.setLayout(null);

		this.scrollPane_1 = new JScrollPane();
		this.scrollPane_1.setBounds(0, 25, 259, 208);
		panelAdbScript.add(this.scrollPane_1);

		this.txtScriptEditor = new JTextArea();
		this.txtScriptEditor.setFont(new Font("Monospaced", 0, 12));
		this.txtScriptEditor.setEnabled(false);
		this.txtScriptEditor.setBackground(Color.WHITE);
		this.scrollPane_1.setViewportView(this.txtScriptEditor);

		this.btnOpenAdbScript = new JButton("ADBScript öffnen");
		this.btnOpenAdbScript.setFont(new Font("Dialog", 0, 12));
		this.btnOpenAdbScript.setEnabled(false);
		this.btnOpenAdbScript.addActionListener(this.action);
		this.btnOpenAdbScript.setBounds(541, 25, 196, 23);
		panelAdbScript.add(this.btnOpenAdbScript);

		this.btnRunAdbScript = new JButton("ADBScript ausführen");
		this.btnRunAdbScript.setFont(new Font("Dialog", 0, 12));
		this.btnRunAdbScript.setEnabled(false);
		this.btnRunAdbScript.addActionListener(this.action);
		this.btnRunAdbScript.setBounds(541, 59, 196, 23);
		panelAdbScript.add(this.btnRunAdbScript);

		this.lblScript = new JLabel("Script");
		this.lblScript.setHorizontalAlignment(0);
		this.lblScript.setBounds(0, 11, 259, 14);
		panelAdbScript.add(this.lblScript);

		this.scrollPane_2 = new JScrollPane();
		this.scrollPane_2.setBounds(269, 25, 259, 208);
		panelAdbScript.add(this.scrollPane_2);

		this.txtScriptOutput = new JTextArea();
		this.txtScriptOutput.setFont(new Font("Monospaced", 0, 12));
		this.txtScriptOutput.setEnabled(false);
		this.txtScriptOutput.setEditable(false);
		this.txtScriptOutput.setBackground(Color.WHITE);
		this.scrollPane_2.setViewportView(this.txtScriptOutput);

		this.lblAusgabe = new JLabel("Ausgabe");
		this.lblAusgabe.setHorizontalAlignment(0);
		this.lblAusgabe.setBounds(269, 11, 259, 14);
		panelAdbScript.add(this.lblAusgabe);

		this.panelProps = new JPanel();
		this.panelProps.setBackground(Color.WHITE);
		tabPaneInfo.addTab("Prop-Editor", null, this.panelProps, null);
		this.panelProps.setLayout(null);

		this.scrollPaneProps = new JScrollPane();
		this.scrollPaneProps.setBounds(10, 11, 510, 191);
		this.panelProps.add(this.scrollPaneProps);

		this.propFilePath = new JComboBox();
		this.propFilePath.setFont(new Font("Dialog", 0, 12));
		this.propFilePath.setModel(new DefaultComboBoxModel(new String[] {
				"/system/build.prop", "/default.prop" }));
		this.propFilePath.setEditable(true);
		this.propFilePath.setBounds(595, 11, 142, 20);
		this.panelProps.add(this.propFilePath);

		JLabel lblDatei = new JLabel("Datei:");
		lblDatei.setFont(new Font("Dialog", 0, 12));
		lblDatei.setBounds(549, 14, 46, 14);
		this.panelProps.add(lblDatei);

		this.btnLoadProp = new JButton("Laden");
		this.btnLoadProp.setFont(new Font("Dialog", 0, 12));
		this.btnLoadProp.addActionListener(this.action);
		this.btnLoadProp.setEnabled(false);
		this.btnLoadProp.setBounds(549, 49, 188, 23);
		this.panelProps.add(this.btnLoadProp);

		this.btnSaveProp = new JButton("Speichern");
		this.btnSaveProp.setFont(new Font("Dialog", 0, 12));
		this.btnSaveProp.addActionListener(this.action);
		this.btnSaveProp.setEnabled(false);
		this.btnSaveProp.setBounds(549, 83, 188, 23);
		this.panelProps.add(this.btnSaveProp);

		this.btnResetPropList = new JButton("Zurücksetzen");
		this.btnResetPropList.setFont(new Font("Dialog", 0, 12));
		this.btnResetPropList.addActionListener(this.action);
		this.btnResetPropList.setEnabled(false);
		this.btnResetPropList.setBounds(549, 117, 188, 23);
		this.panelProps.add(this.btnResetPropList);

		this.lblPropLoadedFileName = new JLabel("");
		this.lblPropLoadedFileName.setBounds(10, 208, 510, 14);
		this.panelProps.add(this.lblPropLoadedFileName);

		this.btnCloseProp = new JButton("Schließen");
		this.btnCloseProp.setFont(new Font("Dialog", 0, 12));
		this.btnCloseProp.addActionListener(this.action);
		this.btnCloseProp.setEnabled(false);
		this.btnCloseProp.setBounds(549, 151, 188, 23);
		this.panelProps.add(this.btnCloseProp);

		JLabel lblIpadresse = new JLabel("IP-Adresse:");
		lblIpadresse.setBounds(10, 7, 90, 23);
		this.frmMeteorittoolbox.getContentPane().add(lblIpadresse);

		JMenuBar menuBar = new JMenuBar();
		this.frmMeteorittoolbox.setJMenuBar(menuBar);

		JMenu mnHelp = new JMenu("?");
		menuBar.add(mnHelp);

		// ABOUT-BOX-BUTTON
		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null,
						"MMB322-Toolbox Alpha2.1\r\nCopyright (C) 2011 M1cha");
			}
		});
		JMenuItem mntmCredits = new JMenuItem("Credits");
		mntmCredits.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"ADB: CLShortFuse@xda-developers.com\r\nRoot: Koush@xda-developers.com\r\nMarket: ahab@android-hilfe.de");
			}
		});
		mnHelp.add(mntmCredits);
		mnHelp.add(mntmInfo);

		// CONNECT-BUTTON
		this.btnVerbinden = new JButton("Verbinden");
		this.btnVerbinden.setFont(new Font("Dialog", 1, 11));
		this.btnVerbinden.addActionListener(this.action);
		this.btnVerbinden.setBounds(229, 8, 114, 21);
		this.frmMeteorittoolbox.getContentPane().add(this.btnVerbinden);

		this.txtLog = new JTextArea();
		this.txtLog.setEditable(false);
		this.txtLog.setLineWrap(true);
		this.txtLog.setFont(new Font("Monospaced", 0, 12));
		this.txtLog.setForeground(Color.GREEN);
		this.txtLog.setBackground(Color.BLACK);

		final JCheckBox chckbxAutoscroll = new JCheckBox("Autoscroll");
		chckbxAutoscroll.setBackground(Color.WHITE);
		chckbxAutoscroll.setSelected(true);
		chckbxAutoscroll.setBounds(0, 412, 112, 24);
		this.frmMeteorittoolbox.getContentPane().add(chckbxAutoscroll);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(0, 302, 752, 109);
		this.scrollPane.setViewportView(this.txtLog);
		
		// AUTOSCROLL
		this.scrollPane.getVerticalScrollBar().addAdjustmentListener(
				new AdjustmentListener() {
					public void adjustmentValueChanged(AdjustmentEvent e) {
						if (chckbxAutoscroll.isSelected())
							e.getAdjustable().setValue(
									e.getAdjustable().getMaximum());
					}
				});
		this.frmMeteorittoolbox.getContentPane().add(this.scrollPane);

		this.lblWarning = new JLabel("");
		this.lblWarning.setForeground(Color.RED);
		this.lblWarning.setBounds(126, 416, 614, 16);
		this.frmMeteorittoolbox.getContentPane().add(this.lblWarning);

		this.btnReload = new JButton("Aktualisieren");
		this.btnReload.setFont(new Font("Dialog", 0, 12));
		this.btnReload.addActionListener(this.action);
		this.btnReload.setEnabled(false);
		this.btnReload.setBounds(355, 8, 138, 22);
		this.frmMeteorittoolbox.getContentPane().add(this.btnReload);
	}

	public void checkConnection() {
		if (this.main.isConnected()) {
			if (!this.connState) {
				// RELOAD INFOS
				reloadInfos();

				// ENABLE CONTROLS
				this.btnReload.setEnabled(true);
				this.btnReboot.setEnabled(true);
				this.btnHotreboot.setEnabled(true);
				this.btnWipe.setEnabled(true);
				this.btnRecoveryreboot.setEnabled(true);
				this.btnInstallMarket.setEnabled(true);
				this.btnUninstallMarket.setEnabled(true);
				this.btnUnlockMarket.setEnabled(true);
				this.btnLockMarket.setEnabled(true);
				this.btnInstallSU.setEnabled(true);
				this.btnUninstallSU.setEnabled(true);
				this.btnInstallSuperuser.setEnabled(true);
				this.btnUninstallSuperuser.setEnabled(true);
				this.btnLoadProp.setEnabled(true);
				this.btnSaveProp.setEnabled(false);
				this.btnResetPropList.setEnabled(false);
				this.btnCloseProp.setEnabled(false);
				this.propFilePath.setEnabled(true);
				this.btnOpenAdbScript.setEnabled(true);
				this.txtScriptEditor.setEnabled(true);
				this.txtScriptOutput.setEnabled(true);
				this.btnRunAdbScript.setEnabled(true);
				this.btnVerbinden.setText("Trennen");

				this.connState = true;
			}

		} else if (this.connState) {
			// RESET AND DISABLE CONTROLS
			this.txtKernelInfo.setText("");
			this.txtFWInfo.setText("");
			this.txtCmdLine.setText("");
			this.lblWarning.setText("");
			this.lblMarketStatus.setText("");
			this.lblMarketAccess.setText("");
			this.lblsubin.setText("");
			this.lblSuperuser.setText("");
			this.btnReload.setEnabled(false);
			this.btnReboot.setEnabled(false);
			this.btnHotreboot.setEnabled(false);
			this.btnWipe.setEnabled(false);
			this.btnRecoveryreboot.setEnabled(false);
			this.btnInstallMarket.setEnabled(false);
			this.btnUninstallMarket.setEnabled(false);
			this.btnUnlockMarket.setEnabled(false);
			this.btnLockMarket.setEnabled(false);
			this.btnInstallSU.setEnabled(false);
			this.btnUninstallSU.setEnabled(false);
			this.btnInstallSuperuser.setEnabled(false);
			this.btnUninstallSuperuser.setEnabled(false);
			this.btnLoadProp.setEnabled(false);
			this.btnOpenAdbScript.setEnabled(false);
			this.txtScriptEditor.setEnabled(false);
			this.txtScriptEditor.setText("");
			this.btnRunAdbScript.setEnabled(false);
			this.txtScriptOutput.setEnabled(false);
			this.txtScriptOutput.setText("");
			unloadPropFile();
			this.propFilePath.setEnabled(false);
			this.btnVerbinden.setText("Verbinden");

			this.connState = false;
		}
	}

	public void log(String text) {
		if (this.txtLog != null)
			this.txtLog.setText(this.txtLog.getText() + "\r\n" + text);
	}

	private void btnClick() {
		// CONNECT
		if (this.btnVerbinden.getText().equals("Verbinden")) {
			this.main.connect(this.txtIP.getText());
		}
		// DISCONNECT
		else {
			this.main.disconnect();
		}
	}

	public void reloadInfos() {
		this.txtKernelInfo.setText(this.main.getKernelInfo());
		this.txtFWInfo.setText("Version: "
				+ this.main.getProp("ro.build.version.release")
				+ "\r\nErstellungsdatum: " + this.main.getProp("ro.build.date")
				+ "\r\nModell: " + this.main.getProp("ro.product.model")
				+ "\r\nSprache: "
				+ this.main.getProp("ro.product.locale.language")
				+ "\r\nRegion: "
				+ this.main.getProp("ro.product.locale.region")
				+ "\r\nRAM-Größe: " + this.main.getProp("ro.build.ddr.size")
				+ "\r\nNAND-Größe: " + this.main.getProp("ro.build.nand.size"));

		// CMDLINE
		this.txtCmdLine.setText(this.main.getCmdLine());

		// MARKET-INSTALLSTATUS
		switch (this.main.isMarketInstalled()) {
		case 3:
			this.lblMarketStatus.setText("installiert");
			this.lblMarketStatus.setForeground(new Color(0, 128, 0));
			break;
		case 2:
			this.lblMarketStatus.setText("nur Framework");
			this.lblMarketStatus.setForeground(new Color(255, 140, 0));
			break;
		case 1:
			this.lblMarketStatus.setText("nur Vending");
			this.lblMarketStatus.setForeground(new Color(255, 140, 0));
			break;
		case 0:
			this.lblMarketStatus.setText("nicht installiert");
			this.lblMarketStatus.setForeground(Color.RED);
		}

		// MARKET-ACCESS
		switch (this.main.isMarketUnlocked()) {
		case 2:
			this.lblMarketAccess.setText("Vollständig");
			this.lblMarketAccess.setForeground(new Color(0, 128, 0));
			break;
		case 1:
			this.lblMarketAccess.setText("Standard");
			this.lblMarketAccess.setForeground(new Color(255, 140, 0));
			break;
		case 0:
			this.lblMarketAccess.setText("Anderer/Unbekannt");
			this.lblMarketAccess.setForeground(Color.RED);
		}

		// ROOT-CHECK
		switch (this.main.suStatus()) {
		case 2:
			this.lblsubin.setText("installiert");
			this.lblsubin.setForeground(new Color(0, 128, 0));
			break;
		case 1:
			this.lblsubin.setText("installiert, unbekannte Version");
			this.lblsubin.setForeground(new Color(255, 140, 0));
			break;
		case 0:
			this.lblsubin.setText("nicht installiert");
			this.lblsubin.setForeground(Color.RED);
		}

		// SuperUser
		switch (this.main.superuserStatus()) {
		case 2:
			this.lblSuperuser.setText("installiert");
			this.lblSuperuser.setForeground(new Color(0, 128, 0));
			break;
		case 1:
			this.lblSuperuser.setText("installiert, unbekannte Version");
			this.lblSuperuser.setForeground(new Color(255, 140, 0));
			break;
		case 0:
			this.lblSuperuser.setText("nicht installiert");
			this.lblSuperuser.setForeground(Color.RED);
		}

		// SYSTEM-CHECK
		if (!this.main.isMMB322()) {
			this.lblWarning
					.setText("Bei dem verbundenen Gerät handelt es sich NICHT um eine MMB-322!");
		} else
			this.lblWarning.setText("");
	}

	private boolean loadPropToList() {
		String[][] data = this.main.parseProp(this.tmpPropTxt);

		if (data == null) {
			return false;
		}

		String[] columnNames = { "Name", "Wert" };
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		this.propTable = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return colIndex != 0;
			}
		};
		this.scrollPaneProps.setViewportView(this.propTable);
		this.lblPropLoadedFileName.setText(this.tmpPropFileName);

		return true;
	}

	private void rebuildTmpPropTxt() {
		String newFile = "";

		String[] lines = this.tmpPropTxt.split("\n");
		int c = 0;
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();

			if ((line.startsWith("#")) || (line.length() == 0)) {
				newFile = newFile + line + "\n";
			} else {
				String[] name_value = line.split("=", 2);

				if ((c < this.propTable.getRowCount())
						&& (this.propTable.getValueAt(c, 0)
								.equals(name_value[0].trim()))) {
					newFile = newFile + name_value[0].trim() + "="
							+ this.propTable.getValueAt(c, 1) + "\n";
				} else {
					newFile = newFile + line + "\n";
				}

				c++;
			}
		}

		this.tmpPropTxt = newFile;
	}

	private void unloadPropFile() {
		this.btnSaveProp.setEnabled(false);
		this.btnResetPropList.setEnabled(false);
		this.btnCloseProp.setEnabled(false);
		this.lblPropLoadedFileName.setText("");

		this.scrollPaneProps.setViewportView(null);

		this.tmpPropTxt = "";
		this.tmpPropFileName = "";
	}

	private class SwingAction extends AbstractAction {
		private SwingAction() {
		}

		public void actionPerformed(ActionEvent e) {
			// CONNECT_DISCONNECT
			if (e.getSource() == WMain.this.btnVerbinden) {
				WMain.this.btnClick();
			}
			
			// POWERCONTROL/WIPE
			else if (e.getSource() == WMain.this.btnReload) {
				WMain.this.reloadInfos();
			} else if (e.getSource() == WMain.this.btnReboot) {
				WMain.this.main.doReboot();
			} else if (e.getSource() == WMain.this.btnHotreboot) {
				WMain.this.main.doHotReboot();
			} else if (e.getSource() == WMain.this.btnWipe) {
				int choise = JOptionPane
						.showConfirmDialog(
								null,
								"Dies löscht ALLE Daten und startet die Box neu!\r\nWirklich fortfahren?",
								"Achtung!", 0, 3);
				if (choise == 0) {
					WMain.this.main.doWipe();
				}
			} else if (e.getSource() == WMain.this.btnRecoveryreboot) {
				WMain.this.main.doRecoveryReboot();
			}
			
			// MARKET-INSTALL
			else if (e.getSource() == WMain.this.btnInstallMarket) {
				int choise = JOptionPane
						.showConfirmDialog(
								null,
								"Market wird installiert und die Box neu gestartet!\r\nWirklich fortfahren?",
								"Achtung!", 0, 3);
				if (choise == 0) {
					WMain.this.main.installMarket();
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			} else if (e.getSource() == WMain.this.btnUninstallMarket) {
				int choise = JOptionPane
						.showConfirmDialog(
								null,
								"Market wird entfernt und die Box neu gestartet!\r\nWirklich fortfahren?",
								"Achtung!", 0, 3);
				if (choise == 0) {
					WMain.this.main.deleteMarket();
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			} else if (e.getSource() == WMain.this.btnUnlockMarket) {
				JOptionPane
						.showMessageDialog(null, "Noch nicht implementiert!");
			} else if (e.getSource() == WMain.this.btnLockMarket) {
				JOptionPane
						.showMessageDialog(null, "Noch nicht implementiert!");
			} 
			
			// ROOT-INSTALL
			else if (e.getSource() == WMain.this.btnInstallSU) {
				int choise = JOptionPane.showConfirmDialog(null,
						"SU-Binary wird installiert!\r\nWirklich fortfahren?",
						"Achtung!", 0, 3);
				if (choise == 0) {
					WMain.this.main.installSU();
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			} else if (e.getSource() == WMain.this.btnUninstallSU) {
				int choise = JOptionPane.showConfirmDialog(null,
						"SU-Binary wird entfernt!\r\nWirklich fortfahren?",
						"Achtung!", 0, 3);
				if (choise == 0) {
					WMain.this.main.deleteSU();
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			} else if (e.getSource() == WMain.this.btnInstallSuperuser) {
				int choise = JOptionPane
						.showConfirmDialog(
								null,
								"Superuser.apk wird installiert!\r\nWirklich fortfahren?",
								"Achtung!", 0, 3);
				if (choise == 0) {
					WMain.this.main.installSuperUser();
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}
			} else if (e.getSource() == WMain.this.btnUninstallSuperuser) {
				int choise = JOptionPane.showConfirmDialog(null,
						"Superuser.apk wird entfernt!\r\nWirklich fortfahren?",
						"Achtung!", 0, 3);
				if (choise == 0) {
					WMain.this.main.deleteSuperUser();
					JOptionPane.showMessageDialog(null, "Erfolgreich!");
				}

			} 
			
			// PROP-EDITOR
			else if (e.getSource() == WMain.this.btnLoadProp) {
				WMain.this.unloadPropFile();

				WMain.this.tmpPropFileName = WMain.this.propFilePath
						.getSelectedItem().toString();

				WMain.this.tmpPropTxt = WMain.this.main
						.getPropFile(WMain.this.tmpPropFileName);

				if (WMain.this.loadPropToList()) {
					WMain.this.btnSaveProp.setEnabled(true);
					WMain.this.btnResetPropList.setEnabled(true);
					WMain.this.btnCloseProp.setEnabled(true);
				} else {
					WMain.this.unloadPropFile();
				}
			} else if (e.getSource() == WMain.this.btnSaveProp) {
				WMain.this.rebuildTmpPropTxt();
				WMain.this.main.savePropFile(WMain.this.tmpPropFileName,
						WMain.this.tmpPropTxt);
				JOptionPane.showMessageDialog(null, "Erfolgreich!");
			} else if (e.getSource() == WMain.this.btnResetPropList) {
				WMain.this.loadPropToList();
			} else if (e.getSource() == WMain.this.btnCloseProp) {
				WMain.this.unloadPropFile();
			} 
			
			// ADBSCRIPT
			else if (e.getSource() == WMain.this.btnOpenAdbScript) {
				switch (WMain.this.fc.showOpenDialog(null)) {
				case 0:
					FileManager afm = new FileManager(WMain.this.fc
							.getSelectedFile().getPath(), "UTF-8");
					WMain.this.txtScriptEditor.setText(afm.read().trim());
					break;
				case -1:
					break;
				case 1:
				default:
					break;
				}
			} else if (e.getSource() == WMain.this.btnRunAdbScript) {
				String[] scriptLines = WMain.this.txtScriptEditor.getText()
						.split("\n");
				for (int i = 0; i < scriptLines.length; i++) {
					String[] out = WMain.this.main.execShell(scriptLines[i]
							.trim());
					WMain.this.txtScriptOutput
							.setText(WMain.this.txtScriptOutput.getText()
									+ "> " + scriptLines[i].trim() + "\r\n"
									+ out[1] + "\r\n");
				}
			}
		}
	}
}