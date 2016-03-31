package com.allianz.drdc24.controllers;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.allianz.drdc24.models.App;
import com.allianz.drdc24.models.ConfiguracionBean;
import com.allianz.drdc24.services.LookupService;

public class DashboardController extends BaseWebFlowController {
	private static final long serialVersionUID = 6848148192857690277L;
		
	@Autowired LookupService lookupService;
	
	public void onInit() throws Exception {
		List<Map<String, Object>> userList = lookupService.userList();
		flowScope.put("userList", userList);
		List<Map<String, Object>> appList = lookupService.appList(null, 0, 10, null, true);
		flowScope.put("appList", appList);
		
		LazyDataModel<App> appDataModel = new LazyDataModel<App>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<App> load(int first, int pageSize, String sortField, 
				SortOrder sortOrder, Map<String, Object> filters) {
				List<App> res = lookupService.appListAll(
					flowScope.getString("searchText"), first, pageSize*10+1, 
					sortField, sortOrder == SortOrder.ASCENDING);
				this.setRowCount(first + res.size());
				
				return res;
			}
		};
		
		flowScope.put("appDataModel", appDataModel);
	}

	public boolean setup(ConfiguracionBean config) { 
		session.setAttribute("config", config);
		return true;
	}	
}
