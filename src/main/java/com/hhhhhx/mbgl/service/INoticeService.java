package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.entity.Patient;
import com.hhhhhx.mbgl.param.notice.NoticeOptionVM;
import com.hhhhhx.mbgl.param.notice.NoticePageVM;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-22
 */
public interface INoticeService extends IService<Notice> {
    @Transactional
    boolean sendApplyConnect(Integer doctorUserId, Patient patient);

    Page<Notice> pageList(NoticePageVM model);
    @Transactional
    boolean option(NoticeOptionVM model);
    @Transactional
    boolean deleteNotice(Integer id);
}
