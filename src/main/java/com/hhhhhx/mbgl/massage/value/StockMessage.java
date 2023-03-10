package com.hhhhhx.mbgl.massage.value;

import com.hhhhhx.mbgl.massage.EnumClass;

public class StockMessage {
    public static EnumClass NO_STOCK = EnumClass.create(-4401,"库存不足");
    public static EnumClass CAL_ERROR = EnumClass.create(-4402,"库存计算出错");
    public static EnumClass UPDATE_ERROR = EnumClass.create(-4403,"库存更新出错");

    public static EnumClass ADD_ORDER_ERROR = EnumClass.create(-4404,"生成订单失败");
    public static EnumClass ADD_CHARGE_ERROR = EnumClass.create(-4405,"生成费用失败");
}
