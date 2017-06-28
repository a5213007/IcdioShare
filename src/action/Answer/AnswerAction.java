package action.Answer;

import java.util.List;
import java.util.Map;
import action.baseAction.IBaseAction;
import service.Answer.AnswerService;

public class AnswerAction implements IBaseAction{
	private AnswerService answerservice = new AnswerService();

	public int save(Object object) throws Exception{
		return answerservice.save(object);		
	}
	
	public int update(Object object) throws Exception{
		return answerservice.update(object);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		return answerservice.getAllInfo();
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		return answerservice.getInfoById(id);
	}
	
	public int delete(Long id) throws Exception{
		return answerservice.delete(id);
	}
}