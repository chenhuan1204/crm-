package com.itheima.crm.web.action;


import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动使用对象
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	//注入service
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注册的方法：regist()
	 * @return
	 */
	public String regist() throws Exception {
		System.out.println("用户注册方法执行了。。。。");
		//调用业务层完成用户注册功能
		userService.regist(user);
		System.out.println(user);
		return LOGIN ;
	}

	/**
	 * 用户登录的执行方法 login
	 * @return
	 */
	public String login() throws Exception {
		System.out.println("用户登录方法执行了。。。。");
		User existUser = userService.login(user);
		//判断用户是否存在
		if (existUser==null) {
			//登录失败
			this.addActionError("用户名或密码错误");
			return LOGIN;
		} else {
			//登录成功
			//将用户的信息存入到session中
			ActionContext.getContext().getSession().put("existUser", existUser);
			//页面跳转
			return SUCCESS;
		}

	}
	/**
	 * 登出
	 */
	
	public String loginout() {
		//获得session，将session中的用户数据删除
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return LOGIN;
	}
	/**
	 * 查询所有用户
	 * @return
	 * @throws IOException 
	 */
	public String findAllUser() throws IOException {
		List<User> list = userService.findAll();
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
