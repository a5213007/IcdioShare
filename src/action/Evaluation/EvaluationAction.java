package action.Evaluation;

import java.util.List;
import java.util.Map;
import action.baseAction.IBaseAction;
import service.Evaluation.EvaluationService;

public class EvaluationAction implements IBaseAction{
	private EvaluationService evaluationservice = new EvaluationService();

	public int save(Object object) throws Exception{
		return evaluationservice.save(object);		
	}
	
	public int update(Object object) throws Exception{
		return evaluationservice.update(object);
	}
	
	public List<Map<String, Object>> getAllInfo() throws Exception{
		return evaluationservice.getAllInfo();
	}
	
	public List<Map<String, Object>> getInfoById(Long id) throws Exception{
		return evaluationservice.getInfoById(id);
	}
	
	public int delete(Long id) throws Exception{
		return evaluationservice.delete(id);
	}
}