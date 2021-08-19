package com.jspController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class JSPAdminUserController {
	
	@GetMapping("/userMangement")
	public String userMangement() {
		return "AdminUserManagement";
	}
}
