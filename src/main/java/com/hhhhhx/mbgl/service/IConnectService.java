package com.hhhhhx.mbgl.service;

import com.hhhhhx.mbgl.entity.Connect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.connect.ConnectApplyVM;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-22
 */
public interface IConnectService extends IService<Connect> {

    Boolean apply(ConnectApplyVM model);

    Boolean confirm(Connect connect);

    Boolean refuse(Connect connect);

    Boolean disconnect(ConnectApplyVM model);

    Connect getConnectByDoubleId(Integer patientUserId, Integer doctorUserId);
}
