package application;


import java.io.*;
import java.net.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
public class Chat implements Initializable,Observer{
	
	@FXML Button btnConnect;
	@FXML Button btnSend;
	@FXML TextArea txtChatArea;
	@FXML TextField txtMessage;
	@FXML TextField txtPort;
	@FXML TextField txtServer;
	@FXML TextField txtUsername;
	@FXML ListView<String> listConnectedUsers;
	
	StringBuilder strbuilder ;
	DataInputStream dis= null;
	DataOutputStream dos = null;
	String Message ="";
	Socket socket = null;
	String Username = "";

 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}
	
	@FXML 
	public void connectClick() {
		Username = txtUsername.getText();
		try {
			int port = 3030;
			String server ="localhost";
			
			ConnectToServer(port,server);
		} catch (NumberFormatException e) {
			System.out.println("Erreur dans le type !!!");
		}
	}
	
	@FXML 
	public void sendClick() {
		try {
			
			sendMessage(txtMessage.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String Message) throws IOException {
		dos.writeUTF(Message);
		dos.flush();
	}
	
	private void ConnectToServer(int Port,String server) {
		try {
		
			socket = new Socket(server,Port);
			System.out.println(Username+" : Connected");
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(Username);
			Client C = new Client(dis,this);
			C.setDaemon(true);
			C.start();
		
			} catch (IOException e) {
			System.out.println("CLIENT: Failed to connect");
			e.printStackTrace();

		}

	}
	
	public synchronized void addMessageToScreen(String Message){	
		txtChatArea.appendText(Message+'\n');
	}
	
	public synchronized void addUsersConnected(List<String> users){	
		
		for (String user : users) {
			
			listConnectedUsers.getItems().add(user);
		}
		
		//listProperty.set(FXCollections.observableArrayList(users));
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Bonjour "+arg);
	}


}
