package cn.cityre.mis.city.tradeProcess.dao;


import cn.cityre.mis.city.tradeProcess.entity.po.Qd_contract;
import cn.cityre.mis.city.tradeProcess.entity.po.Qd_contract_history;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by cityre on 2017/7/18.
 */
@Repository
public interface Qd_contractDao {
    List<Qd_contract> selectQd_contractList(Map<String, Object> map);
    int selectQd_contractCount(Map<String, Object> map);
    String selectBankConameFromCoCouser(String uid);
    String selectTelNoFromUser_tel(String uid);
    String selectAgentConameFromCoCouser1(String uid);
    String selectAgentConameFromCoCouser2(String uid);
    int selectQd_historyCountByContractID(Integer id);
    int insertQd_contract_history(Map<String, Object> map);
    int updateQd_contractById(Map<String, Object> map);

    List<Qd_contract_history> selectQd_contract_historyList(Integer id);
    int selectQd_contract_historyCount(Integer id);

    String selectNameFromUser(String uid);
}
