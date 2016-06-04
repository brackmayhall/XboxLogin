package dbm.XboxLogin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import dbm.XboxLogin.authentication.AuthPayload;
import dbm.XboxLogin.authorization.AuthorizationPayload;
import dbm.XboxLogin.finalAuth.FinalAuth;
import dbm.XboxLogin.loginpayload.Payload;
import dbm.XboxLogin.token.Token;


public class XboxClient {
	
	private final String USER_AGENT = "Mozilla/5.0";
	private static final String FIRST_URL = "https://login.live.com/oauth20_authorize.srf?locale=en&redirect_uri=https://login.live.com/oauth20_desktop.srf&response_type=token&client_id=0000000048093EE3&scope=service::user.auth.xboxlive.com::MBI_SSL&display=touch";
	
	private boolean authenticated = false;
	
	private String username;
	private String password;

	private CookieManager cookieManager;
	private List<HttpCookie> cookies;
	
	private HttpsURLConnection conn;
	Gson gson;
	
	private String accessToken;
	private AuthPayload authPayload;
	private Token token;
	private AuthorizationPayload authorizationPayload;
	private FinalAuth finalAuth;

	public String getAuthorizationHeader(){
		if(this.authenticated) {
			String uhs = token.getDisplayClaims().getXui().get(0).getUhs();
			String authHeader = "XBL3.0 x="+uhs+";"+finalAuth.getToken();
			return authHeader;			
		}
		return "";
	}
	
	public String getUserXid(){
		if(this.authenticated) {
			return finalAuth.getDisplayClaims().getXui().get(0).getXid();
		}
		return "";
	}
	
	public boolean isAuthenticated() {
		return authenticated;
	}

	public XboxClient(String username, String password) {
		this.username = username;
		this.password = password;
		this.cookieManager = new CookieManager();
		this.cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(cookieManager);
		this.gson = new Gson();
	}

