package action.Question;

import java.util.List;
import java.util.Map;
import action.baseAction.IBaseAction;
import service.Question.QuestionService;

public class QuestionAction implements IBaseAction{
	private QuestionService questionservice = new QuestionService();

	public int save(Object object) throws Exception{
		return questionservice.save(object);		
	}
	
	public int update(Object object) throws Exception{
		return questionservice.update(object);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		return questionservice.getAllInfo();
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		return questionservice.getInfoById(id);
	}
	
	public int delete(Long id) throws Exception{
		return questionservice.delete(id);
	}
}