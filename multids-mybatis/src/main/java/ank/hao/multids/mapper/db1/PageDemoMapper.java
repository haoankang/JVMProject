package ank.hao.multids.mapper.db1;

import ank.hao.multids.domain.PageDemo;
import ank.hao.multids.domain.PageDemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageDemoMapper {
    long countByExample(PageDemoExample example);

    int deleteByExample(PageDemoExample example);

    int deleteByPrimaryKey(Integer c1);

    int insert(PageDemo record);

    int insertSelective(PageDemo record);

    List<PageDemo> selectByExample(PageDemoExample example);

    PageDemo selectByPrimaryKey(Integer c1);

    int updateByExampleSelective(@Param("record") PageDemo record, @Param("example") PageDemoExample example);

    int updateByExample(@Param("record") PageDemo record, @Param("example") PageDemoExample example);

    int updateByPrimaryKeySelective(PageDemo record);

    int updateByPrimaryKey(PageDemo record);
}