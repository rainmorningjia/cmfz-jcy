package com.baizhi.cmfz.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baizhi.cmfz.util.CreateValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/img")
public class ImgController {

	@RequestMapping("createImg")
	public void createImg(HttpSession session,HttpServletResponse response) throws IOException{
		CreateValidateCode cvc=new CreateValidateCode();
		String code=cvc.getCode();
		session.setAttribute("code", code);
		cvc.write(response.getOutputStream());
	}

}
