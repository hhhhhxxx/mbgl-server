package com.hhhhhx.mbgl.service.other;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hhhhhx.mbgl.config.properties.SystemConfig;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.wx.WxAccessTokenResult;
import com.hhhhhx.mbgl.entity.wx.WxJscode2sessionResult;
import com.hhhhhx.mbgl.entity.wx.WxSubscribeMessage;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.param.wx.WxDecodeDataVM;
import com.hhhhhx.mbgl.param.wx.WxSendSubscribeMessage;
import com.hhhhhx.mbgl.service.IUserService;
import com.hhhhhx.mbgl.utils.HttpUtil;
import com.hhhhhx.mbgl.utils.RedisConstant;
import com.hhhhhx.mbgl.utils.WxDecodeUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WxRunService {

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IUserService userService;

    private final String template_id = "EfZDuJ1O5KfT7gqW9kb3upmkVUaYk0csck3_cJlrzto";

    public WxJscode2sessionResult getSessionKeyAndOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code";

        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", systemConfig.getWx().getAppid());
        params.put("secret", systemConfig.getWx().getSecret());
        params.put("js_code", code);


        ResponseEntity<String> response = HttpUtil.get(url, params);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return null;
        }

        String body = response.getBody();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        return JSONUtil.toBean(jsonObject, WxJscode2sessionResult.class);
    }

    public boolean sessionkey(Integer userId, String code) {

        WxJscode2sessionResult result = this.getSessionKeyAndOpenId(code);

        if (result == null) {
            return false;
        }

        stringRedisTemplate.opsForValue()
                .set(RedisConstant.USER_PREFIX + userId, JSONUtil.toJsonStr(result), 3L,TimeUnit.DAYS);

        return true;
    }

    public String decode(Integer userId, WxDecodeDataVM model) {

        String s = stringRedisTemplate.opsForValue()
                .get(RedisConstant.USER_PREFIX + userId);

        System.out.println("微信用户信息"+s);

        if(StrUtil.isEmpty(s)) {
            return null;
        }

        JSONObject jsonObject = JSONUtil.parseObj(s);

        WxJscode2sessionResult result = JSONUtil.toBean(jsonObject, WxJscode2sessionResult.class);

        String session_key = result.getSession_key();

        String data = WxDecodeUtil.decryptWxDataOfHutool(session_key, model.getEncryptedData(), model.getIv());

        return data;
    }

    public WxAccessTokenResult getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?appid={appid}&secret={secret}&grant_type=client_credential";

        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", systemConfig.getWx().getAppid());
        params.put("secret", systemConfig.getWx().getSecret());


        ResponseEntity<String> response = HttpUtil.get(url, params);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return null;
        }

        String body = response.getBody();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        return JSONUtil.toBean(jsonObject, WxAccessTokenResult.class);
    }

    public WxSubscribeMessage messageSubscribe(String access_token,String template_id,String openId,JSONObject data) {

        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+access_token;


        JSONObject params = new JSONObject();

        //params.set("access_token",access_token);
        params.set("template_id",template_id);
        params.set("touser",openId);
        params.set("data",data);
        params.set("miniprogram_state","developer");
        params.set("lang","zh_CN");

        ResponseEntity<JSONObject> response = HttpUtil.post(url, params);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return null;
        }

        log.error(response.toString());

        return JSONUtil.toBean(response.getBody(), WxSubscribeMessage.class);
    }

    public Boolean sendMessage(WxSendSubscribeMessage param) {
        // 获取accessToken
        WxAccessTokenResult wxAccessTokenResult = this.getAccessToken();

        // 获取一下参数
        String access_token = wxAccessTokenResult.getAccess_token();

        String template_id = this.template_id;

        User user = userService.getById(param.getUserId());

        if(user == null || user.getOpenId() == null) throw new MbglServiceException();

        String openId = user.getOpenId();

        JSONObject data = new JSONObject();

        JSONObject thing1 = new JSONObject();
        thing1.set("value",param.getDrugName());


        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(param.getTime());

        JSONObject thing2 = new JSONObject();
        thing2.set("value", format);

        JSONObject thing3 = new JSONObject();
        thing3.set("value",param.getAdvice());

        JSONObject thing4 = new JSONObject();
        thing4.set("value",param.getNum());

        JSONObject thing5 = new JSONObject();
        thing5.set("value",param.getInfo());

        data.set("thing1",thing1);
        data.set("time2",thing2);
        data.set("thing3",thing3);
        data.set("thing4",thing4);
        data.set("thing5",thing5);

        log.info(data.toString());
        // 推送消息
        WxSubscribeMessage wxSubscribeMessage = this.messageSubscribe(access_token,template_id,openId,data);

        if(wxSubscribeMessage.getErrcode() != 0) {
            log.error(wxSubscribeMessage.getErrcode()+"-"+wxSubscribeMessage.getErrmsg());
            throw new MbglServiceException();
        }
        return true;
    }
}
