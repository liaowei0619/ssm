package com.louis.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	/*@Autowired
	@Qualifier(value = UsersServiceImpl.SERVICE_NAME)
	private UsersService usersServiceImpl;
*/
	/**
	 * Handler执行之前调用这个方法
	 */

	// TODO :拦截controller接口

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 获取Session

		System.out.println("拦截器启动...");
		
		/*HttpSession session = request.getSession();

		String adminId = (String) session.getAttribute("adminId");

		Omtcp_UserEntity users = usersServiceImpl.find(adminId);
		if (users == null) {
			throw new CustomException("非正常渠道访问,已拒绝!");
		}*/

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
