/**
 * iEvent.biz.eventinfo.add.js
 */
$(function() {
	
	var sVersion = "v1";
	var sResource = "eventInfos";

	var companyid = iEvent.current_user_companyid;
	
	if (companyid == 0) {
		$.scojs_message('您不是企业管理人员，无法处理', $.scojs_message.TYPE_WARN, function(){
			parent.window.location.reload();
		});
		return;
	}
	
	var oDataInit = {'eventInfo': {'companyid': companyid}};
	iEvent.json2form(oDataInit, $('#form_data'));

	var oValid =
    $.scojs_valid('#form_data', 
		{
    		wrapper: "div.form_group",
			rules: {
				'eventInfo.name': ['not_empty'],
				'eventInfo.memo': [{'max_length': 200}]
    		},
    		messages: {
    			
    		}
    	}
    );
	
	var fnPostCallback = function(oData) {
		if (oData.result) {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_OK, function(){
				parent.window.location.reload();
			});
		} else {
			$.scojs_message(oData.msg, $.scojs_message.TYPE_ERROR);
		}			
	}
	
	$('#form_data').submit(function(){
		if (!oValid.validate()) return false;
		
		var oData = iEvent.form2json($(this), ['_wysihtml5_mode']);
		oData.isNew = true;
		//alert(JSON.stringify(oData));
		
		var sPostUrl = iEvent.api_url(['',sVersion,sResource].join('/'));
		
		iEvent.post(sPostUrl, JSON.stringify(oData), fnPostCallback);
		
		return false;
	});
    
    
});
