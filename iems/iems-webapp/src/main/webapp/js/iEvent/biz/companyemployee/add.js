/**
 * iEvent.biz.companyemployee.add.js
 */
$(function() {
	
	var sVersion = "v1";
	var sResource = "companyEmployees";

	var companyid = iEvent.current_user_companyid;
	
	if (companyid == 0) {
		$.scojs_message('您不是企业管理人员，无法处理', $.scojs_message.TYPE_WARN, function(){
			parent.window.location = parent.window.location;
		});
		return;
	}
	
	var oDataInit = {'companyEmployee': {'companyid': companyid}};
	iEvent.json2form(oDataInit, $('#form_data'));

	var oValid =
    $.scojs_valid('#form_data', 
		{
    		wrapper: "div.form_group",
			rules: {
				'companyEmployee.name': ['not_empty'],
				'sysUser.username': ['not_empty'],
				'sysUser.email': ['not_empty', 'email'], 
				'sysUser.mobile': ['not_empty', {'regex': /^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/i}], 
				'sysUser.password': ['not_empty', {'min_length': 6}], 
				'passcheck': [{matches: 'sysUser.password'}]
    		},
    		messages: {
    			'sysUser.mobile': {'regex': 'Please enter a valid mobile.'}
    		}
    	}
    );
	
	var fnPostCallback = function(oData) {
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
		oData.isNew = true;
		//alert(JSON.stringify(oData));
		
		var sPostUrl = iEvent.api_url(['',sVersion,sResource].join('/'));
		
		iEvent.post(sPostUrl, JSON.stringify(oData), fnPostCallback);
		
		return false;
	});
    
    
});
