package com.hhhhhx.mbgl.massage.value;

import com.hhhhhx.mbgl.massage.EnumClass;

public class SystemValue {

    public static EnumClass OK = EnumClass.create(1,"成功");

    public static EnumClass FAIL = EnumClass.create(-1,"失败");
    public static EnumClass PARAM_ERROR = EnumClass.create(-666,"参数错误");


    public static EnumClass SERVER_ERROR = EnumClass.create(-500,"服务器开小差~");
    public static EnumClass CLIENT_ERROR = EnumClass.create(-400,"客户端开小差~");



    public static EnumClass ADD_FAIL = EnumClass.create(-2001,"新增失败");
    public static EnumClass UPDATE_FAIL = EnumClass.create(-2002,"更新失败");
    public static EnumClass DELETE_FAIL = EnumClass.create(-2003,"删除失败");
    public static EnumClass SELECT_FAIL = EnumClass.create(-2004,"查询失败");
}
