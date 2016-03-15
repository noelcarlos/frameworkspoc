package es.generali.primefacespoc.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FlowScope extends HashMap<String, Object> {
	private static final long serialVersionUID = -4413134731739209932L;
	private String executionId;
	
	@SuppressWarnings("unchecked")
	public static FlowScope createOrResume(HttpServletRequest request) {
		String execution = request.getParameter("execution");
		FlowScope fs;
		HttpSession session = request.getSession();
		HashMap<String, Object> flowRepository = (HashMap<String, Object>)session.getAttribute("FLOW_REPOSITORY_IMPL");
		Integer flowIndex = (Integer)session.getAttribute("FLOW_INDEX_IMPL");
		if (flowRepository == null) {
			flowRepository = new HashMap<String, Object>(); 
			session.setAttribute("FLOW_REPOSITORY_IMPL", flowRepository);
			flowIndex = 1;
			session.setAttribute("FLOW_INDEX_IMPL", flowIndex);
		}
		if (execution == null) {
			fs = new FlowScope();
			flowIndex++;
			session.setAttribute("FLOW_INDEX_IMPL", flowIndex);
			execution = "e" + flowIndex + "s1";
		} else {
			fs = (FlowScope) flowRepository.get(execution);
			if (fs == null) {
				fs = new FlowScope();
				flowIndex++;
				session.setAttribute("FLOW_INDEX_IMPL", flowIndex);
				execution = "e" + flowIndex + "s1";
			}
		}
		fs.setExecutionId(execution);
		flowRepository.put(execution,  fs);
		return fs;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	
}
