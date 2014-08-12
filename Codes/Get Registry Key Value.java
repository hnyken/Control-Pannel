
import java.io.*;

public class MyClass {

	public static void main(String[] Args) {
		// System.setProperty("KEY", "HNY81514525MALVIYA");
		// Map<String, String> env = System.getenv();
		// for (String envName : env.keySet())
		// System.out.format("%s = %s%n", envName, env.get(envName));

		// reg add HKCU\\Environment /v developer /d \"Honey Keny Malviya" /f"
		
		try {
			String PERSONAL_FOLDER_CMD = "reg query HKCU\\Environment /v developer";
			String REGSTR_TOKEN ="REG_SZ";
			Process process = Runtime.getRuntime().exec(PERSONAL_FOLDER_CMD);
			StreamReader reader = new StreamReader(process.getInputStream());
			reader.start();
			process.waitFor();
			reader.join();
			String result = reader.getResult();
			int p = result.indexOf(REGSTR_TOKEN);
			if (p == -1)
				return;
			System.out.println(result.substring(p + REGSTR_TOKEN.length()).trim()); 
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	static class StreamReader extends Thread {
		private InputStream is;
		private StringWriter sw;

		StreamReader(InputStream is) {
			this.is = is;
			sw = new StringWriter();
		}

		public void run() {
			try {
				int c;
				while ((c = is.read()) != -1)
					sw.write(c);
			} catch (IOException e) {
				;
			}
		}

		String getResult() {
			return sw.toString();
		}
	}

}
