package com.lalaping.infra.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	public MemberDao memberDao;

	// Member
    public List<MemberDto> selectList(MemberVo memberVo) {
    	return memberDao.selectList(memberVo);
    }
    public List<MemberDto> seqList() {
    	return memberDao.seqList();
    }
    public MemberDto selectOne(MemberDto memberDto) {
    	return memberDao.selectOne(memberDto);
    }
    public int listCount(MemberVo memberVo) {
        return memberDao.listCount(memberVo);
    }
	public int selectOneCount(MemberVo memberVo) {
		return memberDao.selectOneCount(memberVo); 
	}
    
    // Member CRUD
    public int insertMember(MemberDto memberDto) {
        return memberDao.insertMember(memberDto);
    }
    public int updateMember(MemberDto memberDto) {
        return memberDao.updateMember(memberDto);
    }
    public int deleteMember(MemberDto memberDto) {
        return memberDao.deleteMember(memberDto);
    }
    public int ueleteMember(MemberDto memberDto) {
        return memberDao.ueleteMember(memberDto);
    }
    
    //로그인
    public MemberDto selectOneLogin(MemberDto memberDto) {
		return memberDao.selectOneLogin(memberDto);
	}

}
