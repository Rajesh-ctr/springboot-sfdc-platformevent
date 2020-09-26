package com.github.ggan.springboot.sfdcplatformevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.salesforce.emp.connector.BayeuxParameters;
import com.salesforce.emp.connector.DelegatingBayeuxParameters;

@SuppressWarnings("deprecation")
@Component
public class SalesforceAccessTokenProvider {


	private static Logger logger = LoggerFactory.getLogger(SalesforceAccessTokenProvider.class);

	public BayeuxParameters updateBayeuxParametersWithSalesforceAccessToken(String sfdcHost, String username,
			String password, String consumerKey, String secret, BayeuxParameters parameters) {
		return new DelegatingBayeuxParameters(parameters) {
			@SuppressWarnings("unchecked")
			@Override
			public String bearerToken() {
				String token = "";
				try {
					/*
					 * String url = sfdcHost + "/services/oauth2/token"; HttpHeaders headers = new
					 * HttpHeaders(); headers.add("Content-Type",
					 * "application/x-www-form-urlencoded"); String tokenRequest =
					 * "grant_type=password&client_id=" + consumerKey + "&client_secret=" + secret +
					 * "&username=" + username + "&password=" + password; HttpEntity<String> request
					 * = new HttpEntity<>(tokenRequest, headers); ResponseEntity<String> response =
					 * restTemplate.postForEntity(url, request, String.class); String responseText =
					 * response.getBody();
					 * 
					 * @SuppressWarnings("unchecked") Map<String, String> jwtMap =
					 * gson.fromJson(responseText, Map.class); token = jwtMap.get("access_token");
					 */
					
					
					
					    DefaultHttpClient client = new DefaultHttpClient();
					    HttpParams params = client.getParams();
					    HttpClientParams.setCookiePolicy(params, CookiePolicy.RFC_2109);
					    params.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 30000);	
					    
					    
					 // Set the SID.
					    //System.out.println("Logging in as " + username + " in environment " + sfdcHost);
					    logger.debug("Logging in as " + username + " in environment " + sfdcHost);
					    String baseUrl = sfdcHost + "/services/oauth2/token";
					    // Send a post request to the OAuth URL.
					    HttpPost oauthPost = new HttpPost(baseUrl);
					    // The request body must contain these 5 values.
					    List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
					    parametersBody.add(new BasicNameValuePair("grant_type", "password"));
					    parametersBody.add(new BasicNameValuePair("username", username));
					    parametersBody.add(new BasicNameValuePair("password", password));
					    parametersBody.add(new BasicNameValuePair("client_id", consumerKey));
					    parametersBody.add(new BasicNameValuePair("client_secret", secret));
					    oauthPost.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));
					    
					    
					 // Execute the request.
					    //System.out.println("POST " + baseUrl + "...\n");
					    logger.debug("POST " + baseUrl + "...\n");
					    HttpResponse response = client.execute(oauthPost);
					    int code = response.getStatusLine().getStatusCode();
					    @SuppressWarnings("unchecked")
						Map<String, String> oauthLoginResponse = (Map<String, String>)
					    JSON.parse(EntityUtils.toString(response.getEntity()));
					    //System.out.println("OAuth login response");
					    logger.debug("###### OAuth login response ######");
					    for (Map.Entry<String, String> entry : oauthLoginResponse.entrySet()) 
					    {
					        //System.out.println(String.format("  %s = %s", entry.getKey(), entry.getValue()));
					        logger.debug(String.format("  %s = %s", entry.getKey(), entry.getValue()));
					    }
					    //System.out.println("");
					 
					
					    
					 // Get user info.
					    String userIdEndpoint = oauthLoginResponse.get("id");
					    token = oauthLoginResponse.get("access_token");
					    List<BasicNameValuePair> qsList = new ArrayList<BasicNameValuePair>();
					    qsList.add(new BasicNameValuePair("oauth_token", token));
					    String queryString = URLEncodedUtils.format(qsList, HTTP.UTF_8);
					    HttpGet userInfoRequest = new HttpGet(userIdEndpoint + "?" + queryString);
					    HttpResponse userInfoResponse = client.execute(userInfoRequest);
					    Map<String, Object> userInfo = (Map<String, Object>)
					    JSON.parse(EntityUtils.toString(userInfoResponse.getEntity()));
					    //System.out.println("User info response");
					    logger.debug("##### User info response ######");
					    for (Map.Entry<String, Object> entry : userInfo.entrySet()) 
					    {
					        //System.out.println(String.format("  %s = %s", entry.getKey(), entry.getValue()));
					        logger.debug(String.format("  %s = %s", entry.getKey(), entry.getValue()));
					    }
					    //System.out.println("");
					    
					    
					    // Use the user info in interesting ways.
					    //System.out.println("Username is " + userInfo.get("username"));
					    logger.debug("Username is " + userInfo.get("username"));
					    //System.out.println("User's email is " + userInfo.get("email"));
					    logger.debug("User's email is " + userInfo.get("email"));
					    Map<String, String> urls = (Map<String, String>)userInfo.get("urls");
					    //System.out.println("REST API url is " + urls.get("rest").replace("{version}", "50.0"));
					    logger.debug("REST API url is " + urls.get("rest").replace("{version}", "50.0"));
					
				} catch (Exception e) {
					logger.error("Failed to get salesforce access token SalesforceAccessTokenProvider: updateBayeuxParametersWithSalesforceAccessToken ", e);
					//System.exit(1);
				}
				return token;
			}
		};
	}

}
