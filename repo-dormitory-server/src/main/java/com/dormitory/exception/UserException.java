package com.dormitory.exception;

public class UserException {
    private static final int START = 100000;
    public static final AppException NOT_FOUND = new AppException(START + 1, "用户不存在");
    public static final AppException DUP_USERNAME = new AppException(START + 2, "账号重复");
    public static final AppException EMPTY_NAME = new AppException(START + 3, "姓名不能为空");
    public static final AppException EMPTY_USERNAME = new AppException(START + 4, "账号不能为空");
    public static final AppException USERNAME_LONG = new AppException(START + 5, "账号过长");
    public static final AppException EMPTY_DEPARTMENT = new AppException(START + 6, "请选择部门");
    public static final AppException EMPTY_ROLE = new AppException(START + 7, "请选择角色");
    public static final AppException EMPTY_PERMISSION = new AppException(START + 8, "请选择数据权限");
    public static final AppException EMPTY_PASSWORD = new AppException(START + 9, "请输入新密码");
    public static final AppException LOGIN_FAIL = new AppException(START + 10, "账号或密码错误");
    public static final AppException CHANGE_OTHER_PASSWORD = new AppException(START + 11, "无权修改他人密码");
}
