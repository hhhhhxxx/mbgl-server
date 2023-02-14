package com.hhhhhx.mbgl.massage.value;

import com.hhhhhx.mbgl.massage.EnumClass;

public class SystemValue {

    public static EnumClass OK = EnumClass.bulid(1,"成功");

    public static EnumClass FAIL = EnumClass.bulid(-1,"失败");
    public static EnumClass PARAM_ERROR = EnumClass.bulid(-666,"参数错误");


    public static EnumClass SERVER_ERROR = EnumClass.bulid(-500,"服务器开小差~");
    public static EnumClass CLIENT_ERROR = EnumClass.bulid(-400,"客户端开小差~");



    public static EnumClass ADD_FAIL = EnumClass.bulid(-2001,"新增失败");
    public static EnumClass UPDATE_FAIL = EnumClass.bulid(-2002,"更新失败");
    public static EnumClass DELETE_FAIL = EnumClass.bulid(-2003,"删除失败");
    public static EnumClass SELECT_FAIL = EnumClass.bulid(-2004,"查询失败");
}
