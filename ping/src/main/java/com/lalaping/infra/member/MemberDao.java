package com.lalaping.infra.member;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
	
	//Member
	public List<MemberDto> selectList(MemberVo memberVo);
	public List<MemberDto> seqList();
	public MemberDto selectOne(MemberDto memberDto);
	public MemberDto selectUsrOne(MemberDto memberDto);
	public int listCount(MemberVo memberVo);
	public int listAllCount();

	public int insertMember(MemberDto memberDto);
	public int updateMember(MemberDto memberDto);
	public int ueleteMember(MemberDto memberDto);
	public int deleteMember(MemberDto memberDto);
	
	public int selectOneCount(MemberVo memberVo);
	
	public MemberDto selectOneLogin(MemberDto memberDto); 
	
}