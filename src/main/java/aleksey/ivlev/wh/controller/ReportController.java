package aleksey.ivlev.wh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aleksey.ivlev.wh.services.ProductService;



@Controller
@RequestMapping("view/index.jsp")
public class ReportController{
	private ProductService prodService;
	public int getReport(){
		int id = prodService.getProduct("Apple");
		return id;
		
	}
	
}
