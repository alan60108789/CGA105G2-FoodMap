package com.member.model.service;

import java.sql.Date;
import java.util.List;

import com.member.model.Member.dao.MemberDAO_interface;
import com.member.model.Member.dao.impl.MemberDAO;
import com.member.model.Member.pojo.Member;

public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public Member topojo(String memName, String memAcc, String memPwd, String memrecipient, String memTwId,
			Date memBirthday, String memPhone, Integer memPostalcode, String memCity, String memDistrict,
			String memAddress, String memMail) {
		Member member = new Member();

		member.setMemName(memName);
		member.setMemAcc(memAcc);
		member.setMemPwd(memPwd);
		member.setMemRecipient(memrecipient);
		member.setMemTwId(memTwId);
		member.setMemBirthday(memBirthday);
		member.setMemPhone(memPhone);
		member.setMemPostalCode(memPostalcode);
		member.setMemCity(memCity);
		member.setMemDistrict(memDistrict);
		member.setMemAddress(memAddress);
		member.setMemMail(memMail);

		return member;
	}

	public Member addMem(String memName, String memAcc, String memPwd, String memrecipient, String memTwId,
			Date memBirthday, String memPhone, Integer memPostalcode, String memCity, String memDistrict,
			String memAddress, String memMail) {
		Member member = new Member();
		member.setMemName(memName);
		member.setMemAcc(memAcc);
		member.setMemPwd(memPwd);
		member.setMemRecipient(memrecipient);
		member.setMemTwId(memTwId);
		member.setMemBirthday(memBirthday);
		member.setMemPhone(memPhone);
		member.setMemPostalCode(memPostalcode);
		member.setMemCity(memCity);
		member.setMemDistrict(memDistrict);
		member.setMemAddress(memAddress);
		member.setMemMail(memMail);
		dao.insert(member);
		return member;
	}

	public Member updateMem(Integer memId, String memName, String memAcc, String memrecipient, String memTwId,
			Date memBirthday, String memPhone, Integer memPostalcode, String memCity, String memDistrict,
			String memAddress, String memMail, String memText, byte[] memPic) {
		Member member = new Member();

		member.setMemId(memId);
		member.setMemName(memName);
		member.setMemAcc(memAcc);
//		member.setMemPwd(memPwd);
		member.setMemRecipient(memrecipient);
		member.setMemTwId(memTwId);
		member.setMemBirthday(memBirthday);
		member.setMemPhone(memPhone);
		member.setMemPostalCode(memPostalcode);
		member.setMemCity(memCity);
		member.setMemDistrict(memDistrict);
		member.setMemAddress(memAddress);
		member.setMemMail(memMail);
		member.setMemText(memText);
		member.setMemPic(memPic);
		dao.update(member);

		return member;
	}

	public List<Member> getMember(String memName) {
		return dao.getAllByName(memName);
	}

	public List<Member> getMemberList() {
		return dao.getAll();
	}

	public Member signin(String memAcc, String memPwd) {
		Member member = new Member();
		member = dao.signin(memAcc, memPwd);
		return member;
	}

	public Member meminfo(Integer memId) {
		return dao.getById(memId);

	}

	public Member getById(Integer memId) {
		return dao.getById(memId);
	}
	public List<Member> getAllByAcc(String memAcc) {
		return dao.getAllByAcc(memAcc);

	}
	public Member forget1(String memAcc, String memPwd) {
		Member member = new Member();

		member.setMemAcc(memAcc);
		member.setMemPwd(memPwd);
		dao.update3(member);

		return member;
	}

	public Member changepwd(Integer memId, String memPwd) {
		Member member = new Member();

		member.setMemId(memId);
		member.setMemPwd(memPwd);
		dao.update4(member);

		return member;
	}
	public Member updmemPoint(Integer memId, Integer memPoint) {
		Member member = new Member();

		member.setMemId(memId);
		member.setMemPoint(memPoint);
		dao.update5(member);

		return member;
	}

	public Member srhacc(String memacc) {
		return dao.srhacc(memacc);
	}

	public Integer srhmail(String memmail) {
		return dao.srhmail(memmail);
	}
}