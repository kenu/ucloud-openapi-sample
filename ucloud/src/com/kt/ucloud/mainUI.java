package com.kt.ucloud;

import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.BasicManagedEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class mainUI {

	// api spec parser
	private apiParser specParser;
	private ArrayList<apiRoom> apiRooms;
	//api call manager
	private apicallMgr acm;
	private String current_api_key = "0f2b67bd1a7158a10ce6ee03f3c8e837";
	private String current_secret = "e90fe7e5b7456f64ac2ddee9a25418f3";
	private String current_api_id = "1000000181";
	
	private JFrame frmUcloudApiJava;
	// api key & secret text field 
	private JTextField apikey_textField;
	private JTextField secret_textField;
	
	// ucloud combobox 
	private JComboBox api_comboBox;
	
	// api parameter labels
	private JLabel i00;
	private JLabel i01;
	private JLabel i02;
	private JLabel i03;
	private JLabel i04;
	private JLabel i05;
	
	// api parameters text field
	private JTextField i00_textField;
	private JTextField i01_textField;
	private JTextField i02_textField;
	private JTextField i03_textField;
	private JTextField i04_textField;
	private JTextField i05_textField;
	
	// api call button
	private JButton btnApiCall;
	
	private ImageCanvas l_canvas;

	// result data
	TextArea result_textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUI window = new mainUI();
					window.frmUcloudApiJava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainUI() {
		specParser = new apiParser("KTApiSpec_v1.0.43.xml");
		apiRooms = specParser.getApiRooms();
		// ui initialize
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUcloudApiJava = new JFrame();
		frmUcloudApiJava.setTitle("uCloud API Sample Application for Java");
		frmUcloudApiJava.setBounds(100, 100, 821, 600);
		frmUcloudApiJava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUcloudApiJava.getContentPane().setLayout(null);
		
		JLabel lblApikey = new JLabel("api_key:");
		lblApikey.setBounds(12, 20, 57, 15);
		frmUcloudApiJava.getContentPane().add(lblApikey);
		
		JLabel lblSecret = new JLabel("secret:");
		lblSecret.setBounds(12, 55, 57, 15);
		frmUcloudApiJava.getContentPane().add(lblSecret);
		
		apikey_textField = new JTextField();
		apikey_textField.setBounds(108, 17, 422, 21);
		frmUcloudApiJava.getContentPane().add(apikey_textField);
		apikey_textField.setColumns(10);
		
		secret_textField = new JTextField();
		secret_textField.setColumns(10);
		secret_textField.setBounds(108, 52, 422, 21);
		frmUcloudApiJava.getContentPane().add(secret_textField);
		
		JLabel lblUcloudApi = new JLabel("ucloud api:");
		lblUcloudApi.setBounds(12, 92, 69, 15);
		frmUcloudApiJava.getContentPane().add(lblUcloudApi);
		
		
		
		i00 = new JLabel("input_01");
		i00.setBounds(12, 127, 94, 15);
		i00.setVisible(false);
		frmUcloudApiJava.getContentPane().add(i00);
		
		i01 = new JLabel("input_01");
		i01.setVisible(false);
		i01.setBounds(12, 162, 94, 15);
		frmUcloudApiJava.getContentPane().add(i01);
		
		i02 = new JLabel("input_01");
		i02.setVisible(false);
		i02.setBounds(12, 198, 94, 15);
		frmUcloudApiJava.getContentPane().add(i02);
		
		i03 = new JLabel("input_01");
		i03.setVisible(false);
		i03.setBounds(12, 234, 94, 15);
		frmUcloudApiJava.getContentPane().add(i03);
		
		i04 = new JLabel("input_01");
		i04.setVisible(false);
		i04.setBounds(12, 269, 94, 15);
		frmUcloudApiJava.getContentPane().add(i04);
		
		i05 = new JLabel("input_01");
		i05.setVisible(false);
		i05.setBounds(12, 305, 94, 15);
		frmUcloudApiJava.getContentPane().add(i05);
		
		i00_textField = new JTextField();
		i00_textField.setVisible(false);
		i00_textField.setColumns(10);
		i00_textField.setBounds(108, 124, 422, 21);
		frmUcloudApiJava.getContentPane().add(i00_textField);
		
		i01_textField = new JTextField();
		i01_textField.setVisible(false);
		i01_textField.setColumns(10);
		i01_textField.setBounds(108, 159, 422, 21);
		frmUcloudApiJava.getContentPane().add(i01_textField);
		
		i02_textField = new JTextField();
		i02_textField.setVisible(false);
		i02_textField.setColumns(10);
		i02_textField.setBounds(108, 195, 422, 21);
		frmUcloudApiJava.getContentPane().add(i02_textField);
		
		i03_textField = new JTextField();
		i03_textField.setVisible(false);
		i03_textField.setColumns(10);
		i03_textField.setBounds(108, 299, 422, 21);
		frmUcloudApiJava.getContentPane().add(i03_textField);
		
		i04_textField = new JTextField();
		i04_textField.setVisible(false);
		i04_textField.setColumns(10);
		i04_textField.setBounds(108, 263, 422, 21);
		frmUcloudApiJava.getContentPane().add(i04_textField);
		
		i05_textField = new JTextField();
		i05_textField.setVisible(false);
		i05_textField.setColumns(10);
		i05_textField.setBounds(108, 228, 422, 21);
		frmUcloudApiJava.getContentPane().add(i05_textField);
		
		btnApiCall = new JButton("API Call");
		btnApiCall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				apiCall();
			}
		});
		btnApiCall.setBounds(534, 88, 85, 23);
		frmUcloudApiJava.getContentPane().add(btnApiCall);
		
		JLabel lblUcloudApiCall = new JLabel("uCloud API Call Result:");
		lblUcloudApiCall.setBounds(12, 359, 185, 15);
		frmUcloudApiJava.getContentPane().add(lblUcloudApiCall);

		l_canvas = new ImageCanvas("./img/1.jpg");
		l_canvas.setBounds(643, 27, 150, 150);
		frmUcloudApiJava.getContentPane().add(l_canvas);
		
		JLabel lblUp = new JLabel("Local Image");
		lblUp.setBounds(651, 10, 114, 15);
		frmUcloudApiJava.getContentPane().add(lblUp);
		
		result_textArea = new TextArea();
		
		result_textArea.setBounds(12, 380, 781, 172);
		frmUcloudApiJava.getContentPane().add(result_textArea);
		
		api_comboBox = new JComboBox();
		api_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				apiMenuChange();
			}
		});
		api_comboBox.addItem("");
		for(int i=0; i < apiRooms.size() ; i++) {
			apiRoom ar = apiRooms.get(i);
			api_comboBox.addItem(ar.getApiName());
		}
		api_comboBox.addItem("gwFileGet");
		api_comboBox.addItem("gwFilePost");
		api_comboBox.setBounds(108, 89, 422, 21);
		frmUcloudApiJava.getContentPane().add(api_comboBox);
	}
	
	// api call button event handler
	private void apiCall() {
		
		String new_api_key = apikey_textField.getText();
		String new_secret = secret_textField.getText();
		
		if(new_api_key.equalsIgnoreCase(current_api_key) && new_secret.equalsIgnoreCase(current_secret) ) {
			acm = new apicallMgr(new_api_key, new_secret);
		} else { // key & secret 이 변한경우 api call manager instance를 새로 만듬
			System.out.println("handler initialized");
			acm = new apicallMgr(new_api_key, new_secret);
			current_api_key = new_api_key;
			current_secret = new_secret;
		}
		
		if(api_comboBox.getSelectedItem().toString().equalsIgnoreCase("gwFileGet")) {
			try {
				String result = fileDownload(i00_textField.getText(), i01_textField.getText());
				result_textArea.setText("");
				result_textArea.setText(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (api_comboBox.getSelectedItem().toString().equalsIgnoreCase("gwFilePost")) {
			try {
				String result = fileUpload(i00_textField.getText(), i01_textField.getText());
				result_textArea.setText("");
				result_textArea.setText(result);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 일반 API
			String api_id = current_api_id;
			HashMap<String, String> params = new HashMap<String,String>();
			if(i00_textField.isVisible()) {
				params.put(i00_textField.getName(), i00_textField.getText());
			}
			if(i01_textField.isVisible()) {
				params.put(i01_textField.getName(), i01_textField.getText());
			}
			if(i02_textField.isVisible()) {
				params.put(i02_textField.getName(), i02_textField.getText());
			}
			if(i03_textField.isVisible()) {
				params.put(i03_textField.getName(), i03_textField.getText());
			}
			if(i04_textField.isVisible()) {
				params.put(i04_textField.getName(), i04_textField.getText());
			}
			if(i05_textField.isVisible()) {
				params.put(i05_textField.getName(), i05_textField.getText());
			}
		
			if(acm != null) {
				HashMap<?, ?> result = acm.apiCall(api_id, params);
				
				result_textArea.setText("");
				if(result != null) {
					String result_arrange = result.toString();
					result_arrange = result_arrange.replaceAll(",", ",\n\r");
					result_textArea.setText(result_arrange);
				} else {
					result_textArea.setText("API Handler생성 중 문제가 발생하였습니다. API Key, Secret을 확인해주세요.");
				}
			} else {
				result_textArea.setText("api key와 secret을 입력해 주세요.");
			}
		}
		
	}
	
	private String fileUpload(String gw_url, String file_token) throws ClientProtocolException, IOException {
		
		FileInputStream fis = new FileInputStream ("./img/1.jpg");
		
		
		File file = new File ("./img/1.jpg"); 
		byte[] data = new byte[(int) file.length()]; 
		
	       
		try {
			fis.read( data );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/* 업로드 url 로 파일을 올리기 위한 http client 설정 */ 
		HttpClient httpClient = new DefaultHttpClient (); 
		/* 업로드 url 은 4.2.1.2 결과로 얻어짂 redirect_url 과 file_token 그리고 SDK 에서 조회하는 
		api_token 를 가지고 만들어 집니다. api_token 은 m_apiHandler. makeApiToken() 과 같이 
		요청(m_apiHandler 는 KTOpenApiHandler 의 인스턴스임) 
		*/
		/* 예시 : https://gate.ucloud.com/api/open/upload?api_token=NDJiMWU4NDlmMDk3ZWM3OGVmYmRhNThm
		mU4YTZjNzc7MTMzMTA4NzE5MTtyQkFPanlQcWpPUG05WlJFRmJxRVE4RXdVK289&file_token=fcf3f55
		-67fc-11e1-9046-001b21a91ea8 */ 
		String full_url = gw_url + "?api_token=" + acm.getApiToken() + "&file_token="+file_token;
		System.out.println(full_url);
		HttpPut putRequest = new HttpPut ( full_url ); 
		ByteArrayEntity bae = new ByteArrayEntity ( data ); 
		 
		putRequest.setEntity( bae ); 
		// 파일 업로드 
		HttpResponse response = httpClient.execute( putRequest ); 
		// 파일 업로드 결과 확인 
		//System.out.println(  "file upload result: " + response.getStatusLine().toString() );
		return response.getStatusLine().toString();
		
	}
	
	private String fileDownload(String gw_url, String file_token) throws IOException {
		// 다운로드 url 에서 파일을 받기 위한 http client 설정 
		HttpClient httpClient = new DefaultHttpClient (); 
		/* 다운로드 url 은 4.2.2.1 결과로 얻어짂 redirect_url 과 file_token 그리고 SDK 에서 조회하는 api_token 를 가지고 만들어 집니다. api_token 은 m_apiHandler. makeApiToken() 과 같이 
		요청(m_apiHandler 는 KTOpenApiHandler 의 인스턴스임) */ 
		// 다운로드URL 예시 
		/* https://gate.ucloud.com/api/open/download?api_token=NDJiMWU4NDlmMDk3ZWM3OGVmYmRhNTh
		mZmU4YTZjNzc7MTMzMTA5NDkxNjtJbm5jMDVueVhRTmwxd3RwazRiTmpyWkl5aGM9&file_token=f96
		d990a-680e-11e1-9046-001b21a91ea8 */ 
		
		String full_url = gw_url + "?api_token=" + acm.getApiToken() + "&file_token="+file_token;
		HttpGet getRequest = new HttpGet ( full_url ); 
		       
		HttpResponse response = httpClient.execute( getRequest ); 
		       
		if ( 200 == response.getStatusLine().getStatusCode() ) 
		{ 

			//Create file
		    OutputStream os = new FileOutputStream( "./img/download.file");
		    BasicManagedEntity entity = (BasicManagedEntity) response.getEntity(); 
			InputStream is = entity.getContent(); 
		    
		    byte[] buf = new byte[4096];
		    int read;
		    while ((read = is.read(buf)) != -1) {
		        os.write(buf, 0, read);
		    }
		    os.close();
		} 
		//System.out.println( "file download result: " + response.getStatusLine().toString() ); 
		return response.getStatusLine().toString();
	}
	
 	private void apiMenuChange() {
		//System.out.println( api_comboBox.getSelectedItem().toString() );
		System.out.println( api_comboBox.getSelectedItem().toString() );
		// api input label & textfield visible initialize
		i00.setVisible(false); i01.setVisible(false); i02.setVisible(false); i03.setVisible(false); i04.setVisible(false); i05.setVisible(false); 
		i00_textField.setVisible(false); i01_textField.setVisible(false); i02_textField.setVisible(false); 
		i03_textField.setVisible(false); i04_textField.setVisible(false); i05_textField.setVisible(false);
		
		// api input value initialize
		i00_textField.setText(""); i01_textField.setText(""); i02_textField.setText(""); 
		i03_textField.setText(""); i04_textField.setText(""); i05_textField.setText("");
		
		if(api_comboBox.getSelectedItem().toString().equalsIgnoreCase("gwFileGet")) {
			current_api_id = "gwFileGet";
			i00.setText("api_url"); i01.setText("file_token"); 
			i00_textField.setName("api_url"); i01_textField.setName("file_token"); 
			i00.setVisible(true);	i01.setVisible(true); 
			i00_textField.setVisible(true); i01_textField.setVisible(true); 
		} else if (api_comboBox.getSelectedItem().toString().equalsIgnoreCase("gwFilePost")) {
			current_api_id = "gwFilePost";
			i00.setText("api_url"); i01.setText("file_token");
			i00_textField.setName("api_url"); i01_textField.setName("file_token");
			i00.setVisible(true);	i01.setVisible(true);
			i00_textField.setVisible(true); i01_textField.setVisible(true);
		} else {
			for(int i=0; i < apiRooms.size(); i++) {
				apiRoom ar = apiRooms.get(i);
				if( ar.getApiName().equalsIgnoreCase(api_comboBox.getSelectedItem().toString()) ) {
					ArrayList<String> params = ar.getParams();
					current_api_id = ar.getApiID();
					switch(params.size()) {
						case 0:
							break;
						case 1:
							i00.setText(params.get(0)); 
							i00_textField.setName(params.get(0));
							i00.setVisible(true);
							i00_textField.setVisible(true);
							break;
						case 2:
							i00.setText(params.get(0));  i01.setText(params.get(1));
							i00_textField.setName(params.get(0)); i01_textField.setName(params.get(1));
							i00.setVisible(true);	i01.setVisible(true);
							i00_textField.setVisible(true); i01_textField.setVisible(true); 
							break;
						case 3:
							i00.setText(params.get(0));  i01.setText(params.get(1)); i02.setText(params.get(2));
							i00_textField.setName(params.get(0)); i01_textField.setName(params.get(1)); i02_textField.setName(params.get(2));
							i00.setVisible(true);	i01.setVisible(true);	i02.setVisible(true);
							i00_textField.setVisible(true); i01_textField.setVisible(true); i02_textField.setVisible(true); 
							break;
						case 4:
							i00.setText(params.get(0));  i01.setText(params.get(1)); i02.setText(params.get(2)); i03.setText(params.get(3));
							i00_textField.setName(params.get(0)); i01_textField.setName(params.get(1)); i02_textField.setName(params.get(2)); i03_textField.setName(params.get(3));
							i00.setVisible(true);	i01.setVisible(true);	i02.setVisible(true); i03.setVisible(true);
							i00_textField.setVisible(true); i01_textField.setVisible(true); i02_textField.setVisible(true); i03_textField.setVisible(true); 
							break;
						case 5:
							i00.setText(params.get(0));  i01.setText(params.get(1)); i02.setText(params.get(2)); i03.setText(params.get(3)); i04.setText(params.get(4));
							
							i00_textField.setName(params.get(0)); i01_textField.setName(params.get(1)); i02_textField.setName(params.get(2)); i03_textField.setName(params.get(3));
							i04_textField.setName(params.get(4));
							
							i00.setVisible(true);	i01.setVisible(true);	i02.setVisible(true); i03.setVisible(true); i04.setVisible(true);
							i00_textField.setVisible(true); i01_textField.setVisible(true); i02_textField.setVisible(true); i03_textField.setVisible(true); i04_textField.setVisible(true);
							break;	
						case 6:
							i00.setText(params.get(0));  i01.setText(params.get(1)); i02.setText(params.get(2)); i03.setText(params.get(3)); i04.setText(params.get(4));
							i05.setText(params.get(5));
							i00_textField.setName(params.get(0)); i01_textField.setName(params.get(1)); i02_textField.setName(params.get(2)); i03_textField.setName(params.get(3));
							i04_textField.setName(params.get(4)); i05_textField.setName(params.get(5));
							
							i00.setVisible(true);	i01.setVisible(true);	i02.setVisible(true); i03.setVisible(true); i04.setVisible(true); i05.setVisible(true);
							i00_textField.setVisible(true); i01_textField.setVisible(true); i02_textField.setVisible(true); i03_textField.setVisible(true); i04_textField.setVisible(true);
							i05_textField.setVisible(true);
							break;
						default:
							// 입력 값이 6개 이상인 경우 (input field를 만들어 두지 않아 이건 패스)
							System.out.println("현재 생성된 input field는 6개까지입니다.");
							break;
					}
					break;
				}
			}
		}
		


	}
}
