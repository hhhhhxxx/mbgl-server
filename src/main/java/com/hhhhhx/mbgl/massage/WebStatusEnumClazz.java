package com.hhhhhx.mbgl.massage;



/**
 * 公共web响应状态，可以作为子项目的父类集成
 */
public class WebStatusEnumClazz {

  public static final EnumClass NOT_FOUND = EnumClass.create(-404,MbglMessageResource.WEB_STATUS_NOT_FOUND);
  public static final EnumClass DATA_ERROR = EnumClass.create(-400,MbglMessageResource.WEB_STATUS_DATA_ERROR);
  public static final EnumClass METHOD_NOT_SUPPORT = EnumClass.create(-405,MbglMessageResource.WEB_STATUS_METHOD_NOT_SUPPORT);
  public static final EnumClass PARAM_ERROR = EnumClass.create(-1000,MbglMessageResource.WEB_STATUS_PARAM_ERROR);


}
