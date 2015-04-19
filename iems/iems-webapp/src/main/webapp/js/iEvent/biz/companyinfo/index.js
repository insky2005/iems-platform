/**
 * iEvent.system.user.edit.js
 */
$(function() {
	
	var sVersion = "v1";
	var sResource = "companyInfos";
	
	var companyid = iEvent.current_user_companyid;

	if (companyid == 0) {
		$.scojs_message('您不是企业管理人员，无法处理', $.scojs_message.TYPE_WARN, function(){
			window.location.href = iEvent.host+'/error.do';
		});
		return;
	}

	var sId = companyid;
	
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
				//window.location = parent.window.location;
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
		
		var sPutUrl = iEvent.api_url(['',sVersion,sResource, sId].join('/'));
		
		iEvent.put(sPutUrl, JSON.stringify(oData), fnPutCallback);
		
		return false;
	});
    
    
});
