package com.hhhhhx.mbgl.service;

import com.hhhhhx.mbgl.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.RecordAddVM;
import com.hhhhhx.mbgl.param.record.RecordAllDeatilData;
import com.hhhhhx.mbgl.param.record.RecordAllVM;
import com.hhhhhx.mbgl.param.record.RecordPageVM;
import com.hhhhhx.mbgl.param.record.RecordWeekData;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
public interface IRecordService extends IService<Record> {
    @Transactional
    boolean addRecord(RecordAddVM model);

    RecordWeekData getWeekData(RecordPageVM model);

    RecordAllDeatilData getAllDetail(RecordAllVM model);
}
