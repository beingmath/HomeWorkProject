package jxau.soft.action;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import jxau.soft.dao.IUser;
import jxau.soft.pojo.User;
@Controller
@SessionAttributes(value="user")
public class UserControll{
	@Autowired
	IUser iuser;
	@RequestMapping("jsp/Register")
	@ResponseBody
	public String register(@RequestParam String name,@RequestParam String pwd,@RequestParam String role) throws ClassNotFoundException, SQLException {
		if(name.trim().equals("")||pwd.trim().equals("")) {
			return "null";
		}else {
		User user = iuser.selectUser(name);
		if(user==null) {
			//可以注册，将user数据插入数据库中中，注册成功
			iuser.insertUser(new User(name,pwd,role));
			return "true";
		}else {
			return "false";
		}
		}
	}
	@RequestMapping(value ="index.do")
	public String index() {
		return "redirect:jsp/index.html";
	}
	@RequestMapping("jsp/Login")
	public String login(@RequestParam String name,@RequestParam String password,Map<String, User> ma,HttpSession session) {
		User user=new User(name,password);
		User isuser = iuser.selectUserBy(user);
		System.out.println(session.getId());
		if(isuser!=null) {
			ma.put("user", isuser);
			return "redirect:home.jsp";
		}else {
			return "redirect:index.html";
		}
	}
}
