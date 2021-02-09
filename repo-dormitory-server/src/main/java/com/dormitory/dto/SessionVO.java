package com.dormitory.dto;

import lombok.Data;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;

/**
 * 此类的作用是充当一个 Session 值的容器， 我们对每一个用户session的值都应该作为这个类的属性
 *
 * @author 由于经常要放入controller方法作为参数， 为了不使其属性很其他参数混淆，这里的所有属性最好加前缀
 */
@Data
public class SessionVO implements HttpSessionBindingListener, Serializable {

    private transient Logger logger = Logger.getLogger(this.getClass().getName());
    private static final long serialVersionUID = 1L;

    /**
     * member实体类的ID
     */
    private int svoId;
    /**
     * 学生/教师的ID
     */
    private int realId;

    /**
     * 名字
     */
    private String svoName;

    /**
     * 登录用的用户名
     */
    private String svoUsername;

    /**
     * 部门ID
     */
    private Integer svoDepartmentId;

    /**
     * 角色ID
     */
    private Integer svoRoleId;

    /**
     * 权限ID
     */
    private Integer svoPermissionId;

    /**
     * 可查看的渠道类型；1：加盟;2：快法务自营(不会展示和使用);3:快合自营;4,5:第三方;0:全部
     *
     */
    private Integer type;

    public SessionVO() {
        //System.out.println("create SessionVO");
    }

    @Override
    public void valueBound(HttpSessionBindingEvent arg0) {
        logger.info("Session value bound for member:" + svoId + "(" + svoName + ")" + " for key:" + arg0.getName());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent arg0) {
        logger.info("Value unbound for member:" + svoId + "(" + svoName + ")" + " for key:" + arg0.getName());
    }
}
