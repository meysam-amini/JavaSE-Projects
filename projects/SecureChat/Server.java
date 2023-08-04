package SecureChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
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
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Server extends JFrame {

	private final static JTextArea msgArea = new JTextArea();
	private final JTextField msgField = new JTextField();
	private static String str;
	private static DataInputStream msg;
	private static DataOutputStream dout;
	static Socket s = null;
	static ServerSocket ss = null;
	static DataInputStream din;
	private int firstRunFlag = 0;
	private static BigInteger secretKey;
	private static BigInteger sharedKey;
	private static BigInteger publicKey;

	private static JTextArea ciphermsgArea = new JTextArea();
	private JButton start = new JButton();
	private JTextField clientport = new JTextField();
	private JButton portLabel = new JButton("Port :");
	private static JRadioButton isUdp = new JRadioButton("UDP");
	private static JRadioButton isTcp = new JRadioButton("TCP");

	private static DatagramSocket socket;
	private static InetAddress ip = null;
	static int udpport = 0;
	static int port = 0;
	BigInteger key;

	private static BigInteger secretkey;
	private static BigInteger primeValue;

	private static int tcpFlag = 0;
	private static int udpFlag = 0;

	public Server() throws IOException {
		super("Chat App By Meysam Amini_Server");
		super.setSize(new Dimension(450, 350));
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.add(new JScrollPane(msgArea), BorderLayout.CENTER);
		super.setVisible(true);

		initUI();

	}

	public static void main(String[] args) throws Exception {

		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				javax.swing.UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
		new Server();

	}

	private void initUI() {
		this.setSize(new Dimension(550, 350));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout BaseLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(BaseLayout);

		c.fill = GridBagConstraints.HORIZONTAL;

		// port:
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.gridwidth = 1;
		this.add(portLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 4;
		c.gridwidth = 1;
		clientport.setText("9861");
		this.add(clientport, c);

		// udp or tcp:
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.5;
		c.gridwidth = 1;
		isUdp.setSelected(true);
		isUdp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				isTcp.setSelected(false);
				start.setText("Run Server");
				start.setBackground(Color.lightGray);
				try {
					closeTCP();
				} catch (InterruptedException | IOException e) {
					showMsg(e.getMessage());
				}

			}

		});
		this.add(isUdp, c);

		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 0.5;
		c.gridwidth = 1;
		isTcp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				isUdp.setSelected(false);
				start.setText("Run Server");
				start.setBackground(Color.lightGray);
				try {
					closeUDP();
				} catch (InterruptedException e) {
					showMsg(e.getMessage());
				}

			}
		});
		this.add(isTcp, c);

		// connect:
		start.setText("Run Server");
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 0.5;
		c.gridwidth = 1;
		start.setBackground(Color.lightGray);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (start.getText().matches("Run Server")) {
					start.setText("Server Started");
					start.setBackground(new Color(144, 238, 175));
					sharedKeyFlag = 0;
					try {
						setMyRSA();
					} catch (Exception e2) {
						 showMsg(e2.getMessage());
						//e2.printStackTrace();
					}
					if (isUdp.isSelected())
						try {
							startUdp();
						} catch (IOException e1) {
							 showMsg(e1.getMessage());
						//	e1.printStackTrace();

						}

					if (isTcp.isSelected())
						try {
							startTcp();
						} catch (IOException e1) {
							showMsg(e1.getMessage());
						}

				}

			}
		});
		this.add(start, c);

		JScrollPane msg = new JScrollPane(msgArea);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.BOTH;
		msgArea.setEditable(false);

		this.add(msg, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;
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
		});

		msg = new JScrollPane(ciphermsgArea);
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 5;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		ciphermsgArea.setEditable(false);
		this.add(msg, c);

		this.setVisible(true);

	}

	Thread tcpThread;

	protected void runTcp() {

		tcpThread = new Thread(new Runnable() {
			String encryptedInput = "", first4char = "";

			@Override
			public void run() {
				try {
					ss = new ServerSocket(Integer.parseInt(clientport.getText()));
					s = ss.accept();
					dout = new DataOutputStream(s.getOutputStream());
					din = new DataInputStream(s.getInputStream());
					while (true) {
						str = din.readUTF();
						checkInput(str);

					}

				} catch (Exception ex) {
					showMsg(ex.getMessage());
				}

			}
		});
	}

	Thread udpThread;
	byte receiveByte[];
	DatagramPacket receivePacket;

	private void runUdp() throws IOException {
		socket = new DatagramSocket(Integer.parseInt(clientport.getText()));

		udpThread = new Thread(new Runnable() {

			@Override
			public void run() {

				while (udpFlag == 1) {
					try {
						receiveByte = new byte[1024];
						receivePacket = new DatagramPacket(receiveByte, receiveByte.length);
						socket.receive(receivePacket);
						String receiveStr = new String(receivePacket.getData());
						receiveStr = receiveStr.trim();

						ip = receivePacket.getAddress();
						port = receivePacket.getPort();

						checkInput(receiveStr);
						receiveStr = "";
						// showMsg("\nHost: " + receivePacket.getAddress() +
						// "\nPort: " + receivePacket.getPort() + "\nLength: "
						// + receivePacket.getLength() + "\nData: " + new
						// String(receivePacket.getData()));
						//
					} catch (Exception ex) {
						 showMsg(ex.getMessage());
						//ex.printStackTrace();
					}
				}
			}

		});
	}

	private static BigInteger generator;
	private static BigInteger b = new BigInteger("123456789");
	private BigInteger a;

	private int sharedKeyFlag = 0;

	protected void setSecret(String encryptedInput) throws Exception {

		// encryptedInput = crypto.decrypt(encryptedInput, firstkey);

		encryptedInput = RSA.decrypt(encryptedInput, MY_RSA_PRIVATE_KEY);

		String a = "", primeValue = "", g = "";
		int i = 0;
		while (encryptedInput.charAt(i) != '?')
			a += encryptedInput.charAt(i++);
		i += 4;
		while (encryptedInput.charAt(i) != '?')
			primeValue += encryptedInput.charAt(i++);
		i += 4;
		for (int j = i; j < encryptedInput.length(); j++)
			g += encryptedInput.charAt(j);

		generator = new BigInteger(g);
		this.a = new BigInteger(a);
		Random R = new Random();
		b = new BigInteger(51, R);
		publicKey = generator.modPow(this.a, new BigInteger(primeValue));
		sharedKey = publicKey.modPow(b, new BigInteger(primeValue));
		sharedKeyFlag = 1;
		showMsg("sharedKey: " + sharedKey);

		/// String t = "?????" + crypto.encrypt(b.toString(), firstkey);
		String t = "?????" + RSA.encrypt(b.toString(), CLIENT_PUBLIC_KEY);
		// if (isTcp.isSelected()) {
		// dout.writeUTF(t);
		// }
		//
		// if (isUdp.isSelected()) {
		// String secret = t;
		// byte sendByte[] = secret.getBytes();
		// DatagramPacket packetSend = new DatagramPacket(sendByte,
		// sendByte.length, ip, port);
		// socket.send(packetSend);
		// }
		sendNormal(t);

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

			try {
				setSecret(encryptedInput);
			} catch (IOException e) {
				showMsg(e.getMessage());
			}
			first4char = "*";
		}

		if (first4char.compareTo("CP###") == 0) {
			encryptedInput = encryptedInput.substring(5, encryptedInput.length());

			setClientPublicKey(encryptedInput);
			first4char = "*";
		}

		else {
			if (sharedKeyFlag == 1 && first4char.compareTo("*") != 0)
				recieve(s);
			else if (s.compareTo("TCP CLIENT CONNECTED!") == 0)
				showMsg(s);
		}

	}

	private void sendRSAPublickKey() throws NumberFormatException, IOException {
		String s = Base64.getEncoder().encodeToString(MY_RSA_PUBLIC_KEY.getEncoded());
		showMsg("SERVER_PUBLIC_KEY: " + RSA.getPublicKeyFromString(s));
		sendNormal("SP###" + s);
		
	}

	private PublicKey CLIENT_PUBLIC_KEY;
	private void setClientPublicKey(String Input) throws NumberFormatException, IOException {

		CLIENT_PUBLIC_KEY = RSA.getPublicKeyFromString(Input);
		showMsg("CLIENT_PUBLIC_KEY: " + CLIENT_PUBLIC_KEY);
		sendRSAPublickKey();
	}

	private PublicKey MY_RSA_PUBLIC_KEY;
	private PrivateKey MY_RSA_PRIVATE_KEY;

	private void setMyRSA() throws Exception {

		KeyPair keyPair = RSA.generateKeyPair();
		MY_RSA_PUBLIC_KEY = keyPair.getPublic();
		MY_RSA_PRIVATE_KEY = keyPair.getPrivate();
	}

	private void startUdp() throws IOException {

		if (udpFlag == 0) {
			runUdp();
			udpThread.start();
			udpFlag = 1;

		}
	}

	private void startTcp() throws IOException {
		if (tcpFlag == 0) {
			runTcp();
			tcpThread.start();
			tcpFlag = 1;
		}
	}

	private void closeUDP() throws InterruptedException {
		if (udpFlag == 1) {
			socket.close();
			udpThread.interrupt();
			udpFlag = 0;
		}

	}

	private void closeTCP() throws InterruptedException, IOException {
		if (tcpFlag == 1) {
			ss.close();
			tcpThread.interrupt();
			tcpFlag = 0;
		}

	}

	

	private void sendNormal(String massage) throws NumberFormatException, IOException {
		if (isUdp.isSelected()) {
			byte sendByte[] = massage.getBytes();
			DatagramPacket packetSend = new DatagramPacket(sendByte, sendByte.length, ip, port);
			socket.send(packetSend);
		}
		if (isTcp.isSelected()) {
			dout.writeUTF(massage);
		}
	}

	private void send(String m) throws Exception {
		String s = "", hash = "", t;// = crypto.encrypt(m,
									// sharedKey.toString());
		hash=RSA.sign(crypto.calculateHMAC(m, sharedKey.toString()),MY_RSA_PRIVATE_KEY);
		t=crypto.encrypt(m, sharedKey.toString());
		s=t+"?@?@?@?@?"+hash;
		s=crypto.encrypt(s, sharedKey.toString());


		if (isTcp.isSelected()) {
			showMsg("ME TCP : " + m);
			showMsgCipher("ME TCP : " + s);
			// dout.writeUTF(s);
		}

		if (isUdp.isSelected()) {
			showMsg("ME UDP " + m);
			showMsgCipher("ME UDP : " + s);
			// byte sendByte[] = s.getBytes();
			// DatagramPacket packetSend = new DatagramPacket(sendByte,
			// sendByte.length, ip, port);
			// socket.send(packetSend);
		}
		sendNormal(s);

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
		if (RSA.verify(crypto.calculateHMAC(decryptedMassage, sharedKey.toString()), hash, CLIENT_PUBLIC_KEY)==false)
			throw new Exception("Massage Comming From Unknown Person!");

		if (isTcp.isSelected()) {
			showMsg("TCP CLIENT : " + decryptedMassage);
			showMsgCipher("TCP CLIENT : " + cipher);
		}

		if (isUdp.isSelected()) {
			showMsg("UDP CLIENT : " + decryptedMassage);
			showMsgCipher("UDP CLIENT : " + cipher);
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
