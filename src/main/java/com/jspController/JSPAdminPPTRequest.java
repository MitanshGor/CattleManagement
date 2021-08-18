package com.jspController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.PowerPointKeyword;
import com.dao.PowerPointRequestDao;

@RequestMapping("/admin")
@Controller
public class JSPAdminPPTRequest {
	
	@Autowired
	PowerPointRequestDao powerPointRequest;
	
	@GetMapping("/pptRequest")
	public String pptRequest(Model model,HttpSession session) {
		try{
			String msg =(String) session.getAttribute("msg");
			model.addAttribute("msg",msg);
			session.removeAttribute("msg");
		}catch(Exception e) {
			session.removeAttribute("msg");
		}
		model.addAttribute("requestList",powerPointRequest.getAllRequest());
		return "AdminPowerPointRequest";
	}
	
	@PostMapping("/updateComment")
	public @ResponseBody String removeKeyword(@RequestParam("requestID") int requestID, 
			@RequestParam("comment") String comment){
		int i = powerPointRequest.updateComment(requestID,comment);
		return String.valueOf(i);
	}
	
	@GetMapping("closePPTRequestQuery/{requestID}")
	public String closePPTRequestQuery(@PathVariable("requestID") int requestID,HttpSession session) {
		int i = powerPointRequest.closePPTRequestQuery(requestID);
		if(i == 1) {
			session.setAttribute("msg", "Deactivated the query successfully");				
		}
		else {
			session.setAttribute("msg", "Some error occured");
		}
		return "redirect:/admin/pptRequest";
	}
	
	
}
