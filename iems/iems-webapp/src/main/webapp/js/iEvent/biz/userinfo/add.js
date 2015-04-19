/**
 * iEvent.system.user.add.js
 */
$(function() {
	
	var oValid =
    $.scojs_valid('#form_data', 
		{
    		wrapper: "div.form_group",
			rules: {
				'sysUser.username': ['not_empty'],
				'sysUser.email': ['not_empty', 'email'], 
				'sysUser.mobile': ['not_empty', {'regex': /^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/i}], 
				'sysUser.password': ['not_empty', {'min_length': 6}], 
				'userInfo.name': ['not_empty'],
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
		
		var sPostUrl = iEvent.api_url("/v1/userinfos");
		
		iEvent.post(sPostUrl, JSON.stringify(oData), fnPostCallback);
		
		return false;
	});
    
    
});
