package com.iems.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.iems.core.util.HttpClientUtil;
import com.iems.core.util.RSA;

public class OAuthTokenFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			
			String target_url = request.getParameter("state");
			
			String code = request.getParameter("code");
			
			System.out.println(request.getServerName());
	
			String serverUrl = request.getScheme() +"://"+ request.getServerName() +":"+ request.getLocalPort()+request.getContextPath();
			
			String url = serverUrl + "/oauth/token";
			System.out.println(url);
			
			String redirect_uri = serverUrl + request.getServletPath();
			System.out.println(redirect_uri);
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("client_id", "client_iems");
			data.put("client_secret", "secret_iems");
			data.put("grant_type", "authorization_code");
			data.put("code", code);
			data.put("redirect_uri", redirect_uri);
			
			
			String token = HttpClientUtil.get(url, data, "UTF-8", 0);
			System.out.println(token);
			
			JSONObject jsonObject = JSONObject.fromObject(token);
			
			String access_token = jsonObject.getString("access_token");
			System.out.println(access_token);
			
			String token_type = jsonObject.getString("token_type");
			System.out.println(token_type);
			
			String refresh_token = jsonObject.getString("refresh_token");
			
			int expires_in = jsonObject.getInt("expires_in");
			
			String scope = jsonObject.getString("scope");

			// 通过RSA加密后写入cookie
			String modulus = "";
			String exponent = "";
			
			Cookie[] cookies = request.getCookies();
			
			for (int i=0; i<cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase("modulus")) {
					modulus = c.getValue();
				} else if (c.getName().equalsIgnoreCase("exponent")) {
					exponent = c.getValue();
				}
			}
			
			String N = modulus;
			String e = exponent;
			
			System.out.println("N = "+N);
			System.out.println("e = "+e);
			
			RSA rsa = new RSA();
			rsa.initPublicKey(N, e);
			
			/*
			byte[] rsa_encrypt_bytes = rsa.encrypt(token.getBytes("UTF-8"));
			StringBuilder encrypt_token = new StringBuilder();
			for (int i = 0; i < rsa_encrypt_bytes.length; i++)
	        {
				int v = rsa_encrypt_bytes[i] & 0xFF;   
		        String hv = Integer.toHexString(v);   
		        if (hv.length() < 2) {   
		        	encrypt_token.append(0);   
		        }   
		        encrypt_token.append(hv); 
	        }
			System.out.println("encrypt_token = "+encrypt_token.toString());
			*/
			
			byte[] rsa_encrypt_bytes_1 = rsa.encrypt(access_token.getBytes("UTF-8"));
			//System.out.println(rsa_encrypt_bytes_1);
			//System.out.println(new String(rsa_encrypt_bytes_1));
			
			//System.out.println(new String(rsa_encrypt_bytes_1, "UTF-8"));
			
			StringBuilder encrypt_access_token = new StringBuilder();
			for (int i = 0; i < rsa_encrypt_bytes_1.length; i++)
	        {
				int v = rsa_encrypt_bytes_1[i] & 0xFF;   
		        String hv = Integer.toHexString(v);   
		        if (hv.length() < 2) {   
		        	encrypt_access_token.append(0);   
		        }   
		        encrypt_access_token.append(hv); 
	        }
			System.out.println("encrypt_access_token = "+encrypt_access_token.toString());
			
			byte[] rsa_encrypt_bytes_2 = rsa.encrypt(refresh_token.getBytes("UTF-8"));
			
			StringBuilder encrypt_refresh_token = new StringBuilder();
			for (int i = 0; i < rsa_encrypt_bytes_2.length; i++)
	        {
				int v = rsa_encrypt_bytes_2[i] & 0xFF;   
		        String hv = Integer.toHexString(v);   
		        if (hv.length() < 2) {   
		        	encrypt_refresh_token.append(0);   
		        }   
		        encrypt_refresh_token.append(hv); 
	        }
			
			String path = request.getContextPath();
			
			/*
			Cookie c_token = new Cookie("token", encrypt_token.toString());
			c_token.setPath(path);
			response.addCookie(c_token);
			*/

			Cookie c_access_token = new Cookie("access_token", encrypt_access_token.toString());
			c_access_token.setPath(path);
			response.addCookie(c_access_token);
			
			Cookie c_token_type = new Cookie("token_type", token_type);
			c_token_type.setPath(path);
			response.addCookie(c_token_type);
			
			Cookie c_refresh_token = new Cookie("refresh_token", encrypt_refresh_token.toString());
			c_refresh_token.setPath(path);
			response.addCookie(c_refresh_token);
			
			Cookie c_expires_in = new Cookie("expires_in", String.valueOf(expires_in));
			c_expires_in.setPath(path);
			response.addCookie(c_expires_in);

			Cookie c_scope = new Cookie("scope", scope);
			c_scope.setPath(path);
			response.addCookie(c_scope);

			response.sendRedirect(target_url);
			
			return;
		} catch(Exception ex) {
			ex.printStackTrace();
			
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
