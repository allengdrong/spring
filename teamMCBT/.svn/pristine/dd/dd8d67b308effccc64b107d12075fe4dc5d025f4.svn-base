//package com.mcbt.member.controller;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Controller
//public class KakaoController {
//	private final static String K_CLIENT_ID = "dfcf6d62ff8877763ffc3f3de04066b6";
//	private final static String K_REDIRECT_URI ="http://localhost/member/login.do";
//
//	public static String getAuthorizationUrl(HttpSession session) {
//	String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?" + "client_id=" + K_CLIENT_ID + "&redirect_uri="
//			+ K_REDIRECT_URI + "&response_type=code"; 
//			return kakaoUrl;
//			}
//	
//			public static JsonNode getAccessToken(String autorize_code) {
//				final String RequestUrl = "https://kauth.kakao.com/oauth/token";
//				final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
//				postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
//				postParams.add(new BasicNameValuePair("client_id", "dfcf6d62ff8877763ffc3f3de04066b6")); // REST API KEY
//				postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost/member/login.do"));//리다이렉트URI
//			    postParams.add(new BasicNameValuePair("code", autorize_code));
//				final HttpClient client = HttpClientBuilder.create().build();
//				final HttpPost post = new HttpPost(RequestUrl);
//				JsonNode returnNode = null;
//				try { 
//					post.setEntity(new UrlEncodedFormEntity(postParams));
//				final HttpResponse response = client.execute(post);
//				// JSON 형태 반환값 처리 
//				ObjectMapper mapper = new ObjectMapper(); 
//				returnNode = mapper.readTree(response.getEntity().getContent());
//			
//			} catch (UnsupportedEncodingException e) { 
//				e.printStackTrace(); 
//				} catch (ClientProtocolException e) { 
//					e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace(); 
//						} finally { 
//							// clear resources
//							} return returnNode; 
//							}
//			public static JsonNode getKakaoUserInfo(JsonNode accessToken) { 
//				final String RequestUrl = "http://localhost/member/login.do"; 
//				final HttpClient client = HttpClientBuilder.create().build();
//				final HttpPost post = new HttpPost(RequestUrl); 
//				// add header 
//				post.addHeader("Authorization", "Bearer " + accessToken);
//				JsonNode returnNode = null;
//				try { 
//					final HttpResponse response = client.execute(post);
//					// JSON 형태 반환값 처리 
//					ObjectMapper mapper = new ObjectMapper(); 
//					returnNode = mapper.readTree(response.getEntity().getContent());
//					} catch (ClientProtocolException e) {
//						e.printStackTrace();
//						} catch (IOException e) { 
//							e.printStackTrace(); 
//							} finally { 
//								// clear resources 
//								} return returnNode; 
//								}
//}
//	
//
//	
