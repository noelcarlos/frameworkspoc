package com.allianz.drdc24.controllers;

import java.util.ArrayList;
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
				String searchText = flowScope.getString("searchText");
				System.out.println(searchText);
				List<App> res = lookupService.appListAll(
					searchText, first, pageSize*10+1, 
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

	@SuppressWarnings("unchecked")
	public void search() { 
		DataTable dataTable = getComponent("form:dataList"); 
		dataTable.setFirst(0);
		dataTable.loadLazyData();
		
		if (dataTable.getRowCount() == 1) {
			LazyDataModel<App> appDataModel = (LazyDataModel<App>)flowScope.get("appDataModel");
			flowScope.put("nextView", "edit");
			flowScope.put("selecedItem", ((List<App>)appDataModel.getWrappedData()).get(0).getId());
		}
		
	}
	
	public List<String> appAutoComplete(String query) {
		List<String> res = new ArrayList<String>();
		for (App a : lookupService.appListAll(query, 0, 20, null, true)) {
			res.add(a.getName());
		}
		return res;
    }
}
