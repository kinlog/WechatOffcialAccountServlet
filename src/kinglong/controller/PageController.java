package kinglong.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wechat.entity.reply.AccessTokenByCode;
import com.wechat.entity.reply.UserInfoByAuth;
import com.wechat.service.GetUserInfoByAuth;

@Controller
@RequestMapping("/pages")
public class PageController {

	
	@RequestMapping(value="/demo", method=RequestMethod.GET)
	public ModelAndView showDemo(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("demo");
		return model;
	}
	
	// OAuth 2.0 授权测试
	@RequestMapping(value="/auth", method=RequestMethod.GET)
	public ModelAndView auth(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("auth");
		return model;
	}
	
	@RequestMapping(value="/userinfo", method=RequestMethod.GET)
	public ModelAndView getUserInfo(HttpServletRequest request) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		System.out.println(new Date().toString()+"     code:"+code+"   state:"+state);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("userInfo");
		// 用户授权
		if (code != null && !"".equals(code)) {
			model.addObject("code", code);
			AccessTokenByCode accessTokenObject = GetUserInfoByAuth.getAccessToken(code);
			model.addObject("object", accessTokenObject);
			
			// userinfo 方式获取用户详细信息
			if ("userinfo".equals(state)) {
				UserInfoByAuth user = GetUserInfoByAuth.getUserInfo(accessTokenObject.getAccess_token(), accessTokenObject.getOpenid());
				model.addObject("user", user);
			}
		} 
		// 用户未授权
		else {
			
		}
		
		return model;
	}
	
}
