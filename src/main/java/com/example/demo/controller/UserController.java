package com.example.demo.controller;

import com.example.demo.configure.BaseController;
import com.example.demo.domain.SysUser;
import com.example.demo.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(SysController.class);
    @Autowired
    protected UserService userService;
    @Autowired
    HttpServletRequest request;

    public SysUser getUser() {
        return userService.getUserByName("wangwei");
    }

    @RequestMapping("/getList")
    public Object getList() {
        PageHelper.startPage(1, 10);
        Principal userPrincipal = request.getUserPrincipal();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(userService.getList(map));
            return success(sysUserPageInfo);
        } catch (Exception e) {
            log.error("error", e);
            return fail(e);
        }
    }

    @RequestMapping("/test")
    public Object test() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test", propertiesConfig.getTest());
        return map;
    }


}