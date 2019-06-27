import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AccessGitUsers {

	public static void main(String[] args) throws IOException {

		while (true) {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Welcome to findGitUsers console");
			System.out.println("----------------------------------");
			System.out.println("Please Enter userId to find");
			String userId = br.readLine();
			System.out.println("What action you want to perform on this userId");
			System.out.println("Press 1 for getting user info");
			System.out.println("Press 2 for getting user projects");

			int action = Integer.parseInt(br.readLine());

			if (action == 1) {

				githubConnection(action, userId);

			} else if (action == 2) {
				githubConnection(action, userId);

			} else {

				System.out.println("Incorrect input recieved");
				System.out.println("Do You want to try again?");
				//
			}
			// GitConnection(1, "shubham333vm"); //this is for user info
			// GitConnection(2, "shubham333vm"); //this is for user projects

			// gitlabConnection(1,"shubham333vm");
			// gitlabConnection(2,"shubham333vm");
		}
	}

	private static void gitlabConnection(int action, String userId) throws IOException {

		String readLine = null;
		int responseCode = 0;
		String gitlab_api_user = "https://gitlab.com/api/v4/users?username=";
		String gitlab_api_user_project = "https://gitlab.com/api/v4/users/";

		StringBuilder userIdApi = new StringBuilder(gitlab_api_user);
		StringBuilder userProjectApi = new StringBuilder(gitlab_api_user_project);
		gitlab_api_user_project = userProjectApi.append(userId).append("/projects").toString();

		gitlab_api_user = userIdApi.append(userId).toString();
		// gitlab_api_user_project = userIdApi.append(userId).toString();
		// System.out.println("gitlab_api_user_project="+gitlab_api_user_project);
		BufferedReader br = null;
		StringBuffer response = null;
		HttpURLConnection connection = null;
		URL url = null;
		// Shubham333vm/projects

		
		if (action == 1) {
			url = new URL(gitlab_api_user);
			// System.out.println(url);
			//sout
			connection = (HttpURLConnection) url.openConnection();
			// connection.setRequestProperty("accept",
			// "application/vnd.github.inertia-preview+json");
			responseCode = connection.getResponseCode();
			// System.out.println(responseCode);

			if (responseCode == 200) {

				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				response = new StringBuffer();

				while ((readLine = br.readLine()) != null) {

					response.append(readLine);
				}
				br.close();

				System.out.println("GitLab user info=" + response);

			}

			else {
				System.out.println("Sorry Userid not found on gitlab");

			}
		}

		else {

			url = new URL(gitlab_api_user_project);
			// System.out.println(url);
			connection = (HttpURLConnection) url.openConnection();

			// private token "3ZmMMtMvGEPYc8qTm_36"
			// connection.setRequestProperty("accept",
			// "application/vnd.github.inertia-preview+json");
			responseCode = connection.getResponseCode();
			// System.out.println(responseCode);

			if (responseCode == 200) {

				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				response = new StringBuffer();

				while ((readLine = br.readLine()) != null) {

					response.append(readLine);
				}
				br.close();

				System.out.println("GitLab user project info=" + response);

			}

			else {
				System.out.println("Sorry User Projects not found on gitlab");

			}

		}

	}

	private static void githubConnection(int action, String userId) throws IOException {

		String readLine = null;
		int responseCode = 0;
		String git_api_user = "https://api.github.com/users/";
		StringBuilder userIdApi = new StringBuilder(git_api_user);
		git_api_user = userIdApi.append(userId).toString();
		String git_api_user_project = userIdApi.append("/projects").toString();
		BufferedReader br = null;
		StringBuffer response = null;
		HttpURLConnection connection = null;
		URL url = null;
		if (action == 1) {

			url = new URL(git_api_user);
			connection = (HttpURLConnection) url.openConnection();
			// connection.setRequestMethod("Get");
			responseCode = connection.getResponseCode();
			// System.out.println(responseCode);

			if (responseCode == 200) {

				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				response = new StringBuffer();

				while ((readLine = br.readLine()) != null) {

					response.append(readLine);
				}
				br.close();

				System.out.println("Github user info=" + response);
				gitlabConnection(action, userId);

			}

			else {
				System.out.println("Sorry Userid not found on gitHub");
				System.out.println("Searching on gitlab ");

				gitlabConnection(action, userId);

			}
		}

		else {

			url = new URL(git_api_user_project);
			// System.out.println(url);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("accept", "application/vnd.github.inertia-preview+json");
			responseCode = connection.getResponseCode();
			// System.out.println(responseCode);

			if (responseCode == 200) {

				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				response = new StringBuffer();

				while ((readLine = br.readLine()) != null) {

					response.append(readLine);
				}
				br.close();

				System.out.println("Github user project info=" + response);
				gitlabConnection(action, userId);

			} else {

				System.out.println("Sorry User projects not found on github");
				System.out.println("looking on gitlab");
				gitlabConnection(action, userId);

			}

		}
	}

}
