package com.sist.model;

import java.io.PrintWriter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	@RequestMapping("food/find.do")
	public String food_find(HttpServletRequest request,HttpServletResponse response) {
		/*  String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		final int ROWSIZE=12;
		int start=(curpage*ROWSIZE)-ROWSIZE;
		String column=request.getParameter("column");
		if(column==null)
			column="address";
		String ss=request.getParameter("ss");
		if(ss==null)
			ss="마포";
		String[] types=request.getParameterValues("type");
		
		Map map=new HashMap();
		map.put("ss", ss);
		map.put("start", start);
		map.put("column", column);
		map.put("fdArr", types);
		List<FoodVO> list=FoodDAO.foodFindData(map);
		int count=FoodDAO.foodFindCount(map);
		
		int totalpage=(int)(Math.ceil(count/(double)ROWSIZE));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("curpage", curpage);
		request.setAttribute("ss", ss);  */
		return "find.jsp";
	}
	@RequestMapping("food/find_ajax.do")
	public void food_find_ajax(HttpServletRequest request,HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		final int ROWSIZE=12;
		int start=(curpage*ROWSIZE)-ROWSIZE;
		String column=request.getParameter("column");
		if(column==null)
			column="address";
		String ss=request.getParameter("ss");
		if(ss==null)
			ss="마포";
		String[] types=request.getParameterValues("type");
		System.out.println(Arrays.toString(types));
		Map map=new HashMap();
		map.put("ss", ss);
		map.put("start", start);
		map.put("column", column);
		map.put("fdArr", types);
		List<FoodVO> list=FoodDAO.foodFindData(map);
		int count=FoodDAO.foodFindCount(map);
		
		int totalpage=(int)(Math.ceil(count/(double)ROWSIZE));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		try {
			JSONArray arr=new JSONArray();
			int i=0;
			for(FoodVO vo:list) {
				JSONObject obj=new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("poster", vo.getPoster());
				obj.put("address", vo.getAddress());
				obj.put("type", vo.getType());
				if(i==0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
					obj.put("startPage", startPage);
					obj.put("endPage", endPage);
					obj.put("count", count);
				}
				arr.add(obj);
				i++;
			}
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(arr.toJSONString());
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
