package com;

import com.Power;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PowerAPI
 */
@WebServlet("/PowerAPI")
public class PowerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Power powerObj = new Power();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PowerAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = payObj.insertPower(request.getParameter("PowerId"), 
				request.getParameter("cusName"),      
				request.getParameter("pAccNo"),
				request.getParameter("psUnit"),
				request.getParameter("pDate"), 
				request.getParameter("pAmout")); 
	 
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map paras = getParasMap(request); 
		
		String output = powerObj.updatePower(paras.get("hidPowerIDSave").toString(),
				paras.get("cusName").toString(),     
				paras.get("pAccNo").toString(),
				paras.get("psUnit").toString(),  
				paras.get("pDate").toString(),
				paras.get("pAmout").toString());
		 	 
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		Map paras = getParasMap(request); 
		 
		String output = powerObj.deletePower(paras.get("powerId").toString());  
		 
		response.getWriter().write(output);
	}
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
	try
	 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
			 String[] p = param.split("=");
			 map.put(p[0], p[1]);
		 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}

}
