package com.lalaping.infra.code;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface CodeDao {
	public List<CodeDto> selectList(CodeVo vo);
	public int listCount(CodeVo vo);
	public int insert(CodeDto codeDto);
	
	public CodeDto selectOne(CodeDto codeDto);
	
	public int update(CodeDto codeDto);
	
	public int uelete(CodeDto codeDto);
	
	public int delete(CodeDto codeDto);
	
	public List<CodeDto> selectListCachedCodeArrayList();

}