	public boolean login(){
		
		String page;
		try {
			page = this.getLoginPage(FIRST_URL);
			String loginPostUrl = this.getLoginPostUrl(page);
			Payload postParamsObject = this.getFormParamsObject(page);
			
			this.postLogin(loginPostUrl, postParamsObject);
			this.postAuthenticate();
			this.postAuthorize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	private void postLogin(String url, Payload postParamsObject) throws Exception {

	    BasicCookieStore cookieStore = new BasicCookieStore();
	    for(HttpCookie tempCookie : this.cookies) {
		    BasicClientCookie cookie = new BasicClientCookie(tempCookie.getName(), tempCookie.getValue());
		    cookie.setDomain(tempCookie.getDomain());
		    cookie.setPath(tempCookie.getPath());		    
		    cookieStore.addCookie(cookie);	    	
	    }
		
		HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
		HttpPost post = new HttpPost(url);
		
		// add header
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept", "*/*");
		post.setHeader("Connection", "keep-alive");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("login", postParamsObject.getLogin()));
		urlParameters.add(new BasicNameValuePair("passwd", postParamsObject.getPasswd()));
		urlParameters.add(new BasicNameValuePair("PPFT", postParamsObject.getPPFT()));
		urlParameters.add(new BasicNameValuePair("PPSX", postParamsObject.getPPSX()));
		urlParameters.add(new BasicNameValuePair("SI", postParamsObject.getSI()));
		urlParameters.add(new BasicNameValuePair("type", postParamsObject.getType()));
		urlParameters.add(new BasicNameValuePair("NewUser", postParamsObject.getNewUser()));
		urlParameters.add(new BasicNameValuePair("LoginOptions", postParamsObject.getNewUser()));
		urlParameters.add(new BasicNameValuePair("i3", postParamsObject.getI3()));
		urlParameters.add(new BasicNameValuePair("m1", postParamsObject.getM1()));
		urlParameters.add(new BasicNameValuePair("m2", postParamsObject.getM2()));
		urlParameters.add(new BasicNameValuePair("m3", postParamsObject.getM3()));
		urlParameters.add(new BasicNameValuePair("i12", postParamsObject.getI12()));
		urlParameters.add(new BasicNameValuePair("i17", postParamsObject.getI17()));
		urlParameters.add(new BasicNameValuePair("i18", postParamsObject.getI18()));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		// need to check for location
		Header[] headers = response.getHeaders("Location");

		if(headers[0] != null) {
			String locationHeader = headers[0].getValue();
			// then we have location data
			URL locationUrl = new URL(locationHeader);
			String ref = locationUrl.getRef();
			List<NameValuePair> params = URLEncodedUtils.parse(new URI("https://test.com?"+ref), "UTF-8");
			
			for(NameValuePair nvp : params) {
				if(nvp.getName().equals("access_token")){
					this.accessToken = nvp.getValue();
					break;
				}
			}			
		}
	}

	private void postAuthenticate() throws Exception {

		String url = "https://user.auth.xboxlive.com/user/authenticate";
		this.authPayload = new AuthPayload(this.accessToken);
		String authPayloadString = gson.toJson(authPayload);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		
		// add header
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept", "*/*");
		post.setHeader("Connection", "keep-alive");
		post.setHeader("Content-Type", "application/json");
		
		post.setEntity(new StringEntity(authPayloadString));

		HttpResponse response = client.execute(post);


		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		this.token = this.gson.fromJson(result.toString(), Token.class);
	}
	
	private void postAuthorize() throws Exception {

		String url = "https://xsts.auth.xboxlive.com/xsts/authorize";
		List<String> tempUserTokenList = new ArrayList<String>();
		tempUserTokenList.add(this.token.getToken());
		this.authorizationPayload = new AuthorizationPayload(tempUserTokenList);
		String authorizationPayloadString = gson.toJson(authorizationPayload);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		
		// add header
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Accept", "*/*");
		post.setHeader("Connection", "keep-alive");
		post.setHeader("Content-Type", "application/json");
		
		post.setEntity(new StringEntity(authorizationPayloadString));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		this.finalAuth = this.gson.fromJson(result.toString(), FinalAuth.class);
		
		// this needs to be better
		this.authenticated = true;
	}
	
	private String getLoginPage(String url) throws Exception {

		URL obj = new URL(url);
		conn = (HttpsURLConnection) obj.openConnection();

		// default is GET
		conn.setRequestMethod("GET");

		conn.setUseCaches(false);
		conn.setInstanceFollowRedirects(true);
		// act like a browser
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("Connection", "keep-alive");

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

        CookieStore cookieJar =  this.cookieManager.getCookieStore();
        this.cookies = cookieJar.getCookies();

		return response.toString();

	}

	public String getFormParams(String html) throws UnsupportedEncodingException {

		String pattern = "sFTTag:\'.*value=\"(.*)\"/>";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(html);

		String PPFT = "";
		if (m.find()) {
			if (m.group(1) != null) {
				PPFT = m.group(1);
			}
		} else {
			System.out.println("NO MATCH");
		}
		Payload pl = new Payload();
		pl.setLogin(this.username);
		pl.setPasswd(this.password);
		pl.setPPFT(PPFT);
		Gson gson = new Gson();
		return gson.toJson(pl);

	}

	public Payload getFormParamsObject(String html) throws UnsupportedEncodingException {

		String pattern = "sFTTag:\'.*value=\"(.*)\"/>";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(html);

		String PPFT = "";
		if (m.find()) {
			if (m.group(1) != null) {
				PPFT = m.group(1);
			}
		} else {
			System.out.println("NO MATCH");
		}
		Payload pl = new Payload();
		pl.setLogin(this.username);
		pl.setPasswd(this.password);
		pl.setPPFT(PPFT);
		return pl;
	}
	
	private String getLoginPostUrl(String html) {
		String pattern = "urlPost:\'([A-Za-z0-9:\\?_\\-\\.&/=]+)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(html);

		String loginPostUrl = "";
		if (m.find()) {
			if (m.group(1) != null) {
				loginPostUrl = m.group(1);
			}
		} else {
			System.out.println("NO MATCH");
		}
		return loginPostUrl;
	}
}