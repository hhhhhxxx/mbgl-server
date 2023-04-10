package com.hhhhhx.mbgl.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hhhhhx.mbgl.dto.DrugMessageDTO;
import com.hhhhhx.mbgl.entity.Drug;
import com.hhhhhx.mbgl.entity.Message;
import com.hhhhhx.mbgl.entity.enums.MessageType;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.mapper.MessageMapper;
import com.hhhhhx.mbgl.param.drugstore.order.Shop;
import com.hhhhhx.mbgl.param.msg.MessageListVM;
import com.hhhhhx.mbgl.param.msg.MessageSendVM;
import com.hhhhhx.mbgl.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.service.drugstore.IDrugService;
import com.hhhhhx.mbgl.service.drugstore.IPrescriptionService;
import com.hhhhhx.mbgl.ws.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    IDrugService drugService;

    @Autowired
    IPrescriptionService prescriptionService;

    public boolean sendNormalMessage(MessageSendVM model) {
        Message message = new Message();
        message.setCreateTime(LocalDateTime.now());
        message.setSendUserId(model.getSendUserId());
        message.setReceiveUserId(model.getReceiveUserId());
        message.setContent(model.getContent());
        message.setType(MessageType.NORMAL_MSG.getCode());

        if (!message.getContent().isEmpty() && this.save(message)) {
            // websocket发消息
            WebSocketService.sendInfo(message);
            return true;
        } else {
            throw new MbglServiceException();
        }
    }

    public boolean sendPrescriptionMessage(MessageSendVM model) {
        Message message = new Message();
        message.setCreateTime(LocalDateTime.now());
        message.setSendUserId(model.getSendUserId());
        message.setReceiveUserId(model.getReceiveUserId());

        List<Shop> shopList = model.getShopList();

        if (shopList.isEmpty()) {
            throw new MbglServiceException();
        }

        // 判断库存
        List<Integer> drugIds = shopList.stream().map(Shop::getId).collect(Collectors.toList());

        List<Drug> drugs = drugService.listByIds(drugIds);

        Map<Integer, Drug> drugMap = drugs.stream().collect(Collectors.toMap(Drug::getId, Function.identity()));


        // 检验大小
        if(drugs.size() != shopList.size()) {
            throw new MbglServiceException();
        }

        // 创建处方
        Integer preID = prescriptionService.createPrescription(model.getSendUserId(), model.getReceiveUserId(), shopList);


        // 编造消息
        List<DrugMessageDTO> drugMessageDTOList = new ArrayList<>(shopList.size());

        for (Shop shop : shopList) {
            Drug drug = drugMap.get(shop.getId());

            DrugMessageDTO drugMessageDTO = new DrugMessageDTO();

            drugMessageDTO.setId(drug.getId());
            drugMessageDTO.setName(drug.getName());
            drugMessageDTO.setUnit(drug.getUnit());
            drugMessageDTO.setQuantity(shop.getQuantity());
            drugMessageDTOList.add(drugMessageDTO);
        }

        String s = JSON.toJSONString(drugMessageDTOList);

        message.setContent(s);
        message.setPrescriptionId(preID);
        message.setType(MessageType.PRES_MSG.getCode());


        if (!message.getContent().isEmpty() && this.save(message)) {
            // websocket发消息
            WebSocketService.sendInfo(message);
            return true;
        } else {
            throw new MbglServiceException();
        }
    }

    @Override
    @Transactional
    public boolean send(MessageSendVM model) {

        boolean flag = false;
        if(model.getType().equals(MessageType.NORMAL_MSG.getCode())) {
            flag = sendNormalMessage(model);
        } else if(model.getType().equals(MessageType.PRES_MSG.getCode())) {
            flag = sendPrescriptionMessage(model);
        }
        return flag;
    }

    @Override
    public List<Message> getBeforeChat(MessageListVM model) {

        List<Message> earlyChat = this.baseMapper.getEarlyChat(model);
        return earlyChat;
    }

    @Override
    public List<Message> getAfterChat(MessageListVM model) {

        List<Message> newChat = this.baseMapper.getNewChat(model);
        return newChat;
    }
}
