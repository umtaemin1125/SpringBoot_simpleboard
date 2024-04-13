package com.mysite.springmemberboard.board;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BoardMapper {

	@Select("select count(*) from smemberboard")
	public int getListCount();
	
	@Select("select * from smemberboard order by re_ref desc, re_seq asc limit #{page}, #{limit}")
	public List<BoardVO> getBoardList(@Param("page") int page, @Param("limit") int limit);
	// page에 limit 개수만큼 추출하게 된다. (게시글 수를 추출)

	@Select("select IFNULL(max(num),0) from smemberboard")
	public int getMaxNum();
	
	@Insert("insert into smemberboard values(null,#{id},#{subject},#{content},#{org_file},"+
	"#{up_file},#{re_ref},#{re_lev},#{re_seq},#{readcount},now())")
	public boolean boardInsert(BoardVO board);
	
	@Update("update smemberboard set readcount=readcount+1 where num=#{num}")
	public void setReadCountUpdate(@Param("num") int num); //글 조회수 1 증가
	
	@Select("select * from smemberboard where num=#{num}")
	public BoardVO getDetail(@Param("num") int num); // 글 조회 
	
	@Select("select count(*) from smemberboard where num=#{num} and id= #{id}")
	public boolean isBoardWriter(@Param("num") int num, @Param("id") String id);
	//값이 있을때 true , 없을때 null=> false count(*)도 사용 가능.
	
	@Update("update smemberboard set subject=#{subject}, content=#{content} where num=#{num}")
	public boolean boardModify(BoardVO modifyboard);
	
	@Delete("delete from smemberboard where num=#{num}")
	public boolean boardDelete(@Param("num") int num);

	@Update("update smemberboard set re_seq=re_seq+1 where re_ref=#{re_ref} and re_seq>#{re_seq}")
	public int boardReplyUpdate_reseq(BoardVO board);
	
	@Insert("insert into smemberboard (id, subject, content, re_ref, re_lev, re_seq, readcount,boarddate)"
			+ "values(#{id},#{subject},#{content},#{re_ref},#{re_lev},#{re_seq},#{readcount},now())")
	public int boardReply(BoardVO board);


}
