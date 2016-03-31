package com.allianz.drdc24.controllers;

import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.allianz.drdc24.models.App;
import com.allianz.drdc24.services.LookupService;

public class DashboardController extends BaseWebFlowController {
	private static final long serialVersionUID = 6848148192857690277L;
		
	@Autowired LookupService lookupService;
	
	public void onInit() throws Exception {

		LazyDataModel<App> appDataModel = new LazyDataModel<App>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<App> load(int first, int pageSize, String sortField, 
				SortOrder sortOrder, Map<String, Object> filters) {
				List<App> res = lookupService.appListAll(
					flowScope.getString("searchText"), first, pageSize*10+1, 
					sortField, sortOrder == SortOrder.ASCENDING);
				this.setRowCount(lookupService.appCount(flowScope.getString("searchText")));
				return res;
			}
		};
		
		flowScope.put("appDataModel", appDataModel);
	}
	
    @SuppressWarnings("unchecked")
	public static <T extends UIComponent> T getComponent(String componentName) {
        return (T)FacesContext.getCurrentInstance().getViewRoot().findComponent(componentName);
    }

	public void search() { 
		DataTable dataTable = getComponent("form:dataList"); 
		dataTable.setFirst(0);
		dataTable.loadLazyData();
	}	
}
