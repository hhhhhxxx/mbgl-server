package com.hhhhhx.mbgl.mapper;

import com.hhhhhx.mbgl.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhhhhx.mbgl.param.msg.MessageListVM;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Repository
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> getEarlyChat(MessageListVM model);

    List<Message> getNewChat(MessageListVM model);
}
