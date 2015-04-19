/**
 * iEvent.system.user.edit.js
 */
$(function() {
	
	var sVersion = "v1";
	var sResource = "companyInfos";
	
	var sId = iEvent.query_string("id");
	
	var sGetUrl = iEvent.api_url(['',sVersion,sResource, sId].join('/'));
	var fnGetCallback = function(oData) {
		//alert(JSON.stringify(oData));
		iEvent.json2form(oData, $('#form_data'));
	}
	iEvent.get(sGetUrl, {}, fnGetCallback);
	
	
	var oValid =
	    $.scojs_valid('#form_data', 
			{
	    		wrapper: "div.form_group",
				rules: {
					'companyInfo.name': ['not_empty']
	    		},
	    		messages: {
	    			'companyInfo.name': {'not_empty': '请输入企业名称！'}
	    		}
	    	}
	    );
	
	var fnPutCallback = function(oData) {
		if (oData.result) {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_OK, function(){
				parent.window.location = parent.window.location;
			});
		} else {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_ERROR);
		}
	}
	
	$('#form_data').submit(function(){
		if (!oValid.validate()) return false;
		
		var oData = iEvent.form2json($(this));
		oData.isNew = false;
		//alert(JSON.stringify(oData));
		
		var sPutUrl = iEvent.api_url(['',sVersion,sResource,sId].join('/'));
		
		iEvent.put(sPutUrl, JSON.stringify(oData), fnPutCallback);
		
		return false;
	});
    
    
});
