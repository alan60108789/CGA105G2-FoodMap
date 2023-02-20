package com.member.model.Member.dao;

import com.member.model.Member.pojo.Member;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(Member member);
    public void update(Member member);
    public void delete(Integer memId);
    public Member getById(Integer memId);
    public List<Member> getAll();
    public void update(Integer id, Integer status);

    Member signin(String memAcc, String memPwd);

    List<Member> getAllByName(String memName);
    List<Member> getAllByAcc(String memAcc); //CJ 0125
    public void update3(Member member);
    public void update4(Member member);
    public void update5(Member member);
	public Member srhacc(String memacc);
	public Integer srhmail(String memmail);
}
