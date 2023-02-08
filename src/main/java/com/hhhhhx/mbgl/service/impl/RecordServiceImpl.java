package com.hhhhhx.mbgl.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.context.WebContext;
import com.hhhhhx.mbgl.entity.Record;
import com.hhhhhx.mbgl.entity.enums.DataType;
import com.hhhhhx.mbgl.mapper.RecordMapper;
import com.hhhhhx.mbgl.param.RecordAddVM;
import com.hhhhhx.mbgl.param.record.RecordAllDeatilData;
import com.hhhhhx.mbgl.param.record.RecordAllVM;
import com.hhhhhx.mbgl.param.record.RecordPageVM;
import com.hhhhhx.mbgl.param.record.RecordWeekData;
import com.hhhhhx.mbgl.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Autowired
    public WebContext webContext;

    @Override
    public boolean addRecord(RecordAddVM model) {

        Integer user_id = webContext.getCurrentUserId();

        Record record = new Record();

        record.setTime(LocalDateTime.now());
        record.setPatientUserId(user_id);

        if (model.getHeartRate() != null) {

            record.setDataId(DataType.HEART_RATE.getCode());
            record.setValue1(model.getHeartRate());
            record.setValue2(0);


        } else if (model.getStepCount() != null) {

            record.setDataId(DataType.STEP_COUNT.getCode());
            record.setValue1(model.getStepCount());
            record.setValue2(0);


        } else if (model.getBloodPressure1() != null && model.getBloodPressure2() != null) {

            record.setDataId(DataType.BLOOD_PRESSURE.getCode());
            record.setValue1(model.getBloodPressure1());
            record.setValue2(model.getBloodPressure2());

        } else if (model.getBloodSugar() != null) {

            record.setDataId(DataType.BLOOD_SUGAR.getCode());
            record.setValue1(model.getBloodSugar());
            record.setValue2(0);

        } else {
            return false;
        }
        return this.save(record);
    }

    @Override
    public RecordWeekData getWeekData(RecordPageVM model) {

        Page<Record> page = new Page<>(model.getPageIndex(), model.getPageSize());

        page = this.lambdaQuery().eq(Record::getPatientUserId, model.getPatientUserId())
                .eq(Record::getDataId, model.getType()).orderBy(true, true, Record::getTime)
                .page(page);


        List<Record> records = page.getRecords();

        RecordWeekData recordWeekData = new RecordWeekData();
        List<String> x = new ArrayList<>();
        List<Integer> y1 = new ArrayList<>();



        if (model.getType().equals(DataType.BLOOD_PRESSURE.getCode())) {

            List<Integer> y2 = new ArrayList<>();

            for (Record record : records) {

                String format = record.getTime().format(DateTimeFormatter.ofPattern("yy-MM-dd"));

                x.add(format);
                y1.add(record.getValue1());
                y2.add(record.getValue2());
            }

            recordWeekData.setY2(y2);

        } else {

            for (Record record : records) {
                String format = record.getTime().format(DateTimeFormatter.ofPattern("yy-MM-dd"));

                x.add(format);
                y1.add(record.getValue1());
            }
        }

        recordWeekData.setX(x);
        recordWeekData.setY1(y1);

        return recordWeekData;
    }

    @Override
    public RecordAllDeatilData getAllDetail(RecordAllVM model) {

        Integer patientUserId = model.getPatientUserId();

        RecordPageVM recordPageVM = new RecordPageVM();


        recordPageVM.setPatientUserId(patientUserId);
        recordPageVM.setPageIndex(model.getPageIndex());
        recordPageVM.setPageSize(model.getPageSize());


        RecordAllDeatilData recordAllDeatilData = new RecordAllDeatilData();

        recordPageVM.setType(1);
        RecordWeekData weekData1 = this.getWeekData(recordPageVM);

        recordPageVM.setType(2);
        RecordWeekData weekData2 = this.getWeekData(recordPageVM);

        recordPageVM.setType(3);
        RecordWeekData weekData3 = this.getWeekData(recordPageVM);

        recordPageVM.setType(4);
        RecordWeekData weekData4 = this.getWeekData(recordPageVM);



        recordAllDeatilData.setX1(weekData1.getX());
        recordAllDeatilData.setY1(weekData1.getY1());

        recordAllDeatilData.setX2(weekData2.getX());
        recordAllDeatilData.setY2(weekData2.getY1());

        recordAllDeatilData.setX3(weekData3.getX());
        recordAllDeatilData.setY31(weekData3.getY1());
        recordAllDeatilData.setY32(weekData3.getY2());

        recordAllDeatilData.setX4(weekData4.getX());
        recordAllDeatilData.setY4(weekData4.getY1());

        return recordAllDeatilData;
    }
}
