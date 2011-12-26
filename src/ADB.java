import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

public class ADB {
	private WMain wm = null;
	private String adbFile = "";

	public ADB(WMain wmain) {
	
		// delete log-file
		try {
			FileOutputStream erasor = new FileOutputStream("adb.log");
			erasor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String os = System.getProperty("os.name").split(" ")[0].toLowerCase();
		if (os.equals("windows")) {
			this.adbFile = "adb.exe";
		} else if (os.equals("linux")) {
			this.adbFile = "adblinux";
		} else if (os.equals("mac")) {
			this.adbFile = "adbmac";
		} else {
			JOptionPane.showMessageDialog(null,
					"Betriebssystem konnte nicht ermittelt werden!");
			System.exit(0);
		}

		this.wm = wmain;
		startADBD();
		isConnected();
	}

	// start ADB-Daemon
	private void startADBD() {
		try {
			Runtime.getRuntime().exec(
					"lib/adb/" + this.adbFile + " fork-server server");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Folgender Fehler ist aufgetreten:\r\n" + e.toString());
			e.printStackTrace();
			System.exit(0);
		}
	}

	// show error-message
	private void showError(String error) {
		JOptionPane.showMessageDialog(null,
				"Folgender Fehler ist aufgetreten:\r\n" + error);
		System.exit(0);
	}

	// check if connected
	public boolean isConnected() {
		String[] command = { "shell", "busybox", "uname" };
		String result = exec(command)[1].trim();

		return !result.equals("error: device not found");
	}

	// run ADB-command
	public String[] exec(String[] command) {
		if (command == null) {
			String[] result = new String[2];
			result[0] = "0";
			result[1] = "";
			return result;
		}
		if (command.length == 0) {
			String[] result = new String[2];
			result[0] = "0";
			result[1] = "";
			return result;
		}

		// run command
		Process p = null;
		try {
			String[] cmd = new String[command.length + 1];
			cmd[0] = ("lib/adb/" + this.adbFile);
			for (int i = 0; i < command.length; i++) {
				cmd[(i + 1)] = command[i];
			}

			p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			showError(e.toString());
			e.printStackTrace();
		}

		// get in/out-streams
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(
				p.getErrorStream()));

		// read output
		String output = "";
		String s = null;
		try {
			while ((s = stdInput.readLine()) != null)
				output = output + s + "\r\n";
		} catch (IOException e) {
			showError(e.toString());
			e.printStackTrace();
		}

		// read error
		String error = "";
		try {
			while ((s = stdError.readLine()) != null)
				error = error + s;
		} catch (IOException e) {
			showError(e.toString());
			e.printStackTrace();
		}

		String[] result = new String[2];
		if (error.length() > 0) {
			result[0] = "0";
			result[1] = error.trim();
		} else {
			result[0] = "1";
			result[1] = output.trim();
		}
		this.wm.log(result[1]);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("adb.log",
					true));
			out.write("> ");
			for (int i = 0; i < command.length; i++) {
				out.write(command[i] + " ");
			}
			out.write("\r\n");
			out.write(result[1] + "\r\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String[] execShell(String command) {
		String[] cmd = { "shell", command };
		return exec(cmd);
	}

	public boolean fileExists(String filename) {
		String[] command = { "shell",
				"busybox test -f '" + filename + "' && echo true || echo false" };
		boolean result = exec(command)[1].equals("true");
		return result;
	}

	public String packageExists(String name) {
		String[] command = { "shell", "pm path '" + name + "'" };
		String result = exec(command)[1];
		if (result.length() > 0) {
			return result.substring(8);
		}
		return null;
	}
}