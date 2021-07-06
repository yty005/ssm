package com.yty.ssm.dao;

import com.yty.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {
    @Select("select * from member where id = #{memberid}")
    public Member findByMemberId(Integer memberid) throws Exception;
}
