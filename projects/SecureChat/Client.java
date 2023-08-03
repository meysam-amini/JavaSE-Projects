package SecureChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class Client extends JFrame {

	private static JTextArea msgArea = new JTextArea();
	private static JTextArea ciphermsgArea = new JTextArea();
	private JTextField msgField = new JTextField();
	private JButton connect = new JButton();
	private JTextField address = new JTextField();
	private JTextField serverport = new JTextField();
	private JButton addressLabel = new JButton("Address:");
	private JButton portLabel = new JButton("Port :");
	private static JRadioButton isUdp = new JRadioButton("UDP");
	private static JRadioButton isTcp = new JRadioButton("TCP");

	private static DatagramSocket socket;
	private static InetAddress ip = null;
	static int udpport = 0;

	private static String str;
	private static DataInputStream msg;
	private static DataOutputStream dout;
	static Socket s;
	static ServerSocket ss;
	static DataInputStream din;

	private static BigInteger secretKey;
	private static BigInteger sharedKey;
	private static BigInteger publicKey;

	private static Random randomGenerator;
	private static BigInteger primeValue;
	private static BigInteger generator;
	private static BigInteger a = new BigInteger("566565656");
	
	private BigInteger b;
	private int sharedKeyFlag = -1;
	private int connectedFlag=0;

	public Client() throws Exception {
		super("Chat App By Meysam Amini_CLient");
		initUI();


	}

	public static void main(String[] args) throws Exception {

		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				javax.swing.UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}

		new Client();

	}

	private void initUI() {
		this.setSize(new Dimension(550, 350));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout BaseLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(BaseLayout);

		c.fill = GridBagConstraints.HORIZONTAL;

		// address :
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.gridwidth = 1;
		address.setText("");
		this.add(addressLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 4;
		c.gridwidth = 1;
		this.add(address, c);

		// port:
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.5;
		c.gridwidth = 1;
		this.add(portLabel, c);
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 4;
		c.gridwidth = 1;
		serverport.setText("9861");
		this.add(serverport, c);

		// udp or tcp:
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 0.5;
		c.gridwidth = 1;
		isUdp.setSelected(true);
		isUdp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				isTcp.setSelected(false);
				if (!isTcp.isSelected()) {
					connect.setText("Connect");
					connect.setBackground(Color.lightGray);
					try {
						closeTCP();
					} catch (InterruptedException | IOException e) {
						showMsg(e.getMessage());
					}
				}
				isUdp.setSelected(true);
			}

		});
		this.add(isUdp, c);

		c.gridx = 5;
		c.gridy = 0;
		c.weightx = 0.5;
		c.gridwidth = 1;
		isTcp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isUdp.setSelected(false);
				isTcp.setSelected(true);
				connect.setText("Connect");
				connect.setBackground(Color.lightGray);
				try {
					closeUDP();
				} catch (InterruptedException e) {
					showMsg(e.getMessage());
				}

			}
		});
		this.add(isTcp, c);

		// connect:
		connect.setText("Connect");
		c.gridx = 6;
		c.gridy = 0;
		c.weightx = 1;
		c.gridwidth = 1;
		connect.setBackground(Color.lightGray);
		connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (connect.getText().matches("Connect")) {
					

					sharedKeyFlag=0;
					connectedFlag=0;
					try {
						setMyRSA();
					} catch (Exception e2) {
						showMsg(e2.getMessage());
						//e2.printStackTrace();

					}

					if (isUdp.isSelected())
						try {
							startUdp();
							connectedFlag=1;
							setConnectButton();
						} catch (Exception ex) {
							showMsg(ex.getMessage());
							//ex.printStackTrace();

						}

					if (isTcp.isSelected())
						try {
							startTcp();

						} catch (IOException e1) {
							showMsg(e1.getMessage());
							//e1.printStackTrace();

						}
				}

			}
		});
		this.add(connect, c);

		JScrollPane msg = new JScrollPane(msgArea);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 7;
		c.fill = GridBagConstraints.BOTH;
		msgArea.setEditable(false);

		this.add(msg, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 7;
		c.weighty = 0.01;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(msgField, c);
		msgField.addActionListener((ActionEvent evt) -> {

			try {
				send(evt.getActionCommand());
			} catch (Exception e1) {
				showMsg(e1.getMessage());
			}
		}

		);

		msg = new JScrollPane(ciphermsgArea);
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 7;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		ciphermsgArea.setEditable(false);
		this.add(msg, c);

		this.setVisible(true);

	}

	
	private void sendRSAPublickKey() throws NumberFormatException, IOException{
		String s=Base64.getEncoder().encodeToString(MY_RSA_PUBLIC_KEY.getEncoded());
		showMsg("CLIENT_PUBLIC_KEY: " + RSA.getPublicKeyFromString(s));
		sendNormal("CP###"+s);
		
	}
	
	private void sendAESKes() throws Exception{
		

		initKey();
		Random R = new Random();
		a = new BigInteger(51, R);
		sharedKeyFlag = 0;
		
		String massage =  a + "????" + primeValue + "????" + generator;
		//massage="????"+crypto.encrypt(massage, firstkey);
		
		massage="?????"+ RSA.encrypt(massage,SERVER_PUBLIC_KEY);

		sendNormal(massage);

	
	}
	

	private void sendSecrets() throws Exception {
		
    sendRSAPublickKey();

	}
	
	

	private void startUdp() throws Exception {

		if (udpFlag == 0) {
			runUdp();
			udpThread.start();
			udpFlag = 1;

		}
		sendSecrets();

	}

	private static int udpFlag = 0;
	private static int tcpFlag = 0;

	private void startTcp() throws IOException {
		if (tcpFlag == 0) {
			runTcp();
			tcpThread.start();
			
		}
	}

	private void closeUDP() throws InterruptedException {
		if (udpFlag == 1&&sharedKeyFlag==1) {
			socket.close();
			udpThread.interrupt();
			udpFlag = 0;
		}

	}

	private void closeTCP() throws InterruptedException, IOException {
		if (tcpFlag == 1&&sharedKeyFlag==1) {
			s.close();
			tcpThread.interrupt();
			tcpFlag = 0;
		}

	}
	byte receiveByte[];
	DatagramPacket receivePacket;
	private void runUdp() throws Exception {

		ip=InetAddress.getByName(address.getText());
		socket = new DatagramSocket(1111);
		
		udpThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				 while(udpFlag==1){
				try {
					 receiveByte = new byte[1024];
					 receivePacket = new DatagramPacket(receiveByte, receiveByte.length);

					socket.receive(receivePacket);
					String receiveStr = new String(receivePacket.getData());
					receiveStr = receiveStr.trim();
					checkInput(receiveStr);

				} catch (Exception ex) {
					showMsg(ex.getMessage());
				//	ex.printStackTrace();
				}
			}
			}
		});

	}

	Thread tcpThread;
	Thread udpThread;

	private void runTcp() throws IOException {
		tcpThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					s = new Socket(InetAddress.getByName(address.getText()), Integer.parseInt(serverport.getText()));
					
					if (s.isConnected()) {
						showMsg("Connected to server");
						dout = new DataOutputStream(s.getOutputStream());
						str = "TCP CLIENT CONNECTED!";
						dout.writeUTF(str);
						din = new DataInputStream(s.getInputStream());
						connectedFlag=1;
						setConnectButton();
						tcpFlag = 1;
					}
					else{
						connectedFlag=0;
						setConnectButton();
					}
					
					try {
						sendSecrets();
					} catch (Exception e1) {
						showMsg(e1.getMessage());
					//	e1.printStackTrace();
					}

					while (true) {
						str = din.readUTF();
						checkInput(str);
					}

				} catch (Exception ex) {
					showMsg(ex.getMessage());
					//ex.printStackTrace();
				}

			}
		});
	}

	String encryptedInput = "", first4char = "";

	private void checkInput(String s) throws Exception {
		encryptedInput = s;
		first4char = "";
		if (encryptedInput.length() > 5)
			first4char = encryptedInput.substring(0, 5);
		else
			first4char = "";
		if (first4char.compareTo("?????") == 0) {
			encryptedInput = encryptedInput.substring(5, encryptedInput.length());

			setSecret(encryptedInput);
			first4char="*";
		}
		if (first4char.compareTo("SP###") == 0) {
			encryptedInput = encryptedInput.substring(5, encryptedInput.length());

			setServerPublicKey(encryptedInput);
			first4char="*";
		}
		
		else {
			if (sharedKeyFlag == 1&&first4char.compareTo("*")!=0)
				recieve(s);
		}

	}

	private PublicKey MY_RSA_PUBLIC_KEY;
	private PrivateKey MY_RSA_PRIVATE_KEY;
	
	
	private void setMyRSA() throws Exception{
		KeyPair keyPair= RSA.generateKeyPair();
		MY_RSA_PUBLIC_KEY=keyPair.getPublic();
		MY_RSA_PRIVATE_KEY=keyPair.getPrivate();
	}


	private PublicKey SERVER_PUBLIC_KEY;
	private int serverPublicKeyRecieved=0;
	private void setServerPublicKey(String Input) throws Exception {
	
		SERVER_PUBLIC_KEY= RSA.getPublicKeyFromString(Input);
		if(SERVER_PUBLIC_KEY!=null){
			serverPublicKeyRecieved=1;
			showMsg("SERVER_PUBLIC_KEY: "+SERVER_PUBLIC_KEY);
			sendAESKes();
			}
		else{
			showMsg("SERVER_PUBLIC_KEY is null!");
		}
	}
	
	

	private void setSecret(String encryptedInput) throws Exception {
		//encryptedInput=crypto.decrypt(encryptedInput, firstkey);
		encryptedInput= RSA.decrypt(encryptedInput, MY_RSA_PRIVATE_KEY);
		this.b = new BigInteger(encryptedInput);
		publicKey = generator.modPow(this.b, primeValue);
		sharedKey = publicKey.modPow(a, primeValue);
		sharedKeyFlag = 1;
	
		showMsg("sharedKey: " + sharedKey);

	}

	private static void initKey() throws Exception {

		myDH dh = new myDH();
		primeValue = dh.prime;
		generator = dh.g;

	}
	

	private void setConnectButton() {
		
		if(connectedFlag==1){
			connect.setText("Connected");
			connect.setBackground(new Color(144, 238, 175));
		}
		
	}

	
	
	private void sendNormal(String massage) throws NumberFormatException, IOException{
		if (isUdp.isSelected()) {
			byte sendByte[] = massage.getBytes();
			DatagramPacket packetSend = new DatagramPacket(sendByte, sendByte.length,InetAddress.getByName(address.getText()),Integer.parseInt(serverport.getText()));
			socket.send(packetSend);
		}
		if (isTcp.isSelected()) {
			dout.writeUTF(massage);
		}
	}
	
	
	private void send(String m) throws Exception {
		String s="",hash ="",t;//= crypto.encrypt(m, sharedKey.toString());
		hash= RSA.sign(crypto.calculateHMAC(m, sharedKey.toString()),MY_RSA_PRIVATE_KEY);
		t= crypto.encrypt(m, sharedKey.toString());

		s=t+"?@?@?@?@?"+hash;
		s= crypto.encrypt(s, sharedKey.toString());
		
		if (isTcp.isSelected()) {
			showMsg("ME TCP " + m);
			showMsgCipher("ME TCP: " + s);
			dout.writeUTF(s);
		}

		if (isUdp.isSelected()) {
			showMsg("ME UDP : " + m);
			showMsgCipher("ME UDP : " + s);
			byte sendByte[] = s.getBytes();
			DatagramPacket packetSend = new DatagramPacket(sendByte, sendByte.length,InetAddress.getByName(address.getText()),Integer.parseInt(serverport.getText()));
			socket.send(packetSend);
		}

	}

	private void recieve(String m) throws Exception {
		String massagePlusCipher = "", cipher = "", 
		encryptedMassage = "", decryptedMassage, hash = "";
		int i = 0;
		massagePlusCipher = crypto.decrypt(m, sharedKey.toString());

		cipher = m;
		i = massagePlusCipher.indexOf("?@?@?@?@?");
		encryptedMassage = massagePlusCipher.substring(0, i);
		hash = massagePlusCipher.substring(i + 9, massagePlusCipher.length());

		decryptedMassage = crypto.decrypt(encryptedMassage, sharedKey.toString());
		if (RSA.verify(crypto.calculateHMAC(decryptedMassage, sharedKey.toString()), hash, SERVER_PUBLIC_KEY)==false)
			throw new Exception("Massage Comming From Unknown Person!");

		if (isTcp.isSelected()) {
			showMsg("TCP SERVER : " + decryptedMassage);
			showMsgCipher("TCP SERVER : " + cipher);
		}

		if (isUdp.isSelected()) {
			showMsg("UDP SERVER : " + decryptedMassage);
			showMsgCipher("UDP SERVER : " + cipher);
		}

	}

	public static void showMsg(final String msg) {
		SwingUtilities.invokeLater(() -> {
			msgArea.append("\n" + msg);
		});
	}

	private void showMsgCipher(String string) {
		SwingUtilities.invokeLater(() -> {
			ciphermsgArea.append("\n" + string);
		});

	}
}
