package com.code.model.Code.dao;

import com.code.model.Code.pojo.Code;
import com.core.dao.CoreDao;

import java.util.List;

public interface CodeDAO_interface extends CoreDao<Code,Integer> {
    List<Integer> getCodeId(String codeNum, Integer storeId);

    List<Code> getBy(Integer id, String string);


    List<Integer> getBycodeNum(String codeNum, Integer storeId);
}
