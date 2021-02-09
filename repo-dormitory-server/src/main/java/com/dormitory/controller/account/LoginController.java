package com.dormitory.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.dormitory.dao.account.RoleActionRepository;
import com.dormitory.dto.AjaxResponse;
import com.dormitory.dto.SessionVO;
import com.dormitory.exception.UserException;
import com.dormitory.model.account.User;
import com.dormitory.service.account.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import static com.dormitory.util.CodeConst.*;
@RestController
@RequestMapping(value = "/LoginController" , produces = {"application/json;charset=UTF-8"} , method = RequestMethod.POST)
public class LoginController {

    @Autowired
    private RoleActionRepository roleActionRepository;
    @Autowired
    private UserService userService;

    private transient Logger logger = Logger.getLogger(this.getClass().getName());

    @RequestMapping(value = "/login")
    public AjaxResponse login(@RequestBody JSONObject param, HttpServletRequest request, HttpServletResponse response){
        logger.info("start user login");
        AjaxResponse json = AjaxResponse.buildSuccessResponse();
        try {
            //登录验证流程
            //获取当前用户
            Subject currentUser = SecurityUtils.getSubject();
            String username = param.getString("username");
            String password = param.getString("password");
            logger.info("username:" + username);
            logger.info("password:" + password);
            //检测身份是否合理
            if (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0) {
                throw UserException.LOGIN_FAIL;
            } else {
                username = username.trim();
                password = password.trim();
            }
            logger.info("enter stage 1");
            if (!currentUser.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                try {
                    logger.info("enter stage 2");
                    currentUser.login(token);
                    //设置session永不过期 timeout:-1000ms
                    currentUser.getSession().setTimeout(-1000l);
                    logger.info("enter stage 3");
                } catch (AuthenticationException e) {
                    logger.info("enter stage 4", e);
                    json.putError("用户名密码错误");
                    //跳转页面
                    WebUtils.issueRedirect(request, response, "",null,true,false);
                    return json;
                }
            }
            logger.info("enter stage 5");
            User user = (User) currentUser.getPrincipal();
            if (user != null) {
                logger.info("enter stage 6");
                json.putData(user);
                json.putData("sessionId", currentUser.getSession().getId());
                json.putData("realId", user.getId());
                json.putData("permissions", roleActionRepository.listActionTypesByRoleId(user.getRole().getId()));
                json.putData("visibleType", user.getPermission()!=null?user.getPermission().getType():null);

                //设置session SVO
                HttpSession session = request.getSession();
                SessionVO svo = buildUserSessionVO(user);
                session.setAttribute(SESSION_VO_STR, svo);

                System.out.println(session.getAttribute(SESSION_VO_STR));

                //设置cookie
                this.addCookie(request, response, COOKIE_MEMBER_ACCOUNT, username, COOKIE_MAX_DURATION);
            } else {
                logger.info("7");
                throw UserException.LOGIN_FAIL;
            }
        }catch (Exception e){
            logger.error("login " + e.getMessage(), e);
            json.putError(e.getMessage());
        }
        return json;
    }

    private SessionVO buildUserSessionVO(User user) {
        SessionVO svo = new SessionVO();
        if (user.getRole().getId()==2){
            svo.setRealId(user.getStudentInfo().getId());
        }
        if (user.getRole().getId()==3){
            svo.setRealId(user.getTeacherInfo().getId());
        }
        svo.setSvoId(user.getId());
        svo.setSvoName(user.getUsername());
        svo.setSvoRoleId(user.getRole().getId());
//        svo.setSvoPermissionId(user.getPermission().getId());
        svo.setSvoUsername(user.getUsername());
//        svo.setType(user.getPermission().getType());
        return svo;
    }

    private void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String value, int duration) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(request.getContextPath() == null || request.getContextPath().length() == 0 ? "/" : request.getContextPath());
        cookie.setMaxAge(duration);
        response.addCookie(cookie);
    }

}
