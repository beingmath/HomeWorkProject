package jxau.soft.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import jxau.soft.pojo.Work;

public interface IWork {
 List<Work> selectWork(Work work);
 Integer insertWork(Work work);
 List<Work> selectWorkBylessonId(@Param("lessonid")int lessonid);
}
