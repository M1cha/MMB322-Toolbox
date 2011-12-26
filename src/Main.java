import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {
	private ADB adb = null;
	private WMain wmain = null;

	public Main(WMain wmain) {
		this.wmain = wmain;
		this.adb = new ADB(wmain);
	}

	public boolean isConnected() {
		return this.adb.isConnected();
	}

	public void connect(String ip) {
		String[] command = { "connect", ip };
		this.adb.exec(command);
	}

	public void disconnect() {
		String[] command = { "disconnect" };
		this.adb.exec(command);
	}

	public String[] execShell(String command) {
		return this.adb.execShell(command);
	}

	public String getKernelInfo() {
		return this.adb.execShell("busybox uname -a")[1];
	}

	public String getProp(String name) {
		return this.adb.execShell("getprop " + name)[1];
	}

	public String getCmdLine() {
		return this.adb.execShell("cat /proc/cmdline")[1];
	}

	public int isMarketInstalled() {
		boolean GFramework = this.adb
				.fileExists("/system/app/GoogleServicesFramework.apk");
		boolean GVending = this.adb.fileExists("/system/app/Vending.apk");

		if ((GFramework) && (GVending)) {
			return 3;
		}

		if ((GFramework) && (!GVending)) {
			return 2;
		}

		if ((!GFramework) && (GVending)) {
			return 1;
		}

		return 0;
	}

	public int isMarketUnlocked() {
		String buildDescription = this.adb
				.execShell("getprop ro.build.description")[1];
		String buildFingerprint = this.adb
				.execShell("getprop ro.build.fingerprint")[1];

		if ((buildDescription
				.equals("berlin-eng 2.2 MASTER eng.wangshen.20110504.125543 test-keys"))
				&& (buildFingerprint
						.equals("marvell/berlin/berlin/:2.2/MASTER/eng.wangshen.20110504.125543:eng/test-keys"))) {
			return 1;
		}
		if ((buildDescription
				.equals("passion-user 2.2 FRF91 43546 release-keys"))
				&& (buildFingerprint
						.equals("google/passion/passion/mahimahi:2.2/FRF91/43546:user/release-keys"))) {
			return 2;
		}

		return 0;
	}

	public int suStatus() {
		String suInfo = this.adb.execShell("busybox md5sum /system/bin/su")[1];
		boolean suExists = this.adb.fileExists("/system/bin/su");

		if (suExists) {
			String suMD5 = suInfo.split(" ")[0];
			if (suMD5.equals("2242e03a09c72790994842db4ff45c48")) {
				return 2;
			}

			return 1;
		}

		return 0;
	}

	public int superuserStatus() {
		String superuserPath = this.adb.packageExists("koushikdutta.superuser");

		if (superuserPath != null) {
			String superuserInfo = this.adb.execShell("busybox md5sum '"
					+ superuserPath + "'")[1];
			String superuserMD5 = superuserInfo.split(" ")[0];
			if (superuserMD5.equals("621d622b037ec9177514f3e864aeca5c")) {
				return 2;
			}

			return 1;
		}

		return 0;
	}

	public boolean isMMB322() {
		return this.adb.fileExists("/init.mv88de3010.rc");
	}

	public void doReboot() {
		this.adb.execShell("oureboot");
	}

	public void doHotReboot() {
		this.adb.execShell("killall system_server");
	}

	public void doWipe() {
		this.adb.execShell("busybox rm -R -f /cache/*");
		this.adb.execShell("busybox rm -R -f /data/*");
		this.adb.execShell("oureboot");
	}

	public void doRecoveryReboot() {
		this.adb.execShell("oureboot recovery=local");
	}

	public void installMarket() {
		this.adb.execShell("mount -o rw,remount /dev/block/mtdblock5 /");

		String[] command = { "push", "lib/market", "/system" };
		this.adb.exec(command);

		this.adb.execShell("chmod 4755 /system/app/GoogleServicesFramework.apk");
		this.adb.execShell("chmod 4755 /system/app/Vending.apk");

		this.adb.execShell("oureboot");
	}

	public void deleteMarket() {
		this.adb.execShell("mount -o rw,remount /dev/block/mtdblock5 /");

		this.adb.execShell("busybox rm /system/app/GoogleServicesFramework.apk");
		this.adb.execShell("busybox rm /system/app/Vending.apk");

		this.adb.execShell("oureboot");
	}

	public void installSU() {
		this.adb.execShell("mount -o rw,remount /dev/block/mtdblock5 /");

		String[] command = { "push", "lib/root/su", "/system/bin" };
		this.adb.exec(command);

		this.adb.execShell("chmod 4755 /system/bin/su");

		this.adb.execShell("mount -o ro,remount /dev/block/mtdblock5 /");

		this.wmain.reloadInfos();
	}

	public void deleteSU() {
		this.adb.execShell("mount -o rw,remount /dev/block/mtdblock5 /");

		this.adb.execShell("busybox rm /system/bin/su");

		this.adb.execShell("mount -o ro,remount /dev/block/mtdblock5 /");

		this.wmain.reloadInfos();
	}

	public void installSuperUser() {
		deleteSuperUser();

		String[] command = { "install", "lib/root/Superuser.apk" };
		this.adb.exec(command);

		this.wmain.reloadInfos();
	}

	public void deleteSuperUser() {
		String[] command = { "uninstall", "koushikdutta.superuser" };
		this.adb.exec(command);

		this.wmain.reloadInfos();
	}

	public String getPropFile(String remotepath) {
		String filename = remotepath.replace("/", "_");

		String[] command = { "pull", remotepath, "tmp/" + filename };
		String output = this.adb.exec(command)[1];
		if (output.indexOf("does not exist") != -1) {
			JOptionPane.showMessageDialog(null, "Datei wurde nicht gefunden!");
			return null;
		}

		FileManager fm = new FileManager("tmp/" + filename, "UTF-8");
		return fm.read();
	}

	public String[][] parseProp(String txt) {
		String[] lines = txt.split("\n");

		ArrayList dataAL = new ArrayList();
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();

			if ((line.startsWith("#")) || (line.length() == 0)) {
				continue;
			}

			String[] name_value = line.split("=", 2);

			if (name_value == null) {
				JOptionPane.showMessageDialog(null,
						"Die Prop-Datei enthält Fehler! (Zeile " + (i + 1)
								+ ")");
				return null;
			}
			if (name_value.length < 2) {
				JOptionPane.showMessageDialog(null,
						"Die Prop-Datei enthält Fehler! (Zeile " + (i + 1)
								+ ")");
				return null;
			}

			String[] row = { name_value[0].trim(), name_value[1].trim() };
			dataAL.add(row);
		}

		String[][] data = new String[dataAL.size()][];
		for (int i = 0; i < dataAL.size(); i++) {
			data[i] = ((String[]) dataAL.get(i));
		}

		return data;
	}

	public void savePropFile(String remotepath, String newProp) {
		String filename = remotepath.replace("/", "_");

		FileManager fm = new FileManager("tmp/" + filename, "UTF-8");
		fm.write(newProp);

		this.adb.execShell("mount -o rw,remount /dev/block/mtdblock5 /");

		String[] command = { "push", "tmp/" + filename, remotepath };
		this.adb.exec(command);

		this.adb.execShell("busybox chmod 0644 " + remotepath);

		this.adb.execShell("mount -o ro,remount /dev/block/mtdblock5 /");
	}
}