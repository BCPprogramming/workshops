import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class ASCIIScaler {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("C:\\Users\\abhi\\Desktop\\in.txt"));
		PrintWriter out = new PrintWriter("C:\\Users\\abhi\\Desktop\\out.txt");
		Scanner user = new Scanner(System.in);

		int scaleFactor = user.nextInt();

		while (in.hasNextLine()) {
			char[] line = in.nextLine().toCharArray();
			String result = "";

			//Horizontal expansion
			for (char c : line) {
				for (int i = 0; i < scaleFactor; i++) {
					result += c;
				}
			}

			//Vertical expansion
			for (int i = 0; i < scaleFactor; i++) {
				System.out.println(result);
			}
		}
		in.close();
		out.close();
		user.close();
	}
}