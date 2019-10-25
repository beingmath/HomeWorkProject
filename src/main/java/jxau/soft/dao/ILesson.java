package jxau.soft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jxau.soft.pojo.Lesson;
public interface ILesson {
	public void insertLesson(Lesson lesson);
	public List<Lesson> selectLessonByStudentname(@Param("studentname") String studentname);
	public List<Lesson> selectLesson(String username);
	public Lesson selectLessonById(int id);
	public List<Lesson> selectLessonBySearchMsg(@Param("lessonName")String lessonName);
}
