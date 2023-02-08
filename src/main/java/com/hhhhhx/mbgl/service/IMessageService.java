package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.msg.MessageListVM;
import com.hhhhhx.mbgl.param.msg.MessageSendVM;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
public interface IMessageService extends IService<Message> {
    @Transactional
    boolean send(MessageSendVM model);
    //
    List<Message> getBeforeChat(MessageListVM model);

    List<Message> getAfterChat(MessageListVM model);

}
