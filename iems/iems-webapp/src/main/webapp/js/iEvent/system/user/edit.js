/**
 * iEvent.system.user.edit.js
 */
$(function() {
	
	var sUserid = iEvent.query_string("userid");
	
	var sGetUrl = iEvent.api_url("/v1/users"+"/"+sUserid);
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
					'sysUser.username': ['not_empty'],
					'sysUser.email': ['not_empty', 'email'], 
					'sysUser.mobile': ['not_empty', {'regex': /^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/i}], 
					'sysUser.password': [{'min_length': 6}], 
					'passcheck': [{matches: 'sysUser.password'}]
	    		},
	    		messages: {
	    			'sysUser.mobile': {'regex': 'Please enter a valid mobile.'}
	    		}
	    	}
	    );
	
	var fnPutCallback = function(oData) {
		alert(oData.msg)
	}
	
	$('#form_data').submit(function(){
		if (!oValid.validate()) return false;
		
		var oData = iEvent.form2json($(this));
		oData.isNew = false;
		//alert(JSON.stringify(oData));
		
		var sPutUrl = iEvent.api_url("/v1/users"+"/"+sUserid);
		
		iEvent.put(sPutUrl, JSON.stringify(oData), fnPutCallback);
		
		parent.window.location = parent.window.location;
		
		return false;
	});
    
    
});
