package com.yty.ssm.service;

import com.yty.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {

    void saveSysLog(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page,Integer size);
}
