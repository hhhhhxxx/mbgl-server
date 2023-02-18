package com.hhhhhx.mbgl.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Message;
import com.hhhhhx.mbgl.mapper.MessageMapper;
import com.hhhhhx.mbgl.param.msg.MessageListVM;
import com.hhhhhx.mbgl.param.msg.MessageSendVM;
import com.hhhhhx.mbgl.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public boolean send(MessageSendVM model) {

        Message message = new Message();

        message.setContent(model.getContent());

        message.setCreateTime(LocalDateTime.now());

        message.setSendUserId(model.getSendUserId());

        message.setReceiveUserId(model.getReceiveUserId());

        message.setType(1);

        return this.save(message);
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
