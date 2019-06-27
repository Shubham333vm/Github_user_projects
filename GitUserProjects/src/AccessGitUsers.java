import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AccessGitUsers {

	public static void main(String[] args) throws IOException {

		 BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		 System.out.println("Welcome to findGitUsers console");
		 System.out.println("----------------------------------");
		 System.out.println("Please Enter userId to find");
		 String userId=br.readLine();
		 System.out.println("What action you want to perform on this userId");
		 System.out.println("Press 1 for getting user info");
		 System.out.println("Press 2 for getting user projects");
		
		 int action=Integer.parseInt(br.readLine());
		
		 if(action ==1) {
		
		 GitConnection(action,userId);
		
		 }
		 else if(action==2) {
		 GitConnection(action,userId);
		
		 }
		 else {
		
		 System.out.println("Incorrect input recieved");
		 System.out.println("Do You want to try again?");
		
		 }
		//GitConnection(1, "shubham333vm");  //this is for user info
		//GitConnection(2, "shubham333vm");  //this is for user projects

	}

	private static void GitConnection(int action, String userId) throws IOException {

		String readLine = null;
		int responseCode = 0;
		String git_api_user = "https://api.github.com/users/";
		StringBuilder userIdApi = new StringBuilder(git_api_user);
		git_api_user = userIdApi.append(userId).toString();
		String git_api_user_project = userIdApi.append("/projects").toString();
		BufferedReader br = null;
		StringBuffer response = null;
		HttpURLConnection connection=null;
		URL url=null;
		if (action == 1) {

			 url = new URL(git_api_user);
			 connection = (HttpURLConnection) url.openConnection();
			// connection.setRequestMethod("Get");
			responseCode = connection.getResponseCode();
			System.out.println(responseCode);

			if (responseCode == 200) {

				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				response = new StringBuffer();

				while ((readLine = br.readLine()) != null) {

					response.append(readLine);
				}
				br.close();

				System.out.println("Gson String Data=" + response);

			}
			
			else {
				System.out.println("Sorry Userid not found");
			
			}
		}

		else {

		    url = new URL(git_api_user_project);
			System.out.println(url);
		    connection = (HttpURLConnection) url.openConnection();
						connection.setRequestProperty("accept", "application/vnd.github.inertia-preview+json");
			responseCode = connection.getResponseCode();
			System.out.println(responseCode);

			if (responseCode == 200) {

				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				response = new StringBuffer();

				while ((readLine = br.readLine()) != null) {

					response.append(readLine);
				}
				br.close();

				System.out.println("Gson String Data=" + response);

			}
			else {
				
				System.out.println("Sorry Userid not found");

			}
			
			
			
		}
	}

}