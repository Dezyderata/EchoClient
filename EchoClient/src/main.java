import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		JavaClient client = new JavaClient("127.0.0.1", 65000);
		client.connect();
	}
}