package com.yty.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.yty.ssm.dao.SysLogDao;
import com.yty.ssm.domain.SysLog;
import com.yty.ssm.service.SysLogService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysLogService")
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void saveSysLog(SysLog sysLog) throws Exception {
        sysLogDao.saveSysLog(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
