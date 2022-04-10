package GUI;


import java.io.File;
import java.text.DecimalFormat;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Restaurant_Reservation.Customer;
import Restaurant_Reservation.Food;
import Restaurant_Reservation.Manager;
import Restaurant_Reservation.Tables;
import Restaurant_Reservation.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GUI extends Application {	
	
	
	Tables table[] = new Tables[8];
	Food food[] = new Food[9];
	Customer customerData[] = new Customer[8];
	
	User user = new User();
	Customer customer = new Customer();
	Manager manager = new Manager();
	
	private int S=-1; //To Check Smoking Area without confirmation
	private int R=0;  //To Check Reservation of table without confirmation
	private int P=0;  //Temporarily applies some premium features
	private double totalTaxes;
	private double totalPrice;
	private double price;
	private boolean checked;
	
	DecimalFormat df = new DecimalFormat("00");
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage loginStage) throws Exception {
		
		Stage customerDashboard = new Stage(); //Preparing window for customer DashBoard Table reservation
		Stage customerDashboard2 = new Stage(); //Preparing window for customer DashBaord Food ordering
		Stage customerDashboard3 = new Stage(); //Preparing window for customer DashBoard Receipt
		Stage managerDashboard = new Stage(); //Preparing window for manager DashBoard
		Stage waiterDashboard = new Stage(); //Preparing window for waiter DashBoard
		Stage cookerDashboard = new Stage(); //Preparing window for cooker DashBoard

		Label greetings = new Label("");
		
		//LOGIN WINDOW
		
		loginStage.setTitle("Login");
		loginStage.centerOnScreen();
	    loginStage.setResizable(false);
		Pane root = new Pane();
		root.setStyle("-fx-background-color: #ADD8E6"); //Light Blue color
		Scene scene = new Scene(root,1000,650);
		loginStage.setScene(scene);
		loginStage.show();
		
		Stage registerStage = new Stage();
		registerStage.setTitle("Register");
		registerStage.centerOnScreen();
		registerStage.setResizable(false);
		Pane root0 = new Pane();
		root0.setStyle("-fx-background-color: #ADD8E6"); //Light Blue color
		Scene scene0 = new Scene(root0,1000,650);
		registerStage.setScene(scene0);
		
		Label title = new Label();
		title.setText("Login");
		title.setLayoutX(435);
		title.setLayoutY(100);
		title.setFont(new Font("Arial Bold", 50));
		title.setTextFill(Color.web("#FF0000", 1)); //Red color		
		
		Label desc = new Label();
		desc.setText("welcome to the restaurant");
		desc.setLayoutX(355);
		desc.setLayoutY(180);
		desc.setFont(new Font("Arial Bold", 25));
		desc.setTextFill(Color.web("#000000", 1)); //Black color		
		
		Button exit = new Button("Exit");
		exit.setLayoutX(910);
		exit.setLayoutY(600);
		exit.setFont(new Font("Arial Bold", 20));
		exit.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label username = new Label();
		username.setText("Username:");
		username.setLayoutX(220);
		username.setLayoutY(300);
		username.setFont(new Font("Arial Bold", 35));
		username.setTextFill(Color.web("#00008b", 1)); //Dark Blue color	
		
		Label invalid = new Label("");
		invalid.setLayoutX(750);
		invalid.setLayoutY(350);
		invalid.setFont(new Font("Arial Bold", 21));
		invalid.setTextFill(Color.web("#FF0000", 1)); //Red color	
		
		Label password = new Label();
		password.setText("Password:");
		password.setLayoutX(220);
		password.setLayoutY(400);
		password.setFont(new Font("Arial Bold", 35));
		password.setTextFill(Color.web("#00008b", 1)); //Dark Blue color
		
		TextField enterUsername = new TextField();
		enterUsername.setPromptText("Enter your username...");
		enterUsername.setScaleX(1.6);
		enterUsername.setScaleY(1.4);
		enterUsername.setLayoutX(480);
		enterUsername.setLayoutY(305);
		
		PasswordField enterPassword = new PasswordField();
		enterPassword.setPromptText("Enter your password...");
		enterPassword.setScaleX(1.6);
		enterPassword.setScaleY(1.4);
		enterPassword.setLayoutX(480);
		enterPassword.setLayoutY(404);
		
		TextField enterPasswordVisible = new TextField();
		enterPasswordVisible.setPromptText("Enter your password...");
		enterPasswordVisible.setScaleX(1.6);
		enterPasswordVisible.setScaleY(1.4);
		enterPasswordVisible.setLayoutX(480);
		enterPasswordVisible.setLayoutY(404);
		enterPasswordVisible.setVisible(false);
		
		CheckBox showPassword = new CheckBox("show password");
		showPassword.setLayoutX(750);
		showPassword.setLayoutY(404);
		showPassword.setFont(new Font("Arial Bold", 15));
		

		showPassword.setOnAction(e -> {
			
			checked = showPassword.isSelected();
			if(checked == true)
			{
				enterPasswordVisible.setVisible(true);
				enterPassword.setVisible(false);
				enterPasswordVisible.setText(enterPassword.getText());
				
			}
			else if(checked == false)
			{
				enterPasswordVisible.setVisible(false);
				enterPassword.setVisible(true);
				enterPassword.setText(enterPasswordVisible.getText());
			}	
			
			
		});
		
		Button login = new Button("Login");
		login.setLayoutX(450);
		login.setLayoutY(490);
		login.setFont(new Font("Arial Bold", 28));
		login.setTextFill(Color.web("#FF0000", 1)); //Red color	
		login.setStyle("-fx-background-color: #FFFF00"); //Yellow color
		
		Button confirmRegister = new Button("Register");
		confirmRegister.setLayoutX(450);
		confirmRegister.setLayoutY(500);
		confirmRegister.setFont(new Font("Arial Bold", 28));
		confirmRegister.setTextFill(Color.web("#000000", 1)); //Black color	
		confirmRegister.setStyle("-fx-background-color: #FF0000"); //Red color
		
		Label registerTitle = new Label("Register");
		registerTitle.setLayoutX(425);
		registerTitle.setLayoutY(70);
		registerTitle.setFont(new Font("Arial Bold", 50));
		registerTitle.setTextFill(Color.web("#4B0082", 1)); //Indigo color		
			
		Button back = new Button("Back to Login");
		back.setLayoutX(800);
		back.setLayoutY(600);
		back.setFont(new Font("Arial Bold", 20));
		back.setTextFill(Color.web("#000000", 1)); //Black color	
		back.setOnAction(e -> {
			
			enterUsername.setText("");
			enterPassword.setText("");
			enterPasswordVisible.setText("");
			invalid.setText("");
			showPassword.setSelected(false);
			enterPasswordVisible.setVisible(false);
			enterPassword.setVisible(true);
			
			registerStage.close();
			loginStage.show();
			
		});
		
		Label newName = new Label("Real Name:");
		newName.setLayoutX(220);
		newName.setLayoutY(200);
		newName.setFont(new Font("Arial Bold", 35));
		newName.setTextFill(Color.web("#00008b", 1)); //Dark Blue color	
		
		Label newUsername = new Label("Username:");
		newUsername.setLayoutX(220);
		newUsername.setLayoutY(300);
		newUsername.setFont(new Font("Arial Bold", 35));
		newUsername.setTextFill(Color.web("#00008b", 1)); //Dark Blue color	
		
		Label newPassword = new Label("Password:");
		newPassword.setLayoutX(220);
		newPassword.setLayoutY(400);
		newPassword.setFont(new Font("Arial Bold", 35));
		newPassword.setTextFill(Color.web("#00008b", 1)); //Dark Blue color
		
		TextField enterNewName = new TextField();
		enterNewName.setPromptText("Enter your name...");
		enterNewName.setScaleX(1.6);
		enterNewName.setScaleY(1.4);
		enterNewName.setLayoutX(480);
		enterNewName.setLayoutY(205);
		
		TextField enterNewUsername = new TextField();
		enterNewUsername.setPromptText("Enter your username...");
		enterNewUsername.setScaleX(1.6);
		enterNewUsername.setScaleY(1.4);
		enterNewUsername.setLayoutX(480);
		enterNewUsername.setLayoutY(305);
		
		TextField enterNewPassword = new TextField();
		enterNewPassword.setPromptText("Enter your password...");
		enterNewPassword.setScaleX(1.6);
		enterNewPassword.setScaleY(1.4);
		enterNewPassword.setLayoutX(480);
		enterNewPassword.setLayoutY(405);
		
		Label registerLabel = new Label("Not a member yet ?");
		registerLabel.setLayoutX(360);
		registerLabel.setLayoutY(590);
		registerLabel.setFont(new Font("Arial Bold", 18));
		registerLabel.setTextFill(Color.web("#000000", 1)); //Black color
		
		Button register = new Button("Register");
		register.setLayoutX(540);
		register.setLayoutY(585);
		register.setFont(new Font("Arial Bold", 17));
		register.setTextFill(Color.web("#000000", 1)); //Black color	
		register.setStyle("-fx-background-color: #FF0000"); //Red color
		
		Label weakPassword = new Label("");
		weakPassword.setLayoutX(400);
		weakPassword.setLayoutY(450);
		weakPassword.setFont(new Font("Arial Bold", 17));
		weakPassword.setTextFill(Color.web("#FF0000", 1)); //Red color
		
		Label noName = new Label("");
		noName.setLayoutX(460);
		noName.setLayoutY(250);
		noName.setFont(new Font("Arial Bold", 17));
		noName.setTextFill(Color.web("#FF0000", 1)); //Red color
		
		Label noUsername = new Label("");
		noUsername.setLayoutX(450);
		noUsername.setLayoutY(350);
		noUsername.setFont(new Font("Arial Bold", 17));
		noUsername.setTextFill(Color.web("#FF0000", 1)); //Red color
		
		register.setOnAction(e -> {
			enterNewName.setText("");
			enterNewUsername.setText("");
			enterNewPassword.setText("");
			loginStage.hide();
			registerStage.show();
		});
		
		confirmRegister.setOnAction(e -> {
			
			
			try
			{
			File fXmlFile2 = new File("Data.xml");
			DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
			Document doc2 = dBuilder2.parse(fXmlFile2);		
			doc2.getDocumentElement().normalize();

			
			NodeList userList = doc2.getElementsByTagName("User"); //gets all elements by that name

			for (int i = 0; i < userList.getLength(); i++) { //Loop to pass on all users

				Node userNode = userList.item(i);
						
				Element element = (Element) userNode;
				
			if (enterNewUsername.getText().equals(element.getElementsByTagName("Username").item(0).getTextContent()))
			{
				noUsername.setText("Sorry this username is already taken !");
				
				break;
			}
			else
			{
				noUsername.setText("");
			}
			}			
			}catch(Exception c) {System.out.println("Problem while checking data file !");}
			
			if(enterNewName.getText().length()<2)
			{
				noName.setText("Name field required !");
			}
			else
			{
				noName.setText("");

			}
			if(enterNewUsername.getText().length()<2 && noUsername.getText()=="")
			{
				noUsername.setText("Valid username required !");
			}
			else if(enterNewUsername.getText().length()>=2 && noUsername.getText()=="")
			{
				noUsername.setText("");
			}
			if(enterNewPassword.getText().length() < 8)
			{
				weakPassword.setText("Your password should exceed 8 characters !");
			}
			else
			{
				weakPassword.setText("");
			}
			
			if(noUsername.getText()=="" && weakPassword.getText()=="" && noName.getText()=="")
			{
				try {
					File xmlFile = new File("Data.xml");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(xmlFile);
			        DOMSource source = new DOMSource(doc);
			        
			        NodeList addUser = doc.getElementsByTagName("Users");
			        
				    Element user = doc.createElement("User");
			        addUser.item(0).appendChild(user);

				    Element newNameElement = doc.createElement("Name");
				    newNameElement.appendChild(doc.createTextNode(enterNewName.getText()));
			        user.appendChild(newNameElement);
			        
			        Element roleElement = doc.createElement("Role");
			        roleElement.appendChild(doc.createTextNode("Client"));
			        user.appendChild(roleElement);
			        
			        Element newUsernameElement = doc.createElement("Username");
			        newUsernameElement.appendChild(doc.createTextNode(enterNewUsername.getText()));
			        user.appendChild(newUsernameElement);
			        
			        Element newPasswordElement = doc.createElement("Password");
			        newPasswordElement.appendChild(doc.createTextNode(enterNewPassword.getText()));
			        user.appendChild(newPasswordElement);
			        
			        Transformer transformer = TransformerFactory.newInstance().newTransformer();
			        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				    StreamResult file = new StreamResult(new File("Data.xml"));
				    transformer.transform(source, file);
				    
			        
			    }catch(Exception E) {System.out.println(e);}
				
			    Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Registered successfully !");
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.showAndWait();
				
				invalid.setText("");
				enterUsername.setText(enterNewUsername.getText());
				
				if(checked==true)
				{
					enterPasswordVisible.setText(enterNewPassword.getText());
				}
				else if(checked == false)
				{
					enterPassword.setText(enterNewPassword.getText());
					enterPasswordVisible.setVisible(false);
					enterPassword.setVisible(true);

				}
			    registerStage.close();
			    loginStage.show();
			}
		});
		
		root0.getChildren().addAll(confirmRegister,registerTitle,back,newUsername,newPassword,enterNewUsername,enterNewPassword,newName,enterNewName,
				weakPassword,noName,noUsername);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db;
	    db = null;
	    try {
	        db = dbf.newDocumentBuilder();
	        Document doc = db.parse("Data.xml");
	        
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult file = new StreamResult(new File("Data.xml"));
	        
	    
			NodeList nList = doc.getElementsByTagName("customersOrders"); //DELETING OLD ORDERS

			for (int i = 0; i < nList.getLength(); i++) {
			    Node node = nList.item(i);
			    node.getParentNode().removeChild(node);
			    
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        transformer.transform(source, file);
			}
			

	        NodeList Restaurant = doc.getElementsByTagName("Restaurant");
	        Element customersOrders = doc.createElement("customersOrders"); //PREPARING TO SAVE NEW ORDERS TO FILE
	        Restaurant.item(0).appendChild(customersOrders);
		    
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(source, file);
	        
	    } catch (Exception e) {System.out.println("Problem saving in data file !");}
		
		try
		{
		File fXmlFile = new File("Data.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);		
		doc.getDocumentElement().normalize();

		for (int i = 0; i < table.length; i++) {
		    table[i] = new Tables();
		}
		
		for (int i = 0; i < food.length; i++) {
		    food[i] = new Food();
		}
		
		for (int i =0 ; i< customerData.length ; i++)
		{
			customerData[i] = new Customer();
		}
		
		
		login.setOnAction(e -> {
			

			if (enterPassword.getText().length() != enterPasswordVisible.getText().length() && checked==true)
			{
				enterPassword.setText(enterPasswordVisible.getText());
				checked=false;
			}
			
			
			user.setUsername(enterUsername.getText());
			user.setPassword(enterPassword.getText());	

			
			try
			{
			File fXmlFile2 = new File("Data.xml");
			DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
			Document doc2 = dBuilder2.parse(fXmlFile2);		
			doc2.getDocumentElement().normalize();

			
			NodeList userList = doc2.getElementsByTagName("User"); //gets all elements by that name

			for (int i = 0; i < userList.getLength(); i++) { //Loop to pass on all users

				Node userNode = userList.item(i); //components of each user
						
				Element element = (Element) userNode;
				
				if (user.getUsername().equals(element.getElementsByTagName("Username").item(0).getTextContent())
						&& (user.getPassword().equals(element.getElementsByTagName("Password").item(0).getTextContent()) || 
								enterPasswordVisible.getText().equals(element.getElementsByTagName("Password").item(0).getTextContent()))) 
				{
					invalid.setText("");
					
					user.setName(element.getElementsByTagName("Name").item(0).getTextContent());
					user.setRole(element.getElementsByTagName("Role").item(0).getTextContent());				

					
					if(element.getElementsByTagName("Role").item(0).getTextContent().equals("Client") 
							|| element.getElementsByTagName("Role").item(0).getTextContent().equals("premiumClient"))
					{
						if (element.getElementsByTagName("Role").item(0).getTextContent().equals("premiumClient"))
						{
							P=1;
						}
						else
						{
							P=0;
						}
						
						customerDashboard.show();
						loginStage.close();
						customerDashboard.setTitle("Welcome, "+user.getName());
						greetings.setText("Welcome to our restaurant, "+user.getName()+"...");
						greetings.setLayoutX(20);
						greetings.setLayoutY(30);
						greetings.setFont(new Font("Arial Bold", 20));
						greetings.setTextFill(Color.web("#ADD8E6", 1)); //Light Blue color	
						
						enterUsername.setText("");
						enterPassword.setText("");
						enterPasswordVisible.setText("");
						showPassword.setSelected(false);
						enterPasswordVisible.setVisible(false);
						enterPassword.setVisible(true);

					}
					
					else if(element.getElementsByTagName("Role").item(0).getTextContent().equals("Manager"))
					{	
						
						managerDashboard.show();
						loginStage.close();
						
						enterUsername.setText("");
						enterPassword.setText("");
						enterPasswordVisible.setText("");
						showPassword.setSelected(false);
						enterPasswordVisible.setVisible(false);
						enterPassword.setVisible(true);


					}
					else if(element.getElementsByTagName("Role").item(0).getTextContent().equals("Waiter"))
					{	
						
						waiterDashboard.show();
						loginStage.close();
						
						enterUsername.setText("");
						enterPassword.setText("");
						enterPasswordVisible.setText("");
						showPassword.setSelected(false);
						enterPasswordVisible.setVisible(false);
						enterPassword.setVisible(true);


					}
					else if(element.getElementsByTagName("Role").item(0).getTextContent().equals("Cooker"))
					{	
						
						cookerDashboard.show();
						loginStage.close();
						
						enterUsername.setText("");
						enterPassword.setText("");
						enterPasswordVisible.setText("");
						showPassword.setSelected(false);
						enterPasswordVisible.setVisible(false);
						enterPassword.setVisible(true);


					}
					
					break;
				}
				
				else		
				{
					invalid.setText("Invalid Credentials !");
				}	
			}
			}catch (Exception EE){System.out.println("Error in data files !");}
		});
		
		
		NodeList tableList = doc.getElementsByTagName("Table");
		
		for (int i = 0; i < tableList.getLength(); i++) { 

			Node tableNode = tableList.item(i);
					
			Element element = (Element) tableNode;
			
				
				table[i].setNumber(Integer.parseInt(element.getElementsByTagName("Number").item(0).getTextContent().trim()));
				table[i].setSeats(Integer.parseInt(element.getElementsByTagName("Seats").item(0).getTextContent().trim()));
				table[i].setSmoking(Boolean.valueOf(element.getElementsByTagName("Smoking").item(0).getTextContent()));

		}
		
		NodeList foodList = doc.getElementsByTagName("Dish");
		
		for (int i = 0; i < foodList.getLength(); i++) { 

			Node foodNode = foodList.item(i);
					
			Element element = (Element) foodNode;
			
				
				food[i].setName(element.getElementsByTagName("Name").item(0).getTextContent());
				food[i].setPrice(Float.parseFloat(element.getElementsByTagName("Price").item(0).getTextContent().trim()));
				food[i].setType(element.getElementsByTagName("Type").item(0).getTextContent());
				

		}
		
		
		
		}
		catch(Exception e) {invalid.setText("Data file is not found !");}
		
		root.getChildren().addAll(title,exit,desc,username,password,enterUsername,enterPassword,login,invalid,registerLabel,register,enterPasswordVisible,showPassword);
		
		

		//TABLE RESERVATION WINDOW
		
		
		customerDashboard.centerOnScreen();
		customerDashboard.setTitle("Reserving Table");
	    customerDashboard.setResizable(false);
		Pane root2 = new Pane();
		root2.setStyle("-fx-background-color: #9B870C"); //Yellow color
		Scene scene2 = new Scene(root2,1000,650);
		customerDashboard.setScene(scene2);
		
		
		Button logout1 = new Button("Logout");
		logout1.setLayoutX(40);
		logout1.setLayoutY(600);
		logout1.setFont(new Font("Arial Bold", 20));		
		
		Button logout2 = new Button("Logout");
		logout2.setLayoutX(40);
		logout2.setLayoutY(600);
		logout2.setFont(new Font("Arial Bold", 20));		
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to logout ?");
		alert.setTitle("Logout?");
		alert.setHeaderText(null);
		
		
		Label reserveTableLabel = new Label("1. Reserve a table: ");
		reserveTableLabel.setLayoutX(40);
		reserveTableLabel.setLayoutY(100);
		reserveTableLabel.setFont(new Font("Arial Bold", 25));
		reserveTableLabel.setTextFill(Color.web("#00008b", 1)); //Dark Blue color	
		
		Label seatsNumber = new Label("-Select number of seats: ");
		seatsNumber.setLayoutX(100);
		seatsNumber.setLayoutY(200);
		seatsNumber.setFont(new Font("Arial Bold", 22));
		seatsNumber.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label noSeat = new Label("");
		noSeat.setLayoutX(550);
		noSeat.setLayoutY(205);
		noSeat.setFont(new Font("Arial Bold", 20));
		noSeat.setTextFill(Color.web("#8B0000", 1)); //Dark Red color	
		
		Button next = new Button("Next");
		next.setLayoutX(900);
		next.setLayoutY(600);
		next.setFont(new Font("Arial Bold", 20));	
		
		double r=70;
		
		Button smokingBtn = new Button("Smoking");
		smokingBtn.setLayoutX(290);
		smokingBtn.setLayoutY(350);
		smokingBtn.setFont(new Font("Arial Bold", 20));
		smokingBtn.setShape(new Circle(r));
		smokingBtn.setMinSize(2*r, 2*r);
		smokingBtn.setMaxSize(2*r, 2*r);
		smokingBtn.setStyle("-fx-background-color: #8B0000"); //Red color
		smokingBtn.setTextFill(Color.WHITE);

		
		Button nonSmokingBtn = new Button("    Non\nSmoking");
		nonSmokingBtn.setLayoutX(590);
		nonSmokingBtn.setLayoutY(350);
		nonSmokingBtn.setFont(new Font("Arial Bold", 20));	
		nonSmokingBtn.setShape(new Circle(r));
		nonSmokingBtn.setMinSize(2*r, 2*r);
		nonSmokingBtn.setMaxSize(2*r, 2*r);
		nonSmokingBtn.setStyle("-fx-background-color: #0000FF"); //Blue color
		nonSmokingBtn.setTextFill(Color.WHITE);
		
		Label smokeLbl = new Label("");
		smokeLbl.setFont(new Font("Arial Bold", 25));
		
		String seatsNum[] = {"\t--2--","\t--4--","\t--6--","\t--8--","       --12--"};
		ComboBox<String> numberOfSeats = new ComboBox<String>(FXCollections.observableArrayList(seatsNum));
		numberOfSeats.setStyle("-fx-font: 16px \"Arial\";");
		numberOfSeats.setPromptText("----select----");
		numberOfSeats.setLayoutX(380);
		numberOfSeats.setLayoutY(200);
		
		
		smokingBtn.setOnAction(e -> {
			
			smokeLbl.setTextFill(Color.web("#8B0000", 1)); //Red color	
			smokeLbl.setText("Smoking Area");
			smokeLbl.setLayoutX(420);
			smokeLbl.setLayoutY(540);
			S=1;
			
		});
		
		nonSmokingBtn.setOnAction(e -> {
			
			smokeLbl.setTextFill(Color.web("#0000FF", 1)); //Blue color	
			smokeLbl.setText("Non Smoking Area");
			smokeLbl.setLayoutX(400);
			smokeLbl.setLayoutY(540);
			S=0;
			
		});
		
		
		next.setOnAction(e -> { 
			
			if(numberOfSeats.getSelectionModel().getSelectedIndex() != -1 && S == -1)
	    	{
				noSeat.setText("Sorry, you have to choose any smoking area !");

	    	}
			if(numberOfSeats.getSelectionModel().getSelectedIndex() == -1)
	    	{
				noSeat.setText("Please choose number of seats !");

	    	}

			else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 0 && S==0)
	    	{
				
				if (table[0].isReserved() && S==0)
	    		{
	    			noSeat.setText("Sorry, this seat is not available !\ncheck Smoking area.");
	    		}
	    		else
	    		{
	    			noSeat.setText("");
	    			R=1;
	    			customerDashboard2.show();
	    			customerDashboard.hide();
	    		}

	    	}
			else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 0 && S==1)
	    		{
				
				if (table[1].isReserved() && S==1)
	    		{
	    			noSeat.setText("Sorry, this seat is not available !\ncheck Non Smoking area.");
	    		}
	    		else
	    		{
	    			noSeat.setText("");
	    			R=2;
	    			customerDashboard2.show();
	    			customerDashboard.hide();
	    		}
	    		
	    		}
	    	else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 1 && S==0)
	    		{
	    		
				
				if (table[2].isReserved() && S==0)
	    		{
	    			noSeat.setText("Sorry, this seat is not available !\ncheck Smoking area.");
	    		}
	    		else
	    		{
	    			noSeat.setText("");
	    			R=3;
	    			customerDashboard2.show();
	    			customerDashboard.hide();
	    		}
	    		}
	    	else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 1 && S==1)
	    		{
				
	    		if (table[3].isReserved() && S==1)
	    		{
	    			noSeat.setText("Sorry, this seat is not available !\ncheck Non Smoking area.");
	    		}
	    		else
	    		{
	    			noSeat.setText("");
	    			R=4;
	    			customerDashboard2.show();
	    			customerDashboard.hide();
	    		}
	    		}
	    	else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 2 && S==0)
	    	{
				
				if (table[4].isReserved() && S==0)
	    		{
	    			noSeat.setText("Sorry, this seat is not available !\ncheck Smoking area.");
	    		}
	    		else
	    		{
	    			noSeat.setText("");
	    			R=5;
	    			customerDashboard2.show();
	    			customerDashboard.hide();
	    		}
	    	}
	    	else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 2 && S==1)
	    	{
				
				if (table[5].isReserved() && S==1)
	    		{
	    			noSeat.setText("Sorry, this seat is not available !\ncheck Non Smoking area.");
	    		}
	    		else
	    		{
	    			noSeat.setText("");
	    			R=6;
	    			customerDashboard2.show();
	    			customerDashboard.hide();
	    		}
	    	}
	    	else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 3 && S==0)
	    	{
				
				if (table[6].isReserved() && S==0)
	    		{
	    			noSeat.setText("Sorry, this seat is not available !");
	    		}
	    		else
	    		{
	    			noSeat.setText("");
	    			R=7;
	    			customerDashboard2.show();
	    			customerDashboard.hide();
	    		}
	    	}
	    	else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 3 && S==1)
	    	{
	    		noSeat.setText("Sorry, there are no 8 seats in Smoking area !\nThis is a family table.");
	    			
	    	}
	    	else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 4 && S==0)
	    	{
				
				if (table[7].isReserved() && S==0)
	    		{
	    			noSeat.setText("Sorry, this seat is not available !");
	    		}
	    		else
	    		{
	    			noSeat.setText("");
	    			R=8;
	    			customerDashboard2.show();
	    			customerDashboard.hide();
	    		}
	    	}
	    	else if(numberOfSeats.getSelectionModel().getSelectedIndex() == 4 && S==1)
	    	{
	    		noSeat.setText("Sorry, there are no 12 seats in Smoking area !\nThis is a family table.");
	    	}
			
		});
				

		
		
		//FOOD ORDERING WINDOW

		customerDashboard2.setTitle("Ordering Food");
		customerDashboard2.centerOnScreen();
	    customerDashboard2.setResizable(false);
		Pane root3 = new Pane();
		root3.setStyle("-fx-background-color: #9B870C"); //Yellow color
		Scene scene3 = new Scene(root3,1000,650);
		customerDashboard2.setScene(scene3);
		
		
		Button previous = new Button("Previous");
		previous.setLayoutX(40);
		previous.setLayoutY(600);
		previous.setFont(new Font("Arial Bold", 20));
		
		previous.setOnAction(e -> {
			
			customerDashboard2.hide();
			customerDashboard.show();
			
		});
		
		Label orderFoodLabel = new Label("2. Food ordering: ");
		orderFoodLabel.setLayoutX(40);
		orderFoodLabel.setLayoutY(100);
		orderFoodLabel.setFont(new Font("Arial Bold", 25));
		orderFoodLabel.setTextFill(Color.web("#00008b", 1)); //Dark Blue color	
		
		Label mainCourseLabel = new Label("Main Course:");
		mainCourseLabel.setLayoutX(70);
		mainCourseLabel.setLayoutY(200);
		mainCourseLabel.setFont(new Font("Arial Bold", 22));
		mainCourseLabel.setTextFill(Color.web("#4B0082", 1)); //Indigo color	
		
		Label Dish1 = new Label("-Grilled Chicken	75.00 L.E");
		Dish1.setLayoutX(70);
		Dish1.setLayoutY(260);
		Dish1.setFont(new Font("Arial Bold", 20));
		Dish1.setTextFill(Color.web("#000000", 1)); //Black color	
		
		CheckBox checkDish1 = new CheckBox("");
		checkDish1.setLayoutX(350);
		checkDish1.setLayoutY(260);
		checkDish1.setFont(new Font("Arial Bold", 15));
		
		Label Dish2 = new Label("-Mushroom Soup	60.00 L.E");
		Dish2.setLayoutX(70);
		Dish2.setLayoutY(300);
		Dish2.setFont(new Font("Arial Bold", 20));
		Dish2.setTextFill(Color.web("#000000", 1)); //Black color	
		
		CheckBox checkDish2 = new CheckBox("");
		checkDish2.setLayoutX(350);
		checkDish2.setLayoutY(300);
		checkDish2.setFont(new Font("Arial Bold", 15));
		
		Label Dish3 = new Label("-Beef Steak\t	80.00 L.E");
		Dish3.setLayoutX(70);
		Dish3.setLayoutY(340);
		Dish3.setFont(new Font("Arial Bold", 20));
		Dish3.setTextFill(Color.web("#000000", 1)); //Black color	
		
		CheckBox checkDish3 = new CheckBox("");
		checkDish3.setLayoutX(350);
		checkDish3.setLayoutY(340);
		checkDish3.setFont(new Font("Arial Bold", 15));
		
		Label Dish4 = new Label("-Pasta\t\t	40.00 L.E");
		Dish4.setLayoutX(70);
		Dish4.setLayoutY(380);
		Dish4.setFont(new Font("Arial Bold", 20));
		Dish4.setTextFill(Color.web("#000000", 1)); //Black color	
		
		CheckBox checkDish4 = new CheckBox("");
		checkDish4.setLayoutX(350);
		checkDish4.setLayoutY(380);
		checkDish4.setFont(new Font("Arial Bold", 15));
		
		Label Dish5 = new Label("-Fried Chicken	70.00 L.E");
		Dish5.setLayoutX(70);
		Dish5.setLayoutY(420);
		Dish5.setFont(new Font("Arial Bold", 20));
		Dish5.setTextFill(Color.web("#000000", 1)); //Black color	
		
		CheckBox checkDish5 = new CheckBox("");
		checkDish5.setLayoutX(350);
		checkDish5.setLayoutY(420);
		checkDish5.setFont(new Font("Arial Bold", 15));
		
		
		Label appetizersLabel = new Label("Appetizers:");
		appetizersLabel.setLayoutX(570);
		appetizersLabel.setLayoutY(200);
		appetizersLabel.setFont(new Font("Arial Bold", 22));
		appetizersLabel.setTextFill(Color.web("#4B0082", 1)); //Indigo color	
		
		
		Label Appetizer1 = new Label("-Greek Salad\t	35.00 L.E");
		Appetizer1.setLayoutX(570);
		Appetizer1.setLayoutY(250);
		Appetizer1.setFont(new Font("Arial Bold", 20));
		Appetizer1.setTextFill(Color.web("#000000", 1)); //Black color	
		
		CheckBox checkAppetizer1 = new CheckBox("");
		checkAppetizer1.setLayoutX(850);
		checkAppetizer1.setLayoutY(250);
		checkAppetizer1.setFont(new Font("Arial Bold", 15));
		
		Label Appetizer2 = new Label("-Fried Potatoes	30.00 L.E");
		Appetizer2.setLayoutX(570);
		Appetizer2.setLayoutY(290);
		Appetizer2.setFont(new Font("Arial Bold", 20));
		Appetizer2.setTextFill(Color.web("#000000", 1)); //Black color	
		
		CheckBox checkAppetizer2 = new CheckBox("");
		checkAppetizer2.setLayoutX(850);
		checkAppetizer2.setLayoutY(290);
		checkAppetizer2.setFont(new Font("Arial Bold", 15));
		
		Label dessertsLabel = new Label("Desserts:");
		dessertsLabel.setLayoutX(570);
		dessertsLabel.setLayoutY(335);
		dessertsLabel.setFont(new Font("Arial Bold", 22));
		dessertsLabel.setTextFill(Color.web("#4B0082", 1)); //Indigo color	
		
		
		Label Dessert1 = new Label("-Molten Cake\t	60.00 L.E");
		Dessert1.setLayoutX(570);
		Dessert1.setLayoutY(380);
		Dessert1.setFont(new Font("Arial Bold", 20));
		Dessert1.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label recommend = new Label("(Recommended)");
		recommend.setLayoutX(570);
		recommend.setLayoutY(365);
		recommend.setFont(new Font("Arial Bold", 15));
		recommend.setTextFill(Color.web("#8B0000", 1)); //Dark Red color
		
		CheckBox checkDessert1 = new CheckBox("");
		checkDessert1.setLayoutX(850);
		checkDessert1.setLayoutY(380);
		checkDessert1.setFont(new Font("Arial Bold", 15));
		
		Label Dessert2 = new Label("-Apple Pie\t	50.00 L.E");
		Dessert2.setLayoutX(570);
		Dessert2.setLayoutY(420);
		Dessert2.setFont(new Font("Arial Bold", 20));
		Dessert2.setTextFill(Color.web("#000000", 1)); //Black color	
		
		CheckBox checkDessert2 = new CheckBox("");
		checkDessert2.setLayoutX(850);
		checkDessert2.setLayoutY(420);
		checkDessert2.setFont(new Font("Arial Bold", 15));
		
		Button confirmOrder = new Button("Confirm Order");
		confirmOrder.setLayoutX(400);
		confirmOrder.setLayoutY(550);
		confirmOrder.setFont(new Font("Arial Bold", 20));
		confirmOrder.setTextFill(Color.web("#000000", 1)); //Black color	
		confirmOrder.setStyle("-fx-background-color: #FF0000"); //Red color
		
		
		String quantityDish1[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		ComboBox<String> quantityOfDish1 = new ComboBox<String>(FXCollections.observableArrayList(quantityDish1));
		quantityOfDish1.setStyle("-fx-font: 14px \"Arial\";");
		quantityOfDish1.setPromptText("-Quantity-");
		quantityOfDish1.setLayoutX(400);
		quantityOfDish1.setLayoutY(257);
		quantityOfDish1.setVisible(false);
		
		String quantityDish2[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		ComboBox<String> quantityOfDish2 = new ComboBox<String>(FXCollections.observableArrayList(quantityDish2));
		quantityOfDish2.setStyle("-fx-font: 14px \"Arial\";");
		quantityOfDish2.setPromptText("-Quantity-");
		quantityOfDish2.setLayoutX(400);
		quantityOfDish2.setLayoutY(297);
		quantityOfDish2.setVisible(false);
		
		String quantityDish3[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		ComboBox<String> quantityOfDish3 = new ComboBox<String>(FXCollections.observableArrayList(quantityDish3));
		quantityOfDish3.setStyle("-fx-font: 14px \"Arial\";");
		quantityOfDish3.setPromptText("-Quantity-");
		quantityOfDish3.setLayoutX(400);
		quantityOfDish3.setLayoutY(337);
		quantityOfDish3.setVisible(false);
		
		String quantityDish4[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		ComboBox<String> quantityOfDish4 = new ComboBox<String>(FXCollections.observableArrayList(quantityDish4));
		quantityOfDish4.setStyle("-fx-font: 14px \"Arial\";");
		quantityOfDish4.setPromptText("-Quantity-");
		quantityOfDish4.setLayoutX(400);
		quantityOfDish4.setLayoutY(377);
		quantityOfDish4.setVisible(false);
		
		String quantityDish5[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		ComboBox<String> quantityOfDish5 = new ComboBox<String>(FXCollections.observableArrayList(quantityDish5));
		quantityOfDish5.setStyle("-fx-font: 14px \"Arial\";");
		quantityOfDish5.setPromptText("-Quantity-");
		quantityOfDish5.setLayoutX(400);
		quantityOfDish5.setLayoutY(417);
		quantityOfDish5.setVisible(false);
		
		String quantityAppetizer1[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		ComboBox<String> quantityOfAppetizer1 = new ComboBox<String>(FXCollections.observableArrayList(quantityAppetizer1));
		quantityOfAppetizer1.setStyle("-fx-font: 13px \"Arial\";");
		quantityOfAppetizer1.setPromptText("-Quantity-");
		quantityOfAppetizer1.setLayoutX(890);
		quantityOfAppetizer1.setLayoutY(247);
		quantityOfAppetizer1.setVisible(false);
		
		String quantityAppetizer2[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		ComboBox<String> quantityOfAppetizer2 = new ComboBox<String>(FXCollections.observableArrayList(quantityAppetizer2));
		quantityOfAppetizer2.setStyle("-fx-font: 13px \"Arial\";");
		quantityOfAppetizer2.setPromptText("-Quantity-");
		quantityOfAppetizer2.setLayoutX(890);
		quantityOfAppetizer2.setLayoutY(287);
		quantityOfAppetizer2.setVisible(false);
		
		String quantityDessert1[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		ComboBox<String> quantityOfDessert1 = new ComboBox<String>(FXCollections.observableArrayList(quantityDessert1));
		quantityOfDessert1.setStyle("-fx-font: 13px \"Arial\";");
		quantityOfDessert1.setPromptText("-Quantity-");
		quantityOfDessert1.setLayoutX(890);
		quantityOfDessert1.setLayoutY(375);
		quantityOfDessert1.setVisible(false);
		
		String quantityDessert2[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		ComboBox<String> quantityOfDessert2 = new ComboBox<String>(FXCollections.observableArrayList(quantityDessert2));
		quantityOfDessert2.setStyle("-fx-font: 13px \"Arial\";");
		quantityOfDessert2.setPromptText("-Quantity-");
		quantityOfDessert2.setLayoutX(890);
		quantityOfDessert2.setLayoutY(417);
		quantityOfDessert2.setVisible(false);
		

		checkDish1.setOnAction(e -> {
			boolean selectedDish1 = checkDish1.isSelected();
			if(selectedDish1 == true)
			{
				quantityOfDish1.setVisible(true);

			}
			else
			{
				quantityOfDish1.setVisible(false);

			}
		});
		
		checkDish2.setOnAction(e -> {
			boolean selectedDish2 = checkDish2.isSelected();
			if(selectedDish2 == true)
			{
				quantityOfDish2.setVisible(true);
				
			}
			else
			{
				quantityOfDish2.setVisible(false);

			}
		});
		
		checkDish3.setOnAction(e -> {
			boolean selectedDish3 = checkDish3.isSelected();
			if(selectedDish3 == true)
			{
				quantityOfDish3.setVisible(true);
				
			}
			else
			{
				quantityOfDish3.setVisible(false);
			}
		});
		
		checkDish4.setOnAction(e -> {
			boolean selectedDish4 = checkDish4.isSelected();
			if(selectedDish4 == true)
			{
				quantityOfDish4.setVisible(true);
				
			}
			else
			{
				quantityOfDish4.setVisible(false);
			}
		});
		
		checkDish5.setOnAction(e -> {
			boolean selectedDish5 = checkDish5.isSelected();
			if(selectedDish5 == true)
			{
				quantityOfDish5.setVisible(true);
				
			}
			else
			{
				quantityOfDish5.setVisible(false);
			}
		});
		
		checkAppetizer1.setOnAction(e -> {
			boolean selectedAppetizer1 = checkAppetizer1.isSelected();
			if(selectedAppetizer1 == true)
			{
				quantityOfAppetizer1.setVisible(true);
				
			}
			else
			{
				quantityOfAppetizer1.setVisible(false);
			}
		});
		
		checkAppetizer2.setOnAction(e -> {
			boolean selectedAppetizer2 = checkAppetizer2.isSelected();
			if(selectedAppetizer2 == true)
			{
				quantityOfAppetizer2.setVisible(true);
				
			}
			else
			{
				quantityOfAppetizer2.setVisible(false);
			}
		});
		
		checkDessert1.setOnAction(e -> {
			boolean selectedDessert1 = checkDessert1.isSelected();
			if(selectedDessert1 == true)
			{
				quantityOfDessert1.setVisible(true);
				
			}
			else
			{
				quantityOfDessert1.setVisible(false);
			}
		});
		
		checkDessert2.setOnAction(e -> {
			boolean selectedDessert2 = checkDessert2.isSelected();
			if(selectedDessert2 == true)
			{
				quantityOfDessert2.setVisible(true);
				
			}
			else
			{
				quantityOfDessert2.setVisible(false);
			}
		});
		
		Label taxesNote = new Label("Additional taxes: Main Course (15% taxes), Appetizers (10% taxes),  Desserts (20% taxes)");
		taxesNote.setLayoutX(210);
		taxesNote.setLayoutY(500);
		taxesNote.setFont(new Font("Arial Bold", 14));
		taxesNote.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label noOrder = new Label("");
		noOrder.setLayoutX(310);
		noOrder.setLayoutY(600);
		noOrder.setFont(new Font("Arial Bold", 16));
		noOrder.setTextFill(Color.web("#8B0000", 1)); //Dark Red color	
		
		Alert alertFinish = new Alert(AlertType.CONFIRMATION);
		alertFinish.setContentText("Confirm your order ?");
		alertFinish.setTitle("Confirm?");
		alertFinish.setHeaderText(null);
		
		
		// WINDOW OF RECEIPT
		
		
		customerDashboard3.setTitle("Receipt");
		customerDashboard3.centerOnScreen();
	    customerDashboard3.setResizable(false);
		Pane root4 = new Pane();
		root4.setStyle("-fx-background-color: #9B870C"); //Yellow color
		Scene scene4 = new Scene(root4,1000,650);
		customerDashboard3.setScene(scene4);
		
		
		Label receiptLabel = new Label("3. Receipt: ");
		receiptLabel.setLayoutX(40);
		receiptLabel.setLayoutY(100);
		receiptLabel.setFont(new Font("Arial Bold", 25));
		receiptLabel.setTextFill(Color.web("#00008b", 1)); //Dark Blue color
		
		Label order1 = new Label("");
		order1.setLayoutX(70);
		order1.setLayoutY(180);
		order1.setFont(new Font("Arial Bold", 20));
		order1.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order2 = new Label("");
		order2.setLayoutX(70);
		order2.setLayoutY(210);
		order2.setFont(new Font("Arial Bold", 20));
		order2.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order3 = new Label("");
		order3.setLayoutX(70);
		order3.setLayoutY(240);
		order3.setFont(new Font("Arial Bold", 20));
		order3.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order4 = new Label("");
		order4.setLayoutX(70);
		order4.setLayoutY(270);
		order4.setFont(new Font("Arial Bold", 20));
		order4.setTextFill(Color.web("#000000", 1)); //Black color	

		Label order5 = new Label("");
		order5.setLayoutX(70);
		order5.setLayoutY(300);
		order5.setFont(new Font("Arial Bold", 20));
		order5.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order6 = new Label("");
		order6.setLayoutX(70);
		order6.setLayoutY(330);
		order6.setFont(new Font("Arial Bold", 20));
		order6.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order7 = new Label("");
		order7.setLayoutX(70);
		order7.setLayoutY(360);
		order7.setFont(new Font("Arial Bold", 20));
		order7.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order8 = new Label("");
		order8.setLayoutX(70);
		order8.setLayoutY(390);
		order8.setFont(new Font("Arial Bold", 20));
		order8.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order9 = new Label("");
		order9.setLayoutX(70);
		order9.setLayoutY(420);
		order9.setFont(new Font("Arial Bold", 20));
		order9.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order1Price = new Label("");
		order1Price.setLayoutX(400);
		order1Price.setLayoutY(180);
		order1Price.setFont(new Font("Arial Bold", 20));
		order1Price.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order2Price = new Label("");
		order2Price.setLayoutX(400);
		order2Price.setLayoutY(210);
		order2Price.setFont(new Font("Arial Bold", 20));
		order2Price.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order3Price = new Label("");
		order3Price.setLayoutX(400);
		order3Price.setLayoutY(240);
		order3Price.setFont(new Font("Arial Bold", 20));
		order3Price.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order4Price = new Label("");
		order4Price.setLayoutX(400);
		order4Price.setLayoutY(270);
		order4Price.setFont(new Font("Arial Bold", 20));
		order4Price.setTextFill(Color.web("#000000", 1)); //Black color	

		Label order5Price = new Label("");
		order5Price.setLayoutX(400);
		order5Price.setLayoutY(300);
		order5Price.setFont(new Font("Arial Bold", 20));
		order5Price.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order6Price = new Label("");
		order6Price.setLayoutX(400);
		order6Price.setLayoutY(330);
		order6Price.setFont(new Font("Arial Bold", 20));
		order6Price.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order7Price = new Label("");
		order7Price.setLayoutX(400);
		order7Price.setLayoutY(360);
		order7Price.setFont(new Font("Arial Bold", 20));
		order7Price.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order8Price = new Label("");
		order8Price.setLayoutX(400);
		order8Price.setLayoutY(390);
		order8Price.setFont(new Font("Arial Bold", 20));
		order8Price.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label order9Price = new Label("");
		order9Price.setLayoutX(400);
		order9Price.setLayoutY(420);
		order9Price.setFont(new Font("Arial Bold", 20));
		order9Price.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label taxesLabel = new Label("");
		taxesLabel.setLayoutX(350);
		taxesLabel.setLayoutY(500);
		taxesLabel.setFont(new Font("Arial Bold", 21));
		taxesLabel.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label totalPriceLabel = new Label("");
		totalPriceLabel.setLayoutX(370);
		totalPriceLabel.setLayoutY(535);
		totalPriceLabel.setFont(new Font("Arial Bold", 23));
		totalPriceLabel.setTextFill(Color.web("#8B0000", 1)); //Dark Red color	
		
		Label tableLabel = new Label("");
		tableLabel.setLayoutX(200);
		tableLabel.setLayoutY(100);
		tableLabel.setFont(new Font("Arial Bold", 21));
		tableLabel.setTextFill(Color.web("#8B0000", 1)); //Dark Red color	
		
		Label discountLbl = new Label("");
		discountLbl.setRotate(-7);
		discountLbl.setLayoutX(262);
		discountLbl.setLayoutY(527);
		discountLbl.setFont(new Font("Arial Bold", 23));
		discountLbl.setTextFill(Color.web("#8B0000", 1)); //Dark Red color	
		
		Label percentageSale = new Label("");
		percentageSale.setLayoutX(290);
		percentageSale.setLayoutY(570);
		percentageSale.setFont(new Font("Arial Bold", 20));
		percentageSale.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label newPriceLabel = new Label("");
		newPriceLabel.setLayoutX(500);
		newPriceLabel.setLayoutY(535);
		newPriceLabel.setFont(new Font("Arial Bold", 23));
		newPriceLabel.setTextFill(Color.web("#8B0000", 1)); //Dark Red color	

		
		//MANAGER DASHBOARD
		
		
		managerDashboard.setTitle("Manager Dashboard");
		managerDashboard.centerOnScreen();
		managerDashboard.setResizable(false);
		Pane root5 = new Pane();
		root5.setStyle("-fx-background-color: #FF6347"); //Tomato Red color
		Scene scene5 = new Scene(root5,1000,650);
		managerDashboard.setScene(scene5);
		
		Pane root52 = new Pane();
		root52.setStyle("-fx-background-color: #FF6347"); //Tomato Red color
		Scene scene52 = new Scene(root52,1000,650);
		
		Pane root53 = new Pane();
		root53.setStyle("-fx-background-color: #FF6347"); //Tomato Red color
		Scene scene53 = new Scene(root53,1000,650);
		
		Pane root54 = new Pane();
		root54.setStyle("-fx-background-color: #FF6347"); //Tomato Red color
		Scene scene54 = new Scene(root54,1000,650);
		
		Pane root55 = new Pane();
		root55.setStyle("-fx-background-color: #FF6347"); //Tomato Red color
		Scene scene55 = new Scene(root55,1000,650);
		
		Pane root56 = new Pane();
		root56.setStyle("-fx-background-color: #FF6347"); //Tomato Red color
		Scene scene56 = new Scene(root56,1000,650);
		
		Pane root57 = new Pane();
		root57.setStyle("-fx-background-color: #FF6347"); //Tomato Red color
		Scene scene57 = new Scene(root57,1000,650);
		
		Pane root58 = new Pane();
		root58.setStyle("-fx-background-color: #FF6347"); //Tomato Red color
		Scene scene58 = new Scene(root58,1000,650);
		
		Button logoutManager = new Button("Logout");
		logoutManager.setLayoutX(40);
		logoutManager.setLayoutY(600);
		logoutManager.setFont(new Font("Arial Bold", 20));
		logoutManager.setOnAction(e -> {
			
			managerDashboard.close();
			loginStage.show();
			
		});
		
		Button nextCustomer1 = new Button("Next Customer");
		nextCustomer1.setLayoutX(850);
		nextCustomer1.setLayoutY(600);
		nextCustomer1.setOnAction(e -> {
			managerDashboard.setScene(scene52);
		});
		
		Button nextCustomer2 = new Button("Next Customer");
		nextCustomer2.setLayoutX(850);
		nextCustomer2.setLayoutY(600);
		nextCustomer2.setOnAction(e -> {
			managerDashboard.setScene(scene53);
		});
		
		Button nextCustomer3 = new Button("Next Customer");
		nextCustomer3.setLayoutX(850);
		nextCustomer3.setLayoutY(600);
		nextCustomer3.setOnAction(e -> {
			managerDashboard.setScene(scene54);
		});
		
		Button nextCustomer4 = new Button("Next Customer");
		nextCustomer4.setLayoutX(850);
		nextCustomer4.setLayoutY(600);
		nextCustomer4.setOnAction(e -> {
			managerDashboard.setScene(scene55);
		});
		
		Button nextCustomer5 = new Button("Next Customer");
		nextCustomer5.setLayoutX(850);
		nextCustomer5.setLayoutY(600);
		nextCustomer5.setOnAction(e -> {
			managerDashboard.setScene(scene56);
		});
		
		Button nextCustomer6 = new Button("Next Customer");
		nextCustomer6.setLayoutX(850);
		nextCustomer6.setLayoutY(600);
		nextCustomer6.setOnAction(e -> {
			managerDashboard.setScene(scene57);
		});
		
		Button nextCustomer7 = new Button("Next Customer");
		nextCustomer7.setLayoutX(850);
		nextCustomer7.setLayoutY(600);
		nextCustomer7.setOnAction(e -> {
			managerDashboard.setScene(scene58);
		});
		
		Button previousCustomer1 = new Button("Previous Customer");
		previousCustomer1.setLayoutX(30);
		previousCustomer1.setLayoutY(600);
		previousCustomer1.setOnAction(e -> {
			managerDashboard.setScene(scene5);
		});
		
		Button previousCustomer2 = new Button("Previous Customer");
		previousCustomer2.setLayoutX(30);
		previousCustomer2.setLayoutY(600);
		previousCustomer2.setOnAction(e -> {
			managerDashboard.setScene(scene52);
		});
		
		Button previousCustomer3 = new Button("Previous Customer");
		previousCustomer3.setLayoutX(30);
		previousCustomer3.setLayoutY(600);
		previousCustomer3.setOnAction(e -> {
			managerDashboard.setScene(scene53);
		});
		
		Button previousCustomer4 = new Button("Previous Customer");
		previousCustomer4.setLayoutX(30);
		previousCustomer4.setLayoutY(600);
		previousCustomer4.setOnAction(e -> {
			managerDashboard.setScene(scene54);
		});
		
		Button previousCustomer5 = new Button("Previous Customer");
		previousCustomer5.setLayoutX(30);
		previousCustomer5.setLayoutY(600);
		previousCustomer5.setOnAction(e -> {
			managerDashboard.setScene(scene55);
		});
		
		Button previousCustomer6 = new Button("Previous Customer");
		previousCustomer6.setLayoutX(30);
		previousCustomer6.setLayoutY(600);
		previousCustomer6.setOnAction(e -> {
			managerDashboard.setScene(scene56);
		});
		
		Button previousCustomer7 = new Button("Previous Customer");
		previousCustomer7.setLayoutX(30);
		previousCustomer7.setLayoutY(600);
		previousCustomer7.setOnAction(e -> {
			managerDashboard.setScene(scene57);
		});
		
		nextCustomer1.setVisible(false);
		nextCustomer2.setVisible(false);
		nextCustomer3.setVisible(false);
		nextCustomer4.setVisible(false);
		nextCustomer5.setVisible(false);
		nextCustomer6.setVisible(false);
		nextCustomer7.setVisible(false);
		previousCustomer1.setVisible(false);
		previousCustomer2.setVisible(false);
		previousCustomer3.setVisible(false);
		previousCustomer4.setVisible(false);
		previousCustomer5.setVisible(false);
		previousCustomer6.setVisible(false);
		previousCustomer7.setVisible(false);

		
		Label managerTitle = new Label("Today's Reservations");
		managerTitle.setLayoutX(370);
		managerTitle.setLayoutY(50);
		managerTitle.setFont(new Font("Arial Bold", 25));
		managerTitle.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		Label totalMoneyOfTodayLabel = new Label("");
		totalMoneyOfTodayLabel.setLayoutX(300);
		totalMoneyOfTodayLabel.setLayoutY(590);
		totalMoneyOfTodayLabel.setFont(new Font("Arial Bold", 22));
		totalMoneyOfTodayLabel.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label managerTitle2 = new Label("Today's Reservations");
		managerTitle2.setLayoutX(370);
		managerTitle2.setLayoutY(50);
		managerTitle2.setFont(new Font("Arial Bold", 25));
		managerTitle2.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		Label totalMoneyOfTodayLabel2 = new Label("Total money earned today: ");
		totalMoneyOfTodayLabel2.setLayoutX(300);
		totalMoneyOfTodayLabel2.setLayoutY(590);
		totalMoneyOfTodayLabel2.setFont(new Font("Arial Bold", 22));
		totalMoneyOfTodayLabel2.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label managerTitle3 = new Label("Today's Reservations");
		managerTitle3.setLayoutX(370);
		managerTitle3.setLayoutY(50);
		managerTitle3.setFont(new Font("Arial Bold", 25));
		managerTitle3.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		Label totalMoneyOfTodayLabel3 = new Label("Total money earned today: ");
		totalMoneyOfTodayLabel3.setLayoutX(300);
		totalMoneyOfTodayLabel3.setLayoutY(590);
		totalMoneyOfTodayLabel3.setFont(new Font("Arial Bold", 22));
		totalMoneyOfTodayLabel3.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label managerTitle4 = new Label("Today's Reservations");
		managerTitle4.setLayoutX(370);
		managerTitle4.setLayoutY(50);
		managerTitle4.setFont(new Font("Arial Bold", 25));
		managerTitle4.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		Label totalMoneyOfTodayLabel4 = new Label("Total money earned today: ");
		totalMoneyOfTodayLabel4.setLayoutX(300);
		totalMoneyOfTodayLabel4.setLayoutY(590);
		totalMoneyOfTodayLabel4.setFont(new Font("Arial Bold", 22));
		totalMoneyOfTodayLabel4.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label managerTitle5 = new Label("Today's Reservations");
		managerTitle5.setLayoutX(370);
		managerTitle5.setLayoutY(50);
		managerTitle5.setFont(new Font("Arial Bold", 25));
		managerTitle5.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		Label totalMoneyOfTodayLabel5 = new Label("Total money earned today: ");
		totalMoneyOfTodayLabel5.setLayoutX(300);
		totalMoneyOfTodayLabel5.setLayoutY(590);
		totalMoneyOfTodayLabel5.setFont(new Font("Arial Bold", 22));
		totalMoneyOfTodayLabel5.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label managerTitle6 = new Label("Today's Reservations");
		managerTitle6.setLayoutX(370);
		managerTitle6.setLayoutY(50);
		managerTitle6.setFont(new Font("Arial Bold", 25));
		managerTitle6.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		Label totalMoneyOfTodayLabel6 = new Label("Total money earned today: ");
		totalMoneyOfTodayLabel6.setLayoutX(300);
		totalMoneyOfTodayLabel6.setLayoutY(590);
		totalMoneyOfTodayLabel6.setFont(new Font("Arial Bold", 22));
		totalMoneyOfTodayLabel6.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label managerTitle7 = new Label("Today's Reservations");
		managerTitle7.setLayoutX(370);
		managerTitle7.setLayoutY(50);
		managerTitle7.setFont(new Font("Arial Bold", 25));
		managerTitle7.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		Label totalMoneyOfTodayLabel7 = new Label("Total money earned today: ");
		totalMoneyOfTodayLabel7.setLayoutX(300);
		totalMoneyOfTodayLabel7.setLayoutY(590);
		totalMoneyOfTodayLabel7.setFont(new Font("Arial Bold", 22));
		totalMoneyOfTodayLabel7.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label managerTitle8 = new Label("Today's Reservations");
		managerTitle8.setLayoutX(370);
		managerTitle8.setLayoutY(50);
		managerTitle8.setFont(new Font("Arial Bold", 25));
		managerTitle8.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		Label totalMoneyOfTodayLabel8 = new Label("Total money earned today: ");
		totalMoneyOfTodayLabel8.setLayoutX(300);
		totalMoneyOfTodayLabel8.setLayoutY(590);
		totalMoneyOfTodayLabel8.setFont(new Font("Arial Bold", 22));
		totalMoneyOfTodayLabel8.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer1Name= new Label("");
		customer1Name.setLayoutX(70);
		customer1Name.setLayoutY(155);
		customer1Name.setFont(new Font("Arial Bold", 20));
		customer1Name.setTextFill(Color.web("#000000", 1)); //Black color
		
		
		Label customer2Name= new Label("");
		customer2Name.setLayoutX(70);
		customer2Name.setLayoutY(155);
		customer2Name.setFont(new Font("Arial Bold", 20));
		customer2Name.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer3Name= new Label("");
		customer3Name.setLayoutX(70);
		customer3Name.setLayoutY(155);
		customer3Name.setFont(new Font("Arial Bold", 20));
		customer3Name.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer4Name= new Label("");
		customer4Name.setLayoutX(70);
		customer4Name.setLayoutY(155);
		customer4Name.setFont(new Font("Arial Bold", 20));
		customer4Name.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer5Name= new Label("");
		customer5Name.setLayoutX(70);
		customer5Name.setLayoutY(155);
		customer5Name.setFont(new Font("Arial Bold", 20));
		customer5Name.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer6Name= new Label("");
		customer6Name.setLayoutX(70);
		customer6Name.setLayoutY(155);
		customer6Name.setFont(new Font("Arial Bold", 20));
		customer6Name.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer7Name= new Label("");
		customer7Name.setLayoutX(70);
		customer7Name.setLayoutY(155);
		customer7Name.setFont(new Font("Arial Bold", 20));
		customer7Name.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer8Name= new Label("");
		customer8Name.setLayoutX(70);
		customer8Name.setLayoutY(155);
		customer8Name.setFont(new Font("Arial Bold", 20));
		customer8Name.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer1Table= new Label("");
		customer1Table.setLayoutX(70);
		customer1Table.setLayoutY(240);
		customer1Table.setFont(new Font("Arial Bold", 20));
		customer1Table.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer2Table= new Label("");
		customer2Table.setLayoutX(70);
		customer2Table.setLayoutY(240);
		customer2Table.setFont(new Font("Arial Bold", 20));
		customer2Table.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer3Table= new Label("");
		customer3Table.setLayoutX(70);
		customer3Table.setLayoutY(240);
		customer3Table.setFont(new Font("Arial Bold", 20));
		customer3Table.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer4Table= new Label("");
		customer4Table.setLayoutX(70);
		customer4Table.setLayoutY(240);
		customer4Table.setFont(new Font("Arial Bold", 20));
		customer4Table.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer5Table= new Label("");
		customer5Table.setLayoutX(70);
		customer5Table.setLayoutY(240);
		customer5Table.setFont(new Font("Arial Bold", 20));
		customer5Table.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer6Table= new Label("");
		customer6Table.setLayoutX(70);
		customer6Table.setLayoutY(240);
		customer6Table.setFont(new Font("Arial Bold", 20));
		customer6Table.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer7Table= new Label("");
		customer7Table.setLayoutX(70);
		customer7Table.setLayoutY(240);
		customer7Table.setFont(new Font("Arial Bold", 20));
		customer7Table.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer8Table= new Label("");
		customer8Table.setLayoutX(70);
		customer8Table.setLayoutY(240);
		customer8Table.setFont(new Font("Arial Bold", 20));
		customer8Table.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer1PricePaid= new Label("");
		customer1PricePaid.setLayoutX(70);
		customer1PricePaid.setLayoutY(280);
		customer1PricePaid.setFont(new Font("Arial Bold", 20));
		customer1PricePaid.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer2PricePaid= new Label("");
		customer2PricePaid.setLayoutX(70);
		customer2PricePaid.setLayoutY(280);
		customer2PricePaid.setFont(new Font("Arial Bold", 20));
		customer2PricePaid.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer3PricePaid= new Label("");
		customer3PricePaid.setLayoutX(70);
		customer3PricePaid.setLayoutY(280);
		customer3PricePaid.setFont(new Font("Arial Bold", 20));
		customer3PricePaid.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer4PricePaid= new Label("");
		customer4PricePaid.setLayoutX(70);
		customer4PricePaid.setLayoutY(280);
		customer4PricePaid.setFont(new Font("Arial Bold", 20));
		customer4PricePaid.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer5PricePaid= new Label("");
		customer5PricePaid.setLayoutX(70);
		customer5PricePaid.setLayoutY(280);
		customer5PricePaid.setFont(new Font("Arial Bold", 20));
		customer5PricePaid.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer6PricePaid= new Label("");
		customer6PricePaid.setLayoutX(70);
		customer6PricePaid.setLayoutY(280);
		customer6PricePaid.setFont(new Font("Arial Bold", 20));
		customer6PricePaid.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer7PricePaid= new Label("");
		customer7PricePaid.setLayoutX(70);
		customer7PricePaid.setLayoutY(280);
		customer7PricePaid.setFont(new Font("Arial Bold", 20));
		customer7PricePaid.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer8PricePaid= new Label("");
		customer8PricePaid.setLayoutX(70);
		customer8PricePaid.setLayoutY(280);
		customer8PricePaid.setFont(new Font("Arial Bold", 20));
		customer8PricePaid.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer1Dishes= new Label("");
		customer1Dishes.setLayoutX(70);
		customer1Dishes.setLayoutY(320);
		customer1Dishes.setFont(new Font("Arial Bold", 20));
		customer1Dishes.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer2Dishes= new Label("");
		customer2Dishes.setLayoutX(70);
		customer2Dishes.setLayoutY(320);
		customer2Dishes.setFont(new Font("Arial Bold", 20));
		customer2Dishes.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer3Dishes= new Label("");
		customer3Dishes.setLayoutX(70);
		customer3Dishes.setLayoutY(320);
		customer3Dishes.setFont(new Font("Arial Bold", 20));
		customer3Dishes.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer4Dishes= new Label("");
		customer4Dishes.setLayoutX(70);
		customer4Dishes.setLayoutY(320);
		customer4Dishes.setFont(new Font("Arial Bold", 20));
		customer4Dishes.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer5Dishes= new Label("");
		customer5Dishes.setLayoutX(70);
		customer5Dishes.setLayoutY(320);
		customer5Dishes.setFont(new Font("Arial Bold", 20));
		customer5Dishes.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer6Dishes= new Label("");
		customer6Dishes.setLayoutX(70);
		customer6Dishes.setLayoutY(320);
		customer6Dishes.setFont(new Font("Arial Bold", 20));
		customer6Dishes.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer7Dishes= new Label("");
		customer7Dishes.setLayoutX(70);
		customer7Dishes.setLayoutY(320);
		customer7Dishes.setFont(new Font("Arial Bold", 20));
		customer7Dishes.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer8Dishes= new Label("");
		customer8Dishes.setLayoutX(70);
		customer8Dishes.setLayoutY(320);
		customer8Dishes.setFont(new Font("Arial Bold", 20));
		customer8Dishes.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label noReservations = new Label("No Reservations Yet");
		noReservations.setLayoutX(370);
		noReservations.setLayoutY(330);
		noReservations.setFont(new Font("Arial Bold", 25));
		noReservations.setTextFill(Color.web("#000000", 1)); //Black color	

		
		//COOKER DASHBOARD 
		
		cookerDashboard.setTitle("Cooker Dashboard");
		cookerDashboard.centerOnScreen();
		cookerDashboard.setResizable(false);
		Pane root6 = new Pane();
		root6.setStyle("-fx-background-color: #FFE57C"); //Yellowish color
		Scene scene6 = new Scene(root6,1000,650);
		cookerDashboard.setScene(scene6);
		
		Pane root62 = new Pane();
		root62.setStyle("-fx-background-color: #FFE57C"); //Yellowish color
		Scene scene62 = new Scene(root62,1000,650);
		
		Pane root63 = new Pane();
		root63.setStyle("-fx-background-color: #FFE57C"); //Yellowish color
		Scene scene63 = new Scene(root63,1000,650);
		
		Pane root64 = new Pane();
		root64.setStyle("-fx-background-color: #FFE57C"); //Yellowish color
		Scene scene64 = new Scene(root64,1000,650);
		
		Pane root65 = new Pane();
		root65.setStyle("-fx-background-color: #FFE57C"); //Yellowish color
		Scene scene65 = new Scene(root65,1000,650);
		
		Pane root66 = new Pane();
		root66.setStyle("-fx-background-color: #FFE57C"); //Yellowish color
		Scene scene66 = new Scene(root66,1000,650);
		
		Pane root67 = new Pane();
		root67.setStyle("-fx-background-color: #FFE57C"); //Yellowish color
		Scene scene67 = new Scene(root67,1000,650);
		
		Pane root68 = new Pane();
		root68.setStyle("-fx-background-color: #FFE57C"); //Yellowish color
		Scene scene68 = new Scene(root68,1000,650);
		
		Button logoutCooker = new Button("Logout");
		logoutCooker.setLayoutX(40);
		logoutCooker.setLayoutY(600);
		logoutCooker.setFont(new Font("Arial Bold", 20));
		logoutCooker.setOnAction(e -> {
			
			cookerDashboard.close();
			loginStage.show();
			
		});
		
		Button nextDishes1 = new Button("Next Order");
		nextDishes1.setLayoutX(850);
		nextDishes1.setLayoutY(600);
		nextDishes1.setOnAction(e -> {
			cookerDashboard.setScene(scene62);
		});
		
		Button nextDishes2 = new Button("Next Order");
		nextDishes2.setLayoutX(850);
		nextDishes2.setLayoutY(600);
		nextDishes2.setOnAction(e -> {
			cookerDashboard.setScene(scene63);
		});
		
		Button nextDishes3 = new Button("Next Order");
		nextDishes3.setLayoutX(850);
		nextDishes3.setLayoutY(600);
		nextDishes3.setOnAction(e -> {
			cookerDashboard.setScene(scene64);
		});
		
		Button nextDishes4 = new Button("Next Order");
		nextDishes4.setLayoutX(850);
		nextDishes4.setLayoutY(600);
		nextDishes4.setOnAction(e -> {
			cookerDashboard.setScene(scene65);
		});
		
		Button nextDishes5 = new Button("Next Order");
		nextDishes5.setLayoutX(850);
		nextDishes5.setLayoutY(600);
		nextDishes5.setOnAction(e -> {
			cookerDashboard.setScene(scene66);
		});
		
		Button nextDishes6 = new Button("Next Order");
		nextDishes6.setLayoutX(850);
		nextDishes6.setLayoutY(600);
		nextDishes6.setOnAction(e -> {
			cookerDashboard.setScene(scene67);
		});
		
		Button nextDishes7 = new Button("Next Order");
		nextDishes7.setLayoutX(850);
		nextDishes7.setLayoutY(600);
		nextDishes7.setOnAction(e -> {
			cookerDashboard.setScene(scene68);
		});
		
		Button previousDishes1 = new Button("Previous Order");
		previousDishes1.setLayoutX(30);
		previousDishes1.setLayoutY(600);
		previousDishes1.setOnAction(e -> {
			cookerDashboard.setScene(scene6);
		});
		
		Button previousDishes2 = new Button("Previous Order");
		previousDishes2.setLayoutX(30);
		previousDishes2.setLayoutY(600);
		previousDishes2.setOnAction(e -> {
			cookerDashboard.setScene(scene62);
		});
		
		Button previousDishes3 = new Button("Previous Order");
		previousDishes3.setLayoutX(30);
		previousDishes3.setLayoutY(600);
		previousDishes3.setOnAction(e -> {
			cookerDashboard.setScene(scene63);
		});
		
		Button previousDishes4 = new Button("Previous Order");
		previousDishes4.setLayoutX(30);
		previousDishes4.setLayoutY(600);
		previousDishes4.setOnAction(e -> {
			cookerDashboard.setScene(scene64);
		});
		
		Button previousDishes5 = new Button("Previous Order");
		previousDishes5.setLayoutX(30);
		previousDishes5.setLayoutY(600);
		previousDishes5.setOnAction(e -> {
			cookerDashboard.setScene(scene65);
		});
		
		Button previousDishes6 = new Button("Previous Order");
		previousDishes6.setLayoutX(30);
		previousDishes6.setLayoutY(600);
		previousDishes6.setOnAction(e -> {
			cookerDashboard.setScene(scene66);
		});
		
		Button previousDishes7 = new Button("Previous Order");
		previousDishes7.setLayoutX(30);
		previousDishes7.setLayoutY(600);
		previousDishes7.setOnAction(e -> {
			cookerDashboard.setScene(scene67);
		});
		
		nextDishes1.setVisible(false);
		nextDishes2.setVisible(false);
		nextDishes3.setVisible(false);
		nextDishes4.setVisible(false);
		nextDishes5.setVisible(false);
		nextDishes6.setVisible(false);
		nextDishes7.setVisible(false);
		previousDishes1.setVisible(false);
		previousDishes2.setVisible(false);
		previousDishes3.setVisible(false);
		previousDishes4.setVisible(false);
		previousDishes5.setVisible(false);
		previousDishes6.setVisible(false);
		previousDishes7.setVisible(false);

		
		Label noOrdersYet = new Label("No Orders Yet");
		noOrdersYet.setLayoutX(370);
		noOrdersYet.setLayoutY(330);
		noOrdersYet.setFont(new Font("Arial Bold", 25));
		noOrdersYet.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label cookerTitle = new Label("Today's Orders");
		cookerTitle.setLayoutX(370);
		cookerTitle.setLayoutY(50);
		cookerTitle.setFont(new Font("Arial Bold", 25));
		cookerTitle.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		
		Label cookerTitle2 = new Label("Today's Orders");
		cookerTitle2.setLayoutX(370);
		cookerTitle2.setLayoutY(50);
		cookerTitle2.setFont(new Font("Arial Bold", 25));
		cookerTitle2.setTextFill(Color.web("#0000FF", 1)); //Blue color			
		
		Label cookerTitle3 = new Label("Today's Orders");
		cookerTitle3.setLayoutX(370);
		cookerTitle3.setLayoutY(50);
		cookerTitle3.setFont(new Font("Arial Bold", 25));
		cookerTitle3.setTextFill(Color.web("#0000FF", 1)); //Blue color	
		
		Label cookerTitle4 = new Label("Today's Orders");
		cookerTitle4.setLayoutX(370);
		cookerTitle4.setLayoutY(50);
		cookerTitle4.setFont(new Font("Arial Bold", 25));
		cookerTitle4.setTextFill(Color.web("#0000FF", 1)); //Blue color	
		
		Label cookerTitle5 = new Label("Today's Orders");
		cookerTitle5.setLayoutX(370);
		cookerTitle5.setLayoutY(50);
		cookerTitle5.setFont(new Font("Arial Bold", 25));
		cookerTitle5.setTextFill(Color.web("#0000FF", 1)); //Blue color	
		
		Label cookerTitle6 = new Label("Today's Orders");
		cookerTitle6.setLayoutX(370);
		cookerTitle6.setLayoutY(50);
		cookerTitle6.setFont(new Font("Arial Bold", 25));
		cookerTitle6.setTextFill(Color.web("#0000FF", 1)); //Blue color	
		
		Label cookerTitle7 = new Label("Today's Orders");
		cookerTitle7.setLayoutX(370);
		cookerTitle7.setLayoutY(50);
		cookerTitle7.setFont(new Font("Arial Bold", 25));
		cookerTitle7.setTextFill(Color.web("#0000FF", 1)); //Blue color	
		
		Label cookerTitle8 = new Label("Today's Orders");
		cookerTitle8.setLayoutX(370);
		cookerTitle8.setLayoutY(50);
		cookerTitle8.setFont(new Font("Arial Bold", 25));
		cookerTitle8.setTextFill(Color.web("#0000FF", 1)); //Blue color	
		
		Label customer1OrderTable= new Label("");
		customer1OrderTable.setLayoutX(70);
		customer1OrderTable.setLayoutY(155);
		customer1OrderTable.setFont(new Font("Arial Bold", 20));
		customer1OrderTable.setTextFill(Color.web("#000000", 1)); //Black color
		
		
		Label customer2OrderTable= new Label("");
		customer2OrderTable.setLayoutX(70);
		customer2OrderTable.setLayoutY(155);
		customer2OrderTable.setFont(new Font("Arial Bold", 20));
		customer2OrderTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer3OrderTable= new Label("");
		customer3OrderTable.setLayoutX(70);
		customer3OrderTable.setLayoutY(155);
		customer3OrderTable.setFont(new Font("Arial Bold", 20));
		customer3OrderTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer4OrderTable= new Label("");
		customer4OrderTable.setLayoutX(70);
		customer4OrderTable.setLayoutY(155);
		customer4OrderTable.setFont(new Font("Arial Bold", 20));
		customer4OrderTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer5OrderTable= new Label("");
		customer5OrderTable.setLayoutX(70);
		customer5OrderTable.setLayoutY(155);
		customer5OrderTable.setFont(new Font("Arial Bold", 20));
		customer5OrderTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer6OrderTable= new Label("");
		customer6OrderTable.setLayoutX(70);
		customer6OrderTable.setLayoutY(155);
		customer6OrderTable.setFont(new Font("Arial Bold", 20));
		customer6OrderTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer7OrderTable= new Label("");
		customer7OrderTable.setLayoutX(70);
		customer7OrderTable.setLayoutY(155);
		customer7OrderTable.setFont(new Font("Arial Bold", 20));
		customer7OrderTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer8OrderTable= new Label("");
		customer8OrderTable.setLayoutX(70);
		customer8OrderTable.setLayoutY(155);
		customer8OrderTable.setFont(new Font("Arial Bold", 20));
		customer8OrderTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer1Order= new Label("");
		customer1Order.setLayoutX(70);
		customer1Order.setLayoutY(240);
		customer1Order.setFont(new Font("Arial Bold", 20));
		customer1Order.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer2Order= new Label("");
		customer2Order.setLayoutX(70);
		customer2Order.setLayoutY(240);
		customer2Order.setFont(new Font("Arial Bold", 20));
		customer2Order.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer3Order= new Label("");
		customer3Order.setLayoutX(70);
		customer3Order.setLayoutY(240);
		customer3Order.setFont(new Font("Arial Bold", 20));
		customer3Order.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer4Order= new Label("");
		customer4Order.setLayoutX(70);
		customer4Order.setLayoutY(240);
		customer4Order.setFont(new Font("Arial Bold", 20));
		customer4Order.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer5Order= new Label("");
		customer5Order.setLayoutX(70);
		customer5Order.setLayoutY(240);
		customer5Order.setFont(new Font("Arial Bold", 20));
		customer5Order.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer6Order= new Label("");
		customer6Order.setLayoutX(70);
		customer6Order.setLayoutY(240);
		customer6Order.setFont(new Font("Arial Bold", 20));
		customer6Order.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer7Order= new Label("");
		customer7Order.setLayoutX(70);
		customer7Order.setLayoutY(240);
		customer7Order.setFont(new Font("Arial Bold", 20));
		customer7Order.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer8Order= new Label("");
		customer8Order.setLayoutX(70);
		customer8Order.setLayoutY(240);
		customer8Order.setFont(new Font("Arial Bold", 20));
		customer8Order.setTextFill(Color.web("#000000", 1)); //Black color
		
		//WAITER DASHBOARD
		
		
		waiterDashboard.setTitle("Waiter Dashboard");
		waiterDashboard.centerOnScreen();
		waiterDashboard.setResizable(false);
		Pane root7 = new Pane();
		root7.setStyle("-fx-background-color: #EBF4FA "); //Water Blue color
		Scene scene7 = new Scene(root7,1000,650);
		waiterDashboard.setScene(scene7);
		
		Button logoutWaiter = new Button("Logout");
		logoutWaiter.setLayoutX(40);
		logoutWaiter.setLayoutY(600);
		logoutWaiter.setFont(new Font("Arial Bold", 20));
		logoutWaiter.setOnAction(e -> {
			
			waiterDashboard.close();
			loginStage.show();
			
		});
		
		Label noReservationsYet = new Label("No Reservations Yet");
		noReservationsYet.setLayoutX(370);
		noReservationsYet.setLayoutY(330);
		noReservationsYet.setFont(new Font("Arial Bold", 25));
		noReservationsYet.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label waiterTitle = new Label("Today's Reservations");
		waiterTitle.setLayoutX(370);
		waiterTitle.setLayoutY(50);
		waiterTitle.setFont(new Font("Arial Bold", 25));
		waiterTitle.setTextFill(Color.web("#0000FF", 1)); //Blue color	
		
		Label customer1Reservation= new Label("");
		customer1Reservation.setLayoutX(70);
		customer1Reservation.setLayoutY(160);
		customer1Reservation.setFont(new Font("Arial Bold", 20));
		customer1Reservation.setTextFill(Color.web("#000000", 1)); //Black color
		
		
		Label customer2Reservation= new Label("");
		customer2Reservation.setLayoutX(70);
		customer2Reservation.setLayoutY(210);
		customer2Reservation.setFont(new Font("Arial Bold", 20));
		customer2Reservation.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer3Reservation= new Label("");
		customer3Reservation.setLayoutX(70);
		customer3Reservation.setLayoutY(260);
		customer3Reservation.setFont(new Font("Arial Bold", 20));
		customer3Reservation.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer4Reservation= new Label("");
		customer4Reservation.setLayoutX(70);
		customer4Reservation.setLayoutY(310);
		customer4Reservation.setFont(new Font("Arial Bold", 20));
		customer4Reservation.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer5Reservation= new Label("");
		customer5Reservation.setLayoutX(70);
		customer5Reservation.setLayoutY(360);
		customer5Reservation.setFont(new Font("Arial Bold", 20));
		customer5Reservation.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer6Reservation= new Label("");
		customer6Reservation.setLayoutX(70);
		customer6Reservation.setLayoutY(410);
		customer6Reservation.setFont(new Font("Arial Bold", 20));
		customer6Reservation.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer7Reservation= new Label("");
		customer7Reservation.setLayoutX(70);
		customer7Reservation.setLayoutY(460);
		customer7Reservation.setFont(new Font("Arial Bold", 20));
		customer7Reservation.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer8Reservation= new Label("");
		customer8Reservation.setLayoutX(70);
		customer8Reservation.setLayoutY(510);
		customer8Reservation.setFont(new Font("Arial Bold", 20));
		customer8Reservation.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer1ReservationTable= new Label("");
		customer1ReservationTable.setLayoutX(600);
		customer1ReservationTable.setLayoutY(160);
		customer1ReservationTable.setFont(new Font("Arial Bold", 20));
		customer1ReservationTable.setTextFill(Color.web("#000000", 1)); //Black color
		
		
		Label customer2ReservationTable= new Label("");
		customer2ReservationTable.setLayoutX(600);
		customer2ReservationTable.setLayoutY(210);
		customer2ReservationTable.setFont(new Font("Arial Bold", 20));
		customer2ReservationTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer3ReservationTable= new Label("");
		customer3ReservationTable.setLayoutX(600);
		customer3ReservationTable.setLayoutY(260);
		customer3ReservationTable.setFont(new Font("Arial Bold", 20));
		customer3ReservationTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer4ReservationTable= new Label("");
		customer4ReservationTable.setLayoutX(600);
		customer4ReservationTable.setLayoutY(310);
		customer4ReservationTable.setFont(new Font("Arial Bold", 20));
		customer4ReservationTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer5ReservationTable= new Label("");
		customer5ReservationTable.setLayoutX(600);
		customer5ReservationTable.setLayoutY(360);
		customer5ReservationTable.setFont(new Font("Arial Bold", 20));
		customer5ReservationTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer6ReservationTable= new Label("");
		customer6ReservationTable.setLayoutX(600);
		customer6ReservationTable.setLayoutY(410);
		customer6ReservationTable.setFont(new Font("Arial Bold", 20));
		customer6ReservationTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer7ReservationTable= new Label("");
		customer7ReservationTable.setLayoutX(600);
		customer7ReservationTable.setLayoutY(460);
		customer7ReservationTable.setFont(new Font("Arial Bold", 20));
		customer7ReservationTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		Label customer8ReservationTable= new Label("");
		customer8ReservationTable.setLayoutX(600);
		customer8ReservationTable.setLayoutY(510);
		customer8ReservationTable.setFont(new Font("Arial Bold", 20));
		customer8ReservationTable.setTextFill(Color.web("#000000", 1)); //Black color	
		
		
		
		//Adding to files the customer reservation
		
		
		confirmOrder.setOnAction(s -> {
						
			DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db2;
		    db2 = null;
		    try {
		        db2 = dbf2.newDocumentBuilder();
		        Document doc = db2.parse("Data.xml");
		        NodeList customers = doc.getElementsByTagName("customersOrders");
		        
			    Element e = doc.createElement("Customer");
		        customers.item(0).appendChild(e);

			    Element name = doc.createElement("nameOfCustomer");
			    name.appendChild(doc.createTextNode(user.getName()));
		        e.appendChild(name);
			    
			    Element dishes = doc.createElement("dishesOrdered");
			    e.appendChild(dishes);
			
			if(quantityOfDish1.getSelectionModel().getSelectedIndex() >= 0 && quantityOfDish1.isVisible())
			{
			
			food[0].setName("Grilled Chicken");
			food[0].setPrice(75.00);
			food[0].setQuantity((quantityOfDish1.getSelectionModel().getSelectedIndex()+1));
			food[0].setTaxes(75.00, 0.15, (quantityOfDish1.getSelectionModel().getSelectedIndex()+1));
			food[0].setOrdered(true);
			}
			else
			{
				food[0].setOrdered(false);
				food[0].setPrice(0);
				food[0].setTaxes(0, 0, 0);
			}
			
			if (quantityOfDish2.getSelectionModel().getSelectedIndex() >= 0 && quantityOfDish2.isVisible())
			{
			food[5].setName("Mushroom Soup");
			food[5].setPrice(60.00);
			food[5].setQuantity((quantityOfDish2.getSelectionModel().getSelectedIndex()+1));
			food[5].setTaxes(60.00, 0.15, (quantityOfDish2.getSelectionModel().getSelectedIndex()+1));
			food[5].setOrdered(true);
			}
			else
			{
				food[5].setOrdered(false);
				food[5].setPrice(0);
				food[5].setTaxes(0, 0, 0);
			}
			
			if (quantityOfDish3.getSelectionModel().getSelectedIndex() >= 0 && quantityOfDish3.isVisible())
			{
			food[6].setName("Beef Steak");
			food[6].setPrice(80.00);
			food[6].setQuantity((quantityOfDish3.getSelectionModel().getSelectedIndex()+1));
			food[6].setTaxes(80.00, 0.15, (quantityOfDish3.getSelectionModel().getSelectedIndex()+1));
			food[6].setOrdered(true);
			}
			else
			{
				food[6].setOrdered(false);
				food[6].setPrice(0);
				food[6].setTaxes(0, 0, 0);
			}
			
			if (quantityOfDish4.getSelectionModel().getSelectedIndex() >= 0 && quantityOfDish4.isVisible())
			{
			food[7].setName("Pasta");
			food[7].setPrice(40.00);
			food[7].setQuantity((quantityOfDish4.getSelectionModel().getSelectedIndex()+1));
			food[7].setTaxes(40.00, 0.15, (quantityOfDish4.getSelectionModel().getSelectedIndex()+1));
			food[7].setOrdered(true);
			}
			
			else
			{
				food[7].setOrdered(false);
				food[7].setPrice(0);
				food[7].setTaxes(0, 0, 0);
			}
			
			if (quantityOfDish5.getSelectionModel().getSelectedIndex() >= 0 && quantityOfDish5.isVisible())
			{
			food[8].setName("Fried Chicken");
			food[8].setPrice(70.00);
			food[8].setQuantity((quantityOfDish5.getSelectionModel().getSelectedIndex()+1));
			food[8].setTaxes(70.00, 0.15, (quantityOfDish5.getSelectionModel().getSelectedIndex()+1));
			food[8].setOrdered(true);
			}
			else
			{
				food[8].setOrdered(false);
				food[8].setPrice(0);
				food[8].setTaxes(0, 0, 0);
			}
			
			if (quantityOfAppetizer1.getSelectionModel().getSelectedIndex() >= 0 && quantityOfAppetizer1.isVisible())
			{
			food[1].setName("Greek Salad");
			food[1].setPrice(35.00);
			food[1].setQuantity((quantityOfAppetizer1.getSelectionModel().getSelectedIndex()+1));
			food[1].setTaxes(35.00, 0.10, (quantityOfAppetizer1.getSelectionModel().getSelectedIndex()+1));
			food[1].setOrdered(true);
			}
			else
			{
				food[1].setOrdered(false);
				food[1].setPrice(0);
				food[1].setTaxes(0, 0, 0);
			}
			
			if (quantityOfAppetizer2.getSelectionModel().getSelectedIndex() >= 0 && quantityOfAppetizer2.isVisible())
			{
			food[2].setName("Fried Potatoes");
			food[2].setPrice(30.00);
			food[2].setQuantity((quantityOfAppetizer2.getSelectionModel().getSelectedIndex()+1));
			food[2].setTaxes(30.00, 0.10, (quantityOfAppetizer2.getSelectionModel().getSelectedIndex()+1));
			food[2].setOrdered(true);
			}
			else
			{
				food[2].setOrdered(false);
				food[2].setPrice(0);
				food[2].setTaxes(0, 0, 0);
			}
			
			if (quantityOfDessert1.getSelectionModel().getSelectedIndex() >= 0 && quantityOfDessert1.isVisible())
			{
			food[4].setName("Molten Cake");
			food[4].setPrice(60.00);
			food[4].setQuantity((quantityOfDessert1.getSelectionModel().getSelectedIndex()+1));
			food[4].setTaxes(60.00, 0.20, (quantityOfDessert1.getSelectionModel().getSelectedIndex()+1));
			food[4].setOrdered(true);
			}
			else
			{
				food[4].setOrdered(false);
				food[4].setPrice(0);
				food[4].setTaxes(0, 0, 0);
			}
			
			if (quantityOfDessert2.getSelectionModel().getSelectedIndex() >= 0 && quantityOfDessert2.isVisible())
			{
			food[3].setName("Apple Pie");
			food[3].setPrice(50.00);
			food[3].setQuantity((quantityOfDessert2.getSelectionModel().getSelectedIndex()+1));
			food[3].setTaxes(50.00, 0.20, (quantityOfDessert2.getSelectionModel().getSelectedIndex()+1));
			food[3].setOrdered(true);
			}
			else
			{
				food[3].setOrdered(false);
				food[3].setPrice(0);
				food[3].setTaxes(0, 0, 0);
			}
				
			for (int i=0 ; i<9 ; i++)
			{
				if(food[i].isOrdered() == false)
				{

					noOrder.setText("You have to order atleast 1 dish and choose quantity !");
				}
				else
				{
					noOrder.setText("");
				}
			}
			
			for (int i =0 ; i<9 ; i++)
			{
				if(food[i].isOrdered())
				{
					if	(	(quantityOfDish1.isVisible() && quantityOfDish1.getSelectionModel().getSelectedIndex() <0)
							||(quantityOfDish2.isVisible() && quantityOfDish2.getSelectionModel().getSelectedIndex() <0)
							||(quantityOfDish3.isVisible() && quantityOfDish3.getSelectionModel().getSelectedIndex() <0)
							||(quantityOfDish4.isVisible() && quantityOfDish4.getSelectionModel().getSelectedIndex() <0)
							||(quantityOfDish5.isVisible() && quantityOfDish5.getSelectionModel().getSelectedIndex() <0)
							||(quantityOfAppetizer1.isVisible() && quantityOfAppetizer1.getSelectionModel().getSelectedIndex() <0)
							||(quantityOfAppetizer2.isVisible() && quantityOfAppetizer2.getSelectionModel().getSelectedIndex() <0)
							||(quantityOfDessert1.isVisible() && quantityOfDessert1.getSelectionModel().getSelectedIndex() <0)
							||(quantityOfDessert2.isVisible() && quantityOfDessert2.getSelectionModel().getSelectedIndex() <0))
					{
						noOrder.setText("You have to choose quantity for your selected dishes !");
					}
					else
					{
						noOrder.setText("");
					}
				}
			}
			
			if(noOrder.getText()== "")
			{
				Optional<ButtonType> result2 = alertFinish.showAndWait();
				if (result2.isPresent() && result2.get() == ButtonType.OK) 
				{
					
				    customerDashboard2.close();
				    customerDashboard3.show();
				    
					for (int i=0 ; i<9 ; i++)
					{
						if(order1.getText()=="" && food[i].isOrdered())
						{
							order1.setText("- x"+df.format(food[i].getQuantity())+"\t"+food[i].getName());
							order1Price.setText("Price: "+food[i].getPrice()*food[i].getQuantity()+" L.E");
							food[i].setOrdered(false);
							Element dish = doc.createElement("orderedDish"); 
						    dish.appendChild(doc.createTextNode(""+food[i].getQuantity() + "x "+food[i].getName()));
						    dishes.appendChild(dish);
						}
						if(order2.getText()=="" && food[i].isOrdered())
						{
							order2.setText("- x"+df.format(food[i].getQuantity())+"\t"+food[i].getName());
							order2Price.setText("Price: "+food[i].getPrice()*food[i].getQuantity()+" L.E");					
							food[i].setOrdered(false);
							Element dish = doc.createElement("orderedDish"); 
						    dish.appendChild(doc.createTextNode(""+food[i].getQuantity() + "x "+food[i].getName()));
						    dishes.appendChild(dish);
						}
						if(order3.getText()=="" && food[i].isOrdered())
						{
							order3.setText("- x"+df.format(food[i].getQuantity())+"\t"+food[i].getName());
							order3Price.setText("Price: "+food[i].getPrice()*food[i].getQuantity()+" L.E");					
							food[i].setOrdered(false);
							Element dish = doc.createElement("orderedDish"); 
						    dish.appendChild(doc.createTextNode(""+food[i].getQuantity() + "x "+food[i].getName()));
						    dishes.appendChild(dish);
						}
						if(order4.getText()=="" && food[i].isOrdered())
						{
							order4.setText("- x"+df.format(food[i].getQuantity())+"\t"+food[i].getName());
							order4Price.setText("Price: "+food[i].getPrice()*food[i].getQuantity()+" L.E");					
							food[i].setOrdered(false);
							Element dish = doc.createElement("orderedDish"); 
						    dish.appendChild(doc.createTextNode(""+food[i].getQuantity() + "x "+food[i].getName()));
						    dishes.appendChild(dish);
						}
						if(order5.getText()=="" && food[i].isOrdered())
						{
							order5.setText("- x"+df.format(food[i].getQuantity())+"\t"+food[i].getName());
							order5Price.setText("Price: "+food[i].getPrice()*food[i].getQuantity()+" L.E");						
							food[i].setOrdered(false);
							Element dish = doc.createElement("orderedDish"); 
						    dish.appendChild(doc.createTextNode(""+food[i].getQuantity() + "x "+food[i].getName()));
						    dishes.appendChild(dish);
						}
						if(order6.getText()=="" && food[i].isOrdered())
						{
							order6.setText("- x"+df.format(food[i].getQuantity())+"\t"+food[i].getName());
							order6Price.setText("Price: "+food[i].getPrice()*food[i].getQuantity()+" L.E");						
							food[i].setOrdered(false);
							Element dish = doc.createElement("orderedDish"); 
						    dish.appendChild(doc.createTextNode(""+food[i].getQuantity() + "x "+food[i].getName()));
						    dishes.appendChild(dish);
						}
						if(order7.getText()=="" && food[i].isOrdered())
						{
							order7.setText("- x"+df.format(food[i].getQuantity())+"\t"+food[i].getName());
							order7Price.setText("Price: "+food[i].getPrice()*food[i].getQuantity()+" L.E");						
							food[i].setOrdered(false);
							Element dish = doc.createElement("orderedDish"); 
						    dish.appendChild(doc.createTextNode(""+food[i].getQuantity() + "x "+food[i].getName()));
						    dishes.appendChild(dish);
						}
						if(order8.getText()=="" && food[i].isOrdered())
						{
							order8.setText("- x"+df.format(food[i].getQuantity())+"\t"+food[i].getName());
							order8Price.setText("Price: "+food[i].getPrice()*food[i].getQuantity()+" L.E");						
							food[i].setOrdered(false);
							Element dish = doc.createElement("orderedDish"); 
						    dish.appendChild(doc.createTextNode(""+food[i].getQuantity() + "x "+food[i].getName()));
						    dishes.appendChild(dish);
						}
						if(order9.getText()=="" && food[i].isOrdered())
						{
							order9.setText("- x"+df.format(food[i].getQuantity())+"\t"+food[i].getName());
							order9Price.setText("Price: "+food[i].getPrice()*food[i].getQuantity()+" L.E");						
							food[i].setOrdered(false);
							Element dish = doc.createElement("orderedDish"); 
						    dish.appendChild(doc.createTextNode(""+food[i].getQuantity() + "x "+food[i].getName()));
						    dishes.appendChild(dish);
						}
					}
					
					
					for(int i=0 ; i<9 ; i++)
					{
					totalTaxes+= food[i].getTaxes();
					}
					
					for(int i=0 ; i<9 ; i++)
					{
					totalPrice+= food[i].getPrice()*food[i].getQuantity();
					}
					
					
					taxesLabel.setText("Additional Taxes= "+totalTaxes+" L.E");
					
					price=totalPrice+totalTaxes;
					
					if (P==0)
					{
						newPriceLabel.setText("");
						discountLbl.setText("");
						percentageSale.setText("");
						totalPriceLabel.setLayoutX(370);
						totalPriceLabel.setFont(new Font("Arial Bold", 23));
						totalPriceLabel.setTextFill(Color.web("#8B0000", 1)); //Dark Red color	
						totalPriceLabel.setText("Total Price: "+price+" L.E");
					    
					    Element paidAmount = doc.createElement("paidAmount");
					    paidAmount.appendChild(doc.createTextNode(String.valueOf(price)));
					    e.appendChild(paidAmount);
					}
					else
					{
						discountLbl.setText("_______________");
						totalPriceLabel.setLayoutX(260);
						totalPriceLabel.setFont(new Font("Arial Bold", 20));
						totalPriceLabel.setTextFill(Color.web("#000000", 1)); //Black color	
						totalPriceLabel.setText("Total Price: "+price+" L.E");	
						customer.setTotalPricePaid(price, 0.1);
						newPriceLabel.setText("Total Price with Discount: "+customer.getTotalPricePaid()+" L.E");
						
					    Element paidAmount = doc.createElement("paidAmount");
					    paidAmount.appendChild(doc.createTextNode(String.valueOf(customer.getTotalPricePaid())));
					    e.appendChild(paidAmount);
						
						if(totalPrice>300)
						{
							percentageSale.setText("25% Discount");
						}
						else
						{
							percentageSale.setText("10% Discount");
						}
					}
					
				    Element tableID = doc.createElement("Table");

					switch(R)
					{
					case 1: table[0].setReserved(true);
							tableLabel.setText("#Table number "+table[0].getNumber()+" with "+table[0].getSeats()+" seats (Non Smoking Area)");
						    tableID.appendChild(doc.createTextNode(String.valueOf(table[0].getNumber())));
						    e.appendChild(tableID);
					break;
					
					case 2: table[1].setReserved(true);
							tableLabel.setText("#Table number "+table[1].getNumber()+" with "+table[1].getSeats()+" seats (Smoking Area)");
						    tableID.appendChild(doc.createTextNode(String.valueOf(table[1].getNumber())));
						    e.appendChild(tableID);
					break;
					
					case 3: table[2].setReserved(true);
							tableLabel.setText("#Table number "+table[2].getNumber()+" with "+table[2].getSeats()+" seats (Non Smoking Area)");
						    tableID.appendChild(doc.createTextNode(String.valueOf(table[2].getNumber())));
						    e.appendChild(tableID);
					break;
					
					case 4: table[3].setReserved(true);
							tableLabel.setText("#Table number "+table[3].getNumber()+" with "+table[3].getSeats()+" seats (Smoking Area)");
						    tableID.appendChild(doc.createTextNode(String.valueOf(table[3].getNumber())));
						    e.appendChild(tableID);
					break;
					
					case 5: table[4].setReserved(true);
							tableLabel.setText("#Table number "+table[4].getNumber()+" with "+table[4].getSeats()+" seats (Non Smoking Area)");
						    tableID.appendChild(doc.createTextNode(String.valueOf(table[4].getNumber())));
						    e.appendChild(tableID);
					break;
					
					case 6: table[5].setReserved(true);
							tableLabel.setText("#Table number "+table[5].getNumber()+" with "+table[5].getSeats()+" seats (Smoking Area)");
						    tableID.appendChild(doc.createTextNode(String.valueOf(table[5].getNumber())));
						    e.appendChild(tableID);
					break;
					
					case 7: table[6].setReserved(true);
							tableLabel.setText("#Table number "+table[6].getNumber()+" with "+table[6].getSeats()+" seats (Non Smoking Area) [Family Table]");
						    tableID.appendChild(doc.createTextNode(String.valueOf(table[6].getNumber())));
						    e.appendChild(tableID);
					break;
					
					case 8: table[7].setReserved(true);
							tableLabel.setText("#Table number "+table[7].getNumber()+" with "+table[7].getSeats()+" seats (Non Smoking Area) [Family Table]");
						    tableID.appendChild(doc.createTextNode(String.valueOf(table[7].getNumber())));
						    e.appendChild(tableID);
					break;
						
					}
		}	
				}

	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        
	        StreamResult file = new StreamResult(new File("Data.xml"));
	        transformer.transform(source, file);
		}catch(Exception e) {System.out.println("Data can't be added to file !");};
		
		
		try
		{
		File fXmlFile2 = new File("Data.xml");
		DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
		Document doc2 = dBuilder2.parse(fXmlFile2);		
		doc2.getDocumentElement().normalize();

		
		NodeList customerList = doc2.getElementsByTagName("Customer"); //gets all elements by that name

		for (int i = 0; i < customerList.getLength(); i++) { //Loop to pass on all users

			Node customerNode = customerList.item(i); //components of each user
					
			Element element = (Element) customerNode;
			
			customerData[i].setName(element.getElementsByTagName("nameOfCustomer").item(0).getTextContent());
			customerData[i].setTableID(Integer.parseInt(element.getElementsByTagName("Table").item(0).getTextContent()));
			customerData[i].setPaidAmount(Double.parseDouble(element.getElementsByTagName("paidAmount").item(0).getTextContent()));
			customerData[i].setDishes(element.getElementsByTagName("dishesOrdered").item(0).getTextContent());

			
			System.out.println(customerData[0].getName());
			System.out.println(customerData[0].getTableID());
			System.out.println(customerData[0].getPaidAmount());
			System.out.println(customerData[0].getDishes());

		}
		
			
			manager.setTotalMoneyEarnedToday(customerData[0].getPaidAmount()+customerData[1].getPaidAmount()+customerData[2].getPaidAmount()
					+customerData[3].getPaidAmount()+customerData[4].getPaidAmount()+customerData[5].getPaidAmount()+customerData[6].getPaidAmount()
					+customerData[7].getPaidAmount());
			
			//MANAGER DATA
		
			if(customer1Name.getText()=="")
			{
				noReservations.setText("");
				customer1Name.setText("Customer #1\n\nName: "+customerData[0].getName());
				customer1Table.setText("Table Number: "+customerData[0].getTableID());
				customer1PricePaid.setText("Amount Paid: "+customerData[0].getPaidAmount()+" L.E");
				customer1Dishes.setText("Dishes Ordered: \n"+customerData[0].getDishes());
			}
			else if(customer2Name.getText()=="")
			{
				nextCustomer1.setVisible(true);
				previousCustomer1.setVisible(true);
				customer2Name.setText("Customer #2\n\nName: "+customerData[1].getName());
				customer2Table.setText("Table Number: "+customerData[1].getTableID());
				customer2PricePaid.setText("Amount Paid: "+customerData[1].getPaidAmount()+" L.E");
				customer2Dishes.setText("Dishes Ordered: \n"+customerData[1].getDishes());
			}
			else if(customer3Name.getText()=="")
			{
				nextCustomer2.setVisible(true);
				previousCustomer2.setVisible(true);
				customer3Name.setText("Customer #3\n\nName: "+customerData[2].getName());
				customer3Table.setText("Table Number: "+customerData[2].getTableID());
				customer3PricePaid.setText("Amount Paid: "+customerData[2].getPaidAmount()+" L.E");
				customer3Dishes.setText("Dishes Ordered: \n"+customerData[2].getDishes());
			}
			else if(customer4Name.getText()=="")
			{
				nextCustomer3.setVisible(true);
				previousCustomer3.setVisible(true);
				customer4Name.setText("Customer #4\n\nName: "+customerData[3].getName());
				customer4Table.setText("Table Number: "+customerData[3].getTableID());
				customer4PricePaid.setText("Amount Paid: "+customerData[3].getPaidAmount()+" L.E");
				customer4Dishes.setText("Dishes Ordered: \n"+customerData[3].getDishes());
			}
			else if(customer5Name.getText()=="")
			{
				nextCustomer4.setVisible(true);
				previousCustomer4.setVisible(true);
				customer5Name.setText("Customer #5\n\nName: "+customerData[4].getName());
				customer5Table.setText("Table Number: "+customerData[4].getTableID());
				customer5PricePaid.setText("Amount Paid: "+customerData[4].getPaidAmount()+" L.E");
				customer5Dishes.setText("Dishes Ordered: \n"+customerData[4].getDishes());
			}
			else if(customer6Name.getText()=="")
			{
				nextCustomer5.setVisible(true);
				previousCustomer5.setVisible(true);
				customer6Name.setText("Customer #6\n\nName: "+customerData[5].getName());
				customer6Table.setText("Table Number: "+customerData[5].getTableID());
				customer6PricePaid.setText("Amount Paid: "+customerData[5].getPaidAmount()+" L.E");
				customer6Dishes.setText("Dishes Ordered: \n"+customerData[5].getDishes());
			}
			else if(customer7Name.getText()=="")
			{
				nextCustomer6.setVisible(true);
				previousCustomer6.setVisible(true);
				customer7Name.setText("Customer #7\n\nName: "+customerData[6].getName());
				customer7Table.setText("Table Number: "+customerData[6].getTableID());
				customer7PricePaid.setText("Amount Paid: "+customerData[6].getPaidAmount()+" L.E");
				customer7Dishes.setText("Dishes Ordered: \n"+customerData[6].getDishes());
			}
			else if(customer8Name.getText()=="")
			{
				nextCustomer7.setVisible(true);
				previousCustomer7.setVisible(true);
				customer8Name.setText("Customer #8\n\nName: "+customerData[7].getName());
				customer8Table.setText("Table Number: "+customerData[7].getTableID());
				customer8PricePaid.setText("Amount Paid: "+customerData[7].getPaidAmount()+" L.E");
				customer8Dishes.setText("Dishes Ordered: \n"+customerData[7].getDishes());
			}
		
		
			if(noReservations.getText() == "")
			{
				totalMoneyOfTodayLabel.setText("Total money earned today: "+manager.getTotalMoneyEarnedToday()+" L.E");
				totalMoneyOfTodayLabel2.setText("Total money earned today: "+manager.getTotalMoneyEarnedToday()+" L.E");
				totalMoneyOfTodayLabel3.setText("Total money earned today: "+manager.getTotalMoneyEarnedToday()+" L.E");
				totalMoneyOfTodayLabel4.setText("Total money earned today: "+manager.getTotalMoneyEarnedToday()+" L.E");
				totalMoneyOfTodayLabel5.setText("Total money earned today: "+manager.getTotalMoneyEarnedToday()+" L.E");
				totalMoneyOfTodayLabel6.setText("Total money earned today: "+manager.getTotalMoneyEarnedToday()+" L.E");
				totalMoneyOfTodayLabel7.setText("Total money earned today: "+manager.getTotalMoneyEarnedToday()+" L.E");
				totalMoneyOfTodayLabel8.setText("Total money earned today: "+manager.getTotalMoneyEarnedToday()+" L.E");

			}
		
			
			
			//COOKER DATA
			
			if(customer1OrderTable.getText()=="")
			{
				noOrdersYet.setText("");
				customer1OrderTable.setText("Table Number: "+customerData[0].getTableID());
				customer1Order.setText("Dishes Ordered: \n"+customerData[0].getDishes());
			}
			else if(customer2OrderTable.getText()=="")
			{
				nextDishes1.setVisible(true);
				previousDishes1.setVisible(true);
				customer2OrderTable.setText("Table Number: "+customerData[1].getTableID());
				customer2Order.setText("Dishes Ordered: \n"+customerData[1].getDishes());
			}
			else if(customer3OrderTable.getText()=="")
			{
				nextDishes2.setVisible(true);
				previousDishes2.setVisible(true);
				customer3OrderTable.setText("Table Number: "+customerData[2].getTableID());
				customer3Order.setText("Dishes Ordered: \n"+customerData[2].getDishes());
			}
			else if(customer4OrderTable.getText()=="")
			{
				nextDishes3.setVisible(true);
				previousDishes3.setVisible(true);
				customer4OrderTable.setText("Table Number: "+customerData[3].getTableID());
				customer4Order.setText("Dishes Ordered: \n"+customerData[3].getDishes());
			}
			else if(customer5OrderTable.getText()=="")
			{
				nextDishes4.setVisible(true);
				previousDishes4.setVisible(true);
				customer5OrderTable.setText("Table Number: "+customerData[4].getTableID());
				customer5Order.setText("Dishes Ordered: \n"+customerData[4].getDishes());
			}
			else if(customer6OrderTable.getText()=="")
			{
				nextDishes5.setVisible(true);
				previousDishes5.setVisible(true);
				customer6OrderTable.setText("Table Number: "+customerData[5].getTableID());
				customer6Order.setText("Dishes Ordered: \n"+customerData[5].getDishes());
			}
			else if(customer7OrderTable.getText()=="")
			{
				nextDishes6.setVisible(true);
				previousDishes6.setVisible(true);
				customer7OrderTable.setText("Table Number: "+customerData[6].getTableID());
				customer7Order.setText("Dishes Ordered: \n"+customerData[6].getDishes());
			}
			else if(customer8OrderTable.getText()=="")
			{
				nextDishes7.setVisible(true);
				previousDishes7.setVisible(true);
				customer8OrderTable.setText("Table Number: "+customerData[7].getTableID());
				customer8Order.setText("Dishes Ordered: \n"+customerData[7].getDishes());
			}
			
			
			
			//WAITER DATA
			
			if(customer1Reservation.getText()=="")
			{
				noReservationsYet.setText("");
				customer1Reservation.setText("#1 Customer Name: "+customerData[0].getName());
				customer1ReservationTable.setText("Table number: "+customerData[0].getTableID());
			}
			else if(customer2Reservation.getText()=="")
			{
				customer2Reservation.setText("#2 Customer Name: "+customerData[1].getName());
				customer2ReservationTable.setText("Table number: "+customerData[1].getTableID());
			}
			else if(customer3Reservation.getText()=="")
			{
				customer3Reservation.setText("#3 Customer Name: "+customerData[2].getName());
				customer3ReservationTable.setText("Table number: "+customerData[2].getTableID());
			}
			else if(customer4Reservation.getText()=="")
			{
				customer4Reservation.setText("#4 Customer Name: "+customerData[3].getName());
				customer4ReservationTable.setText("Table number: "+customerData[3].getTableID());
			}
			else if(customer5Reservation.getText()=="")
			{
				customer5Reservation.setText("#5 Customer Name: "+customerData[4].getName());
				customer5ReservationTable.setText("Table number: "+customerData[4].getTableID());
			}
			else if(customer6Reservation.getText()=="")
			{
				customer6Reservation.setText("#6 Customer Name: "+customerData[5].getName());
				customer6ReservationTable.setText("Table number: "+customerData[5].getTableID());
			}
			else if(customer7Reservation.getText()=="")
			{
				customer7Reservation.setText("#7 Customer Name: "+customerData[6].getName());
				customer7ReservationTable.setText("Table number: "+customerData[6].getTableID());
			}
			else if(customer8Reservation.getText()=="")
			{
				customer8Reservation.setText("#8 Customer Name: "+customerData[7].getName());
				customer8ReservationTable.setText("Table number: "+customerData[7].getTableID());
			}
			
		
		}catch(Exception p) {System.out.println("Something went wrong in data file !");};
	    });
		

		
		logout1.setOnAction(e -> {
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) 
			{
    			numberOfSeats.setValue(null);
    			S=-1;
    			R=0;
    			smokeLbl.setText("");
    			checkDish1.setSelected(false);
    			checkDish2.setSelected(false);
    			checkDish3.setSelected(false);
    			checkDish4.setSelected(false);
    			checkDish5.setSelected(false);
    			checkAppetizer1.setSelected(false);
    			checkAppetizer2.setSelected(false);
    			checkDessert1.setSelected(false);
    			checkDessert2.setSelected(false);
				quantityOfDish1.setVisible(false);
				quantityOfDish2.setVisible(false);
				quantityOfDish3.setVisible(false);
				quantityOfDish4.setVisible(false);
				quantityOfDish5.setVisible(false);
				quantityOfAppetizer1.setVisible(false);
				quantityOfAppetizer2.setVisible(false);
				quantityOfDessert1.setVisible(false);
				quantityOfDessert2.setVisible(false);
				quantityOfDish1.setValue(null);
				quantityOfDish2.setValue(null);
				quantityOfDish3.setValue(null);
				quantityOfDish4.setValue(null);
				quantityOfDish5.setValue(null);
				quantityOfAppetizer1.setValue(null);
				quantityOfAppetizer2.setValue(null);
				quantityOfDessert1.setValue(null);
				quantityOfDessert2.setValue(null);
				tableLabel.setText("");
				taxesLabel.setText("");
				totalPriceLabel.setText("");
				totalTaxes=0;
				totalPrice=0;
				order1.setText("");
				order2.setText("");
				order3.setText("");
				order4.setText("");
				order5.setText("");
				order6.setText("");
				order7.setText("");
				order8.setText("");
				order9.setText("");
				order1Price.setText("");
				order2Price.setText("");
				order3Price.setText("");
				order4Price.setText("");
				order5Price.setText("");
				order6Price.setText("");
				order7Price.setText("");
				order8Price.setText("");
				order9Price.setText("");



				
			    customerDashboard.close();
			    customerDashboard2.close();
			    customerDashboard3.close();
			    loginStage.show();
			}
			
		});
		
		logout2.setOnAction(e -> {
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) 
			{
    			numberOfSeats.setValue(null);
    			S=-1;
    			R=0;
    			smokeLbl.setText("");
    			checkDish1.setSelected(false);
    			checkDish2.setSelected(false);
    			checkDish3.setSelected(false);
    			checkDish4.setSelected(false);
    			checkDish5.setSelected(false);
    			checkAppetizer1.setSelected(false);
    			checkAppetizer2.setSelected(false);
    			checkDessert1.setSelected(false);
    			checkDessert2.setSelected(false);
				quantityOfDish1.setVisible(false);
				quantityOfDish2.setVisible(false);
				quantityOfDish3.setVisible(false);
				quantityOfDish4.setVisible(false);
				quantityOfDish5.setVisible(false);
				quantityOfAppetizer1.setVisible(false);
				quantityOfAppetizer2.setVisible(false);
				quantityOfDessert1.setVisible(false);
				quantityOfDessert2.setVisible(false);
				quantityOfDish1.setValue(null);
				quantityOfDish2.setValue(null);
				quantityOfDish3.setValue(null);
				quantityOfDish4.setValue(null);
				quantityOfDish5.setValue(null);
				quantityOfAppetizer1.setValue(null);
				quantityOfAppetizer2.setValue(null);
				quantityOfDessert1.setValue(null);
				quantityOfDessert2.setValue(null);
				tableLabel.setText("");
				taxesLabel.setText("");
				totalPriceLabel.setText("");
				totalTaxes=0;
				totalPrice=0;
				order1.setText("");
				order2.setText("");
				order3.setText("");
				order4.setText("");
				order5.setText("");
				order6.setText("");
				order7.setText("");
				order8.setText("");
				order9.setText("");
				order1Price.setText("");
				order2Price.setText("");
				order3Price.setText("");
				order4Price.setText("");
				order5Price.setText("");
				order6Price.setText("");
				order7Price.setText("");
				order8Price.setText("");
				order9Price.setText("");



				
			    customerDashboard.close();
			    customerDashboard2.close();
			    customerDashboard3.close();
			    loginStage.show();
			}
			
		});
		
		exit.setOnAction(e -> {
			
			try
			{

			File xmlFile = new File("Data.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        
			NodeList nList = doc.getElementsByTagName("customersOrders"); //DELETING ORDERS UPON EXIT

				for (int i = 0; i < nList.getLength(); i++) {
				    Node node = nList.item(i);
				    node.getParentNode().removeChild(node); 
				    
			       StreamResult file = new StreamResult(new File("Data.xml"));
			        transformer.transform(source, file);
				}
				
			}catch(Exception efinal) {System.out.println("Error in Data File !");};
				
				
		System.exit(0);

		});
		
		
		root2.getChildren().addAll(logout1,greetings,reserveTableLabel,seatsNumber,numberOfSeats,noSeat,next,smokingBtn,nonSmokingBtn,smokeLbl);
	
		root3.getChildren().addAll(previous,orderFoodLabel,mainCourseLabel,Dish1,checkDish1,Dish2,checkDish2,Dish3,checkDish3,Dish4,checkDish4,
				Dish5,checkDish5,appetizersLabel,Appetizer1,checkAppetizer1,Appetizer2,checkAppetizer2,dessertsLabel,recommend,
				Dessert1,checkDessert1,Dessert2,checkDessert2,confirmOrder,quantityOfDish1,quantityOfDish2,quantityOfDish3,quantityOfDish4,
				quantityOfDish5,quantityOfAppetizer1,quantityOfAppetizer2,quantityOfDessert1,quantityOfDessert2,taxesNote,noOrder);
		
		root4.getChildren().addAll(logout2,receiptLabel,order1,order2,order3,order4,order5,order6,order7,order8,order9,
				order1Price,order2Price,order3Price,order4Price,order5Price,order6Price,order7Price,order8Price,order9Price,
				taxesLabel,totalPriceLabel,tableLabel,discountLbl,newPriceLabel,percentageSale);
	
		root5.getChildren().addAll(managerTitle,totalMoneyOfTodayLabel,customer1Name,customer1Table,customer1PricePaid,customer1Dishes,nextCustomer1,logoutManager,noReservations);

		root52.getChildren().addAll(customer2Name,customer2Table,customer2PricePaid,customer2Dishes,nextCustomer2,previousCustomer1,managerTitle2,totalMoneyOfTodayLabel2);
		root53.getChildren().addAll(customer3Name,customer3Table,customer3PricePaid,customer3Dishes,nextCustomer3,previousCustomer2,managerTitle3,totalMoneyOfTodayLabel3);
		root54.getChildren().addAll(customer4Name,customer4Table,customer4PricePaid,customer4Dishes,nextCustomer4,previousCustomer3,managerTitle4,totalMoneyOfTodayLabel4);
		root55.getChildren().addAll(customer5Name,customer5Table,customer5PricePaid,customer5Dishes,nextCustomer5,previousCustomer4,managerTitle5,totalMoneyOfTodayLabel5);
		root56.getChildren().addAll(customer6Name,customer6Table,customer6PricePaid,customer6Dishes,nextCustomer6,previousCustomer5,managerTitle6,totalMoneyOfTodayLabel6);
		root57.getChildren().addAll(customer7Name,customer7Table,customer7PricePaid,customer7Dishes,nextCustomer7,previousCustomer6,managerTitle7,totalMoneyOfTodayLabel7);
		root58.getChildren().addAll(customer8Name,customer8Table,customer8PricePaid,customer8Dishes,previousCustomer7,managerTitle8,totalMoneyOfTodayLabel8);

		root6.getChildren().addAll(cookerTitle,customer1OrderTable,customer1Order,nextDishes1,logoutCooker,noOrdersYet);
		root62.getChildren().addAll(cookerTitle2,customer2OrderTable,customer2Order,nextDishes2,previousDishes1);
		root63.getChildren().addAll(cookerTitle3,customer3OrderTable,customer3Order,nextDishes3,previousDishes2);
		root64.getChildren().addAll(cookerTitle4,customer4OrderTable,customer4Order,nextDishes4,previousDishes3);
		root65.getChildren().addAll(cookerTitle5,customer5OrderTable,customer5Order,nextDishes5,previousDishes4);
		root66.getChildren().addAll(cookerTitle6,customer6OrderTable,customer6Order,nextDishes6,previousDishes5);
		root67.getChildren().addAll(cookerTitle7,customer7OrderTable,customer7Order,nextDishes7,previousDishes6);
		root68.getChildren().addAll(cookerTitle8,customer8OrderTable,customer8Order,previousDishes7);

		root7.getChildren().addAll(waiterTitle,logoutWaiter,customer1Reservation,customer2Reservation,customer3Reservation,customer4Reservation,
				customer5Reservation,customer6Reservation,customer7Reservation,customer8Reservation,noReservationsYet,customer1ReservationTable,
				customer2ReservationTable,customer3ReservationTable,customer4ReservationTable,customer5ReservationTable,customer6ReservationTable,
				customer7ReservationTable,customer8ReservationTable);
	}
}
